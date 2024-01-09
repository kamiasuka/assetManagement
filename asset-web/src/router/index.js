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
      {path:'/dept', component: () => import( '../views/home/DeptView'), meta: { breadcrumb: ['基础信息', '部门管理'] }},
      {path:'/user', component: () => import( '../views/home/UserView'),  meta: { breadcrumb: ['基础信息', '用户管理'] } },
      {path:'/attachment', component: () => import( '../views/home/AnnexView'),  meta: { breadcrumb: ['附件管理', '增加查询'] } },
      {path:'/asset-category', component: () => import( '../views/home/AssetCategoryView'),meta: { breadcrumb: ['基础信息', '资产分类'] }},
      {path:'/asset-manage', component: () => import( '../views/home/AssetManageView'),meta: { breadcrumb: ['资产管理', '资产管理'] }},
    ]
  },
  {path:'/login', component: () => import( '../views/home/LoginView')}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
