// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag: false,
    tasks:
    {
      startsite: '二七',
      endsite: '火车站',
      starttime: '05-23-07:30',
      endtime: '05-23-09:30',
      totalnum: 10,
      remark: '请司机注意各个站点的规定到达时间',
      busnum: '豫A095X',
      site: [
        { siteid: '001', sitename: '文化路三全路', offnum: 2, reachtime: '7:00' },
        { siteid: '002', sitename: '三全路瞿东路', offnum: 2, reachtime: '7:20' },
        { siteid: '003', sitename: '郑州七中新校', offnum: 6, reachtime: '7:40' },
        { siteid: '004', sitename: '京广路人民路', offnum: 2, reachtime: '8:00' },
      ],
    },
    countRout: 0,
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
    wx.request({
      url: api.RouteRun,
      data: {
        countRout: 0,
        pm: 0
      },
      header: {
        "Content-Type": "application/json",
        "Connect_Platform": "Weixin_Passenger"
      },
      method: 'POST',
      success: function (res) {
        console.log("请求数据：" + res.data)
        if (res.data.errno === 0) {
          console.log("设置：" + res.data.data.routes)
          that.setData({
            routes: res.data.data.routes,
            countRout: that.countRout + 10//这个值由后台确定
          })
        }
      },
      fail: function (res) {
        util.showErrorToast("加载失败");
      },
      complete: function () {
        console.log(that.data)
        wx.hideLoading();
      }
    })
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
  onPullDownRefresh: function () {
    //重新加载数据
    let that = this;
    wx.showLoading({
      title: '加载中...',
    })
    wx.request({
      url: api.RouteRun,
      data: {
        countRout: 0,
        pm: 0,
      },
      method: 'POST',
      success: function (res) {
        if (res.data.errno === 0) {
          that.setData({
            routes: res.data.data.routes,
            countRout: that.countRout + 10,//一次加10
            isAm: false,
            isPm: false
          })
        }
      },
      fail: function (res) {
        util.showErrorToast("加载失败");
      },
      complete: function () {
        wx.hideLoading();
      }
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {//存在问题
    //检测数量，以及已经显示的页码，进行拼接、将下面的数据添加到新的里面

    let that = this;
    wx.showLoading({
      title: '加载中...',
    })
    wx.request({
      url: api.RouteRun,
      data: {
        countRout: that.data.countRout,//翻页
        pm: that.data.pm,
      },
      method: 'POST',
      success: function (res) {
        if (res.data.errno === 0) {
          // 数据需要处理一下，做一个拼接
          that.setData({
            routes: res.data.data.routes,
            countRout: that.countRout + 10
          })
        }
      },
      fail: function (res) {
        util.showErrorToast("加载失败");
      },
      complete: function () {
        wx.hideLoading();
      }
    })
  },
  showview: function () {
    this.setData({
      flag: !this.data.flag
    })
    console.log(this.data.flag)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})