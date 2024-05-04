<template>
    <div class="common-layout">
        <el-container >
            <el-header style="margin-top: 15px">
                <el-row :gutter="10" class="search">
                    <el-col :span="4" style="font-size: 14px;">
                        资产编码：
                        <el-input style="width: 180px" size="normal"></el-input>
                    </el-col>

                    <el-col :span="4" style="font-size: 14px;">
                        资产名称：
                        <el-input style="width: 180px" size="normal"></el-input>
                    </el-col>

                    <el-col :span="5" style="font-size: 14px;">
                        <div class="block">
                            <span class="demonstration">创建时间：</span>
                            <el-date-picker
                                    placeholder="附件创建时间"
                                    value-format="yyyy-MM-dd"
                            />
                        </div>
                    </el-col>

                    <el-col :span="4" style="font-size: 14px;">
                        使用状态：
                        <el-select class="m-2" placeholder="Select" size="normal" style="width: 130px">

                        </el-select>
                    </el-col>
                    <el-col :span="2">
                        <el-button type="primary" size="name">查询</el-button>
                    </el-col>
                </el-row>
            </el-header>
            <el-container>
            <el-aside width="180px">
                <div>
                    <el-button type="primary" @click="dialogFormVisible = true">编辑资产分类</el-button>
                    <el-dialog v-model="dialogFormVisible" title="编辑资产分类">
                        <el-form :model="form">
                            <el-form-item label="分类名称" :label-width="formLabelWidth">
                                <el-input v-model="form.name" autocomplete="off"/>
                            </el-form-item>

                            <el-form-item label="分类级别" :label-width="formLabelWidth">
                                <el-select v-model="form.region" placeholder="请选择分类级别">
                                    <el-option label="Zone No.1" value="shanghai"/>
                                    <el-option label="Zone No.2" value="beijing"/>
                                </el-select>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <span class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">取消</el-button>
                                <el-button type="primary" @click="dialogFormVisible = false">提交</el-button>
                            </span>
                        </template>
                    </el-dialog>
                </div>

                <div>
                    <el-row class="tac">
                        <el-col>
                            <h5 class="mb-2">资产分类</h5>
                            <el-menu
                                    default-active="2"
                                    class="el-menu-vertical"
                                    unique-opened="true"

                            >
<!--
                              <template v-for="category in categoryOne" :key="category.id">
                                <el-sub-menu :index="category.id.toString()" >
                                  <template #title>
                                    <el-icon>
                                      <document />
                                    </el-icon>
                                    <span>{{ category.name }}</span>
                                  </template>
                                  <el-sub-menu
                                      v-for="category2 in categoryTwo"
                                      :index="category2.id.toString()"
                                      :key="category2.id"
                                  >
                                    <template #title>{{ category2.name }}</template>
                                    &lt;!&ndash; 这里你可以继续根据需要添加其他层级的分类或菜单项 &ndash;&gt;
                                  </el-sub-menu>
                                </el-sub-menu>
                              </template>
-->
<!--                              <el-menu-item index="1" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>土地</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="2" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>房屋</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="3" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>构筑物</span>-->
<!--                              </el-menu-item>-->
                              <template #default="scope">
                              <el-menu-item index="4" @click="loadContents">
                                <el-icon><location /></el-icon>
                                <span>通用设备</span>
                              </el-menu-item>
                              </template>
<!--                              <el-menu-item index="5" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>专用设备</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="6" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>车辆</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="7" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>图书档案</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="8" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>动植物</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="9" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>无形资产</span>-->
<!--                              </el-menu-item>-->
<!--                              <el-menu-item index="10" @click="selectAsset">-->
<!--                                <el-icon><location /></el-icon>-->
<!--                                <span>在建工程</span>-->
<!--                              </el-menu-item>-->
                            </el-menu>
                        </el-col>
                    </el-row>
                </div>

            </el-aside>
                <el-main>
                    <el-table :data="tableData">
                        <el-table-column fixed prop="id" label="资产编码" width="100"/>
                        <el-table-column prop="name" label="资产名称" width="120"/>
                        <el-table-column prop="type" label="资产分类" width="120"/>
                        <el-table-column prop="dept" label="所属部门" width="120"/>
                        <el-table-column prop="unit" label="所属单位" width="120"/>
                        <el-table-column prop="life" label="使用年限" width="120"/>
                        <el-table-column prop="amount" label="资产价值" width="120"/>
                        <el-table-column prop="useStatus" label="使用状态" width="120"/>
                        <el-table-column prop="reviewStatus" label="审核状态" width="120"/>
                        <el-table-column prop="approvalDate" label="审核通过日期" width="120"/>
                        <el-table-column prop="note" label="备注" width="120"/>
                        <el-table-column fixed="right" label="操作" width="140">
                                <template #default>
                                    <div v-if="user.identity==='管理员'">
                                    <el-button type="success" size="small">编辑</el-button>
                                    <el-button type="danger" size="small">删除</el-button>
                                    </div>
                                </template>
                        </el-table-column>
                    </el-table>
<!--
                  <div style="position: fixed; bottom: 10px;">
                    <el-pagination
                        background layout="prev, pager, next"
                        :total="Total"
                        :page-size="pageSize"
                        @current-change="handleCurrentChange"
                    />
                  </div>
-->
            </el-main>

        </el-container>
        </el-container>
    </div>
</template>

<script setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    location,
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
import router from "@/router";
import {ElMessage} from "element-plus";

//获取用户登录信息（session）
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;

/*
const loadContents = () => {
    //展示数据
    axios.get('http://localhost:9002/v1/asset-category/listAllCategory')
        .then((response) => {
            if (response.data.code == 2001) {
                const filteredCategories1 = response.data.data.filter(item => item.level === 1);
                categoryOne.value = filteredCategories1;
                // const filteredCategories2 = response.data.data.filter(item => item.level === 2);
                // categoryTwo.value = filteredCategories2;
            }
        })
}
const categoryOne = ref([]);
const categoryTwo = ref([]);
const categoryThree = ref([]);
onMounted(() => {
    loadContents();
})

*/



const loadContents = () => {
  //展示数据
  const page = parseInt(pageNum.value);
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    axios.get('http://localhost:9002/v1/asset-category/getAsset/tysb/'+page )
        .then((response) => {
          if (response.data.code == 2001) {
            const responseData = response.data.data;
            tableData.value = responseData.list;
            Total.value = responseData.total;
          }
        })
  }else {
    ElMessage.error("登录超时，请重新登录！");
    router.push('/login')
  }


}
const tableData = ref([]);
const Total = ref();
const pageSize = 16; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据

const handleCurrentChange = (val) => {
      pageNum.value = val;
      loadContents();
};


onMounted(() => {
  loadContents();
});


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
</style>