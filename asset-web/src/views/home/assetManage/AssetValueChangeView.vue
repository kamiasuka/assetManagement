<template>
    <el-tabs type="border-card">
        <el-tab-pane label="价值变更列表">
            <div class="mt-4">
                变更日期：
                <el-input>
                    <el-form-item label="Activity time">
                        <el-col :span="11">
                            <el-date-picker
                                    v-model="form.date1"
                                    type="date"
                                    placeholder="Pick a date"
                                    style="width: 100%"
                            />
                        </el-col>
                    </el-form-item></el-input>
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
    </el-tabs>
</template>

<script setup>

</script>

<style scoped>

</style>