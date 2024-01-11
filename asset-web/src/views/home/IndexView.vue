<template>
    <div>
        <div class="up-side">
            <el-card>
                <el-row :gutter="12">
                    <el-col :span="4" v-for="(item, index) in recipeArr" :key="index">
                        <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                            <router-link to="#" style="color: #333;text-decoration: none">
                                <p class="card-title">9999999</p>
                            </router-link>
                            <el-row :gutter="10">
                                <el-col :span="24" class="card-text">土地</el-col>
                                <el-col :span="24" class="card-text">666</el-col>
                            </el-row>
                        </el-card>
                    </el-col>
                </el-row>
                <el-row :gutter="12" style="margin-top: 8px;">
                    <el-col :span="4" v-for="(item, index) in recipe" :key="index">
                        <el-card class="custom-card" :style="{ backgroundColor: item.color }">
                            <router-link to="#" style="color: #333;text-decoration: none">
                                <p class="card-title">9999999</p>
                            </router-link>
                            <el-row :gutter="10">
                                <el-col :span="24" class="card-text">土地</el-col>
                                <el-col :span="24" class="card-text">666</el-col>
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
const recipeArr = ref([
    {title: 'Recipe 1', description: 'Description 1', color: '#588EBD'},
    {title: 'Recipe 2', description: 'Description 2', color: '#E25A5A'},
    {title: 'Recipe 3', description: 'Description 3', color: '#45B6B0'},
    {title: 'Recipe 4', description: 'Description 4', color: '#8775A9'},
    {title: 'Recipe 5', description: 'Description 5', color: '#4F5C65'},
    {title: 'Recipe 6', description: 'Description 6', color: '#13AAE3'},
])

const recipe = ref([
    {title: 'Recipe 1', description: 'Description 1', color: '#939EB0'},
    {title: 'Recipe 2', description: 'Description 2', color: '#F29603'},
    {title: 'Recipe 3', description: 'Description 3', color: '#9BCC34'},
    {title: 'Recipe 4', description: 'Description 4', color: '#BC8F8C'},
    {title: 'Recipe 5', description: 'Description 5', color: '#FDB35C'},
    {title: 'Recipe 6', description: 'Description 6', color: '#49C0BE'},
])

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
            data: ["土地", "房屋", "构筑物", "通用设备", "专用设备", "车辆", "文物和陈列品", "家具用具", "图书档案", "动植物", "无形资产", "在建工程"],
            axisLabel: {
                interval: 0
            }
        },
        yAxis: {},
        series: [
            {
                name: "数量",
                type: "bar",
                data: [5, 20, 36, 10, 10, 20, 5, 20, 36, 10, 10, 20]
            },
        ]
    };

    setInterval(() => {
        const data0 = option.series[0].data;
        option.series.forEach(item => {
            // item.data.shift();
            // item.data.push(Math.round(Math.random() * 39 + 1));
        });
        barChart.setOption(option);
    }, 2000);
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
            text: "Customized Pie", // 标题文本
            left: "center", // 标题居中
            top: 20, // 上边距为20
            textStyle: {
                color: "#f00" // 标题文字颜色为红色
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
                color: "#f00" // 图例文字颜色为红色
            },
            data: ["土地", "房屋", "构筑物", "通用设备", "专用设备", "车辆", "文物和陈列品", "家具用具", "图书档案", "动植物", "无形资产", "在建工程"], // 图例的数据，与数据项名一一对应
        },
        series: [ // 系列列表
            {
                name: "访问来源", // 系列名称
                type: "pie", // 图表类型为饼图
                radius: ["55%"], // 饼图半径
                center: ["50%", "50%"], // 饼图中心点位置
                data: [ // 数据项列表
                    { value: 335, name: "直接访问" }, // 数据项，value：数值，name：数据项名称
                    { value: 310, name: "邮件营销" },
                    { value: 274, name: "联盟广告" },
                    { value: 235, name: "视频广告" },
                    { value: 400, name: "搜索引擎" }
                ].sort(function(a, b) { // 按照数值从小到大排序
                    return a.value - b.value;
                }),
                roseType: "radius", // 南丁格尔玫瑰图，通过半径展现数据的大小，也可以改为 "area"，表示通过面积展现数据的大小
                label: {
                    color: "rgba(30, 30, 30, 0.3)" // 标签文字颜色为灰色，透明度为 0.3
                },
                labelLine: {
                    lineStyle: {
                        color: "rgba(30, 30, 30, 0.3)" // 标签线颜色为灰色，透明度为 0.3
                    },
                    smooth: 0.2, // 标签线的弯曲程度
                    length: 10, // 第一段标签线的长度
                    length2: 20 // 第二段标签线的长度
                },
                itemStyle: {
                    color: "#c23531", // 每个扇形部分的颜色
                    shadowBlur: 200, // 阴影的大小
                    shadowColor: "rgba(0, 0, 0, 0.5)" // 阴影的颜色
                },

                animationType: "scale", // 动画类型：缩放
                animationEasing: "elasticOut", // 缩放动画的效果
                animationDelay: function(idx) { // 动画延时
                    return Math.random() * 200;
                }
            }
        ]
    };
    setInterval(function() {
        var data0 = option.series[0].data.sort(function(a, b) {
            return a.value - b.value;
        });
        // { value: 335, name: "直接访问" }
        let name = data0.shift();
        data0.push({
            value: Math.round(Math.random() * 400 + 1),
            name: name.name
        });
        option.series[0].data = data0.sort(function(a, b) {
            return a.value - b.value;
        });
        pieChart.setOption(option);
    }, 2000);

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
    font-size: 16px; /* 调整为您希望的字体大小 */
    line-height: 20px; /* 调整为您希望的行高 */
}

.card-text {
    line-height: 20px; /* 调整为您希望的行高 */
}
</style>