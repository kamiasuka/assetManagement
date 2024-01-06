import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入element框架相关
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//直接给window.BASE_URL赋值地址，标签里面是访问不到window.BASE_URL的
//因为标签访问的是VUE实例，不会找window对象
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

const BASE_URL = 'http://localhost:8080';
window.BASE_URL = BASE_URL;

app.config.globalProperties.BASE_URL = BASE_URL;

//在VUE实例中添加全局变量（供页面标签中使用）
app.use(ElementPlus).use(store).use(router).mount('#app');
