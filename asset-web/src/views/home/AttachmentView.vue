<template>
    <el-tabs type="border-card">
        <el-tab-pane label="附件列表">
            <el-row :gutter="10" class="search">
                <el-col :span="5" style="font-size: 14px;" >
                    所属单位：
                    <el-input
                            size="normal"
                            style="width: 200px"
                            v-model="searchlist.unit"
                            placeholder="XX公司"
                            class="input-with-select">
                        <template #append >
                            <el-button size="normal" :icon="Search" />
                        </template>
                    </el-input></el-col>
                <el-col :span="4" style="font-size: 14px;"   >
                    批次号：
                    <el-input style="width: 180px" size="normal"  ></el-input>

                </el-col>
                <el-col :span="5" style="font-size: 14px;" >
                    附件名称：
                    <el-input style="width: 180px" size="normal" v-model="searchlist.name" ></el-input>

                </el-col>
                <el-col :span="4" style="font-size: 14px;">
                    附件类型：
                    <el-select v-model="value2" class="m-2" placeholder="Select" size="normal" style="width: 130px">
                        <el-option
                                v-for="item in options2"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                    </el-select>
                </el-col>
                <el-col :span="4" style="font-size: 14px;">
                    使用状态：
                    <el-select v-model="value" class="m-2" placeholder="Select" size="normal" style="width: 130px">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                        />
                    </el-select>
                </el-col>
                <el-col :span="2" >
                    <el-button  type="primary" size="name" @click="search">查询</el-button>
                </el-col>
            </el-row>
            <el-table :data="arr" #default="scope"  style="width: 100%; margin-top: 5px;"  :row-height="50"  >
                <el-table-column :span="2" prop="unit" label="单位" width="80"></el-table-column>
                <el-table-column :span="4" prop="name" label="批次号" width="200"></el-table-column>
                <el-table-column :span="2" prop="type" label="类型" width="180"> <template #default="{ row }">
                    {{ getText(row.type) }}
                </template>
                </el-table-column>
                <el-table-column :span="2" prop="name" label="名称" width="180"></el-table-column>
                <el-table-column :span="4" prop="status" label="使用状态" width="180">
                    <template #default="{ row }">
                        <el-button :type="row.status === '1' ? 'success' : 'danger'"  :style="{ 'pointer-events': 'none' }" >
                            {{ row.status === '1' ? '已启用' : '未启用' }}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column :span="2" prop="tip" label="备注" width="300"></el-table-column>
                <el-table-column :span="4" prop="updatedTime" label="更新时间" width="220">
                </el-table-column>
                <el-table-column :span="8" prop="address" label="操作" width="300">
                    <template #default="scope">
                        <a :href="'http://localhost:9004/' + scope.row.url" target="_blank">
                        <el-button type="success">查看</el-button>
                        </a>
                        <el-button style=";margin-left: 20px" type="primary" @click="edit(scope.row)">编辑</el-button>
                    <el-button style="width: 70px; height: 30px;margin-left: 20px" type="danger"  @click="del(scope.$index,scope.row)">
                        删除附件
                    </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="附件添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item  label="附件名称:">
                    <el-input v-model="attachmentlist.name" style="width: 400px;" ></el-input>
                </el-form-item>
                <el-form-item  label="所属单位:">
                    <el-input v-model="attachmentlist.unit" style="width: 400px;" ><template #append>
                        <el-button :icon="Search" />
                    </template></el-input>
                </el-form-item>
                    <el-form-item label="附件类型:">
                        <el-select v-model="value3" class="m-2" placeholder="Select"  style="width: 400px;">
                            <el-option
                                    v-for="item in options3"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"/>
                        </el-select>

                    </el-form-item>
                <el-form-item  label="附件备注:">
                    <el-input
                            style="width: 400px;"
                            v-model="textarea"
                            :rows="2"
                            type="textarea"
                            placeholder=""
                    />
                </el-form-item>
                <el-form-item label="添加附件:">
<!--                    <img v-if="attachmentlist.id!=null&&fileList.length==0"-->
<!--                         :src="'http://localhost:8080'+content.imgUrl"-->
<!--                         style="width: 148px;height: 148px;"> -->
                    <!--   上传组件开始   -->
                    <el-upload
                            v-model:file-list="fileList"
                            name="file"
                            :limit="1"
                            action="http://localhost:9004/v1/upload"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                    >
                        <el-icon><Plus /></el-icon>
                    </el-upload>

                    <el-dialog v-model="dialogVisible">
                        <img w-full :src="dialogImageUrl" alt="Preview Image" />
                    </el-dialog>
                    <!--   上传组件结束   -->
                </el-form-item>

                <el-form-item>
                    <el-button style=""  @click="saveAttachment" type="primary">保存</el-button>
                    <el-button style="" type="" >返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
    import { Search } from '@element-plus/icons-vue';
    import {onMounted, ref,reactive } from 'vue';
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import router from "@/router";



    // 使用 `ref` 来声明响应式数据
    const selectedOption = ref('全部'); // Assuming `selectedOption` is a reactive variable

    // 使用 `methods` 来声明方法
    const handleDropdownItemClick = (action) => {
        selectedOption.value = action;
    };

    const value = ref('')
    const options = [
        {value: '', label: '全部'},
        {value: '1', label: '已启用',},
        {value: '2', label: '未启用',},
    ]

    const value2 = ref('')
    const options2 = [
        {value: '', label: '全部'},
        {value: '1', label: '好看的',},
        {value: '2', label: '好吃的',},
        {value: '3', label: '好玩的',}
    ]
    const value3 = ref('')
    const options3 = [
        {value: '1', label: '好看的',},
        {value: '2', label: '好吃的',},
        {value: '3', label: '好玩的',}
    ]
    const getText = (type) => {
        const option = options2.find(option => option.value === type);
        return option ? option.label : '';
    };





    //列表关联数据库
    const arr = ref([]);
    onMounted(()=>{
        if (!isMounted.value) {

            isMounted.value = true;
        }
        axios.get("http://localhost:9004"+"/v1/asset-attachment/admin").then((response)=>{
            if (response.data.code==2001){
                arr.value = response.data.data;
            }
        })
    })
    const isMounted = ref(false);

    const searchlist = ref({
        unit: '',
        type: '',
        name: '',
        status:''
    });
    const arr2 = ref([]);

    const search = () => {
        const searchData = {
            name: searchlist.value.name,
            status: value.value,
            unit: searchlist.value.unit,
            type: value2.value,

        };

        console.log(searchData);

        axios.post('http://localhost:9004/v1/asset-attachment/search', searchData)
            .then(response => {
                // 处理来自后端的响应
                console.log('来自后端的响应:', response.data);
                // 可选择根据响应更新前端
                if (response.data.code==2001){
                    arr.value = response.data.data;
                }
                ElMessage.success("附件查询成功");
            })
            .catch(error => {
                // 处理错误
                console.error('发送数据到后端时出错:', error);
            });
    };

    //添加附件相关
    // Declare your reactive variables
    const attachmentlist = ref({
        name: '',
        unit: '',
    });

    const textarea = ref('');
    // Function to handle saving the attachment
    const saveAttachment = () => {

        // Prepare data to send
        const data = {
            name: attachmentlist.value.name,
            unit: attachmentlist.value.unit,
            type: value3.value,
            tip: textarea.value,
            // Assuming you have a field to store the address of the attachment
            url: fileList.value.length > 0 ? fileList.value[0].response.data : ''
        };
        console.log(data);

        // Make POST request to the backend
        axios.post('http://localhost:9004/v1/asset-attachment/create', data)
            .then(response => {
                if (response.data.code === 2001) {
                    ElMessage.success("附件添加成功");
                    // Optionally, you can clear the form fields after successful submission
                    attachmentlist.value.unit = '';
                    value2.value = '';
                    textarea.value = '';
                    fileList.value = [];
                } else {
                    ElMessage.error("附件添加失败");
                }
                location.reload();
            })
            .catch(error => {
                console.error("Error adding attachment:", error);
                ElMessage.error("附件添加失败");
            });
    };

    const dialogFormVisible = ref(false)
    const formLabelWidth = '140px'

    /*上传组件开始*/
    const fileList = ref([])
    const dialogImageUrl = ref('')
    const dialogVisible = ref(false)
    const handleRemove = (uploadFile, uploadFiles) => {

        axios.post('http://localhost:9004/v1/remove?imgUrl='+uploadFile.response.data)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("服务器文件删除完成!");
                }
            })

    }
    const handlePictureCardPreview = (uploadFile) => {
        dialogImageUrl.value = uploadFile.url
        dialogVisible.value = true
    }
    /*上传组件结束*/

    const del =(i,attachmentlist)=>{
        if(confirm("您确认删除吗?")){
            axios.post("http://localhost:9004"+'/v1/asset-attachment/'+attachmentlist.id+"/delete").then((response)=>{
                    if (response.data.code==2001){
                        ElMessage.success("删除完成")
                        arr.value.splice(i,1)
                    }
                }
            )
        }
    }

    const edit = (c)=>{
        localStorage.from = 'admin';//设置从哪个页面去的修改页面
        router.push('/personal/post?id='+c.id);
    }



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











<!--<el-dialog v-model="dialogFormVisible" title="选择文件" width="30%" center  :modal="false" :show-close="false"  >-->
<!--    <el-upload-->
<!--            v-model:file-list="fileList"-->
<!--            name="file"-->
<!--            :limit="1"-->
<!--            action="http://localhost:8080/v1/upload"-->
<!--            list-type="picture-card"-->
<!--            :on-preview="handlePictureCardPreview"-->
<!--            :on-remove="handleRemove"-->
<!--    >-->
<!--        <el-icon><Plus /></el-icon>-->
<!--    </el-upload>-->

<!--    <el-dialog v-model="dialogVisible">-->
<!--        <img w-full :src="dialogImageUrl" alt="Preview Image" />-->
<!--    </el-dialog>-->

<!--    <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="dialogFormVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="dialogFormVisible = false">-->
<!--          上传文件-->
<!--        </el-button>-->
<!--      </span>-->
<!--    </template>-->
<!--</el-dialog>-->