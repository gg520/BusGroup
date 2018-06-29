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
      // { startsite: '二七', endsite: '火车站', starttime: '05-23-07:30', endtime: '05-23-09:30' },
      // { startsite: '博颂路风华路', endsite: '省实验小学', starttime: '05-24-07:30', endtime: '05-24-09:30' },
      // { startsite: '风华路', endsite: '博颂路', starttime: '05-25-07:30', endtime: '05-25-09:30' },
      // { startsite: '河南职业学院', endsite: '郑东新区小学', starttime: '05-27-07:30', endtime: '05-27-09:30' },
      // { startsite: '二七', endsite: '火车站', starttime: '05-23-07:30', endtime: '05-23-09:30' },
      // { startsite: '博颂路风华路', endsite: '省实验小学', starttime: '05-24-07:30', endtime: '05-24-09:30' },
      // { startsite: '风华路', endsite: '博颂路', starttime: '05-25-07:30', endtime: '05-25-09:30' },
      // { startsite: '河南职业学院', endsite: '郑东新区小学', starttime: '05-27-07:30', endtime: '05-27-09:30' }
    ],
    countRout: 0,
    showType: 0,
  },

  getAllStask(){
    let that=this;
    wx.showLoading({
      title: '加载中...',
    });
    util.request(api.FindTask, {}, "POST").then(function (res) {
      wx.hideLoading();
      // console.log("请求数据：" + res.data)
      if (res.errno === 0) {


        var tasks = [];
        var datas = res.data;
        // console.log("测试数据：" + datas[0].creatTime)
        for (let i = 0; i < datas.length; i++) {
          var task = { creatTime: '', endSite: '', endTime: '', startSite: '', startTime: '' };
          task.creatTime = datas[i].creatTime.substring(0, datas[i].creatTime.indexOf('T'));
          task.endSite = datas[i].route.endSite;
          task.endTime = datas[i].route.endTime;
          task.startSite = datas[i].route.startSite;
          task.startTime = datas[i].route.startTime;
          tasks.push(task);
        }

        that.setData({
          tasks: tasks,

        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //加载请求后台数据,options跳转带来的参数
    this.getAllStask();
    
    
  },
  //查询本月某天任务
  bindDateChange: function (e) {
    let that=this;
    console.log("修改日期")
    wx.showLoading({
      title: '加载中...',
    });
    this.setData({
      selectdate: e.detail.value,//获取到要查询的当天日期交给后台处理
      showType: 1,
    })
    util.request(api.FindTaskByTime, { time:e.detail.value}, "POST").then(function (res) {
      wx.hideLoading();
      // console.log("请求数据：" + res.data)
      if (res.errno === 0) {

        var tasks = [];
        var datas = res.data;
        // console.log("测试数据：" + datas[0].creatTime)
        for (let i = 0; i < datas.length; i++) {
          var task = { creatTime: '', endSite: '', endTime: '', startSite: '', startTime: '' };
          task.creatTime = datas[i].creatTime.substring(0, datas[i].creatTime.indexOf('T'));
          task.endSite = datas[i].route.endSite;
          task.endTime = datas[i].route.endTime;
          task.startSite = datas[i].route.startSite;
          task.startTime = datas[i].route.startTime;
          tasks.push(task);
        }

        that.setData({
          tasks: tasks,

        })
      }
    })
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
  switchTab: function (event) {
    let showType = event.currentTarget.dataset.index;

    // this.getOrderList();
    if (showType == 0) {
      //请求所有数据
      this.getAllStask();
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