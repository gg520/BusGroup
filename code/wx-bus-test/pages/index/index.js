//index.js
const api=require('../../config/api.js');
var util=require("../../utils/util.js");
//获取应用实例
const app = getApp()


Page({
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    banner: [
      { url:'/static/images/ic_menu_me_nor.png'},
      { url: '/static/images/ic_menu_me_nor.png'}
    ],
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
  },
  //扫一扫
  goQRcode: function () {
    if (app.globalData.hasLogin) {
      wx.scanCode({
        scanType: 'QR_CODE',
        success: (res) => {
          console.log(res.result);
          //result是一个网址，向网站发送请求，验证自己是否可以坐车
          util.request(res.result, { userInfo: wx.getStorageSync('userInfo') }, 'POST').then(res => {
            if (res.errno === 0) {
              //返回的消息正确说明可以上车，启动一个音频文件提示
              wx.playBackgroundAudio({
                dataUrl: '/static/voice/success.mp3',
              });
              console.log("成功" + res);
            } else {//不可以上车，启动另一个音频文件说明不能上车
              // console.log(res);
              wx.playBackgroundAudio({
                dataUrl: '/static/voice/success.mp3',
              });
            }
          })
        }
      })
    } else {
      util.showWarningToast("未登录");
    }
  },
  //线路征集
  goInquiryRoute: function () {
    if (app.globalData.hasLogin) {
      wx.navigateTo({
        url: '/pages/route/inquiryRoute/inquiryRoute',
      })
    } else {
      util.showWarningToast("未登录");
    }
  },
  //跳转到线路招募
  goPlantRoute:function(){
    
      wx.navigateTo({
        url: '/pages/route/planRoute/planRoute',
      })
    
  },
  //跳转到已开线路
  goRunRoute:function(){
    if (app.globalData.hasLogin) {
      wx.navigateTo({
        url: '/pages/route/runRoute/runRoute',
      })
    } else {
      util.showWarningToast("未登录");
    }
  },
  getQRcode:function(){
    //获取我订购的距离该时间的或者地点最近的线路的二维码
    //发送的数据必须把个人信息带上，文件
    wx.navigateTo({
      url: '/pages/qrcode/qrcode',
    })
  }
})
