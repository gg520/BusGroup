// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

    tasks:
    {
      startsite: '',//二七
      endsite: '',//火车站
      starttime: '',//05-23-07:30
      endtime: '',//05-23-09:30
      totalnum: '',//05-23-09:30
      busnum: '',//豫A095X
      site: [
        // { siteid: '001', sitename: '文化路三全路', reachtime: '7:00' },
        // { siteid: '002', sitename: '三全路瞿东路', reachtime: '7:20' },
        // { siteid: '003', sitename: '郑州七中新校', reachtime: '7:40' },
        // { siteid: '004', sitename: '京广路人民路', reachtime: '8:00' },
      ],
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //加载请求后台数据,
    //console.log(this.data.tasks.site.length)
    wx.showLoading({
      title: '加载中...',
    });
    let that = this;
    util.request(api.FindCurrentRoad, {},"POST").then(function(res){
      if (res.errno === 0) {
        console.log("设置：" + res.data)
        that.setData({
          routes: res.data.routes,
          countRout: that.countRout + 10//这个值由后台确定
        })
      }
    })
    // wx.request({
    //   url: api.FindCurrentRoad,
    //   data: {
    //   },
    //   method: '',
    //   success: function (res) {
    //     console.log("请求数据：" + res.data)
        
    //   },
    //   fail: function (res) {
    //     util.showErrorToast("加载失败");
    //   },
    //   complete: function () {
    //     console.log(that.data)
    //     wx.hideLoading();
    //   }
    // })
  },
  /* 生命周期函数--监听页面初次渲染完成
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


  /**
   * 页面上拉触底事件的处理函数
   */

  showview: function () {
    this.setData({
      flag: !this.data.flag
    })
    console.log(this.data.flag)
  },

  /**
   * 用户点击右上角分享
   */
})
