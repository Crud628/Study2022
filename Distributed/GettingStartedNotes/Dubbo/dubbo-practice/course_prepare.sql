/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.28-log : Database - course_prepare
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`course_prepare` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `course_prepare`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `name` varchar(50) DEFAULT NULL COMMENT '课程名',
  `valid` int(1) DEFAULT '1' COMMENT '是否上架，1是上架，0是下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

/*Table structure for table `course_price` */

DROP TABLE IF EXISTS `course_price`;

CREATE TABLE `course_price` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `price` int(11) DEFAULT NULL COMMENT '价格（单位：元）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course_price` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
