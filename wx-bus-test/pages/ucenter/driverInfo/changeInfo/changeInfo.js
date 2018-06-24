var util = require("../../../../utils/util.js");
var tcity = require("../../../../utils/citys.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    provinces: [],
    province: "河南省",
    citys: [],
    city: "郑州市",
    countys: [],
    county: '新郑市',
    value: [0, 0, 0],
    values: [0, 0, 0],
    condition: false,
    userInfo: {
      name: '',
      address: '',
      phone: '',
      avatar: "http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
    }
  },
  bindChange: function (e) {
    //console.log(e);
    var val = e.detail.value
    var t = this.data.values;
    var cityData = this.data.cityData;
    if (val[0] != t[0]) {
      console.log('province no ');
      const citys = [];
      const countys = [];

      for (let i = 0; i < cityData[val[0]].sub.length; i++) {
        citys.push(cityData[val[0]].sub[i].name)
        //console.log("aaaaabbbb"+citys[i])

      }
      for (let i = 0; i < cityData[val[0]].sub[0].sub.length; i++) {
        countys.push(cityData[val[0]].sub[0].sub[i].name)
        //console.log("aaaaabbbb" + countys[i])
      }

      this.setData({
        province: this.data.provinces[val[0]],
        //city: cityData[val[0]].sub[0].name,
        citys: citys,
        // county: cityData[val[0]].sub[0].sub[0].name,
        countys: countys,
        values: val,
        value: [val[0], 0, 0]
      })




      return;
    }
    if (val[1] != t[1]) {
      console.log('city no');
      const countys = [];

      for (let i = 0; i < cityData[val[0]].sub[val[1]].sub.length; i++) {
        countys.push(cityData[val[0]].sub[val[1]].sub[i].name)
      }

      this.setData({
        city: this.data.citys[val[1]],
        //county: cityData[val[0]].sub[val[1]].sub[0].name,
        countys: countys,
        values: val,
        value: [val[0], val[1], 0]
      })

      return;
    }
    if (val[2] != t[2]) {
      console.log('county no');
      this.setData({
        county: this.data.countys[val[2]],
        values: val
      })
      return;
    }


  },
  open: function () {
    this.setData({
      condition: !this.data.condition
    })
  },
  open1: function () {
    this.setData({
      'province': '河南省',
      'city': '郑州市',
      'county': '新郑市',
      condition: !this.data.condition
    })
  },
  onReady: function () {
  },
  onLoad: function () {
    console.log("onLoad");
    var that = this;

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
      'countys': countys,
    })
    console.log('初始化完成');
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
    /*console.log("aaaaaa" + this.data.province);
    console.log("bbbb" + this.data.city);
    console.log("ccccc" + this.data.county)*/
    wx.showToast({
      title: '修改成功',
      icon: 'success',
      duration: 1500
    })
    /*wx.redirectTo({
      url: '/pages/Driver-info/index/index',
    })*/
  },
  sendCode: function () {
    wx.showModal({
      title: '注意',
      content: '由于目前不支持手机短信发送，因此验证码任意值都可以',
      showCancel: false
    });
  },
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  }
})