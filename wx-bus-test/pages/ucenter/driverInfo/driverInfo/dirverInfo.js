var util = require("../../../../utils/util.js");
var tcity = require("../../../../utils/citys.js");
var api=require("../../../../config/api.js")
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    drivertype: 0,//司机类型
    bustype: '',//准驾车型
    licence: '',
    identityid: '',
    address: '',
    phone: '',
    score: 0,
    status: 0,
    avatar: "http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
  },
  onReady: function () {
  },
  onLoad: function () {
  },
  onShow: function () {

    let that = this;
    util.request(api.FindDriverInfo,
      {}, "POST").then(function (res) {
        if (res.errno == 0) {

          that.setData({
            name: res.data.driverinfo.driverName,
            identityid: res.data.driverinfo.driverCitizenship,
            phone: res.data.driverinfo.driverMobile,
            score: res.data.driverinfo.integral,//信誉积分
            statusid: res.data.driverinfo.driverStatus,//状态标识
            drivertypeid: res.data.driverinfo.driverMark,//类型标识（0 私人司机、 1 公司司机）
            bustype: res.data.driverinfo.drivingType,//准驾车型
            avatar: res.data.driverinfo.driverAvater,
            address: res.data.driverinfo.driverAddress,
            licence: res.data.driverinfo.driverLicence,
          })



        }

        else {
          wx.showModal({
            showCancel: false,
            title: '错误反馈',
            content: res.errmsg,
            success: function (res) {
              if (res.confirm) {
                wx.redirectTo({
                  url: '/pages/Driver-index/index',
                })
              }
            }
          })
        }
      })

  },
  onHide: function () {

  },
  changeinfo: function () {
    wx.navigateTo({
      url: '/pages/ucenter/driverInfo/changeInfo/changeInfo?phone=' + this.data.phone + "&address=" + this.data.address + "&avatar=" + this.data.avatar +"&name="+this.data.name,
    })
  },

  // //省市截取功能函数
  // getCaption: function (obj, a, str) {
    
  //   var index = obj.indexOf(str);
  //   obj = obj.substring(a, index + 1);
  //   return obj;

  // },
  // // 区或区级市截取
  // getDistrict: function (obj) {
  //   var indexofqu = obj.indexOf('区');
  //   var indexofshi = obj.indexOf('市');
  //   if (indexofqu != -1) {
  //     obj = obj.substring(0, indexofqu + 1);
  //     return obj;
  //   }
  //   else {
  //     obj = obj.substring(0, indexofshi + 1);
  //     return obj;
  //   }


  // },

  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }

})