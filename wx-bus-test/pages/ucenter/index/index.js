// pages/ucenter/index/index.js
var util=require("../../../utils/util.js");
var api = require("../../../config/api.js")
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{
      nickName:"点击登录",
      avatarUrl:"/static/images/ic_nu_user.png",
      exitLoginBtn:true,
    },
    //设置显示信息
    //乘客
    isPassenger:false,
    //司机
    isDirver:false,
    new:false,
  },
 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let that =this
    if (app.globalData.hasLogin) {
      util.request(api.GetNotify, {}, "POST").then(function (res) {
        if (res.errno === 0) {
          console.log(res.data.length)
          var length=0;
          for(let i=0;i<res.data.length;i++){
            if(res.data[i].mark==0){
              length++;
            }
          }
          if (length > 0) {

            that.setData({
              new:true
            })
          } else{
            that.setData({
              new: false
            })
          }
        }
      });
      //验证是否登录
      console.log("界面验证已经成功登录");
      let userInfo = wx.getStorageSync('userInfo');
      
      if(userInfo==null||userInfo.length<=0){
        app.globalData.hasLogin=false;
        console.log("userInfo,为空，请重新登录" );
      }
      var user = wx.getStorageSync("user");
      if (user === 'Weixin_Driver') {
        this.setData({
          userInfo: userInfo,
          exitLoginBtn: false,
          isDirver: true,
        });
      }
      if (user === 'Weixin_Passenger') {
        this.setData({
          userInfo: userInfo,
          exitLoginBtn: false,
          isPassenger: true,
        
        });
      }
      

    } else {//没有登录
      console.log("在界面验证登录为未登录");
      this.setData({
        userInfo: {
          nickName: "点击登录",
          avatarUrl: "/static/images/ic_nu_user.png",
          exitLoginBtn: true,
          
        },
        isPassenger: false,
        isDirver: false
      });
      console.log(this.data.isPassenger)
    }
  },
  //扫一扫
  goQRcode:function(){
    if (app.globalData.hasLogin) {
      wx.scanCode({
        scanType:'QR_CODE',
        success:(res)=>{
          console.log(res.result);
          //result是一个网址，向网站发送请求，验证自己是否可以坐车
          util.request(res.result, { userInfo: wx.getStorageSync('userInfo') }, 'POST').then(res => {
            if (res.errno === 0) {
              //返回的消息正确说明可以上车，启动一个音频文件提示
              wx.playBackgroundAudio({
                dataUrl: '/static/voice/success.mp3',
              });
              console.log("成功"+res);
            }else{//不可以上车，启动另一个音频文件说明不能上车
              // console.log(res);
              wx.playBackgroundAudio({
                dataUrl: '/static/voice/success.mp3',
              });
            }
          })
        }
      })
    }else{
      util.showWarningToast("未登录");
    }
  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
/**
 * 跳转到登录界面
 */
  goLogin:function(){
    console.log("登录")
    if (!app.globalData.hasLogin) {
      wx.navigateTo({
        url: '/pages/auth/login/login',
      })
    }else{
      console.log("已登录" + app.globalData.hasLogin)
    }
  },
  exitLogin:function(){
    let that=this;
    if (app.globalData.hasLogin) {
      wx.showModal({
        title: '',
        confirmColor: '#b4282d',
        content: '退出登录',
        success: function (res) {
          if (res.confirm) {
            wx.removeStorageSync("token");
            wx.removeStorageSync("userInfo");
            wx.removeStorageSync("user");
            app.globalData.hasLogin = false;
            // this.setData;
            // wx.navigateBack({
              
            // });
            that.setData({
              userInfo: {
                nickName: "点击登录",
                avatarUrl: "/static/images/ic_nu_user.png",
                exitLoginBtn: true,
                isPassenger: false,
                isDirver: false
              }
            });
            wx.switchTab({
              url: '/pages/index/index',
            })
          }
        }
      })
    }else{
      util.showWarningToast("未登录");
    }
  },
  //线路征集
  goInquiryRoute:function(){
    if(app.globalData.hasLogin){
      wx.navigateTo({
        url: '/pages/route/inquiryRoute/inquiryRoute',
      })
    }else{
      util.showWarningToast("未登录");
    }
    
  },
  goOreder: function () {
    console.log("denglu");
    if (app.globalData.hasLogin) {

      wx.navigateTo({
        url: '/pages/ucenter/order/order',
      })
    } else {
      util.showWarningToast("未登录");
    }
  },
  getPhoneNumber: function (e) {
    console.log(e.detail.errMsg)
    console.log(e.detail.iv)
    console.log(e.detail.encryptedData)
  },
  goMyInfo:function(){
    wx.navigateTo({
      url: '/pages/ucenter/myInfo/myInfo',
    })
  },
  goMyCollect:function(){
    wx.navigateTo({
      url: '/pages/ucenter/myCollect/myCollect',
    })
  },
  goHelp:function(){
    wx.navigateTo({
      url: '/pages/help/help/help',
    })
  },
  goDriverFootprint:function(){
    wx.navigateTo({
      url: '/pages/ucenter/driverInfo/driverInfo/dirverInfo',
    })
  },
  goStask:function(){
    wx.navigateTo({
      url: '/pages/ucenter/driverTask/driverTask',
    })
  },
  goBusInfo:function(){
    wx.navigateTo({
      url: '/pages/ucenter/busInfo/busInfo',
    })
  },
  goInform:function(){
    wx.navigateTo({
      url: '/pages/ucenter/notify/notifyIndex/notifyIndex',
    })
  },
  goAnnouncement:function(){
    wx.navigateTo({
      url: '/pages/system/messageList/messageList',
    })
  }
})