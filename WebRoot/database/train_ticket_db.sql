/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50129
Source Host           : localhost:3306
Source Database       : train_ticket_db

Target Server Type    : MYSQL
Target Server Version : 50129
File Encoding         : 65001

Date: 2017-03-30 22:21:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `train_no` varchar(255) NOT NULL COMMENT '车次',
  `from` varchar(255) NOT NULL COMMENT '出发地',
  `start_time` varchar(255) NOT NULL COMMENT '出发时间',
  `to` varchar(255) NOT NULL COMMENT '目的地',
  `end_time` varchar(255) NOT NULL COMMENT '到站时间',
  `date` varchar(255) NOT NULL COMMENT '日期',
  `seat` varchar(255) NOT NULL COMMENT '席别',
  `carriage` varchar(255) NOT NULL COMMENT '车厢',
  `seat_no` varchar(255) NOT NULL COMMENT '座位号',
  `money` varchar(255) NOT NULL COMMENT '票价',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
