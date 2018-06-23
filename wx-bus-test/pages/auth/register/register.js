var api = require('../../../config/api.js');
var app = getApp();
Page({
  data: {
    username: '',//姓名
    password: '',//密码
    confirmPassword: '',//再次密码
    mobile: '',//电话号码
    drivenum:'',//驾驶证号码1
    citizenship:'',//身份证号码
    avaterFilePaths:'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png',//头像
    frontFilePaths:'/static/images/icon_add.png',//身份证正面
    contraryFilePaths:'/static/images/icon_add.png',//身份证反面
    pwsIcon:true,//密码正确
    mobileIcon:'',//电话号码
    drivenumIcon:'',//驾驶证号码
    citizenshipIcon:'',//身份证规约
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    // 页面渲染完成
    
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

  startRegister: function () {
    var that = this;

    if (this.data.password.length < 3 || this.data.username.length < 3) {
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

    if (this.data.mobile.length == 0 || this.data.drivenum.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '手机号和驾驶证号码不能为空',
        showCancel: false
      });
      return false;
    }
    if (this.data.citizenship.length == 0) {
      wx.showModal({
        title: '错误信息',
        content: '身份证号码不能为空',
        showCancel: false
      });
      return false;
    }
    if (this.data.frontFilePaths == '/static/images/icon_add.png' || this.data.contraryFilePaths=='/static/images/icon_add.png'){
      wx.showModal({
        title: '错误信息',
        content: '未上传身份证图片',
        showCancel: false
      });
      return false;
    }
    wx.request({
      url: api.AuthRegister,
      data: {
        username: that.data.username,
        password: that.data.password,
        mobile: that.data.mobile,
        coddrivenum: that.data.drivenum,
        citizenship: that.data.citizenship,
      },
      method: 'POST',
      header: {
        'content-type': 'application/json',
        
      },
      success: function (res) {
        if (res.errno == 0) {
          
        }
      }
    });
  },
  usernameChange: function (e) {

    this.setData({
      username: e.detail.value
    });
  },
  passwordChange: function (e) {
    let password = this.data.confirmPassword;
    if (password) {
      if (password == e.detail.value) {
        this.setData({
          password: e.detail.value,
          pwsIcon: true
        });
      } else {
        this.setData({
          password: e.detail.value,
          pwsIcon: false
        });
      }
    } else {
      this.setData({
        password: e.detail.value,
        // pwsIcon: false
      });
    }
  },
  confirmPasswordChange: function (e) {
    let password=this.data.password;
    if (password){
      if (password==e.detail.value){
        this.setData({
          confirmPassword: e.detail.value,
          pwsIcon:true
        });
      }else{
        this.setData({
          confirmPassword: e.detail.value,
          pwsIcon: false
        });
      }
    }else{
      this.setData({
        confirmPassword: e.detail.value,
        // pwsIcon: false
      });
    }
    
  },
  //测试手机号码是否正确
  testPhone: function (s) {
    if (s != null && s) {
      var length = s.length
      if (length = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(s)) {
        return true
      } else {
        return false
      }
    }
  },
  mobileChange: function (e) {
    if (this.testPhone(e.detail.value)){
      this.setData({
        mobile: e.detail.value,
        mobileIcon:true,
      });
    }else{
      this.setData({
        mobile: e.detail.value,
        mobileIcon: false,
      });
    }
    
  },
  drivenumChange: function (e) {

    this.setData({
      drivenum: e.detail.value,
      drivenumIcon:true,
    });
  },
  citizenshipChange:function(e){
    this.setData({
      citizenship: e.detail.value,
      citizenshipIcon:true,
    });
  },

  clearInput: function (e) {
    switch (e.currentTarget.id) {
      case 'clear-username':
        this.setData({
          username: ''
        });
        break;
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
  },//头像
  upAvater:function(){
    let that=this;
    wx.chooseImage({
      success: function(res) {
        that.setData({
          avaterFilePaths:res.tempFilePaths
        })
      },
    })
  },//身份证正面
  upFront:function(){
    let that = this;
    wx.chooseImage({
      success: function (res) {
        that.setData({
          frontFilePaths:res.tempFilePaths
        })
      },
    })
  },//身份证反面
  upContrary:function(){
    let that = this;
    wx.chooseImage({
      success: function (res) {
        that.setData({
          contraryFilePaths:res.tempFilePaths
        })
      },
    })
  }
})