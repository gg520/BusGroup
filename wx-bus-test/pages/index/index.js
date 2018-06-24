//index.js
const api=require('../../config/api.js');
var util=require("../../utils/util.js");
//获取应用实例
const app = getApp()


Page({
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo'),//判断小程序的API，回调，参数，组件等是否在当前版本可用。
    banner: [
      { url:'/static/images/ic_mu_index1.png'},
      { url: '/static/images/ic_mu_index2.jpg'},
      { url: '/static/images/ic_mu_index5.jpg' },
      { url: '/static/images/ic_mu_index4.jpg' }
    ],
    //设置显示信息
    //乘客
    isPassenger: false,
    //司机
    isDirver: false,
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    //设置信息

    var token = wx.getStorageSync("token");
    if(token!=null){
      app.globalData.hasLogin=true;
    }
    //数据显示
    if (app.globalData.hasLogin) {
      var user = wx.getStorageSync("user");
      console.log(user)
      if (user === 'Weixin_Driver') {
        this.setData({

          exitLoginBtn: false,
          isDirver: true
        });
      }
      if (user === 'Weixin_Passenger') {
        this.setData({

          exitLoginBtn: false,
          isPassenger: true,
        });
      }
    } else {
      console.log("未登录")
      this.setData({
        exitLoginBtn: false,
        isPassenger: false,
        isDirver: false,
      });
    }
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
      //判断是否实名认证了
      wx.request({
        url: api.CheckInfo,
        header: {
          "X-Bus-Token": wx.getStorageSync("token"),
          "Connect_Platform": "Weixin_Passenger"
        },
        success: function (res) {
          if (res.data.errno === 0) {
            //验证通过
            wx.navigateTo({
              url: '/pages/route/inquiryRoute/inquiryRoute',
            });

          } else if (res.errno === 401) {
            util.showWarningToast("未登录");
            }else{
            //验证失败
            util.showWarningToast("未实名认证");
          }
        }
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
    
    wx.navigateTo({
      url: '/pages/route/runRoute/runRoute',
    })
  },
  getQRcode:function(){
    //获取我订购的距离该时间的或者地点最近的线路的二维码
    //发送的数据必须把个人信息带上，文件

    
    if (app.globalData.hasLogin) {

      //判断是否实名认证了
      wx.request({

        url: api.CheckInfo, 
        header: {
          "X-Bus-Token": wx.getStorageSync("token"),
          "Connect_Platform": "Weixin_Passenger"
        },
        success:function(res){
          if(res.data.errno===0){
            //验证通过
            wx.navigateTo({
              url: '/pages/qrcode/qrcode',
            });

          } else if (res.errno === 401) {
            util.showWarningToast("未登录");
          }else{
            //验证失败
            util.showWarningToast("未实名认证");
          }
        }
      })
     
    } else {
      util.showWarningToast("未登录");
    }
  },
  onShow: function () {
    //数据显示
    if (app.globalData.hasLogin) {
      var user = wx.getStorageSync("user");
      console.log(user)
      if (user === 'Weixin_Driver') {
        this.setData({

          exitLoginBtn: false,
          isDirver: true
        });
      }
      if (user === 'Weixin_Passenger') {
        this.setData({

          exitLoginBtn: false,
          isPassenger: true,
        });
      }
    }else{
      this.setData({

        exitLoginBtn: false,
        isPassenger: false,
        isDirver:false,
      });
    }
  },
  bindGetUserInfo: function (e) {
    console.log(e.detail.userInfo)
  }
})
