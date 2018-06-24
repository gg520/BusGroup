
var util = require("../../../utils/util.js");
var api = require("../../../config/api.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {

    textMaxLen: 140,
    textareaNum: 0,
    images: [],
    phone: ''
  },
  //上传图片
  ChooseImage: function () {
    var that = this
    var Count = 3
    wx.chooseImage({
      count: Count,
      success: function (res) {
        console.log(res.tempFilePaths)
        var i = 0;
        var imageList = {};
        for (i; i <= res.tempFilePaths.lengh; i++) {

        }
        that.setData({
          imageList: res.tempFilePaths
        })
      }
    })
  },
  //字数动态显示
  textareaIn: function (e) {
    this.setData({
      textareaNum: e.detail.cursor
    })
  },
  bind_mobile: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },
  testPhone: function (s) {
    if (s != null && s) {
      var length = s.length
      if (length = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(s)) {
        return true
      } else {
        return false
      }
    }
  },
  //提交反馈信息
  commit: function (e) {
    //判断手机号合法性
    if(this.data.phone.length<=0){
      util.showWarningToast("手机号不能为空");
      return false;
    }
    if (!this.testPhone(this.data.phone)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号格式错误',
        showCancel: false
      });
      return false;
    }
    //手机号合法，请求后台
    util.request(api.FeedBack,
      {
        message: this.data.message
      }
      , "POST").then(function (res) {
        if (res.errno == 0) {
          util.showSuccessToast("感谢您的反馈")
        } else {
          util.showErrorToast("反馈失败")
        }
      });
  },
  //获取反馈消息
  changeMessage: function (e) {
    this.setData({
      message: e.detail.value,
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

  }
})