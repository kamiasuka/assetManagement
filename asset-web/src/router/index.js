import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: '系统首页',
    component: HomeView,
    redirect: '/index',
    children: [
      { path: '/index', component: () => import('../views/home/IndexView'), meta: { breadcrumb: ['系统首页'] }},
      { path: '/dept', component: () => import('../views/home/DeptView'),  meta: { breadcrumb: ['基础信息', '部门管理'] }},
      { path: '/user', component: () => import('../views/home/UserView'),  meta: { breadcrumb: ['基础信息', '用户管理'] } }
    ]
  },
  { path: '/login', component: () => import('../views/home/LoginView'), meta: { breadcrumb: ['登录'] } }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
