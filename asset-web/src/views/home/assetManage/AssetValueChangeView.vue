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
<!--                            <el-table-column prop="approvalDate" label="审核通过日期" width="200"/>-->
                            <el-table-column prop="note" label="备注" width="150"/>
                            <el-table-column fixed="right" label="操作" width="150">
                                <template #default="scope">
                                    <div v-if="user.identity==='管理员'&& tableData.length >= 1" style="display: flex; align-items: center;">
                                        <el-button type="success" size="small" @click="handleClickOnEdit(scope.row)" style="margin-right: 10px;">修改</el-button>
                                        <el-button type="danger" size="small" @click="deleteByCode(scope.row)">删除</el-button>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>

                    <el-dialog
                        v-model="assetUpdateDialogVisible"
                        title="资产修改"
                        width="500"
                    >
                        <!-- :before-close="handleClose"-->

                        <el-form label-width="100px" style="margin: 0px 0px">
                            <el-form-item label="资产名称:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.name"></el-input>
                            </el-form-item>
                            <el-form-item label="资产分类:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.type"></el-input>
                            </el-form-item>
<!--                            <el-form-item label="所属最大分类:">-->
<!--                                <el-input style="width: 300px;" v-model="assetUpdateInfo.maxType"></el-input>-->
<!--                            </el-form-item>-->
                            <el-form-item label="所属部门:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.dept"></el-input>
                            </el-form-item>
                            <el-form-item label="所属单位:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.unit"></el-input>
                            </el-form-item>
                            <el-form-item label="使用年限:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.life"></el-input>
                            </el-form-item>
                            <el-form-item label="价值:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.amount"></el-input>
                            </el-form-item>
                            <el-form-item label="在用状态:">
                                <el-input style="width: 300px;" v-model="assetUpdateInfo.useStatus"></el-input>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <div class="dialog-footer">
                                <el-button @click="assetUpdateDialogVisible = false">取消</el-button>
                                <el-button type="primary" @click="updateAsset()">确认</el-button>
                            </div>
                        </template>
                    </el-dialog>

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
import qs from "qs";
import {ElMessage} from "element-plus";

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
const pageSize = 16; // 每页显示的条目数
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


const assetUpdateDialogVisible = ref(false);

const handleClickOnEdit = (row) => {
    assetUpdateDialogVisible.value = true;
    assetUpdateInfo.value = row;
}
const updateAsset = ()=>{
    assetUpdateDialogVisible.value = false;
    let data = qs.stringify(assetUpdateInfo.value);
    console.log(data);
    axios.post("http://localhost:9002/v1/asset/update/",data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("申请提交成功");
                location.reload();
            }
            else {
                ElMessage.error(response.data.msg);
            }
        })
}

const deleteByCode = (row) => {
    let code = row.code;
    axios.get("http://localhost:9002/v1/asset/delete/"+code)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("申请提交成功");
                location.reload();
            }
            else {
                ElMessage.error(response.data.msg);
            }
        })
}





//资产修改列表的数组
const assetAddInfo = ref([{
    name: '',
    type: '',
    maxType: '',
    dept: '',
    unit: '',
    life: '',
    amount: '',
    useStatus: '',
}]);

const assetUpdateInfo = ref([{
    name: '',
    type: '',
    maxType: '',
    dept: '',
    unit: '',
    life: '',
    amount: '',
    useStatus: '',
}]);


</script>

<style scoped>

</style>