/*
 Navicat Premium Data Transfer

 Source Server         : Local_8
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : course_price

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 22/10/2022 20:30:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL,
  `course_id` int NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `valid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 1, '1111', 1);
INSERT INTO `course` VALUES (2, 2, '222', 1);
INSERT INTO `course` VALUES (3, 3, '333', 1);

-- ----------------------------
-- Table structure for course_price
-- ----------------------------
DROP TABLE IF EXISTS `course_price`;
CREATE TABLE `course_price`  (
  `id` int NOT NULL,
  `course_id` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_price
-- ----------------------------
INSERT INTO `course_price` VALUES (1, 1, 11);

SET FOREIGN_KEY_CHECKS = 1;
