<template>
    <el-tabs type="border-card" v-model="deptTab">
        <el-tab-pane label="部门列表" name="tab1">
            <div class="mt-4">
                所属单位：
                <el-input
                        style="width: 200px"
                        v-model="getByUnit"
                        placeholder="XX公司"
                        class="input-with-select"
                >
                        <template #append>
                            <el-button :icon="Search"></el-button>
                        </template>
                </el-input>
                部门名称：
                <el-input style="width: 180px" v-model="getByName"></el-input>
                <el-button @click="clearFilter" type="primary">查询</el-button>
            </div>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="name" label="部门名称" width="200"></el-table-column>
                <el-table-column prop="unit" label="所属单位" width="200"></el-table-column>
                <el-table-column prop="code" label="部门编码" width="200"></el-table-column>
                <el-table-column prop="tel" label="部门电话" width="200"></el-table-column>
                <el-table-column fixed="right" label="操作" width="200">
                    <template #default>
                        <div v-if="user.identity==='管理员'&& tableData.length > 1">
                            <el-button type="success" size="small">编辑</el-button>
                            <el-button type="danger" size="small">删除</el-button>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="部门添加" name="tab2">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="部门编码:">
                    <el-input style="width: 300px;" v-model="addCode"></el-input>
                </el-form-item>
                <el-form-item label="部门名称:" >
                    <el-input style="width: 300px;" v-model="addName"></el-input>
                </el-form-item>
                <el-form-item label="所属单位:" >
                    <el-input style="width: 300px;" v-model="addUnit"></el-input>
                </el-form-item>
                <el-form-item label="部门电话:" >
                    <el-input style="width: 300px;" v-model="addTel"></el-input>
                </el-form-item>
<!--                <el-form-item label="备注:">-->
<!--                    <el-input-->
<!--                            style="width: 300px;"-->
<!--                            v-model="textarea"-->
<!--                            :rows="2"-->
<!--                            type="textarea"-->
<!--                            placeholder=""-->
<!--                    />-->
<!--                </el-form-item>-->
                <el-form-item>
                    <el-button style="" type="primary" >保存</el-button>
                    <el-button style="" type="" @click="goToTab('tab1')">返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
    Check,
    Delete,
    Edit,
    Message,
    Search,
    Star,
} from '@element-plus/icons-vue'
import {onMounted, reactive, ref} from 'vue'
import axios from "axios";
import {ElMessage} from "element-plus";

//获取用户登录信息（session）
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

const tableData = ref([{id:"",code:"",unit:"",tel:""}]);

const getByUnit = ref("");
const getByName = ref("");
const addUnit = ref("");
const addName = ref("");
const addCode = ref("");
const addTel = ref("");

const deptTab = ref('tab1');
// const tableData = ref([{name:"cc",code:"a",unit:"ab",tel:"111"}]);

//获取列表数据
const listAllDept = ()=>{
    axios.get("http://localhost:9002/v1/dept/listAll")
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("操作成功");
                tableData.value = response.data.data;
                console.log(tableData);
                console.log(response.data.data);
            }
        })
};

const goToTab = (tabName) => {
    addCode.value = "";
    addName.value = "";
    addUnit.value = "";
    addTel.value = "";
    deptTab.value = tabName;
};

onMounted(()=>{
    listAllDept();
});

</script>

<style scoped>

</style>