//服务器的信息
// var WxApiRoot ="http://192.168.105.1:8888/weixin/";//测试本地后台数据
var WxApiRoot = "http://localhost:8888/weixin/";//测试本地后台数据

module.exports = {
  AuthLoginByWeixin: WxApiRoot + 'auth/loginByWeixin',//微信登录
  AuthReset: WxApiRoot + 'auth/reset', //重置密码
  AuthRegister: WxApiRoot + 'auth/register', //注册账号
  AuthLoginByAccount: WxApiRoot + 'auth/login', //账号登录
  UserInfo:WxApiRoot+"user/userInfo",

  RouteInquiry: WxApiRoot+'route/inquiryRoute',//线路征集链接
  RouteInfo: WxApiRoot + 'route/routeInfo',//获取路线的详细信息链接
  RoutePlant: WxApiRoot + 'route/routePlant',//获取招募路线的信息
  RouteRun:WxApiRoot+ 'route/routeRun',//获取运行路线的信息

  SearchSite: WxApiRoot + "search/site",//搜索站点
  SearchPlant: WxApiRoot + "search/routePlant",//搜索招募线路3
  SearchRun: WxApiRoot + 'search/routeRun',  //搜索开通线路
  SearchHelper: WxApiRoot + 'search/helper',  //搜索帮助
  SearchClearHistory: WxApiRoot + 'search/clearhistory',  //搜索历史清楚

  CheckInfo:WxApiRoot+"check/info",//验证是否实名注册
  GetQRcode:WxApiRoot+"qrcode/getQRcode",//獲取二維碼，即是二維碼的下載，post
  
}