<template>
    <el-tabs type="border-card">
        <el-tab-pane label="个人信息">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">{{ user.unit }}</el-form-item>
                <el-form-item label="所属部门:">{{ user.dept }}</el-form-item>
                <el-form-item label="用户姓名:">
                    <el-input v-model="user.nickname" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱:">
                    <el-input v-model="user.email" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="联系电话:">
                    <el-input v-model="user.tel" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="用户角色:">{{ user.identity }}</el-form-item>
                <el-form-item>
                    <el-button style="margin-left: 100px" type="primary" @click="save()">保存</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改密码">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="旧密码:"><el-input v-model="user.password" style="width: 200px"></el-input></el-form-item>
                <el-form-item label="新密码:"><el-input v-model="newPwd1" style="width: 200px"></el-input></el-form-item>
                <el-form-item label="确认新密码:"><el-input v-model="newPwd2" style="width: 200px"></el-input></el-form-item>
                <el-form-item><el-button type="primary" @click="savePwd()">保存</el-button></el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import { ref } from 'vue'
import qs from "qs";
import axios from "axios";
import {ElMessage} from "element-plus";

console.log(localStorage.user);
// 修改用户信息
const save = ()=>{
    let data = qs.stringify(user.value);
    axios.post('http://localhost:9001/v1/users/'+user.value.id+'/info/update',data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("修改完成!");
                //更新LocalStorage里面的用户数据
                localStorage.user = JSON.stringify(user.value);
            }
        })
}

// 修改密码
const newPwd1 = ref("");//  新密码
const newPwd2 = ref("");//确认新密码
// todo 修改密码业务
const savePwd= ()=>{
    if (newPwd1.value === newPwd2.value){
        let dataJson = {
            id: user.value.id,
            newPassword: newPwd2.value
        };
        console.log(dataJson);
        let data = qs.stringify(dataJson);
        console.log(dataJson.id);
        axios.post('http://localhost:9001/v1/users/'+dataJson.id+'/password/update',data)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("修改完成!");
                    //刷新“旧密码”框的显示
                    user.value.password = newPwd2;
                    //刷新输入框
                    newPwd1.value="";
                    newPwd2.value="";
                }else{
                    console.log(localStorage.user);
                    ElMessage.error(response.data.msg);
                }
            })
    }else {
        newPwd1.value="";
        newPwd2.value="";
        ElMessage.error("新密码不一致，请重新输入");
    }
//更新LocalStorage里面的用户数据
    localStorage.user = JSON.stringify(user.value);

}
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

</script>

<style scoped>

</style>