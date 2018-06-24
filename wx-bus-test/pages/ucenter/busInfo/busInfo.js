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
    this.setData({
      carnumber: '豫A1269x',
      enginenum: '27046',
      category: '小巴',
      owner: '公司',
      seatting: 15,
      model: 'Benz-Q7',
      registration_date: '2018-07-03',
      oppen_date: '2018-09-06',
      road: '嵩山路陇海路——紫荆山',
    })
    //验证登陆状态，请求后台数据
    if (app.globalData.hasLogin) {
      util.request(api.FindBusInfo,
        {
          token: wx.getStorageInfoSync('token')
        }, "POST").then(function (res) {
          if (res.errno == 0) {
            this.setData({
              carnumber: res.data.carnumber,//车牌号
              enginenum: res.data.enginenum,
              category: res.data.category,
              owner: res.data.categoryowner,
              seatting: res.data.seatting,
              model: res.data.model,
              registration_date: res.data.registration_date,
              oppen_date: res.data.oppen_date,
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