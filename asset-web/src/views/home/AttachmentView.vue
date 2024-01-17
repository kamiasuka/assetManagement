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
            <el-table :data="tableData"  style="width: 100%; margin-top: 5px;"  :row-height="50" >
                <el-table-column :span="2" prop="date" label="单位" width="80"></el-table-column>
                <el-table-column :span="4" prop="name" label="批次号" width="200"></el-table-column>
                <el-table-column :span="2" prop="address" label="类型" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="名称" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="使用状态" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="数量" width="180"></el-table-column>
                <el-table-column :span="2" prop="address" label="备注" width="240"></el-table-column>
                <el-table-column :span="4" prop="address" label="更新时间" width="220"></el-table-column>
                <el-table-column :span="4" prop="address" label="操作" width="200">
                    <span>查看</span>
                    <el-button text @click="dialogFormVisible = true">
                        添加附件
                    </el-button>

                    <el-dialog v-model="dialogFormVisible" title="选择文件" width="30%" center  :modal="false" :show-close="false"  >
                        <el-upload
                                v-model:file-list="fileList"
                                name="file"
                                :limit="1"
                                action="http://localhost:8080/v1/upload"
                                list-type="picture-card"
                                :on-preview="handlePictureCardPreview"
                                :on-remove="handleRemove"
                        >
                            <el-icon><Plus /></el-icon>
                        </el-upload>

                        <el-dialog v-model="dialogVisible">
                            <img w-full :src="dialogImageUrl" alt="Preview Image" />
                        </el-dialog>

                        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          上传文件
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


    //数据保存 上传组件相关
    const save =()=>{
        if(fileList.value.length>0){
            let imgUrl = fileList.value[0].response.data;
            user.value.imgUrl = imgUrl;
        }

        let data = qs.stringify(user.value);
        // axios.post('http://localhost:8080/v1/users/update',data)
        //     .then((response)=>{
        //         if(response.data.code==2001){
        //             ElMessage.success("修改完成！");
        //             //修改localStorage中的数据
        //             localStorage.user =JSON.stringify(user.value);
        //             //让整个页面刷新
        //             localtion.reload();
        //         }
        //     })
    }
    const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

    /*上传组件开始*/
    const fileList = ref([])
    const dialogImageUrl = ref('')
    const dialogVisible = ref(false)
    const handleRemove = (uploadFile, uploadFiles) => {

        // axios.post('http://localhost:8080/v1/remove?imgUrl='+uploadFile.response.data)
        //     .then((response)=>{
        //         if (response.data.code==2001){
        //             ElMessage.success("服务器文件删除完成!");
        //         }
        //     })

    }
    const handlePictureCardPreview = (uploadFile) => {
        dialogImageUrl.value = uploadFile.url
        dialogVisible.value = true
    }
    /*上传组件结束*/



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