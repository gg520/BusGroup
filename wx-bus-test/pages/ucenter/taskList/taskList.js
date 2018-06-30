// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    routeid: 0,
    tasks: [
      
    ],
    startnum: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //加载请求后台数据,options跳转带来的参数
    let that = this;
    wx.request({
      url: api.TaskList,
      data: {
        Tasknum: 10,//每次10条
        startnum: 0,//起始位置
      },
      method: 'POST',
      header: {
        "Content-Type": "application/json",
        "Connect_Platform": "Weixin_Passenger",
        "X-Bus-Token": wx.getStorageSync("token")
      }, success: function (res) {
        console.log(res.data.data.tasks)
        if (res.data.errno === 0) {
          console.log("设置：")
          that.setData({
            tasks: res.data.data.tasks,
            startnum: that.data.startnum + 10
          })
        } else if (res.data.errno === 401) {
          wx.navigateTo({
            url: '/pages/auth/login/login',
          });
        }
      },
      fail: function (res) {
        wx.showToast({
          title: '数据加载失败',
          icon: 'none',
          duration: 2000
        })
      }
    })

  },

  /* 生命周期函数--监听页面初次渲染完成
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
  onPullDownRefresh: function () {
    //重新加载数据
    let that = this;
    wx.request({
      api: api.GetTotalTask,
      data: {
        Tasknum: 10,
        startnum: that.data.startnum,
      },
      method: 'POST',
      header: {
        "Content-Type": "application/json",
        "Connect_Platform": "Weixin_Passenger",
        "X-Bus-Token": wx.getStorageSync("token")

      },
      success: function (res) {
        console.log("请求数据：" + res.data)
        if (res.data.errno === 0) {
          console.log("设置：" + res.data.tasks)
          that.setData({
            tasks: res.data.tasks,
            startnum: that.data.startnum + 10
          })
        }
      },
      fail: function (res) {
        wx.showToast({
          title: '数据加载失败',
          icon: 'none',
          duration: 2000
        })
      }
    })
  },




  //选定某一任务并提交后台以领取任务
  goRoute: function (e) {
    let that = this;
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '确定领取此任务？',
      success: function (res) {
        if (res.confirm) {
          //匹配到所选定的任务
          for (let i = 0; i < that.data.tasks.length; i++) {
            if (that.data.tasks[i].routeid == e.currentTarget.dataset.id) {
              that.setData({
                routeid: that.data.tasks[i].routeid
              })
            }
          }

          util.request(api.Gettask,
            { routeid: that.data.routeid }, "POST").then(function (res) {
              if (res.errno == 0) {

                wx.showModal({
                  showCancel: false,
                  title: '',
                  content: '绑定任务成功',
                  success: function (res) {
                    if (res.confirm) {
                      wx.redirectTo({
                        url: '/pages/Driver-route/DriverCRoute/DriverCRoute',
                      })
                    }
                  }
                })


              }
              else {
                wx.showModal({
                  showCancel: false,
                  title: '领取失败',
                  content: res.errmsg,
                  success: function (res) {
                    if (res.confirm) {
                      wx.redirectTo({
                        url: '/pages/Gettask/index/index',
                      })
                    }
                  }
                })
              }
            })
        }
      }
    })
  }

})