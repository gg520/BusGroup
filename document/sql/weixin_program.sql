/*
Navicat MySQL Data Transfer

Source Server         : 139.199.166.250_腾讯云
Source Server Version : 50626
Source Host           : 139.199.166.250:3306
Source Database       : weixin_program

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-06-25 17:01:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  `admin_owner` varchar(255) NOT NULL,
  `admin_power` int(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '阳光公司', '8');

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus` (
  `bus_num` int(11) NOT NULL AUTO_INCREMENT,
  `bus_id` varchar(255) NOT NULL,
  `bus_type` varchar(255) NOT NULL,
  `bus_owner` varchar(255) NOT NULL,
  `characters` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `VIN` varchar(255) NOT NULL,
  `engine_num` varchar(255) NOT NULL,
  `registration_date` date NOT NULL,
  `oppen_date` date NOT NULL,
  `seating` int(255) NOT NULL,
  `bus_status` int(255) NOT NULL DEFAULT '0' COMMENT '0空闲，1已用',
  `bus_mark` int(255) NOT NULL DEFAULT '0' COMMENT '0可用，1不可用',
  PRIMARY KEY (`bus_num`),
  UNIQUE KEY `engine_num` (`engine_num`),
  UNIQUE KEY `bus_id` (`bus_id`),
  KEY `bus_type` (`bus_type`),
  KEY `seating` (`seating`),
  KEY `bus_status` (`bus_status`),
  KEY `bus_mark` (`bus_mark`)
) ENGINE=InnoDB AUTO_INCREMENT=3136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus
-- ----------------------------
INSERT INTO `bus` VALUES ('3132', '豫A34579', 'A1', '回来了', '公司运营', '及', '1', '111', '2018-05-17', '2018-05-24', '20', '1', '0');
INSERT INTO `bus` VALUES ('3133', 'adsa', 'A1', 'das', '私营', 'dsa', 'dsa', 'dsa', '2018-06-06', '2018-06-09', '20', '2', '0');
INSERT INTO `bus` VALUES ('3134', '京A9856', 'A1', '125', '私营', '223', '333', '3633', '2018-05-02', '2018-06-13', '15', '0', '0');
INSERT INTO `bus` VALUES ('3135', 'das', 'A1', 'dsa', '公司运营', 'dsa', 'dgaga', 'ga', '2018-06-16', '2018-06-06', '20', '1', '0');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `driver_num` int(11) NOT NULL AUTO_INCREMENT,
  `driver_id` varchar(255) NOT NULL COMMENT '登录账号',
  `driver_name` varchar(255) DEFAULT NULL,
  `driver_gender` varchar(255) NOT NULL,
  `driver_password` varchar(255) NOT NULL,
  `driver_avater` varchar(255) DEFAULT NULL COMMENT '头像',
  `driver_citizenship` varchar(255) NOT NULL,
  `driver_licence` varchar(255) DEFAULT NULL COMMENT '驾驶证号码',
  `driver_nationality` varchar(255) DEFAULT NULL,
  `driver_address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `first_getlicence` date DEFAULT NULL,
  `driver_mobile` varchar(255) DEFAULT NULL,
  `register_ip` varchar(255) NOT NULL,
  `register_time` datetime NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `driving_type` varchar(255) DEFAULT NULL,
  `integral` int(255) DEFAULT '100' COMMENT '信誉积分',
  `driver_mark` int(255) NOT NULL DEFAULT '0' COMMENT '0 私人司机 1 公司司机',
  `driver_status` int(255) NOT NULL DEFAULT '1' COMMENT '0 可用、1 审核中、2 停用、3 删除',
  PRIMARY KEY (`driver_num`),
  UNIQUE KEY `driver_id` (`driver_id`),
  UNIQUE KEY `driver_citizenship` (`driver_citizenship`),
  KEY `driving_type` (`driving_type`),
  KEY `integral` (`integral`),
  KEY `driver_mark` (`driver_mark`),
  KEY `driver_status` (`driver_status`)
) ENGINE=InnoDB AUTO_INCREMENT=33337 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('11111', '123456', '陈二狗', '女', 'e10adc3949ba59abbe56e057f20f883e', 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', '4108831995', '333333337333333332', '中国', '中原工学院', '2018-05-24', '2018-05-16', '13592573332', '192.168.1.1', '2018-05-01 08:55:23', '2018-05-16 08:55:27', '111', '到底', '100', '0', '0');
INSERT INTO `driver` VALUES ('22222', '234567', '接口', '女', 'e10adc3949ba59abbe56e057f20f883e', 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', '4242242', '333333333333333330', '中国', '中原工学院', '2018-05-02', '2018-05-07', '13592573333', '192.168.1.1', '2018-05-09 08:58:20', '2018-05-24 08:58:23', '222', '来了', '200', '0', '0');
INSERT INTO `driver` VALUES ('33333', '345678', '哈哈', '女', 'e10adc3949ba59abbe56e057f20f883e', 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', '5553335353', '333333333333333331', '中国', '中原工学院', '2018-05-09', '2018-05-11', '13592573329', '192.168.1.1', '2018-05-04 08:59:40', '2018-05-06 08:59:43', '333', '分页', '230', '0', '3');
INSERT INTO `driver` VALUES ('33334', '158935', '陈二狗', '男', 'e10adc3949ba59abbe56e057f20f883e', 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', '333333333333333333', '333333333333333336', '中国', '中原工学院', '2018-06-24', '2018-06-24', '13592573322', '192.168.1.1', '2018-06-24 17:33:41', null, null, 'A1', '100', '0', '1');
INSERT INTO `driver` VALUES ('33336', '383493', '陈二狗', '男', 'e10adc3949ba59abbe56e057f20f883e', 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png', '333333333333333332', '333333333333333332', '中国', '中原工学院', '2018-06-24', '2018-06-24', '13592573328', '', '2018-06-24 17:39:27', null, null, 'A1', '100', '0', '1');

-- ----------------------------
-- Table structure for driver_bus_route
-- ----------------------------
DROP TABLE IF EXISTS `driver_bus_route`;
CREATE TABLE `driver_bus_route` (
  `d_b_r_id` int(255) NOT NULL AUTO_INCREMENT,
  `driver_id` varchar(255) DEFAULT NULL,
  `bus_id` varchar(255) DEFAULT NULL,
  `route_id` int(255) DEFAULT NULL,
  `dirver_time` datetime DEFAULT NULL,
  `bus_time` datetime DEFAULT NULL,
  `route_status` int(11) NOT NULL DEFAULT '0',
  `driver_out_time` datetime DEFAULT NULL,
  `driver_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`d_b_r_id`),
  KEY `driver_id` (`driver_id`),
  KEY `bus_id` (`bus_id`),
  KEY `route_id` (`route_id`),
  KEY `route_status` (`route_status`),
  KEY `driver_status` (`driver_status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver_bus_route
-- ----------------------------
INSERT INTO `driver_bus_route` VALUES ('1', '123456', '1', '2', '2018-06-20 20:20:36', '2018-06-21 20:20:42', '0', '2018-06-27 20:20:46', '0');

-- ----------------------------
-- Table structure for helper
-- ----------------------------
DROP TABLE IF EXISTS `helper`;
CREATE TABLE `helper` (
  `help_Id` int(255) NOT NULL AUTO_INCREMENT,
  `help_name` varchar(255) NOT NULL,
  `help_message` varchar(255) NOT NULL,
  PRIMARY KEY (`help_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of helper
-- ----------------------------
INSERT INTO `helper` VALUES ('1', '怎么团', '你说怎么团');
INSERT INTO `helper` VALUES ('2', '什么是团招募', '你猜');
INSERT INTO `helper` VALUES ('3', '招募多少人开团', '招到我们满意');
INSERT INTO `helper` VALUES ('4', '开团啊', '缉私分局');
INSERT INTO `helper` VALUES ('5', '是多方设法说服', '发射点发生');
INSERT INTO `helper` VALUES ('6', '反对诉讼法首发式', '十分十分十分士大夫');
INSERT INTO `helper` VALUES ('7', '快来升级拉开距离撒', 'iowrqoifska ');
INSERT INTO `helper` VALUES ('8', '我开始奋斗可怜的就开发建设看了', '请我irwqiorf');
INSERT INTO `helper` VALUES ('9', '哦', 'ijcixzjsf');
INSERT INTO `helper` VALUES ('10', '撒娇分厘卡', '我却认为其辱');
INSERT INTO `helper` VALUES ('11', '香蕉哲学哦', '阿森纳烦恼什么');

-- ----------------------------
-- Table structure for overall_config
-- ----------------------------
DROP TABLE IF EXISTS `overall_config`;
CREATE TABLE `overall_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `routeInquiryPeriod` int(11) NOT NULL DEFAULT '7' COMMENT '线路招募周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of overall_config
-- ----------------------------
INSERT INTO `overall_config` VALUES ('1', '7');

-- ----------------------------
-- Table structure for passenger
-- ----------------------------
DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passenger_citizenship` varchar(255) DEFAULT NULL,
  `passenger_nickname` varchar(255) DEFAULT NULL,
  `passenger_name` varchar(255) DEFAULT NULL,
  `passenger_password` varchar(255) DEFAULT NULL,
  `passenger_gender` varchar(255) NOT NULL,
  `passenger_address` varchar(255) DEFAULT NULL,
  `passenger_birthday` date DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `passenger_mobile` varchar(255) DEFAULT NULL,
  `passenger_avater` varchar(255) NOT NULL,
  `weixin_openid` varchar(255) DEFAULT NULL,
  `passenger_status` int(255) NOT NULL DEFAULT '0',
  `fist_login_time` datetime NOT NULL,
  `fist_login_ip` varchar(255) NOT NULL,
  `deleted` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `passenger_citizenship` (`passenger_citizenship`),
  KEY `passenger_status` (`passenger_status`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passenger
-- ----------------------------
INSERT INTO `passenger` VALUES ('1', '41272819950914333X', '小工头2号', '郭苏州', '25f9e794323b453885f5181f1b624d0b', '男', '河南省沈丘县', '1995-11-06', '2018-06-25 08:51:52', 'e9zri89g1ag0gjd2sh51r4qhv03fuezo', '13592573327', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLTSSMGTh7KLk61qOAeroGCr0kFOeQIQib45geM6ciablhXDre0icxgQWS0tWsvf2eKBlhHwQwynYBBQ/132', 'odcAb5PT5gghksRoj9hzUX_VAHiY', '0', '2018-05-11 16:07:21', '192.168.1.103', '0');
INSERT INTO `passenger` VALUES ('5', '411521199409013314', 'soul', 'asss', '25f9e794323b453885f5181f1b624d0b', '男', null, null, '2018-06-23 18:26:06', '', '13339876780', 'https://wx.qlogo.cn/mmopen/vi_32/EicgVNQkSBEicg6hMkicY7l1caIKv1lnJZbkBn8ZIokPWPicYLHfVTtF02PuIvF4VOyibu6GPeypvScwhvNTzwqnYSQ/132', 'o9Mb05d0H3phz1BUnftLzgGMTJ-w', '0', '2018-06-23 18:19:22', '', '0');
INSERT INTO `passenger` VALUES ('6', '444444444444444444', 'soul', '陈宝童', null, '男', 'qqqqq', '2018-06-23', '2018-06-23 18:29:47', '', '12345678901', 'https://wx.qlogo.cn/mmopen/vi_32/WNVcSSibFIkhys6kdI2rJFA1hVUPCPrEfWw0x0A21E5RrQrjZGBoIdVmuRI3UNU654ib9MvdKdCuSGibS4l689rzQ/132', 'odcAb5PQ6fxyLrMP1THTPQi45nuA', '0', '2018-06-23 18:28:27', '', '0');

-- ----------------------------
-- Table structure for passenger_route
-- ----------------------------
DROP TABLE IF EXISTS `passenger_route`;
CREATE TABLE `passenger_route` (
  `p_r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `passenger_id` int(255) NOT NULL,
  `route_id` int(255) NOT NULL,
  `reserve_day` int(11) NOT NULL DEFAULT '0' COMMENT '订购的天数',
  `pay_money` double(255,0) DEFAULT NULL COMMENT '花费的总钱数',
  `creat_time` datetime DEFAULT NULL COMMENT '创建的时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `reserve_days` longtext COMMENT '订购的日期列表',
  `start_status` int(255) NOT NULL COMMENT '0 收藏（待支付） 1 已下单  2 下单未支付（暂存数据，下单后更新数据） ',
  PRIMARY KEY (`p_r_id`),
  KEY `passenger_id` (`passenger_id`),
  KEY `route_id` (`route_id`),
  KEY `creat_time` (`creat_time`),
  KEY `end_time` (`end_time`),
  KEY `start_status` (`start_status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passenger_route
-- ----------------------------
INSERT INTO `passenger_route` VALUES ('1', null, '1', '4', '0', null, '2018-06-14 18:31:29', null, null, '0');
INSERT INTO `passenger_route` VALUES ('3', null, '1', '2', '0', null, '2018-06-16 18:31:43', null, null, '0');
INSERT INTO `passenger_route` VALUES ('4', '20180620183151', '1', '2', '22', '40', '2018-06-20 18:31:51', null, '', '1');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `route_id` int(255) NOT NULL AUTO_INCREMENT,
  `creat_user` int(255) NOT NULL,
  `creat_time` datetime NOT NULL COMMENT '线路创建时间',
  `route_status` int(255) NOT NULL DEFAULT '0' COMMENT '0 待审核、1 审核通过、2 审核不通过、3 招募中、4 招募失败、5 开通、6 过期',
  `recruit_time` int(11) DEFAULT NULL COMMENT '周期单位天',
  `run_time` varchar(255) NOT NULL COMMENT '开始线路时间',
  `start_site` varchar(255) NOT NULL,
  `start_coord` varchar(255) DEFAULT NULL COMMENT '开始地点坐标 纬度，经度',
  `end_site` varchar(255) NOT NULL COMMENT '结束地点名称',
  `end_coord` varchar(255) DEFAULT NULL COMMENT '结束地点坐标，纬度，经度',
  `station_id` varchar(255) DEFAULT NULL COMMENT '站点ID，多个用逗号分隔',
  `start_time` varchar(255) NOT NULL COMMENT '发车时间',
  `end_time` varchar(255) NOT NULL COMMENT '预计结束时间',
  `price` double(255,0) DEFAULT NULL COMMENT '单价',
  `recruit_num` int(255) DEFAULT NULL COMMENT '招募人数',
  `start_recruit` varchar(255) DEFAULT NULL COMMENT '线路运行开始时间',
  `ends_recruit` varchar(255) DEFAULT NULL COMMENT '线路运行结束时间',
  `massage` longtext COMMENT '线路描述描述',
  PRIMARY KEY (`route_id`),
  KEY `creat_user` (`creat_user`),
  KEY `route_status` (`route_status`),
  KEY `start_site` (`start_site`),
  KEY `start_coord` (`start_coord`),
  KEY `end_site` (`end_site`),
  KEY `end_coord` (`end_coord`),
  KEY `start_time` (`start_time`),
  KEY `end_time` (`end_time`),
  KEY `price` (`price`),
  KEY `recruit_num` (`recruit_num`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES ('1', '1', '2018-05-10 09:03:02', '3', null, '7', '新政', '10,20', '龙湖', '60,70', '1,2,3', '16:11', '09:03', '12', '10', '2018-06-1', '2018-07-02', null);
INSERT INTO `route` VALUES ('2', '1', '2018-05-14 09:11:30', '1', null, '7', '龙湖', '34.74725,113.62493', '新政', '20,30', '2,3', '09:11', '09:11', '2', '20', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('3', '1', '2018-04-18 09:15:51', '1', null, '7', '樱桃沟', '50,70', '新政', '20,30', '2,1', '09:14', '09:14', '20', '20', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('4', '1', '2018-05-10 09:03:02', '5', null, '7', '新政', '10,20', '龙湖', '60,70', '1,2,3', '09:11', '09:03', '3', '10', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('5', '1', '2018-05-14 09:11:30', '1', null, '7', '龙湖', '34.74725,113.62493', '新政', '20,30', '2,3', '09:11', '09:11', '4', '20', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('6', '1', '2018-04-18 09:15:51', '2', null, '7', '樱桃沟', '50,70', '新政', '20,30', '2,1', '18:14', '09:14', '20', '20', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('7', '1', '2018-05-29 11:31:09', '5', null, '8', '中原工学院南区', '80,90', '郑州火车站', '21,22', '2,1', '08:00', '17:00', '5', '15', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('8', '1', '2018-05-29 11:33:20', '3', null, '21', '中原工学院南区', '100,500', '郑州火车站', '12,35', '2,1', '08:00', '17:00', '6', '15', '2018-06-20', '2018-07-20', null);
INSERT INTO `route` VALUES ('18', '1', '2018-06-23 16:20:42', '0', null, '28', '二测起点', null, '二测终点', null, null, '06:00', '15:00', null, null, '2018-06-30', '2018-07-28', '二测备注信息');
INSERT INTO `route` VALUES ('19', '1', '2018-06-23 20:34:58', '0', null, '31', '中原工学院南区图书馆', null, '升达大学', null, null, '20:34', '21:34', null, null, '2018-07-14', '2018-08-14', '测试数据');

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `station_id` int(255) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(255) NOT NULL,
  `station_coord` varchar(255) NOT NULL COMMENT '站点坐标 纬度，经度',
  `station_status` int(255) NOT NULL DEFAULT '0' COMMENT '站点状态 0可用，1过期',
  `station_describe` varchar(255) DEFAULT NULL COMMENT '站点描述',
  PRIMARY KEY (`station_id`),
  UNIQUE KEY `station_coord` (`station_coord`),
  KEY `station_status` (`station_status`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', '新政', '20,30', '1', '看看大家是否');
INSERT INTO `station` VALUES ('2', '龙湖', '34.74725,113.62493', '1', 'dfsfsfsf');
INSERT INTO `station` VALUES ('3', '郑州', '40,60', '1', 'dsfsfsfsfsfewew');
INSERT INTO `station` VALUES ('4', '樱桃沟', '50,70', '1', 'sdfsfwerbg');
INSERT INTO `station` VALUES ('5', '黄山', '12,45', '1', '这是一个神奇的地方');
INSERT INTO `station` VALUES ('6', '中原工学院南区', '100,500', '1', null);
INSERT INTO `station` VALUES ('7', '郑州火车站', '12,35', '1', null);
INSERT INTO `station` VALUES ('8', 'das', 'adas', '1', null);
