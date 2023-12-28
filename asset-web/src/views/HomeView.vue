<template>
    <div class="common-layout">
        <el-container class="box">
            <!-- 左侧菜单栏 -->
            <el-aside class="el-aside">
                <h1 class="logoBox">资产管理系统</h1>
                <el-row class="tac" style="width: 201px;">

                    <el-col>
                        <el-menu
                                active-text-color="#ffd04b"
                                background-color="#333"
                                class="el-menu-vertical-demo"
                                default-active="1-1"
                                text-color="#fff"
                                @open="handleOpen"
                                @close="handleClose"
                        >
                            <el-sub-menu index="1">
                                <template #title >
                                    <el-icon><location /></el-icon>
                                    <span>系统首页</span>
                                </template>
                                <el-menu-item-group >
                                    <el-menu-item index="1-1">系统首页</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="2">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>基础信息</span>
                                </template>
                                <el-menu-item-group >
                                    <el-menu-item index="2-1">部门管理</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="2-2">资产分类</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="2-3">使用年限</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="3">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>附件管理</span>
                                </template>
                                <el-menu-item-group >
                                    <el-menu-item index="3-1">增加查询</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="4">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产管理</span>
                                </template>
                                <el-menu-item-group >
                                    <el-menu-item index="4-1">资产录入</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="4-2">资产查询</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="4-3">资产上报</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="4-4">资产报损</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group >
                                    <el-menu-item index="4-5">价值变更</el-menu-item>
                                </el-menu-item-group>
                            </el-sub-menu>
                            <el-sub-menu index="5">
                                <template #title>
                                    <el-icon><location /></el-icon>
                                    <span>资产年表</span>
                                </template>
                                <el-menu-item-group >
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
                    <el-icon :size="25" style="left: 20px" ><HomeFilled /></el-icon>
                    <el-breadcrumb :separator-icon="ArrowRight" style="margin-left: 30px;">
                        <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
                        <el-breadcrumb-item></el-breadcrumb-item>

                    </el-breadcrumb>
                </el-header>
                <div class="div-tags">
                    <ul class="ul-c">
                        <li v-for="(item, index) in tagsArry" :key="index" :class="{active: isActive(item.name)}" @click="activeTag(index)">
                            <a class="li-a">
                                {{item.text}}
                            </a>
                            <Icon size="16" @click="closeTag(index)" type="md-close" />
                        </li>
                    </ul>
                </div>
                <!-- main主体模块：标签页 + 当前路由内容 -->
                <el-main class="el-main"><router-view/></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
    import { ArrowRight } from '@element-plus/icons-vue'
    import {onMounted, ref} from "vue";
    import axios from "axios";
    const asideMenu = ref ( [
        {title: "系统主页", index: "user",},
        {title: "文章", subs: [{title: "文章列表", index: "articleList",},],},
        {title: "测试", index: "test",},])
    onMounted(() => {

    })


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
        width: 200px;
        background: #333 ;
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


</style>