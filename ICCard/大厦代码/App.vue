<template>
  <div id="app">
    <keep-alive>
      <router-view :key="$route.fullPath" />
    </keep-alive>
  </div>
</template>
<script>
import { wxLogin } from "@/api/index";
export default {
  data() {
    return {
      path: "",
    };
  },
  watch: {
    // 监听路由跳转。
    $route(newRoute, oldRoute) {
      this.path = newRoute.path;
      if (!window.localStorage.getItem("openid")) {
        // 获取openId
        if (this.getQueryVariable("code")) {
          let code = this.getQueryVariable("code");
          this.getCode(code);
        } else {
          this.getCode();
        }
      }
    },
  },
  created() {
    // window.localStorage.removeItem("openid"); // wcs openid
    // window.localStorage.setItem('openid', 'oQCy86k3qIBSVBIjEP0RtrC-Wg7k') // Developer openid
    // window.localStorage.setItem('openid', 'oQCy86oGAvYYy3A2HYgkB2tg39jY') // 周杰伦 openid
    // window.localStorage.setItem("openid", "oXFfz5lcTDOFS-fQ8pkeXq5E5nRU"); // 我
    // window.localStorage.setItem('openid', 'oXFfz5tG6xJaHii_DTxIEkZJ3aTo') // 王涵

    this.font_size(); //rem响应式
  },
  methods: {
    getCode(code) {
      // const code = this.$route.query.code
      if (code) {
        // 获取openid
        wxLogin(code).then((res) => {
          if (res.errno == 0) {
            // window.localStorage.removeItem('openid')
            window.localStorage.setItem("openid", res.data.openid);
            if (this.getQueryVariable("code")) {
              window.location.href =
                this.GLOBAL.HTTP_ADDRESS + "/#" + this.path;
            }
          }
        });
      } else {
        const local = window.location.href;
        window.location.href = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${
          this.GLOBAL.APPID
        }&redirect_uri=${encodeURIComponent(
          local
        )}&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect`;
      }
    },
    getQueryVariable(variable) {
      var query = window.location.search.substring(1);
      var vars = query.split("&");
      for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
          return pair[1];
        }
      }
      return false;
    },
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
