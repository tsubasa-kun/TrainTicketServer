/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50129
Source Host           : localhost:3306
Source Database       : train_ticket_db

Target Server Type    : MYSQL
Target Server Version : 50129
File Encoding         : 65001

Date: 2017-05-06 23:00:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `member_real_name` varchar(255) NOT NULL COMMENT '联系人真实姓名',
  `member_id_number` varchar(255) NOT NULL COMMENT '联系人身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) NOT NULL COMMENT '订单ID',
  `account` varchar(255) NOT NULL COMMENT '用户账号',
  `real_name` varchar(255) NOT NULL,
  `train_no` varchar(255) NOT NULL COMMENT '车次',
  `from_station` varchar(255) NOT NULL COMMENT '出发地',
  `start_time` varchar(255) NOT NULL COMMENT '出发时间',
  `to_station` varchar(255) NOT NULL COMMENT '目的地',
  `end_time` varchar(255) NOT NULL COMMENT '到站时间',
  `date` varchar(255) NOT NULL COMMENT '日期',
  `seat` varchar(255) NOT NULL COMMENT '席别',
  `carriage` varchar(255) NOT NULL COMMENT '车厢',
  `seat_no` varchar(255) NOT NULL COMMENT '座位号',
  `money` varchar(255) NOT NULL COMMENT '票价',
  `type` varchar(255) NOT NULL COMMENT '车票类型',
  `pay_status` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否完成:0未完成1完成2退票3改签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for tickets
-- ----------------------------
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `train_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '火车ID',
  `train_code` varchar(255) NOT NULL COMMENT '车次',
  `start_date` varchar(255) NOT NULL COMMENT '日期',
  `start_station_name` varchar(255) NOT NULL COMMENT '出发站',
  `start_time` varchar(255) NOT NULL COMMENT '发车时间',
  `lishi` varchar(255) NOT NULL COMMENT '历时',
  `to_station_name` varchar(255) NOT NULL COMMENT '目的地',
  `arrive_time` varchar(255) NOT NULL COMMENT '到达时间',
  `swz_num` varchar(255) DEFAULT '0' COMMENT '商务座数量',
  `zy_num` varchar(255) DEFAULT '0' COMMENT '一等座数量',
  `ze_num` varchar(255) DEFAULT '0' COMMENT '二等座数量',
  `yz_num` varchar(255) DEFAULT '0' COMMENT '硬座数量',
  `yw_num` varchar(255) DEFAULT '0' COMMENT '硬卧数量',
  `wz_num` varchar(255) DEFAULT '0' COMMENT '无座数量',
  `swz_money` varchar(255) DEFAULT '0' COMMENT '商务座价格',
  `zy_money` varchar(255) DEFAULT '0' COMMENT '一等座价格',
  `ze_money` varchar(255) DEFAULT '0' COMMENT '二等座价格',
  `yz_money` varchar(255) DEFAULT '0' COMMENT '硬座价格',
  `yw_money` varchar(255) DEFAULT '0' COMMENT '硬卧价格',
  `wz_money` varchar(255) DEFAULT '0' COMMENT '无座价格',
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tickets
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `id_number` varchar(255) NOT NULL COMMENT '身份证号码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
