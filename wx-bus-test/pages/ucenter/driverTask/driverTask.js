// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    endate: "",
    stdate: "",
    selectdate: '只看某天',//选定要查看的当天时间
    tasks: [
      { startsite: '二七', endsite: '火车站', starttime: '05-23-07:30', endtime: '05-23-09:30' },
      { startsite: '博颂路风华路', endsite: '省实验小学', starttime: '05-24-07:30', endtime: '05-24-09:30' },
      { startsite: '风华路', endsite: '博颂路', starttime: '05-25-07:30', endtime: '05-25-09:30' },
      { startsite: '河南职业学院', endsite: '郑东新区小学', starttime: '05-27-07:30', endtime: '05-27-09:30' },
      { startsite: '二七', endsite: '火车站', starttime: '05-23-07:30', endtime: '05-23-09:30' },
      { startsite: '博颂路风华路', endsite: '省实验小学', starttime: '05-24-07:30', endtime: '05-24-09:30' },
      { startsite: '风华路', endsite: '博颂路', starttime: '05-25-07:30', endtime: '05-25-09:30' },
      { startsite: '河南职业学院', endsite: '郑东新区小学', starttime: '05-27-07:30', endtime: '05-27-09:30' }
    ],
    countRout: 0,
    showType: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //加载请求后台数据,options跳转带来的参数
    wx.showLoading({
      title: '加载中...',
    });
    let that = this;
    wx.request({
      url: api.FindTask,
      data: {
        countRout: 0,
        pm: 0
      },
      header: {
        "Content-Type": "application/json",
        "Connect_Platform": "Weixin_Driver"
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
  //查询本月某天任务
  bindDateChange: function (e) {
    this.setData({
      selectdate: e.detail.value,//获取到要查询的当天日期交给后台处理
      showType: 1,
    })
  },
  /* 生命周期函数--监听页面初次渲染完成
  */
  onReady: function () {
    date = new Date()
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var time
    var time1
    if (month >= 10 && day >= 10)
      time = year + '-' + month + '-' + day;
    else if (month >= 10 && day < 10)
      time = year + '-' + month + '-0' + day;
    else if (month < 10 && day < 10) {
      time = year + '-0' + month + '-0' + day;
      time1 = year + '-0' + month + '-01';
    }

    else
      time = year + '-0' + month + '-' + day;
    this.setData({
      endate: time,
      stdate: time1,
    })
    //console.log("数据"+this.data.endate+this.data.stdate)
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  switchTab: function (event) {
    let showType = event.currentTarget.dataset.index;

    // this.getOrderList();
    if (showType == 0) {
      //请求所有数据
      this.setData({
        showType: showType,
        selectdate: '只看某天',//选定要查看的当天时间
      });

    } else {
      this.setData({
        showType: showType,
      });
    }
  },
})