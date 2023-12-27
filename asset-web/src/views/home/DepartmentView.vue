<template>
    <el-tabs type="border-card">
        <el-tab-pane label="部门列表">
            <el-button @click="resetDateFilter">reset date filter</el-button>
            <el-button @click="clearFilter">reset all filters</el-button>
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

</script>

<style scoped>

</style>