var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    searchSite:'',//搜索站点锁定的位置，开始 start 或者 end，默认end
    address:[
      { stationId: 0, stationName: "江西农业大学", stationCoord: "20,30", stationStatus: 0, stationDescribe: "江西南昌市志敏大道1101号" },
      { stationId: 1, stationName: "江西财经大学(蛟桥园校区)", stationCoord: "20,30", stationStatus: 0, stationDescribe: "江西省南昌市青山湖区双港东大街169号" },
      { stationId: 2, stationName: "东华大道", stationCoord: "20,30", stationStatus: 0, stationDescribe: "江西省南昌市青山湖区" },
      { stationId: 3, stationName: "范家新村-公交站", stationCoord: "20,30", stationStatus: 0, stationDescribe: "青三湖区广兰路东" },
      { stationId: 4, stationName: "万达广场(红谷滩店)", stationCoord: "20,30", stationStatus: 0, stationDescribe: "江西省南昌市青山湖区会展路999号" },
      { stationId: 5, stationName: "昌北机场T2航空楼", stationCoord: "20,30", stationStatus: 0, stationDescribe: "江西南昌市新建区机场大道" },
    ],
    history:[],
  },
  goSite:function(e){
    let that=this;
    const destination = e.currentTarget.dataset.destination;//说明
    const stationName = e.currentTarget.dataset.stationname;//站点
    const routeId = e.currentTarget.dataset.routeid;//站点ID
    const coord = e.currentTarget.dataset.coord; //坐标获取
    //将数据传送回搜索页面
    // console.log("什么：" + destination + "   加  " + stationName + "routeId:" + routeId+"坐标："+coord);
    //将数据保存到
    var msg = { stationId: routeId, stationName: stationName, stationCoord: coord, stationStatus: 0, stationDescribe: destination };
    util.saveSearchHistory(msg);//保存历史
    wx.navigateTo({
      url: '/pages/search/search/search?sign=' + that.data.searchSite + '&name=' + stationName + '&id=' + routeId + '&coord=' + coord,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //判断参数
    console.log(options.value);
    var value = options.value;
    if(options.value.length<=0){
      value='end';
    }
    this.setData({
      searchSite:value,
    });
  

    /**
     * 获取历史信息将信息输出
     * 历史信息的读取历史信息的保存
     * util.saveSearchHistory(msg)
     * wx.getStorageSync('searchHistory');
     */
    var history = wx.getStorageSync('searchHistory');// JSON.stringify(wx.getStorageSync('searchHistory'));
    console.log("长度"+history)
    this.setData({
      history:history,
    })
  },
  searchInput:function(e){
    let that = this;
    if (e.detail.value != null && '' != e.detail.value) {
      //获取匹配站点
      console.log(e.detail.value);
      this.setData({
        flagSeach: true
      });
      wx.request({
        url: api.SearchSite,
        data: {
          site: e.detail.value,
        },
        method: "POST",
        header: {
          "Content-Type": "application/json",
          "Connect_Platform": "Weixin_Passenger"
        },
        success: function (res) {

          if (res.data.errno === 0) {
            console.log(res.data)
            //获取站点信息
            //设置信息为空显示
            that.setData({

              address: res.data.data,
              history:[],
            });
          }
        },
        fail: function (res) {

        }
      })
    } else {
      this.setData({
        flagSeach: false
      });
    }

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
  back:function(){
    wx.navigateTo({
      url: '/pages/search/search/search?sign=brack',

    })
  }
})