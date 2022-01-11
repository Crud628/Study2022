<template>
  <div class="staffinfo">
    <Header :info="info" />

    <div class="mui-content">
      <div class="lineH5"><b></b>员工身份认证</div>
      <form class="mui-input-group">
        <div class="mui-input-row">
          <label>员工姓名</label>
          <input
            v-model="form.employeeName"
            type="text"
            placeholder="请输入您的姓名"
            id="name"
          />
        </div>
        <div class="mui-input-row">
          <label>手机号后四位</label>
          <input
            v-model="form.subCard"
            type="text"
            placeholder="请输入您的手机号号码后四位"
            maxlength="18"
            id="card"
          />
        </div>
      </form>

      <div class="inforBtn">
        <button @click="submit" style="width: 100%" type="button">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header"; // secondary package based on el-pagination

import {
  authentication, // 员工身份信息验证
} from "@/api/index";

export default {
  components: { Header },
  name: "staffinfo",
  data() {
    return {
      info: {
        title: "员工身份认证",
        flag: true,
      },
      form: {
        employeeName: null, // 员工姓名
        subCard: null, // 身份证后四位
        weixinOpenid: window.localStorage.getItem("openid"), // 身份证后四位
      },
    };
  },
  created() {},
  methods: {
    // 校验员工身份信息
    submit() {
      let that = this;

      if (
        this.form.employeeName == "" ||
        this.form.employeeName == null ||
        this.form.employeeName == undefined
      ) {
        mui.toast("请输入姓名");
        return false;
      }

      if (
        this.form.subCard == "" ||
        this.form.subCard == null ||
        this.form.subCard == undefined
      ) {
        mui.toast("请输入后四位身份证号");
        return false;
      }

      if (this.form.subCard.length != 4) {
        mui.toast("请输入正确的后四位身份证号");
        return false;
      }

      authentication(JSON.stringify(this.form)).then((res) => {
        if (res.errno == 0) {
          // window.localStorage.setItem('staffinfo', JSON.stringify(res.data))
          that.storage.setItem({
            name: "staffinfo",
            value: res.data,
            expires: 1000 * 60 * 60 * 8, //以毫秒为单位
          });

          this.$router.push({
            path: "/staindex",
          });
        } else {
          mui.toast(res.errmsg);
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.mui-input-group:after {
  height: 0;
}
</style>
>
