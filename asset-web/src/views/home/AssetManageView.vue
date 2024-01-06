<template>
    <el-tabs type="border-card">
        <el-tab-pane label="资产列表">
            <div class="mt-4">
                资产编码：
                <el-input
                        style="width: 200px"
                        v-model="input"
                        placeholder=""
                        class="input-with-select"
                >
                    <template #append>
                        <el-button :icon="Search" />
                    </template>
                </el-input>
                资产名称：
                <el-input style="width: 180px" ></el-input>
                <el-button type="primary">查询</el-button>
                <el-button type="primary">刷新</el-button>

            </div>
            <el-table stripe border style="width: 100%">
                <el-table-column prop="" label="资产编号" width="180">{{tableArr.name}}</el-table-column>
                <el-table-column prop="" label="资产名称" width="180"></el-table-column>
                <el-table-column prop="" label="资产分类"></el-table-column>
                <el-table-column prop="" label="所属单位"></el-table-column>
                <el-table-column prop="" label="代管单位"></el-table-column>
                <el-table-column prop="" label="产权单位"></el-table-column>
                <el-table-column prop="" label="上报状态"></el-table-column>
                <el-table-column prop="" label="财务入账状态"></el-table-column>
                <el-table-column prop="" label="财务入账日期">
                        <template>
                            <el-form-item label="Activity time">
                                <el-col :span="11">
                                    <el-date-picker
                                            v-model="form.date1"
                                            type="date"
                                            placeholder="Pick a date"
                                            style="width: 100%"
                                    />
                                </el-col>
                            </el-form-item>
                        </template>
                </el-table-column>
                <el-table-column prop="" label="价值类型"></el-table-column>
                <el-table-column prop="" label="价值(元)"></el-table-column>
                <el-table-column prop="" label="使用状况"></el-table-column>
                <el-table-column prop="" label="当前状态"></el-table-column>
                <el-table-column prop="" label="操作">
                    <span>浏览</span>
                    <span>添加照片</span>
                    <span>修改</span>
                    <span>删除</span>
                </el-table-column>
            </el-table>
        </el-tab-pane>
        <el-tab-pane label="资产添加">
            <el-form label-width="100px" style="margin: 50px 150px">
                <el-form-item label="所属单位:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="资产分类:">
                    <el-input style="width: 300px;">
                        <template #append>
                            <el-button :icon="Search" />
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item label="本年财政拨款:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="财政拨款结转:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="其他资金:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="预计使用年限:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="使用人:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="使用管理部门:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="预算项目编号:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="合计凭证号:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="产权形式:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="使用权类型:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="权属证明:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>

                <el-form-item label="坐落位置:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="权属性质:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="取得方式:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="选择资产附件">
                    <el-input style="width: 300px;">
                        <template #append>
                            <el-button :icon="Search" @click="dialogFormVisible = true"/>
                        </template>
                    </el-input>
                    <el-dialog v-model="dialogFormVisible" title="选择资产附件">
                        <el-form :model="form">
                            <el-form-item label="资产附件:" :label-width="formLabelWidth">
                                <el-input v-model="form.name" autocomplete="off">
                                    <template #append>
                                        <el-button :icon="Search" />
                                    </template>
                                </el-input>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <span class="dialog-footer">
                                <el-button @click="dialogFormVisible = false">提交</el-button>
                            </span>
                        </template>
                    </el-dialog>
                </el-form-item>
                <el-form-item label="使用状况:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="Zone one" value="shanghai" />
                        <el-option label="Zone two" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="在建工程转入:">
                    <el-select v-model="form.region" placeholder="please select your zone">
                        <el-option label="是" value="shanghai" />
                        <el-option label="否" value="beijing" />
                    </el-select>
                </el-form-item>
                <el-form-item label="Activity time">
                    <el-col :span="11">
                        <el-date-picker
                                v-model="form.date1"
                                type="date"
                                placeholder="Pick a date"
                                style="width: 100%"
                        />
                    </el-col>
                </el-form-item>
                <el-form-item label="维修费用合计:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="地类(用途):">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="使用权面积:">
                    <el-input style="width: 300px;"></el-input>
                </el-form-item>
                <el-form-item label="权属年限:">
                    <el-input style="width: 300px;"></el-input>
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
    import { ref } from 'vue';
    import { reactive} from 'vue'
    import {Search} from '@element-plus/icons-vue';
    let dialogTableVisible = ref(false);
    let dialogFormVisible = ref(false);
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