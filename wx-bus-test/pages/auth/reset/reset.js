var api = require('../../../config/api.js');
var app = getApp();
var send;
Page({
  data: {
    mobile: '',
    code: '',
    password: '',
    confirmPassword: '',
    second:'',
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    // 页面渲染完成
    this.setData({
      second: 0,
      alreadySend: false,
      send: false
    })
  },
  onReady: function () {

  },
  onShow: function () {
    // 页面显示

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },
  sendCode: function () {
    if (this.data.send){
      return false;
    }
    let that=this;
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
      }
    })
    
  },
  startReset: function () {
    var that = this;

    if (this.data.mobile.length == 0 || this.data.code.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '手机号和验证码不能为空',
        showCancel: false
      });
      return false;
    }
    if(this.data.code!=this.data.result){
      wx.showModal({
        title: '错误信息',
        content: '验证码输入错误',
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
    if (this.data.password.length < 3) {
      wx.showModal({
        title: '错误信息',
        content: '用户名和密码不得少于3位',
        showCancel: false
      });
      return false;
    }

    if (this.data.password != this.data.confirmPassword) {
      wx.showModal({
        title: '错误信息',
        content: '确认密码不一致',
        showCancel: false
      });
      return false;
    }
    
    wx.request({
      url: api.AuthReset,
      data: {
        mobile: that.data.mobile,
        code: that.data.code,
        password: that.data.password
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        if (res.data.errno == 0) {
          wx.navigateBack();
        }
        else {
          wx.showModal({
            title: '密码重置失败',
            content: res.data.errmsg,
            showCancel: false
          });
        }
      }
    });
  },
  bindPasswordInput: function (e) {

    this.setData({
      password: e.detail.value
    });
  },
  bindConfirmPasswordInput: function (e) {

    this.setData({
      confirmPassword: e.detail.value
    });
  },
  bindMobileInput: function (e) {

    this.setData({
      mobile: e.detail.value
    });
  },
  bindCodeInput: function (e) {

    this.setData({
      code: e.detail.value
    });
  },
  clearInput: function (e) {
    switch (e.currentTarget.id) {
      case 'clear-password':
        this.setData({
          password: ''
        });
        break;
      case 'clear-confirm-password':
        this.setData({
          confirmPassword: ''
        });
        break;
      case 'clear-mobile':
        this.setData({
          mobile: ''
        });
        break;
      case 'clear-code':
        this.setData({
          code: ''
        });
        break;
    }
  },
  ceshi(){
    wx.request({
      url: 'https://www.didu86.com/Clothes-manager-web/codenum',
      data: {
        tel: '13592573327',
      },
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        var result = res.data.code;
        console.log(result)

      }
    })
  },
  timer: function () {

    var second = 60;
    var interval = setInterval(function () {
      second--;
      this.setData({
        second: second,
        send:true,
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