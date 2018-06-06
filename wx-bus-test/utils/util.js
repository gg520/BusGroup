wx.showLoading({
  title: '加载中.  const hour = date.getHours()
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

module.exports = {
  formatTime,
  request,
  redirect,
  showErrorToast,
  showWarningToast,
  showSuccessToast
}
