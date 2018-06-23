// pages/route/routeInfo/routeInfo.js

var util=require("../../../utils/util.js");
var api=require("../../../config/api.js");

var app = getApp();
/**
 * getWorkDay方法设置不可选的数据，然后根据数据进行数据的处理，节假日等数据的断定
 */
var countday=0;
var comeFrom='';
var routeId=0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    comeFrom:'',//plan 或者 run

    price: '',//单价
    startSite: '',//出发地点
    endSite: '',//到达地点
    driver: '',//司机
    starttime: '',//出发时间
    arrivaltime: '',//预计到达时间
    busNum: '',//车号
    phone: '',//电话
    busId: '',//牌照
    stations:[//途径站信息
      { stationname: "", stationid: '' }
    ],//车站的站点，有顺序

    startRecruit:'',//运行周期开始
    endsRecruit:'',//运行结束
    recruit:'',//发车周期

    countday:0,//选择的天数
    routeid:'',//线路的id
    totalmoney:0.00,//总钱数，即为支付金额
  },
  /**
   * 生命周期函数--监听页面加载
   */

  /**
   * 根据routeid获取到数据，然后根据routestaute确定是招募信息还是其他
   */
  onLoad: function (options) {
    
    routeId = options.routeid;
   
  }, 

// 日期的设置获取当前日期day和周次，
  getNowDayAndWeek: function (starttime, endstime) {//startTime 开始时间，countNum 天数，flag 是否计算节假日
 
  
  // console.log("价格" + this.data.stations.length)
  

  //获取开始时间
  

console.log(starttime+"  cc  "+endstime)

  var sD=starttime.split("-");
  var eD=endstime.split("-");
  var startDate=new Date(sD[0],sD[1]-1,sD[2]);
  var endsDate=new Date(eD[0],eD[1]-1,eD[2]);
  var longDi=endsDate.getTime()-startDate.getTime();

  var maxDay = new Date(sD[0], sD[1], 0).getDate();//开始月的最大时间

  //计算时间差
  var dayDi=longDi/(1000*60*60*24);
  //设置时间表
  var week = startDate.getDay();//开始时间的周次
  let empytGrids = [];
  let days=[];

  /**
   * 设置占位符
   */
  for (let i = 0; i < week - sD[2] + 1; i++) {
    empytGrids.push(i);
    // console.log("站位")
  }

  /**
   * 判断设置前半部分不可选
   * 
   */
  if((sD[2])<week-1){//小于周次
    console.log("小于周次")
    for (let i = 1; i < sD[2]; ++i) {
      days.push({
        day: i,//日期
        month:sD[1],
        choosed: false,//已选择
        check: false,//可更改
      });
      
    }
  }else{
    console.log("不小于周次")
    for(let i=0;i<week;i++){
      days.push({
        day: sD[2]-week+i,//日期
        month: sD[1],
        choosed: false,//已选择
        check: false,//可更改
      });
    }
  }
  
  this.setData({
    days: days,
    hasEmptyGrid: true,
    empytGrids,
    recruit: dayDi,
  })

  /**
   * 设置选择日期
   * 
   */
  this.setSelect(starttime, endstime);//设置选择日期

},
//设置可选日期
  setSelect: function (starttime, endstime){

  var sD = starttime.split("-");
  var eD = endstime.split("-");
  var startDate = new Date(sD[0], sD[1]-1, sD[2]);
  var endsDate = new Date(eD[0], eD[1]-1, eD[2]);
  var longDi = endsDate.getTime() - startDate.getTime();

  var maxDay = new Date(sD[0], sD[1], 0).getDate();//开始月的最大时间

  //计算时间差
  var dayDi = longDi / (1000 * 60 * 60 * 24);

  let days=this.data.days;

  if ((dayDi + startDate.getDate()) > maxDay) {//第二月有数据
    //设置文
    console.log("第二个月有数据")
    dayDi=dayDi-(maxDay-sD[2]);//第二月拥有的天数
    while(sD[2]<=maxDay){//将第一个月的数据保存
      if (this.getWorkDay(sD[0], sD[1], (sD[2]))) {
        if (comeFrom === 'plan') {
          countday++;
          days.push({
            day: sD[2],//日期
            month: sD[1],
            choosed: true,//已选择
            check: true,//可更改
          });
        }else{
          days.push({
            day: sD[2],//日期
            month: sD[1],
            choosed: false,//已选择
            check: true,//可更改
          });
        }
       
      }else{
        days.push({
          day: sD[2],//日期
          month: sD[1],
          choosed: false,//已选择
          check: false,//可更改
        });
      }
      
      sD[2]++;
    }
    for(let i=1;i<dayDi;++i){//将第二个月数据保存
      if (this.getWorkDay(sD[0], eD[1], i)) {
        if (comeFrom === 'plan') {
          countday++;
          days.push({
            day: i,//日期
            month: eD[1],
            choosed: true,//已选择
            check: true,//可更改
          });
        }else{
          days.push({
            day: i,//日期
            month: eD[1],
            choosed: false,//已选择
            check: true,//可更改
          });
        }
        
      }else{
        days.push({
          day: i,//日期
          month: eD[1],
          choosed: false,//已选择
          check: false,//可更改
        });
      }
      
    }
  }else{//第二个月没有数据
    console.log("第二个月没有数据")
    let i=0;
    while (i < dayDi-1) {
      if (this.getWorkDay(sD[0], sD[1], (sD[2] + i))){
        if (comeFrom === 'plan') {
          countday++;
          days.push({
            day: sD[2] + i,//日期
            month: sD[1],
            choosed: true,//已选择
            check: true,//可更改
          });
        }else{
          days.push({
            day: sD[2] + i,//日期
            month: sD[1],
            choosed: false,//已选择
            check: true,//可更改
          });
        }
        
      }else{
        days.push({
          day: sD[2] + i,//日期
          month: sD[1],
          choosed: false,//已选择
          check: false,//可更改
        });
      }
      
      i++;
    }
  }
  console.log("结束周次：" + endsDate)
  if(endsDate.getDay()!=0){
    let i = 1;
    while (i <= (7 - endsDate.getDay())) {
      days.push({
        day: eD[2]++,//日期
        month: eD[1],
        choosed: false,//已选择
        check: false,//可更改
      });
      i++;
    }
  }
  if (comeFrom === 'plan') {
    this.setData({
      days: days,
      countday: countday,
      totalmoney: (countday * this.data.price).toFixed(2),
    });

  }else{
    this.setData({
      days: days,
    })
  }
  
},

  addBuy:function(){
    if(countday===0){
      util.showWarningToast("请选择坐车日期");
      return false;
    }

    console.log();

    let that=this;
    //验证是否完善信息，不完善提示并保存到收藏
    if (app.globalData.hasLogin) {

      //判断是否实名认证了
      wx.request({

        url: api.CheckInfo,
        header: {
          "X-Bus-Token": wx.getStorageSync("token"),
          "Connect_Platform": "Weixin_Passenger"
        },
        success: function (res) {
          if (res.statusCode===302){
            util.showErrorToast("未登录");
            wx.navigateTo({
              url: '/pages/auth/login/login',
            })
          }else if (res.data.errno === 0) {
            //验证通过
            wx.navigateTo({
              url: '/pages/shopping/checkout/checkout?countDay=' + countday + '&routeid=' + that.data.routeid + '&selectdays=' + that.getDays(),
            })

          } else if (res.data.errno === 402){
            //验证失败
            util.showWarningToast("未完善信息");
            wx.navigateTo({
              url: '/pages/ucenter/myInfo/myInfo',
            })
          }else{
            util.showWarningToast("未知错误");
          }
        }
      })

    } else {
      util.showWarningToast("未登录");
      wx.navigateTo({
        url: '/pages/auth/login/login',
      })
    }
    
  },
 
  addMyCollect:function(){
    //收藏
    util.request(api.CollectAdd,
    {
      routeId:this.data.routeId,

    },"POST").then(function(res){
      if (res.data.erron === 0) {
        util.showSuccessToast("收藏成功");
      } else {
        util.showErrorToast("收藏失败");
      }
    });
  },
  selectDays:function(e){
    if (comeFrom === 'plan') {//招募
      return false;
    }
    const day = e.currentTarget.dataset.day;//日期
    const months = e.currentTarget.dataset.months;//月份
   
    
    const days=this.data.days;
    // console.log(days)
    for(let i=0; i<days.length;i++){
      if (days[i].check&&day==days[i].day&&months==days[i].month){
        if (days[i].choosed){
          days[i].choosed = false;
          countday--;
        }else{
          days[i].choosed = true;
          countday++;
        }
        
      }
    }
    let that=this;
    this.setData({
      days: days,
      countday: countday,
      totalmoney: (countday * that.data.price).toFixed(2),
    })
  },
// 获取时间的数据方法
  getDays:function(){
    var selectdays="";
    const days = this.data.days;//获取日期
    var startRecruit = this.data.startRecruit;//运行周期开始
    var endsRecruit = this.data.endsRecruit;//运行结束
    var startmonth = startRecruit.substring(startRecruit.indexOf("-") + 1, startRecruit.lastIndexOf("-"));//第一个月
    var endmonth = endsRecruit.substring(endsRecruit.indexOf("-") + 1, endsRecruit.lastIndexOf("-"));//第二个月   
    for (let i = 0; i < days.length; i++) {
      // console.log(i)
      if (days[i].choosed){
        // console.log("选中："+days.day)
        if ( startmonth == days[i].month) {//第一个月
          if (selectdays.length>0){
            selectdays += "," + startRecruit.substring(0, startRecruit.lastIndexOf("-")+1)+days[i].day;
          }else{
            selectdays += startRecruit.substring(0, startRecruit.lastIndexOf("-") + 1) + days[i].day;
          }
        }else if(endmonth==days[i].month){//第二个月
          if (selectdays.length > 0) {
            selectdays += "," + endsRecruit.substring(0, endsRecruit.lastIndexOf("-") + 1) + days[i].day;
          } else {
            selectdays += endsRecruit.substring(0, endsRecruit.lastIndexOf("-") + 1) + days[i].day;
          }
        }
      }
      
    }
    // console.log(days)
    // console.log(selectdays)
    return selectdays;//返回选择的数据
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },
  /**
   * 根据年月日判断是否可选
   */
  getWorkDay:function(year,month,day){
    var date = new Date(year, month - 1, day);
    // console.log(year, month, day, date.getDay())
    
    if (date.getDay() == 6 || date.getDay()===0){
      return false;
    }
    return true;
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

    countday = 0;
    let that = this;
    comeFrom = '';
    console.log(routeId);
    //获取来源页面
    console.log("执行")

    if (routeId.length > 0) {
      let that = this;
      that.setData({
        routeid: routeId
      });
      //获取到线路的id，根据id获取后台的线路信息
      util.request(api.RouteInfo, { routeId: routeId }, 'POST').then(function (res) {
        // console.log("数据：" + JSON.stringify(res.data));
        if (res.errno === 0) {//请求成功
          let data = res.data;
          console.log("测试时间：" + data)
          comeFrom = data.routeStatus == 3 ? 'plan' : 'run';
          console.log("comFrom:" + comeFrom + "标示量：" + data.routeStatus)
          that.setData({
            price: data.price,
            startSite: data.startSite,//出发地点
            endSite: data.endSite,//到达地点
            driver: data.driver,//司机
            starttime: data.starttime,//出发时间
            arrivaltime: data.arrivaltime,//预计到达时间
            busNum: data.busNum,//车号
            phone: data.phone,//电话
            busId: data.busId,//牌照
            stations: data.stations,
            startRecruit: data.startRecruit,//运行周期开始
            endsRecruit: data.endsRecruit,//运行结束
          });
          that.getNowDayAndWeek(data.startRecruit, data.endsRecruit);

        } else {
          console.log("失败" + res.errno);
        }
      });
    } else {
      util.showErrorToast("加载失败");
    }
    // console.log()
    const weeksCh = ['日', '一', '二', '三', '四', '五', '六'];

    this.setData({
      weeksCh,
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