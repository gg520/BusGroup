
var api=require("../../../config/api.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    versions: [
      { id: 0, title: "巴士团0.8.0", date:'05月30日', content: "测试更改" },
      { id: 1, title: "巴士团0.7.0", date: '05月30日', content: "测试更改" },
      { id: 2, title: "巴士团0.6.0", date: '05月30日', content: "测试更改" },
      { id: 3, title: "巴士团0.5.0", date: '05月30日', content: "测试更改" },
      { id: 4, title: "巴士团0.4.0", date: '05月30日', content: "测试更改" },
      { id: 5, title: "巴士团0.3.0", date: '05月30日', content: "测试更改" },
      { id: 6, title: "巴士团0.2.0", date: '05月30日', content: "测试更改" },
      { id: 7, title: "巴士团0.1.0", date: '05月30日', content: "测试更改" },
      { id: 8, title: "巴士团0.0.9", date: '05月30日', content: "测试更改" },
    ],
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
    wx.request({
      url: api.SystemMessage,
      method:"POST",
      header: {
        "Content-Type": "application/json",
        "Connect_Platform": "Weixin_Passenger"
      }, 
      success: function (res) {
        if(res.errno===0){
          this.setData({
            versions:res.data
          })
        }else{

        }
      },
      fail:function(err){
        console.log(err)
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
  goContent:function(e){
    wx.navigateTo({
      url: '/pages/system/messageContent/messageContent?content=' + e.currentTarget.dataset.content,
    })
  }
})