<template>
    <el-tabs type="border-card">
        <el-tab-pane label="资产列表" >
            <div>
                <el-table :data="assetData" style="width: 100%">
                    <el-table-column prop="code" label="资产编码" width="150"/>
                    <el-table-column prop="name" label="资产名称" width="100"/>
                    <el-table-column prop="type" label="资产分类" width="130"/>
                    <el-table-column prop="dept" label="所属部门" width="100"/>
                    <el-table-column prop="unit" label="所属单位" width="100"/>
                    <el-table-column prop="life" label="使用年限" width="100"/>
                    <el-table-column prop="amount" label="资产价值" width="100"/>
                    <el-table-column prop="useStatus" label="使用状态" width="100"/>
                    <!--                        <el-table-column prop="reviewStatus" label="审核状态" width="100"/>-->
                    <el-table-column prop="note" label="备注" width="150"/>
                    <el-table-column fixed="right" label="操作" width="150">
                        <template #default="scope">
                            <div v-if="user.identity==='管理员' && assetData.length >= 1 " style="display: flex; align-items: center;">
                                <el-button type="danger" size="small" @click="handleClickOnSale(scope.row)"
                                           style="margin-right: 10px;">
                                    变卖
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <el-dialog
                    v-model="assetSaleDialogVisible"
                    title="填写资产变卖信息"
                    width="500"
            >
                <el-form label-width="100px">
                    <el-form-item label="变卖金额:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.revenueMoney"></el-input>
                    </el-form-item>
                    <el-form-item label="变卖时间:">
                        <!--                        <el-input style="width: 300px;" v-model="saleAddInfo.saleTime"></el-input>-->
                        <el-date-picker
                                v-model="saleAddInfo.saleTime"
                        />
                    </el-form-item>
                    <el-form-item label="接收方:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.receiver"></el-input>
                    </el-form-item>
                    <el-form-item label="资产编码:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.assetCode"></el-input>
                    </el-form-item>
                    <el-form-item label="资产名称:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.assetName"></el-input>
                    </el-form-item>
                    <el-form-item label="资产价值:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.assetValue"></el-input>
                    </el-form-item>
                    <el-form-item label="合同号:">
                        <el-input style="width: 300px;" v-model="saleAddInfo.contractNumber"></el-input>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <div class="dialog-footer">
                        <el-button @click="assetSaleDialogVisible = false">取消</el-button>
                        <el-button type="primary" @click="saleAsset()">确认</el-button>
                    </div>
                </template>
            </el-dialog>
            <div style="position: fixed; bottom: 10px;">
                <el-pagination
                        background layout="prev, pager, next"
                        :total="Total"
                        :page-size="pageSize"
                        @current-change="handleCurrentChange1"
                />
            </div>
        </el-tab-pane>
        <el-tab-pane label="变卖记录">
            <el-button type="primary" size="big" @click="reflush">刷新</el-button>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="revenueMoney" label="变卖金额" width="100"></el-table-column>
                <el-table-column prop="saleTime" label="变卖时间" width="200"></el-table-column>
                <el-table-column prop="receiver" label="接收方" width="100"></el-table-column>
                <el-table-column prop="assetCode" label="资产编码" width="150"></el-table-column>
                <el-table-column prop="assetName" label="资产名称" width="100"></el-table-column>
                <el-table-column prop="assetValue" label="资产价值" width="100"></el-table-column>
                <el-table-column prop="contractNumber" label="合同号" width="100"></el-table-column>
                <el-table-column prop="approvalStatus" label="审核状态" width="100"></el-table-column>
                <el-table-column prop="approvalDate" label="审核通过日期" width="200"></el-table-column>
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
        <div v-if="user.identity==='审核员'&& tableData.length >= 1">
            <el-tab-pane label="变卖审核">
                <el-button type="primary" size="big" @click="reflush">刷新</el-button>
                <el-table :data="postTableData" style="width: 100%">
                    <el-table-column prop="revenueMoney" label="变卖金额" width="100"></el-table-column>
                    <el-table-column prop="saleTime" label="变卖时间" width="200"></el-table-column>
                    <el-table-column prop="receiver" label="接收方" width="100"></el-table-column>
                    <el-table-column prop="assetCode" label="资产编码" width="150"></el-table-column>
                    <el-table-column prop="assetName" label="资产名称" width="100"></el-table-column>
                    <el-table-column prop="assetValue" label="资产价值" width="100"></el-table-column>
                    <el-table-column prop="contractNumber" label="合同号" width="100"></el-table-column>
                    <el-table-column prop="approvalStatus" label="审核状态" width="100"></el-table-column>
                    <el-table-column prop="approvalDate" label="审核通过日期" width="200"></el-table-column>
                    <el-table-column fixed="right" label="操作" width="150">
                        <template #default="scope">
                            <div v-if="user.identity==='审核员'&& postTableData.length >= 1">
                                <el-button type="success" size="small" @click="handleClickAddOn(scope.row)">批准
                                </el-button>
                                <el-button type="danger" size="small" @click="handleClickAddOff(scope.row)">驳回
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
                            @current-change="handleCurrentChange3"
                    />
                </div>
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
const assetInfo = ref(localStorage.assetInfo ? JSON.parse(localStorage.assetInfo) : null);

const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;
const Total = ref();
const tableData = ref([]);
const assetData = ref([]);
const postTableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange1 = (val) => {
    pageNum.value = val;
    loadContents1();
};
onMounted(() => {
    loadContents1();
    loadContents2();
    loadContents3();
})
const loadContents1 = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/listAllAsset/' + page)
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    //let assetInfo = response.data.data.list;
                    assetData.value = responseData.list;
                    Total.value = responseData.total;
                    //localStorage.setItem('assetInfo',JSON.stringify(assetInfo));
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

/////////////////////////////////////////
const handleCurrentChange2 = (val) => {
    pageNum.value = val;
    loadContents2();
};

const loadContents2 = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/' + page + '/getSaleAsset')
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    tableData.value = responseData.list;
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
const handleClickOnSale = (row) => {
    assetSaleDialogVisible.value = true;
    saleAddInfo.value = row;
}
const assetSaleDialogVisible = ref(false);
const saleAddInfo = ref([{
    revenueMoney: '',
    saleTime: '',
    receiver: '',
    assetCode: '',
    assetName: '',
    assetValue: '',
    contractNumber: '',
}]);
const saleAsset = () => {
    if (!saleAddInfo.value.assetName || !saleAddInfo.value.assetCode || !saleAddInfo.value) {
        ElMessage.error("请填写完整的附件信息");
        return;
    }
    assetSaleDialogVisible.value = false;
    let data = qs.stringify(saleAddInfo.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.post("http://localhost:9002/v1/module/submit", data)
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

//////////////////////////////////////
const handleCurrentChange3 = (val) => {
    pageNum.value = val;
    loadContents3();
};

const loadContents3 = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/module/' + page + '/getSalePostAsset')
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    postTableData.value = responseData.list;
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

const handleClickAddOn = (row) => {
    let code = row.assetCode;
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        if (confirm("是否批准?")) {
            axios.get('http://localhost:9002/v1/module/saleOn/' + code)
                .then((response) => {
                    if (response.data.code == 2001) {
                        ElMessage.success("操作成功！");
                        location.reload();
                    } else {
                        ElMessage.error("失败");
                    }
                })
        }
    } else {
        ElMessage.error("登录超时，请重新登录！");
        router.push('/login')
    }
}

const handleClickAddOff = (row) => {
    let code = row.assetCode;
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        if (confirm("是否驳回?")) {
            axios.get('http://localhost:9002/v1/module/saleOff/' + code)
                .then((response) => {
                    if (response.data.code == 2001) {
                        ElMessage.success("操作成功！");
                        location.reload();
                    } else {
                        ElMessage.error(response.data.msg);
                    }
                })
        }
    } else {
        ElMessage.error("登录超时，请重新登录！");
        router.push('/login')
    }
}

</script>

