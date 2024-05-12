import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import {ElMessage} from 'element-plus';

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        redirect: '/index',
        children: [
            {path: '/index', component: () => import( '../views/home/IndexView'), meta: {breadcrumb: ['系统首页'],}},
            {
                path: '/dept',
                component: () => import( '../views/home/base/DeptView'),
                meta: {breadcrumb: ['基础信息', '部门管理'],}
            },
            {
                path: '/attachment',
                component: () => import( '../views/home/AttachmentView.vue'),
                meta: {breadcrumb: ['附件管理', '附件查询'],}
            },
            // {path:'/asset-category', component: () => import( '../views/home/base/AssetCategoryView'),meta: { breadcrumb: [ '基础信息','资产分类'] }},
            {
                path: '/asset-manage',
                component: () => import( '../views/home/assetManage/AssetManageView'),
                meta: {breadcrumb: ['资产管理', '资产录入'],}
            },
            // {path:'/asset-life', component: () => import( '../views/home/base/AssetLifeView'), meta: { breadcrumb: ['基础信息','使用年限'] }},
            {
                path: '/asset-query',
                component: () => import( '../views/home/assetManage/AssetQueryView'),
                meta: {breadcrumb: ['资产管理', '资产查询'],}
            },
            {
                path: '/asset-post',
                component: () => import( '../views/home/assetManage/AssetPostView'),
                meta: {breadcrumb: ['资产管理', '资产审核'],}
            },
            {
                path: '/asset-report',
                component: () => import( '../views/home/assetManage/AssetReportView'),
                meta: {breadcrumb: ['资产管理', '资产报损'],}
            },
            {
                path: '/asset-value-change',
                component: () => import( '../views/home/assetManage/AssetValueChangeView'),
                meta: {breadcrumb: ['资产管理', '资产变更'],}
            },
            {path: '/annual-report', component: () => import( '../views/home/AnnualReportView'), meta: {breadcrumb: ['资产年表', '年度报表'],}},
            {path: '/user', component: () => import( '../views/home/UserView'), meta: {breadcrumb: ['个人中心'],}},
            {path: '/asset-lease', component: () => import( '../views/home/assetManage/AssetLease'), meta: {breadcrumb: ['资产使用','资产出借'],}},
            {
                path: '/asset-sale',
                component: () => import( '../views/home/assetManage/AssetSale'),
                meta: {breadcrumb: ['资产管理','资产变卖'],}
            },
            {
                path: '/contract',
                component: () => import( '../views/home/assetManage/Contract'),
                meta: {breadcrumb: ['收入管理','合同管理'],}
            },
            {
                path: '/income',
                component: () => import( '../views/home/assetManage/InCome'),
                meta: {breadcrumb: ['收入管理','收益管理'],}
            }
        ]
    },
    {path: '/login', component: () => import( '../views/home/LoginView'),}
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const user = localStorage.user ? JSON.parse(localStorage.user) : null;

    // 如果用户已登录，则允许导航到目标页面
    if (to.path === '/login') {
        next(); // 直接允许导航到登录页面
    } else {
        // 检查用户登录状态
        if (user) {
            next(); // 允许导航到目标页面
        } else {
            alert("请先登录!")
            // 未登录时跳转到登录页面
            next('/login');
        }
    }
});

export default router
