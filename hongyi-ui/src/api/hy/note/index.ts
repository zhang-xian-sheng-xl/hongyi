import request from '@/config/axios'
import type { Dayjs } from 'dayjs';

/** 我的日记信息 */
export interface Note {
          id: number; // 主鍵id
          name: string; // 标题
          item: string; // 系统
          content: string; // 内容
          annexUrl: string; // 附件
  }

// 我的日记 API
export const NoteApi = {
  // 查询我的日记分页
  getNotePage: async (params: any) => {
    return await request.get({ url: `/hy/note/page`, params })
  },

  // 查询我的日记详情
  getNote: async (id: number) => {
    return await request.get({ url: `/hy/note/get?id=` + id })
  },

  // 新增我的日记
  createNote: async (data: Note) => {
    return await request.post({ url: `/hy/note/create`, data })
  },

  // 修改我的日记
  updateNote: async (data: Note) => {
    return await request.put({ url: `/hy/note/update`, data })
  },

  // 删除我的日记
  deleteNote: async (id: number) => {
    return await request.delete({ url: `/hy/note/delete?id=` + id })
  },

  /** 批量删除我的日记 */
  deleteNoteList: async (ids: number[]) => {
    return await request.delete({ url: `/hy/note/delete-list?ids=${ids.join(',')}` })
  },

  // 导出我的日记 Excel
  exportNote: async (params) => {
    return await request.download({ url: `/hy/note/export-excel`, params })
  },
}