<template>
    <div class="common-layout">
        <el-container class="box">
            <!-- 左侧菜单栏 -->
            <el-aside class="el-aside">
                <h1 class="logoBox">资产管理系统</h1>
                <el-row class="tac" style="width: 231px;">

                    <el-col >
                        <el-menu
                                active-text-color="#ffd04b"
                                background-color="#304156"
                                class="el-menu-vertical-demo"
                                default-active="1-1"
                                text-color="#D5D5D5"
                                @open="handleOpen"
                                @close="handleClose"
                                unique-opened="true"
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
                                <el-menu-item-group  class="b1" @click="router.push('/asset-category')">
                                    <el-menu-item index="2-2">资产分类</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group  class="b1" @click="router.push('/asset-life')">
                                    <el-menu-item index="2-3">使用年限</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="3">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>附件管理</span>
                                </template>
                                <el-menu-item-group  class="b1" @click="router.push('/attachment')">
                                    <el-menu-item index="3-1">增加查询</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="4">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产管理</span>
                                </template>
                                <el-menu-item-group class="b1" @click="router.push('/asset-manage')">
                                    <el-menu-item index="4-1">资产录入</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" @click="router.push('/asset-quary')">
                                    <el-menu-item index="4-2">资产查询</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" @click="router.push('/asset-post')" >
                                    <el-menu-item index="4-3">资产上报</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" @click="router.push('/asset-report')">
                                    <el-menu-item index="4-4">资产报损</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group class="b1" @click="router.push('/asset-value-change')">
                                    <el-menu-item index="4-5">价值变更</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="5">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产年表</span>
                                </template>
                                <el-menu-item-group class="b1" @click="router.push('/annual-report')">
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
                        <el-icon :size="35" style="left: 20px"><HomeFilled /></el-icon>
                    </el-col>
                    <el-col :span="20">

                        <el-breadcrumb :separator-icon="ArrowRight" style="margin-left: 30px;">
                            <!-- 首页固定为系统首页 -->
                            <el-breadcrumb-item :to="{ path: '/index' }">系统首页</el-breadcrumb-item>

                            <!-- 遍历动态生成的面包屑项 -->
                            <el-breadcrumb-item v-for="(breadcrumb, index) in breadcrumbs" :key="index" :to="getBreadcrumbLink(breadcrumb)">
                                {{ breadcrumb }}
                            </el-breadcrumb-item>
                        </el-breadcrumb>
                    </el-col>
                    <el-col :span="2.5" >
                        <el-dropdown :hide-on-click="false" >
                         <span class="el-dropdown-link" style="padding-top: 3px;">
                            你好，资产管理员
                           <el-icon><CaretBottom /></el-icon>
                            </span>
                            <template #dro  pdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click="router.push('/user')">个人中心</el-dropdown-item>
                                    <el-dropdown-item ></el-dropdown-item>
                                    <el-dropdown-item divided>修改密码</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </el-col>
                    <el-col :span="1" >
                        <div class="hoverable-icon">
                            <el-icon size="20px" @click="logout()" ><SwitchButton /></el-icon>
                        </div>
                    </el-col>
                </el-header>
                <div class="div-tags">
<!--                    <router-link v-if="!isHomePageTagGenerated" :to="{ path: '/' }" style="text-decoration: none !important;" class="router">-->
<!--                        <el-tag class="tag1">-->
<!--                            系统首页-->
<!--                        </el-tag>-->
<!--                    </router-link>-->
<!--                    <router-link :to="{ path: '/' }" style="text-decoration: none !important;" class="router">-->
<!--                        <el-tag class="tag1">-->
<!--                            系统首页-->
<!--                        </el-tag>-->
<!--                    </router-link>-->

                    <router-link v-for="(tag, index) in generatedTags" :key="index" :to="tag.link" style="text-decoration: none !important;" class="router">
                        <el-tag v-if="!tag.isHomePage" closable @close="removeTag(tag)" class="tag1">
                            {{ tag.name }}
                        </el-tag>
                    </router-link>

                </div>
                <!-- main主体模块：标签页 + 当前路由内容 -->
                <el-main class="el-main" style="
    padding: 5px;

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
    import { useRoute } from 'vue-router';
    const breadcrumbs = ref([]);

    const isHomePageTagGenerated = ref(false);


    const getBreadcrumbLink = (breadcrumb) => {
        return getBreadcrumbLinkInternal(breadcrumb);
    };

    const getBreadcrumbLinkInternal = (breadcrumb) => {
        // 返回相应的链接
        switch (breadcrumb) {
            case '部门管理':
                return '/dept';
            case '增加查询':
                return '/attachment';
            case '资产分类':
                return '/asset-category';
            case '资产录入':
                return '/asset-manage';
            case '使用年限':
                return '/asset-life';
            case '资产查询':
                return '/asset-quary';
            case '资产上报':
                return '/asset-post';
            case '资产报损':
                return '/asset-report';
            case '价值变更':
                return '/asset-value-change';
            case '年度报表':
                return '/annual-report';
            default:
                return null;
            // 添加其他面包屑链接，如果需要的话
        }
    };

    const route = useRoute();

    watch(
        () => route.matched,
        (matched) => {
            breadcrumbs.value = matched.flatMap(route => route.meta?.breadcrumb || []);
        },
        { immediate: true }
    );




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


    //标签页相关//event.preventDefault();
    const generatedTags = ref([]);



    const removeTag = (tag) => {
        event.preventDefault();
        // 判断是否是系统首页标签
        if (tag.isHomePage) return;

        const index = generatedTags.value.indexOf(tag);
        if (index !== -1) {
            generatedTags.value.splice(index, 1);
            // 获取上一个标签的路由并跳转
            const prevTag = generatedTags.value[index - 1];
            if (prevTag) {
                router.push(prevTag.link);
            }
        }
    };




    const getTagLink = (tag) => {
        return tag.link;
    };

    const generateTagsFromBreadcrumbs = () => {
        const currentBreadcrumb = breadcrumbs.value[breadcrumbs.value.length - 1];
        // 判断是否是首页标签
        const isHomePage = currentBreadcrumb === '系统首页';

        if (isHomePage) {
            // 如果是系统首页标签，不再生成新标签
            isHomePageTagGenerated.value = true;
            return;
        }

        const currentTag = {
            name: currentBreadcrumb,
            link: getBreadcrumbLinkInternal(currentBreadcrumb),
            isHomePage: false,
        };

        // 判断是否生成重复的标签
        if (!generatedTags.value.some(tag => tag.link === currentTag.link)) {
            // 保留当前标签
            generatedTags.value.push(currentTag);
        }
    };



    onMounted(() => {
        generateTagsFromBreadcrumbs();
    });


    // 监听路由变化
    watch(() => route.fullPath, () => {
        generateTagsFromBreadcrumbs();
    });





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
        height: 60px;
        background-color: #fff;
        display: flex;
        align-items: center;
        border-top: 0.8px solid #D7D7D7;
        border-right: 0.8px solid #D7D7D7;
        border-left: 0.8px solid #D7D7D7;
    }
    .el-aside {
        overflow: hidden;
        width: 230px;
        background: #304156 ;
        padding-top: 58px;
    }
    .div-tags{
        padding: 0;
        height: 35px;
        background-color: #fff;
        display: flex;
        align-items: center;
        border: 0.8px solid #D7D7D7;
        border-radius: 1px;
        box-shadow:  0 5px 5px -5px rgba(0, 0, 0, 0.4);
    }
    .b1{
        background-color: #1F2D3D;
        --el-menu-hover-bg-color:#1a2334;

    }
    .el-menu-item is-active{
        height: 64px;
    }

    .hoverable-icon:hover {
        /* 在这里设置鼠标悬停时的样式 */
        /* 例如，改变背景色或添加阴影效果 */
        background-color: #eee;
        box-shadow: 0 0 0px rgba(0, 0, 0, 0.3);
    }
    .hoverable-icon{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 40px;
        height: 50px;
        cursor: pointer;

    }
    .tag1{
        --el-tag-bg-color:#fff;
        margin: 5px;
    }

    .tag1:hover {
        background-color: #f0f0f0; /* 悬浮时的背景颜色 */
    }
    .el-tag{
        height:26px;
        width: 95px;
        font-size: 13px;
    }



</style>