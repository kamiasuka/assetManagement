<template>
    <el-tabs type="border-card">
        <el-tab-pane label="【资产上报】申请列表">
            <div class="mt-4">
                是否合同：
                <el-select v-model="value" class="m-2" placeholder="全部" size="large" style="width: 240px">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>

                <el-button type="primary">查询</el-button>
                <el-button type="primary">刷新</el-button>

            </div>
            <div class="mt-4">
                <el-button type="warning">上报</el-button>
            </div>
            <el-table
                    border
                    stripe
                    :data="tableData"
                    style="width: 100%"
            >
                <el-table-column type="selection" width="55" />
                <el-table-column property="id" label="资产编码" width="150" />
                <el-table-column property="name" label="资产名称" width="150" />
                <el-table-column label="申请日期" width="160">
                    <template #default="scope">{{ scope.row.startdate }}</template>
                </el-table-column>
                <el-table-column property="unit" label="所属单位" width="150" />
                <el-table-column property="status" label="审核状态" width="150">
                    <template #default="scope">
                        <el-button type="warning" v-if="scope.row.status==0">审核中</el-button>
                        <el-button type="success" v-if="scope.row.status==1">已通过</el-button>
                    </template>
                </el-table-column>
                <el-table-column label="通过日期" width="160">
                    <template #default="scope">{{ scope.row.enddate }}</template>
                </el-table-column>
                <el-table-column property="operate" label="操作" width="150">

                    <el-button text @click="dialogFormVisible = true">查看</el-button>

                    <el-dialog v-model="dialogFormVisible" title="资产详情">
                        <el-form label-width="100px">
                            <el-form-item label="资产编码"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="资产名称"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="资产类型"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="所属部门"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="所属单位"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="使用年限"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                            <el-form-item label="资产价值"><el-input disabled style="width: 200px;"></el-input></el-form-item>
                        </el-form>
                        <template #footer>
                                    <span class="dialog-footer">
                                        <el-button @click="dialogFormVisible = false">确定</el-button>
                                    </span>
                        </template>
                    </el-dialog>
                </el-table-column>

            </el-table>
        </el-tab-pane>
        <el-tab-pane label="【资产上报】申请">
            <div class="common-layout">
                <el-container>
                    <el-aside width="300px">
                        <el-card class="box-card">
                            <template #header>
                                <div class="card-header">
                                    <span>【资产上报】申请编辑</span>
                                </div>
                            </template>
                            <el-form label-width="100px">
                                <el-form-item label="操作类型:" style="text-align: center">
                                    <el-select v-model="value" class="m-2" placeholder="全部" size="large" style="width: 240px">
                                        <el-option v-for="item in applyForOptions" :key="item.value" :label="item.label" :value="item.value"/>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="备注:" style="text-align: center">
                                    <el-input v-model="form" type="textarea" />
                                </el-form-item>
                            </el-form>
                        </el-card>
                    </el-aside>
                    <el-main>

                        <div class="mt-4">
                            资产编码:
                            <el-input style="width: 150px"></el-input>
                            资产名称:
                            <el-input style="width: 150px"></el-input>
                            通用类别：
                            <el-select v-model="value" class="m-2" placeholder="全部" size="large" style="width: 150px">
                                <el-option v-for="item in categoryOptions" :key="item.value" :label="item.label" :value="item.value"/>
                            </el-select>
                            <el-button type="primary">查询</el-button>
                            <el-button type="primary" style="text-align: right">提交</el-button>
                            <el-button type="primary" style="text-align: right">返回</el-button>
                        </div>
                        <el-table :data="applytableData">
                            <el-table-column>
                                <el-table-column type="selection" width="55" />
                                <el-table-column property="id" label="资产编码" width="150" />
                                <el-table-column property="name" label="资产名称" width="150" />
                                <el-table-column property="category" label="资产分类" width="150" />
                                <el-table-column property="unit" label="所属单位" width="150" />
                                <el-table-column property="status" label="审核状态" width="150">
                                    <template #default="scope">
                                        <el-button type="warning" v-if="scope.row.status==0">审核中</el-button>
                                        <el-button type="success" v-if="scope.row.status==1">已通过</el-button>
                                    </template>
                                </el-table-column>
                                <el-table-column property="amount" label="资产价值" width="150" />
                                <el-table-column property="useStatus" label="使用状态" width="150">
                                    <template #default="scope">
                                        <a v-if="scope.row.useStatus==0">在用</a>
                                        <a v-if="scope.row.useStatus==1">闲置</a>
                                        <a v-if="scope.row.useStatus==2">在修</a>
                                        <a v-if="scope.row.useStatus==3">报废</a>
                                    </template>
                                </el-table-column>
                                <el-table-column property="nowStatus" label="当前状态" width="150">
                                    <template #default="scope">
                                        <a v-if="scope.row.nowStatus==0">资产新增</a>
                                    </template>
                                </el-table-column>
                                <el-table-column property="unit" label="操作" width="50">
                                    <a>附件浏览</a>
                                </el-table-column>
                            </el-table-column>

                        </el-table>


                    </el-main>
                </el-container>
            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
    import {reactive, ref } from 'vue';
    import { ElTable } from 'element-plus';
    let dialogTableVisible = ref(false);
    let dialogFormVisible = ref(false);
    const value = ref('');
    const categoryOptions = [
        {
            value:'option1',
            label:'全部',
        }
    ]
    const applyForOptions = [
        {
            value:'option1',
            label:'资产上报',
        },
    ]
    const options = [
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
        },

    ]

    const tableData =[
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            startdate:'2001-12-03',
            enddate:'2003-04-05',
            status:'1',
            nowStatus:'0',
        },
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            startdate:'2001-12-03',
            enddate:'2003-04-05',
            status:'0',
            nowStatus:'0',
        },
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            startdate:'2001-12-03',
            enddate:'2003-04-05',
            status:'1',
            nowStatus:'0',
        },

    ]

    const applytableData =[
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            status:'1',
            amount:'￥24114',
            useStatus:'1',
        },
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            status:'0',
            amount:'￥24114',
            useStatus:'1',
        },
        {
            id:'124241151',
            name:'资产名称',
            unit:'所属单位',
            status:'1',
            amount:'￥24114',
            useStatus:'1',
        },

    ]
</script>

<style scoped>
    .dialog-footer button:first-child {
        margin-right: 10px;
    }
</style>
