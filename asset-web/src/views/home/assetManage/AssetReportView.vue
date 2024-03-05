<template>
    <div v-if="user.identity==='管理员'">管理员</div>
    <div v-else-if="user.identity==='审核员'">审核员</div>
</template>

<script setup>
    import {ref} from "vue";
    //获取用户登录信息（session）
    const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
</script>


<style>

</style>
