// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flagSeach: true,
    routes: '',
    search: {
      startLatitude: '',
      startLongitude: '',
      endLatitude: '',
      endLongitude: '',
      //到达站点
      endSite: '',
      startSite: '我的位置',//起始位置
    },
    //搜索数据的现实, 名称，详细位置信息，相距距离,坐标
    searchSite: '',
    countRout: 0,//显示的线路的数量默认是0
    pm: 0,//上午 1 下午 2，默认是0 没有选择上下午
    isAm: false,//是否上午
    isPm: false,//是否是下午
    isAll: true,
    isEmptySite: false,
    inputStartSite: "我的位置",
    inputEndSite:'',
    //搜索历史
    historyKeyword: []//'新郑','中原工学院'
  },
  //出发位置焦点
  inputStartFocus: function () {
    // console.log("开始焦点");
    wx.navigateTo({
      url: '/pages/search/searchStation/searchStation?value=start',
    })
  },
  inputEndFocus: function () {
    // console.log("结束焦点");
    wx.navigateTo({
      url: '/pages/search/searchStation/searchStation?value=end',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    
    that.setData({
      inputStartSite: app.search.startSite,
      search: app.search,
    })
    // console.log("cehsissdfsdfsdf")
    if (options.sign!=null){
      // console.log("不看")

      let search = that.data.search;
      if (options.sign==='start'){
        //设置搜索的初始坐标
        search.startLatitude = options.coord.split(",")[0];
        search.startLongitude = options.coord.split(",")[1];
        search.startSite = options.name;
        
      }else if(options.sign==='end'){
        
        search.endLatitude = options.coord.split(",")[0];
        search.endLongitude = options.coord.split(",")[1];
        search.endSite = options.name;
      }else{//其他即是不修改内容
        console.log("错误")
      }
      app.search = search;
      that.setData({
        inputStartSite: app.search.startSite,
        search: app.search,
        inputEndSite: app.search.endSite,
      })
      console.log(that.data.inputStartSite)
      console.log(that.data.search)
      //有数据就进行搜索查询啊
      if (that.data.search.startSite != null && that.data.search.endSite){//数据都已经输完
        wx.request({
          url: api.SearchRoute,
          data: {
            startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
            endSite: that.data.search.endSite,
            startNum: 0,
            num: 20
          },
          method: 'POST',
          success: function (res) {
            console.log(res.data)
            if (res.data.errno === 0) {
              console.log("设置：" + res.data.data)
              that.setData({
                flagSeach: false,
                routes: res.data.data,
                countRout: that.countRout + res.data.data.length//这个值由后台确定
              })
            }
          },
          fail: function (res) {
            util.showErrorToast("加载失败");
          },
          complete: function () {
            console.log(that.data)

          }
        })
      }
    }else{
      let search = that.data.search;
      wx.getLocation({
        type: 'gcj02',
        success: function (res) {
          search.startLatitude = res.latitude;
          search.startLongitude = res.longitude;
        },
      })
      search.startSite='我的位置';
      app.search = search;
      // console.log("sesrch:"+search)
    }
    //获取历史记录
    // let searchHistory = wx.getStorageSync('searchHistory');
    // console.log(searchHistory)

   
    // console.log("测试添加历史记录的方法")
    // util.saveSearchHistory("新郑");
  },
  /**
   * 不区分是招募线路还是已开线路的跳转
   * 是否是已开线路由数据的某一属性设定，在这里不做判断
   */
  goRoute: function (e) {
    if (app.globalData.hasLogin) {//判断您是否登录，登录可以跳转
      console.log("线路ID："+e.currentTarget.id);
      //获取线路的id
      //根据界面跳转
      wx.navigateTo({
        url: '/pages/route/routeInfo/routeInfo?routeid=' + e.currentTarget.id,
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
  //上午的数据
  goAm: function () {
    let that = this;
    console.log("设置前：" + this.data.isAm);
    wx.showLoading({
      title: '加载中...',
    });
    //查找数据
    if (that.data.searchSite.length > 0) {
      wx.request({
        url: api.SearchRoute,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.searchSite,
          startNum: 0,
          num: 20,
          time: 1
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              flagSeach: false,
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: true,//是否上午
              isPm: false,//是否是下午
              isAll: false,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      })
    } else {
      //所有数据
      wx.request({
        url: api.SearchRoute,
        data: {
          startNum: 0,
          num: 20,
          time: 1,
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: true,//是否上午
              isPm: false,//是否是下午
              isAll: false,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      });
    }


    wx.hideLoading();
  },
  goPm: function () {
    let that = this;
    wx.showLoading({
      title: '加载中...',
    });

    if (that.data.searchSite.length > 0) {
      wx.request({
        url: api.SearchRoute,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.searchSite,
          startNum: 0,
          num: 20,
          time: 2
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              flagSeach: false,
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: false,//是否上午
              isPm: true,//是否是下午
              isAll: false,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      })
    } else {
      //所有数据
      wx.request({
        url: api.SearchRoute,
        data: {
          startNum: 0,
          num: 20,
          time: 2,
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: false,//是否上午
              isPm: true,//是否是下午
              isAll: false,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      });
    }
    wx.hideLoading();
  },

  goAll: function () {
    let that = this;
    //加载请求后台数据,options跳转带来的参数
    wx.showLoading({
      title: '加载中...',
    });

    if (that.data.searchSite.length > 0) {
      wx.request({
        url: api.SearchRoute,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.searchSite,
          startNum: 0,
          num: 20,
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              flagSeach: false,
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: false,//是否上午
              isPm: false,//是否是下午
              isAll: true,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      })
    } else {
      //所有数据
      wx.request({
        url: api.SearchRoute,
        data: {
          startNum: 0,
          num: 20,
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length,//这个值由后台确定
              isAm: false,//是否上午
              isPm: false,//是否是下午
              isAll: true,
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      });
    }

    wx.hideLoading();
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
    console.log("界面隐藏")
    wx.navigateTo({
      url: '/pages/index/index',
    })
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    // console.log("界面卸载")
    // wx.navigateTo({
    //   url: '/pages/index/index',
    // })
    let search = this.data.search;
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        search.startLatitude = res.latitude;
        search.startLongitude = res.longitude;
      },
    })
    search.startSite = '我的位置';
    search.endLatitude='';
    search.endLongitude='';
    search.endSite='';//到达站点
    app.search = search;
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    let that = this;
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {

        that.setData({
          search: {
            startLatitude: res.latitude,
            startLongitude: res.longitude,
          }
        })
      },
    })

    //加载请求后台数据,options跳转带来的参数
    wx.showLoading({
      title: '加载中...',
    });
    var time = 0;
    if (that.data.isAm) {
      time = 1;
    } else if (that.data.isPm) {
      time = 2;
    }
    wx.request({
      url: api.RoutePlant,
      data: {
        startNum: 0,
        num: 20,
        time: time,
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data)
        if (res.data.errno === 0) {
          console.log("设置：" + res.data.data)
          that.setData({
            routes: res.data.data,
            countRout: that.countRout + res.data.data.length//这个值由后台确定
          })
        }
      },
      fail: function (res) {
        util.showErrorToast("加载失败");
      },
      complete: function () {
        console.log(that.data)

      }
    });
    wx.hideLoading();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {//存在问题
    //检测数量，以及已经显示的页码，进行拼接、将下面的数据添加到新的里面

    let that = this;
    wx.showLoading({
      title: '加载中...',
    })
    wx.request({
      url: api.RoutePlant,
      data: {

        startNum: that.data.countRout,
        num: 20
      },
      method: 'POST',
      success: function (res) {
        if (res.data.errno === 0) {
          // 数据需要处理一下，做一个拼接
          that.setData({
            routes: res.data.data,
            countRout: that.countRout + res.data.data.length//这个值由后台确定
          })
        }
      },
      fail: function (res) {
        util.showErrorToast("加载失败");
      },
      complete: function () {
        wx.hideLoading();
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  // 地点匹配
  searchSiteIndex: function (event) {
    //获取地址信息
    let itemIndex = event.currentTarget.id;
    let that = this;
    this.setData({
      searchSite: itemIndex
    })
    console.log(itemIndex)
    if (that.data.inputSite === 'start') {
      console.log("开始")
      //将数据赋值给数据初始位置
      that.setData({
        search: {
          // startLatitude: '',
          // startLongitude: '',
          //到达站点
          startSite: itemIndex,//起始位置
          routes: '',
        },
        inputStartSite: itemIndex,
      });

      console.log(that.data.inputStartSite)
    } else if (that.data.inputSite === 'end') {

      console.log(that.data.search.startLatitude);
      //获取到站点的ID从而将数据封装到数据传输获取站点数据
      wx.request({
        url: api.SearchRoute,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: itemIndex,
          startNum: 0,
          num: 20
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data)
          if (res.data.errno === 0) {
            console.log("设置：" + res.data.data)
            that.setData({
              flagSeach: false,
              routes: res.data.data,
              countRout: that.countRout + res.data.data.length//这个值由后台确定
            })
          }
        },
        fail: function (res) {
          util.showErrorToast("加载失败");
        },
        complete: function () {
          console.log(that.data)

        }
      })
    }

  },
  // 交换起始位置和终点的内容
  swop: function () {
    console.log("交换");
    var swap = this.data.search;
    this.setData({
      search: {
        startLatitude: swap.endLatitude,
        startLongitude: swap.endLongitude,
        endLatitude: swap.startLatitude,
        endLongitude: swap.startLongitude,
        endSite: swap.startSite,
        startSite: swap.endSite
      }
    })
  }

})