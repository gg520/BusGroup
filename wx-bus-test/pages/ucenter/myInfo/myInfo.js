
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo:'',
    gender:['男','女'],
    mobileIcon:'',//手机号码是否正确
    brithday:'2018-04-10',
    password:'1234567891023',

    showModal:false,//模态框显示
    showcheck:false,//旧密码验证
    shownewpw:false,//新密码显示
    oldpw:'',//旧密码
    newpw1:'',//新密码1
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
    if (user!=null&&user === 'Weixin_Driver') {//司机
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isDirver: true
      });
    } else if (user != null &&user === 'Weixin_Passenger') {//乘客
      this.setData({
        userInfo: userInfo,
        exitLoginBtn: false,
        isPassenger: true
      });
    }else{//其他为错误信息

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
  
    let _self=this;
    var isPhone = _self.testPhone(_self.data.userInfo.mobile)
    let citizenshipIcon=false;
    let mobileIcon=false;
    if (isPhone) {
        mobileIcon=true;
    }
    if (_self.testIP(_self.data.userInfo.citizenship)){
      citizenshipIcon=true;
    }
    _self.setData({
      mobileIcon: mobileIcon,
      citizenshipIcon: citizenshipIcon,
    })
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
  datePickerBindchange: function (e) {
    let userInfo = this.data.userInfo;
    userInfo.birthday=e.detail.value
    this.setData({
      userInfo:userInfo
    })
    console.log(this.data.userInfo)
    console.log(this.data.userInfo.birthday.length)
  },
  genderChange:function(e){
    let userInfo = this.data.userInfo;
    console.log(e.detail.value)
    userInfo.gender = e.detail.value=='0'?1:2
    this.setData({
      userInfo: userInfo
    })
    console.log(this.data.userInfo)
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
  testIP:function(ip){
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
  mobileChange:function(e){
    
    var _self = this
    let userInfo = this.data.userInfo;
    userInfo.mobile = e.detail.value
    _self.setData({ userInfo: userInfo })
    console.log(_self.data.userInfo)
    var isPhone = _self.testPhone(e.detail.value)
    console.log("isPhone"+isPhone)
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
  changeInfo:function(){
    //获取信息
    
    //将信息发送到后台
    if (this.data.userInfo.nickName.length < 0) {
      util.showErrorToast("昵称不能为空");
      return false;
    }
    if (!this.data.mobileIcon) {
      util.showErrorToast("手机号码错误");
      return false;
    }
    if (this.data.userInfo.name.length < 0) {
      util.showErrorToast("姓名不能为空");
      return false;
    }
    if (!this.testIP(this.data.userInfo.citizenship)) {
      util.showErrorToast("身份证不合法");
      return false;
    }
   
    util.request(
      api.ChangUserInfo,
      {
        userInfo: this.data.userInfo,
      }, 'POST').then(function (res) {
        console.log(res);
        if (res.errno === 0) {
          //成功
          util.showSuccessToast("提交成功");
          wx.setStorageSync('userInfo', res.data.userInfo);
          
        }else{
         
        }
      })
    
  },
  changpw:function(e){//每次修改密码将以前输入的记录清空
    var pwd = e.detail.value;
    this.setData({
      showModal: true,
      showcheck: true,//旧密码验证
      oldpw: '',//旧密码
      newpw1: '',//新密码1
      newpw2: '',//新密码2
    })
  },
  /**
   * showModal:false,//模态框显示
    showcheck:false,//旧密码验证
    shownewpw:false,//新密码显示
    oldpw:'',//旧密码
    newpw1:'',//新密码1
    newpw2: '',//新密码2
   */
  onCancel:function(){
    //取消
    this.setData({
      showModal: false,//模态框显示
      showcheck: false,//旧密码验证
      shownewpw: false,//新密码显示
      oldpw: '',//旧密码
      newpw1: '',//新密码1
      newpw2: '',//新密码2
    })
  },
  changChackpw:function(e){
    this.setData({
      oldpw:e.detail.value
    });
    
  },
  checkConfirm:function(e){
    if (!this.data.oldpw){
      util.showWarningToast("密码不能为空");
      return false;
    }
    //验证信息

    let that = this;
    util.request(
      api.CheckPassword,
      { password: this.data.oldpw}, 'POST').then(function (res) {
        console.log(res);
        if (res.errno === 0) {
          //成功，将模态框改成
          that.setData({
            showcheck: false,//旧密码验证
            shownewpw: true,//新密码显示
            // oldpw: '',//旧密码
            newpw1: '',//新密码1
            newpw2: '',//新密码2
          })
          
        }else if(res.errno===-1){
          util.showErrorToast("密码错误");
        }
      })
  
  },
  inputNew1Change:function(e){
    this.setData({
      newpw1: e.detail.value
    });
  },
  inputNew2Change: function (e) {
    this.setData({
      newpw2: e.detail.value
    });
  },
  newConfirm:function(){
    if (this.data.newpw2 && this.data.newpw1){//不为空
      if (this.data.newpw2 === this.data.newpw1){
        //将数据提交个给userInfo
        // var userInfo=this.data.userInfo;
        // userInfo.password = this.data.newpw2;
        //两次密码一致将密码更新
       
        util.request(
          api.ChangePassword,
          { 
            oldPassword: this.data.oldpw,//旧密码
            newPassword: this.data.newpw2,//新密码
          }, 'POST').then(function (res) {
            // console.log(res);
            if (res.errno === 0) {
              util.showSuccessToast("密码修改成功！");
            }
          });
      
        this.setData({
          // userInfo: userInfo,
          showModal: false,//模态框显示
          showcheck: false,//旧密码验证
          shownewpw: false,//新密码显示
          oldpw: '',//旧密码
          newpw1: '',//新密码1
          newpw2: '',//新密码2
        })
       
      }else{
        util.showWarningToast("两次密码不一致");
      }
    }else{
      util.showWarningToast("密码不能为空");
    }
  },
  upAcatar:function(){
    let userInfo=this.data.userInfo;
    let that=this;
    wx.chooseImage({
      success: function(res) {
        userInfo.avatarUrl=res.tempFilePaths;
        that.setData({
          userInfo:userInfo,
        });
        console.log(res.tempFilePaths)
        res.tempFiles;//文件上传
        wx.uploadFile({
          url: api.ChangeAvater, 
          filePath: res.tempFilePaths[0],
          name: 'passengeravater',
          formData: {
            'user': 'test'
          },
          header: {
            "Content-Type": "application/json",
            "X-Bus-Token": wx.getStorageSync("token"),
            "Connect_Platform": "Weixin_Passenger"
          },
          success: function (res) {
            var data = res.data
            //do something
          }
        })

      },
    })
  },
  changeNickName:function(e){
    if (e.detail.value.length>0){
      let userInfo=this.data.userInfo;
      userInfo.nickName = e.detail.value;
      this.setData({
        userInfo:userInfo,
      })
    }
  },
  changeAddress:function(e){
    if (e.detail.value.length > 0) {
      let userInfo = this.data.userInfo;
      userInfo.address = e.detail.value;
      this.setData({
        userInfo: userInfo,
      })
    }
  },
  changeName:function(e){
    if (e.detail.value.length > 0) {
      let userInfo = this.data.userInfo;
      userInfo.name = e.detail.value;
      this.setData({
        userInfo: userInfo,
      })
    }
  },
  changeCitizenship:function(e){
    if (e.detail.value.length > 0) {
      let userInfo = this.data.userInfo;
      userInfo.citizenship = e.detail.value;
      if(this.testIP(e.detail.value)){
        this.setData({
          userInfo: userInfo,
          citizenshipIcon:true
        })
        
      }else{
        this.setData({
          userInfo: userInfo,
          citizenshipIcon: false
        })
      }
    }
  }
})