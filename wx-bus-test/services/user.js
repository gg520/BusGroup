/**
 * 用户的相关服务
 * 登录验证，界面验证
 */
const util = require('../utils/util.js');
const api = require('../config/api.js');
var app=getApp();
/**
 * Promise封装wx.checkSession
 */
function checkSession(){
  return new Promise(function(resolve,reject){
    wx.checkSession({
      success:function(){
        resolve(true);
      },
      fail:function(){
        reject(false);
      }
    })
  });
}

/**
 * promise封装wx.login
 */
function login(){
  return new Promise(function(resolve,reject){
    wx.login({
      success:function(res){
        //微信申请登录
        console.log("微信申请登录成功");
        if(res.code){
          resolve(res);
        }else{
          reject(res);
        }
      },
      fail:function(err){

        console.log("微信申请登录失败");
        reject(err);
      }
    })
  });
}

/**
 * Promise封装wx.getUserInfo
 */
function getUserInfo(){
  return new Promise(function(resolve,reject){
    wx.getUserInfo({
      withCredentials:true,
      success:function(res){
        console.log("userInfo"+res.userInfo)
        resolve(res)
      },
      fail:function(err){
        util.showWarningToast("未授权");
      }
    })
  })
}

/**
 * 微信登录
 */
function loginByWeixin() {

  let modile=null;
  let code = null;
  return new Promise(function (resolve, reject) {
    return login().then((res) => {
      code = res.code;
      modile=res.phone;
      console.log("电话"+modile);
      return getUserInfo();
    }).then((userInfo) => {
      //登录链接地址
      // console.log('登录链接地址：'+api.AuthLoginByWeixin);
      // console.log('登录链接地址：http://localhost:8888/login/loginByWeixin' );
      // console.log("code:" + code);
      // console.log("userInfo:"+userInfo);
      //登录远程服务器
      util.request(api.AuthLoginByWeixin, { code: code, userInfo: userInfo, modile: modile }, 'POST').then(res => {
        if (res.errno === 0) {
          //存储用户信息
          //登录成功，设置登录成功后的消息
          console.log('登录成功：'+res.data.userInfo);
          wx.setStorageSync('userInfo', res.data.userInfo);
          wx.setStorageSync('token', res.data.token);
          wx.setStorageSync('user', "Weixin_Passenger");
          // console.log("获取："+wx.getStorageSync('userInfo').nickName);
          resolve(res);
        } else {
          reject(res);
        }
      }).catch((err) => {
        reject(err);
      });
    }).catch((err) => {
      reject(err);
    })
  });
}

/**
 * 判断用户是否登录
 */
function checkLogin(){
  return new Promise(function(resolve,reject){
    if (wx.getStorageSync('userInfo') && wx.getStorageSync('token') && app.globalData.hasLogin){
      //账号已经登录
      console.log("账号已经登录");
      checkSession().then(()=>{
        resolve(true);
      }).catch(()=>{
        reject(false);
      });
    }else{
      //未登录
      console.log("未登录");
      reject(false);
    }
  })
}

module.exports ={
  loginByWeixin,
  checkLogin,
  getUserInfo
}