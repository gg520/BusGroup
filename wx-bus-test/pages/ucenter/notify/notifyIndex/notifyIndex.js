// pages/Nav/Nav.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    common_navs_list: ['最新消息', '历史消息',],
    id: 0,
    index: 0,
    currentnews: [],//最新消息
    pastnews: [],//历史消息
    length: 0,//消息数量
    flag: true
  },
  NavClick: function (e) {
    var id = e.currentTarget.dataset.id
    console.log('id=' + id)
    console.log('index=' + this.data.index)
    var that = this
    that.setData({
      id: id
    })
    console.log('data.id=' + this.data.id)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //从后台读取消息数据（历史消息与最新消息）
    this.setData({
      currentnews: [{ id: '0', title: '出勤通知', contend: '老司机发车了,再不发车就要耽误时间了', time: '05-23-07:30', publisher: '张三' }],
      pastnews: [{ id: '0', title: '过年好', contend: '中国国际巴士团携全体员工祝各位老司机新年快乐', time: '05-23-07:30', publisher: '张三' }, { id: '0', title: '扣分提醒', contend: '您好，您于2018-08-09-15：30未出行所领任务，扣除信誉积分0.1分！', time: '05-23-07:30', publisher: '张三' }]
    });
    const currentnew = this.data.currentnews;
    currentnew.push({ id: '0', title: '扣分提醒', contend: '您好，您于2018-08-09-15：30未出行所领任务，扣除信誉积分0.1分！', time: '05-23-07:30', publisher: '张三' })
    //设置各个数组元素的id使之与数组下表相同
    for (var i = 0; i < currentnew.length; i++) {
      currentnew[i].id = i;
    }

    this.setData({
      currentnews: currentnew,
      length: currentnew.length,
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
  //最新消息详情页处理
  havareadcurrentnews: function (e) {
    const historynew = this.data.pastnews;
    const currentnew = this.data.currentnews;
    historynew.push(this.data.currentnews[e.currentTarget.dataset.id])
    currentnew.splice(e.currentTarget.dataset.id, 1)
    this.setData({
      pastnews: historynew,
      option: !this.data.option
    })
    wx.redirectTo({
      url:
      '/pages/Driver-notify/currentnewsdetail/detail?title=' + this.data.pastnews[this.data.pastnews.length - 1].title + '&contend=' + this.data.pastnews[this.data.pastnews.length - 1].contend + '&time=' + this.data.pastnews[this.data.pastnews.length - 1].time + '&publisher=' + this.data.pastnews[this.data.pastnews.length - 1].publisher
    })
  },
  //历史消息详情页处理
  havareadpastnews: function (e) {
    console.log(e.currentTarget.dataset.num)
    wx.navigateTo({
      url: '/pages/Driver-notify/pastnewsdetail/detail?title=' + this.data.pastnews[e.currentTarget.dataset.num].title + '&contend=' + this.data.pastnews[e.currentTarget.dataset.num].contend + '&time=' + this.data.pastnews[e.currentTarget.dataset.num].time + '&publisher=' + this.data.pastnews[e.currentTarget.dataset.num].publisher
    })
  }
})