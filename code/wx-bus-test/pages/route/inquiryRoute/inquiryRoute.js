// pages/route/inquiryRoute/inquiryRoute.js

var api=require("../../../config/api.js");
var util=require("../../../utils/util.js");

var app=getApp();
var startList = ["07:30", "08:00", "08:30", "08:50", "09:00"];
var endList = ["16:30", "17:00", "17:30", "18:00", "18:30"];
var successFalg=false;
var index=-1;
Page({
  /**
   * 页面的初始数据
   */
  data: {
    startsite:'',//出发地点
    endsite:'',//结束地点
    period:'',//招募周期
    starttimename:"出发时间",
    starttime:'',
    endtime:'',
    endtimename:'返程时间',
    username:'',//您的姓名
    mobile:'',//您的真实电话
    mesage:'',//备注信息
    startChioceIcon:'/static/images/icon-chioce.png',
    endChioceIcon:'/static/images/icon-chioce.png',
    // startChioce:false,
    // endChioce:false,
  },
  //时间选择
  choiceTime:function(e){
    // console.log(e.currentTarget.dataset.item);
    switch(e.currentTarget.dataset.item){
      case "startTime":
        //设置弹框
        wx.showActionSheet({
          itemList: startList,
          success:function(res){
            console.log(startList[res.tapIndex]);//显示的下标
            index = res.tapIndex;//设置下标
            successFalg=true;
            
          },
          fail:function(res){
            console.log(res.errMsg);
          }
        });
        if(successFalg){
          successFalg = false;
          this.setData({
            starttimename: "出发时间：" + startList[index],
            starttime: startList[index],
          })
        }
        break;
      case "endTime":
        //设置弹框
        wx.showActionSheet({
          itemList: endList,
          success: function (res) {
            console.log(endList[res.tapIndex]);//显示的下标
            index = res.tapIndex;//设置下标
            successFalg=true;
          },
          fail: function (res) {
            console.log(res.errMsg);
          }
        });
        if (successFalg) {
          successFalg=false;
          console.log("返程时间：" + endList[index]);
          this.setData({
            endtimename: "返程时间：" + endList[index],
            endtime: endList[index],
          })
        }
        break;
    }
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
    if (this.data.starttime.length <= 0 || this.data.endtime.length <= 0){
      util.showErrorToast("未选择时间");
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
    util.request(
      api.RouteInquiry,
      {
        username: this.data.username,
        phone: this.data.mobile,
        statsite: this.data.statsite,
        endsite: this.data.endsite,
        starttime: this.data.starttime,
        endtime: this.data.endtime,
        message: this.data.mesage,
      },'POST').then(function(res){
        wx.hideLoading();
        if (res.errno === 0){
          //成功
          util.showSuccessToast("提交成功");
          wx.navigateBack({//返回上页
            delta: 1
          })
        }else{
          util.showErrorToast("提交失败");
        }
      })
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
  bindMobileInput:function(e){
    this.setData({
      mobile: e.detail.value
    })

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
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
      startsite: '',//出发地点
      endsite: '',//结束地点
      starttimename: "出发时间",
      endtimename: '返程时间',
      username: '',//您的姓名
      mobile: '',//您的真实电话
      mesage: '',//备注信息
      startChioceIcon: '/static/images/icon-chioce.png',
      endChioceIcon: '/static/images/icon-chioce.png',
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
  
  }
})