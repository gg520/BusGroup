var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();
Page({
  data: {
    collectList: [
     
    ],
    showType: 0
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
     this.getCollectList();
  },
  getCollectList() {
    let that = this;
    util.request(api.CollectGet,{},"POST").then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          collectList: res.data
        });
      }
    });
  },
  clearCollect:function(e){
    let id = e.currentTarget.dataset.id;//获取ID
    let that = this;
 


    //弹框确定删除收藏
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '删除该收藏？', 
      success: function (res) {
        if (res.confirm) {
         

          //根据ID删除数据
          util.request(api.CollectClear, { pRId: id }, "POST").then(function (res) {
            if (res.errno === 0) {
              console.log(res.data);
              //成功,隐藏该条数据
              let collectList = that.data.collectList;
              for (let i = 0; i < collectList.length; i++) {
                if (collectList[i].pRId == id) {
                  collectList[i].hidden = true;
                }
              }
              that.setData({
                collectList: collectList,
              });
            }
          });
          
        }
      }
    });

    
    
  },
  goRoute:function(e){
    if (app.globalData.hasLogin) {//判断您是否登录，登录可以跳转
      console.log("routeid" + e.currentTarget.dataset.routeid);
      //获取线路的id
      //根据界面跳转
      wx.navigateTo({
        url: '/pages/route/routeInfo/routeInfo?routeid=' + e.currentTarget.dataset.routeid,//站点ID,
      })
    } else {//未登录，确定跳转到登录界面
      wx.showModal({
        title: '',
        confirmColor: '#3CC51F',
        content: '未登录',
        confirmText: '登录',
        success: function (res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/pages/auth/login/login',
            })
          }
        }
      })
    }
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})