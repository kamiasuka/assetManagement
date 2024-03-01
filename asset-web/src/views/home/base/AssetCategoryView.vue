<template>
    <div class="common-layout">
        <!--        <el-header>-->
        <!--        </el-header>-->
        <el-container>
            <el-aside width="200px" style="border: 5px;border-color: black">
                    <!-- Form -->
                    <el-button type="primary" @click="dialogFormVisible = true">编辑资产分类</el-button>

                    <el-dialog v-model="dialogFormVisible" title="编辑资产分类">
                        <el-form :model="form">
                            <el-form-item label="分类名称" :label-width="formLabelWidth">
                                <el-input v-model="form.name" autocomplete="off"/>
                            </el-form-item>

                            <el-form-item label="分类级别" :label-width="formLabelWidth">
                                <el-select v-model="form.region" placeholder="请选择分类级别">
                                    <el-option label="Zone No.1" value="shanghai"/>
                                    <el-option label="Zone No.2" value="beijing"/>
                                </el-select>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <span class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取消</el-button>
                                <el-button type="primary" @click="dialogFormVisible = false">提交</el-button>
                            </span>
                        </template>
                    </el-dialog>
                <el-row class="tac">
                    <el-col>
                        <h5 class="mb-2">资产分类</h5>
                        <el-menu
                                default-active="2"
                                class="el-menu-vertical-demo"
                                @open="handleOpen"
                                @close="handleClose"
                        >

                            <el-sub-menu index="1">
                                <template #title>
                                    <el-icon>
                                        <document/>
                                    </el-icon>
                                    <span>土地</span>
                                </template>
                                <el-sub-menu index="1-1">
                                    <template #title>商用土地</template>
                                    <el-menu-item index="1-1-1">item one</el-menu-item>
                                </el-sub-menu>
                                <el-sub-menu index="1-2">
                                    <template #title>出租土地</template>
                                    <el-menu-item index="1-1-2">item 2</el-menu-item>
                                </el-sub-menu>
                            </el-sub-menu>



                            <el-sub-menu index="2">
                                <template #title>
                                    <el-icon>
                                        <document/>
                                    </el-icon>
                                    <span>房屋</span>
                                </template>
                                <el-sub-menu index="2-1">
                                    <template #title>商用房屋</template>
                                    <el-menu-item index="1-1-1">item one</el-menu-item>
                                </el-sub-menu>
                                <el-sub-menu index="2-2">
                                    <template #title>出租房屋</template>
                                    <el-menu-item index="1-1-2">item 2</el-menu-item>
                                </el-sub-menu>
                            </el-sub-menu>



                            <el-sub-menu index="3">
                                <template #title>
                                    <el-icon>
                                        <document/>
                                    </el-icon>
                                    <span>电子设备</span>
                                </template>
                                <el-sub-menu index="3-1">
                                    <template #title>办公设备</template>
                                    <el-menu-item index="3-1-1">显示器</el-menu-item>
                                    <el-menu-item index="3-1-2">鼠标</el-menu-item>
                                    <el-menu-item index="3-1-3">主机</el-menu-item>
                                    <el-menu-item index="3-1-4">投影仪</el-menu-item>
                                </el-sub-menu>
                                <el-sub-menu index="3-2">
                                    <template #title>监控设备</template>
                                    <el-menu-item index="3-2-1">摄像头</el-menu-item>
                                    <el-menu-item index="3-2-2">全景摄像头</el-menu-item>
                                    <el-menu-item index="3-2-3">监控服务器</el-menu-item>
                                    <el-menu-item index="3-2-4">监控显示器</el-menu-item>
                                </el-sub-menu>
                            </el-sub-menu>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-main>
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column fixed prop="id" label="资产编码" width="100"/>
                    <el-table-column prop="name" label="资产名称" width="120"/>
                    <el-table-column prop="type" label="资产分类" width="120"/>
                    <el-table-column prop="dept" label="所属部门" width="120"/>
                    <el-table-column prop="unit" label="所属单位" width="120"/>
                    <el-table-column prop="life" label="使用年限" width="120"/>
                    <el-table-column prop="amount" label="资产价值" width="120"/>
                    <el-table-column prop="useStatus" label="使用状态" width="120"/>
                    <el-table-column prop="reviewStatus" label="审核状态" width="120"/>
                    <el-table-column prop="approvalDate" label="审核通过日期" width="120"/>
                    <el-table-column prop="note" label="备注" width="120"/>
                    <el-table-column fixed="right" label="操作" width="140">
                        <template #default>
                            <!--                            <el-button link type="primary"  @click="handleClick">Detail</el-button>-->
                            <!--                            <el-button link type="primary" size="small">Edit</el-button>-->
                            <el-button type="success" size="small">编辑</el-button>
                            <el-button type="danger" size="small">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <div style="margin: 20px 20px">
                    <el-pagination background layout="prev, pager, next" :total="1000"/>
                </div>
            </el-main>
        </el-container>
    </div>
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

const category_level = 1;

const handleClick = () => {
    console.log('click')
}

const tableData = [
    {
        id: '1',
        name: '逸一时误一世',
        type: '无形资产',
        dept: '下北泽部门',
        unit: '仙贝有限公司',
        life: '114',
        amount: '514',
        useStatus: '在用',
        reviewStatus: '已通过',
        approvalDate: '审核通过日期',
        note: ''
    },
    {
        id: '2',
        name: '逸一时误一世',
        type: '无形资产',
        dept: '下北泽部门',
        unit: '仙贝有限公司',
        life: '114',
        amount: '514',
        useStatus: '在用',
        reviewStatus: '已通过',
        approvalDate: '审核通过日期',
        note: ''
    },
    {
        id: '3',
        name: '逸一时误一世',
        type: '无形资产',
        dept: '下北泽部门',
        unit: '仙贝有限公司',
        life: '114',
        amount: '514',
        useStatus: '在用',
        reviewStatus: '已通过',
        approvalDate: '审核通过日期',
        note: ''
    },
    {
        id: '4',
        name: '逸一时误一世',
        type: '无形资产',
        dept: '下北泽部门',
        unit: '仙贝有限公司',
        life: '114',
        amount: '514',
        useStatus: '在用',
        reviewStatus: '已通过',
        approvalDate: '审核通过日期',
        note: ''
    },
]

import { reactive, ref } from 'vue'

const dialogTableVisible = ref(false)
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

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

const gridData = [
    {
        date: '2016-05-02',
        name: 'John Smith',
        address: 'No.1518,  Jinshajiang Road, Putuo District',
    },
    {
        date: '2016-05-04',
        name: 'John Smith',
        address: 'No.1518,  Jinshajiang Road, Putuo District',
    },
    {
        date: '2016-05-01',
        name: 'John Smith',
        address: 'No.1518,  Jinshajiang Road, Putuo District',
    },
    {
        date: '2016-05-03',
        name: 'John Smith',
        address: 'No.1518,  Jinshajiang Road, Putuo District',
    },
]


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
</style>