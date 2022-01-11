<template>
  <div class="history">
    <Header :info="info" />

    <div id="refreshContainer" class="mui-content mui-scroll-wrapper">
      <!-- <div id="segmentedControl" class="mui-segmented-control swiper-tab">
        <a class="mui-control-item mui-active" href="#item1">访客邀约</a>
        <a class="mui-control-item" href="#item2">到访申请</a>
      </div>-->

      <div id="item1" class="mui-control-content">
        <div v-for="item in data1" :key="item.id" class="cardBox">
          <div>
            <div class="cardMem">
              <span>{{ item.visitorsName }}</span>
              <span>访客</span>
              <div v-if="item.status == '审核拒绝'" class="cardYq">{{ item.status }}</div>
              <div v-if="item.status == '申请成功'" class="cardSucc">{{ item.status }}</div>
              <div v-if="item.status == '已完成'" class="cardOver">{{ item.status }}</div>
            </div>
            <div class="cardInfo">
              <div>
                <img src="@/assets/image/man.png" />
                <p>
                  <i>被访人员</i>
                  {{ item.employeeName }}
                </p>
              </div>
              <div>
                <img src="@/assets/image/clock.png" />
                <p>
                  <i>来访时间</i>
                  <span>{{ getTime(item) }}</span>
                </p>
              </div>
              <div>
                <img src="@/assets/image/book.png" />
                <p>
                  <i>来访事由</i>
                  <span>{{ item.reasons }}</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div id="item2" class="mui-control-content mui-active">
        <div v-for="item in data0" :key="item.id" class="cardBox">
          <div>
            <div class="cardMem">
              <span>{{ item.visitorsName }}</span>
              <span>访客</span>
              <div v-if="item.status == '审核拒绝'" class="cardYq">{{ item.status }}</div>
              <div v-if="item.status == '申请成功'" class="cardSucc">{{ item.status }}</div>
              <div v-if="item.status == '审核通过'" class="cardSucc">{{ item.status }}</div>
              <div v-if="item.status == '已完成'" class="cardOver">{{ item.status }}</div>
            </div>
            <div class="cardInfo">
              <div>
                <img src="@/assets/image/man.png" />
                <p>
                  <i>被访人员</i>
                  {{ item.employeeName }}
                </p>
              </div>
              <div>
                <img src="@/assets/image/clock.png" />
                <p>
                  <i>来访时间</i>
                  <span>{{ getTime(item) }}</span>
                </p>
              </div>
              <div>
                <img src="@/assets/image/book.png" />
                <p>
                  <i>来访事由</i>
                  <span>{{ item.reasons }}</span>
                </p>
              </div>
            </div>
          </div>
        </div>

        <!-- <div class="cardBox">
          <div>
            <div class="cardMem">
              <span>李鹏飞11</span>
              <span>访客</span>
              <div class="cardSucc">申请成功</div>
            </div>
            <div class="cardInfo">
              <div>
                <img src="@/assets/image/man.png" />
                <p>
                  <i>被访人员</i>爱德华
                </p>
              </div>
              <div>
                <img src="@/assets/image/clock.png" />
                <p>
                  <i>来访时间</i>2020 05/22 20:20-20:50
                </p>
              </div>
              <div>
                <img src="@/assets/image/book.png" />
                <p>
                  <i>来访事由</i>商务洽谈
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="cardBox">
          <div>
            <div class="cardMem">
              <span>李鹏飞</span>
              <span>访客</span>
              <div class="cardOver">已完成</div>
            </div>
            <div class="cardInfo">
              <div>
                <img src="@/assets/image/man.png" />
                <p>
                  <i>被访人员</i>爱德华
                </p>
              </div>
              <div>
                <img src="@/assets/image/clock.png" />
                <p>
                  <i>来访时间</i>2020 05/22 20:20-20:50
                </p>
              </div>
              <div>
                <img src="@/assets/image/book.png" />
                <p>
                  <i>来访事由</i>商务洽谈
                </p>
              </div>
            </div>
          </div>
        </div>-->
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header' // secondary package based on el-pagination

import {
  visitHistory, // 邀约/到访申请历史记录
} from '@/api/index'

export default {
  components: { Header },
  name: 'history',
  data() {
    return {
      info: {
        title: '历史记录',
        flag: true,
      },
      form: {
        weixinOpenid: window.localStorage.getItem('openid'),
        visitorsType: 0,
        limit: 10,
      },
      pageType0: 0,
      pageType1: 0,
      data0: [],
      data1: [],
      data0Flag: false,
      data1Flag: false,
    }
  },
  computed: {
    getTime() {
      return function (item) {
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

          let date =
            visityear +
            ' ' +
            visitmonth +
            '/' +
            visitday +
            ' ' +
            visitStartTime +
            ' - ' +
            visitEndTime

          return date
        }
      }
    },
  },
  created() {
    // 申请 历史记录
    this.getList(0)
    // 邀约 历史记录
    this.getList(1)
  },
  mounted() {
    var scrooll = document.getElementById('refreshContainer')
    // addEventListener() 方法用于向指定元素添加事件句柄。
    scrooll.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    getList(num) {
      let that = this

      if (document.querySelector('.mui-active.mui-control-item')) {
        let text =
          document.querySelector('.mui-active.mui-control-item').innerText || ''

        if (text == '访客邀约' && that.data1Flag) {
          return false
        }

        if (text == '到访申请' && that.data0Flag) {
          return false
        }
      }
      that.form.visitorsType = num
      if (num == 0) {
        that.pageType0 = that.pageType0 + 1
        that.form.page = that.pageType0
      } else {
        that.pageType1 = that.pageType1 + 1
        that.form.page = that.pageType1
      }

      visitHistory(JSON.stringify(that.form)).then((res) => {
        if (num == 0) {
          if (res.data.length == 0) {
            that.data0Flag = true
          }
          that.data0 = [...that.data0, ...res.data]
        } else {
          if (res.data.length == 0) {
            that.data1Flag = true
          }
          that.data1 = [...that.data1, ...res.data]
        }
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
        let text = document.querySelector(
          '.mui-active.mui-control-item'
        ).innerText
        if (text == '访客邀约') {
          this.getList(1)
        } else {
          this.getList(0)
        }
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.swiper-tab {
  position: fixed;
  top: 44px;
  z-index: 99;
}
.mui-bar-nav ~ .mui-content {
  padding-top: 80px;
}
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
</style>>
