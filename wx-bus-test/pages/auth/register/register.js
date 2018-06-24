
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{
      name: '', 
      mobile: '', 
      gender: '男', 
      birthday: '', 
      address: '', 
      licence: '', 
      firstgetlicence: '', 
      password: '', 
      citizenship: '', 
      bustype: 'A1', 
      avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', //头像
      frontFilePaths: '/static/images/icon_add.png',//身份证正面
      contraryFilePaths: '/static/images/icon_add.png',//反面
      },
    bustype: ['A1', 'A2','A3'],
    typeindex:0,
    genderindex:0,
    gender:['男','女'],
    mobileIcon: '',//手机号码是否正确
    brithday: '2018-04-10',
    password: '',
    imageflag:[],
    showModal: false,//模态框显示
    showcheck: false,//旧密码验证
    shownewpw: false,//新密码显示
    oldpw: '',//旧密码
    newpw1: '',//新密码1
    newpw2: '',//新密码2
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取个人信息
    //判断司机还是乘客
    var user = wx.getStorageSync("user");
    let userInfo = wx.getStorageSync("userInfo");
    console.log(userInfo);
    if (user != null && user === 'Weixin_Driver') {//司机
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isDirver: true
      });
    } else if (user != null && user === 'Weixin_Passenger') {//乘客
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isPassenger: true
      });
    } else {//其他为错误信息

    }

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

    let _self = this;
    var isPhone = _self.testPhone(_self.data.userInfo.mobile)
    console.log("isPhone" + isPhone)
    if (isPhone) {
      console.log(isPhone)
      _self.setData({//设置正确性
        mobileIcon: 'true'
      })
    } else {
      _self.setData({
        mobileIcon: 'false'
      })
    }
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //测试手机号码是否正确
  testPhone: function (s) {
    if (s != null && s) {
      var length = s.length
      if (length = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(s)) {
        return true
      } else {
        return false
      }
    }
  },
  //获取性别选定向
  genderChange:function(e)
  {
    let userinfo=this.data.userInfo;
    userinfo.gender=this.data.gender[e.detail.value];
    this.setData({
      genderindex: e.detail.value,
      userInfo:userinfo
    })
  },
  //获取驾驶车型选定项
  bustypeChange:function(e)
  {
    let userinfo = this.data.userInfo;
    userinfo.bustype= this.data.bustype[e.detail.value];
    this.setData({
      typeindex: e.detail.value,
      userInfo: userinfo
    })
  },
  //获取姓名
  Nameinput:function(e){
    let userinfo = this.data.userInfo;
    userinfo.name = e.detail.value;
    this.setData({
      userInfo: userinfo
    })

  },
  //获取电话
  mobileinput:function(e){
    let userinfo = this.data.userInfo;
    userinfo.mobile = e.detail.value;
    if (this.testPhone(e.detail.value)){
      this.setData({
        userInfo: userinfo,
        mobileIcon:true
      })
    }else{
      this.setData({
        userInfo: userinfo,
        mobileIcon: false
      })
    }
    
  },
  //获取出生日期
  birthdayinput:function(e)
  {
    let userinfo = this.data.userInfo;
    userinfo.birthday = e.detail.value;
    this.setData({
      userInfo: userinfo
    })
    console.log(this.data.userInfo)
    console.log(this.data.userInfo.birthday.length)
  },
  //获取家庭住址
  Addressinput:function(e){
    let userinfo = this.data.userInfo;
    userinfo.address= e.detail.value;
    this.setData({
      userInfo: userinfo
    })
  },
  //获取初次领证日期
  firstgetlicenceinput:function(e)
{
    let userinfo = this.data.userInfo;
    userinfo.firstgetlicence = e.detail.value;
    this.setData({
      userInfo: userinfo
    })
},
//获取密码
  changepassword:function(e){
    let userinfo = this.data.userInfo;
    userinfo.password = e.detail.value;
    this.setData({
      userInfo: userinfo
    })
  },
  //获取确认密码
  passwordagain:function(e)
  {
    this.setData({
      password:e.detail.value
    })
  },
  //获取身份证号码
  changeCitizenship:function(e){
    let userinfo = this.data.userInfo;
    userinfo.citizenship = e.detail.value;
    userinfo.licence=e.detail.value;
    if (this.checkcitizenship(e.detail.value)){
      this.setData({
        userInfo: userinfo,
        citizenshipIcon:true,
      })
    }else{
      this.setData({
        userInfo: userinfo,
        citizenshipIcon: false,
      })
    }
    
  },
  //头像
  upAvater: function () {
    let that = this;
    let userinfo = that.data.userInfo;
    let image = that.data.imageflag;
    wx.chooseImage({
      success: function (res) {
        userinfo.avatarUrl = res.tempFilePaths;
        image.push(0);
        that.setData({
          userInfo:userinfo,
          imageflag:image,
        })
      },
    })
    console.log("长度"+this.data.imageflag.length)
  },//身份证正面
  upFront: function () {
    let that = this;
    wx.chooseImage({
      success: function (res) {
        let userinfo = that.data.userInfo;
        let image = that.data.imageflag;
        userinfo.frontFilePaths= res.tempFilePaths;
        image.push(0);
        that.setData({
          userInfo: userinfo,
          imageflag: image,
        })
      },
    })
    console.log("长度" + this.data.imageflag.length)
  },
      //身份证反面
  upContrary: function () {
    let that = this;
    wx.chooseImage({
      success: function (res) {
        let userinfo = that.data.userInfo;
        let image =that.data.imageflag;
        userinfo.contraryFilePaths = res.tempFilePaths;
        image.push(0);
        that.setData({
          userInfo: userinfo,
          imageflag: image,
        })
      },
    })
    console.log("长度" + this.data.imageflag.length)
  },
  mobileChange:function (e) {

    var _self = this
    let userInfo = this.data.userInfo;
    userInfo.mobile = e.detail.value
    _self.setData({ userInfo: userInfo })
    console.log(_self.data.userInfo)
    var isPhone = _self.testPhone(e.detail.value)
    console.log("isPhone" + isPhone)
    if (isPhone) {
      console.log(isPhone)
      _self.setData({//设置正确性
        mobileIcon: 'true'
      })
    } else {
      _self.setData({
        mobileIcon: 'false'
      })
    }
  },
 
  //密码验证
  checkpassword: function (password) {
    if (password.length<5||password.length>8) {
      return false;
    }
    else{
      return true;
    }
  },
  //确认密码验证
  checkpasswordagain:function(password,passwordagain)
  {
    if(password!=passwordagain)
    {
      return false;
    }
    else{
      return true;
    }
  },
  //验证姓名，准驾车型，出生日期，初次领证日期
  checknameand:function(name,bustype,birthday,firstgetlicence)
  {
    if (name == '' || birthday == '' ||bustype==''||firstgetlicence=='')
    {
      return false;
    }
    else
    {
      return true;
    }

  },
  testPhone: function (s) {
    if (s != null && s) {
      var length = s.length
      if (length = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(s)) {
        return true
      } else {
        return false
      }
    }
  },
  //验证手机号格式
  checkphone:function(mobile)
  {
    if (!this.testPhone(mobile)) 
    {
      return false
    }
    else{
      return true
    }
  },
  //验证身份证格式
  checkcitizenship: function (ip) {
    if (ip != null && ip) {
      console.log(ip)
      var length = ip.length
      if (ip = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(ip)) {
        return true
      } else {
        return false
      }
    }
  },
  checkimagenum(num)
  {
    if(num!=3)
    return false;
    else
    return true;
  },
  register: function () {

    if (!this.checknameand(this.data.userInfo.name, this.data.userInfo.bustype, this.data.userInfo.birthday, this.data.userInfo.firstgetlicence))
    {
      util.showErrorToast("请补全注册信息")
    }
    else if (!this.checkpassword(this.data.userInfo.password))
    {
      util.showErrorToast("密码最少5位字符最多8位字符")
    }
    else if (!this.checkpasswordagain(this.data.password, this.data.userInfo.password))
    {
      util.showErrorToast("两次密码不一致")
    }
    else if (!this.checkphone(this.data.userInfo.mobile))
    {
      util.showErrorToast("手机号格式不正确")
    }
    else if (!this.checkcitizenship(this.data.userInfo.citizenship))
    {
      util.showErrorToast("证件格式不正确")
    }
    else if (!this.checkimagenum(this.data.imageflag.length))
    {
      util.showErrorToast("请补全图像信息")
    }
    else{
    //注册信息提交到后台
  //  console.log(this.data.startsite)
    util.request(
      api.AuthRegister,
      {
        name: this.data.userInfo.name,
        mobile: this.data.userInfo.mobile,
        gender: this.data.userInfo.gender,
        birthday: this.data.userInfo.birthday,
        address: this.data.userInfo.address,
        licence: this.data.userInfo.licence,
        firstgetlicence: this.data.userInfo.firstgetlicence,
        password: this.data.userInfo.password,
        citizenship: this.data.userInfo.citizenship,
        bustype: this.data.userInfo.bustype, 
        avatarUrl: this.data.userInfo.avatarUrl,
      }, 'POST').then(function (res) {
        console.log(res);
        wx.hideLoading();
        if (res.errno === 0) {
          //成功
          wx.showModal({
            title: '注册成功',
            confirmColor: '#b4282d',
            content: '注册号码：' + res.data.driverId,
            success: function (res) {
              if (res.confirm) {
                wx.navigateBack({
                  delta: 1
                })
              }
            }
          });
        }
        else {
          util.showErrorToast(res.errmsg)
          // wx.navigateBack({
          //   delta: 1
          // })
        }
      })
    wx.hideLoading();
    }
  },
})