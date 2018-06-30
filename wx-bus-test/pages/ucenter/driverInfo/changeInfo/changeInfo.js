var util = require("../../../../utils/util.js");
var tcity = require("../../../../utils/citys.js");
var api= require("../../../../config/api.js")
var app = getApp();
var proviceData = new Array(), cityData = new Array(), districtData = new Array();
var proviceNum = 0, cityNum = 0, districtNum = 0;
Page({


  /**
   * 页面的初始数据
   */
  data: {
    provice: '',
    city: '',
    district: '',
    name: '',
    address: '',
    phone: '',
    avatar: "http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png",
    code:'',//验证码
    second:0,
    mobile:'',//设置第一次手机号
  },
  /**
   * 页面选址触发事件
   */
  choosearea: function () {
    this.setData({
      isShow: !this.data.isShow
    })
    //console.log(this.data.provice + this.data.city + this.data.district)
  },
  cancle: function () {
    this.setData({
      isShow: false,
      provice: '请',
      city: '选',
      district: '择'
    })

  },

  /**
   * 滑动事件
   */
  bindChange: function (e) {
    const current_value = e.detail.value;

  },


  onReady: function () {
  },
  onLoad: function (options) {

    var that = this;
    var data = that.data;
    // cityJS.init(that);
    that.setData({
      name: options.name,
      address: options.address,
      phone: options.phone,
      avatar: options.avatar,
      mobile: options.phone,
    })


  },
  onShow: function () {

  },
  onHide: function () {

  },
  //数据发往后台，请求更改
  changeinfo: function () {

    if (!this.checkphone(this.data.phone)) {
      wx.showModal({
        title: '错误信息',
        content: '手机号格式错误！',
        showCancel: false
      });
    }else if(this.data.code!=this.data.result){
      wx.showModal({
        title: '错误信息',
        content: '验证码错误！',
        showCancel: false
      });
    }
    else if (!this.checkaddress) {
      wx.showModal({
        title: '错误信息',
        content: '请填写地址！',
        showCancel: false
      });
    }
    else {
      util.request(api.ModifyDriverInfo,
        {
          // avaterurl: this.data.avatar,
          phone: this.data.phone,
          address: this.data.address,
        }, "POST").then(function (res) {
          if (res.errno == 0) {
            wx.showModal({
              showCancel: false,
              title: '提示',
              content: '信息修改成功！',
              success: function (res) {
                if (res.confirm) {
                  wx.redirectTo({
                    url: '/pages/ucenter/index/index',
                  })
                }
              }
            })

          }
          else {
            wx.showModal({
              showCancel: false,
              title: '错误反馈',
              content: '修改失败:' + res.errmsg,
              success: function (res) {
                if (res.confirm) {
                  wx.redirectTo({
                    url: '/pages/Driver-info/index/index',
                  })
                }
              }
            })
          }
        })

    }

  },
  //发送验证码
  sendCode: function () {
    if (this.data.send) {//已经发送
      return false;
    }
    let that = this;
    //验证手机号码
    if (this.data.mobile.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '手机号不能为空',
        showCancel: false
      });
      return false;
    }
    if (!(/^1[34578]\d{9}$/.test(this.data.mobile))) {
      wx.showModal({
        title: '错误信息',
        content: '手机号输入错误',
        showCancel: false
      });
      return false;
    }
    wx.showLoading({
      title: '加载中...',
    });
    wx.request({
      url: 'https://www.didu86.com/Clothes-manager-web/codenum',
      data: {
        tel: this.data.mobile,
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var result = res.data.code;
        console.log(result)

        that.setData({
          result: result
        })
        that.timer();
      },complete(){
        wx.hideLoading();
      }
    })
  },
  //获取手机号
  phoneinput: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },
  //获取地址
  addressinput: function (e) {
    //地址拼接成字符串
  
    var address = e.detail.value;
   this.setData({
     address
   })
    
  },
  onPullDownRefresh: function () {

  },
  //check the phone number
  checkphone: function (s) {
    if (s != null && s) {
      var length = s.length
      if (length = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(s)) {
        return true
      } else {
        return false
      }
    }
  },
  //check the address 
  checkaddress: function (a) {
    if (a == '请选择') {
      return false;
    }
    else
      return true;
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },
  bindCodeInput:function(e){
    this.setData({
      code: e.detail.value
    })
  },
  timer: function () {

    var second = 60;
    var interval = setInterval(function () {
      second--;
      this.setData({
        second: second,
        send: true,
      });

      if (second < 0) {
        clearInterval(interval);

        this.setData({
          second: second,
          send: false,
        });
      }
    }.bind(this), 1000);
  },

})