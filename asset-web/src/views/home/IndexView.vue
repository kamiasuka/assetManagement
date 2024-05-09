<template>
    <div>
        <div class="up-side">
            <el-card>
                <el-row :gutter="24">
                    <el-col :span="4" v-for="(item, index) in cardArr.slice(0,5)" :key="index">
                        <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                            <router-link to="#" style="color: #333;text-decoration: none">
                                <p class="card-title">{{ item.worth }}元</p>
                            </router-link>
                            <el-row :gutter="10">
                                <el-col :span="24" class="card-text">{{ item.type }}({{item.num}})</el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
                <el-row :gutter="24" style="margin-top: 8px;">
                    <el-col :span="4" v-for="(item, index) in cardArr.slice(5,10)" :key="index">
                        <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                            <router-link to="#" style="color: #333;text-decoration: none">
                                <p class="card-title">{{ item.worth }}元</p>
                            </router-link>
                            <el-row :gutter="10">
                                <el-col :span="24" class="card-text">{{ item.type }}({{item.num}})</el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
            </el-card>
        </div>

        <div class="down-side">
            <el-card>
                <el-row :gutter="12">
                    <el-col :span="16">
                        <el-card>
                            <div id="asset_bar_chart" style="width:100%;height:450px;"></div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">

                        <el-card>
                            <div id="asset_pie_chart" style="width:100%;height:450px;"></div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-card>
        </div>
    </div>


</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from 'axios';
import qs from 'qs';
import {ElMessage} from 'element-plus'
import * as echarts from "echarts";
import router from "@/router";

const user = ref({username: "", password: "", nickname: ""});
// const worthData = [
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
//     10,
// ];

const colorData = [
    '#588EBD',
    '#E25A5A',
    '#45B6B0',
    '#8775A9',
    '#4F5C65',

    '#939EB0',
    '#F29603',
    '#9BCC34',
    '#BC8F8C',
    '#FDB35C',
];

const typeData = ["土地", "房屋", "构筑物", "通用设备", "专用设备", "车辆",  "图书档案", "动植物", "无形资产", "在建工程"];
// const numData = [5, 10, 25, 10, 10, 20, 5, 20, 36, 10, 10, 20];
const worthData = ref([]);
const numData = ref([]);
const statistics = ref([]);//{worth:0.0,type:"",num:0}
const cardArr = ref([]);
const pieChartArr = ref([]);

const token = localStorage.getItem("token") ? localStorage.getItem("token") : null;

onMounted(() => {
  console.log("获取首页展示数据")
  if (token != null) {
    axios.defaults.headers.common['Authorization'] = token;
    axios.get("http://localhost:9002/v1/asset/getStatistics")
        .then((response) => {
          console.log("发送axios请求")
          if (response.data.code == 2001) {
            statistics.value = response.data.data;
            for (let i = 0; i < typeData.length; i++) {
              numData.value.push(statistics.value[i].num);
              worthData.value.push(statistics.value[i].worth);
            }
          }else if (response.data.code == 1004){
            ElMessage.error("登录超时，请重新登录！");
            router.push('/login')
          }else {
            ElMessage.error(response.data.msg);
          }
          showIndexView();
        })
  }else {
    ElMessage.error("系统未登录！");
    router.push('/login')
  }
});

// 获取资产最大级分类的统计信息
const getStatistics = ()=>{

}

//绘制首页页面
const showIndexView = ()=>{
    console.log("开始封装cardArr");
    for (let i = 0; i < typeData.length; i++) {
        cardArr.value.push({
            worth: worthData.value[i],
            color: colorData[i],
            type: typeData[i],
            num: numData.value[i]
        });
    }
    console.log(cardArr);

    console.log("开始封装pieChartArr");
    for (let i = 0; i < typeData.length; i++) {
        pieChartArr.value.push({
            value: worthData.value[i],
            itemStyle:  {color: colorData[i]},
            name: typeData[i]
        });
    }
    console.log(pieChartArr);
    //绘制柱状图
    showBarChart();
    //绘制饼图
    showPieChart();
}



/*柱状图展示部分--开始*/
const assetBarChartRef = ref(null);
//绘制柱状图
const showBarChart = () => {
    console.log("开始绘制柱状图)");
    assetBarChartRef.value = document.getElementById('asset_bar_chart');
    const barChart = echarts.init(assetBarChartRef.value);
    console.log(numData);
    let option = {
        title: {
            text: "资产数量统计"
        },
        tooltip: {},
        legend: {
            data: ["数量"]
        },
        xAxis: {
            data: typeData,
            axisLabel: {
                interval: 0
            }
        },
        yAxis: {},
        series: [
            {
                name: "数量",
                type: "bar",
                data: numData.value

            },
        ]
    };

    barChart.setOption(option);
    // setInterval(() => {
    //     const data0 = option.series[0].data;
    //     option.series.forEach(item => {
    //         // item.data.shift();
    //         // item.data.push(Math.round(Math.random() * 39 + 1));
    //     });
    //     barChart.setOption(option);
    // }, 2000);
}
/*柱状图展示部分--结束*/


/*饼状图展示部分--开始*/
const assetPieChartRef = ref(null);
//绘制饼状图
const showPieChart = () => {
    console.log("开始绘制饼图)");
    assetPieChartRef.value = document.getElementById('asset_pie_chart');
    const pieChart = echarts.init(assetPieChartRef.value);
    let option = {
        backgroundColor: "#fff", // 设置背景颜色为白色
        title: { // 标题配置
            text: "资产价值统计", // 标题文本
            left: "center", // 标题居中
            top: 20, // 上边距为20
            textStyle: {
                color: "#2c343c" // 标题文字颜色为红色
            }
        },
        tooltip: { // 提示框显示
            trigger: "item", // 当鼠标悬停在图形上时触发
            formatter: "{a} <br/>{b} : {c} ({d}%)" // 显示文字格式，{a}：系列名，{b}：数据项名，{c}：数值，{d}：百分比
        },
        visualMap: { // 视觉映射组件，设置成 false 表示不显示
            show: false,
            min: 80, // 最小值为80
            max: 600, // 最大值为600
            inRange: {
                colorLightness: [0, 1] // 显示颜色的亮度范围
            }
        },
        legend: { // 图例组件
            orient: "vertical", // 图例纵向排列
            x: "left", // 图例位于左侧
            textStyle: {
                color: "#2c343c" // 图例文字颜色为红色
            },
            data: typeData, // 图例的数据，与数据项名一一对应
        },
        series: [ // 系列列表
            {
                name: "访问来源", // 系列名称
                type: "pie", // 图表类型为饼图
                radius: ["0%", "60%"], // 饼图半径
                center: ["50%", "50%"], // 饼图中心点位置
                data: pieChartArr.value.sort(function (a, b) { // 按照数值从小到大排序
                    return a.value - b.value;
                }),
                roseType: "", // 南丁格尔玫瑰图，通过半径展现数据的大小，也可以改为 "area"，表示通过面积展现数据的大小
                label: {
                    // show:false,
                    color: "rgba(10, 10, 10, 0.9)" // 标签文字颜色为灰色，透明度为 0.3
                },
                labelLine: {
                    // show:false,
                    lineStyle: {
                        color: "rgba(10, 10, 10, 0.9)" // 标签线颜色为灰色，透明度为 0.3
                    },
                    smooth: 0.2, // 标签线的弯曲程度
                    length: 5, // 第一段标签线的长度
                    length2: 10 // 第二段标签线的长度
                },
                itemStyle: {
                    color: "#0f0", // 每个扇形部分的颜色
                    shadowBlur: 200, // 阴影的大小
                    shadowColor: "rgba(255,255,255, 0.9)" // 阴影的颜色
                },

                animationType: "scale", // 动画类型：缩放
                animationEasing: "elasticOut", // 缩放动画的效果
                animationDelay: function (idx) { // 动画延时
                    return Math.random() * 200;
                }
            }
        ]
    };
    pieChart.setOption(option);
    // setInterval(function() {
    //     let data0 = option.series[0].data.sort(function(a, b) {
    //         return a.value - b.value;
    //     });
    //     { value: 5, name: "土地" }
    //     let name = data0.shift();
    //     data0.push({
    //         value: Math.round(Math.random() * 400 + 1),
    //         name: name.name
    //     });
    //     option.series[0].data = data0.sort(function(a, b) {
    //         return a.value - b.value;
    //     });
    //     pieChart.setOption(option);
    // }, 2000);

}
/*饼状图展示部分--结束*/





</script>

<style scoped>
.custom-card {
    height: 120px; /* 调整为您希望的高度 */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.card-title {
    height: 40px; /* 调整为您希望的高度 */
    margin: 0;
    font-size: 30px; /* 调整为您希望的字体大小 */
    line-height: 20px; /* 调整为您希望的行高 */
    color: white;
}

.card-text {
    line-height: 20px; /* 调整为您希望的行高 */
    color: white;
    font-size: 20px;
}
</style>