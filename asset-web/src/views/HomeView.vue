<template>
    <div class="common-layout">
        <el-container class="box">
            <!-- 左侧菜单栏 -->
            <el-aside class="el-aside">
                <h1 class="logoBox">资产管理系统</h1>
                <el-row class="tac" style="width: 201px;">

                    <el-col >
                        <el-menu
                                active-text-color="#ffd04b"
                                background-color="#304156"
                                class="el-menu-vertical-demo"
                                default-active="1-1"
                                text-color="#D5D5D5"
                                @open="handleOpen"
                                @close="handleClose"
                        >
                            <el-sub-menu index="1" >
                                <template #title  >
                                    <el-icon><location /></el-icon>
                                    <span >系统首页</span>
                                </template>
                                <el-menu-item-group class="b1" @click="router.push('/index')" >
                                    <el-menu-item index="1-1">系统首页</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="2">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>基础信息</span>
                                </template>
                                <el-menu-item-group class="b1" @click="router.push('/dept')">
                                    <el-menu-item index="2-1">部门管理</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group  class="b1">
                                    <el-menu-item index="2-2">资产分类</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group  class="b1">
                                    <el-menu-item index="2-3">使用年限</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="3">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>附件管理</span>
                                </template>
                                <el-menu-item-group  class="b1" @click="router.push('/annex')">
                                    <el-menu-item index="3-1">增加查询</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="4">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产管理</span>
                                </template>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="4-1">资产录入</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="4-2">资产查询</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="4-3">资产上报</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="4-4">资产报损</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="4-5">价值变更</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="5">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产年表</span>
                                </template>
                                <el-menu-item-group class="b1" >
                                    <el-menu-item index="5-1">年度报表</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>


            <el-container>
                <!-- header头部菜单 -->
                <el-header class="header" >
                    <el-col :span="0.5">
                        <el-icon :size="25" style="left: 20px"><HomeFilled /></el-icon>
                    </el-col>
                    <el-col :span="20">
                        <el-breadcrumb :separator-icon="ArrowRight" style="margin-left: 30px;">
                            <el-breadcrumb-item v-for="(breadcrumb, index) in breadcrumbs" :key="index"  :to="getBreadcrumbLink(breadcrumb)">
                                {{ breadcrumb }}
                            </el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-col>


                    <el-col :span="1">
                        <el-popover v-if="user==null" placement="bottom" title="资产管理系统" :width="200">
                            <template #reference>
                                <el-icon style="margin-top: 12px" size="25"><User/></el-icon>
                            </template>
                            <div style="text-align: center">
                                <el-button type="info" @click="router.push('/reg')">注册</el-button>
                                <el-button type="warning" @click="router.push('/login')">登录</el-button>
                            </div>
                        </el-popover>
                        <el-popover v-else placement="bottom" title="欢迎访问烘焙坊" :width="200">
                            <template #reference>
                                <el-icon style="margin-top: 28px" size="25"><User/></el-icon>
                            </template>
                            <div style="text-align: center">
                                <!--                 设置头像展示 -->
                                <el-avatar :src="BASE_URL+user.imgUrl"></el-avatar><br>
                                <el-button type="info" size="small" @click="router.push('/personal')">个人中心</el-button>
                                <el-button type="warning" size="small" @click="logout()">退出登录</el-button><br>
                                <el-button v-if="user.isAdmin==1" type="danger" size="small"
                                           @click="router.push('/admin')">后台管理</el-button>
                            </div>
                        </el-popover>
                    </el-col>
                </el-header>
                <div  class="div-tags">
                </div>
                <!-- main主体模块：标签页 + 当前路由内容 -->
                <el-main class="el-main" style="
    padding: 3px;

"><router-view/></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
    import router from '@/router';
    import { ArrowRight, HomeFilled, User,Icon } from '@element-plus/icons-vue';
    import { onMounted, ref, watch,computed } from 'vue';
    import axios from 'axios';
    //面包屑
    const breadcrumbs = ref([]);

    watch(
        () => router.currentRoute.value.matched,
        (matched) => {
            breadcrumbs.value = matched.flatMap(route => route.meta?.breadcrumb || []);
        },
        { immediate: true }
    );

    const getBreadcrumbLink = (breadcrumb) => {
        // 根据面包屑内容返回相应的路由信息
        switch (breadcrumb) {
            case '系统首页':
                return '/index';
            case '部门管理':
                return '/dept';
            case '增加查询':
                return '/annex';
            default:
                return '/';
        }
    };

    const wd = ref('');
    const search = ()=>{
        router.push('/list?wd='+wd.value);
    }

    const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
    const logout = ()=>{
        if (confirm("您确认退出登录吗?")){
            localStorage.clear();
            user.value=null;
            router.push('/login');
        }
    }



</script>

<style scoped>

.logoBox {
    position: absolute;
    top: -3px;
    left: 25px;
    font-size: 24px;
    color: #fff;
}

    .box {
        width: 100vw;
        height: 100vh;
    }
    .header {
        padding: 0;
        height: 50px;
        background-color: #fff;
        display: flex;
        align-items: center;
        border-top: 0.8px solid #D7D7D7;
        border-right: 0.8px solid #D7D7D7;
        border-left: 0.8px solid #D7D7D7;
    }
    .el-aside {
        overflow: hidden;
        width: 200px;
        background: #304156 ;
        padding-top: 58px;
    }
    .div-tags{
        padding: 0;
        height: 28px;
        background-color: #fff;
        display: flex;
        align-items: center;
        border: 0.8px solid #D7D7D7;
        border-radius: 1px;
        box-shadow:  0 5px 5px -5px rgba(0, 0, 0, 0.4);
    }
    .b1{
        background-color: #1F2D3D ;
    }


</style>