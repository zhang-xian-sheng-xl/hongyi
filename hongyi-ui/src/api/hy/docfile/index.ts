import request from '@/config/axios'

/** 文档分类信息 */
export interface DocFile {
          id: number; // 主鍵id
          parentId: number; // 上级
          name: string; // 标题
          numSeq: number; // 排序
    children?: DocFile[];
  }

// 文档分类 API
export const DocFileApi = {
  // 查询文档分类列表
  getDocFileList: async (params) => {
    return await request.get({ url: `/hy/doc-file/list`, params })
  },

  // 查询文档分类详情
  getDocFile: async (id: number) => {
    return await request.get({ url: `/hy/doc-file/get?id=` + id })
  },

  // 新增文档分类
  createDocFile: async (data: DocFile) => {
    return await request.post({ url: `/hy/doc-file/create`, data })
  },

  // 修改文档分类
  updateDocFile: async (data: DocFile) => {
    return await request.put({ url: `/hy/doc-file/update`, data })
  },

  // 删除文档分类
  deleteDocFile: async (id: number) => {
    return await request.delete({ url: `/hy/doc-file/delete?id=` + id })
  },


  // 导出文档分类 Excel
  exportDocFile: async (params) => {
    return await request.download({ url: `/hy/doc-file/export-excel`, params })
  },
  getDocFileList: async (params) => {
    return await request.get({ url: `/hy/doc-file/list`, params })
  },

  // 获取最大的序号
  getMaxno: async (parentId: number) => {
    return await request.get({ url: `/hy/doc-file/getMaxno?parentId=` + parentId })
  },

}
