
var api=require("../config/api.js");
var app=getApp();

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 封装微信登录后的request
 */
function request(url,data={},method="GET"){
  console.log(data)
  return new Promise(function (resolve,reject){
    wx.request({
      url: url,
      data:data,
      method:method,
      header:{
        "Content-Type":"application/json",
        "X-Bus-Token":wx.getStorageSync("token"),
        "Connect_Platform":"Weixin_Passenger"
      },
      success:function(res){
        if(res.statusCode==200){//链接后台成功
          if(res.data.errno==401){
            //清除登录的相关内容
            try{
              wx.removeStorageSync("userInfo");
              wx.removeStorageSync("token");
            }catch(e){

            }

            wx.navigateTo({
              url: '/pages/auth/login/login',
            });
          }else{
            resolve(res.data);
          }
        }else{
          reject(res.errMsg);
        }
      },
      fail:function(err){
        reject(err)
      }
    })
  });
}



/**
 * 判断页面是否需要登录
 */
function redirect(url){
  if(false){//后期的设置
    wx.redirectTo({
      url: '/pages/login/login'
    });
    return false;
  }else{
    wx.redirectTo({
      url: url
    });
  }
}
/**
 * 失败显示
 */
function showErrorToast(msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

/**
 * 警告信息显示
 */
function showWarningToast(msg){
  wx.showToast({
    title: msg,
    image: '/static/images/icon_warning.png'
  })
}

/**
 * 成功信息显示
 */
function showSuccessToast(msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_success.png'
  })
}

function saveSearchHistory(msg){
  var searchHistory =wx.getStorageSync('searchHistory');
  wx.removeStorageSync("searchHistory");
  console.log(searchHistory)
  console.log(searchHistory.length)
  if(searchHistory.length<=0){
    console.log("空")
    wx.setStorageSync('searchHistory', [msg]);
  }else{
    console.log("有值")
    var flag = false;
    for (var i = 0; i < searchHistory.length; ++i) {
      //将数组遍历
      if (msg.stationId === searchHistory[i].stationId) {
        console.log(msg+"  === "+searchHistory[i])
        flag = true;
        break;
      }
    }
    if (!flag) {//没有该搜索历史
      //插入新的历史
      searchHistory.push(msg);
    }
    
    wx.setStorageSync('searchHistory', searchHistory);
  }
  console.log(msg + " : " + wx.getStorageSync('searchHistory'))
}


module.exports = {
  formatTime,
  request,
  redirect,
  showErrorToast,
  showWarningToast,
  showSuccessToast,
  saveSearchHistory,

}
