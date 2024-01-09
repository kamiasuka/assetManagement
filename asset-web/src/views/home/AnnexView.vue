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
                <el-col :span="5" >
                附件名称：
                <el-input style="width: 150px" size="small" ></el-input>

                </el-col>
                <el-col :span="4">
                     附件类型：
                    <el-select v-model="value" class="m-2" placeholder="Select" size="small">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                        />
                    </el-select>
                </el-col>
                    <el-col :span="4">
                        使用状态：
                        <el-select v-model="value1" class="m-2" placeholder="Select" size="small">
                            <el-option
                                    v-for="item in options1"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                            />
                        </el-select>
                </el-col>
                <el-col :span="2" >
                    <el-button @click="clearFilter" type="primary" size="small">查询</el-button>
                </el-col>
            </el-row>
            <el-table :data="tableData" stripe style="width: 100%; margin-top: 5px;"  :row-height="50" >
                <el-table-column :span="2" prop="date" label="单位" width="80"></el-table-column>
                <el-table-column :span="4" prop="name" label="批次号" width="200"></el-table-column>
                <el-table-column :span="2" prop="address" label="类型" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="名称" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="使用状态" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="数量" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="备注" width="240"></el-table-column>
                <el-table-column :span="4" prop="address" label="更新时间" width="220"></el-table-column>
                <el-table-column :span="4" prop="address" label="操作" width="300">
                    <span>查看</span>
                    <el-button text @click="dialogFormVisible = true">
                        添加附件
                    </el-button>

                    <el-dialog v-model="dialogFormVisible" title="Shipping address">
                        <el-form :model="form">
                            <el-form-item label="Promotion name" :label-width="formLabelWidth">
                                <el-input v-model="form.name" autocomplete="off" />
                            </el-form-item>
                            <el-form-item label="Zones" :label-width="formLabelWidth">
                                <el-select v-model="form.region" placeholder="Please select a zone">
                                    <el-option label="Zone No.1" value="shanghai" />
                                    <el-option label="Zone No.2" value="beijing" />
                                </el-select>
                            </el-form-item>
                        </el-form>
                        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          Confirm
        </el-button>
      </span>
                        </template>
                    </el-dialog>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="附件添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">
                    <el-input style="width: 400px;"><template #append>
                        <el-button :icon="Search" />
                    </template></el-input>
                </el-form-item>
                <el-form-item label="附件类型:">
                        <el-select v-model="value2" class="m-2" placeholder="Select"  style="width: 400px;">
                            <el-option
                                    v-for="item in options2"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"/>
                        </el-select>

                </el-form-item>
                <el-form-item label="附件备注:">
                    <el-input
                            style="width: 400px;"
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
    import { ref,reactive } from 'vue';

    // 使用 `ref` 来声明响应式数据
    const selectedOption = ref('全部'); // Assuming `selectedOption` is a reactive variable

    // 使用 `methods` 来声明方法
    const handleDropdownItemClick = (action) => {
        selectedOption.value = action;
    };

    const value = ref('')
    const options = [
        {value: '1', label: '全部',},
        {value: '2', label: 'Option2',},
        {value: '3', label: 'Option3',},
        {value: '4', label: 'Option4',},
        {value: '5', label: 'Option5',},]
    const value1 = ref('')
    const options1 = [
        {value: '1', label: '已使用',},
        {value: '2', label: '未使用',},
    ]
    const value2 = ref('')
    const options2 = [
        {value: '1', label: '好看的',},
        {value: '2', label: '好吃的',},
    ]

    const tableData = [
        {date: '2016-05-03', name: 'Tom', address: 'No. 189',},
        {date: '2016-05-02', name: 'Tom', address: 'No. 189',},
        {date: '2016-05-04', name: 'Tom', address: 'No. 189',},]


    //添加附件相关

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



</script>

<style >
    .search{
        font-size: 12px;
        display: flex;
        align-items: center;
    }
    .m-2{
        width: 100px;
    }
    .table{
        height: 20px;
    }

</style>