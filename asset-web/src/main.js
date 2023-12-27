import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入element框架相关
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//定义全局路径
const BASE_URL = 'http://localhost:8080';
window.BASE_URL=BASE_URL;

const app = createApp(App)
//往VUE实例中添加全局变量(供页面标签使用)
app.config.globalProperties.BASE_URL = BASE_URL;
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus).use(store).use(router).mount('#app')


//解决页面缩放时的报错问题
const debounce = (fn, delay) => {
    let timer = null;
    return function () {
        let context = this;
        let args = arguments;
        clearTimeout(timer);
        timer = setTimeout(function () {
            fn.apply(context, args);
        }, delay);
    };
};
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
        callback = debounce(callback, 16);
        super(callback);
    }
};