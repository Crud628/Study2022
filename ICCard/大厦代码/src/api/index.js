import request from "@/utils/request";

// 微信授权 通过code换取openId等信息
export function wxLogin(code) {
  return request({
    url: "/common/code2Session?jsCode=" + code,
    method: "post",
  });
}


// 根据被访人手机号获取名字/部门
export function wxGetEmployeeByPhone(data) {
  return request({
    url: "/visit/wxGetEmployeeByPhone?phone=" + data,
    method: "post",
  });
}

// 获取wx.config 所需参数 
export function createJsapiSignature(data) {
  return request({
    url: "/common/createJsapiSignature",
    method: "post",
    data
  });
}

// 通用上传接口
export function upload(data) {
  return request({
    url: "/storage/upload",
    headers: {
      "Content-Type": "multipart/form-data"
    },
    method: "post",
    data
  });
}

// 点击访客按钮添加访客信息
export function wxInsertVisitor(data) {
  return request({
    url: "/visit/wxInsertVisitor",
    headers: {
      "Content-Type": "multipart/form-data"
    },
    method: "post",
    data
  });
}

// 提交访客信息按钮
export function wxSubmitVisitRecord(data) {
  return request({
    url: "/visit/wxSubmitVisitRecord",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}


// 获取当前部门的访客规则
export function wxGetVisitRule(data) {
  return request({
    url: "/visit/wxGetVisitRule",
    headers: {
      "Content-Type": "multipart/form-data"
    },
    method: "post",
    data
  });
}

// 员工身份信息验证
export function authentication(data) {
  return request({
    url: "/employee/authentication",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}

// 获取来访申请列表
export function visitApplicationList(data) {
  return request({
    url: "/employee/visitApplicationList",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}

// 同意访客申请接口
export function passApplication(data) {
  return request({
    url: "/employee/passApplication",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}


// 拒绝访客申请接口
export function refuseApplication(data) {
  return request({
    url: "/employee/refuseApplication",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}


// 邀约/到访申请历史记录
export function visitHistory(data) {
  return request({
    url: "/employee/visitHistory",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}

// 访客邀约
export function invitationVisitors(data) {
  return request({
    url: "/employee/invitationVisitors",
    headers: {
      "Content-Type": "application/json"
    },
    method: "post",
    data
  });
}



// 访客身份验证
export function visitAuthCode(code) {
  return request({
    url: "/visit/visitAuthCode?authCode=" + code,
    method: "post",
  });
}

// 获取员工信息
export function getEmployeeInfo(id) {
  return request({
    url: "/employee/getEmployeeInfo?id=" + id,
    method: "post",
  });
}