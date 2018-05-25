// pages/route/routeInfo/routeInfo.js

var util=require("../../../utils/util.js");
var api=require("../../../config/api.js");

var app = getApp();

//日历
let chooseYear = null;
let chooseMonth = null;
let chooseDay=null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rInfo:
    {
      starttime:'',//出发时间
      arrivaltime:'',//预计到达时间
      distance:'',//距离
      price:'',//单价
      monthNum:'',//每月发车次数
      driver:'',//司机
      phone:'',//电话
      busNum:'',//车牌
      stations:[
        '','',''
      ],//车站的站点，有顺序
    },
    countday:0,//选择的天数
    routeid:'',//线路的id
    totalmoney:0.00,//总钱数，即为支付金额
    // 日历
    hasEmptyGrid: false,
    showPicker: false,
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.routeid);
    if (options.routeid.length > 0){
      let that=this;
      that.setData({
        routeid: options.routeid
      });
      //获取到线路的id，根据id获取后台的线路信息
      util.request(api.RouteInfo, { routeid: options.routeid},'POST').then(function(res){
        if(res.errno === 0){//请求成功
          console.log(res.data);
          that.setData({
            rInfo:res.data,
            
            totalmoney: res.data.monthNum * res.data.price,
          });
        }else{
          console.log("失败"+res);
        }
      });
    };
    // 日历
    const date = new Date();
    const curYear = date.getFullYear();
    const curMonth = date.getMonth() + 1;
    const weeksCh = ['日', '一', '二', '三', '四', '五', '六'];
   //设置选择日期
   
    this.calculateEmptyGrids(curYear, curMonth);
    this.calculateDays(curYear, curMonth);
    this.setData({
      curYear,
      curMonth,
      weeksCh,
      // days
    });
  }, 
  getThisMonthDays(year, month) {//获取也的第一天
    return new Date(year, month, 0).getDate();
  },
  getFirstDayOfWeek(year, month) {//获取周的第一天
    return new Date(Date.UTC(year, month - 1, 1)).getDay();
  },
  calculateEmptyGrids(year, month) {
    const firstDayOfWeek = this.getFirstDayOfWeek(year, month);
    let empytGrids = [];
    if (firstDayOfWeek > 0) {
      for (let i = 0; i < firstDayOfWeek; i++) {
        empytGrids.push(i);
      }
      this.setData({
        hasEmptyGrid: true,
        empytGrids
      });
    } else {
      this.setData({
        hasEmptyGrid: false,
        empytGrids: []
      });
    }
  },
  calculateDays(year, month) {
    let days = [];

    const thisMonthDays = this.getThisMonthDays(year, month);

    for (let i = 1; i <= thisMonthDays; i++) {
      days.push({
        day: i,
        choosed: false
      });
    }

    this.setData({
      days
    });
  },
  // 查找上月或下月
  // handleCalendar(e) {
  //   const handle = e.currentTarget.dataset.handle;
  //   const curYear = this.data.curYear;
  //   const curMonth = this.data.curMonth;
  //   if (handle === 'prev') {//上月
  //     let newMonth = curMonth - 1;
  //     let newYear = curYear;
  //     if (newMonth < 1) {
  //       newYear = curYear - 1;
  //       newMonth = 12;
  //     }

  //     this.calculateDays(newYear, newMonth);
  //     this.calculateEmptyGrids(newYear, newMonth);

  //     this.setData({
  //       curYear: newYear,
  //       curMonth: newMonth
  //     });
  //   } else {//下月
  //     let newMonth = curMonth + 1;
  //     let newYear = curYear;
  //     if (newMonth > 12) {
  //       newYear = curYear + 1;
  //       newMonth = 1;
  //     }

  //     this.calculateDays(newYear, newMonth);
  //     this.calculateEmptyGrids(newYear, newMonth);

  //     this.setData({
  //       curYear: newYear,
  //       curMonth: newMonth
  //     });
  //   }
  // },
  // tapDayItem(e) {
  //   console.log("点击");
  //   date = new Date();
  //   console.log(date.getDay());
  //   console.log(this.data.days);

  //   // const idx = e.currentTarget.dataset.idx;
  //   var idx = date.getDate()-1;
  //   const days = this.data.days;
  //   days[idx].choosed = true;
  //   this.setData({
  //     days,
  //   });
  // },
  // chooseYearAndMonth() {
  //   const curYear = this.data.curYear;
  //   const curMonth = this.data.curMonth;
  //   let pickerYear = [];
  //   let pickerMonth = [];
  //   for (let i = 1900; i <= 2100; i++) {
  //     pickerYear.push(i);
  //   }
  //   for (let i = 1; i <= 12; i++) {
  //     pickerMonth.push(i);
  //   }
  //   const idxYear = pickerYear.indexOf(curYear);
  //   const idxMonth = pickerMonth.indexOf(curMonth);
  //   this.setData({
  //     pickerValue: [idxYear, idxMonth],
  //     pickerYear,
  //     pickerMonth,
  //     showPicker: true,
  //   });
  // },
  pickerChange(e) {
    const val = e.detail.value;
    chooseYear = this.data.pickerYear[val[0]];
    chooseMonth = this.data.pickerMonth[val[1]];
  },
  tapPickerBtn(e) {
    const type = e.currentTarget.dataset.type;
    const o = {
      showPicker: false,
    };
    if (type === 'confirm') {
      o.curYear = chooseYear;
      o.curMonth = chooseMonth;
      this.calculateEmptyGrids(chooseYear, chooseMonth);
      this.calculateDays(chooseYear, chooseMonth);
    }

    this.setData(o);
  },
  onShareAppMessage() {
    return {
      title: '小程序日历',
      desc: '还是新鲜的日历哟',
      path: 'pages/index/index'
    };
  },
  addBuy:function(){
    // console.log(this.data);
    wx.navigateTo({
      url: '/pages/shopping/checkout/checkout',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },
  /**
   * 根据本月的多少号判断周次
   */
  getWorkDay(day,nowDay,nowWeek){//本月的多少号，现在周几
    // console.log("参数："+day+","+nowDay+","+nowWeek)
    if(day>31||day<0){
      return false;
    }else{
      if (day < nowDay){
        return false;
      }
      // console.log("nowWeek:" + nowWeek)
      var week = nowWeek + (day - nowDay) % 7;//获取到周次
      if(week>7){
        week = week % 7;
      }
      // console.log( week <= 5);
      if (week <= 5){
        // console.log("week:" + week);
        return true;
      }else{
        return false;
      }
    }
    

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var countdays=0;
    const date=new Date();
    console.log("当前周次："+date.getTimezoneOffset());
    var idx = date.getDate() ;
    var nowDay=idx;
    const days = this.data.days;
    
    var week = date.getDay();
    if (week != 0 && week != 6){
      days[idx].choosed = true;
      countdays++;
    }
    
    while(idx<30){
      idx += 1;
      
      // console.log(this.getWorkDay(idx + 1, nowDay, week))
      if (this.getWorkDay(idx + 1, nowDay, week)){
        countdays++;
        days[idx].choosed = true;
      }
      // console.log(idx);
    }
    this.setData({
      days,
      countday: countdays,
    });
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
  
  }
})