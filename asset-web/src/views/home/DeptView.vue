<template>
    <el-tabs type="border-card">
        <el-tab-pane label="部门列表">
            <div class="mt-4">
                所属单位：
                <el-input
                        style="width: 150px"
                        v-model="input"
                        placeholder="Please input"
                        class="input-with-select"
                >
                    <template #append>
                        <el-button :icon="Search" />
                    </template>
                </el-input>
            </div>
            <el-input ></el-input>
            <el-button @click="clearFilter" type="primary">查询</el-button>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="date" label="Date" width="180" />
                <el-table-column prop="name" label="Name" width="180" />
                <el-table-column prop="address" label="Address" />
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="部门添加">

        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
    import {Search} from '@element-plus/icons-vue';
</script>

<style scoped>

</style>