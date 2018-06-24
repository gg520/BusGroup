var util = require("../../../../utils/util.js");
var tcity = require("../../../../utils/citys.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {
      name: '',
      licence: '',
      identityid: '',
      address: '',
      phone: '',
      score: 0,
      status: '',
      avatar: "http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
    }
  },
  onReady: function () {
  },
  onLoad: function () {
    /*var that = this
    wx.getStorage({
      key: 'userInfo',
      success: function (res) {
        that.setData({
          userInfo: res.data,
          gender: res.data.gender,
          'province': res.data.province,
          'city': res.data.city,
          'county': res.data.county
        })
      },
      fail: function (res) {
        app.login();
      }
    })
    //console.log(that.userInfo);
    tcity.init(that);
    var cityData = that.data.cityData;


    const provinces = [];
    const citys = [];
    const countys = [];

    for (let i = 0; i < cityData.length; i++) {
      provinces.push(cityData[i].name);
    }
    console.log('省份完成');
    for (let i = 0; i < cityData[0].sub.length; i++) {
      citys.push(cityData[0].sub[i].name)
    }
    console.log('city完成');
    for (let i = 0; i < cityData[0].sub[0].sub.length; i++) {
      countys.push(cityData[0].sub[0].sub[i].name)
    }

    that.setData({
      'provinces': provinces,
      'citys': citys,
      'countys': countys
    })
    console.log('初始化完成');*/
  },
  onShow: function () {
    this.setData({
      name: 'Chens',
      licence: '20147X',
      identityid: '41152119940901795x',
      address: '双湖经济开发区淮河路1号',
      phone: '18538241560',
      score: 89,
      status: '停用',
      avatar: "http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
    })
  },
  onHide: function () {

  },
  changeinfo: function () {
    wx.navigateTo({
      url: '../changeInfo/changeInfo',
    })
  },
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }

})