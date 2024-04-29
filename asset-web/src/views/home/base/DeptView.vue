<template>
    <el-tabs type="border-card" v-model="deptTab">
        <el-tab-pane label="部门列表" name="tab1">
            <div class="mt-4">
                所属单位：
                <el-input
                        style="width: 200px"
                        v-model="QueryByUnit"
                        placeholder="XX公司"
                        class="input-with-select"
                >
                        <template #append>
                            <el-button :icon="Search"></el-button>
                        </template>
                </el-input>
                部门名称：
                <el-input style="width: 180px" v-model="QueryByName"></el-input>
                <el-button @click="clearFilter" type="primary">查询</el-button>
            </div>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="name" label="部门名称" width="200"></el-table-column>
                <el-table-column prop="unit" label="所属单位" width="200"></el-table-column>
                <el-table-column prop="code" label="部门编码" width="200"></el-table-column>
                <el-table-column prop="tel" label="部门电话" width="200"></el-table-column>
                <el-table-column fixed="right" label="操作" width="200">
                    <template #default="scope">
                        <div v-if="user.identity==='管理员'&& tableData.length >= 1">
                            <el-button type="success" size="small" @click="handleClickOnEdit(scope.row)">编辑</el-button>
                            <el-button type="danger" size="small" @click="deleteByCode(scope.row)">删除</el-button>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </el-tab-pane>
       <div v-if="user.identity==='管理员'">
           <el-tab-pane label="部门添加" name="tab2">
               <el-form label-width="100px" style="margin: 50px 150px">
                   <el-form-item label="部门编码:">
                       <el-input style="width: 300px;" v-model="deptAddInfo.code"></el-input>
                   </el-form-item>
                   <el-form-item label="部门名称:" >
                       <el-input style="width: 300px;" v-model="deptAddInfo.name"></el-input>
                   </el-form-item>
                   <el-form-item label="所属单位:" >
                       <el-input style="width: 300px;" v-model="deptAddInfo.unit"></el-input>
                   </el-form-item>
                   <el-form-item label="部门电话:" >
                       <el-input style="width: 300px;" v-model="deptAddInfo.tel"></el-input>
                   </el-form-item>

                   <el-form-item>
                       <el-button style="" type="primary" @click="saveDept">保存</el-button>
                       <el-button style="" type="" @click="goToTab('tab1')">返回</el-button>
                   </el-form-item>
               </el-form>
           </el-tab-pane>
       </div>
    </el-tabs>

<!--    部门修改按钮的弹窗-->
    <el-dialog
        v-model="deptUpdateDialogVisible"
        title="部门修改"
        width="500"
    >
    <!-- :before-close="handleClose"-->

        <el-form label-width="100px" style="margin: 0">
            <el-form-item label="部门编码:">
                <el-input :disabled="true" style="width: 300px;" v-model="deptUpdateInfo.code" ></el-input>
            </el-form-item>
            <el-form-item label="部门名称:" >
                <el-input style="width: 300px;" v-model="deptUpdateInfo.name"></el-input>
            </el-form-item>
            <el-form-item label="所属单位:" >
                <el-input style="width: 300px;" v-model="deptUpdateInfo.unit"></el-input>
            </el-form-item>
            <el-form-item label="部门电话:" >
                <el-input style="width: 300px;" v-model="deptUpdateInfo.tel"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="deptUpdateDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="handleClickOnUpdate()">
                    确认
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
    Check,
    Delete,
    Edit,
    Message,
    Search,
    Star,
} from '@element-plus/icons-vue'
import {onMounted, reactive, ref} from 'vue'
import axios from "axios";
import qs from "qs";
import {ElMessage, ElMessageBox} from "element-plus";

//获取用户登录信息（session）
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);

const tableData = ref([{id:"",code:"",unit:"",tel:""}]);

const QueryByUnit = ref("");
const QueryByName = ref("");

const deptAddInfo = ref({unit:'',name:'',code:'',tel:''});

const deptTab = ref('tab1');
// const tableData = ref([{name:"cc",code:"a",unit:"ab",tel:"111"}]);

const deptUpdateDialogVisible = ref(false)

const deptUpdateInfo = ref({unit:'',name:'',code:'',tel:''});



// const handleClose = () => {
//     ElMessageBox.confirm('你确定要关闭该弹窗吗?')
//         .then(() => {
//             done()
//         })
//         .catch(() => {
//             // catch error
//         })
// }

const handleClickOnEdit = (row) => {
    deptUpdateDialogVisible.value = true;
    // console.log("deptUpdateDialogVisible已设置true");
    getDeptByCode(row);
    // console.log("获取row:"+row);
}

const handleClickOnUpdate = () => {
    deptUpdateDialogVisible.value = false;
    // console.log("deptUpdateDialogVisible已设置false");
    updateDept();
}

const getDeptByCode = (row)=>{
    // console.log("调用获取row的方法")
    // console.log("获取row:"+row);
    let code = row.code;
    // console.log("获取code:"+code);
    axios.get("http://localhost:9002/v1/dept/getDeptByCode/"+code)
        .then((response)=>{
            if (response.data.code==2001){
                // ElMessage.success("得到数据成功");
                deptUpdateInfo.value = response.data.data;
            }else {
                ElMessage.error(response.data.msg);
            }
        })
}

//获取列表数据
const getAllDept = ()=>{
    axios.get("http://localhost:9002/v1/dept/listAll")
        .then((response)=>{
            if (response.data.code==2001){
                // ElMessage.success("获取列表完成");
                tableData.value = response.data.data;
                // console.log(tableData);
                // console.log(response.data.data);
            }else {
                ElMessage.error(response.data.msg);
            }
        })
};

const saveDept = ()=>{
    let data = qs.stringify(deptAddInfo.value)
    // console.log(data);
    axios.post("http://localhost:9002/v1/dept/add-new/",data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("提交成功");
                goToTab("tab1");
                location.reload();
            }
        })
}

//deptTab的值为tab1，默认跳转到“部门列表”
const goToTab = (tabName) => {
    deptAddInfo.value = {unit:'',name:'',code:'',tel:''};
    //
    deptTab.value = tabName;
};

//删除按钮操作
const deleteByCode = (row)=>{
    if (confirm("是否要删除该数据？")){
        // console.log(row);
        let code = row.code;
        // console.log(code);
        axios.get("http://localhost:9002/v1/dept/delete/"+code)
            .then((response)=>{
                if (response.data.code==2001){
                    ElMessage.success("删除成功");
                    location.reload();
                }else {
                    ElMessage.error(response.data.msg);
                }
            })
    }
}

const updateDept = ()=>{
    // console.log("正在执行更新部门操作");
    let data = qs.stringify(deptUpdateInfo.value)
    // console.log(data);
    axios.post("http://localhost:9002/v1/dept/update/",data)
        .then((response)=>{
            if (response.data.code==2001){
                ElMessage.success("更新成功");
                location.reload();
            }
        })
}

onMounted(()=>{
    getAllDept();
});

</script>

<style scoped>

</style>