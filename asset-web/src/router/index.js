import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:'/index',
    children:[
      {path:'/index', component: () => import( '../views/home/IndexView')},
      {path:'/dept', component: () => import( '../views/home/DeptView')},
      {path:'/user', component: () => import( '../views/home/UserView')},
      {path:'/asset-category', component: () => import( '../views/home/AssetCategoryView')},
      {path:'/attachment', component: () => import( '../views/home/AttachmentView')},
      {path:'/asset-manage', component: () => import( '../views/home/AssetManageView')},
    ]
  },
  {path:'/login', component: () => import( '../views/home/LoginView')}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
