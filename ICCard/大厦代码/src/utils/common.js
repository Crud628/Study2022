/**
 * 通用js方法封装处理
 */



export function font_size() {
  var pwidth = 750
  var prem = 100

  getRem(pwidth, prem)
  //rem.js 自适应css
  // window.onload = function(){
  // 	// 375为设计师尺寸  分为100份
  //     getRem(375,100)
  // };
  window.onresize = function () {
    getRem(pwidth, prem)
  }

  function getRem(pwidth, prem) {
    var html = document.getElementsByTagName("html")[0]
    var oWidth =
      document.body.clientWidth || document.documentElement.clientWidth
    html.style.fontSize = (oWidth / pwidth) * prem + "px"
  }
}

// 手机号校验
export function test_phone(val) {
  var reg_tel = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
  return !!val && reg_tel.test(val)
}


// 身份证号码校验
export function test_card_no(val) {
  var reg_tel = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
  return !!val && reg_tel.test(val)
}



export function toThousands(num) {
  var result = [], counter = 0;
  num = (num || 0).toString().split('');
  for (var i = num.length - 1; i >= 0; i--) {
    counter++;
    result.unshift(num[i]);
    if (!(counter % 3) && i != 0) { result.unshift(','); }
  }
  return result.join('');
}

// 日期格式化
export function parseTime(time, pattern) {
  if (arguments.length === 0) {
    return null;
  }
  const format = pattern || "{y}-{m}-{d} {h}:{i}:{s}";
  let date;
  if (typeof time === "object") {
    date = time;
  } else {
    if (typeof time === "string" && /^[0-9]+$/.test(time)) {
      time = parseInt(time);
    }
    if (typeof time === "number" && time.toString().length === 10) {
      time = time * 1000;
    }
    date = new Date(time);
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  };
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key];
    // Note: getDay() returns 0 on Sunday
    if (key === "a") {
      return ["日", "一", "二", "三", "四", "五", "六"][value];
    }
    if (result.length > 0 && value < 10) {
      value = "0" + value;
    }
    return value || 0;
  });
  return time_str;
}

// 表单重置
export function resetForm(refName) {
  if (this.$refs[refName] !== undefined) {
    this.$refs[refName].resetFields();
  }
}

// 添加日期范围
export function addDateRange(params, dateRange) {
  var search = params;
  if (null != dateRange) {
    search.params = {
      beginTime: this.dateRange[0],
      endTime: this.dateRange[1]
    };
  }
  return search;
}

// 回显数据字典
export function selectDictLabel(datas, value) {
  var actions = [];
  Object.keys(datas).map(key => {
    if (datas[key].dictValue == "" + value) {
      actions.push(datas[key].dictLabel);
      return false;
    }
  });
  return actions.join("");
}

// 通用下载方法
export function download(fileName) {
  window.location.href =
    SERVER_ADDRESS +
    "/common/download?fileName=" +
    encodeURI(fileName) +
    "&delete=" +
    true;
}

// 字符串格式化(%s )
export function sprintf(str) {
  var args = arguments,
    flag = true,
    i = 1;
  str = str.replace(/%s/g, function () {
    var arg = args[i++];
    if (typeof arg === "undefined") {
      flag = false;
      return "";
    }
    return arg;
  });
  return flag ? str : "";
}

//普通字符转换成转意符
export function html2Escape(str) {
  return str.replace(/[<>&"]/g, function (c) {
    return {
      "<": "&lt;",
      ">": "&gt;",
      "&": "&amp;",
      '"': "&quot;"
    }[c];
  });
}

/* eslint-disable */
export function getUrlKey(str) {
  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null
}



export function getQueryVariable(variable) {
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0] == variable) { return pair[1]; }
  }
  return (false);
}

//  图片压缩共通方法
export function compressImg(file) {

  var src;
  var files;
  var fileSize = parseFloat(parseInt(file['size']) / 1024 / 1024).toFixed(2);
  var read = new FileReader();
  read.readAsDataURL(file);
  return new Promise(function (resolve, reject) {
    read.onload = function (e) {
      var img = new Image();
      img.src = e.target.result;
      img.onload = function () {
        //默认按比例压缩
        var w = this.width,
          h = this.height;
        //生成canvas
        var canvas = document.createElement('canvas');
        var ctx = canvas.getContext('2d');
        var base64;
        // 创建属性节点
        canvas.setAttribute("width", w);
        canvas.setAttribute("height", h);
        ctx.drawImage(this, 0, 0, w, h);
        if (fileSize < 1) {
          //如果图片小于一兆 那么不执行压缩操作
          base64 = canvas.toDataURL(file['type'], 1);
        } else if (fileSize > 1 && fileSize < 2) {
          //如果图片大于1M并且小于2M 那么压缩0.5
          base64 = canvas.toDataURL(file['type'], 0.5);
        } else {
          //如果图片超过2m 那么压缩0.2
          base64 = canvas.toDataURL(file['type'], 0.2);
        }
        // 回调函数返回file的值（将base64编码转成file）
        files = dataURLtoFile(base64); //如果后台接收类型为base64的话这一步可以省略

        resolve(files)
      };
    };
  })

}


//base64转码（压缩完成后的图片为base64编码，这个方法可以将base64编码转回file文件）
function dataURLtoFile(dataurl) {
  var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n);
  }
  let filename = guid() + "." + "image/jpeg".split("/")[1]
  return new File([u8arr], filename, { type: mime });

}

// 获取uuid
function guid() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = Math.random() * 16 | 0,
      v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}


