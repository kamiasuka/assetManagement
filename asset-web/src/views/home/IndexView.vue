<template>
    <div>
        <div class="up-side">
            <el-card>
                <el-row :gutter="12">
                    <el-col :span="4" v-for="(item, index) in recipeArr.slice(0,6)" :key="index">
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
                <el-row :gutter="12" style="margin-top: 8px;">
                    <el-col :span="4" v-for="(item, index) in recipeArr.slice(6,12)" :key="index">
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

const user = ref({username: "", password: "", nickname: ""});
const worthData = [
    'Recipe 1',
    'Recipe 2',
    'Recipe 3',
    'Recipe 4',
    'Recipe 5',
    'Recipe 6',
    'Recipe 7',
    'Recipe 8',
    'Recipe 9',
    'Recipe 1',
    'Recipe 1',
    'Recipe 1',
    'Recipe 1',
];

const colorData = [
    '#588EBD',
    '#E25A5A',
    '#45B6B0',
    '#8775A9',
    '#4F5C65',
    '#13AAE3',
    '#939EB0',
    '#F29603',
    '#9BCC34',
    '#BC8F8C',
    '#FDB35C',
    '#49C0BE'
];

const typeData = ["土地", "房屋", "构筑物", "通用设备", "专用设备", "车辆", "文物和陈列品", "家具用具", "图书档案", "动植物", "无形资产", "在建工程"];
const numData = [5, 20, 25, 10, 10, 20, 5, 20, 36, 10, 10, 20];

const recipeArr = ref([]);
for (let i = 0; i < typeData.length; i++) {
    recipeArr.value.push({
        worth: worthData[i],
        color: colorData[i],
        type: typeData[i],
        num: numData[i],
    });
}
// const recipeArr = ref([
//     {title: 'Recipe 1',  color: '#588EBD',type: '土地',num: 1},
//     {title: 'Recipe 2',  color: '#E25A5A',type: '土地',num: 1},
//     {title: 'Recipe 3',  color: '#45B6B0',type: '土地',num: 1},
//     {title: 'Recipe 4',  color: '#8775A9',type: '土地',num: 1},
//     {title: 'Recipe 5',  color: '#4F5C65',type: '土地',num: 1},
//     {title: 'Recipe 6',  color: '#13AAE3',type: '土地',num: 1},
//     {title: 'Recipe 7',  color: '#939EB0',type: '房屋',num: 0},
//     {title: 'Recipe 8',  color: '#F29603',type: '房屋',num: 0},
//     {title: 'Recipe 9',  color: '#9BCC34',type: '房屋',num: 0},
//     {title: 'Recipe 1',  color: '#BC8F8C',type: '房屋',num: 0},
//     {title: 'Recipe 1',  color: '#FDB35C',type: '房屋',num: 0},
//     {title: 'Recipe 1',  color: '#49C0BE',type: '房屋',num: 0},
// ])


/*柱状图展示部分--开始*/
const assetBarChartRef = ref(null);
//绘制柱状图
const showBarChart = () => {
    assetBarChartRef.value = document.getElementById('asset_bar_chart');
    const barChart = echarts.init(assetBarChartRef.value);
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
                data: numData

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
                data: [ // 数据项列表
                    {value: 5, itemStyle: {color: '#588EBD'}, name: "土地"}, // 数据项，value：数值，name：数据项名称
                    {value: 20, itemStyle: {color: '#E25A5A'}, name: "房屋"},
                    {value: 36, itemStyle: {color: '#45B6B0'}, name: "构筑物"},
                    {value: 10, itemStyle: {color: '#8775A9'}, name: "通用设备"},
                    {value: 10, itemStyle: {color: '#4F5C65'}, name: "专用设备"},
                    {value: 20, itemStyle: {color: '#13AAE3'}, name: "车辆"},
                    {value: 5, itemStyle: {color: '#939EB0'}, name: "文物和陈列品"},
                    {value: 20, itemStyle: {color: '#F29603'}, name: "家具用具"},
                    {value: 36, itemStyle: {color: '#9BCC34'}, name: "图书档案"},
                    {value: 10, itemStyle: {color: '#BC8F8C'}, name: "动植物"},
                    {value: 10, itemStyle: {color: '#FDB35C'}, name: "无形资产"},
                    {value: 20, itemStyle: {color: '#49C0BE'}, name: "在建工程"}
                ].sort(function (a, b) { // 按照数值从小到大排序
                    return a.value - b.value;
                }),
                roseType: "", // 南丁格尔玫瑰图，通过半径展现数据的大小，也可以改为 "area"，表示通过面积展现数据的大小
                label: {
                    color: "rgba(10, 10, 10, 0.9)" // 标签文字颜色为灰色，透明度为 0.3
                },
                labelLine: {
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

onMounted(() => {
    showBarChart();
    showPieChart();
});
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