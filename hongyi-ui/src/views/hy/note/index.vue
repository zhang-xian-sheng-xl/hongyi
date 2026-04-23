<template>
  <el-row :gutter="20">
    <!-- 左侧部门树 -->
    <el-col :span="4" :xs="24">
      <ContentWrap class="h-1/1">
        <doc-filet-tree @node-click="handleDeptNodeClick" />
      </ContentWrap>
    </el-col>
    <el-col :span="20" :xs="24">
      <ContentWrap>
        <!-- 搜索工作栏 -->
        <el-form
          class="-mb-15px"
          :model="queryParams"
          ref="queryFormRef"
          :inline="true"
          label-width="68px"
        >
          <el-form-item label="标题" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入标题"
              clearable
              @keyup.enter="handleQuery"
              class="!w-240px"
            />
          </el-form-item>
<!--          {{ queryParams.item }}-->
          <!--          <el-form-item label="系统" prop="item">-->
          <!--            <el-select-->
          <!--              v-model="queryParams.item"-->
          <!--              placeholder="请选择系统"-->
          <!--              clearable-->
          <!--              class="!w-240px"-->
          <!--            >-->
          <!--              <el-option-->
          <!--                v-for="dict in getStrDictOptions(DICT_TYPE.PROJECT_LIST)"-->
          <!--                :key="dict.value"-->
          <!--                :label="dict.label"-->
          <!--                :value="dict.value"-->
          <!--              />-->
          <!--            </el-select>-->
          <!--          </el-form-item>-->
          <el-form-item label="创建时间" prop="createTime">
            <el-date-picker
              v-model="queryParams.createTime"
              value-format="YYYY-MM-DD HH:mm:ss"
              type="daterange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
              class="!w-220px"
            />
          </el-form-item>
          <el-form-item>
            <el-button @click="handleQuery"
              ><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button
            >
            <el-button @click="resetQuery"
              ><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button
            >
            <el-button
              type="primary"
              plain
              @click="openForm('create')"
              v-hasPermi="['hy:note:create']"
            >
              <Icon icon="ep:plus" class="mr-5px" /> 新增
            </el-button>
            <el-button
              type="success"
              plain
              @click="handleExport"
              :loading="exportLoading"
              v-hasPermi="['hy:note:export']"
            >
              <Icon icon="ep:download" class="mr-5px" /> 导出
            </el-button>
            <el-button
              type="danger"
              plain
              :disabled="isEmpty(checkedIds)"
              @click="handleDeleteBatch"
              v-hasPermi="['hy:note:delete']"
            >
              <Icon icon="ep:delete" class="mr-5px" /> 批量删除
            </el-button>
          </el-form-item>
        </el-form>
      </ContentWrap>

      <!-- 列表 -->
      <ContentWrap>
        <el-table
          row-key="id"
          v-loading="loading"
          :data="list"
          :stripe="true"
          :show-overflow-tooltip="true"
          @selection-change="handleRowCheckboxChange"
        >
          <el-table-column type="selection" width="55" />
          <!--      <el-table-column label="主鍵id" align="center" prop="id" />-->
          <el-table-column label="标题" align="center" prop="name" />
<!--          <el-table-column label="系统" align="center" prop="item">-->
<!--            <template #default="scope">-->
<!--              <dict-tag :type="DICT_TYPE.PROJECT_LIST" :value="scope.row.item" />-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column label="内容" align="center" prop="content" />
<!--          <el-table-column label="附件" align="center" prop="annexUrl" />-->
          <el-table-column
            label="创建时间"
            align="center"
            prop="createTime"
            :formatter="dateFormatter"
            width="180px"
          />
          <el-table-column label="操作" align="center" min-width="120px">
            <template #default="scope">
              <el-button
                link
                type="primary"
                @click="openForm('update', scope.row.id)"
                v-hasPermi="['hy:note:update']"
              >
                编辑
              </el-button>
              <el-button
                link
                type="danger"
                @click="handleDelete(scope.row.id)"
                v-hasPermi="['hy:note:delete']"
              >
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </ContentWrap>

      <!-- 表单弹窗：添加/修改 -->
      <NoteForm ref="formRef" @success="getList" />
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import { getStrDictOptions, DICT_TYPE } from '@/utils/dict'
import { isEmpty } from '@/utils/is'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { NoteApi, Note } from '@/api/hy/note'
import NoteForm from './NoteForm.vue'
import DocFiletTree from './DocFileTree.vue'

/** 我的日记 列表 */
defineOptions({ name: 'Note' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<Note[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  name: undefined,
  item: undefined,
  content: undefined,
  annexUrl: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await NoteApi.getNotePage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id, queryParams.item)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await NoteApi.deleteNote(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 批量删除我的日记 */
const handleDeleteBatch = async () => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    await NoteApi.deleteNoteList(checkedIds.value)
    checkedIds.value = []
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

const checkedIds = ref<number[]>([])
const handleRowCheckboxChange = (records: Note[]) => {
  checkedIds.value = records.map((item) => item.id!)
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await NoteApi.exportNote(queryParams)
    download.excel(data, '我的日记.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}
/** 处理部门被点击 */
const handleDeptNodeClick = async (row: any) => {
  if (row === undefined) {
    queryParams.item = undefined
    await getList()
  } else {
    queryParams.item = row.id
    await getList()
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
