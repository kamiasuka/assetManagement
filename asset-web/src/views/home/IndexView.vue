<template>
    <div>
        <h1>index</h1>
    </div>
</template>

<script setup>
    import {ref} from "vue";
    import axios from 'axios';
    import qs from 'qs';
    import {ElMessage} from 'element-plus'
    const user = ref({username:"",password:"",nickname:""});


</script>

<style scoped>

</style>