/*
Navicat MySQL Data Transfer

Source Server         : 58.96.190.79
Source Server Version : 50173
Source Host           : 58.96.190.79:3306
Source Database       : mr

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-09-06 17:46:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL COMMENT '分类Id',
  `title` varchar(40) NOT NULL COMMENT '标题',
  `content` blob NOT NULL COMMENT '内容',
  `description` varchar(500) NOT NULL COMMENT '文章简介  用于列表显示',
  `statue` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0：正常  1：不可用',
  `author` varchar(15) DEFAULT 'Coriger' COMMENT '作者',
  `createTime` datetime NOT NULL COMMENT '发表时间',
  `showCount` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  PRIMARY KEY (`id`),
  KEY `fk_t_article_t_category1_idx` (`categoryId`),
  CONSTRAINT `fk_t_article_t_category1` FOREIGN KEY (`categoryId`) REFERENCES `t_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for t_article_image
-- ----------------------------
DROP TABLE IF EXISTS `t_article_image`;
CREATE TABLE `t_article_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imageUrl` varchar(100) NOT NULL COMMENT '图片地址',
  `articleId` int(11) NOT NULL COMMENT '文章Id',
  PRIMARY KEY (`id`,`articleId`),
  KEY `fk_t_article_image_t_article_idx` (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='文章图  主要用于列表浏览';

-- ----------------------------
-- Table structure for t_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag`;
CREATE TABLE `t_article_tag` (
  `articleId` int(11) NOT NULL COMMENT '文章Id',
  `tagId` int(11) NOT NULL COMMENT '标签Id',
  PRIMARY KEY (`articleId`,`tagId`),
  KEY `fk_t_article_has_t_tag_t_tag1_idx` (`tagId`),
  KEY `fk_t_article_has_t_tag_t_article1_idx` (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签中间表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(20) NOT NULL COMMENT '分类名称  唯一',
  `iconClass` varchar(45) NOT NULL COMMENT '图标样式',
  `aliasName` varchar(20) NOT NULL COMMENT '别名  唯一  比如新闻 就用News 代替  栏目Id不显示在url中',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序 （0-10）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `aliasName_UNIQUE` (`aliasName`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='分类表  只支持一级分类  如果需要分多个层次 用标签来协助实现';

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteName` varchar(15) NOT NULL COMMENT '站点名',
  `siteUrl` varchar(45) NOT NULL COMMENT '站点地址',
  `siteDesc` varchar(45) NOT NULL COMMENT '站点描述  简单备注下 ',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='合作伙伴';

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(25) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(25) NOT NULL COMMENT '标签名称  唯一',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tagName_UNIQUE` (`tagName`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='标签表';
