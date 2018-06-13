var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();
Page({
  data: {
    orderList: [
      { routeStatus: "1", routeId: "1", createDate: '2018-05-04', startSite: "鬼地方个水费地方", endSite: '的鬼地方', actualPrice: "23", startTime: "07:00" },
      { routeStatus: "2", routeId: "1", createDate: '2018-05-03', startSite: "鬼地方个水电地方", endSite: '的鬼地方', actualPrice: "23", startTime: "07:00" },
      { routeStatus: "2", routeId: "1", createDate: '2018-05-06', startSite: "鬼地方个水电地方", endSite: '的鬼地方', actualPrice: "23", startTime: "07:00" },
      { routeStatus: "1", routeId: "1",createDate: '2018-05-07', startSite: "鬼地方个水费地方", endSite: '的鬼地方', actualPrice: "23", startTime: "07:00" },
      { routeStatus: "1", routeId: "1", createDate: '2018-05-09', startSite: "鬼地方个水费地方", endSite: '的鬼地方', actualPrice: "23", startTime: "07:00" },
    ],
    showType: 0
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.getOrderList();
  },
  getOrderList() {
    let that = this;
    util.request(api.OrderList, { showType: that.data.showType }).then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          orderList: res.data.data
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
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})