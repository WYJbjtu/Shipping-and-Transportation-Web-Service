/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3309
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3309
 Source Schema         : wuliu

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 15/06/2023 22:24:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人姓名',
  `sender_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人电话',
  `sender_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寄件人地址',
  `addressee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `addressee_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人联系电话',
  `addressee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人地址',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物件名称',
  `weight` double NULL DEFAULT NULL COMMENT '寄件重量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `expresspoints_id` bigint(20) NULL DEFAULT NULL COMMENT '快递点编号',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '待发车  运输中  派送中  已签收',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发车时间',
  `car_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型  普货 特快小车 货运飞机',
  `driver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '签收时间',
  `order_no` bigint(20) NULL DEFAULT NULL COMMENT '物流单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '阿道夫希特勒', '15599079892', '帝国大厦101号', '斯大林', '15612341234', '莫斯科克里姆林宫', '豹式坦克', 25000, 25007.00, 1686742088365, '运输中', '2023-06-15 05:44:18', '货运飞机', '大牛', NULL, 2344456457456);
INSERT INTO `t_order` VALUES (2, '阿道夫希特勒', '15599079892', '帝国大厦101号', '罗斯福', '13312341234', '华盛顿白宫101号', 'V3导弹', 2000, 2007.00, 1686742088364, '待发车', NULL, NULL, NULL, NULL, 1686815319283);
INSERT INTO `t_order` VALUES (3, '张三', '13812341234', '上海市西藏北路101号', '刘二', '15912344321', '北京市王府井大街101号', '茅台王子酒', 4, 11.00, 1686742088365, '待发车', NULL, NULL, NULL, NULL, 1686821758144);
INSERT INTO `t_order` VALUES (4, '墨索尼里', '15599091234', '罗马帝国大厦101号', '戴高乐', '13512345678', '凯旋门1号', '高射炮', 200, 207.00, 1686742088365, '运输中', '2023-06-15 13:09:47', '货运飞机', '小王', NULL, 1686834533408);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名首字母缩写加学号后四位',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码为8位数字加字母',
  `stu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流站点；  托运人；  系统管理员',
  `expresspoints_id` bigint(20) NULL DEFAULT NULL COMMENT '物流站点编号',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '张三', 'lgx0113', '123456', '', NULL, NULL, NULL, '托运人', NULL, '13812341234');
INSERT INTO `tb_user` VALUES (2, '李四', 'gak0106', '123456', '', NULL, NULL, NULL, '托运人', NULL, '13612341234');
INSERT INTO `tb_user` VALUES (3, '管理员', 'admin', '123', '', 20, '女', '二人若若若若', 'admin', NULL, '13756789098');
INSERT INTO `tb_user` VALUES (4, '丁小旺', 'dxw4234', 'dxw123', '', NULL, NULL, NULL, '托运人', NULL, '13012341234');
INSERT INTO `tb_user` VALUES (5, '张三', 'zhangsan', 'zs123456', NULL, NULL, NULL, NULL, '托运人', NULL, '15512349089');
INSERT INTO `tb_user` VALUES (6, '阿黄快递站', 'ahuang', 'ah123456', NULL, NULL, NULL, NULL, '快递点', 1686742088365, '15823459089');
INSERT INTO `tb_user` VALUES (7, '龙华快递站', 'longhua', 's123456', NULL, NULL, NULL, NULL, '快递点', 1686742088364, '1581234578');
INSERT INTO `tb_user` VALUES (8, '买买提', 'maimaiti', 's123456', NULL, NULL, NULL, NULL, '托运人', NULL, '13309893456');
INSERT INTO `tb_user` VALUES (9, '阿道夫希特勒', 'Adolf Hitler', 's123456', NULL, 50, '男', '帝国大厦101号', '托运人', NULL, '15599079892');
INSERT INTO `tb_user` VALUES (10, '墨索尼里', 'mosi', 's123456', NULL, 56, '男', '罗马帝国大厦101号', '托运人', NULL, '15599091234');

-- ----------------------------
-- Table structure for wuliu
-- ----------------------------
DROP TABLE IF EXISTS `wuliu`;
CREATE TABLE `wuliu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物流编号',
  `car_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆类别',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机姓名',
  `formgo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发地',
  `togo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的地',
  `go_time` datetime(0) NULL DEFAULT NULL COMMENT '发车时间',
  `good_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wuliu
-- ----------------------------
INSERT INTO `wuliu` VALUES (1, '普货', '测试', '郑州', '安阳', '2023-03-26 16:00:00', '已发货');
INSERT INTO `wuliu` VALUES (4, '普货', 'sdf ', 'sdfsf', 'sdfsdfsf', '2023-03-26 16:00:00', '已发货');

SET FOREIGN_KEY_CHECKS = 1;
