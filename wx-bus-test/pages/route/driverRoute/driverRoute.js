// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

    tasks:
    {
      // startsite: '',//二七
      // endsite: '',//火车站
      // starttime: '',//05-23-07:30
      // endtime: '',//05-23-09:30
      // totalnum: '',//05-23-09:30
      // busnum: '',//豫A095X
      site: [
        // { siteid: '001', sitename: '文化路三全路', reachtime: '7:00' },
        // { siteid: '002', sitename: '三全路瞿东路', reachtime: '7:20' },
        // { siteid: '003', sitename: '郑州七中新校', reachtime: '7:40' },
        // { siteid: '004', sitename: '京广路人民路', reachtime: '8:00' },
      ],
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
    

  },
  /* 生命周期函数--监听页面初次渲染完成
  */
  onReady: function () {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

   
    let that = this;
    util.request(api.FindCurrentRoad, {}, "POST").then(function (res) {
     
      if (res.errno === 0) {
        console.log("设置：" + res.data)
        that.setData({
          tasks: res.data,
          countRout: that.countRout + 10//这个值由后台确定
        })
      } else {


        wx.navigateBack();
        util.showErrorToast(res.errmsg);
      }
    })
   
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


  /**
   * 页面上拉触底事件的处理函数
   */

  showview: function () {
    this.setData({
      flag: !this.data.flag
    })
    console.log(this.data.flag)
  },
  //司机完成任务解绑车辆
  dispatchCar: function () {
    console.log(this.data.tasks.busId)
    wx.showModal({
      title: '注意',
      busnum: '',//豫A095X
      content: '解绑车牌号为' + this.data.tasks.busId + '的车辆前，请确认已完成该线路任务',
      confirmText: '确认解绑',
      showCancel: true,
      success: function (res) {
        if (res.confirm) {
          util.request(
            api.driverOpenBindCar,
            {},
            'POST').then(function (res) {
              if (res.errno === 0) {
                //成功
                util.showSuccessToast("解绑成功");
                wx.navigateTo({
                  url: '/pages/ucenter/index/index',
                })
              }
              else {
                util.showErrorToast(res.errmsg)
                wx.navigateBack({

                })
              }


            })

        }

      }
    });
  }
})
