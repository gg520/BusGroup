// pages/route/inquiryRoute/inquiryRoute.js

var api=require("../../../config/api.js");
var util=require("../../../utils/util.js");

var bmap=require("../../../libs/bmap-wx.js");

var routeInquiryPeriod=7;
var app=getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    startsite: '',//出发地点   中原工学院南区
    startCoor:'',
    endCoor:'',
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
    
    if (this.data.startsite.length <= 0||this.data.endsite <= 0){
      util.showErrorToast("地点不能为空");
      return false;
    }

    if(this.data.startCoor.length<=0 || this.data.endCoor.length<=0 ){
      util.showErrorToast("地点信息错误");
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
    wx.showLoading({
      title: '加载中...',
    });
    //将信息发送到后台
    console.log(this.data.startsite)
    util.request(
      api.RouteInquiry,
      {
        startsite: this.data.startsite,
        endsite: this.data.endsite,
        starttime: this.data.starttime,
        endtime: this.data.endtime,
        startCoor:this.data.startCoor,
        endCoor:this.data.endCoor,
        periodStart: this.data.periodStart,
        periodEnd: this.data.periodEnd,
        massage: this.data.massage,
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
    
    if (e.detail.value.length==0){
      this.setData({
        startsite: e.detail.value,
        sugStartData:'',
      })
      return false;
    }
    this.setData({
      startsite: e.detail.value,
    })
    let that = this;

    //将地图搜索地址获取
    var BMap = new bmap.BMapWX({
      ak: 'N0KvpqkorE9GPG467fqlThoTX6QLFjGH'
    });
    var fail = function (data) {
      console.log(data)
    };
    var success = function (data) {
      console.log(data.result)
      that.setData({
        sugStartData: data.result
      });
    }
    BMap.suggestion({
      query: e.detail.value,
      region: this.data.city,
      city_limit: true,
      fail: fail,
      success: success
    });
  },
  goStartSite:function(e){
    //获取坐标，获取数据
    console.log(e.currentTarget.dataset.name)
    // console.log( app.getCityName())
    this.setData({
      startsite: e.currentTarget.dataset.name,
      startCoor: e.currentTarget.dataset.lat + "," + e.currentTarget.dataset.lng,
      sugStartData:'',
    })
  },

  bindEndsiteInput:function(e){
    // console.log("地点2")
   
    if (e.detail.value.length == 0) {
      this.setData({
        endsite: e.detail.value,
        sugEndData: '',
      })
      return false;
    }
    this.setData({
      endsite: e.detail.value,
    })
    let that = this;

    //将地图搜索地址获取
    var BMap = new bmap.BMapWX({
      ak: 'N0KvpqkorE9GPG467fqlThoTX6QLFjGH'
    });
    var fail = function (data) {
      console.log(data)
    };
    var success = function (data) {
      console.log(data.result)
      that.setData({
        sugEndData: data.result
      });
    }
    BMap.suggestion({
      query: e.detail.value,
      region: this.data.city,
      city_limit: true,
      fail: fail,
      success: success
    });
  },
  goEndSite:function(e){
    console.log(e.currentTarget.dataset.name)
    this.setData({
      endsite: e.currentTarget.dataset.name,
      endCoor: e.currentTarget.dataset.lat + "," + e.currentTarget.dataset.lng,
      sugEndData: '',
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
 bindMessageInput:function(e){
    this.setData({
      massage: e.detail.value
    })
  },
  clearInput: function (e) {
    console.log(e.currentTarget.id)
    switch (e.currentTarget.id) {
      case 'clear-startsite':
        this.setData({
          startsite: '',
          sugStartData:'',
        });
        break;
      case 'clear-endsite':
        this.setData({
          endsite: ''
        });
        break;
      case 'clear-massage':
        this.setData({
          massage: ''
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
    let that=this;
    var BMap = new bmap.BMapWX({//创建
      ak: 'N0KvpqkorE9GPG467fqlThoTX6QLFjGH'
    });
    var fail = function (data) {
      console.log(data);
    };
    var city='';
    var success = function (data) {
      
      wxMarkerData = data.wxMarkerData;
      
      city = data.originalData.result.addressComponent.city;
      console.log("城市信息：" + city.substring(0, city.length - 1))
      
      that.setData({
        city: city.substring(0, city.length - 1)
      })
    }
    // 发起regeocoding检索请求 
    BMap.regeocoding({
      fail: fail,
      success: success,
     

    });
    
    
  
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