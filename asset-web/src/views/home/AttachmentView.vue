<template>
    <el-tabs type="border-card">
        <el-tab-pane label="附件列表">
            <div class="mt-4">
                <el-button type="primary" @click="reflush">刷新</el-button>
            </div>
            <el-table :data="tableData" #default="scope" style="width: 100%; margin-top: 5px;" :row-height="50">
                <el-table-column :span="2" prop="attachmentCode" label="附件编码" width="200" class="black"></el-table-column>
                <el-table-column :span="2" prop="type" label="附件資產類型" width="180" class="black"></el-table-column>
                <el-table-column :span="4" prop="assetCode" label="附件所属资产编码" width="200" class="black"></el-table-column>
                <el-table-column :span="2" prop="assetName" label="附件所属资产名" width="180"></el-table-column>
<!--<el-table-column :span="4" prop="status" label="使用状态" width="180" class="black">
                    <template #default="{ row }">
                        <el-button :type="row.status === '在用' ? 'success' : 'danger'"
                                   :style="{ 'pointer-events': 'none' }">
                            {{ row.status === '在用' ? '已启用' : '未启用' }}
                        </el-button>
                    </template>
                </el-table-column>-->
                <el-table-column :span="4" prop="updatedTime" label="更新时间" width="220" class="black"></el-table-column>
                <el-table-column :span="2" prop="tip" label="备注" width="300" class="black"></el-table-column>
                <el-table-column :span="8" prop="address" label="操作" width="300" class="black">
                    <template #default="scope">
                        <a :href="'http://localhost:9004/' + scope.row.url" target="_blank">
                            <el-button style="width: 70px; height: 30px;margin-left: 20px" type="success">查看附件</el-button>
                        </a>
                        <el-button style="width: 70px; height: 30px;margin-left: 20px" type="danger" @click="del(scope.row)">删除附件</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="position: fixed; bottom: 10px;">
                <el-pagination
                        background layout="prev, pager, next"
                        :total="Total"
                        :page-size="pageSize"
                        @current-change="handleCurrentChange"
                />
            </div>
        </el-tab-pane>
        <el-tab-pane label="附件添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="附件名称:">
                    <el-input v-model="attachmentAddInfo.assetName" style="width: 400px;"></el-input>
                </el-form-item>
                <el-form-item label="资产编码:">
                    <el-input v-model="attachmentAddInfo.assetCode" style="width: 400px;">
                    </el-input>
                </el-form-item>
                <el-form-item label="附件类型:">
                    <el-select v-model="attachmentAddInfo.type" class="m-2" placeholder="Select" style="width: 400px;">
                        <el-option
                                v-for="item in options3"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="附件备注:">
                    <el-input
                            style="width: 400px;"
                            v-model="attachmentAddInfo.tip"
                            :rows="2"
                            type="textarea"
                            placeholder=""
                    />
                </el-form-item>
                <el-form-item label="添加附件:">
                    <el-upload
                            v-model:file-list="fileList"
                            name="file"
                            :limit="1"
                            action="http://localhost:9004/v1/upload"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                    >
                        <el-icon>
                            <Plus/>
                        </el-icon>
                    </el-upload>
                    <el-dialog v-model="dialogVisible">
                        <img w-full :src="dialogImageUrl" alt="Preview Image"/>
                    </el-dialog>
                    <!--   上传组件结束   -->
                </el-form-item>

                <el-form-item>
                    <el-button style="" @click="saveAttachment" type="primary">保存</el-button>
                    <el-button style="" type="">返回</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import {Search} from '@element-plus/icons-vue';
import {onMounted, ref, reactive, computed, watch} from 'vue';
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import qs from "qs";
const reflush = () => {
    location.reload();
}

const options3 = [
    {value: '资产新增', label: '资产新增',},
    {value: '资产变更', label: '资产变更',},
    {value: '资产报废', label: '资产报废',},
]

const Total = ref();
const tableData = ref([]);
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据
const handleCurrentChange = (val) => {
    pageNum.value = val;
    loadContents();
};
onMounted(() => {
    loadContents();
})

const loadContents = () => {
    //展示数据
    const page = parseInt(pageNum.value);
    axios.get('http://localhost:9004/v1/asset-attachment/listAll/' + page)
        .then((response) => {
            if (response.data.code == 2001) {
                const responseData = response.data.data;
                tableData.value = responseData.list;
                console.log("tableData:"+tableData);
                Total.value = responseData.total;
            }  else {
                ElMessage.error(response.data.msg);
            }
        })
}

//添加附件相关
const fileList = ref([])

const attachmentAddInfo = ref([{
    assetName: '',
    assetCode: '',
    type: '',
    tip: '',
    url: ''
}]);

const saveAttachment = () => {
/*    if (!attachmentAddInfo.value.assetName || !attachmentAddInfo.value.assetCode || !attachmentAddInfo.value || !attachmentAddInfo.value) {
        ElMessage.error("请填写完整的附件信息");
        return;
    }*/
    //取出图片路径
    if (fileList.value.length > 0) {
        let imgUrl = fileList.value[0].response.data;
        //把上传成功的图片路径存到user对象里面
        attachmentAddInfo.value.url = imgUrl;
    }
    let code = attachmentAddInfo.value.assetCode;
    let data = qs.stringify(attachmentAddInfo.value);
    axios.get('http://localhost:9004/v1/asset-attachment/check/' + code)
        .then(response => {
            if (response.data.code === 2001) {
                console.log("attachmentAddInfo: "+data)
                axios.post('http://localhost:9004/v1/asset-attachment/create', data)
                    .then(response => {
                        if (response.data.code === 2001) {
                            ElMessage.success("附件添加成功");
                            attachmentAddInfo.value.assetName = '';
                            attachmentAddInfo.value.assetCode = '';
                            attachmentAddInfo.value.type = '';
                            attachmentAddInfo.value.tip = '';
                            fileList.value = [];
                        } else {
                            ElMessage.error("附件添加失败");
                        }
                        //location.reload();
                    })
                    .catch(error => {
                        console.error("Error adding attachment:", error);
                        ElMessage.error("附件添加失败");
                    });
            } else {
                ElMessage.error("请填入正确的资产编码");
            }
        })
        .catch(error => {
            console.error("Error checking asset:", error);
            ElMessage.error("请输入正确资产");
        });
};

/*上传组件开始*/
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handleRemove = (uploadFile, uploadFiles) => {
    axios.post('http://localhost:9004/v1/remove?imgUrl=' + uploadFile.response.data)
        .then((response) => {
            if (response.data.code == 2001) {
                ElMessage.success("服务器文件删除完成!");
            }else {
                ElMessage.error(response.data.msg);
            }
        })
}

const handlePictureCardPreview = (uploadFile) => {
    dialogImageUrl.value = uploadFile.url
    dialogVisible.value = true
}
/*上传组件结束*/

const del = (row) => {
    let code = row.attachmentCode;
    if (confirm("您确认删除吗?")) {
        axios.post('http://localhost:9004/v1/asset-attachment/' + code + "/delete")
            .then((response) => {
                if (response.data.code == 2001) {
                    ElMessage.success("删除完成")
                    location.reload();
                }else {
                    ElMessage.error(response.data.msg);
                }
            }
        )
    }
}


</script>

<style>
.search {
    font-size: 12px;
    display: flex;
    align-items: center;
}

.m-2 {
    width: 100px;
}

.table {
    height: 20px;
}

.pagination-wrapper {
    position: fixed;
    bottom: 50px;
    left: 280px; /* 调整左边距 */
}

.black {
    color: black;
}

</style>


