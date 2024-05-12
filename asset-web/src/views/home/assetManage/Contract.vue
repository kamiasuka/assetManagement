<template>
    <el-tabs type="border-card">
        <el-tab-pane label="租借合同">
            <el-button type="primary" size="big" @click="reflush">刷新</el-button>
            <div>
                <el-table :data="LeasetableData" style="width: 100%">
                    <el-table-column prop="contractNumber" label="合同号" width="200"/>
                    <el-table-column prop="contractType" label="合同类型" width="150"/>
                    <el-table-column prop="partyA" label="甲方" width="100"/>
                    <el-table-column prop="partyB" label="乙方" width="100"/>
                    <el-table-column prop="signingDate" label="签订日期" width="200"/>
                    <el-table-column prop="terminationDate" label="终止日期" width="200"/>
                </el-table>
            </div>
            <div style="position: fixed; bottom: 10px;">
                <el-pagination
                        background layout="prev, pager, next"
                        :total="Total"
                        :page-size="pageSize"
                        @current-change="handleCurrentChange1"
                />
            </div>
        </el-tab-pane>
        <el-tab-pane label="变卖合同">
            <el-button type="primary" size="big" @click="reflush">刷新</el-button>
            <div>
                <el-table :data="SaletableData" style="width: 100%">
                    <el-table-column prop="contractNumber" label="合同号" width="200"/>
                    <el-table-column prop="contractType" label="合同类型" width="150"/>
                    <el-table-column prop="partyA" label="甲方" width="100"/>
                    <el-table-column prop="partyB" label="乙方" width="100"/>
                    <el-table-column prop="signingDate" label="签订日期" width="200"/>
                    <!--                    <el-table-column prop="terminationDate" label="审核通过日期" width="200"/>-->
                </el-table>
            </div>
            <div style="position: fixed; bottom: 10px;">
                <el-pagination
                    background layout="prev, pager, next"
                    :total="Total"
                    :page-size="pageSize"
                    @current-change="handleCurrentChange2"
                />
            </div>
        </el-tab-pane>
        <div v-if="user.identity==='管理员'">
        <el-tab-pane label="合同添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="合同号:">
                    <el-input style="width: 300px;" v-model="AddInfo.contractNumber"></el-input>
                </el-form-item>
                <el-form-item label="合同类型:">
                    <el-input style="width: 300px;" v-model="AddInfo.contractType"></el-input>
                </el-form-item>
                <el-form-item label="甲方:">
                    <el-input style="width: 300px;" v-model="AddInfo.partyA"></el-input>
                </el-form-item>
                <el-form-item label="乙方:">
                    <el-input style="width: 300px;" v-model="AddInfo.partyB"></el-input>
                </el-form-item>
                <el-form-item label="签订日期:">
                    <el-date-picker
                        v-model="AddInfo.signingDate"
                    />
                </el-form-item>
                <el-form-item label="截止日期:">
                    <el-date-picker
                        v-model="AddInfo.terminationDate"
                    />
                </el-form-item>

                <el-form-item>
                    <el-button style="" type="primary" @click="save">提交</el-button>
                    <el-button style="" type="">返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        </div>
    </el-tabs>
</template>

<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import qs from "qs";


const reflush = () => {
    location.reload();
}
const user = ref(localStorage.user ? JSON.parse(localStorage.user) : null);

const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;
const Total = ref();
const tableData = ref([]);
const SaletableData = ref([]);
const LeasetableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange1 = (val) => {
    pageNum.value = val;
    loadContentsSale();
};
const handleCurrentChange2 = (val) => {
    pageNum.value = val;
    loadContentsSale();
};
onMounted(() => {
    loadContentsSale();
    loadContentsLease();
})

const loadContentsSale = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/' + page + '/getContractSale')
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    SaletableData.value = responseData.list;
                    Total.value = responseData.total;
                } else if (response.data.code == 1004) {
                    ElMessage.error("登录超时，请重新登录！");
                    router.push('/login')
                } else {
                    ElMessage.error(response.data.msg);
                }
            })
    } else {
        ElMessage.error("系统未登录！");
        router.push('/login')
    }
}

const loadContentsLease = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/' + page + '/getContractLease')
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    LeasetableData.value = responseData.list;
                    Total.value = responseData.total;
                } else if (response.data.code == 1004) {
                    ElMessage.error("登录超时，请重新登录！");
                    router.push('/login')
                } else {
                    ElMessage.error(response.data.msg);
                }
            })
    } else {
        ElMessage.error("系统未登录！");
        router.push('/login')
    }
}
const AddInfo = ref([{
    contractNumber: '',
    contractType: '',
    partyA: '',
    partyB: '',
    signingDate: '',
}]);
const save = () => {
    if (!AddInfo.value.contractNumber || !AddInfo.value.contractType || !AddInfo.value) {
        ElMessage.error("请填写完整信息");
        return;
    }
    let data = qs.stringify(AddInfo.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.post("http://localhost:9002/v1/module/submitContract", data)
            .then((response) => {
                if (response.data.code == 2001) {
                    ElMessage.success("申请提交成功");
                    location.reload();
                } else if (response.data.code == 1004) {
                    ElMessage.error("登录超时，请重新登录！");
                    router.push('/login')
                } else {
                    ElMessage.error(response.data.msg);
                }
            })
    } else {
        ElMessage.error("系统未登录！");
        router.push('/login')
    }


}

</script>

