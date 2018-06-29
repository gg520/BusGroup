// pages/qrcode/qrcode.js
var util = require("../../../utils/util.js");
var api = require("../../../config/api.js");
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //生成的二维码图片的临时地址
    imgpath: '',
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
    let that = this;
    console.log("显示" + api.GetDriveQRcode)
    wx.downloadFile({
      url: api.GetDriveQRcode,
      header: {
        "X-Bus-Token": wx.getStorageSync("token"),
        "Connect_Platform": "Weixin_Driver"
      },
      success: function (res) {
        console.log(res.data)
        if (res.statusCode === 200) {
          if (res.errno == 401) {
            //清除登录的相关内容
            // console.log("执行跳转")
            try {
              wx.removeStorageSync("userInfo");
              wx.removeStorageSync("token");
            } catch (e) {

            }
            wx.navigateTo({
              url: '/pages/auth/login/login',
            });
          }
          //成功
          console.log(res.tempFilePath);
          that.setData({
            imgpath: res.tempFilePath,
          });
        } else {
          //错误数据
        }
      }

    })
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

  }
})
