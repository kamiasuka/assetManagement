<template>
    <el-tabs type="border-card">
        <el-tab-pane label="资产列表">
            <div class="mt-4">
                资产编码：
                <el-input
                        style="width: 200px"
                        v-model="input"
                        placeholder=""
                        class="input-with-select"
                >
                    <template #append>
                        <el-button :icon="Search"/>
                    </template>
                </el-input>
                资产名称：
                <el-input style="width: 180px"></el-input>
                <el-button type="primary">查询</el-button>
                <el-button type="primary">刷新</el-button>

            </div>
            <el-table :data="tableData" stripe border style="width: 100%">
                <el-table-column prop="code" label="资产编号" width="150"></el-table-column>
                <el-table-column prop="name" label="资产名称" width="150"></el-table-column>
                <el-table-column prop="type" label="资产分类" width="150"></el-table-column>
                <el-table-column prop="maxType" label="所属最大分类" width="120"></el-table-column>
                <el-table-column prop="dept" label="所属部门" width="100"></el-table-column>
                <el-table-column prop="unit" label="所属单位" width="100"></el-table-column>
                <el-table-column prop="life" label="使用年限" width="100"></el-table-column>
                <el-table-column prop="amount" label="价值(元)" width="100"></el-table-column>
                <el-table-column prop="useStatus" label="使用状况" width="100"></el-table-column>
                <el-table-column prop="reviewStatus" label="审核状态" width="100"></el-table-column>
                <el-table-column prop="approvalDate" label="审核通过日期" width="150"></el-table-column>

                <el-table-column prop="" label="操作" width="200">
                    <template #default="scope">
                        <!--在外层使用两个不同的 div 元素来包裹两组按钮，并分别使用 flex 布局控制它们的排列方式-->
                        <div style="display: flex; align-items: center;">
                            <el-button type="info" size="small" style="margin-right: 10px;">浏览</el-button>
                            <div v-if="user.identity==='管理员'&& tableData.length > 1" style="display: flex; align-items: center;">
                                <el-button type="warning" size="small" style="margin-right: 10px;">添加附件</el-button>
                            </div>
                        </div>
                        <div v-if="user.identity==='管理员'&& tableData.length > 1 && scope.row.reviewStatus==='未通过'" style="display: flex; align-items: center;">
                            <el-button type="success" size="small" @click="handleClickOnEdit(scope.row)" style="margin-right: 10px;">修改</el-button>
                            <el-button type="danger" size="small" @click="deleteByCode(scope.row)">删除</el-button>
                        </div>
                    </template>
                </el-table-column>
                <!-- 表头样式 -->
                <!--                <template slot="header">-->
                <!--                    <thead style="height: 60px;">-->
                <!--                    <tr>-->
                <!--                        <th>资产编号</th>-->
                <!--                        <th>资产名称</th>-->
                <!--                        &lt;!&ndash; 其他表头内容 &ndash;&gt;-->
                <!--                    </tr>-->
                <!--                    </thead>-->
                <!--                </template>-->
            </el-table>
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
            <el-tab-pane label="资产添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="资产名称:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.name"></el-input>
                </el-form-item>
                <el-form-item label="资产分类:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.type"></el-input>
                </el-form-item>
                <el-form-item label="所属最大分类:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.maxType"></el-input>
                </el-form-item>
                <el-form-item label="所属部门:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.dept"></el-input>
                </el-form-item>
                <el-form-item label="所属单位:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.unit"></el-input>
                </el-form-item>
                <el-form-item label="使用年限:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.life"></el-input>
                </el-form-item>
                <el-form-item label="价值:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.amount"></el-input>
                </el-form-item>
                <el-form-item label="在用状态:">
                    <el-input style="width: 300px;" v-model="assetAddInfo.useStatus"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button style="" type="primary" @click="saveAsset">提交</el-button>
                    <el-button style="" type="">返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane></div>

        <!--    资产录入修改按钮的弹窗-->
        <el-dialog
            v-model="assetUpdateDialogVisible"
            title="部门修改"
            width="500"
        >
            <!-- :before-close="handleClose"-->

            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="资产名称:">
                    <el-input style="width: 300px;" v-model="assetUpdateInfo.name"></el-input>
                </el-form-item>
                <el-form-item label="资产分类:">
                    <el-input style="width: 300px;" v-model="assetUpdateInfo.type"></el-input>
                </el-form-item>
                <el-form-item label="所属最大分类:">
                    <el-input style="width: 300px;" v-model="assetUpdateInfo.maxType"></el-input>
                </el-form-item>
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
                    <el-button type="primary" @click="updateAsset()">
                        确认
                    </el-button>
                </div>
            </template>
        </el-dialog>

    </el-tabs>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue'
import axios from "axios";
import qs from "qs";
import {ElMessage, ElMessageBox} from "element-plus";
import {Search} from '@element-plus/icons-vue';
// let dialogTableVisible = ref(false);
// let dialogFormVisible = ref(false);
// const form = reactive({
//     name: '',
//     region: '',
//     date1: '',
//     date2: '',
//     delivery: false,
//     type: [],
//     resource: '',
//     desc: '',
// })
//获取用户登录信息（session）
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

//资产录入列表的数组
const tableData = ref([{
    code: '',
    name: '',
    type: '',
    maxType: '',
    dept: '',
    unit: '',
    life: '',
    amount: '',
    useStatus: '',
    reviewStatus: '',
    approvalDate: ''
}]);

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

const Total = ref();
// const tableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码

const assetUpdateDialogVisible = ref(false);

//修改按钮的点击事件
const handleClickOnEdit = (row) => {
    assetUpdateDialogVisible.value = true;
    assetUpdateInfo.value = row;
}



//发出更新数据的请求
const updateAsset = ()=>{
    assetUpdateDialogVisible.value = false;
    let data = qs.stringify(assetUpdateInfo.value);
    // let data = (assetUpdateInfo.value);
    console.log(data);
    axios.post("http://localhost:9002/v1/asset/add-update/",data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("更新成功");
                location.reload();

            }
        })
}

const getAllAssets = () => {
    const page = parseInt(pageNum.value)
    axios.get("http://localhost:9002/v1/assetReview/listAllAdd/" + page)
        .then((response) => {
            if (response.data.code == 2001) {
                ElMessage.success("成功获取所有录入资产!");
                console.log(response.data.data);
                const responseData = response.data.data;
                tableData.value = responseData.list;
                Total.value = responseData.total;
            } else {
                ElMessage.error(response.data.msg);
            }
        })
}

const saveAsset = ()=>{
    let data = qs.stringify(assetAddInfo.value)
    // console.log(data);
    axios.post("http://localhost:9002/v1/asset/add-new/",data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("提交成功");
                location.reload();
            }
        })
}

const deleteByCode = (row)=>{
    if (confirm("是否要删除该数据？")){
        // console.log(row);
        let code = row.code;
        // console.log(code);
        axios.get("http://localhost:9002/v1/asset/add-delete/"+code)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("删除成功");
                    location.reload();
                }else {
                    ElMessage.error(response.data.msg);
                }
            })
    }
}

onMounted(() => {
    getAllAssets();
});


</script>

<style scoped>
.el-button--text {
    margin-right: 15px;
}

.el-select {
    width: 300px;
}

.el-input {
    width: 300px;
}

.dialog-footer button:first-child {
    margin-right: 10px;
}

.demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
}

.demo-date-picker .block {
    padding: 30px 0;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    flex: 1;
}

.demo-date-picker .block:last-child {
    border-right: none;
}

.demo-date-picker .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    margin-bottom: 20px;
}
</style>