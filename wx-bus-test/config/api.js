//服务器的信息
// var WxApiRoot ="http://47.94.232.43:8888/weixin/";//测试本地后台数据
var WxApiRoot = "http://139.199.166.250:8888/weixin/";//腾讯服务器
// var WxApiRoot = "http://localhost:8888/weixin/";//测试本地后台数据

module.exports = {
  AuthLoginByWeixin: WxApiRoot + 'auth/loginByWeixin',//微信登录
  AuthReset: WxApiRoot + 'auth/reset', //重置密码
  AuthRegister: WxApiRoot + 'auth/register', //注册账号
  AuthLoginByAccount: WxApiRoot + 'auth/login', //账号登录

  ChangUserInfo:WxApiRoot+"user/changeUserInfo",//修改个人信息，完善个人信息
  Payment:WxApiRoot+'pay/',//支付

  CollectGet:WxApiRoot+'user/getCollect',//获取收藏信息
  CollectAdd:WxApiRoot+'user/addCollect',//添加到收藏
  CollectClear:WxApiRoot+'user/clearCollect',//删除收藏的信息

  RouteInquiry: WxApiRoot+'route/inquiryRoute',//线路征集链接
  RouteInfo: WxApiRoot + 'route/routeInfo',//获取路线的详细信息链接
  RoutePlant: WxApiRoot + 'route/routePlant',//获取招募路线的信息
  RouteRun:WxApiRoot+ 'route/routeRun',//获取运行路线的信息
  RouteInquiryPeriod : WxApiRoot + 'route/routePeriod',//获取线路的招募周期

  SearchSite: WxApiRoot + "search/site",//搜索站点
  SearchPlant: WxApiRoot + "search/routePlant",//搜索招募线路3
  SearchRun: WxApiRoot + 'search/routeRun',  //搜索开通线路
  SearchRoute:WxApiRoot+'search/routeAll',//搜索所有站点

  CheckPassword:WxApiRoot+'check/password',//验证密码是否正确
  CheckInfo:WxApiRoot+"check/info",//验证是否完善信息
  GetQRcode:WxApiRoot+"qrcode/getQRcode",//獲取二維碼，即是二維碼的下載，post
  
}