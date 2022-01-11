<template>
  <div class="staindex-box">
    <Header :info="info" />

    <div class="staindex">
      <div class="indexText1">Staff</div>
      <div class="indexText2">Entrance</div>
      <div class="indexText3">员工首页</div>
    </div>
    <div class="staMain">
      <!-- <a href="javascript:void(0)" @click="jumpLink('/visinv')">
        <div>
          <img src="@/assets/image/sta1.png" alt />
        </div>
        <b>访客邀约</b>
      </a>-->

      <a href="javascript:void(0)" @click="jumpLink('/appliList')">
        <div>
          <img src="@/assets/image/sta2.png" alt />
        </div>
        <b>来访申请</b>
      </a>
      <a href="javascript:void(0)" @click="jumpLink('/history')">
        <div>
          <img src="@/assets/image/sta3.png" alt />
        </div>
        <b>历史记录</b>
      </a>
      <a href="javascript:void(0)" @click="getCode(true)">
        <div>
          <img src="@/assets/image/erCode.png" alt />
        </div>
        <b>员工二维码</b>
      </a>
    </div>

    <div v-show="flag" @click="flag = false" class="mask">
      <img :src="QRcode" alt />
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header' // secondary package based on el-pagination
import {
  getEmployeeInfo, // 邀约/到访申请历史记录
} from '@/api/index'

export default {
  components: { Header },
  name: 'staindex',
  data() {
    return {
      info: {
        title: '员工首页',
        flag: true,
      },
      flag: false,
      QRcode: '',
    }
  },
  created() {
    // if (this.storage.getItem("staffinfo")) {
    //   this.QRcode = this.storage.getItem("staffinfo").employeeQrcodeUrl;
    // }
  },
  mounted() {
    // 获取默认二维码
    this.getCode(false)
  },
  methods: {
    getCode(flag) {
      let id
      if (this.storage.getItem('staffinfo')) {
        id = this.storage.getItem('staffinfo').id
      }
      getEmployeeInfo(id).then((res) => {
        this.QRcode = res.data.employeeQrcodeUrl
      })
      this.flag = flag
    },

    jumpLink(path) {
      this.$router.push({
        path,
        query: { randomID: 'id' + Math.random() },
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.mask {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.6);
  z-index: 9;
  display: flex;
  justify-content: center;
  align-items: center;
}

.mask img {
  width: 200px;
  height: 200px;
}

html,
body {
  height: 100%;
}
.staindex-box {
  height: 100vh;
  background: url(../../assets/image/staBg.jpg) no-repeat;
  background-size: 100% 100%;
}
.indexText1 {
  font-size: 43px;
  color: #fff;
  font-weight: bold;
  padding-top: 40px;
}
.indexText2 {
  font-size: 43px;
  color: #fff;
  font-weight: bold;
  padding-top: 10px;
}
.indexText3 {
  font-size: 25px;
  color: #fff;
  padding-top: 5px;
  line-height: 30px;
}
</style>>
