var util = require("../../../utils/util.js");
var api = require("../../../config/api.js");

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  submitOrder:function(){
    //结算付款
    //请求后台
    
    wx.navigateTo({
      url: '/pages/shopping/payResult/payResult',
    })
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取参数
    if (options.routeid.length > 0) {
      let that = this;
      that.setData({
        routeid: options.routeid,
        countday: options.countDay,
      });
      //获取到线路的id，根据id获取后台的线路信息
      util.request(api.RouteInfo, { routeId: options.routeid }, 'POST').then(function (res) {
        // console.log("数据：" + JSON.stringify(res.data));
        if (res.errno === 0) {//请求成功
          let data = res.data;
          console.log("测试时间：" + data.price)
          that.setData({
            price: data.price,
            startSite: data.startSite,//出发地点
            endSite: data.endSite,//到达地点
            driver: data.driver,//司机
            starttime: data.starttime,//出发时间
            arrivaltime: data.arrivaltime,//预计到达时间
            busNum: data.busNum,//车号
            phone: data.phone,//电话
            busId: data.busId,//牌照
            stations: data.stations,
            totalmoney: (that.data.countday * data.price).toFixed(2),
            // startRecruit: data.startRecruit,//运行周期开始
            // endsRecruit: data.endsRecruit,//运行结束

          });

        } else {
          console.log("失败" + res);
        }
      });
    } else {
      util.showErrorToast("加载失败");
    }
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