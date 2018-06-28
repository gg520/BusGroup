Page({
  data: {
    publisher: '',
    time: '',
    title: '',
    contend: '',
    pastnews: [],
    length: 0,
  },
  onLoad: function (options) {

    this.setData({
      id: options.id,
      title: options.title,
      contend: options.contend,
      time: options.time
    })
  },
  haveread: function () {
    wx.redirectTo({
      url: '/pages/Driver-notify/index',
    })
  }
})
