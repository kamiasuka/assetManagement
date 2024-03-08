<template>
    <el-tabs type="border-card">
        <el-tab-pane label="资产变更列表">
            <el-container>
                <el-main>
                    <div>
                        <el-table :data="tableData" style="width: 100%">
                            <el-table-column prop="code" label="资产编码" width="150"/>
                            <el-table-column prop="name" label="资产名称" width="100"/>
                            <el-table-column prop="type" label="资产分类" width="130"/>
                            <el-table-column prop="dept" label="所属部门" width="100"/>
                            <el-table-column prop="unit" label="所属单位" width="100"/>
                            <el-table-column prop="life" label="使用年限" width="100"/>
                            <el-table-column prop="amount" label="资产价值" width="100"/>
                            <el-table-column prop="useStatus" label="使用状态" width="100"/>
                            <el-table-column prop="reviewStatus" label="审核状态" width="100"/>
                            <el-table-column prop="approvalDate" label="审核通过日期" width="250"/>
                            <el-table-column prop="note" label="备注" width="200"/>
                            <el-table-column fixed="right" label="操作" width="140">
                                <template #default>
                                    <div v-if="user.identity==='管理员'">
                                        <el-button type="success" size="small">编辑</el-button>
                                        <el-button type="danger" size="small">删除</el-button>
                                    </div>
                                </template>
                            </el-table-column>
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

        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import axios from "axios";

const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

const loadContents = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    axios.get('http://localhost:9002/v1/excel/listAll/' + page)
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
const pageSize = 12; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange = (val) => {
    pageNum.value = val;
    console.log(val);
    loadContents();
};
onMounted(() => {
    loadContents();
})



const form = reactive({
    name: '',
    region: '',
    date1: '',
    date2: '',
    delivery: false,
    type: [],
    resource: '',
    desc: '',
})


</script>

<style scoped>

</style>