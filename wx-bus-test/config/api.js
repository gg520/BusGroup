//服务器的信息

// var WxApiRoot = "http://139.199.166.250:8888/weixin/";//腾讯服务器
var WxApiRoot = "http://localhost:8888/weixin/";//测试本地后台数据

module.exports = {
  // Test:WxApiRoot+"test/test2",//测试数据，只用于开发前台测试接口连接问题

  AuthLoginByWeixin: WxApiRoot + 'auth/loginByWeixin',//微信登录
  AuthReset: WxApiRoot + 'auth/reset', //重置密码
  AuthRegister: WxApiRoot + 'auth/register', //注册账号
  AuthLoginByAccount: WxApiRoot + 'auth/login', //账号登录

  ChangUserInfo:WxApiRoot+"user/changeUserInfo",//修改个人信息，完善个人信息
  ChangePassword:WxApiRoot+"user/changePassword",//修改密码
  ChangeAvater: WxApiRoot +"file/submitPicture",//修改头像

  Payment: WxApiRoot +'pay/prepay',//支付

  OrderList: WxApiRoot +"order/orderList",//获取订单信息
  OrderAdd:WxApiRoot+"order/orderAdd",//添加订单

  CollectGet:WxApiRoot+'collect/getCollect',//获取收藏信息
  CollectAdd: WxApiRoot +'collect/addCollect',//添加到收藏
  CollectClear: WxApiRoot +'collect/clearCollect',//删除收藏的信息

  RouteInquiry: WxApiRoot+'route/inquiryRoute',//线路征集链接
  RouteInfo: WxApiRoot + 'route/routeInfo',//获取路线的详细信息链接
  RoutePlant: WxApiRoot + 'route/routePlant',//获取招募路线的信息
  RouteRun:WxApiRoot+ 'route/routeRun',//获取运行路线的信息
  RouteInquiryPeriod : WxApiRoot + 'route/routePeriod',//获取线路的招募周期
  RouteAll: WxApiRoot + 'route/queryAllReoute',//获取所有正在招募和运行的线路

  SearchSite: WxApiRoot + "search/site",//搜索站点
  SearchPlant: WxApiRoot + "search/routePlant",//搜索招募线路3
  SearchRun: WxApiRoot + 'search/routeRun',  //搜索开通线路
  SearchRoute: WxApiRoot +'search/routeAll',//搜索所有线路

  CheckPassword:WxApiRoot+'check/password',//验证密码是否正确
  CheckInfo:WxApiRoot+"check/info",//验证是否完善信息
  CheckPDB:WxApiRoot+'check/checkPDB',//验证乘客和该线路的关系，

  GetQRcode:WxApiRoot+"qrcode/getQRcode",//獲取二維碼，即是二維碼的下載，post
  GetDriveQRcode: WxApiRoot + 'qrcode/getCheckQRcode',//获取验票二维码

  HelpList:WxApiRoot+"help/helpList",//随机获取十个帮助信息

  SystemMessage: WxApiRoot +"system/getNotice",//获取系统公告

  GetNotify: WxApiRoot + 'user/getNotify',//获取通知信息
  ReadNotify:WxApiRoot+'user/readNotify',//已读设置

  //司机端接口
  FeedBack: WxApiRoot + 'help/feedBackInfo',//反馈信息

  //司机端接口
  // FeedBack: WxApiRoot + 'feedBack/feedBackInfo',//反馈信息
  FindBusInfo: WxApiRoot + 'driver/findBusInfo',//查询车辆信息
  Bindcar: WxApiRoot + 'bind/bindCar',//扫码绑定车辆
  CheckTicket: WxApiRoot + 'check/checkTicket',//扫码验证乘客是否可以乘车
 

  FindDriverInfo: WxApiRoot + 'user/findDriverInfo',//查询司机信息信息
  ModifyDriverInfo: WxApiRoot + 'user/modifyDriverInfo',//修改司机信息

  FindTask: WxApiRoot + 'driver/findTask',//查询司机所有任务记录
  FindTaskByTime: WxApiRoot + 'driver/findTaskByTime',//按时间查询司机某一天的任务记录
  FindCurrentRoad: WxApiRoot + 'driver/findCurrentRoad',//查询司机当前任务

  TaskList: WxApiRoot +'task/getDriverTask',



  
}