<template>
    <el-tabs type="border-card">
        <el-tab-pane label="【资产审核】录入申请">
            <div class="mt-4">
                资产编码:
                <el-input style="width: 150px"></el-input>
                资产名称:
                <el-input style="width: 150px"></el-input>
                审核状态：
                <el-select v-model="value" class="m-2" placeholder="全部" size="large" style="width: 150px">
                    <el-option v-for="item in categoryOptions" :key="item.value" :label="item.label"
                               :value="item.value"/>
                </el-select>
                <el-button type="primary">查询</el-button>
                <el-button type="primary">刷新</el-button>
            </div>
            <el-table
                    border
                    stripe
                    :data="tableData"
                    style="width: 100%"
            >
                <el-table-column>
                    <el-table-column type="selection" width="55"/>
                    <el-table-column property="code" label="资产编码" width="150"/>
                    <el-table-column property="name" label="资产名称" width="100"/>
                    <el-table-column property="type" label="资产名称" width="100"/>
                    <el-table-column property="dept" label="资产名称" width="100"/>
                    <el-table-column property="unit" label="所属单位" width="100"/>
                    <el-table-column property="life" label="使用年限" width="90"/>
                    <el-table-column property="amount" label="资产价值(元)" width="120"/>
                    <el-table-column property="useStatus" label="使用状态" width="100"/>
                    <el-table-column property="reviewStatus" label="审核状态" width="100">
                        <template #default="scope">
                            <el-button type="warning" v-if="scope.row.status=='审核中'">审核中</el-button>
                            <el-button type="success" v-if="scope.row.status=='已通过'">已通过</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column property="submitDate" label="申请日期" width="180"/>
                    <el-table-column property="approvalDate" label="通过日期" width="180"/>
                    <!--                    <el-table-column property="note" label="备注" width="120"/>-->
                    <el-table-column fixed="right" label="操作" width="200">
                        <template #default="scope">
                            <div v-if="user.identity==='审核员'&& tableData.length > 1">
                                <el-button type="success" size="small" @click="handleClickAddOn(scope.row)">批准
                                </el-button>
                                <el-button type="danger" size="small" @click="handleClickAddOff(scope.row)">驳回
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table-column>

            </el-table>

            <div style="position: fixed; bottom: 10px;">
                <el-pagination
                        background layout="prev, pager, next"
                        :total="Total"
                        :page-size="pageSize"
                        @current-change="handleCurrentChange1"
                />
            </div>
        </el-tab-pane>
        <el-tab-pane label="【资产审核】变更申请">
            <div class="common-layout">
                <div class="mt-4">
                    资产编码:
                    <el-input style="width: 150px"></el-input>
                    资产名称:
                    <el-input style="width: 150px"></el-input>
                    <el-select v-model="value" class="m-2" placeholder="全部" size="large" style="width: 150px">
                        <el-option v-for="item in categoryOptions" :key="item.value" :label="item.label" :value="item.value"/>
                    </el-select>
                    <el-button type="primary">查询</el-button>
                    <el-button type="primary">刷新</el-button>
                </div>
                <el-table :data="applytableData">
                    <el-table-column type="selection" width="55"/>
                    <el-table-column property="code" label="资产编码" width="150"/>
                    <el-table-column property="name" label="资产名称" width="100"/>
                    <el-table-column property="type" label="资产名称" width="100"/>
                    <el-table-column property="dept" label="资产名称" width="100"/>
                    <el-table-column property="unit" label="所属单位" width="100"/>
                    <el-table-column property="life" label="使用年限" width="90"/>
                    <el-table-column property="amount" label="资产价值(元)" width="120"/>
                    <el-table-column property="useStatus" label="使用状态" width="100"/>
                    <el-table-column property="reviewStatus" label="审核状态" width="100">
                        <template #default="scope">
                            <el-button type="warning" v-if="scope.row.status=='审核中'">审核中</el-button>
                            <el-button type="success" v-if="scope.row.status=='已通过'">已通过</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column property="submitDate" label="申请日期" width="180"/>
                    <el-table-column property="approvalDate" label="通过日期" width="180"/>
                    <!--                        <el-table-column property="note" label="备注" width="120"/>-->
                    <el-table-column fixed="right" label="操作" width="200">
                        <template #default="scope">
                            <div v-if="user.identity==='审核员'&& tableData.length > 1">
                                <el-button type="success" size="small" @click="handleClickChangeOn(scope.row)">批准
                                </el-button>
                                <el-button type="danger" size="small" @click="handleClickChangeOff(scope.row)">驳回
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="position: fixed; bottom: 10px;">
                    <el-pagination
                            background layout="prev, pager, next"
                            :total="Total"
                            :page-size="pageSize"
                            @current-change="handleCurrentChange2"
                    />
                </div>

            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue';
import {ElMessage, ElTable} from 'element-plus';
import axios from "axios";

let dialogFormVisible = ref(false);
const value = ref('');
const categoryOptions = [

    {
        value: 'option1',
        label: '审核中',
    },
    {
        value: 'option2',
        label: '未通过',
    },
    {
        value: 'option3',
        label: '已通过',
    }

]
const options = []
const user = ref(localStorage.user ? JSON.parse(localStorage.user) : null);


const loadContents1 = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    axios.get('http://localhost:9002/v1/assetReview/listAllAdd/' + page)
        .then((response) => {
            if (response.data.code == 2001) {
                const responseData = response.data.data;
                tableData.value = responseData.list;
                Total.value = responseData.total;
            }
        })
}
const Total = ref();
const tableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange1 = (val) => {
    pageNum.value = val;
    console.log(val);
    loadContents1();
};
onMounted(() => {
    loadContents1();
})

const handleClickAddOn = (row) => {
    let code = row.code;
    if (confirm("是否批准?")) {
        axios.get('http://localhost:9002/v1/assetReview/addNewOn/' + code)
            .then((response) => {
                if (response.data.code == 2001) {
                    ElMessage.success("操作成功！");
                    location.reload();
                } else {
                    ElMessage.error("失败");
                }
            })
    }
}

const handleClickAddOff = (row) => {
    let code = row.code;
    if (confirm("是否驳回?")) {
        axios.get('http://localhost:9002/v1/assetReview/addNewOff/' + code)
            .then((response) => {
                if (response.data.code == 2001) {
                    ElMessage.success("操作成功！");
                    location.reload();
                } else {
                    ElMessage.error(response.data.msg);
                }
            })
    }
}


const loadContents2 = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    axios.get('http://localhost:9002/v1/assetReview/listAllChange/' + page)
        .then((response) => {
            if (response.data.code == 2001) {
                const responseData = response.data.data;
                applytableData.value = responseData.list;
                Total.value = responseData.total;
            }
        })
}
const applytableData = ref([]);
const handleCurrentChange2 = (val) => {
    pageNum.value = val;
    console.log(val);
    loadContents2();
};
onMounted(() => {
    loadContents2();
})
const handleClickChangeOn = (row) => {
    getDeptByCode(row);
    // console.log("获取row:"+row);
}


</script>

<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>
