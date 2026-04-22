import request from '@/config/axios'



// 我的日记 API
export const SpptApi = {


  // 查询我的日记详情
  getpwd: async (pwd: String) => {
    return await request.get({ url: `/hy/sppt/get?pwd=` + pwd })
  },





}
