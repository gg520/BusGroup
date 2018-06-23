var util=require("../../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    status:true,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.result)
    if(options.result==='成功'){
      this.setData({
        status:true,
        id: options.id,
      })
    } else if (options.result === '失败') {
      this.setData({
        status: false,
        nonceStr: options.nonceStr,
        prePayId: options.prePayId,
        signType: options.signType,
        paySign: options.paySign,
        id:options.id,
      })
    }else{
      console.log("未知异常")
    }
  },
  payOrder:function(){
    let that = this;
    wx.requestPayment({
      timeStamp: String(Date.parse(new Date())),   //时间戳,
      nonceStr: this.data.nonceStr,
      package: this.data.prePayId,
      signType: this.data.signType,
      paySign: this.data.paySign,
      success: function (res) {
        // 保留当前页面，跳转到应用内某个页面，使用wx.nevigeteBack可以返回原页面
        that.setData({
          status:true
        })
      },
      fail: function (res) {
        console.log(res.errMsg)
        util.showErrorToast("付款失败");

      }
    })
  },
  
  back:function(){

    wx.navigateBack();
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
  
  }
})