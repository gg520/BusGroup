// pages/qrcode/qrcode.js
var util = require("../../utils/util.js");
var api = require("../../config/api.js");
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //生成的二维码图片的临时地址
    imgpath:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that=this;
    //第一步：获取是否已经实名验证，如果验证过了生成，如果没有
    wx.downloadFile({
      url: api.GetQRcode,
      header: {
        "X-Bus-Token": wx.getStorageSync("token"),
        "Connect_Platform": "Weixin_Passenger"
      },
      success: function (res) {
        // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
        if (res.statusCode === 200) {
          //成功
          console.log(res.tempFilePath);
          that.setData({
            imgpath:res.tempFilePath,
          });
        }else{
          //错误数据
        }
      }
      
    })
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
  
  }
})