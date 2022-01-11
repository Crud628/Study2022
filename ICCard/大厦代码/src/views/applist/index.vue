<template>
  <div class="appliList">
    <Header :info="info" />
    <div id="refreshContainer" class="mui-content mui-scroll-wrapper">
      <div v-for="item in list" :key="item.id" class="cardBox">
        <input type="hidden" value class="boxId" />
        <div>
          <div class="cardMem">
            <span>{{ item.visitorsName }}</span>
            <span>访客</span>
          </div>
          <div class="cardInfo">
            <div>
              <img src="@/assets/image/man.png" alt />
              <p>
                <i>被访人员</i>
                {{ item.employeeName }}
              </p>
            </div>
            <div>
              <img src="@/assets/image/clock.png" alt />
              <p>
                <i>来访时间</i>
                <span>{{ getTime(item) }}</span>
              </p>
            </div>
            <div>
              <img src="@/assets/image/book.png" alt />
              <p>
                <i>来访事由</i>
                <span>{{ item.reasons }}</span>
              </p>
            </div>
          </div>
          <div v-if="item.canAudit == 1" class="cardBtn">
            <span @click="refuse(item)">拒绝</span>
            <span @click="agree(item)">同意</span>
          </div>
        </div>
      </div>
    </div>

    <div v-if="agreeAllType" class="cardBtn">
      <span @click="agreeAll()" id="popTj3">全部同意</span>
    </div>

    <!-- 弹窗 -->
    <div class="mui-popup mui-popup-in" id="popup1" v-show="agreeFlag">
      <div class="mui-popup-inner">
        <div class="mui-popup-title">您确定要同意</div>
        <div class="mui-popup-text">
          访客-
          <em id="popName1">{{ parameter.visitorsName }}</em>
          <span>的申请吗</span>
        </div>
      </div>
      <div class="mui-popup-buttons">
        <span @click="agreeFlag = false" class="mui-popup-button popQuxiao">取消</span>
        <span @click="submitAgree" class="mui-popup-button" id="popTj1">提交</span>
      </div>
    </div>

    <!-- 弹窗 -->
    <div class="mui-popup mui-popup-in" id="popup3" v-show="agreeFlagAll">
      <div class="mui-popup-inner">
        <div class="mui-popup-title">您确定要同意所有访客的申请么</div>
        <!-- <div class="mui-popup-text">
          访客-
          <em id="popName1">{{ parameter.visitorsName }}</em>
          <span>的申请吗</span>
        </div>-->
      </div>
      <div class="mui-popup-buttons">
        <span @click="agreeFlagAll = false" class="mui-popup-button popQuxiao">取消</span>
        <span @click="submitAgree('all')" class="mui-popup-button">提交</span>
      </div>
    </div>

    <!-- 弹窗 -->
    <div class="mui-popup mui-popup-in" id="popup2" v-show="refuseFlag">
      <div class="mui-popup-inner">
        <div class="mui-popup-title">
          您拒绝了
          <span id="popName2">{{ parameter.visitorsName }}</span>的申请
        </div>
        <div class="wx-popup-con">请填写拒绝理由</div>
        <textarea v-model="reasons" maxlength="50" placeholder="请填在此输入理由"></textarea>
      </div>
      <div class="mui-popup-buttons">
        <span @click="refuseFlag = false" class="mui-popup-button">取消</span>
        <span @click="submitrefus" class="mui-popup-button">提交</span>
      </div>
    </div>
    <div class="mui-popup-backdrop mui-active" v-show="agreeFlag || refuseFlag || agreeFlagAll"></div>
  </div>
</template>

<script>
import Header from '@/components/Header' // secondary package based on el-pagination

import {
  visitApplicationList, // 来访申请列表
  passApplication, // 通过访客申请接口
  refuseApplication, // 拒绝访客申请接口
} from '@/api/index'

export default {
  components: { Header },
  name: 'appliList',

  data() {
    return {
      info: {
        title: '来访申请列表',
        flag: true,
      },
      form: {
        weixinOpenid: window.localStorage.getItem('openid'),
        page: 0, // 页码
        limit: 10, // 每夜条数
      },
      list: [],
      flag: false,
      agreeFlag: false,
      refuseFlag: false,
      agreeFlagAll: false,
      parameter: {},
      reasons: '',
      agreeAllType: false,
    }
  },
  computed: {
    // 计算属性的 getter
    getTime: function () {
      return function (item) {
        // `this` 指向 vm 实例
        if (item.dateType == '短期') {
          let Startyear = item.pluralityVisitStartTime.substring(0, 4)
          let Startmonth = item.pluralityVisitStartTime.substring(5, 7)
          let Startday = item.pluralityVisitStartTime.substring(8, 10)
          let Endyear = item.pluralityVisitEndTime.substring(0, 4)
          let Endmonth = item.pluralityVisitEndTime.substring(5, 7)
          let Endday = item.pluralityVisitEndTime.substring(8, 10)

          let startHours = item.pluralityVisitStartTime.substring(11, 13)
          let startMin = item.pluralityVisitStartTime.substring(14, 16)

          let endHours = item.pluralityVisitEndTime.substring(11, 13)
          let endMin = item.pluralityVisitEndTime.substring(14, 16)

          let date =
            Startyear +
            ' ' +
            Startmonth +
            '/' +
            Startday +
            ' ' +
            startHours +
            ':' +
            startMin +
            ' - ' +
            Endyear +
            ' ' +
            Endmonth +
            '/' +
            Endday +
            ' ' +
            endHours +
            ':' +
            endMin

          return date
        } else {
          let visityear = item.visitDayTime.substring(0, 4)
          let visitmonth = item.visitDayTime.substring(5, 7)
          let visitday = item.visitDayTime.substring(8, 10)
          let visitStartTime = item.visitStartTime.substring(0, 5)
          let visitEndTime = item.visitEndTime.substring(0, 5)

          let timeHtml =
            visityear +
            ' ' +
            visitmonth +
            '/' +
            visitday +
            ' ' +
            visitStartTime +
            ' - ' +
            visitEndTime
          return timeHtml
        }
      }
    },
  },
  created() {
    this.getList()
  },
  mounted() {
    var scrooll = document.getElementById('refreshContainer')
    // addEventListener() 方法用于向指定元素添加事件句柄。

    scrooll.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    getList() {
      let that = this
      if (that.flag) {
        return false
      }

      this.form.page = this.form.page + 1
      visitApplicationList(JSON.stringify(this.form)).then((res) => {
        if (res.data.length == 0) {
          that.handleScroll = null
          that.flag = true
        } else {
          that.list = [...that.list, ...res.data]
        }
        that.agreeAllType = that.list.some((item) => item.canAudit != 0)
      })
    },

    handleScroll() {
      let node = document.getElementById('refreshContainer')
      // 滚动条总高度
      let scrollHeight = node.scrollHeight
      // 滚动条距离顶部距离
      let scrollTop = node.scrollTop
      // 文档可是距离高度
      let clientHeight = node.clientHeight

      var num = clientHeight + scrollTop - scrollHeight

      if (num > -10 && num < 10) {
        this.getList()
      }
    },

    //点击同意执行的方法
    agree(item) {
      this.parameter = item
      this.agreeFlag = true
    },

    //点击拒绝执行的方法
    refuse(item) {
      this.parameter = item
      this.refuseFlag = true
    },

    // 点击全部同意方法
    agreeAll() {
      this.agreeFlagAll = true
    },

    // 提交同意
    submitAgree(type) {
      let that = this
      let form
      if (type && type == 'all') {
        let idList = this.list.map((item) => item.id)
        form = {
          visitRecordList: idList,
          weixinOpenid: this.form.weixinOpenid,
        }
      } else {
        form = {
          visitRecordList: [this.parameter.id],
          weixinOpenid: this.form.weixinOpenid,
        }
      }

      passApplication(JSON.stringify(form)).then((res) => {
        if (res.errno == 0) {
          that.agreeFlagAll = false
          that.agreeFlag = false
          mui.toast('提交成功')
          setTimeout(function () {
            window.location.reload()
          }, 2000)
        }
      })
    },
    // 提交拒绝
    submitrefus() {
      if (this.reasons.length == 0) {
        mui.toast('请填写拒绝理由')
        return false
      }

      let form = {
        id: this.parameter.id,
        weixinOpenid: this.form.weixinOpenid,
        excuseReasons: this.reasons,
      }
      refuseApplication(JSON.stringify(form)).then((res) => {
        if (res.errno == 0) {
          this.refuseFlag = false
          mui.toast('提交成功')
          setTimeout(function () {
            window.location.reload()
          }, 2000)
        }
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.mui-content {
  overflow: auto;
}

.cardInfo div p i {
  vertical-align: top;
}
.cardInfo div p span {
  width: calc(100vw - 200px);
  display: inline-block;
  text-align: left;
}
.cardBox > div .cardMem {
  text-align: left;
}
.mui-table-view:after {
  background-color: #fff;
}

.mui-table-view {
  background-color: #f8f8f8;
  // padding: 40px 0px 0 0;
}

body,
html {
  height: 100%;
}

body {
  background-color: #f8f8f8;
}

.mui-content {
  background-color: #f8f8f8;
  padding-bottom: 20px;
}

.mui-input-group:after {
  height: 0;
}

.mui-popup-button:first-child {
  color: #333;
}

.mui-popup-title {
  font-weight: bold;
  line-height: 30px;
  padding-top: 10px;
}

.mui-popup-title + .mui-popup-text {
  font-size: 18px;
  color: #999;

  line-height: 30px;
}

.mui-popup-title + .mui-popup-text span {
  color: #333;
  font-weight: bold;
  margin-left: 6px;
}

.mui-popup-title + .mui-popup-text em {
  font-style: normal;
}

.wx-popup-con {
  margin: 0px 5px20px;
  text-align: center;
  font-size: 15px;
  color: #999999;
}

.mui-popup textarea {
  background: #eee;
  width: 100%;
  padding: 10px;
  height: 100px;
  font-size: 14px;
  border-radius: 5px;
  border: none;
  margin-top: 10px;
  display: block;
}
#popTj3 {
  position: fixed;
  bottom: 20px;
  width: calc(100vw - 40px) !important;
  z-index: 99;
  color: #fff;
  background-color: #3295ed;
}
#refreshContainer {
  padding-bottom: 50px;
}
</style>>
