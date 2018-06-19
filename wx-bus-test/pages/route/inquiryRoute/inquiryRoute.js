// pages/route/inquiryRoute/inquiryRoute.js

var api=require("../../../config/api.js");
var util=require("../../../utils/util.js");
var routeInquiryPeriod=7;
var app=getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    startsite: '',//出发地点   中原工学院南区
    endsite: '',//结束地点  郑州火车站
    period: '',//招募周期  8
    starttimename: "出发时间",//
    starttime: '',//08:00
    endtime: '',//17:00
    endtimename: '返程时间',//
    username: '',//您的姓名  郭苏州
    mobile: '',//您的真实电话 //13592573327
    startChioceIcon:'/static/images/icon_time.png',
    endChioceIcon:'/static/images/icon_time.png',
    startDefault:'',//默认线路运行时间，设定线路招募的周期，统一设定由后台设定
    endsDefault:'',//结束的最晚时间,默认一个月，可以适当的减少

    periodStart:'',
    periodEnd:'',
  },
  start:function(){
    //正则
    //手机
    var mobile=new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
    //固定电话
    var phone = new RegExp("^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$");
    if (this.data.startsite.length <= 0||this.data.endsite <= 0){
      util.showErrorToast("地点不能为空");
      return false;
    }
    if (this.data.period.length<=0){
      util.showErrorToast("周期不能为空");
      return false;
    }
    try{
      parseInt(this.data.period);
    }
    catch(e){
      util.showErrorToast("周期必须是整数");
      return false;
    }
    if (this.data.starttime.length <= 0 || this.data.endtime.length <= 0){
      util.showErrorToast("未选择时间");
      return false;
    }
    if (this.data.periodStart.length <= 0 || this.data.periodEnd <= 0){
      util.showErrorToast("未选择日期");
      return false;
    }
    if(this.data.username.length <= 0 || this.data.mobile.length <= 0){
    // if (this.data.mobile.length <= 0){
      util.showErrorToast("未输入姓名或电话");
      return false;
    } else if (phone.test(this.data.mobile) || mobile.test(this.data.mobile.length)){
      util.showErrorToast("电话号码错误");
      return false;
    }
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
        periodStart: this.data.periodStart,
        periodEnd: this.data.periodEnd,
      },'POST').then(function(res){
        console.log(res);
        if (res.errno === 0){
          //成功
          util.showSuccessToast("提交成功");
          wx.navigateBack({//返回上页
            delta: 1
          })
        } 
      })
    wx.hideLoading();
  },

  bindStartsiteInput:function(e){
    // console.log("地点1")
    this.setData({
      startsite: e.detail.value
    })
  },
  bindEndsiteInput:function(e){
    // console.log("地点2")
    this.setData({
      endsite: e.detail.value
    })
  },
  bindUsernameInput:function(e){
    this.setData({
      username: e.detail.value
    })
  },
  bindPeriodStartChange:function(e){
    //设置开始时间
    //根据开始时间设置默认时间
    let start = e.detail.value;
    var sd=start.split("-");
    ++sd[1];
    if(sd[1]>12){
      ++sd[0];
      sd[1]=01;

    }
    this.setData({
      periodStart: start,
      endsDefault: sd[0]+"-"+sd[1]+"-"+sd[2]
    })
  },
  bindPeriodEndChange:function(e){
    this.setData({
      periodEnd: e.detail.value
    })
  },
  bindMobileInput:function(e){
    
    this.setData({
      mobile: e.detail.value
    })
  }, bindMessageInput:function(e){
    this.setData({
      message: e.detail.value
    })
  },
  clearInput: function (e) {
    console.log(e.currentTarget.id)
    switch (e.currentTarget.id) {
      case 'clear-startsite':
        this.setData({
          startsite: ''
        });
        break;
      case 'clear-endsite':
        this.setData({
          endsite: ''
        });
        break;
      case 'clear-message':
        this.setData({
          message: ''
        });
        break;
      case 'clear-username':
        this.setData({
          username: ''
        });
        break;
      case 'clear-mobile':
        this.setData({
          mobile: ''
        });
        break;
      case 'clear-period':
        this.setData({
          period: ''
        });
        break;
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取后台的招募 周期，即是招募时间
    wx.showLoading({
      title: '加载中...',
    });
    //将信息发送到后台
    console.log(this.data.startsite)
    // util.request(
    //   api.RouteInquiryPeriod,
    //   {}, 'POST').then(function (res) {
    //     console.log(res);
    //     if (res.errno === 0) {
    //       //获取招募周期
    //       routeInquiryPeriod=res.data;
    //     }
    // })
    wx.hideLoading();
    //初始化线路招募周期
    this.initDefault();
  },
  //初始化线路招募周期
  initDefault:function(){
    var date = new Date();
    var day = date.getDate();//获取当前时间
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    var maxDay = new Date(year, month, 0).getDate();//获取当月最大的天数
    day = routeInquiryPeriod + day;
    if (day > maxDay){
      if(month===12){
        ++year;
        month=01;
        day=day-maxDay;
      }else{
        ++month;
        day = day - maxDay;
      }
    }
    
    var dayStr = year + "-" + month + '-' + day;
    ++month;
    if (month>12){
      ++year;
      month=01;
    }
    var dayends = year + "-" + month + '-' + day;
    console.log(dayStr)
    this.setData({
      startDefault: dayStr,
      endsDefault:dayends,
    })
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
  onPullDownRefresh: function () {//刷新界面
    this.setData({
      startsite: '中原工学院南区',//出发地点
      endsite: '郑州火车站',//结束地点
      period: '8',//招募周期
      starttimename: "出发时间",
      starttime: '08:00',
      endtime: '17:00',
      endtimename: '返程时间',
      username: '郭苏州',//您的姓名
      mobile: '13592573327',//您的真实电话
      startChioceIcon: '/static/images/icon_time.png',
      endChioceIcon: '/static/images/icon_time.png',
    })
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
  bindStartTimeChange: function (e) {

    this.setData({
      starttimename: '出发时间：'+e.detail.value,
      starttime: e.detail.value,
      
    })
  },
  bindEndTimeChange: function (e) {
    this.setData({
      endtimename: '返程时间：' + e.detail.value,
      endtime: e.detail.value,
    })
  },
})