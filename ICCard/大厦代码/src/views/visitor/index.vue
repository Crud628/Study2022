<template>
  <div class="visitor">
    <Header :info="info" />
    <div class="mui-content">
      <div class="lineH5"><b></b>被访信息</div>

      <div class="mui-input-group">
        <div class="mui-input-row">
          <label>被访人电话</label>
          <input v-model="phone" type="number" placeholder="请输入被访人电话" />
        </div>

        <div class="mui-input-row">
          <label>被访人</label>
          <input
            v-model="intervieweeName"
            type="text"
            class="mui-input-clear"
            value
            disabled
            id="name"
          />
          <span class="mui-icon mui-icon-clear mui-hidden"></span>
        </div>

        <div class="mui-input-row">
          <label>到访类型</label>

          <label class="visRad mui-radio">
            <input
              @click="form.dateType = 0"
              name="radio1"
              type="radio"
              checked
            />单日到访
          </label>
          <label class="visRad mui-radio">
            <input
              @click="form.dateType = 1"
              name="radio1"
              type="radio"
            />短期到访
          </label>
        </div>

        <div v-show="form.dateType == 0" class="mui-input-row">
          <label>到访日期</label>
          <button @click="visit('date', 1)" class="btn mui-btn mui-btn-block">
            <div v-show="!form.visitDayTime">选择到访日期</div>
            <div v-show="form.visitDayTime">{{ form.visitDayTime }}</div>
            <i class="mui-icon mui-icon-arrowdown"></i>
          </button>
        </div>

        <div v-show="form.dateType == 0" class="mui-input-row visTime">
          <label>到访时间</label>
          <button @click="visit('time', 2)" class="btn mui-btn mui-btn-block">
            <div v-show="!form.visitStartTime">开始时间</div>
            <div v-show="form.visitStartTime">{{ form.visitStartTime }}</div>

            <i class="mui-icon mui-icon-arrowdown"></i>
          </button>
          <span>至</span>
          <button @click="visit('time', 3)" class="btn mui-btn mui-btn-block">
            <div v-show="!form.visitEndTime">结束时间</div>
            <div v-show="form.visitEndTime">{{ form.visitEndTime }}</div>

            <i class="mui-icon mui-icon-arrowdown"></i>
          </button>
        </div>

        <div v-show="form.dateType == 1" class="mui-input-row">
          <label>开始日期</label>
          <button
            @click="visit('datetime', 4)"
            class="btn mui-btn mui-btn-block"
          >
            <div v-show="!form.pluralityVisitStartTime">请选择到访开始日期</div>
            <div v-show="form.pluralityVisitStartTime">
              {{ form.pluralityVisitStartTime }}
            </div>

            <i class="mui-icon mui-icon-arrowdown"></i>
          </button>
        </div>

        <div v-show="form.dateType == 1" class="mui-input-row">
          <label>结束日期</label>

          <button
            @click="visit('datetime', 5)"
            class="btn mui-btn mui-btn-block"
          >
            <div v-show="!form.pluralityVisitEndTime">请选择到访结束日期</div>
            <div v-show="form.pluralityVisitEndTime">
              {{ form.pluralityVisitEndTime }}
            </div>

            <i class="mui-icon mui-icon-arrowdown"></i>
          </button>
        </div>
      </div>

      <div class="lineH5"><b></b>访客信息</div>

      <div class="mui-input-group">
        <div class="mui-input-row">
          <label>访客姓名</label>
          <input
            v-model="form.visitor.visitorsName"
            type="text"
            placeholder="请输入您的姓名"
            id="visitorsName"
          />
        </div>
        <div class="mui-input-row">
          <label>访客手机号</label>
          <input
            v-model="form.visitor.visitorsPhone"
            type="text"
            placeholder="请输入您的手机号"
            id="visitorsCard"
          />
        </div>

        <!--  <div class="mui-input-row">
          <label>访客身份证</label>
          <input
            v-model="form.visitor.visitorsCard"
            type="text"
            placeholder="请输入您的身份证号码"
            id="visitorsCard"
          />
        </div>
        -->
        <!-- <div class="mui-input-row">
          <label>人脸识别</label>
          <span @click="jumpLink" class="visHref">立即添加</span>
        </div>-->

        <!-- <div class="mui-input-row" id="face">
          <label>人脸信息</label>
          <div>
            <img v-if="form.visitor.visitorsPic" :src="form.visitor.visitorsPic" alt />
            <img v-if="!form.visitor.visitorsPic" src="@/assets/image/member.png" />
            <a @click="jumpLink" href="javascript:void(0)">重新采集</a>
          </div>
        </div>-->

        <div class="mui-input-row visArea">
          <label>来访事由</label>
          <textarea
            v-model="form.reasons"
            maxlength="50"
            placeholder="输入您的来访事情由"
          ></textarea>
        </div>
      </div>

      <div class="inforBtn">
        <button @click="submit" style="width: 100%" type="button">提交</button>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header"; // secondary package based on el-pagination
import "@/assets/mui/js/mui.picker.min.js"; //mui的css样式
import "@/assets/mui/css/mui.picker.min.css"; //mui的css样式

import {
  wxGetEmployeeByPhone, // 根据被访人手机号获取名字
  wxInsertVisitor, // 根据被访人手机号获取名字
  wxSubmitVisitRecord, // 提交访客信息
  wxGetVisitRule, // 获取当前部门的访客规则
} from "@/api/index";

export default {
  components: { Header },
  name: "visitor",
  data() {
    return {
      info: {
        title: "访客申请",
        flag: true,
      },
      intervieweeName: "", // 被访人姓名
      visRad: true, // 单日到访true ，时间段到访 false
      picker: null, // 日期实例
      phone: null, // 被坊人手机号码
      form: {
        visitDepartmentId: null, // 被访部门id
        reasons: null, // 来访原因
        intervieweeId: null, // 被访人id  dateType是0时必填
        visitorId: null, //当前访客的id
        dateType: 0, //0：单天 1：短期
        visitStartTime: "", // 来访开始时间  dateType是0时必填
        visitEndTime: "", //来访结束时间   dateType是0时必填
        visitDayTime: "", //来访日期 dateType是0时必填
        pluralityVisitStartTime: "", //预约开始时间
        pluralityVisitEndTime: "", // 预约结束时间
        visitor: {
          id: null, // 当前访客的id
          visitorsName: null, // 当前访客的姓名
          // visitorsPic: window.localStorage.getItem('imgUrl') || '', // 当前访客的人脸地址
          // visitorsCard: null, // 当前访客的身份证号
          // visitorsPicKey: null, //当前访客的人脸地址key
          visitorsPhone: null, // 访客手机号
        },
      },
      startTime: null,
      endTime: null,
      weixinOpenid: window.localStorage.getItem("openid"),
    };
  },
  watch: {
    phone: function (val) {
      let that = this;
      if (that.test_phone(val) && val.length == 11) {
        wxGetEmployeeByPhone(val).then((res) => {
          that.intervieweeName = res.data.employeeName;
          that.form.visitDepartmentId = res.data.departmentId;
          that.form.intervieweeId = res.data.id;

          // 获取当前部门的访客规则
          // let params = new FormData()
          // params.append('departmentId', that.form.visitDepartmentId)
          // wxGetVisitRule(params).then((res1) => {
          //   that.startTime = res1.data.startTime
          //   that.endTime = res1.data.endTime
          // })
        });
      } else {
        that.intervieweeName = "";
        that.startTime = "";
        that.endTime = "";
        that.visitStartTime = ""; // 来访开始时间  dateType是0时必填
        that.visitEndTime = ""; //来访结束时间   dateType是0时必填
        that.visitDayTime = ""; //来访日期 dateType是0时必填
        that.pluralityVisitStartTime = ""; //预约开始时间
        that.pluralityVisitEndTime = ""; // 预约结束时间
      }
    },
    weixinOpenid: {
      handler: function (newVal, oldVal) {
        if (newVal) {
          let params = new FormData();
          params.append("weixinOpenid", window.localStorage.getItem("openid"));
          let that = this;
          // 进入页面就获取访客id
          wxInsertVisitor(params).then((res) => {
            that.form.visitorId = res.data;
            that.form.visitor.id = res.data;
          });
        }
      },
      deep: true,
      immediate: true,
    },
    // 'form.visitor.visitorsPic': {
    //   handler(val, old) {
    //     if (val) {
    //       let reg = new RegExp(
    //         'https://eroad.mynatapp.cc/v1/e-wxapi/storage/fetch/',
    //         'g'
    //       )
    //       let visitorsPicKey = val.replace(reg, '')
    //       this.$set(this.form.visitor, 'visitorsPicKey', visitorsPicKey)
    //     }
    //   },

    //   immediate: true,
    // },
    // $route(to, from) {
    //   if (to.name == 'Visitor') {
    //     let imgUrl = window.localStorage.getItem('imgUrl') || ''
    //     this.$set(this.form.visitor, 'visitorsPic', imgUrl)
    //   }
    // },
  },
  created() {},

  mounted() {},
  methods: {
    // 跳转到人脸识别页面
    jumpLink() {
      this.$router.push({
        path: "/face",
        query: { randomID: "id" + Math.random() },
      });
    },

    // 获取当前日期
    getToday() {
      let time = new Date();
      // time.setTime(time.getTime() + 24 * 60 * 60 * 1000)

      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();

      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }
      let today = year + "-" + month + "-" + day;
      return today;
      // return new Date(time.getFullYear(), time.getMonth() + 1, time.getDate())
    },

    getTodayTime() {
      let time = new Date();
      // time.setTime(time.getTime() + 24 * 60 * 60 * 1000)

      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let hour = time.getHours();
      let minutes = time.getMinutes();
      let seconds = time.getSeconds();

      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }

      if (hour < 10) {
        hour = "0" + hour;
      }
      if (minutes < 10) {
        minutes = "0" + minutes;
      }
      if (day < 10) {
        seconds = "0" + seconds;
      }

      let todayTime =
        year +
        "-" +
        month +
        "-" +
        day +
        " " +
        hour +
        ":" +
        minutes +
        ":" +
        seconds;
      return todayTime;
    },

    // 获取当前时分秒函数
    getTime() {
      let time = new Date();
      // time.setTime(time.getTime() + 24 * 60 * 60 * 1000)

      let hour = time.getHours();
      let minutes = time.getMinutes();
      let seconds = time.getSeconds();

      if (hour < 10) {
        hour = "0" + hour;
      }
      if (minutes < 10) {
        minutes = "0" + minutes;
      }

      let newTime = hour + ":" + minutes + ":" + seconds;
      return newTime;
    },

    // 点击当日到访时间
    visit(date, type) {
      // 1 到访日期
      // 2 开始时间
      // 3 结束时间
      // 4 时间段到访 开始日期
      // 5 时间段到访 结束日期
      let that = this;
      if (type == 2 || type == 3) {
        // if (!this.startTime || !this.endTime) {
        //   mui.toast('请选择被访问人')
        //   return false
        // }

        if (!that.form.intervieweeId) {
          mui.toast("请选择被访问人");
          return false;
        }
      }

      let option = {
        type: date,
      };

      that.picker = new mui.DtPicker(option);
      that.picker.show(function (rs) {
        if (type == 1) {
          let today = that.getToday();

          if (rs.text < today) {
            mui.toast("到访日期不能小于当前日期");
            return;
          } else {
            let visitStartTime =
              that.form.visitStartTime == ""
                ? (that.form.visitStartTime = null)
                : that.form.visitStartTime;

            if (visitStartTime) {
              let time_str = rs.text.replace(/-/g, "/");
              if (
                new Date(time_str + " " + visitStartTime + ":00").getTime() <
                new Date().getTime()
              ) {
                mui.toast("开始时间必须大于当前时间");
                return;
              }
            }

            that.form.visitDayTime = rs.text;
          }
        } else if (type == 2) {
          let visitEndTime =
            that.form.visitEndTime == ""
              ? (that.form.visitEndTime = null)
              : that.form.visitEndTime;

          if (rs.text >= visitEndTime) {
            mui.toast("结束时间不能早于开始时间！");
            return;
          } else {
            // if (
            //   rs.text + ':00' < that.startTime ||
            //   rs.text + ':00' > that.endTime
            // ) {
            //   mui.toast('不在可预约的时间段内，请重新选择时间!')
            //   return
            // }

            // let time = that.getTime(); // 获取时分秒
            let time_str = that.form.visitDayTime.replace(/-/g, "/");

            if (
              new Date(time_str + " " + rs.text + ":00").getTime() <
              new Date().getTime()
            ) {
              mui.toast("开始时间必须大于当前时间");
              return;
            }

            that.form.visitStartTime = rs.text;
          }
        } else if (type == 3) {
          let visitStartTime =
            that.form.visitStartTime == ""
              ? (that.form.visitStartTime = null)
              : that.form.visitStartTime;

          // var visitStartTime =
          //   document.getElementById('demo5').innerText + ':00'
          if (visitStartTime >= rs.text) {
            mui.toast("结束时间不能早于开始时间！");
            return;
          } else {
            // if (
            //   rs.text + ':00' > that.endTime ||
            //   rs.text + ':00' < that.startTime
            // ) {
            //   mui.toast('不在可预约的时间段内，请重新选择时间!')
            //   return
            // }

            let time = that.getTime(); // 获取时分秒
            let time_str = that.form.visitDayTime.replace(/-/g, "/");

            if (
              new Date(time_str + " " + rs.text + ":00").getTime() <
              new Date().getTime()
            ) {
              mui.toast("结束时间必须大于当前时间");
              return;
            }

            that.form.visitEndTime = rs.text;
          }
        } else if (type == 4) {
          let pluralityVisitEndTime =
            that.form.pluralityVisitEndTime == ""
              ? (that.form.pluralityVisitEndTime = null)
              : that.form.pluralityVisitEndTime;

          // var pluralityVisitEndTime =
          //   document.getElementById('demo2').innerText + ':00'
          console.log(pluralityVisitEndTime);

          let todayTime = that.getTodayTime();

          if (rs.text + ":00" < todayTime) {
            mui.toast("开始日期不得小于当前日期");
            return;
          }

          if (rs.text + ":00" >= pluralityVisitEndTime) {
            mui.toast("结束时间不能早于被开始时间！");
            return;
          } else {
            that.form.pluralityVisitStartTime = rs.text + ":00";
          }
        } else if (type == 5) {
          let pluralityVisitStartTime =
            that.form.pluralityVisitStartTime == ""
              ? (that.form.pluralityVisitStartTime = null)
              : that.form.pluralityVisitStartTime;

          // var pluralityVisitStartTime =
          //   document.getElementById('demo3').innerText + ':00'
          if (pluralityVisitStartTime >= rs.text + ":00") {
            mui.toast("结束时间不能早于被开始时间！");
            return;
          } else {
            that.form.pluralityVisitEndTime = rs.text + ":00";
          }
        }

        that.picker.dispose();
        that.picker = null;
      });
    },

    // 提交访客信息
    submit() {
      if (!this.test_phone(this.phone) || this.intervieweeName == "") {
        mui.toast("请输入正确的访问人手机号");
        return false;
      }
      // else {
      //   this.form.visitor.visitorsPhone = this.phone
      // }
      if (this.form.dateType == 0) {
        if (this.form.visitDayTime == "") {
          mui.toast("请选择来访日期");
          return false;
        }
        if (this.form.visitStartTime == "") {
          mui.toast("请选择开始时间");
          return false;
        }

        if (this.form.visitEndTime == "") {
          mui.toast("请选择结束时间");
          return false;
        }
      }

      if (this.form.dateType == 1) {
        if (this.form.pluralityVisitStartTime == "") {
          mui.toast("请选择预约开始时间");
          return false;
        }
        if (this.form.pluralityVisitEndTime == "") {
          mui.toast("请选择预约结束时间");
          return false;
        }
      }

      if (!this.form.visitor.visitorsName) {
        mui.toast("请输入访客姓名");
        return false;
      }

      // if (!this.test_card_no(this.form.visitor.visitorsCard)) {
      //   mui.toast("请输入正确的访客身份证号码");
      //   return false;
      // }

      if (!this.test_phone(this.form.visitor.visitorsPhone)) {
        mui.toast("请输入正确的访客手机号");
        return false;
      }
      // if (!this.form.visitor.visitorsPicKey) {
      //   mui.toast('请录入人脸信息')
      //   return false
      // }

      if (!this.form.reasons) {
        mui.toast("请输入来访原因");
        return false;
      }

      wxSubmitVisitRecord(JSON.stringify(this.form)).then((res) => {
        let that = this;
        if (res.errno == 0) {
          window.localStorage.removeItem("imgUrl");
          mui.toast("访客申请成功");
          setTimeout(function () {
            that.$router.push({
              path: "/appliSucc",
            });
            // window.location.reload()
          }, 2000);
        } else {
          mui.toast(res.errmsg);
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.mui-btn-block div {
  display: inline;
}
.mui-input-group:after {
  height: 0;
}
#face {
  height: auto;
  padding-bottom: 10px;
}
#face div {
  width: 65%;
  float: left;
}
#face img {
  width: 121px;
  margin-top: 10px;
  display: block;
  margin-bottom: 10px;
}
#face a {
  display: block;
  border-radius: 30px;
  width: 121px;
  height: 34px;
  line-height: 34px;
  background-color: #eeeeee;
  color: #999999;
  text-align: center;
  font-size: 13px;
}

.mui-input-clear {
  color: #3295ed !important;
}
</style>
>
