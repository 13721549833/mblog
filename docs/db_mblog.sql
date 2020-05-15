/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : db_mblog

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2020-05-15 13:01:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES ('1', '3.2', 'update', 'SQL', 'V3.2__update.sql', '-53734780', 'root', '2020-03-23 13:07:11', '17', '1');

-- ----------------------------
-- Table structure for mto_channel
-- ----------------------------
DROP TABLE IF EXISTS `mto_channel`;
CREATE TABLE `mto_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `thumbnail` varchar(128) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_channel
-- ----------------------------
INSERT INTO `mto_channel` VALUES ('1', 'banner', 'banner', '1', '', '6');
INSERT INTO `mto_channel` VALUES ('2', 'blog', '博客', '0', '', '5');
INSERT INTO `mto_channel` VALUES ('3', 'jotting', '随笔', '0', '', '1');
INSERT INTO `mto_channel` VALUES ('4', 'share', '分享', '0', '', '0');

-- ----------------------------
-- Table structure for mto_comment
-- ----------------------------
DROP TABLE IF EXISTS `mto_comment`;
CREATE TABLE `mto_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `pid` bigint(20) NOT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IK_POST_ID` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_comment
-- ----------------------------
INSERT INTO `mto_comment` VALUES ('7', '1', '大赛大使', '2020-04-22 16:46:24', '0', '7', '0');
INSERT INTO `mto_comment` VALUES ('8', '1', '挺好', '2020-04-23 12:30:39', '0', '22', '0');
INSERT INTO `mto_comment` VALUES ('11', '1', 'hhhhh', '2020-04-23 17:28:04', '0', '22', '0');
INSERT INTO `mto_comment` VALUES ('12', '1', 'dasdasd', '2020-04-23 17:33:50', '0', '22', '0');
INSERT INTO `mto_comment` VALUES ('13', '1', 'gasd', '2020-05-08 14:11:59', '0', '8', '0');

-- ----------------------------
-- Table structure for mto_favorite
-- ----------------------------
DROP TABLE IF EXISTS `mto_favorite`;
CREATE TABLE `mto_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IK_USER_ID` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_favorite
-- ----------------------------
INSERT INTO `mto_favorite` VALUES ('1', '2020-03-24 15:52:04', '1', '1');
INSERT INTO `mto_favorite` VALUES ('2', '2020-04-21 16:40:40', '1', '2');
INSERT INTO `mto_favorite` VALUES ('3', '2020-04-21 16:54:00', '2', '2');
INSERT INTO `mto_favorite` VALUES ('4', '2020-04-21 17:03:22', '3', '2');
INSERT INTO `mto_favorite` VALUES ('6', '2020-04-22 16:46:40', '7', '1');

-- ----------------------------
-- Table structure for mto_links
-- ----------------------------
DROP TABLE IF EXISTS `mto_links`;
CREATE TABLE `mto_links` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_links
-- ----------------------------

-- ----------------------------
-- Table structure for mto_message
-- ----------------------------
DROP TABLE IF EXISTS `mto_message`;
CREATE TABLE `mto_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `event` int(11) NOT NULL,
  `from_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_message
-- ----------------------------
INSERT INTO `mto_message` VALUES ('1', '2020-03-24 15:50:07', '3', '1', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('2', '2020-03-24 15:52:04', '1', '1', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('3', '2020-04-21 16:39:08', '3', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('4', '2020-04-21 16:40:40', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('5', '2020-04-21 16:40:41', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('6', '2020-04-21 16:40:42', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('7', '2020-04-21 16:40:43', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('8', '2020-04-21 16:40:43', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('9', '2020-04-21 16:40:43', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('10', '2020-04-21 16:40:43', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('11', '2020-04-21 16:40:43', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('12', '2020-04-21 16:40:44', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('13', '2020-04-21 16:40:44', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('14', '2020-04-21 16:42:52', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('15', '2020-04-21 16:42:52', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('16', '2020-04-21 16:42:53', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('17', '2020-04-21 16:42:53', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('18', '2020-04-21 16:42:53', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('19', '2020-04-21 16:42:53', '1', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('20', '2020-04-21 16:54:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('21', '2020-04-21 16:54:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('22', '2020-04-21 16:54:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('23', '2020-04-21 16:54:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('24', '2020-04-21 16:54:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('25', '2020-04-21 16:54:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('26', '2020-04-21 16:54:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('27', '2020-04-21 16:54:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('28', '2020-04-21 16:54:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('29', '2020-04-21 16:54:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('30', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('31', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('32', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('33', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('34', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('35', '2020-04-21 16:54:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('36', '2020-04-21 16:54:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('37', '2020-04-21 16:54:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('38', '2020-04-21 16:54:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('39', '2020-04-21 16:54:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('40', '2020-04-21 16:54:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('41', '2020-04-21 16:54:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('42', '2020-04-21 16:54:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('43', '2020-04-21 16:54:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('44', '2020-04-21 16:54:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('45', '2020-04-21 16:54:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('46', '2020-04-21 16:54:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('47', '2020-04-21 16:54:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('48', '2020-04-21 16:54:07', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('49', '2020-04-21 16:54:07', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('50', '2020-04-21 16:54:08', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('51', '2020-04-21 16:54:09', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('52', '2020-04-21 16:54:51', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('53', '2020-04-21 16:54:51', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('54', '2020-04-21 16:54:51', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('55', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('56', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('57', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('58', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('59', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('60', '2020-04-21 16:54:52', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('61', '2020-04-21 16:54:53', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('62', '2020-04-21 16:54:53', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('63', '2020-04-21 16:54:53', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('64', '2020-04-21 16:54:53', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('65', '2020-04-21 16:54:53', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('66', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('67', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('68', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('69', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('70', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('71', '2020-04-21 16:54:54', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('72', '2020-04-21 16:54:55', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('73', '2020-04-21 16:54:55', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('74', '2020-04-21 16:54:55', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('75', '2020-04-21 16:54:55', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('76', '2020-04-21 16:54:56', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('77', '2020-04-21 16:54:56', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('78', '2020-04-21 16:54:56', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('79', '2020-04-21 16:54:56', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('80', '2020-04-21 16:54:56', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('81', '2020-04-21 16:54:57', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('82', '2020-04-21 16:54:57', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('83', '2020-04-21 16:54:57', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('84', '2020-04-21 16:54:57', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('85', '2020-04-21 16:54:57', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('86', '2020-04-21 16:54:58', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('87', '2020-04-21 16:54:58', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('88', '2020-04-21 16:54:58', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('89', '2020-04-21 16:54:58', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('90', '2020-04-21 16:54:58', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('91', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('92', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('93', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('94', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('95', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('96', '2020-04-21 16:54:59', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('97', '2020-04-21 16:55:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('98', '2020-04-21 16:55:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('99', '2020-04-21 16:55:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('100', '2020-04-21 16:55:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('101', '2020-04-21 16:55:00', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('102', '2020-04-21 16:55:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('103', '2020-04-21 16:55:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('104', '2020-04-21 16:55:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('105', '2020-04-21 16:55:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('106', '2020-04-21 16:55:01', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('107', '2020-04-21 16:55:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('108', '2020-04-21 16:55:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('109', '2020-04-21 16:55:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('110', '2020-04-21 16:55:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('111', '2020-04-21 16:55:02', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('112', '2020-04-21 16:55:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('113', '2020-04-21 16:55:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('114', '2020-04-21 16:55:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('115', '2020-04-21 16:55:03', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('116', '2020-04-21 16:55:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('117', '2020-04-21 16:55:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('118', '2020-04-21 16:55:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('119', '2020-04-21 16:55:04', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('120', '2020-04-21 16:55:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('121', '2020-04-21 16:55:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('122', '2020-04-21 16:55:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('123', '2020-04-21 16:55:05', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('124', '2020-04-21 16:55:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('125', '2020-04-21 16:55:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('126', '2020-04-21 16:55:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('127', '2020-04-21 16:55:06', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('128', '2020-04-21 16:55:23', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('129', '2020-04-21 16:55:24', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('130', '2020-04-21 16:55:24', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('131', '2020-04-21 16:55:25', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('132', '2020-04-21 16:55:25', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('133', '2020-04-21 16:56:22', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('134', '2020-04-21 16:56:28', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('135', '2020-04-21 16:56:28', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('136', '2020-04-21 16:56:28', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('137', '2020-04-21 16:56:29', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('138', '2020-04-21 16:56:29', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('139', '2020-04-21 16:56:41', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('140', '2020-04-21 16:57:24', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('141', '2020-04-21 17:02:19', '1', '2', '2', '1', '2');
INSERT INTO `mto_message` VALUES ('142', '2020-04-21 17:03:22', '1', '2', '3', '1', '2');
INSERT INTO `mto_message` VALUES ('143', '2020-04-21 17:13:55', '3', '1', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('144', '2020-04-21 18:02:14', '3', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('145', '2020-04-21 18:08:53', '3', '2', '1', '1', '2');
INSERT INTO `mto_message` VALUES ('146', '2020-04-22 16:41:06', '1', '1', '7', '1', '2');
INSERT INTO `mto_message` VALUES ('147', '2020-04-22 16:41:29', '3', '1', '7', '1', '2');
INSERT INTO `mto_message` VALUES ('148', '2020-04-22 16:46:24', '3', '1', '7', '1', '2');
INSERT INTO `mto_message` VALUES ('149', '2020-04-22 16:46:40', '1', '1', '7', '1', '2');
INSERT INTO `mto_message` VALUES ('150', '2020-04-23 12:30:39', '3', '1', '22', '0', '2');
INSERT INTO `mto_message` VALUES ('153', '2020-04-23 17:28:04', '3', '1', '22', '0', '2');
INSERT INTO `mto_message` VALUES ('154', '2020-04-23 17:33:50', '3', '1', '22', '0', '2');
INSERT INTO `mto_message` VALUES ('155', '2020-05-08 14:11:59', '3', '1', '8', '0', '2');

-- ----------------------------
-- Table structure for mto_options
-- ----------------------------
DROP TABLE IF EXISTS `mto_options`;
CREATE TABLE `mto_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_` varchar(32) DEFAULT NULL,
  `type` int(5) DEFAULT '0',
  `value` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_options
-- ----------------------------
INSERT INTO `mto_options` VALUES ('1', 'site_name', '0', 'Mtons');
INSERT INTO `mto_options` VALUES ('2', 'site_domain', '0', 'http://mtons.com');
INSERT INTO `mto_options` VALUES ('3', 'site_keywords', '0', 'mtons,博客,社区');
INSERT INTO `mto_options` VALUES ('4', 'site_description', '0', 'Mtons, 做一个有内涵的技术社区');
INSERT INTO `mto_options` VALUES ('5', 'site_metas', '0', '');
INSERT INTO `mto_options` VALUES ('6', 'site_copyright', '0', 'Copyright © Jerry');
INSERT INTO `mto_options` VALUES ('7', 'site_icp', '0', '黔ICP备18002968号');
INSERT INTO `mto_options` VALUES ('8', 'qq_callback', '0', '');
INSERT INTO `mto_options` VALUES ('9', 'qq_app_id', '0', '');
INSERT INTO `mto_options` VALUES ('10', 'qq_app_key', '0', '');
INSERT INTO `mto_options` VALUES ('11', 'weibo_callback', '0', '');
INSERT INTO `mto_options` VALUES ('12', 'weibo_client_id', '0', '');
INSERT INTO `mto_options` VALUES ('13', 'weibo_client_sercret', '0', '');
INSERT INTO `mto_options` VALUES ('14', 'github_callback', '0', '');
INSERT INTO `mto_options` VALUES ('15', 'github_client_id', '0', '');
INSERT INTO `mto_options` VALUES ('16', 'github_secret_key', '0', '');

-- ----------------------------
-- Table structure for mto_photo
-- ----------------------------
DROP TABLE IF EXISTS `mto_photo`;
CREATE TABLE `mto_photo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(256) DEFAULT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_photo
-- ----------------------------
INSERT INTO `mto_photo` VALUES ('4', '北京故宫', 'http://image.snowsea.com.cn/static/702d823d86a586ab49e89d94d245f0d7.jpg', '2020-05-08 21:48:25', null);
INSERT INTO `mto_photo` VALUES ('5', '苏州七里山塘街', 'http://image.snowsea.com.cn/static/4bbcf6181718b2c22351b016d773a693.jpg', '2020-05-08 21:54:10', null);
INSERT INTO `mto_photo` VALUES ('6', '苏州虎丘山风景区', 'http://image.snowsea.com.cn/static/250c2d2fb0e66bf02f19c509ab436620.jpg', '2020-05-08 21:55:58', null);
INSERT INTO `mto_photo` VALUES ('7', '苏州平江路', 'http://image.snowsea.com.cn/static/ed8c4b35999a104671283bac447eb9e3.jpg', '2020-05-08 22:03:56', null);
INSERT INTO `mto_photo` VALUES ('8', '苏州虎丘山风景区', 'http://image.snowsea.com.cn/static/0118a746b6f79e12b70ee0d732077a9c.jpg', '2020-05-08 22:13:21', null);
INSERT INTO `mto_photo` VALUES ('9', '苏州寒山寺', 'http://image.snowsea.com.cn/static/dd5e16403cc4c54cf8345d2bec79d5f6.jpg', '2020-05-08 22:13:29', null);
INSERT INTO `mto_photo` VALUES ('11', '苏州七里山塘街', 'http://image.snowsea.com.cn/static/0f33433ae1412ee499b826f1ca118691.jpg', '2020-05-08 23:02:36', null);
INSERT INTO `mto_photo` VALUES ('12', '苏州金鸡湖夜景', 'http://image.snowsea.com.cn/static/272421db579945ffa7dcddd51b85e917.jpg', '2020-05-08 23:05:51', null);
INSERT INTO `mto_photo` VALUES ('13', '浙江横店影视城', 'http://image.snowsea.com.cn/static/abbe0d2912b65f08c03f1b3b9b556d32.jpg', '2019-09-13 16:00:06', null);
INSERT INTO `mto_photo` VALUES ('14', '浙江横店影视城 - 秦王宫', 'http://image.snowsea.com.cn/static/a1b48f022ca1b4937f3f635f9a67ab94.jpg', '2020-05-12 20:05:50', null);

-- ----------------------------
-- Table structure for mto_post
-- ----------------------------
DROP TABLE IF EXISTS `mto_post`;
CREATE TABLE `mto_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `favors` int(11) NOT NULL,
  `featured` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `summary` varchar(140) DEFAULT NULL,
  `tags` varchar(64) DEFAULT NULL,
  `thumbnail` varchar(128) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `views` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IK_CHANNEL_ID` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_post
-- ----------------------------
INSERT INTO `mto_post` VALUES ('1', '2', '2', '5', '2020-03-23 13:24:23', '2', '0', '0', 'IDEA配置GIT 注：此方法可用于配置gitlab也可用于配置github 1.在github中创建一个账号：https://github.com/join?source=header-home 2.下载并安装git：https://git-s...', 'Git,Push', '', 'IDEA配置GIT', '104', '0');
INSERT INTO `mto_post` VALUES ('2', '2', '2', '0', '2020-03-31 17:31:22', '1', '0', '0', 'asdasdasd', 'das,dasdsad', '', 'sadsad', '19', '0');
INSERT INTO `mto_post` VALUES ('3', '2', '2', '0', '2020-03-31 17:37:15', '1', '0', '0', 'asdasd', 'dasdas,dasdasdasd', '', 'sadas', '18', '0');
INSERT INTO `mto_post` VALUES ('7', '2', '2', '2', '2020-04-22 16:19:42', '2', '1', '0', 'asdasdasdasd', 'das,dasd', '', 'IDEA新特性：提前知道代码怎么走！', '12', '0');
INSERT INTO `mto_post` VALUES ('8', '2', '2', '1', '2020-04-22 16:59:10', '0', '0', '0', '大赛大使', '大,使', '', '大赛大使', '3', '0');
INSERT INTO `mto_post` VALUES ('9', '2', '2', '0', '2020-04-22 17:01:03', '0', '0', '0', '哈哈哈哈', '哈哈', '', '哈哈哈哈', '0', '0');
INSERT INTO `mto_post` VALUES ('10', '2', '2', '0', '2020-04-22 17:02:52', '0', '0', '0', '大三大四的', 'dasd', '', '大叔大叔大叔大', '0', '0');
INSERT INTO `mto_post` VALUES ('11', '2', '2', '0', '2020-04-22 17:05:41', '0', '0', '0', '卧槽，尼玛标签插入不了', '阿斯达', '', '卧槽，尼玛标签插入不了', '0', '0');
INSERT INTO `mto_post` VALUES ('12', '2', '2', '0', '2020-04-22 17:13:37', '0', '0', '0', '测试插入标签', '测试,嗯大神,测', '', '测试插入标签', '0', '0');
INSERT INTO `mto_post` VALUES ('13', '2', '2', '0', '2020-04-22 17:33:15', '0', '0', '0', '大赛大使大所', '大苏打', '', '大赛大使大所', '0', '0');
INSERT INTO `mto_post` VALUES ('14', '2', '2', '0', '2020-04-22 17:33:32', '0', '0', '0', 'ASDASD', '大苏打', '', 'ASAS', '0', '0');
INSERT INTO `mto_post` VALUES ('17', '2', '2', '0', '2020-04-23 11:17:52', '0', '0', '0', '苏打水', '三', '', '三生三世', '0', '0');
INSERT INTO `mto_post` VALUES ('21', '2', '2', '0', '2020-04-23 12:22:02', '0', '0', '0', '三生三世十里桃花', '桃花', '', '三生三世十里桃花', '0', '0');
INSERT INTO `mto_post` VALUES ('22', '2', '2', '3', '2020-04-23 12:29:04', '0', '1', '0', 'SpringBoot整合elasticsearch', 'SpringBoot', '', 'SpringBoot整合elasticsearch', '88', '0');
INSERT INTO `mto_post` VALUES ('23', '2', '2', '0', '2020-05-13 11:20:05', '0', '0', '0', 'asdasdasd', 'dasd adas,dasdgsdf', '', 'dasdasd', '0', '0');

-- ----------------------------
-- Table structure for mto_post_attribute
-- ----------------------------
DROP TABLE IF EXISTS `mto_post_attribute`;
CREATE TABLE `mto_post_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) DEFAULT NULL,
  `content` longtext,
  `editor` varchar(16) DEFAULT 'tinymce',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_post_attribute
-- ----------------------------
INSERT INTO `mto_post_attribute` VALUES ('1', '1', '**IDEA配置GIT**\r\n注：此方法可用于配置gitlab也可用于配置github\r\n\r\n1.在github中创建一个账号：https://github.com/join?source=header-home\r\n\r\n2.下载并安装git：https://git-scm.com/downloads\r\n\r\n3.安装成功后打开Git Bash，输入下列命令，设置git全局用户名和邮箱\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502154824523-1593919339.png]()\r\n\r\n4.在IDEA中设置Git，在File-->Setting->Version Control-->Git-->Path to Git executable选择你的git安装后的git.exe文件，然后点击Test，测试是否设置成功\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502155327617-454151389.png]()\r\n\r\n5.在IDEA中设置GitHub，File-->Setting->Version Control-->GibHub\r\n\r\n　　Host：github.com\r\n\r\n　　Token：点击Create API Token，输入在github中注册的用户名和密码生成token\r\n\r\n　　点击Test，测试是否连接成功\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502155236523-1176370171.png]()\r\n\r\n6.创建本地仓库，VCS-->Import into Version Control-->Create Git Repository...\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502155706992-411273097.png]()\r\n在弹框中选中项目所在的位置，点击OK，此时项目文件全部变成红色（若选中其他位置，则git-->add不可点选，不知为何）\r\n\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502160500398-1494808551.png]()\r\n\r\n7.上传项目到本地仓库，项目右键选择Git-->add，此时项目文件变成绿色，此时文件只是处于暂存区，并没有真正进入到版本库中\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502160640367-2003355485.png]()\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502160657804-658587989.png]()\r\n项目右键Git--> Commit Directory，在弹窗中输入Commit Message，点击commit，此时项目文件从暂存区真正进入版本库中，项目文件变成白色\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502161116898-521997511.png]()\r\n![https://images2015.cnblogs.com/blog/1140292/201705/1140292-20170502161227382-881900698.png]()\r\n\r\n这里选择commit and push\r\n\r\n然后配置push的信息  push即可\r\n\r\n', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('2', '2', 'asdasdasd', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('3', '3', 'asdasd', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('4', '7', 'IDEA新特性：提前知道代码怎么走！', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('5', '8', '大赛大使', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('6', '9', '哈哈哈哈', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('7', '10', '大三大四的', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('8', '11', '卧槽，尼玛标签插入不了', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('9', '12', '测试插入标签', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('10', '13', '大赛大使大所', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('11', '14', 'ASDASD', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('12', '17', '苏打水', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('13', '21', '三生三世十里桃花', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('14', '22', 'SpringBoot整合elasticsearch', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('16', '23', '**mybatis简介**\r\n\r\nmybatis官网系统学习：\r\n\r\n- [http://www.mybatis.org/mybatis-3/zh/index.html\r\n](http://www.mybatis.org/mybatis-3/zh/index.html\r\n)\r\n\r\nMyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。\r\n\r\n## jdbc与mybatis\r\n\r\n需要先注册驱动和数据库信息、操作Connection、通过statement对象执行SQL，将结果返回给resultSet，然后从resultSet中读取数据并转换为pojo对象，最后需要关闭数据库相关资源。而且还需要自己对JDBC过程的异常进行捕捉和处理\r\n\r\n\r\n\r\nMyBatis对JDBC的封装很好，几乎可以取代Jdbc。\r\n\r\nMyBatis使用SqlSessionFactoryBuilder来连接完成JDBC需要代码完成的数据库获取和连接，减少了代码的重复。JDBC将SQL语句写到代码里，属于硬编码，非常不易维护，MyBatis可以将SQL代码写入xml中，易于修改和维护。JDBC的resultSet需要用户自己去读取并生成对应的POJO，MyBatis的mapper会自动将执行后的结果映射到对应的Java对象中。\r\n\r\n## mybatis重要组件\r\n\r\n- **Configuration**\r\n\r\nMyBatis所有的配置信息都维持在Configuration对象之中。\r\n\r\n- **SqlSession**\r\n\r\n作为MyBatis工作的主要顶层API，表示和数据库交互的会话，完成必要数据库增删改查功能\r\n\r\n- **Executor**\r\n\r\nMyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护\r\n\r\n- **StatementHandler**\r\n\r\n封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。\r\n\r\n- **ParameterHandler** \r\n\r\n负责对用户传递的参数转换成JDBC Statement 所需要的参数，\r\n\r\n- **ResultSetHandler**\r\n\r\n负责将JDBC返回的ResultSet结果集对象转换成List类型的集合；\r\n\r\n- **TypeHandler**\r\n\r\n 负责java数据类型和jdbc数据类型之间的映射和转换\r\n\r\n- **MappedStatement**\r\n\r\n MappedStatement维护了一条<select|update|delete|insert>节点的封装，\r\n\r\n- **SqlSource**\r\n\r\n负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回\r\n\r\n- **BoundSql**\r\n\r\n表示动态生成的SQL语句以及相应的参数信息\r\n\r\n**原理**\r\n\r\n![img](https://images-cdn.shimo.im/ie6Sqa3g4L0iRScB/image.png!thumbnail)\r\n\r\n![img](https://uploader.shimo.im/f/VI4rxV5Ub9wToOW3.png!thumbnail)\r\n\r\n﻿**mybatis如何获取数据库中的字段**\r\n\r\n**原理**\r\n\r\ninformation_schema数据库是MySQL自带的，它提供了访问数据库元数据的方式。什么是元数据呢？元数据是关于数据的数据，如数据库名或表名，列的数据类型，或访问权限等。有些时候用于表述该信息的其他术语包括“数据词典”和“系统目录”。\r\n\r\n\r\n\r\n在MySQL中，把 information_schema 看作是一个数据库，确切说是信息数据库。其中保存着关于MySQL服务器所维护的所有其他数据库的信息。如数据库名，数据库的表，表栏的数据类型与访问权 限等。在INFORMATION_SCHEMA中，有数个只读表。它们实际上是视图，而不是基本表，因此，你将无法看到与之相关的任何文件。\r\n\r\n### **information_schema表说明**\r\n\r\nSCHEMATA表：提供了当前mysql实例中所有数据库的信息。是show databases的结果取之此表。\r\n\r\nTABLES表：提供了关于数据库中的表的信息（包括视图）。详细表述了某个表属于哪个schema，表类型，表引擎，创建时间等信息。是show tables from schemaname的结果取之此表。\r\n\r\nCOLUMNS表：提供了表中的列信息。详细表述了某张表的所有列以及每个列的信息。是show columns from schemaname.tablename的结果取之此表。\r\n\r\nSTATISTICS表：提供了关于表索引的信息。是show index from schemaname.tablename的结果取之此表。\r\n\r\nUSER_PRIVILEGES（用户权限）表：给出了关于全程权限的信息。该信息源自mysql.user授权表。是非标准表。\r\n\r\nSCHEMA_PRIVILEGES（方案权限）表：给出了关于方案（数据库）权限的信息。该信息来自mysql.db授权表。是非标准表。\r\n\r\nTABLE_PRIVILEGES（表权限）表：给出了关于表权限的信息。该信息源自mysql.tables_priv授权表。是非标准表。\r\n\r\nCOLUMN_PRIVILEGES（列权限）表：给出了关于列权限的信息。该信息源自mysql.columns_priv授权表。是非标准表。\r\n\r\nCHARACTER_SETS（字符集）表：提供了mysql实例可用字符集的信息。是SHOW CHARACTER SET结果集取之此表。\r\n\r\nCOLLATIONS表：提供了关于各字符集的对照信息。\r\n\r\nCOLLATION_CHARACTER_SET_APPLICABILITY表：指明了可用于校对的字符集。这些列等效于SHOW COLLATION的前两个显示字段。\r\n\r\nTABLE_CONSTRAINTS表：描述了存在约束的表。以及表的约束类型。\r\n\r\nKEY_COLUMN_USAGE表：描述了具有约束的键列。\r\n\r\nROUTINES表：提供了关于存储子程序（存储程序和函数）的信息。此时，ROUTINES表不包含自定义函数（UDF）。名为“mysql.proc name”的列指明了对应于INFORMATION_SCHEMA.ROUTINES表的mysql.proc表列。\r\n\r\nVIEWS表：给出了关于数据库中的视图的信息。需要有show views权限，否则无法查看视图信息。\r\n\r\nTRIGGERS表：提供了关于触发程序的信息。必须有super权限才能查看该表\r\n\r\n![img](https://images-cdn.shimo.im/9KHDIgsUyq4c8T8w/image.png!thumbnail)\r\n\r\n查找当前数据库的表信息：\r\n\r\n**第一种方法：**\r\n\r\n```\r\nselect * from information_schema.TABLES where TABLE_SCHEMA=(select database())\r\n```\r\n\r\n**第二种方法：**\r\n\r\n```\r\n#获取表信息\r\nshow table status \r\n```\r\n\r\n查找当前表的所有字段信息：\r\n\r\n**第一种方法：**\r\n\r\n```\r\nselect * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}\r\n```\r\n\r\n**第二种方法：**\r\n\r\n```\r\nshow full fields from `student`;\r\n```\r\n\r\n### 集成spring的多数据源处理\r\n\r\n1、通过扫描包区分\r\n\r\ngit demo: https://gitee.com/lv-success/git-second/tree/master/course-3-mybatis/springBootMybatisMulidatasource\r\n\r\n\r\n\r\n2、通过动态数据源区分\r\n\r\n一般使用注解的形式，这里先留着，后面的renren-fast项目\r\n\r\n## mybatis的读写分离\r\n\r\n原理：\r\n\r\n和多数据源处理差不多，mybatis做读写分离也有多种方法。通过拦截发起请求的方法或执行的sql来自动判断需要的数据源！\r\n\r\n\r\n\r\n1、拦截发起操作的方法名\r\n\r\n需要自己约定增删改查的前缀，然后根据前缀选择数据源！\r\n\r\ngit demo：https://gitee.com/lv-success/git-second/tree/master/course-3-mybatis/bounterMybatis\r\n\r\n\r\n\r\n2、拦截发起操作的sql\r\n\r\ngit demo:https://github.com/shawntime/shawn-rwdb\r\n\r\n## mybatis源码分析\r\n\r\n![img](https://uploader.shimo.im/f/PMTBnkVXJ84Hmivq.png!thumbnail)\r\n\r\n**课程mybatis源码注释git分支**\r\n\r\n- https://github.com/lv-success/mybatis-3\r\n\r\n源码分析部分因为内容比较多，就不一一写下来的，大家去看看别人的博文\r\n\r\n\r\n\r\n- 大闲人柴毛毛\r\n\r\n[MyBatis源码解析(一)——MyBatis初始化过程解析](https://www.jianshu.com/p/7bc6d3b7fb45)\r\n\r\n[MyBatis源码解析(二)——动态代理实现函数调用](https://www.jianshu.com/p/46c6e56d9774)\r\n\r\n\r\n\r\n- zhjh256\r\n\r\n[mybatis 3.x源码深度解析与最佳实践](https://www.cnblogs.com/zhjh256/p/8512392.html)\r\n\r\n\r\n\r\n- 南轲梦\r\n\r\n[随笔分类 - mybatis](https://www.cnblogs.com/dongying/category/620960.html)\r\n\r\n## mybatis plus的简单运用\r\n\r\n文章：https://www.java-mindmap.com/view/21\r\n\r\n官网：https://mp.baomidou.com/guide\r\n\r\n官网实例：https://gitee.com/baomidou/mybatis-plus-samples', 'markdown');
INSERT INTO `mto_post_attribute` VALUES ('18', '24', '##### 1. 在pom.xml中添加如下：\r\n\r\n```\r\n<!--整合rabbitmq-->\r\n<dependency>\r\n  <groupId>org.springframework.boot</groupId>\r\n  <artifactId>spring-boot-starter-amqp</artifactId>\r\n</dependency>\r\n```\r\n\r\n##### 2.application.yml添加如下：\r\n\r\n```\r\nspring:\r\n	#rabbitmq 配置\r\n    rabbitmq:\r\n      username: mblog\r\n      password: mblog\r\n	  host: 127.0.0.1\r\n      port: 5672\r\n```\r\n\r\n##### 3.添加配置类：\r\n\r\n```\r\n@Configuration\r\npublic class RabbitConfig {\r\n\r\n    public final static String es_queue = \"es_queue\";\r\n    public final static String es_exchage = \"es_exchage\";\r\n    public final static String es_bind_key = \"es_exchage\";\r\n\r\n    @Bean\r\n    public Queue exQueue() {\r\n        return new Queue(es_queue);\r\n    }\r\n\r\n    @Bean\r\n    DirectExchange exchange() {\r\n        return new DirectExchange(es_exchage);\r\n    }\r\n\r\n    @Bean\r\n    Binding binding(Queue exQueue, DirectExchange exchange) {\r\n        return BindingBuilder.bind(exQueue).to(exchange).with(es_bind_key);\r\n    }\r\n\r\n}\r\n```\r\n\r\n##### 4.添加PostMqMessage类：\r\n\r\n```\r\n@Data\r\n@AllArgsConstructor\r\npublic class PostMqMessage implements Serializable {\r\n    private static final long serialVersionUID = 3572599349158869479L;\r\n    /**\r\n     * 新增或修改\r\n     */\r\n    public final static String CREATE_OR_UPDATE = \"create_or_update\";\r\n    /**\r\n     * 删除\r\n     */\r\n    public final static String REMOVE = \"remove\";\r\n    /**\r\n     * 文章id\r\n     */\r\n    private Long postId;\r\n    /**\r\n     * 文章操作类型\r\n     */\r\n    private String action;\r\n}\r\n```\r\n\r\n##### 5.PostSearchService接口添加如下方法：\r\n\r\n```\r\n/**\r\n * 创建或更新索引\r\n *\r\n * @param message 消息\r\n */\r\nvoid createOrUpdateIndex(PostMqMessage message);\r\n\r\n/**\r\n * 删除索引\r\n *\r\n * @param message 消息\r\n */\r\nvoid removeIndex(PostMqMessage message);\r\n```\r\n\r\n##### 6.PostSearchServiceImpl实现类实现以上接口：\r\n\r\n```\r\n@Slf4j\r\n@Service\r\n@Transactional(readOnly = true)\r\npublic class PostSearchServiceImpl implements PostSearchService {\r\n\r\n    @Autowired\r\n    private UserService userService;\r\n\r\n    @Autowired\r\n    private ChannelService channelService;\r\n\r\n    @Autowired\r\n    private PostService postService;\r\n\r\n    @Autowired\r\n    private ArticlesRepository articlesRepository;\r\n\r\n    @Override\r\n    public void createOrUpdateIndex(PostMqMessage message) {\r\n        Long postId = message.getPostId();\r\n        Post post = postService.getPostById(postId);\r\n        Articles articles = BeanMapUtil.post2Articles(post);\r\n        UserVO author = userService.get(post.getAuthorId());\r\n        Channel channel = channelService.getById(post.getChannelId());\r\n        articles.setAuthor(author);\r\n        articles.setChannel(channel);\r\n        articlesRepository.save(articles);\r\n        log.info(\"es 索引更新成功！ ---> {}\", articles.toString());\r\n    }\r\n\r\n    @Override\r\n    public void removeIndex(PostMqMessage message) {\r\n        Long postId = message.getPostId();\r\n\r\n        articlesRepository.deleteById(postId);\r\n        log.info(\"es 索引删除成功！ ---> {}\", message.toString());\r\n    }\r\n}\r\n```\r\n\r\n##### 7.PostServiceImpl新增文章方法中添加如下代码：\r\n\r\n```\r\n@Service\r\n@Transactional\r\npublic class PostServiceImpl implements PostService {\r\n\r\n	@Autowired\r\n    private PostMapper postMapper;\r\n    \r\n    @Autowired\r\n    private PostAttributeMapper postAttributeMapper;\r\n    \r\n    @Autowired\r\n    private TagService tagService;\r\n    \r\n    @Autowired\r\n    private AmqpTemplate amqpTemplate;\r\n\r\n	@Override\r\n    @Transactional\r\n    public long post(PostVO post) {\r\n        Post po = new Post();\r\n        BeanUtils.copyProperties(post, po);\r\n        po.setStatus(post.getStatus());\r\n		// 处理摘要\r\n        if (StringUtils.isBlank(post.getSummary())) {\r\n            po.setSummary(trimSummary(post.getEditor(), post.getContent()));\r\n        } else {\r\n            po.setSummary(post.getSummary());\r\n        }\r\n        postMapper.insert(po);\r\n\r\n        tagService.batchUpdate(po.getTags(), po.getId());\r\n\r\n        String key = ResourceLock.getPostKey(po.getId());\r\n        AtomicInteger lock = ResourceLock.getAtomicInteger(key);\r\n        try {\r\n            synchronized (lock){\r\n                PostAttribute attr = new PostAttribute();\r\n                attr.setContent(post.getContent());\r\n                attr.setEditor(post.getEditor());\r\n                attr.setPostId(po.getId());\r\n                postAttributeMapper.insert(attr);\r\n\r\n                countResource(po.getId(), null,  attr.getContent());\r\n                onPushEvent(po, PostUpdateEvent.ACTION_PUBLISH);\r\n\r\n//                insertEs(po);\r\n                amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                                new PostMqMessage(post.getId(), PostMqMessage.CREATE_OR_UPDATE));\r\n//                TODO\r\n                return po.getId();\r\n            }\r\n        }finally {\r\n            ResourceLock.giveUpAtomicInteger(key);\r\n        }\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void update(PostVO p) {\r\n        Post po = getPostById(p.getId());\r\n        if (po != null) {\r\n            String key = ResourceLock.getPostKey(p.getId());\r\n            AtomicInteger lock = ResourceLock.getAtomicInteger(key);\r\n            try {\r\n                synchronized (lock) {\r\n                    po.setTitle(p.getTitle());\r\n                    po.setChannelId(p.getChannelId());\r\n                    po.setThumbnail(p.getThumbnail());\r\n                    po.setStatus(p.getStatus());\r\n                    // 处理摘要\r\n                    if (StringUtils.isBlank(p.getSummary())) {\r\n                        po.setSummary(trimSummary(p.getEditor(), p.getContent()));\r\n                    } else {\r\n                        po.setSummary(p.getSummary());\r\n                    }\r\n                    // 标签\r\n                    po.setTags(p.getTags());\r\n                    postMapper.updateById(po);\r\n\r\n                    PostAttribute postAttribute = getPostAttribute(po.getId());\r\n                    String originContent = \"\";\r\n                    if (postAttribute != null) {\r\n                        originContent = postAttribute.getContent();\r\n                    }\r\n                    PostAttribute attr = new PostAttribute();\r\n                    attr.setContent(p.getContent());\r\n                    attr.setEditor(p.getEditor());\r\n                    attr.setPostId(po.getId());\r\n                    attr.setId(postAttribute.getId());\r\n                    postAttributeMapper.updateById(attr);\r\n\r\n                    tagService.batchUpdate(po.getTags(), po.getId());\r\n                    countResource(po.getId(), originContent, p.getContent());\r\n\r\n//                    insertEs(po);\r\n                    // TODO\r\n                    amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                            new PostMqMessage(po.getId(), PostMqMessage.CREATE_OR_UPDATE));\r\n                }\r\n            } finally {\r\n                ResourceLock.giveUpAtomicInteger(key);\r\n            }\r\n        }\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void updateFeatured(long id, int featured) {\r\n        Post post = getPostById(id);\r\n\r\n        int status = Consts.FEATURED_ACTIVE == featured ? Consts.FEATURED_ACTIVE : Consts.FEATURED_DEFAULT;\r\n        post.setFeatured(status);\r\n        postMapper.updateById(post);\r\n\r\n//        insertEs(post);\r\n        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                new PostMqMessage(post.getId(), PostMqMessage.CREATE_OR_UPDATE));\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void delete(long id, long authorId) {\r\n        Post post = getPostById(id);\r\n        // 判断文章是否属于当前登录用户\r\n        Assert.isTrue(post.getAuthorId() == authorId, \"认证失败\");\r\n        String key = ResourceLock.getPostKey(post.getId());\r\n        AtomicInteger lock = ResourceLock.getAtomicInteger(key);\r\n        try {\r\n            synchronized (lock) {\r\n                postMapper.deleteById(id);\r\n                postAttributeMapper.deleteById(id);\r\n                cleanResource(post.getId());\r\n                onPushEvent(post, PostUpdateEvent.ACTION_DELETE);\r\n\r\n//                articlesRepository.delete(BeanMapUtil.post2Articles(post));\r\n                // TODO\r\n                amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                                new PostMqMessage(post.getId(), PostMqMessage.REMOVE));\r\n            }\r\n        } finally {\r\n            ResourceLock.giveUpAtomicInteger(key);\r\n        }\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void delete(Collection<Long> ids) {\r\n        if (CollectionUtils.isNotEmpty(ids)) {\r\n            List<Post> postList = postMapper.selectBatchIds(ids);\r\n            postList.forEach(post -> {\r\n                String key = ResourceLock.getPostKey(post.getId());\r\n                AtomicInteger lock = ResourceLock.getAtomicInteger(key);\r\n                try {\r\n                    synchronized (lock){\r\n                        postMapper.deleteById(post.getId());\r\n                        postAttributeMapper.deleteById(post.getId());\r\n                        cleanResource(post.getId());\r\n                        onPushEvent(post, PostUpdateEvent.ACTION_DELETE);\r\n\r\n//                        articlesRepository.delete(BeanMapUtil.post2Articles(post));\r\n                        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                                new PostMqMessage(post.getId(), PostMqMessage.REMOVE));\r\n                    }\r\n                } finally {\r\n                    ResourceLock.giveUpAtomicInteger(key);\r\n                }\r\n            });\r\n        }\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void identityViews(long id) {\r\n        // 次数不清理缓存, 等待文章缓存自动过期\r\n        Post post = getPostById(id);\r\n\r\n        postMapper.updateViews(id, Consts.IDENTITY_STEP);\r\n        post.setViews(post.getViews() + Consts.IDENTITY_STEP);\r\n//        insertEs(post);\r\n        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                new PostMqMessage(post.getId(), PostMqMessage.CREATE_OR_UPDATE));\r\n    }\r\n    \r\n    @Override\r\n    @Transactional\r\n    public void identityComments(long id) {\r\n        Post post = getPostById(id);\r\n\r\n        postMapper.updateComments(id, Consts.IDENTITY_STEP);\r\n        post.setComments(post.getComments() + Consts.IDENTITY_STEP);\r\n//        insertEs(post);\r\n        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                new PostMqMessage(post.getId(), PostMqMessage.CREATE_OR_UPDATE));\r\n    }\r\n    \r\n}    \r\n```\r\n\r\n以上所有的**insertEs(post);**替换成**amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,\r\n                new PostMqMessage(post.getId(), PostMqMessage.CREATE_OR_UPDATE));**\r\n\r\n##### 8.最重要的一步添加PostMqHandler类：\r\n\r\n```\r\n@Slf4j\r\n@Component\r\n@RabbitListener(queues = RabbitConfig.es_queue)\r\npublic class PostMqHandler {\r\n\r\n    @Autowired\r\n    private PostSearchService postSearchService;\r\n\r\n    @RabbitHandler\r\n    public void handler(PostMqMessage message) {\r\n\r\n        log.info(\"mq 收到一条消息： {}\", message.toString());\r\n\r\n        switch (message.getAction()) {\r\n            case PostMqMessage.CREATE_OR_UPDATE:\r\n                postSearchService.createOrUpdateIndex(message);\r\n                break;\r\n            case PostMqMessage.REMOVE:\r\n                postSearchService.removeIndex(message);\r\n                break;\r\n            default:\r\n                log.error(\"没找到对应的消息类型，请注意！！ --》 {}\", message.toString());\r\n                break;\r\n        }\r\n    }\r\n}\r\n```\r\n\r\nok了，大功告成咯！！！', 'markdown');

-- ----------------------------
-- Table structure for mto_post_resource
-- ----------------------------
DROP TABLE IF EXISTS `mto_post_resource`;
CREATE TABLE `mto_post_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `IK_POST_ID` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_post_resource
-- ----------------------------

-- ----------------------------
-- Table structure for mto_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `mto_post_tag`;
CREATE TABLE `mto_post_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  `weight` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IK_TAG_ID` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_post_tag
-- ----------------------------
INSERT INTO `mto_post_tag` VALUES ('1', '1', '1', '1584941063507');
INSERT INTO `mto_post_tag` VALUES ('2', '1', '2', '1584941063512');
INSERT INTO `mto_post_tag` VALUES ('3', '2', '3', '1585647110186');
INSERT INTO `mto_post_tag` VALUES ('4', '2', '4', '1585647110216');
INSERT INTO `mto_post_tag` VALUES ('5', '3', '5', '1585647435380');
INSERT INTO `mto_post_tag` VALUES ('6', '3', '6', '1585647435385');
INSERT INTO `mto_post_tag` VALUES ('14', '7', '0', '1587543582418');
INSERT INTO `mto_post_tag` VALUES ('15', '7', '0', '1587544134790');
INSERT INTO `mto_post_tag` VALUES ('16', '8', '0', '1587545950152');
INSERT INTO `mto_post_tag` VALUES ('17', '8', '0', '1587545950160');
INSERT INTO `mto_post_tag` VALUES ('18', '9', '0', '1587546062949');
INSERT INTO `mto_post_tag` VALUES ('19', '10', '0', '1587546172417');
INSERT INTO `mto_post_tag` VALUES ('20', '11', '0', '1587546761489');
INSERT INTO `mto_post_tag` VALUES ('22', '12', '8', '1587547115544');
INSERT INTO `mto_post_tag` VALUES ('23', '12', '9', '1587547116763');
INSERT INTO `mto_post_tag` VALUES ('24', '12', '10', '1587547224430');
INSERT INTO `mto_post_tag` VALUES ('25', '12', '7', '1587547368338');
INSERT INTO `mto_post_tag` VALUES ('26', '13', '11', '1587547994770');
INSERT INTO `mto_post_tag` VALUES ('27', '14', '11', '1587548011567');
INSERT INTO `mto_post_tag` VALUES ('28', '17', '12', '1587611872295');
INSERT INTO `mto_post_tag` VALUES ('29', '21', '13', '1587615722458');
INSERT INTO `mto_post_tag` VALUES ('30', '22', '14', '1587616144538');
INSERT INTO `mto_post_tag` VALUES ('31', '23', '17', '1589340005450');
INSERT INTO `mto_post_tag` VALUES ('32', '23', '18', '1589340005459');

-- ----------------------------
-- Table structure for mto_resource
-- ----------------------------
DROP TABLE IF EXISTS `mto_resource`;
CREATE TABLE `mto_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `md5` varchar(100) NOT NULL DEFAULT '',
  `path` varchar(255) NOT NULL DEFAULT '',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_MD5` (`md5`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_resource
-- ----------------------------
INSERT INTO `mto_resource` VALUES ('1', '0', null, '3LMKU9CL5VLAM3UHU5G96EMTO1', 'http://image.itaka.top//images/000/75b53c9654bfaaac3f47c5824ceb7701.png', '2020-04-09 10:13:14');
INSERT INTO `mto_resource` VALUES ('2', '0', null, '245QMNRSH5NSFITJF9ITB3U6D2', 'http://image.itaka.top//images/000/442ead7df225bf1f2ecde997563f19a2.png', '2020-04-09 10:13:17');

-- ----------------------------
-- Table structure for mto_security_code
-- ----------------------------
DROP TABLE IF EXISTS `mto_security_code`;
CREATE TABLE `mto_security_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL,
  `created` datetime NOT NULL,
  `expired` datetime NOT NULL,
  `key_` varchar(64) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_shxjkbkgnpxa80pnv4ts57o19` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_security_code
-- ----------------------------
INSERT INTO `mto_security_code` VALUES ('1', '277694', '2020-03-23 13:52:46', '2020-03-23 14:22:46', '2', '1', 'gmh0612@163.com', '1');
INSERT INTO `mto_security_code` VALUES ('2', '647217', '2020-03-27 16:19:56', '2020-05-15 11:59:49', 'gmh0612@163.com', '0', 'gmh0612@163.com', '3');
INSERT INTO `mto_security_code` VALUES ('3', '749369', '2020-03-27 16:54:30', '2020-04-24 20:14:37', '1980822294@qq.com', '1', '1980822294@qq.com', '3');
INSERT INTO `mto_security_code` VALUES ('7', '872710', '2020-04-24 19:46:46', '2020-04-24 20:16:46', '1073960301@qq.com', '1', '1073960301@qq.com', '3');
INSERT INTO `mto_security_code` VALUES ('11', '014071', '2020-05-15 11:23:39', '2020-05-15 11:53:39', '18851986646@189.com', '0', '18851986646@189.com', '3');

-- ----------------------------
-- Table structure for mto_tag
-- ----------------------------
DROP TABLE IF EXISTS `mto_tag`;
CREATE TABLE `mto_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latest_post_id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `posts` int(11) NOT NULL,
  `thumbnail` varchar(128) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ki38gg59bw5agwxsc4xtednf` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_tag
-- ----------------------------
INSERT INTO `mto_tag` VALUES ('1', '2020-03-23 13:24:24', null, '1', 'Git', '1', null, '2020-03-23 13:24:24');
INSERT INTO `mto_tag` VALUES ('2', '2020-03-23 13:24:24', null, '1', 'Push', '1', null, '2020-03-23 13:24:24');
INSERT INTO `mto_tag` VALUES ('3', '2020-03-31 17:31:50', null, '7', 'das', '2', null, '2020-04-22 16:19:42');
INSERT INTO `mto_tag` VALUES ('4', '2020-03-31 17:31:50', null, '2', 'dasdsad', '1', null, '2020-03-31 17:31:50');
INSERT INTO `mto_tag` VALUES ('5', '2020-03-31 17:37:15', null, '3', 'dasdas', '1', null, '2020-03-31 17:37:15');
INSERT INTO `mto_tag` VALUES ('6', '2020-03-31 17:37:15', null, '3', 'dasdasdasd', '1', null, '2020-03-31 17:37:15');
INSERT INTO `mto_tag` VALUES ('7', '2020-04-22 17:22:48', null, '12', '测试', '2', null, null);
INSERT INTO `mto_tag` VALUES ('8', '2020-04-22 17:18:30', null, '12', '测', '1', null, null);
INSERT INTO `mto_tag` VALUES ('9', '2020-04-22 17:18:37', null, '12', '试', '1', null, null);
INSERT INTO `mto_tag` VALUES ('10', '2020-04-22 17:20:23', null, '12', '嗯大神', '1', null, null);
INSERT INTO `mto_tag` VALUES ('11', '2020-04-22 17:33:32', null, '14', '大苏打', '2', null, null);
INSERT INTO `mto_tag` VALUES ('12', '2020-04-23 11:17:52', null, '17', '三', '1', null, null);
INSERT INTO `mto_tag` VALUES ('13', '2020-04-23 12:22:02', null, '21', '桃花', '1', null, null);
INSERT INTO `mto_tag` VALUES ('14', '2020-04-23 12:29:05', null, '22', 'SpringBoot', '1', null, null);
INSERT INTO `mto_tag` VALUES ('15', '2020-04-23 15:45:07', null, '23', 'idea', '0', null, null);
INSERT INTO `mto_tag` VALUES ('16', '2020-05-12 14:05:57', null, '23', 'mybatis', '0', null, null);
INSERT INTO `mto_tag` VALUES ('17', '2020-05-13 11:20:05', null, '23', 'dasd adas', '1', null, null);
INSERT INTO `mto_tag` VALUES ('18', '2020-05-13 11:20:05', null, '23', 'dasdgsdf', '1', null, null);
INSERT INTO `mto_tag` VALUES ('19', '2020-05-14 22:47:27', null, '24', 'rabbitmq', '0', null, null);
INSERT INTO `mto_tag` VALUES ('20', '2020-05-14 22:47:27', null, '24', 'elasticsearch', '0', null, null);

-- ----------------------------
-- Table structure for mto_user
-- ----------------------------
DROP TABLE IF EXISTS `mto_user`;
CREATE TABLE `mto_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT '/dist/images/ava/default.png',
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `status` int(5) NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `gender` int(5) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
  `signature` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_user
-- ----------------------------
INSERT INTO `mto_user` VALUES ('1', 'admin', '小豆丁', 'http://image.snowsea.com.cn/static/ee7cc2e32a6bfe3e4269230af8a237fa.jpg', 'example@mtons.com', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2017-08-06 17:52:41', '2020-05-08 15:55:15', '2020-03-27 13:34:49', '0', '1', '8', '0', '');
INSERT INTO `mto_user` VALUES ('2', 'qq974691001', '郭靖宇', '/storage/avatars/000/000/002_240.jpg', 'gmh0612@163.com', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2020-03-27 16:54:46', '2020-04-22 16:55:06', '2020-04-17 10:20:40', '0', null, '3', '15', '现实与所愿交错，不肯有如果，真心追时光蹉跎，可否有你我？');
INSERT INTO `mto_user` VALUES ('3', 'q974691001', 'q974691001', 'https://en.gravatar.com/userimage/154673030/b9a54b5b990a61cc074668b2e2a0b8c0.png', '1980822294@qq.com', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2020-03-27 16:54:46', null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('4', 'g13721549833', 'g13721549833', 'https://en.gravatar.com/userimage/154673030/b9a54b5b990a61cc074668b2e2a0b8c0.png', '1073960301@qq.com', 'UUKHSDDI5KPA43A8VL06V0TU2', '0', '2020-04-24 19:47:00', null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('5', '123123123', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('6', '12312312444', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('7', '123', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('8', '1235', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('9', '1234', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('10', '12512', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);
INSERT INTO `mto_user` VALUES ('11', '12314', 'sdasdas', '/dist/images/ava/default.png', null, 'UUKHSDDI5KPA43A8VL06V0TU2', '0', null, null, null, '0', null, '0', '0', null);

-- ----------------------------
-- Table structure for mto_user_oauth
-- ----------------------------
DROP TABLE IF EXISTS `mto_user_oauth`;
CREATE TABLE `mto_user_oauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `access_token` varchar(128) DEFAULT NULL,
  `expire_in` varchar(128) DEFAULT NULL,
  `oauth_code` varchar(128) DEFAULT NULL,
  `oauth_type` int(11) DEFAULT NULL,
  `oauth_user_id` varchar(128) DEFAULT NULL,
  `refresh_token` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_user_oauth
-- ----------------------------

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(140) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES ('1', '进入后台', 'admin', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('2', '栏目管理', 'channel:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('3', '编辑栏目', 'channel:update', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('4', '删除栏目', 'channel:delete', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('5', '文章管理', 'post:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('6', '编辑文章', 'post:update', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('7', '删除文章', 'post:delete', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('8', '评论管理', 'comment:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('9', '删除评论', 'comment:delete', '8', '0', '0');
INSERT INTO `shiro_permission` VALUES ('10', '用户管理', 'user:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('11', '用户授权', 'user:role', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('12', '修改密码', 'user:pwd', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('13', '激活用户', 'user:open', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('14', '关闭用户', 'user:close', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('15', '角色管理', 'role:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('16', '修改角色', 'role:update', '15', '0', '0');
INSERT INTO `shiro_permission` VALUES ('17', '删除角色', 'role:delete', '15', '0', '0');
INSERT INTO `shiro_permission` VALUES ('18', '主题管理', 'theme:index', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('19', '系统配置', 'options:index', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('20', '修改配置', 'options:update', '19', '0', '0');

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(140) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('1', null, 'admin', '0');
INSERT INTO `shiro_role` VALUES ('2', null, 'admin1', '0');
INSERT INTO `shiro_role` VALUES ('3', null, 'admin2', '0');
INSERT INTO `shiro_role` VALUES ('4', null, 'admin3', '0');
INSERT INTO `shiro_role` VALUES ('5', null, 'admin4', '0');
INSERT INTO `shiro_role` VALUES ('6', null, 'admin12', '0');
INSERT INTO `shiro_role` VALUES ('7', null, 'admin123', '0');
INSERT INTO `shiro_role` VALUES ('8', null, 'admin1234', '0');
INSERT INTO `shiro_role` VALUES ('9', null, 'admin112', '0');
INSERT INTO `shiro_role` VALUES ('10', null, 'admin113', '0');
INSERT INTO `shiro_role` VALUES ('11', null, 'admin114', '0');

-- ----------------------------
-- Table structure for shiro_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_permission`;
CREATE TABLE `shiro_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_role_permission
-- ----------------------------
INSERT INTO `shiro_role_permission` VALUES ('1', '1', '1');
INSERT INTO `shiro_role_permission` VALUES ('2', '2', '1');
INSERT INTO `shiro_role_permission` VALUES ('3', '3', '1');
INSERT INTO `shiro_role_permission` VALUES ('4', '4', '1');
INSERT INTO `shiro_role_permission` VALUES ('5', '5', '1');
INSERT INTO `shiro_role_permission` VALUES ('6', '6', '1');
INSERT INTO `shiro_role_permission` VALUES ('7', '7', '1');
INSERT INTO `shiro_role_permission` VALUES ('8', '8', '1');
INSERT INTO `shiro_role_permission` VALUES ('9', '9', '1');
INSERT INTO `shiro_role_permission` VALUES ('10', '10', '1');
INSERT INTO `shiro_role_permission` VALUES ('11', '11', '1');
INSERT INTO `shiro_role_permission` VALUES ('12', '12', '1');
INSERT INTO `shiro_role_permission` VALUES ('13', '13', '1');
INSERT INTO `shiro_role_permission` VALUES ('14', '14', '1');
INSERT INTO `shiro_role_permission` VALUES ('15', '15', '1');
INSERT INTO `shiro_role_permission` VALUES ('16', '16', '1');
INSERT INTO `shiro_role_permission` VALUES ('17', '17', '1');
INSERT INTO `shiro_role_permission` VALUES ('18', '18', '1');
INSERT INTO `shiro_role_permission` VALUES ('19', '19', '1');
INSERT INTO `shiro_role_permission` VALUES ('20', '20', '1');

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES ('1', '1', '1');
