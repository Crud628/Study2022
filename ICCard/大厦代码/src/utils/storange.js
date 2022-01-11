import promise from 'es6-promise'
promise.polyfill()
import axios from "axios";
import GLOBAL from "@/utils/global_variable.js";


// import store from "@/store";
// import { getToken } from "@/utils/auth";

// create an axios instance
// axios.defaults.headers["Content-Type"] = "application/json;charset=utf-8";
const service = axios.create({
  baseURL: GLOBAL.SERVER_ADDRESS, // url = base url + request url 修改当前访问地址
  // baseURL: "https://eroad.mynatapp.cc/v1/e-wxapi", // url = base url + request url 修改当前访问地址
  timeout: 50000000 // request timeout
  // withCredentials: true, // send cookies when cross-domain requests
  // timeout: process.env.NODE_ENV === "development" ? 0 : 10000
});

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    // if (store.getters.token) {
    //   // let each request carry token
    //   // ['X-Token'] is a custom headers key
    //   // please modify it according to the actual situation
    //   config.headers["X-Token"] = getToken();
    // }
    return config;
  },
  error => {
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    return response.data;
  },
  error => {
    alert("异常" + JSON.stringify(error));
    console.log(error);
    return error;
  }
);

export default service;