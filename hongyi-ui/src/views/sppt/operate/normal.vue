<template>
  <el-row>
    <el-col :span="12">
      文件地址：D:\myapps\storage\superuser.json
      <el-input v-model="miwen" @change="handleJieMi" />
      密码：{{ pwd }}
      <div> 用户密码表： sppt T_ACCOUNT</div>
      <div>
        <span class="changeline">修改某菜单名称（软件修改不成功需要到对应的文件中去改） 例：</span>
        <span class="changeline">
          D:\myapps\storage\workspace\华能山东如意巴基斯坦审批平台.application\安全生产类 Safety
          Product.menu\安反措计划实施验收单（停用).menu
        </span>
        <span class="changeline"> 修改完要重构索引</span>
      </div>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import { SpptApi } from '@/api/hy/sppt'

/** 我的日记 列表 */
defineOptions({ name: 'normal' })
const miwen = ref('')
const pwd = ref('')

/** 删除按钮操作 */
const handleJieMi = async () => {
  pwd.value = await SpptApi.getpwd(miwen.value)
}

/** 初始化 **/
onMounted(() => {
  // getList()
})
</script>

<style scoped lang="scss">
.changeline {
  display: block;
  margin: 4px 0;
}
</style>
