/*
 Navicat Premium Data Transfer

 Source Server         : wsl
 Source Server Type    : MariaDB
 Source Server Version : 100338
 Source Host           : wsl.local:3306
 Source Schema         : read_and_note

 Target Server Type    : MariaDB
 Target Server Version : 100338
 File Encoding         : 65001

 Date: 20/03/2023 04:18:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `article_abstract` varchar(8000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `from_site` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `first_publish` datetime NULL DEFAULT NULL,
  `last_edit` datetime NULL DEFAULT NULL,
  `last_read` datetime NULL DEFAULT NULL,
  `progress` float NULL DEFAULT NULL,
  `is_read` tinyint(1) NULL DEFAULT NULL,
  `is_liked` tinyint(1) NULL DEFAULT NULL,
  `is_collected` tinyint(1) NULL DEFAULT NULL,
  `views` int(11) NULL DEFAULT NULL,
  `likes` int(11) NULL DEFAULT NULL,
  `dislikes` int(11) NULL DEFAULT NULL,
  `articleTypes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uid` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_index`(`id`) USING BTREE,
  INDEX `uid_index`(`uid`) USING BTREE,
  INDEX `author_index`(`author`) USING BTREE,
  INDEX `time_index`(`first_publish`) USING BTREE,
  FULLTEXT INDEX `title_index`(`title`),
  FULLTEXT INDEX `content_index`(`content`),
  CONSTRAINT `uid_id_index` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
