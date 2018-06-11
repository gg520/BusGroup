
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    // dateValue: '请选择车辆上路时间',
    // array: [
    //   '京', '粤', '沪', '津', '湘', '鄂', '豫', '冀','渝',
    //   '云','辽','黑','皖','鲁','新','苏','浙','赣','鄂',
    //   '桂','甘','晋','蒙','陕','吉','闽','贵','粤','青',
    //   '藏','川','宁','琼'],
    // index: 0,
    userInfo:'',
    gender:['男','女'],
    mobileIcon:'',//手机号码是否正确
    brithday:'2018-04-10'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取个人信息
    //判断司机还是乘客
    var user = wx.getStorageSync("user");
    let userInfo = wx.getStorageSync("userInfo");
    console.log(userInfo);
    if (user!=null&&user === 'Weixin_Driver') {//司机
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isDirver: true
      });
    } else if (user != null &&user === 'Weixin_Passenger') {//乘客
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isPassenger: true
      });
    }else{//其他为错误信息

    }
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
    let _self=this;
    var isPhone = _self.testPhone(_self.data.userInfo.mobile)
    console.log("isPhone" + isPhone)
    if (isPhone) {
      console.log(isPhone)
      _self.setData({//设置正确性
        mobileIcon: 'true'
      })
    } else {
      _self.setData({
        mobileIcon: 'false'
      })
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  datePickerBindchange: function (e) {
    let userInfo = this.data.userInfo;
    userInfo.birthday=e.detail.value
    this.setData({
      userInfo:userInfo
    })
    console.log(this.data.userInfo)
    console.log(this.data.userInfo.birthday.length)
  },
  genderChange:function(e){
    let userInfo = this.data.userInfo;
    console.log(e.detail.value)
    userInfo.gender = e.detail.value=='0'?1:2
    this.setData({
      userInfo: userInfo
    })
    console.log(this.data.userInfo)
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
  mobileChange:function(e){
    
    var _self = this
    let userInfo = this.data.userInfo;
    userInfo.mobile = e.detail.value
    _self.setData({ userInfo: userInfo })
    console.log(_self.data.userInfo)
    var isPhone = _self.testPhone(e.detail.value)
    console.log("isPhone"+isPhone)
    if (isPhone) {
      console.log(isPhone)
      _self.setData({//设置正确性
        mobileIcon: 'true'
      })
    } else {
      _self.setData({
        mobileIcon: 'false'
      })
    }
  },
  changeInfo:function(){
    //获取信息
    wx.showLoading({
      title: '加载中...',
    });
    //将信息发送到后台
    console.log(this.data.startsite)
    util.request(
      api.RouteInquiry,
      {
        username: this.data.username,
        phone: this.data.mobile,
        startsite: this.data.startsite,
        endsite: this.data.endsite,
        starttime: this.data.starttime,
        endtime: this.data.endtime,
        period: this.data.period,
      }, 'POST').then(function (res) {
        console.log(res);
        if (res.errno === 0) {
          //成功
          util.showSuccessToast("提交成功");
          wx.navigateBack({//返回上页
            delta: 1
          })
        }
      })
    wx.hideLoading();
  }
})