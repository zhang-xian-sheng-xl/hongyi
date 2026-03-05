class EhsAppRouteRecordRaw {
}

const ehsRouter: EhsAppRouteRecordRaw[] = [
  {
    path: '/sppt/operate', // 添加参数占位符
    component: () => import('@/views//sppt/operate/index.vue'),
    name: 'Sppt',
    meta: {
      noCache: true,
      hidden: true,
      canTo: true,
      title: '操作中心',
      activeMenu: 'sppt/operate/index'
    }
  }
]
export default ehsRouter
