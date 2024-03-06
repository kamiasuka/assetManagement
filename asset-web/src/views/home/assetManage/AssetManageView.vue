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
                        <el-button :icon="Search" />
                    </template>
                </el-input>
                资产名称：
                <el-input style="width: 180px" ></el-input>
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
                    <span>浏览</span>
                    <span>添加照片</span>
                    <span>修改</span>
                    <span>删除</span>
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
        </el-tab-pane>

        <el-tab-pane label="资产添加">
            <el-form label-width="100px" style="margin: 50px 150px">

                <el-form-item label="资产名称:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="资产分类:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="所属最大分类:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="所属部门:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="所属单位:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="使用年限:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="价值:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="在用状态:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button style="" type="primary">保存</el-button>
                    <el-button style="" type="" >返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
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

    const tableData = ref([{code:'',name:'',type:'',maxType:'',dept:'',unit:'',life:'',amount:'',useStatus:'',reviewStatus:'',approvalDate:''}]);


    const getAllAssets = ()=>{
        axios.get("http://localhost:9002/v1/assetReview/listAllAdd")
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("成功获取所有录入资产!");
                    tableData.value = response.data.data;
                    console.log("tableData:"+tableData.value);
                }else {
                    ElMessage.error(response.data.msg);
                }
            })
    }

onMounted(()=>{
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