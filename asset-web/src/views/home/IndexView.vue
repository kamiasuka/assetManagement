<template>
    <el-row :gutter="8">
        <el-col :span="4" v-for="(item, index) in recipeArr" :key="index">
            <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                <router-link to="#" style="color: #333;text-decoration: none">
                    <p class="card-title">9999999</p>
                </router-link>
                <el-row :gutter="10">
                    <el-col :span="24" class="card-text">土地</el-col>
                    <el-col :span="24" class="card-text">666</el-col>
                </el-row>
            </el-card>
        </el-col>
    </el-row>
    <el-row :gutter="8" style="margin-top: 8px;" >
        <el-col :span="4" v-for="(item, index) in recipeArr" :key="index">
            <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                <router-link to="#" style="color: #333;text-decoration: none">
                    <p class="card-title">9999999</p>
                </router-link>
                <el-row :gutter="10">
                    <el-col :span="24" class="card-text">土地</el-col>
                    <el-col :span="24" class="card-text">666</el-col>
                </el-row>
            </el-card>
        </el-col>
    </el-row>

</template>

<script setup>
    import {ref} from "vue";
    import axios from 'axios';
    import qs from 'qs';
    import {ElMessage} from 'element-plus'
    const user = ref({username:"",password:"",nickname:""});
        const recipeArr = ref(   [
        { title: 'Recipe 1', description: 'Description 1', color: '#588EBD' },
        { title: 'Recipe 2', description: 'Description 2', color: '#E25A5A' },
        { title: 'Recipe 3', description: 'Description 3', color: '#45B6B0' },
        { title: 'Recipe 4', description: 'Description 4', color: '#8775A9' },
        { title: 'Recipe 5', description: 'Description 5', color: '#4F5C65' },
        { title: 'Recipe 1', description: 'Description 1', color: '#13AAE3' },
    ])


</script>

<style scoped>
    .custom-card {
        height: 120px; /* 调整为您希望的高度 */
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .card-title {
        height: 40px; /* 调整为您希望的高度 */
        margin: 0;
        font-size: 16px; /* 调整为您希望的字体大小 */
        line-height: 20px; /* 调整为您希望的行高 */
    }

    .card-text {
        line-height: 20px; /* 调整为您希望的行高 */
    }
</style>