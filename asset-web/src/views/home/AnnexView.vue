<template>
    <el-tabs type="border-card">
        <el-tab-pane label="附件列表">
            <el-row :gutter="10" class="search">
                <el-col :span="5" >
                    所属单位：
                <el-input
                        size="small"
                        style="width: 180px"
                        v-model="input"
                        placeholder="XX公司"
                        class="input-with-select">
                    <template #append >
                        <el-button size="small" :icon="Search" />
                    </template>
                </el-input></el-col>
                <el-col :span="4" >
                    批次号：
                    <el-input style="width: 150px" size="small" ></el-input>

                </el-col>
                <el-col :span="4.5" >
                附件名称：
                <el-input style="width: 150px" size="small" ></el-input>

                </el-col>
                <el-col :span="2.5">
                     附件类型：
                    <el-dropdown popper-class="custom-dropdown" >
                        <el-button type="primary" size="small" style="width: 100px;" >
                            {{ selectedOption }}<el-icon class="el-icon--right"><arrow-down /></el-icon>
                        </el-button>
                        <template #dropdown>
                            <el-dropdown-menu style="background-color: white;">
                                <el-dropdown-item @click="handleDropdownItemClick('全部')">全部</el-dropdown-item>
                                <el-dropdown-item @click="handleDropdownItemClick('Action 2')">Action 2</el-dropdown-item>
                                <el-dropdown-item @click="handleDropdownItemClick('Action 3')">Action 3</el-dropdown-item>
                                <el-dropdown-item @click="handleDropdownItemClick('Action 4')">Action 4</el-dropdown-item>
                                <el-dropdown-item @click="handleDropdownItemClick('Action 5')">Action 5</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </el-col>
                <el-col :span="2" >


                    <el-button @click="clearFilter" type="primary" size="small">查询</el-button>
                </el-col>
            </el-row>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="date" label="单位" width="180"></el-table-column>
                <el-table-column prop="name" label="批次号" width="180"></el-table-column>
                <el-table-column prop="address" label="类型"></el-table-column>
                <el-table-column prop="address" label="名称"></el-table-column>
                <el-table-column prop="address" label="使用状态"></el-table-column>
                <el-table-column prop="address" label="数量"></el-table-column>
                <el-table-column prop="address" label="备注"></el-table-column>
                <el-table-column prop="address" label="更新时间"></el-table-column>
                <el-table-column prop="address" label="操作">
                    <span>查看</span>
                    <span>添加附件</span>
                    <span>附件浏览</span>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="附件添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">
                    <el-input style="width: 300px;"><template #append>
                        <el-button :icon="Search" />
                    </template></el-input>
                </el-form-item>
                <el-form-item label="附件备注:">
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
    import { Search } from '@element-plus/icons-vue';
    import { ref } from 'vue';

    // 使用 `ref` 来声明响应式数据
    const selectedOption = ref('全部'); // Assuming `selectedOption` is a reactive variable

    // 使用 `methods` 来声明方法
    const handleDropdownItemClick = (action) => {
        selectedOption.value = action;
    };
</script>

<style >
    .search{
        font-size: 12px;
        display: flex;
        align-items: center;
    }


</style>