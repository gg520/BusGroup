var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app=getApp();
Page({
  data: {
    orderList: [
      {
        routeStatus: "1",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay:'20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点', 
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId:'1',//线路ID
      },
      {
        routeStatus: "1",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay: '20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点',
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId: '1',//线路ID
      },
      {
        routeStatus: "2",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay: '20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点',
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId: '1',//线路ID
      },
      {
        routeStatus: "1",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay: '20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点',
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId: '1',//线路ID
      },
      {
        routeStatus: "2",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay: '20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点',
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId: '1',//线路ID
      },
      {
        routeStatus: "2",//线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
        orderNumber: '订单编号',
        reserveDay: '20',//订购的天数
        startSite: "起始地点",
        endSite: '到达地点',
        price: "10", //单价
        startTime: "07:00",//出发时间
        payMoney: "200",//总价 reserveDay*price
        routeId: '1',//线路ID
      },
    ],
    showType: 0
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    // this.getOrderList();
  },
  getOrderList() {
    let that = this;//api.OrderList
    var showType = that.data.showType;

    console.log(showType)
    util.request(api.OrderList, { showType: showType},"POST").then(function (res) {
      console.log(res.data);
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          orderList: res.data
        });
      }
    });
  },
  switchTab: function (event) {
    let showType = event.currentTarget.dataset.index;
    this.setData({
      showType: showType
    });
    this.getOrderList();
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    this.getOrderList();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})