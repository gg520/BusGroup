//app.js
var util=require("./utils/util.js")
var api=require("./config/api.js")
var user=require("./services/user.js")
App({
  onLaunch: function () {
    //获取权限
    wx.getSetting({
      success(res){
        if(!res.authSetting['scope.record']){
          wx.authorize({
            scope: 'scope.userLocation',
            success(){
              //用户已经同意获取当前位置的信息，后续调用不在弹出
              wx.getLocation({
                type:'gcj02',
                success: function(res) {
                  var latitude = res.latitude
                  var longitude = res.longitude
                  var speed = res.speed
                  var accuracy = res.accuracy
                  console.log("latitude:" + latitude + ";longitude:" + longitude + ";speed:" + speed + ";accuracy:" + accuracy);
                  // wx.openLocation({
                  //   latitude: latitude,
                  //   longitude: longitude,
                  //   scale:28
                  // })
                },
              })
            }
          })
        }
      }
    })
    //获取位置
    

  },
  onShow:function(options){
    user.checkLogin().then(res=>{
      this.globalData.hasLogin=true;
    }).catch(()=>{
      this.globalData.hasLogin=false;
    })
  },
  globalData: {
    hasLogin:false
  }
})