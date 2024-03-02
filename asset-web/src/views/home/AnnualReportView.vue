<template>
    <div class="common-layout">
        <el-container>
            <el-header style="padding-top: 20px">

                <el-button type="success" size="big" @click="exportExcel">全部导出</el-button>

                <el-button type="primary" @click="dialogFormVisible = true">分类导出</el-button>
                <el-dialog v-model="dialogFormVisible" title="按资产分类导出" style="width: 700px">
                    <el-form :model="form">
                        <el-form-item label="分类级别" :label-width="formLabelWidth">
                            <el-select :data="typeList" placeholder="请选择分类">
                                <el-option label="土地" value="land"/>
                                <el-option label="通用设备" value="device"/>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <template #footer>
                            <span class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取消</el-button>
                                <el-button type="primary" @click="exportExcelByType">导出</el-button>
                            </span>
                    </template>
                </el-dialog>

            </el-header>
            <el-main>
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column prop="index" label="index" width="100"/>
                    <el-table-column prop="code" label="资产编码" width="100"/>
                    <el-table-column prop="name" label="资产名称" width="100"/>
                    <el-table-column prop="type" label="资产分类" width="100"/>
                    <el-table-column prop="dept" label="所属部门" width="100"/>
                    <el-table-column prop="unit" label="所属单位" width="100"/>
                    <el-table-column prop="life" label="使用年限" width="100"/>
                    <el-table-column prop="amount" label="资产价值" width="100"/>
                    <el-table-column prop="useStatus" label="使用状态" width="100"/>
                    <el-table-column prop="reviewStatus" label="审核状态" width="100"/>
                    <el-table-column prop="approvalDate" label="审核通过日期" width="200"/>
                    <el-table-column prop="note" label="备注" width="400"/>
                </el-table>

                <div style="margin: 20px 20px">
                    <el-pagination background layout="prev, pager, next" :total="1000"/>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<style>

</style>
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
import {onMounted, reactive, ref} from "vue";
import {ElMessageBox} from 'element-plus'
import router from "@/router";
import axios from "axios";
import qs from "qs";

const dialogVisible = ref(false)

const loadContents = () => {
    //展示数据
    axios.get('http://localhost:9002/v1/excel/listAll/')
        .then((response) => {
            if (response.data.code == 2001) {
                tableData.value = response.data.data;
            }
        })
}
const tableData = ref([]);
onMounted(() => {
    loadContents();
})

const exportExcel = () => {
    if (confirm("是否全部导出?")) {
        window.location.href = 'http://localhost:9002/v1/excel/download/';
    }
}


const loadTypeContents = () => {
    //展示数据
    axios.get('http://localhost:9002/v1/excel/listAll/')
        .then((response) => {
            if (response.data.code == 2001) {
                tableData.value = response.data.data;
            }
        })
}
const typeList = ref([]);
onMounted(() => {
    loadTypeContents();
})
const dialogFormVisible = ref(false)
const exportExcelByType = () => {
    if (confirm("是否导出?")) {
        let data = qs.stringify(type);
        axios.get('http://localhost:9002/v1/excel/downloadByType/',data)
            .then((response) => {
                console.log("下载excel");
                window.location.href = 'http://localhost:9002/v1/excel/downloadByType/';

            })
    }
}

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