/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50712
Source Host           : 127.0.0.1:3306
Source Database       : studentinfo

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-12-23 12:35:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
`id`  int(4) NOT NULL AUTO_INCREMENT ,
`userName`  varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`password`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
BEGIN;
INSERT INTO `t_admin` VALUES ('1', 'Dawn', '0211'), ('2', 'fans', '1234');
COMMIT;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`id`  int(4) NOT NULL AUTO_INCREMENT ,
`userName`  varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`password`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`name`  varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`age`  int(4) NOT NULL ,
`sex`  varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`dept`  varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`tel`  varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`email`  varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'B14060405', '0211', '范卓', '22', '男', '计算机信息与技术系', '15891300969', 'fans@e-team.cn'), ('2', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('3', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('4', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('5', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('6', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('7', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('8', 'xx', 'xx', 'xx', '22', 'x', 'xx', 'xx', 'xx'), ('9', 'xx', 'xx', 'xx', '22', 'x', 'xx', '22222', 'xx22'), ('10', '54', '45', '54', '55', '男', '2424', '4204', '21@524');
COMMIT;

-- ----------------------------
-- Auto increment value for `t_admin`
-- ----------------------------
ALTER TABLE `t_admin` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `t_user`
-- ----------------------------
ALTER TABLE `t_user` AUTO_INCREMENT=11;
