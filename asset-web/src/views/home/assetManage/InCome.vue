<template>
    <div class="common-layout">
        <el-container>
            <el-header style="padding-top: 20px">
                <el-button type="primary" size="big" @click="reflush">刷新</el-button>
            </el-header>
            <el-main>
                <div>
                    <el-table :data="tableData" style="width: 100%">
                        <el-table-column prop="incomeType" label="收入类型" width="200"/>
                        <el-table-column prop="assetCode" label="资产编码" width="150"/>
                        <el-table-column prop="assetName" label="资产名称" width="100"/>
                        <el-table-column prop="incomeAmount" label="收入金额" width="100"/>
<!--                        <el-table-column prop="entryStatus" label="入账时间" width="100"/>-->
                        <el-table-column prop="entryTime" label="入账时间" width="200"/>
                    </el-table>
                </div>
                <div style="position: fixed; bottom: 10px;">
                    <el-pagination
                            background layout="prev, pager, next"
                            :total="Total"
                            :page-size="pageSize"
                            @current-change="handleCurrentChange"
                    />
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";


const reflush = () => {
    location.reload();
}
const user = ref(localStorage.user ? JSON.parse(localStorage.user) : null);
const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;
const Total = ref();
const tableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange = (val) => {
    pageNum.value = val;
    loadContents();
};
onMounted(() => {
    loadContents();
})

const loadContents = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/'+page+'/income')
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    tableData.value = responseData.list;
                    Total.value = responseData.total;
                }else if (response.data.code==1004){
                    ElMessage.error("登录超时，请重新登录！");
                    router.push('/login')
                }else {
                    ElMessage.error(response.data.msg);
                }
            })
    }else {
        ElMessage.error("系统未登录！");
        router.push('/login')
    }
}
</script>

