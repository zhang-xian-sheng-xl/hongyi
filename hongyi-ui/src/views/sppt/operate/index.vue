<template xmlns:el-col="http://www.w3.org/1999/html">
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-tabs v-model="activeTab">
      <el-tab-pane label="表单" name="form">
        <el-card class="section-card" shadow="never">
          <template #header>清空签字</template>
          <el-input v-model="qkqz" @blur="clearAndAssign" placeholder="请输入相关字段" style="max-width: 400px" />
          <div class="code-row">
            <span class="label">生成结果：</span>
            <span class="sppt">{{ qkqzval }}</span>
            <el-button @click="copyToClipboard(qkqzval)" type="primary" size="small">复制</el-button>
          </div>

          <div class="radio-area">
            <div class="label" style="margin-bottom: 8px; font-weight: 600">选择签字项（自动填入下方输入框）：</div>
            <el-radio-group v-model="selectedQkqzItem" @change="onQkqzItemChange">
              <el-radio v-for="item in qkqzItems" :key="item" :value="item">{{ item }}</el-radio>
            </el-radio-group>
            <template v-if="selectedQkqzItem">
              <div class="code-row" style="margin-top: 10px">
                <span class="label">当前选中：</span>
                <span class="sppt">{{ selectedQkqzItem }}</span>
                <el-button @click="copyToClipboard(selectedQkqzItem)" type="primary" size="small">复制</el-button>
              </div>
            </template>
          </div>
        </el-card>
        <el-card class="section-card" shadow="never">
          <template #header>签字按钮</template>
          <el-row class="row-gutter">
            <el-col :span="5">
              <el-input v-model="srk" @blur="srkradio" placeholder="输入框名称" />
              <div class="code-row"><span class="label">名称</span><span class="sppt">{{ srk }}</span><el-button @click="copyToClipboard(srk)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">隐藏条件</span><span class="sppt">{{ srkyc }}</span><el-button @click="copyToClipboard(srkyc)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">只读条件</span><span class="sppt">true</span><el-button @click="copyToClipboard(true)" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="5">
              <div class="label" style="font-weight: 600; margin-bottom: 6px">计算脚本</div>
              <div class="code-row"><span class="label">名称</span><span class="sppt">{{ jsjbmc }}</span><el-button @click="copyToClipboard(jsjbmc)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">脚本</span><span class="sppt">{{ jsjbjb }}</span><el-button @click="copyToClipboard(jsjbjb)" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="5">
              <div class="label" style="font-weight: 600; margin-bottom: 6px">签字按钮</div>
              <div class="label" style="font-size: 12px; color: #909399">按钮名称：签字A（不重复）<br />说明文字：签字A（不重复）</div>
              <div class="code-row"><span class="label">隐藏条件</span><span class="sppt">{{ yctj }}</span><el-button @click="copyToClipboard(yctj)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">动作前脚本</span><span class="sppt">{{ signdzzxqjb }}</span><el-button @click="copyToClipboard(signdzzxqjb)" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="5">
              <el-checkbox v-model="bhyj" @change="handleCheckboxChange">包含意见</el-checkbox>
              <div v-show="bhyj" style="margin-top: 6px">
                <el-input @blur="handleCheckboxChange" v-model="yjmc" placeholder="意见名称" size="small" />
                <div class="code-row"><span class="label">意见名称</span><span class="sppt">{{ yjmc }}</span><el-button @click="copyToClipboard(yjmc)" type="primary" size="small">复制</el-button></div>
              </div>
            </el-col>
            <el-col :span="4">
              <el-checkbox v-model="bhrq" @change="handleriqiCheckboxChange">包含日期</el-checkbox>
              <div v-show="bhrq" style="margin-top: 6px">
                <el-input @blur="handleriqiCheckboxChange" v-model="rqmc" placeholder="日期名称" size="small" />
                <div class="code-row"><span class="label">日期名称</span><span class="sppt">{{ rqmc }}</span><el-button @click="copyToClipboard(rqmc)" type="primary" size="small">复制</el-button></div>
              </div>
            </el-col>
          </el-row>
        </el-card>
        <el-card class="section-card" shadow="never">
          <template #header>其他</template>
          <el-row class="row-gutter">
            <el-col :span="4">
              <div class="label" style="font-weight: 600; margin-bottom: 6px">必填字段</div>
              <div class="code-row"><span class="label">编号</span><el-button @click="copyToClipboard('编号')" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">只读</span><span class="sppt">true</span><el-button @click="copyToClipboard(true)" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="5">
              <div class="label" style="font-weight: 600; margin-bottom: 6px">编制人</div>
              <div class="code-row"><span class="label">名称</span><el-button @click="copyToClipboard('编制人')" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">默认值</span><span class="sppt">defaultbzr('编制人')</span><el-button @click="copyToClipboard(`defaultbzr('编制人')`)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">隐藏</span></div>
            </el-col>
            <el-col :span="6">
              <div class="label" style="font-weight: 600; margin-bottom: 6px">编制日期</div>
              <div class="code-row"><span class="label">名称</span><el-button @click="copyToClipboard('编制日期')" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">默认值</span><span class="sppt">defaultDateYMD('编制日期')</span><el-button @click="copyToClipboard(`defaultDateYMD('编制日期')`)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">隐藏</span></div>
            </el-col>
            <el-col :span="4">
              <div class="code-row"><span class="label">中文标题</span><span class="sppt">fromname();</span><el-button @click="copyToClipboard('fromname();')" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">英文标题</span><span class="sppt">englishname();</span><el-button @click="copyToClipboard('englishname();')" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="4">
              <div class="code-row"><span class="label">数字格式</span><span class="sppt">#,##0.00</span><el-button @click="copyToClipboard('#,##0.00')" type="primary" size="small">复制</el-button></div>
            </el-col>
          </el-row>
        </el-card>
        <el-card class="section-card" shadow="never">
          <template #header>操作按钮</template>
          <el-row class="row-gutter">
            <el-col :span="8">
              <div class="code-row">
                <span class="label">保存并启动流程</span>
                <span class="sppt">Save and start the process</span>
                <el-button @click="copyToClipboard('保存并启动流程 Save and start the process')" type="primary" size="small">复制</el-button>
              </div>
              <div class="label" style="margin-top: 8px">表单简写</div>
              <el-input v-model="bdjx" placeholder="请输入表单简写" size="small" style="max-width: 200px" />
              <div class="code-row"><span class="label">动作前脚本</span><span class="sppt">generateBianhao('编号','{{ bdjx }}')</span><el-button @click="copyToClipboard(`generateBianhao('编号','${bdjx}')`)" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">隐藏条件</span><span class="sppt">flowStartHide()</span><el-button @click="copyToClipboard('flowStartHide();')" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="4">
              <div class="code-row">
                <span class="label">保存</span>
                <span class="sppt">Save</span>
                <el-button @click="copyToClipboard('保存 Save')" type="primary" size="small">复制</el-button>
              </div>
              <div class="label" style="font-size: 12px; color: #909399">动作选择：保存</div>
            </el-col>
            <el-col :span="6">
              <div class="code-row">
                <span class="label">提交</span>
                <span class="sppt">Submit</span>
                <el-button @click="copyToClipboard('提交 Submit')" type="primary" size="small">复制</el-button>
              </div>
              <div class="label" style="font-size: 12px; color: #909399">选择对应的作用流程</div>
              <div class="code-row"><span class="label">隐藏条件</span><span class="sppt">submitHide()</span><el-button @click="copyToClipboard('submitHide()')" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="6">
              <div class="code-row">
                <span class="label">打印导出</span>
                <span class="sppt">Export</span>
                <el-button @click="copyToClipboard('打印导出 Export')" type="primary" size="small">复制</el-button>
              </div>
              <div class="label" style="font-size: 12px; color: #909399">① 动作：跳转　② 类型：跳到指定URL</div>
              <div class="code-row"><span class="label">地址脚本</span><span class="sppt">exportPdf('{{ bdjx }}')</span><el-button @click="copyToClipboard(`exportPdf('${bdjx}')`)" type="primary" size="small">复制</el-button></div>
            </el-col>
            <el-col :span="4">
              <div class="code-row">
                <span class="label">返回</span>
                <span class="sppt">Return</span>
                <el-button @click="copyToClipboard('返回 Return')" type="primary" size="small">复制</el-button>
              </div>
              <div class="label" style="font-size: 12px; color: #909399">动作选择：返回</div>
            </el-col>
          </el-row>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="视图-流程" name="process">
        <el-card class="section-card" shadow="never">
          <template #header>视图</template>
          <div class="code-row">
            <span class="label">按钮动作执行前脚本</span>
            <span class="sppt">adminDelete();</span>
            <el-button @click="copyToClipboard('adminDelete();')" type="primary" size="small">复制</el-button>
          </div>
        </el-card>

        <el-card class="section-card" shadow="never">
          <template #header>流程</template>
          <el-row class="row-gutter">
            <el-col :span="12">
              <el-input v-model="scjb" placeholder="请输入签字框名称" style="max-width: 300px" />
              <div class="code-row">
                <span class="label">路径送出校验（单个）</span>
                <span class="sppt">jinru('{{ scjb }}');</span>
                <el-button @click="copyToClipboard(`jinru('${scjb}');`)" type="primary" size="small">复制</el-button>
              </div>

              <div class="radio-area" v-if="qkqzItems.length > 0">
                <div class="label" style="margin-bottom: 8px; font-weight: 600">批量生成（来自 clearsign 解析结果）：</div>
                <div v-for="item in qkqzItems" :key="item" class="code-row">
                  <span class="sppt">jinru('{{ item }}');</span>
                  <el-button @click="copyToClipboard(`jinru('${item}');`)" type="primary" size="small">复制</el-button>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="code-row"><span class="label">经办人</span><span class="sppt">getjbr();</span><el-button @click="copyToClipboard('getjbr();')" type="primary" size="small">复制</el-button></div>
              <div class="code-row"><span class="label">部门主任</span><span class="sppt">getbmfzr();</span><el-button @click="copyToClipboard('getbmfzr();')" type="primary" size="small">复制</el-button></div>
            </el-col>
          </el-row>
        </el-card>
      </el-tab-pane>
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
 * 解析 qkqzval.value 中 clearsign 函数第一个引号内的签字项列表
 * 例如: clearsign("会签一,会签二", "", true) -> ["会签一", "会签二"]
 */
const qkqzItems = computed<string[]>(() => {
  if (!qkqzval.value) return []
  // 匹配第一个双引号内的内容
  const match = qkqzval.value.match(/^[^(]*\("([^"]*)"/)
  if (match && match[1]) {
    return match[1]
      .split(',')
      .map((s) => s.trim())
      .filter(Boolean)
  }
  return []
})

/** 当前选中的清空签字项 */
const selectedQkqzItem = ref('')

/**
 * 清空签字单选框选中变化时的处理：
 * 将选中值赋给 srk，并自动触发 srkradio 生成相关代码
 */
const onQkqzItemChange = () => {
  if (selectedQkqzItem.value) {
    srk.value = selectedQkqzItem.value
    srkradio()
  }
}

/**
 * 清空并更新 qkqzval.value 的值
 */
const clearAndAssign = () => {
  if (typeof qkqz.value === 'string') {
    qkqzval.value = cleanQkqzValue(qkqz.value)
    // 重置选中项（当 qkqzval 更新时）
    selectedQkqzItem.value = qkqzItems.value[0] || ''
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
/* 代码文字高亮 - 等宽字体 + 背景卡片 */
.sppt {
  display: inline-block;
  font-family: 'Cascadia Code', 'Fira Code', Consolas, Monaco, monospace;
  font-size: 13px;
  color: #c7254e;
  background: #f9f2f4;
  padding: 2px 8px;
  border-radius: 4px;
  word-break: break-all;
  line-height: 1.6;
}

/* 统一一行：标签 + 代码 + 复制按钮 */
.code-row {
  display: flex;
  align-items: center;
  gap: 6px;
  margin: 6px 0;
  flex-wrap: wrap;
}
.code-row .label {
  font-size: 13px;
  color: #606266;
  white-space: nowrap;
}

/* 分组卡片 */
.section-card {
  margin-bottom: 16px;
}
.section-card :deep(.el-card__header) {
  padding: 10px 16px;
  font-weight: 600;
  color: #303133;
}

/* el-col 之间加间距 */
.row-gutter > .el-col {
  padding-left: 8px;
  padding-right: 8px;
  margin-bottom: 12px;
}

/* 单选框区域 */
.radio-area {
  padding: 12px 16px;
  background: #f5f7fa;
  border-radius: 6px;
  margin-top: 10px;
}
</style>
