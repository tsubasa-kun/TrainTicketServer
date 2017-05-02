/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50129
Source Host           : localhost:3306
Source Database       : train_ticket_db

Target Server Type    : MYSQL
Target Server Version : 50129
File Encoding         : 65001

Date: 2017-05-02 22:31:20
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('1', '3', 'Cookie', '322355199900009134');
INSERT INTO `members` VALUES ('2', '3', '幾瑆灀', '333258190001101478');
INSERT INTO `members` VALUES ('3', '4', '皮卡丘', '230256133608541697');
INSERT INTO `members` VALUES ('4', '4', '喷火龙', '111111111111111111');
INSERT INTO `members` VALUES ('5', '4', '妙蛙种子', '111111111111111112');
INSERT INTO `members` VALUES ('6', '4', '杰尼龟', '111111111111111113');
INSERT INTO `members` VALUES ('7', '4', '太阳珊瑚', '111111111111112222');
INSERT INTO `members` VALUES ('8', '4', '黏美露龙', '521521521521521521');
INSERT INTO `members` VALUES ('10', '4', '迷拟Q', '785612658796432531');
INSERT INTO `members` VALUES ('11', '3', '皮卡丘', '111111111111111111');
INSERT INTO `members` VALUES ('12', '3', '喷火龙', '111111111111111112');
INSERT INTO `members` VALUES ('13', '3', '小火龙', '111111111111111113');
INSERT INTO `members` VALUES ('14', '3', '火恐龙', '111111111111111114');
INSERT INTO `members` VALUES ('15', '3', '杰尼龟', '111111111111111115');
INSERT INTO `members` VALUES ('16', '3', '卡咪龟', '111111111111111116');
INSERT INTO `members` VALUES ('17', '3', '水箭龟', '111111111111111117');
INSERT INTO `members` VALUES ('18', '3', '妙蛙种子', '111111111111111118');
INSERT INTO `members` VALUES ('19', '3', '妙蛙花', '111111111111111119');
INSERT INTO `members` VALUES ('20', '3', '妙娃草', '111111111111111121');
INSERT INTO `members` VALUES ('21', '3', '黏美露龙', '121212221212121212');
INSERT INTO `members` VALUES ('22', '3', '甲贺忍蛙', '323232323232323233');
INSERT INTO `members` VALUES ('23', '3', '皮丘', '898964542164346945');
INSERT INTO `members` VALUES ('24', '3', '雷丘', '649797513134645784');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` varchar(255) NOT NULL COMMENT '订单ID',
  `account` varchar(255) NOT NULL COMMENT '用户账号',
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
  `pay_status` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否完成:0未完成1完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('21', '1491111360', '123123', 'G229', '徐州东', '13:36', '南京南', '14:51', '2017-04-02', '商务座', '11', '20', '391', '成人票', '1');
INSERT INTO `orders` VALUES ('22', '1491123120', '123123', 'G131', '南京南', '16:52', '上海虹桥', '18:14', '2017-04-02', '商务座', '8', '67', '447', '成人票', '1');
INSERT INTO `orders` VALUES ('23', '1491126720', '123123', 'K5812', '杭州', '17:52', '上海南', '20:00', '2017-04-02', '无座', '5', '无', '447', '成人票', '0');
INSERT INTO `orders` VALUES ('24', '1493399160', '123123', 'K8356', '南京', '01:06', '徐州', '05:24', '2017-04-29', '无座', '12', '无', '75', '成人票', '1');
INSERT INTO `orders` VALUES ('25', '1491149280', '123123', '1228', '南京', '00:08', '徐州', '04:39', '2017-04-03', '无座', '5', '无', '307', '成人票', '1');
INSERT INTO `orders` VALUES ('26', '1491144300', '123123', 'K1150', '南京', '22:45', '徐州', '04:45', '2017-04-02', '硬卧', '2', '24', '51', '成人票', '1');
INSERT INTO `orders` VALUES ('27', '1492491600', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-18', '商务座', '15', '84', '295', '成人票', '0');
INSERT INTO `orders` VALUES ('28', '1492529400', '123123', 'G229', '徐州东', '23:30', '南京南', '15:00', '2017-04-18', '商务座', '10', '43', '184', '成人票', '0');
INSERT INTO `orders` VALUES ('29', '1492529401', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-18', '商务座', '8', '62', '203', '成人票', '0');
INSERT INTO `orders` VALUES ('30', '1492529402', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-18', '商务座', '2', '61', '278', '成人票', '0');
INSERT INTO `orders` VALUES ('31', '1492529403', '123123', 'Z230', '徐州', '13:00', '南京', '14:30', '2017-04-18', '硬座', '3', '55', '456', '成人票', '0');
INSERT INTO `orders` VALUES ('32', '1492578000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-19', '商务座', '15', '54', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('33', '1492578000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-19', '商务座', '12', '92', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('34', '1492615800', '123123', 'G229', '徐州东', '23:30', '南京南', '15:00', '2017-04-19', '商务座', '2', '90', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('35', '1492615800', '123123', 'G229', '徐州东', '23:30', '南京南', '15:00', '2017-04-19', '商务座', '12', '97', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('36', '1492578000', '123123', 'Z230', '徐州', '13:00', '南京', '14:30', '2017-04-19', '无座', '12', '无', '0', '成人票', '0');
INSERT INTO `orders` VALUES ('37', '1492615800', '123123', 'G229', '徐州东', '23:30', '南京南', '15:00', '2017-04-19', '商务座', '10', '98', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('38', '1492578000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-19', '商务座', '4', '36', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('39', '1493528400', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-30', '商务座', '5', '7', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('40', '1492578000', '123123', 'Z230', '徐州', '13:00', '南京', '14:30', '2017-04-19', '硬座', '9', '无', '120', '成人票', '0');
INSERT INTO `orders` VALUES ('41', '1493442000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-29', '二等座', '8', '41', '100', '成人票', '1');
INSERT INTO `orders` VALUES ('42', '1493442000', '123123', 'Z230', '徐州', '13:00', '南京', '14:30', '2017-04-29', '硬座', '4', '49', '120', '成人票', '1');
INSERT INTO `orders` VALUES ('43', '1493442000', '123123', 'Z230', '徐州', '13:00', '南京', '14:30', '2017-04-29', '无座', '12', '无', '80', '成人票', '1');
INSERT INTO `orders` VALUES ('44', '1492664400', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-20', '商务座', '6', '67', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('45', '1492664400', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-20', '商务座', '9', '98', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('46', '1492664400', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-20', '商务座', '16', '50', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('47', '1493442000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-29', '商务座', '14', '21', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('48', '1493442000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-29', '商务座', '12', '26', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('49', '1493442000', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-29', '商务座', '10', '29', '400', '成人票', '1');
INSERT INTO `orders` VALUES ('50', '1493096400', '123123', 'G230', '徐州东', '13:00', '南京南', '14:30', '2017-04-25', '商务座', '4', '53', '400', '成人票', '0');
INSERT INTO `orders` VALUES ('51', '1493134200', '123123', 'G229', '徐州东', '23:30', '南京南', '15:00', '2017-04-25', '商务座', '1', '18', '400', '成人票', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tickets
-- ----------------------------
INSERT INTO `tickets` VALUES ('1', 'G229', '2017-05-18', '徐州东', '23:30', '01:30', '南京南', '15:00', '10', '100', '210', '--', '--', '--', '400', '150', '100', '0', '0', '0');
INSERT INTO `tickets` VALUES ('2', 'G230', '2017-05-17', '徐州东', '13:00', '01:30', '南京南', '14:30', '10', '100', '210', '--', '--', '--', '400', '150', '100', '0', '0', '0');
INSERT INTO `tickets` VALUES ('3', 'Z230', '2017-05-17', '徐州', '13:00', '01:30', '南京', '14:30', '--', '--', '--', '500', '--', '100', '0', '0', '0', '120', '0', '80');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('0000000001', 'admin', 'admin', 'admin', '363235911562312356');
INSERT INTO `users` VALUES ('0000000002', '15651990315', '15651990315', '幾瑆灀', '333258190001101478');
INSERT INTO `users` VALUES ('0000000003', '123123', '123123', 'Cookie', '322355199900009134');
INSERT INTO `users` VALUES ('0000000004', '12306', '12306', '皮卡丘', '230256133608541697');
