import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children:[

        ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import( '../views/AboutView.vue')
  },
  {path:'/login',component:()=> import('../views/home/LoginView')},
  {path:'/user',component:()=> import('../views/home/UserView')},
  {path:'/department',component:()=> import('../views/home/DepartmentView')},

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
