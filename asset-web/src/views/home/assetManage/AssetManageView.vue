<template>
  <el-tabs type="border-card">
    <el-tab-pane label="资产录入列表">
<!--
      <div class="mt-4">
        资产编码：
        <el-input
            style="width: 200px"
            v-model="input"
            placeholder=""
            class="input-with-select"
        >
          <template #append>
            <el-button :icon="Search"/>
          </template>
        </el-input>
        资产名称：
        <el-input style="width: 180px"></el-input>
        <el-button type="primary">查询</el-button>
        <el-button type="primary">刷新</el-button>
      </div>
-->
        <div class="mt-4">
            <el-button type="primary" @click="reflush">刷新</el-button>
        </div>
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column prop="code" label="资产编号" width="150"></el-table-column>
        <el-table-column prop="name" label="资产名称" width="150"></el-table-column>
        <el-table-column prop="type" label="资产分类" width="100"></el-table-column>
        <el-table-column prop="maxType" label="所属最大分类" width="120"></el-table-column>
        <el-table-column prop="dept" label="所属部门" width="100"></el-table-column>
        <el-table-column prop="unit" label="所属单位" width="100"></el-table-column>
        <el-table-column prop="life" label="使用年限" width="100"></el-table-column>
        <el-table-column prop="amount" label="价值(元)" width="100"></el-table-column>
        <el-table-column prop="useStatus" label="使用状况" width="100"></el-table-column>
        <el-table-column prop="reviewStatus" label="审核状态" width="100"></el-table-column>
        <el-table-column prop="approvalDate" label="审核通过日期" width="200"></el-table-column>

        <el-table-column prop="" label="操作" width="200">
          <template #default="scope">
            <!--在外层使用两个不同的 div 元素来包裹两组按钮，并分别使用 flex 布局控制它们的排列方式-->
<!--            <div style="display: flex;">
              <el-button type="info" size="small" style="margin-right: 10px;">浏览</el-button>
              <div v-if="user.identity==='管理员'&& tableData.length > 0" style="display: flex; ">
                <el-button type="warning" size="small" @click="handleClickOnAttachment(scope.row)"
                           style="margin-right: 10px;">添加附件
                </el-button>
              </div>
            </div>-->
            <div v-if="user.identity==='管理员'&& tableData.length > 0 && scope.row.reviewStatus==='未通过'"
                 style="display: flex; ">
              <el-button type="success" size="small" @click="handleClickOnEdit(scope.row)" style="margin-right: 10px;">
                修改
              </el-button>
              <el-button type="danger" size="small" @click="deleteByCode(scope.row)">删除</el-button>
            </div>
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
    <div v-if="user.identity==='管理员'">
      <el-tab-pane label="资产添加">
        <el-form label-width="100px" style="margin: 50px 150px">
          <el-form-item label="资产名称:">
            <el-input style="width: 300px;" v-model="assetAddInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="资产分类:">
            <el-input style="width: 300px;" v-model="assetAddInfo.type"></el-input>
          </el-form-item>
          <el-form-item label="所属最大分类:">
            <el-input style="width: 300px;" v-model="assetAddInfo.maxType"></el-input>
          </el-form-item>
          <el-form-item label="所属部门:">
            <el-input style="width: 300px;" v-model="assetAddInfo.dept"></el-input>
          </el-form-item>
          <el-form-item label="所属单位:">
            <el-input style="width: 300px;" v-model="assetAddInfo.unit"></el-input>
          </el-form-item>
          <el-form-item label="使用年限:">
            <el-input style="width: 300px;" v-model="assetAddInfo.life"></el-input>
          </el-form-item>
          <el-form-item label="价值:">
            <el-input style="width: 300px;" v-model="assetAddInfo.amount"></el-input>
          </el-form-item>
          <el-form-item label="在用状态:">
            <el-input style="width: 300px;" v-model="assetAddInfo.useStatus"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button style="" type="primary" @click="saveAsset">提交</el-button>
            <el-button style="" type="">返回</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </div>

    <!--    资产录入修改按钮的弹窗-->
    <el-dialog
        v-model="assetUpdateDialogVisible"
        title="修改"
        width="500px"
    >
      <!-- :before-close="handleClose"-->
      <el-form label-width="100px" style="margin: 35px 15px">
        <el-form-item label="资产名称:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="资产分类:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.type"></el-input>
        </el-form-item>
        <el-form-item label="所属最大分类:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.maxType"></el-input>
        </el-form-item>
        <el-form-item label="所属部门:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.dept"></el-input>
        </el-form-item>
        <el-form-item label="所属单位:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.unit"></el-input>
        </el-form-item>
        <el-form-item label="使用年限:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.life"></el-input>
        </el-form-item>
        <el-form-item label="价值:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.amount"></el-input>
        </el-form-item>
        <el-form-item label="在用状态:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.useStatus"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="assetUpdateDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="updateAsset()">确认</el-button>
        </div>
      </template>
    </el-dialog>

    <!--    添加附件按钮的弹窗-->
    <el-dialog
        v-model="attachmentDialogVisible"
        title="附件"
        width="600px"
    >
      <!-- :before-close="handleClose"-->
      <el-form label-width="100px" style="margin: 35px 15px">
        <el-form-item label="资产编码:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.code"></el-input>
        </el-form-item>
        <el-form-item label="资产类型:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.type"></el-input>
        </el-form-item>
        <el-form-item label="所属资产名称:">
          <el-input style="width: 300px;" v-model="assetUpdateInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="备注:">
          <el-input
              style="width: 300px;"
              v-model="tip"
              :rows="2"
              type="textarea"
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

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button style="" @click="save" type="primary">保存</el-button>
          <el-button @click="attachmentDialogVisible = false">取消</el-button>
        </div>
      </template>
    </el-dialog>

  </el-tabs>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue'
import axios from "axios";
import qs from "qs";
import {ElMessage, ElMessageBox} from "element-plus";
import {Search} from '@element-plus/icons-vue';
import router from "@/router";

//获取用户登录信息（session）
const user = ref(localStorage.user ? JSON.parse(localStorage.user) : null);

//资产录入列表的数组
const tableData = ref([{
  code: '',
  name: '',
  type: '',
  maxType: '',
  dept: '',
  unit: '',
  life: '',
  amount: '',
  useStatus: '',
  reviewStatus: '',
  approvalDate: ''
}]);

const assetAddInfo = ref([{
  name: '',
  type: '',
  maxType: '',
  dept: '',
  unit: '',
  life: '',
  amount: '',
  useStatus: '',
}]);

const assetUpdateInfo = ref([{
  name: '',
  type: '',
  maxType: '',
  dept: '',
  unit: '',
  life: '',
  amount: '',
  useStatus: '',
}]);


const tip = ref('');

const Total = ref();
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
const assetUpdateDialogVisible = ref(false);
const attachmentDialogVisible = ref(false);
const handleCurrentChange = (val) => {
    pageNum.value = val;
    getAllAssets();
};
//修改按钮的点击事件
const handleClickOnEdit = (row) => {
  assetUpdateDialogVisible.value = true;
  assetUpdateInfo.value = row;
}

const reflush = () => {
    location.reload();
}

//添加附件的点击事件
const handleClickOnAttachment = (row) => {
  attachmentDialogVisible.value = true;
  attachment.value = row;
  assetUpdateInfo.value = row;
}

const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;

onMounted(() => {
  getAllAssets();
});

//发出更新数据的请求
const updateAsset = () => {
  assetUpdateDialogVisible.value = false;
  let data = qs.stringify(assetUpdateInfo.value);
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    axios.post("http://localhost:9002/v1/asset/add-update/", data)
        .then((response) => {
          if (response.data.code == 2001) {
            ElMessage.success("更新成功");
            location.reload();
          } else if (response.data.code == 1004) {
            ElMessage.error("登录超时，请重新登录！");
            router.push('/login')
          } else {
            ElMessage.error(response.data.msg);
          }
        })
  } else {
    ElMessage.error("系统未登录！");
    router.push('/login')
  }

}

const getAllAssets = () => {
  const page = parseInt(pageNum.value)
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    axios.get("http://localhost:9002/v1/assetReview/listAllAdd/" + page)
        .then((response) => {
          if (response.data.code == 2001) {
            ElMessage.success("成功获取所有录入资产!");
            console.log(response.data.data);
            const responseData = response.data.data;
            tableData.value = responseData.list;
            Total.value = responseData.total;
          } else if (response.data.code == 1004) {
            ElMessage.error("登录超时，请重新登录！");
            router.push('/login')
          } else {
            ElMessage.error(response.data.msg);
          }
        })
  } else {
    ElMessage.error("系统未登录！");
    router.push('/login')
  }


}

const saveAsset = () => {
  let data = qs.stringify(assetAddInfo.value)
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    axios.post("http://localhost:9002/v1/asset/add-new/", data)
        .then((response) => {
          if (response.data.code == 2001) {
            ElMessage.success("提交成功");
            location.reload();
          } else if (response.data.code == 1004) {
            ElMessage.error("登录超时，请重新登录！");
            router.push('/login')
          } else {
            ElMessage.error(response.data.msg);
          }
        })
  } else {
    ElMessage.error("系统未登录！");
    router.push('/login')
  }

}

const deleteByCode = (row) => {
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    if (confirm("是否要删除该数据？")) {
      // console.log(row);
      let code = row.code;
      // console.log(code);
      axios.get("http://localhost:9002/v1/asset/add-delete/" + code)
          .then((response) => {
            if (response.data.code == 2001) {
              ElMessage.success("删除成功");
              location.reload();
            } else if (response.data.code == 1004) {
              ElMessage.error("登录超时，请重新登录！");
              router.push('/login')
            } else {
              ElMessage.error(response.data.msg);
            }
          })
    }
  } else {
    ElMessage.error("系统未登录！");
    router.push('/login')
  }
}

const save = () => {
  //取出图片路径
  if (fileList.value.length > 0) {
    let imgUrl = fileList.value[0].response.data;
    //把上传成功的图片路径存到user对象里面
    attachment.value.url = imgUrl;
  }

  const data = {
    atName: assetUpdateInfo.value.name,
    code: assetUpdateInfo.value.code,
    type: assetUpdateInfo.value.type,
    tip: tip.value,
    assetId: '',
    url: attachmentInfo.url
  };

  axios.post('http://localhost:8080/v1/users/update', data)
      .then((response) => {
        if (response.data.code == 2001) {
          ElMessage.success("修改完成!");
          //更新LocalStorage里面的用户数据
          localStorage.user = JSON.stringify(user.value);
          //让整个页面刷新
          location.reload();
        }
      })
}
const attachment = ref([{
  assetCode: '',
  type: '',
  assetName: '',
  tip: '',
  url: '',
}]);

/*上传组件开始*/
const fileList = ref([])
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handleRemove = (uploadFile, uploadFiles) => {
  axios.post('http://localhost:8080/v1/remove?imgUrl=' + uploadFile.response.data)
      .then((response) => {
        if (response.data.code == 2001) {
          ElMessage.success("服务器文件删除完成!");
        }
      })
}
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true
}
/*上传组件结束*/

</script>

<style scoped>
.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-date-picker .block:last-child {
  border-right: none;
}

.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>