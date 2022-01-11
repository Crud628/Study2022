import Vue from 'vue'
import App from './App.vue'
import router from './router'
import wx from 'weixin-js-sdk'

import 'vant/lib/index.css';
import "@/style/index.css" // 引入公共css
import '@/assets/mui/css/mui.css'//mui的css样式    
import '@/assets/mui/css/main.css'//mui的css样式    



import mui from '@/assets/mui/js/mui.js'


Vue.prototype.mui = mui



import {
  font_size, // 响应式
  getUrlKey, // 获取地址上参数
  compressImg, // 图片压缩
  test_phone, // 校验手机号
  test_card_no, // 校验身份证号
} from "@/utils/common";


Vue.prototype.font_size = font_size;
Vue.prototype.getUrlKey = getUrlKey;
Vue.prototype.test_phone = test_phone;
Vue.prototype.test_card_no = test_card_no;
Vue.prototype.compressImg = compressImg;
Vue.prototype.wx = wx


import GLOBAL from "@/utils/global_variable";
Vue.prototype.GLOBAL = GLOBAL

import storage from "@/utils/storage"
Vue.prototype.storage = storage;


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')