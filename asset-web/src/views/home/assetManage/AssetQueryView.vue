<template>
    <div class="common-layout">
        <el-container>
            <el-header style="margin-top: 15px">
                <el-row :gutter="10" class="search">

                    <el-col :span="4" style="font-size: 14px;">
                        资产编码：
                        <el-input
                            size="normal"
                            style="width: 150px"
                            v-model="searchlist.code"
                            placeholder="全部"
                            class="input-with-select">
                        </el-input>
                    </el-col>

                    <el-col :span="4" style="font-size: 14px;">
                        资产名称：
                        <el-input
                            size="normal"
                            style="width: 150px"
                            v-model="searchlist.name"
                            placeholder="全部"
                            class="input-with-select">
                        </el-input>
                    </el-col>
<!--
                    <el-col :span="5" style="font-size: 14px;">
                        <div class="block">
                            <span class="demonstration">创建时间：</span>
                            <el-date-picker
                                    placeholder="附件创建时间"
                                    value-format="yyyy-MM-dd"
                            />
                        </div>
                    </el-col>
-->
                    <el-col :span="4" style="font-size: 14px;">
                        使用状态：
                        <el-select class="m-2" v-model="searchlist.status" placeholder="Select" size="normal" style="width: 130px">
                            <el-option
                                v-for="item in status"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                        </el-select>
                    </el-col>

                    <el-col :span="4" style="font-size: 14px;">
                        部门：
                        <el-select class="m-3" v-model="searchlist.dept" placeholder="Select" size="normal" style="width: 130px">
                            <el-option
                                v-for="item in dept"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                        </el-select>
                    </el-col>
                    <el-col :span="2">
                        <el-button type="primary" size="name"  @click="search">查询</el-button>
                    </el-col>

                </el-row>
            </el-header>
            <el-container>
                <el-aside width="180px">
                    <div>
                        <h5 class="mb-2">资产分类</h5>
                            <el-menu  @select="handleRecipe" default-active="1" active-text-color="orange">
                                <!-- 使用 v-for 和 :key 创建一个可循环的菜单项，每个菜单项绑定点击事件 -->
                                <el-menu-item
                                    v-for="item in state.categoryArr"
                                    :index="item.index"
                                    :key="item.index"
                                    @click="loadContents(item.name)"
                                >
                                    {{ item.name }}
                                </el-menu-item>
                            </el-menu>
                    </div>

                </el-aside>
                <el-main>
                    <el-table :data="tableData" #default="scope">
                        <el-table-column fixed prop="code" label="资产编码" width="150"/>
                        <el-table-column prop="name" label="资产名称" width="120"/>
                        <el-table-column prop="type" label="资产分类" width="120"/>
                        <el-table-column prop="dept" label="所属部门" width="120"/>
                        <el-table-column prop="unit" label="所属单位" width="120"/>
                        <el-table-column prop="life" label="使用年限" width="100"/>
                        <el-table-column prop="amount" label="资产价值" width="100"/>
                        <el-table-column prop="useStatus" label="使用状态" width="120"/>
<!--                        <el-table-column prop="reviewStatus" label="审核状态" width="120"/>-->
                        <el-table-column prop="approvalDate" label="审核通过日期" width="200"/>
                        <el-table-column :span="8" prop="address" label="操作" width="300" class="black">
                            <template #default="scope">
                                <a :href="'http://localhost:9004/' + scope.row.url" target="_blank">
                                    <el-button style="width: 70px; height: 30px;margin-left: 20px" type="success">查看附件</el-button>
                                </a>
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
const user = ref(localStorage.user ? JSON.parse(localStorage.user) : null);
const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;

const state = reactive({  categoryArr: [] })
// const state = ref({ categoryArr:[] })

const listAsset = ref([{
    category:'',
    page:''
}])

function loadContents(item) {
    const page = parseInt(pageNum.value);
    console.log("item",item);
    listAsset.value.category = item;
    listAsset.value.page = page;
    console.log("listAsset",listAsset);
    //展示数据
    // let data = qs.stringify(listAsset.value);
    // console.log(data);
    if (token != null) {
        axios.defaults.headers.common['Authorization'] = token;
        axios.get('http://localhost:9002/v1/asset/getAsset/'+item+'/'+page)
            .then((response) => {
                if (response.data.code == 2001) {
                    const responseData = response.data.data;
                    tableData.value = responseData.list;
                    Total.value = responseData.total;
                }
            })
    } else {
        ElMessage.error("登录超时，请重新登录！");
        router.push('/login')
    }
}

const tableData = ref([{}]);
const Total = ref();
const pageSize = 12; // 每页显示的条目数
const pageNum = ref(1); // 当前页码
// 计算当前页的数据

const handleCurrentChange = (val) => {
    pageNum.value = val;
    loadContents();
};

onMounted(() => {
    //loadContents();
    axios.defaults.headers.common['Authorization'] = token;
    axios.get('http://localhost:9002/v1/assetCategory/listMaxCategory/')
        .then((response) => {
        if (response.data.code == 2001) {
            state.categoryArr = response.data.data
            console.log("数据:",state.categoryArr);
        }
    })
});

const status = [
    {value: '在用', label: '在用',},
    {value: '闲置', label: '闲置',},
    {value: '在修', label: '在修',},
    {value: '报废', label: '报废',},
]

const dept = [
    {value: '在用', label: '在用',},
    {value: '闲置', label: '闲置',},
    {value: '在修', label: '在修',},
    {value: '报废', label: '报废',},
]

const searchlist = ref({
    code: '',
    name: '',
    status:'',
    dept: '',
});
const search = () => {
    //let updatedTime = formattedDate.value !== '1970-01-01' ? formattedDate.value : '';
    const page = parseInt(pageNum.value);
    let data = qs.stringify(searchlist.value);
    axios.post('http://localhost:9002/v1/asset/'+page+'/search/',data)
        .then(response => {
            console.log('来自后端的响应:', response.data.data);
            if (response.data.code==2001){
                tableData.value = response.data.data.list;
            }
            ElMessage.success("查询成功");
        })
        .catch(error => {
            console.error('发送数据到后端时出错:', error);
        });
};


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