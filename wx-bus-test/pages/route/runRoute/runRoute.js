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
      startSite: '',//起始位置
    },
    //搜索数据的现实, 名称，详细位置信息，相距距离,坐标
    searchSite:'',
    countRout: 0,//显示的线路的数量默认是0
    pm: 0,//上午 1 下午 2，默认是0 没有选择上下午
    isAm: false,//是否上午
    isPm: false,//是否是下午
    isAll: true,
    isEmptySite: false,
  },

  inputFocus: function () {
    // console.log("获取焦点");

    // console.log(this.data.searchSite);
    //将数据屏蔽
    // this.setData({
    //   searchSite:'假数据',
    // });
  },

  inputChange: function (e) {

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

              searchSite: res.data.data
            });



            //请求成功
            //获取到站点信息，然后根据站点信息获取数据的显示怎么显示

            // var BMap=new bmap.BMapWX({//创建
            //   ak:'N0KvpqkorE9GPG467fqlThoTX6QLFjGH'
            // });
            // var fail=function(data){
            //   console.log(data);
            // };

            // var success=function(data){
            //   wxMarkerData = data.wxMarkerData;
            //   console.log("信息：" + wxMarkerData[0].address)
            // }
            // // 发起regeocoding检索请求 
            // BMap.regeocoding({
            //   fail: fail,
            //   success: success,
            //   // iconPath: '../../img/marker_red.png',
            //   // iconTapPath: '../../img/marker_red.png'
            //   location: '37.74725,113.62493'

            //   //  address: '地址：' + data[i].address + '\n',
            //   // desc: '描述：' + data[i].desc + '\n',
            //   // business: '商圈：' + data[i].business

            // });
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
            countRout: that.countRout + 20//这个值由后台确定
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
      console.log(e.currentTarget.dataset.routeid);
      //获取线路的id
      //根据界面跳转
      wx.navigateTo({
        url: '/pages/route/routeInfo/routeInfo?comeFrom=run&routeid=' + e.currentTarget.dataset.routeid,
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
    if (that.data.searchSite.length > 0) {
      wx.request({
        url: api.SearchRun,
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
    wx.showLoading({
      title: '加载中...',
    });
    if (that.data.searchSite.length > 0) {
      wx.request({
        url: api.SearchRun,
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
    //重新加载数据
    let that = this;
    wx.showLoading({
      title: '加载中...',
    })
    wx.request({
      url: api.RouteRun,
      data: {
        countRout: 0,
        pm: 0,
      },
      method: 'POST',
      success: function (res) {
        if (res.data.errno === 0) {
          that.setData({
            routes: res.data.data.routes,
            countRout: that.countRout + 10,//一次加10
            isAm: true,
            isPm: false
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
        countRout: that.data.countRout,//翻页
        pm: that.data.pm,
      },
      method: 'POST',
      success: function (res) {
        if (res.data.errno === 0) {
          // 数据需要处理一下，做一个拼接
          that.setData({
            routes: res.data.data.routes,
            countRout: that.countRout + 10
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
  // 地点匹配
  searchSiteIndex: function (event) {
    //获取地址信息
    let itemIndex = event.currentTarget.id;
    let that = this;
    this.setData({
      searchSite: itemIndex
    })
    console.log(that.data.search.startLatitude);
    //获取到站点的ID从而将数据封装到数据传输获取站点数据
    wx.request({
      url: api.SearchRun,
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