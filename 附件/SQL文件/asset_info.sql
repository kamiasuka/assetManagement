/*
 Navicat MariaDB Data Transfer

 Source Server         : asset
 Source Server Type    : MariaDB
 Source Server Version : 50568 (5.5.68-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : asset

 Target Server Type    : MariaDB
 Target Server Version : 50568 (5.5.68-MariaDB)
 File Encoding         : 65001

 Date: 23/12/2023 15:52:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset_info
-- ----------------------------
DROP TABLE IF EXISTS `asset_info`;
CREATE TABLE `asset_info`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产名',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产分类',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属单位',
  `life` int(10) NOT NULL COMMENT '使用年限',
  `amount` decimal(10, 2) NOT NULL COMMENT '资产价值',
  `amount_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '原始价值' COMMENT '价值类型（原价、账面价值、折旧后残值和市场价值）',
  `use_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '闲置' COMMENT '使用状态，资产的使用状态有八种。分别是在用、待修、在修、备用、闲置、调剂、待报废、报废八种。',
  `review_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '审核中' COMMENT '审核状态，状态有审核中和已通过，默认为审核中',
  `approval_date` datetime NOT NULL COMMENT '批准日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
