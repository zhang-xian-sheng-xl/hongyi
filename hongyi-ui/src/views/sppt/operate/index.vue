<template xmlns:el-col="http://www.w3.org/1999/html">
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-tabs v-model="activeTab">
      <el-tab-pane label="表单" name="form">
        <div>
          <el-row>
            <el-divider content-position="center">清空签字</el-divider>
            <el-col :span="12">
              <el-input v-model="qkqz" @blur="clearAndAssign" placeholder="请输入相关字段" />

              <br />
              <span class="sppt">{{ qkqzval }}</span>
              <el-button @click="copyToClipboard(qkqzval)" type="primary" size="small"
                >复制</el-button
              >
            </el-col>
          </el-row>
        </div>
        <el-divider content-position="center">签字按钮</el-divider>
        <div style="margin-top: 10px">
          <el-row>
            <el-col :span="5">
              <el-input
                v-model="srk"
                @blur="srkradio"
                placeholder="输入框名称"
                style="width: 70%"
              />
              <br />名称<span class="sppt">{{ srk }}</span>
              <el-button @click="copyToClipboard(srk)" type="primary" size="small">复制</el-button
              ><br />

              隐藏条件 <span class="sppt">{{ srkyc }}</span>
              <el-button @click="copyToClipboard(srkyc)" type="primary" size="small">复制</el-button
              ><br />
              只读条件 <span class="sppt">true</span>
              <el-button @click="copyToClipboard(true)" type="primary" size="small">复制</el-button
              ><br />
            </el-col>
            <el-col :span="5">
              计算脚本 <br />
              名称 <span class="sppt">{{ jsjbmc }}</span>
              <el-button @click="copyToClipboard(jsjbmc)" type="primary" size="small"
                >复制</el-button
              ><br />

              脚本 <span class="sppt">{{ jsjbjb }}</span>
              <el-button @click="copyToClipboard(jsjbjb)" type="primary" size="small"
                >复制</el-button
              ><br />
            </el-col>
            <el-col :span="5">
              签字按钮<br />
              按钮名称 签字A 不重复<br />
              说明文字 签字A 不重复<br />
              隐藏条件 <span class="sppt">{{ yctj }}</span>
              <el-button @click="copyToClipboard(yctj)" type="primary" size="small">复制</el-button
              ><br />
              动作执行前脚本
              <span class="sppt">{{ signdzzxqjb }}</span>
              <el-button @click="copyToClipboard(signdzzxqjb)" type="primary" size="small"
                >复制</el-button
              ><br />
            </el-col>
            <el-col :span="5">
              <el-checkbox v-model="bhyj" @change="handleCheckboxChange">包含意见</el-checkbox>
              <div v-show="bhyj">
                <el-input
                  @blur="handleCheckboxChange"
                  v-model="yjmc"
                  placeholder="请输入意见名称"
                />
                意见名称<span class="sppt">{{ yjmc }}</span>
                <el-button @click="copyToClipboard(yjmc)" type="primary" size="small"
                  >复制</el-button
                ><br />
              </div>
            </el-col>
            <el-col :span="4">
              <el-checkbox v-model="bhrq" @change="handleriqiCheckboxChange">包含日期</el-checkbox>
              <div v-show="bhrq">
                <el-input
                  @blur="handleriqiCheckboxChange"
                  v-model="rqmc"
                  placeholder="请输入日期名称"
                />
                日期名称<span class="sppt">{{ rqmc }}</span>
                <el-button @click="copyToClipboard(rqmc)" type="primary" size="small"
                  >复制</el-button
                ><br />
              </div>
            </el-col>
          </el-row>
          <el-divider content-position="center">其他</el-divider>
          <el-row>
            <el-col :span="4">
              必填字段<br />
              编号
              <el-button @click="copyToClipboard('编号')" type="primary" size="small"
                >复制</el-button
              ><br />
              只读 <span class="sppt">true</span
              ><el-button @click="copyToClipboard(true)" type="primary" size="small">复制</el-button
              ><br />
            </el-col>
            <el-col :span="5">
              编制人
              <el-button @click="copyToClipboard('编制人')" type="primary" size="small"
                >复制</el-button
              ><br />
              默认值 <span class="sppt">defaultbzr('编制人')</span>
              <el-button
                @click="copyToClipboard('defaultbzr(\'编制人\')')"
                type="primary"
                size="small"
              >
                复制
              </el-button>
              <br />

              隐藏
            </el-col>
            <el-col :span="6">
              编制日期
              <el-button @click="copyToClipboard('编制日期')" type="primary" size="small"
                >复制</el-button
              ><br />
              默认值 <span class="sppt">defaultDateYMD('编制日期')</span>
              <el-button
                @click="copyToClipboard('defaultDateYMD(\'编制日期\')')"
                type="primary"
                size="small"
              >
                复制
              </el-button>
              <br />

              隐藏
            </el-col>
            <el-col :span="4">
              中文标题 <span class="sppt">fromname();</span>
              <el-button @click="copyToClipboard('fromname();')" type="primary" size="small">
                复制</el-button
              ><br />
              英文标题 <span class="sppt">englishname();</span
              ><el-button @click="copyToClipboard('englishname();')" type="primary" size="small">
                复制</el-button
              >
            </el-col>
            <el-col :span="4">
              数字格式 <span class="sppt">#,##0.00</span>
              <el-button @click="copyToClipboard('#,##0.00')" type="primary" size="small">
                复制</el-button
              ><br />
            </el-col>
          </el-row>
          <el-divider content-position="center">操作按钮</el-divider>
          <el-row>
            <el-col :span="8">
              <span class="sppt">保存并启动流程 Save and start the process</span>

              <el-button
                @click="copyToClipboard('保存并启动流程 Save and start the process')"
                type="primary"
                size="small"
              >
                复制</el-button
              >
              <br />
              表单简写
              <el-input v-model="bdjx" placeholder="请输入表单简写" />
              动作执行前脚本： <span class="sppt">generateBianhao('编号','{{ bdjx }}')</span>
              <el-button
                @click="copyToClipboard(`generateBianhao('编号','${bdjx}')`)"
                type="primary"
                size="small"
              >
                复制</el-button
              >
              <br />
              隐藏条件：<span class="sppt">flowStartHide()</span>
              <el-button @click="copyToClipboard('flowStartHide();')" type="primary" size="small">
                复制</el-button
              >
            </el-col>
            <el-col :span="2">
              <span class="sppt">保存 Save</span>
              <el-button @click="copyToClipboard('保存 Save')" type="primary" size="small"
                >复制</el-button
              ><br />动作选择保存</el-col
            >
            <el-col :span="5">
              <span class="sppt">提交 Submit</span>
              <el-button @click="copyToClipboard('提交 Submit')" type="primary" size="small"
                >复制</el-button
              ><br />
              选择对应的作用流程<br />隐藏条件 :<span class="sppt">submitHide()</span>
              <el-button @click="copyToClipboard('submitHide()')" type="primary" size="small">
                复制</el-button
              ></el-col
            >
            <el-col :span="5">
              <span class="sppt">打印导出 Export</span>
              <el-button @click="copyToClipboard('打印导出 Export')" type="primary" size="small"
                >复制</el-button
              ><br />1 动作是跳转 <br />2 类型 跳转到制定url <br />3 地址脚本
              <span class="sppt">exportPdf('{{ bdjx }}')</span>
              <el-button
                @click="copyToClipboard(`exportPdf(\'${bdjx}')`)"
                type="primary"
                size="small"
                >复制</el-button
              >
            </el-col>
            <el-col :span="3">
              <span class="sppt">返回 Return</span>
              <el-button @click="copyToClipboard('返回 Return')" type="primary" size="small"
                >复制</el-button
              ><br />
              动作 返回
            </el-col>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="视图-流程" name="process">
        <el-divider content-position="center">视图</el-divider>
        <el-row>
          <el-col :span="6">
            视图 按钮 动作执行前脚本：<span class="sppt">adminDelete();</span>
            <el-button @click="copyToClipboard('adminDelete();')" type="primary" size="small">
              复制</el-button
            >
          </el-col>
        </el-row>
        <el-divider content-position="center">流程</el-divider>
        <el-row>
          <el-col :span="6">
            <el-input v-model="scjb" placeholder="请输入签字框名称" />
            路径送出校验脚本：<span class="sppt">jinru('{{ scjb }}');</span>
            <el-button @click="copyToClipboard(`jinru(('${scjb}')`)" type="primary" size="small">
              复制</el-button
            >
          </el-col>
          <el-col :span="6">
            经办人：<span class="sppt">getjbr();</span>
            <el-button @click="copyToClipboard(`getjbr();`)" type="primary" size="small">
              复制</el-button
            ><br />
            部门主任：<span class="sppt">getbmfzr();</span>
            <el-button @click="copyToClipboard(`getbmfzr();`)" type="primary" size="small">
              复制</el-button
            >
          </el-col>
        </el-row>
      </el-tab-pane>
      <!--      <el-tab-pane label="视图" name="view">-->
      <!--        <div>视图内容区域 zhang1992 zhangxianglai </div>-->
      <!--      </el-tab-pane>-->
    </el-tabs>
  </ContentWrap>
</template>

<script setup lang="ts">
/** 我的日记 列表 */
import { copyToClipboard, cleanQkqzValue } from '@/utils/sppt'

defineOptions({ name: 'Sppt' })
const activeTab = ref('form')
const qkqz = ref('')
const qkqzval = ref('')

/**
 * 清空并更新 qkqzval.value 的值
 */
const clearAndAssign = () => {
  if (typeof qkqz.value === 'string') {
    qkqzval.value = cleanQkqzValue(qkqz.value)
  } else {
    console.warn('qkqz.value 不是有效的字符串')
  }
}
const srk = ref('')
const srkyc = ref('')
const yctj = ref('')
const srkradio = () => {
  srkyc.value = "signatureUpdate('" + srk.value + "')"
  yctj.value = "hideViewBox('" + srk.value + "')"
  jsjbjb.value = "showSign('" + srk.value + "')"
  jsjbmc.value = srk.value + '计算'
  changeSigndzzxqjb()
}
//计算脚本
const jsjbmc = ref('')
const jsjbjb = ref('')
//包含意见
const bhyj = ref(false)
const yjmc = ref('')

/**
 * 复选框状态改变时的处理函数
 * @param checked - 当前复选框的状态（true/false）
 */
const handleCheckboxChange = (checked: boolean) => {
  if (checked) {
    bhyj.value = true
    // console.log('复选框被选中')
    if (yjmc.value === '') {
      yjmc.value = srk.value + '意见'
    }
  } else {
    bhyj.value = false
    // 可以在这里添加取消选中后的逻辑，例如清空意见名称
    yjmc.value = ''
  }
  changeSigndzzxqjb()
}
//包含日期
const bhrq = ref(false)
const rqmc = ref('')
//签字动作执行前脚本
const signdzzxqjb = ref('')
const changeSigndzzxqjb = () => {
  signdzzxqjb.value = "sign('" + srk.value + "')"

  if (bhyj.value) {
    signdzzxqjb.value = signdzzxqjb.value + '\n' + "defaultAgree('" + yjmc.value + "')"
  }
  if (bhrq.value) {
  }
  // signdzzxqjb.value = "showSign('" + srk.value + "')"
}
/**
 * 复选框状态改变时的处理函数
 * @param checked - 当前复选框的状态（true/false）
 */
const handleriqiCheckboxChange = (checked: boolean) => {
  if (checked) {
    bhrq.value = true
    if (rqmc.value === '') {
      rqmc.value = srk.value + '日期'
    }
  } else {
    bhrq.value = false
    // 可以在这里添加取消选中后的逻辑，例如清空意见名称
    rqmc.value = ''
  }
  changeSigndzzxqjb()
}
const bdjx = ref('')
const scjb = ref('')
/** 初始化 **/
onMounted(() => {
  // getList()
})
</script>

<style scoped>
.sppt {
  color: #af4032;
  font-weight: bold;
}
</style>
