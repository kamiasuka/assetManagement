<template>
    <el-tabs type="border-card">
        <el-tab-pane label="个人信息">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">{{ userinfo.unit }}</el-form-item>
                <el-form-item label="所属部门:">{{ userinfo.dept }}</el-form-item>
                <el-form-item label="用户昵称:">
                    <el-input v-model="userinfo.nickname" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱:">
                    <el-input v-model="userinfo.email" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="联系电话:">
                    <el-input v-model="userinfo.tel" style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="用户角色:">{{ userinfo.identity }}</el-form-item>
                <el-form-item>
                    <el-button style="margin-left: 100px" type="primary" @click="save()">保存</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改密码">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="旧密码:"><el-input v-model="userinfo.password" style="width: 200px"></el-input></el-form-item>
                <el-form-item label="新密码:"><el-input v-model="newPwd1" style="width: 200px"></el-input></el-form-item>
                <el-form-item label="确认新密码:"><el-input v-model="newPwd2" style="width: 200px"></el-input></el-form-item>
                <el-form-item><el-button type="primary" @click="savePwd()">保存</el-button></el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import qs from "qs";
import axios from "axios";
import {ElMessage} from "element-plus";

// console.log(localStorage.user);
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

const userinfo = ref({id:"",username:"",password:"",nickname:"",identity:"",tel:"",email:"",dept:"",unit:""}); //{id:"",username:"",password:"",nickname:"",identity:"",tel:"",email:"",dept:"",unit:""}

// const token = localStorage.token; // 获取存储的Token
// if (token) {
//   axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
// }


const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;
console.log(localStorage.token);

// 获取用户信息
const getUserInfo = ()=>{
  let userId = user.value.id;
  if (token!=null) {
    axios.defaults.headers.common['Authorization'] = token;
  }
  axios.get("http://localhost:9001/v1/users/getInfoById/"+userId)
      .then((response)=>{
          if (response.data.code=2001){
              userinfo.value = response.data.data;
          }
      })
};

// 修改用户信息
const save = ()=>{
    let data = qs.stringify(userinfo.value);
    axios.post('http://localhost:9001/v1/users/'+userinfo.value.id+'/info/update',data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("修改完成!");
                //更新LocalStorage里面的用户数据
                user.value.nickname = userinfo.value.nickname;
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
            id: userinfo.value.id,
            newPassword: newPwd2.value
        };
        console.log(dataJson);
        let data = qs.stringify(dataJson);
        axios.post('http://localhost:9001/v1/users/'+dataJson.id+'/password/update',data)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("修改完成!");
                    //刷新“旧密码”框的显示
                    userinfo.value.password = newPwd2.value;
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


onMounted(() => {
    getUserInfo();
});

</script>

<style scoped>

</style>