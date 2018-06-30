// pages/login/login.js
var user = require("../../../services/user.js");
var util = require("../../../utils/util.js");
var api = require("../../../config/api.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',//13592573327
    password: '',//123456
    code: '',
    loginErrotCount: 0

  },
  bindUsernameInput:function(e){
    this.setData({
      username:e.detail.value
    })
  },
  bindPasswordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
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
  wxLogin: function (e) {
 
      console.log(e.detail.userInfo)
    
    user.checkLogin().catch(() => {
      wx.showLoading({
        title: '加载中...',
      })
      user.loginByWeixin().then(res => {
        app.globalData.hasLogin = true;
        wx.hideLoading();
        wx.navigateBack({
          delta: 1
        })
      }).catch((err) => {
        wx.hideLoading();
        app.globalData.hasLogin = false;
        console.log(err);
        util.showErrorToast('微信登录失败');
      })
      
    })
  },
  // 用户登录
  accountLogin:function(e){
    let that=this;
    console.log("username:" + this.data.username)
    user.checkLogin().catch(() => {
      wx.showLoading({
        title: '加载中...',
      })
      wx.request({
        url: api.AuthLoginByAccount,
        data:{
          username:that.data.username,
          password:that.data.password
        },
        header: {
          "Content-Type": "application/json",
          "Connect_Platform": "Weixin_Passenger"
        },
        method:"POST",
        success:function(res){
          if(res.data.errno === 0){
            app.globalData.hasLogin = true;
            wx.setStorageSync('token', res.data.data.token);
            wx.setStorageSync('userInfo', res.data.data.userInfo);
            wx.setStorageSync("user", res.header.Connect_Platform);
            //乘客：Weixin_Passenger
            //司机：Weixin_Driver
            console.log(res.header.Connect_Platform);
            app.globalData.hasLogin = true;
            
            wx.navigateBack({
              delta: 1
            })
            
          } else if (res.data.errno === -1) {
            util.showWarningToast(res.data.errmsg);
          }
          
        },
        fail:function(mgs){
          
        },
        complete(){
          wx.hideLoading();
        }
        
      })
      
    })

    
  },
  clearInput: function (e) {
    switch (e.currentTarget.id) {
      case 'clear-username':
        this.setData({
          username: ''
        });
        break;
      case 'clear-password':
        this.setData({
          password: ''
        });
        break;
      case 'clear-code':
        this.setData({
          code: ''
        });
        break;
    }
  }
  
})