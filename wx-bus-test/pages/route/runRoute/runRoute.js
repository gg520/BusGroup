// pages/route/planRoute/planRoute.js
var api = require("../../../config/api.js");
var util = require("../../../utils/util.js");
var bmap = require("../../../libs/bmap-wx.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flagSeach: false,
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

    //设置搜索标识量
    searchMark: 0,//0 空闲，1 开始 ，2 结束
  },

  inputFocus: function () {


  },
  //开始改变
  startInputChange: function (e) {

    let that = this;
    if (e.detail.value != null && '' != e.detail.value) {
      //获取匹配站点
      console.log(e.detail.value);
      this.setData({
        searchMark: 1,//设置开始标量
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

              searchSite: res.data.data
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
  //结束改变
  endInputChange: function (e) {

    let that = this;
    if (e.detail.value != null && '' != e.detail.value) {
      //获取匹配站点
      console.log(e.detail.value);
      this.setData({
        searchMark: 2,//设置开始标量
        flagSeach: true
      });
      console.log("改变的位置：" + this.data.searchMark)
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

              searchSite: res.data.data
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {

        that.setData({
          search: {
            startLatitude: res.latitude,
            startLongitude: res.longitude,
            startSite: '我的位置'
          }
        })
      },
    })

    //加载请求后台数据,options跳转带来的参数
    wx.showLoading({
      title: '加载中...',
    });

    wx.request({
      url: api.RouteRun,
      data: {
        startNum: 0,
        num: 20
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
   * 不区分是招募线路还是已开线路的跳转
   * 是否是已开线路由数据的某一属性设定，在这里不做判断
   */
  goRoute: function (e) {
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
        url: api.SearchRun,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.search.endSite,
          startSite: that.data.search.startSite,
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
        url: api.RouteRun,
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
        url: api.SearchRun,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.search.endSite,
          startSite: that.data.search.startSite,
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
        url: api.RouteRun,
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
        url: api.SearchRun,
        data: {
          startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
          endSite: that.data.search.endSite,
          startSite: that.data.search.startSite,
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
        url: api.RouteRun,
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
      url: api.RouteRun,
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
      url: api.RouteRun,
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

    let coord = [];//获取坐标
    if (event.currentTarget.dataset.coord) {
      coord = event.currentTarget.dataset.coord.split(",");

    }
    let that = this;
    let search = this.data.search;
    console.log("searchMark:" + this.data.searchMark)
    if (this.data.searchMark === 1) {
      search.startSite = itemIndex;//设置起始地点的数据
      search.startLatitude = coord[0];//起始坐标维度
      search.startLongitude = coord[1];//起始坐标的经度
    } else if (this.data.searchMark === 2) {
      search.endSite = itemIndex;
    } else {
      return false;
    }

    this.setData({
      searchMark: 0,
      search: search
    })
    console.log(that.data.search.startLatitude);
    console.log("开始：" + this.data.search.startSite)
    console.log("结束：" + this.data.search.endSite)
    // if (this.data.search.startSite != null && this.data.search.endSite!=null){
    //获取到站点的ID从而将数据封装到数据传输获取站点数据
    wx.request({
      url: api.SearchRun,
      data: {
        startCoord: that.data.search.startLatitude + "," + that.data.search.startLongitude,
        endSite: this.data.search.endSite,
        startSite: that.data.search.startSite,
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
  },
  clearstatSite: function () {
    let search = this.data.search;
    search.startSite = '';
    this.setData({
      search: search
    })
  },
  clearendSite: function () {
    let search = this.data.search;
    search.endSite = '';
    this.setData({
      search: search
    })
  }
})