import Vue from 'vue'
import VueRouter from 'vue-router'
import storage from "@/utils/storage"
Vue.use(VueRouter)

const routes = [{
  path: '/index',
  name: 'Home',
  label: "首页",
  component: () => import('@/views/home/index')
},
{
  path: '/visitor',
  name: 'Visitor',
  label: "访客申请",
  component: () => import('@/views/visitor/index')
},

{
  path: '/appliFail',
  name: 'AppliFail',
  label: "被访人不在",
  component: () => import('@/views/appliFail/index')
},
{
  path: '/appliList',
  name: 'AppliList',
  label: "来访申请列表",
  component: () => import('@/views/appliList/index')
},
{
  path: '/appliSucc',
  name: 'AppliSucc',
  label: "申请成功",
  component: () => import('@/views/appliSucc/index')
},
{
  path: '/face',
  name: 'Face',
  label: "选择人脸采集",
  component: () => import('@/views/face/index')
},
{
  path: '/faceDet',
  name: 'FaceDet',
  label: "选择人脸识别",
  component: () => import('@/views/faceDet/index')
},
{
  path: '/history',
  name: 'History',
  label: "历史记录",
  component: () => import('@/views/history/index')
},
{
  path: '/staffinfo',
  name: 'Staffinfo',
  label: "员工身份认证",
  component: () => import('@/views/staffinfo/index')
},
{
  path: '/staindex',
  name: 'Staindex',
  label: "员工首页",
  component: () => import('@/views/staindex/index')
},
{
  path: '/visinfo',
  name: 'Visinfo',
  label: "请您完善访客信息",
  component: () => import('@/views/visinfo/index')
},
{
  path: '/visinv',
  name: 'Visinv',
  label: "访客邀约",
  component: () => import('@/views/visinv/index')
},
{
  path: '/visitorVerification',
  name: 'VisitorVerification',
  label: "访客校验",
  component: () => import('@/views/visitorVerification/index')
},
]

const router = new VueRouter({
  mode: "hash",
  routes
})


router.beforeEach((to, from, next) => {
  let staffinfo = storage.getItem('staffinfo')
  if (to.name == "Staffinfo" && staffinfo) {
    next({
      path: '/staindex'
    })
  } else if (to.name == "Staindex" && !staffinfo) {
    next({
      path: '/staffinfo'
    })
  } else {
    next()
  }
})


export default router