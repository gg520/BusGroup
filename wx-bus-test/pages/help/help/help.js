var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    helps: [
      // { id: 0, name: "什么是巴士团", describe: "江西省南昌市青山湖区双港东大街169号1" },
      // { id: 1, name: "怎么团", describe: "江西省南昌市青山湖区双港东大街169号2" },
      // { id: 2, name: "怎么团成功", describe: "江西省南昌市青山湖区双港东大街169号3" },
      // { id: 3, name: "什么是招募", describe: "江西省南昌市青山湖区双港东大街169号4" },
      // { id: 4, name: "怎么参与招募", describe: "江西省南昌市青山湖区双港东大街169号5" },
      // { id: 5, name: "怎么招募成功", describe: "江西省南昌市青山湖区双港东大街169号6" },
    ],
    showModal: false,
    showTittle: '',
    showText: '',
    use: '',
    usebutton: true,
  },
  godest: function (e) {

    let helps = this.data.helps;
    for (let i = 0; i < helps.length; i++) {
      if (e.currentTarget.dataset.id == helps[i].helpId) {
        this.setData({
          showTittle: helps[i].helpName,
          showText: helps[i].helpMessage,
          showModal: true,
        })
      }
    }
  },
  closeModal: function () {
    this.setData({
      showModal: false,
    })
  },

  goused: function (e) {
    // console.log(e.currentTarget.dataset.index)
    let that = this;
    // if(this.data.usebutton){
    if (e.currentTarget.dataset.index == 'used') {
      //弹窗设置点击事件
      this.setData({
        use: 'used',
        usebutton: false
      })
      util.showSuccessToast("感谢您的支持")
    } else if (e.currentTarget.dataset.index == 'useless') {
      this.setData({
        use: 'useless',
        usebutton: false
      })
      console.log("无用")
      wx.showActionSheet({
        itemList: ['内容太啰嗦', '规则不合理', '方法行不通', '其他原因'],
        success: function (res) {
          util.showSuccessToast("感谢您的反馈");
          that.setData({
            showModal: false,
          })
        },
        fail: function (res) {
        }
      })
    }
    // }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.myRequest();
  },
  myRequest: function () {
    let that=this;
    wx.showLoading({
      title: '加载中...',
    })
    util.request(api.HelpList,{}, "POST").then(function (res) {
      if (res.errno === 0) {
        that.setData({
          helps: res.data,
        })
      }
    });
    wx.hideLoading();
  },
  push: function () {
    this.myRequest();
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


  //pages/help/feedback/feedback
  goFeedBack:function(){
    wx.navigateTo({
      url: '/pages/help/feedback/feedback',
    })
  }
})