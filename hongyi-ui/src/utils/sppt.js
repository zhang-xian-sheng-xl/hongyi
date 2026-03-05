// src/utils/clipboard.ts
/**
 * 复制文本到剪贴板
 * @param text - 需要复制的文本
 * @returns Promise<void> - 成功时无返回值，失败时抛出错误
 */
export const copyToClipboard = (text) => {
  try {
    navigator.clipboard.writeText(text)
    console.log('复制成功')
  } catch (err) {
    console.error('复制失败:', err)
    throw new Error('复制失败，请检查浏览器权限或环境')
  }
}
/**
 * 清理字符串中的指定内容
 * @param {string} input - 待清理的字符串
 * @returns {string} 清理后的字符串
 */
export const cleanQkqzValue = (input) => {
  // 边界条件检查
  if (typeof input !== 'string') return ''

  // 定义需要移除的模式（注意转义特殊字符）
  const patternsToRemove = [
    /,""/g, // 匹配 ,""
    /,true/g, // 匹配 ,true
    /item_/g, // 匹配 item_
    /= ''/g,
    /=''/g // 匹配 = ''
  ]

  // 依次应用所有模式的替换
  let result = input
  patternsToRemove.forEach((pattern) => {
    result = result.replace(pattern, '')
  })
  result = result.replace('ClearSignatureLogic', 'clearsign')
  result = result.replace(/，/g, ',');
  //todo 如果不存在clearsign 则添加clearsign(“+result+")
  if (!result.includes('clearsign')) {
    result = 'clearsign("' + result + '")'
  }
  return result
}
