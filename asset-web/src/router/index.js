import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:'/index',
    children:[
      {path:'/index', component: () => import( '../views/home/IndexView'), meta: { breadcrumb: ['系统首页'] }},
      {path:'/dept', component: () => import( '../views/home/base/DeptView'), meta: { breadcrumb: [ '部门管理'], }},
      {path:'/user', component: () => import( '../views/home/UserView'),  meta: { breadcrumb: ['用户管理'] } },
      {path:'/attachment', component: () => import( '../views/home/AttachmentView'),  meta: { breadcrumb: [ '增加查询'] } },
      {path:'/asset-category', component: () => import( '../views/home/base/AssetCategoryView'),meta: { breadcrumb: [ '资产分类'] }},
      {path:'/asset-manage', component: () => import( '../views/home/assetManage/AssetManageView'),meta: { breadcrumb: [ '资产管理'] }},
      {path:'/asset-life', component: () => import( '../views/home/base/AssetLifeView'), meta: { breadcrumb: ['使用年限'] }},
      {path:'/asset-query', component: () => import( '../views/home/assetManage/AssetQueryView'), meta: { breadcrumb: ['资产查询'] }},
      {path:'/asset-post', component: () => import( '../views/home/assetManage/AssetPostView'), meta: { breadcrumb: ['资产上报'] }},
      {path:'/asset-report', component: () => import( '../views/home/assetManage/AssetReportView'), meta: { breadcrumb: ['资产报损'] }},
      {path:'/asset-value-change', component: () => import( '../views/home/assetManage/AssetValueChangeView'), meta: { breadcrumb: ['价值变更'] }},
      {path:'/annual-report', component: () => import( '../views/home/AnnualReportView'),  meta: { breadcrumb: [ '年度报表'] } },





    ]
  },
  {path:'/login', component: () => import( '../views/home/LoginView')}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
