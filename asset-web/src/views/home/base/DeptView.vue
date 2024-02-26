<template>
    <el-tabs type="border-card">
        <el-tab-pane label="部门列表">
            <div class="mt-4">
                所属单位：
                <el-input
                        style="width: 200px"
                        v-model="input"
                        placeholder="XX公司"
                        class="input-with-select"
                >
                    <template #append>
                        <el-button :icon="Search" />
                    </template>
                </el-input>
                部门名称：
                <el-input style="width: 180px" ></el-input>
                <el-button @click="clearFilter" type="primary">查询</el-button>
            </div>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="date" label="部门名称" width="180"></el-table-column>
                <el-table-column prop="name" label="所属单位" width="180"></el-table-column>
                <el-table-column prop="address" label="部门编码"></el-table-column>
                <el-table-column prop="address" label="部门电话"></el-table-column>
                <el-table-column prop="address" label="操作">
                    <span>修改</span>
                    <span>删除</span>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="部门添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">
                    <el-input style="width: 300px;"><template #append>
                        <el-button :icon="Search" />
                    </template></el-input>
                </el-form-item>
                <el-form-item label="部门名称:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="部门编码:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="部门电话:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="备注:">
                    <el-input
                            style="width: 300px;"
                            v-model="textarea"
                            :rows="2"
                            type="textarea"
                            placeholder=""
                    />
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
    import {Search} from '@element-plus/icons-vue';
</script>

<style scoped>

</style>