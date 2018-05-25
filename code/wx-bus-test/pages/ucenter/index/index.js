// pages/ucenter/index/index.js
var util=require("../../../utils/util.js");

var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{
      nickName:"点击登录",
      avatarUrl:"http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
      exitLoginBtn:true,
    }
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
    if(app.globalData.hasLogin){
      //验证是否登录
      // console.log("界面验证已经成功登录");
      let userInfo = wx.getStorageSync('userInfo');
      console.log(userInfo);
      this.setData({
        userInfo:userInfo,
        exitLoginBtn:false
      });
    }else{//没有登录
      // console.log("在界面验证登录为未登录");
      this.setData({
        userInfo: {
          nickName: "点击登录",
            avatarUrl:"http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
            exitLoginBtn: true
        }
      });
      exitLogin:;
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
    if (!app.globalData.hasLogin) {
      wx.navigateTo({
        url: '/pages/auth/login/login',
      })
    }
  },
  exitLogin:function(){
    if (app.globalData.hasLogin) {
      wx.showModal({
        title: '',
        confirmColor: '#b4282d',
        content: '退出登录',
        success: function (res) {
          if (res.confirm) {
            wx.removeStorageSync("token");
            wx.removeStorageSync("userInfo");
            app.globalData.hasLogin = false;
            // this.setData;
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

})