var util = require("../../../utils/util.js");
var api = require("../../../config/api.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    carnumber: '',//车牌号
    enginenum: '',
    category: '',//车辆类型
    registration_date: '',//注册日期
    oppen_date: '',//发证日期
    owner: '',//所有人
    seatting: '',//座位数
    model: '',//品牌型号
    road: '',//当前运行线路

    imgUrl: '/static/images/ic_mu_index1.png'
  },
  onReady: function () {
  },
  onShow: function () {
    let that=this;
    //验证登陆状态，请求后台数据
    if (app.globalData.hasLogin) {
      util.request(api.FindBusInfo,
        {}, "POST").then(function (res) {
          if (res.errno == 0) {
            that.setData({
              carnumber: res.data.busId,//车牌号
              enginenum: res.data.engineNum,
              category: res.data.busType,
              owner: res.data.characters,
              seatting: res.data.seating,
              model: res.data.model,
              // datas[i].creatTime.substring(0, datas[i].creatTime.indexOf('T'));
              registration_date: res.data.registrationDate.substring(0, res.data.registrationDate.indexOf('T')),
              oppen_date: res.data.oppenDate.substring(0, res.data.oppenDate.indexOf('T')),
              road: res.data.road,
            })
          } else {
            util.showErrorToast(res.errmsg)
          }
        });
    }
  },
  onHide: function () {

  },
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }
})
