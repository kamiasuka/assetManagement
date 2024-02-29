import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入element框架相关
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'



// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//直接给window.ADMIN_URL，标签里面是访问不到window.ADMIN_URL
//因为标签访问的是VUE实例，不会找window对象
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
//
const ADMIN_URL = 'http://localhost:9001';
window.ADMIN_URL = ADMIN_URL;

app.config.globalProperties.ADMIN_URL = ADMIN_URL;

//在VUE实例中添加全局变量（供页面标签中使用）
app.use(ElementPlus).use(store).use(router).mount('#app');
