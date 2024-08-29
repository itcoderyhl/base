/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50642
Source Host           : localhost:3306
Source Database       : db_student

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2024-08-26 17:20:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `username` varchar(32) DEFAULT NULL COMMENT '学号',
  `nickname` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别：0男，1女',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日',
  `telephone` varchar(32) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1', '2024001', '张晓雯', '1', '2002-02-10', '11111111111', '1111@qq.com');
INSERT INTO `t_student` VALUES ('2', '2024002', '李思思', '1', '2001-02-10', '11111111112', '1112@qq.com');
INSERT INTO `t_student` VALUES ('3', '2024003', '王小二', '0', '2001-03-10', '11111111113', '1113@qq.com');
INSERT INTO `t_student` VALUES ('4', '2024004', '宋小微', '0', '2001-03-10', '11111111114', '1114@qq.com');
INSERT INTO `t_student` VALUES ('6', '2024005', '李志丹', '0', '2001-03-10', '11111111115', '1115@qq.com');
