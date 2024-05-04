<template>
    <div id="main_div">
        <el-card style="width: 500px;height: 400px;margin: 150px auto">
            <el-form label-width="100px" style="margin-top: -10px">
                <h1 style="text-align: center">企业资产管理系统</h1>
                <el-form-item label="用户名">
                    <el-input v-model="user.username" placeholder="请输入用户名"></el-input></el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="user.password" type="password" placeholder="请输入密码"></el-input></el-form-item>
                <el-form-item style="margin-bottom: -20px;margin-top: -20px">
                    <el-checkbox v-model="checked1" label="记住登录账号" size="large" />
                </el-form-item>
                <el-divider/>
                <el-form-item>
                    <el-button style="position: relative;left: 50px;top: 10px;width: 100px" type="primary" @click="login()">登录</el-button>
                    <el-button style="position: relative;left: 50px;top: 10px" type="primary">重置</el-button>
                </el-form-item>
                <p>Copynight @ 2018-2023<span style="color: blue"> xx市国有企业固定资产管理系统</span>Passed By </p>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
    import {ref} from "vue";
    const checked1 = ref(false);
    import qs from "qs";
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import router from "@/router";

    const user = ref({username:'',password:''});
    /* const  ADMIN_URL = 'http://localhost:9001';*/
    const login = ()=>{
        let data = qs.stringify(user.value);
        axios.post(ADMIN_URL+'/v1/users/login',data)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("登录成功!");
                    let user = response.data.data;
                    const token = user.jwt;
                    //获取jwt令牌
                    // 将令牌存储到本地存储，以便后续使用
                    localStorage.setItem('token',token);
                  
                    localStorage.setItem('user',JSON.stringify(user));
                    location.href='/';   //跳转到首页  整个页面重新加载
                    const keys = Object.keys(localStorage);

                    // 遍历每个键，并打印对应的值
                    keys.forEach(key => {
                        const value = localStorage.getItem(key);
                        console.log(`${key}: ${value}`);
                    });
                }else{
                    ElMessage.error(response.data.msg);
                }
            })

    }
</script>

<style scoped>
    #main_div{
        overflow: hidden;
        height: 900px;
        background-image: url("/public/imgs/10035.jpg");
        background-size: cover;/*设置封面尺寸*/
        background-position: center;/*设置背景图片居中*/
    }
</style>