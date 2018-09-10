/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : car_tenancy

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 10/09/2018 10:23:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addr_region
-- ----------------------------
DROP TABLE IF EXISTS `addr_region`;
CREATE TABLE `addr_region`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名 唯一',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限url',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '权限类型 0-菜单 1-api',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限代码，使用分隔符\":\"',
  `sort_string` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序字符串',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '直接父权限 外键',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有父权限，使用“/”分隔',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-可用，0-禁用',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名 唯一',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-可用，0-禁用',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '（管理）系统用户 主键 (自增)',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名 唯一',
  `wechat_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `last_access_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-可用，0-禁用',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_card` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户 外键',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_user_permission`;
CREATE TABLE `system_user_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户 外键',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户 外键',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_address
-- ----------------------------
DROP TABLE IF EXISTS `tnc_address`;
CREATE TABLE `tnc_address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_id` bigint(20) NULL DEFAULT NULL COMMENT '省 外键',
  `city_id` bigint(20) NULL DEFAULT NULL COMMENT '市 外键',
  `area_id` bigint(20) NULL DEFAULT NULL COMMENT '区 外键',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址 详细地址',
  `store_or_user` tinyint(4) NULL DEFAULT NULL COMMENT '地址类型 0-门店地址 1-用户地址',
  `longitude` decimal(30, 0) NULL DEFAULT NULL COMMENT '经度',
  `latitude` decimal(30, 0) NULL DEFAULT NULL COMMENT '纬度',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_ads
-- ----------------------------
DROP TABLE IF EXISTS `tnc_ads`;
CREATE TABLE `tnc_ads`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0-轮播图 1-其它',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_brand
-- ----------------------------
DROP TABLE IF EXISTS `tnc_brand`;
CREATE TABLE `tnc_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_car
-- ----------------------------
DROP TABLE IF EXISTS `tnc_car`;
CREATE TABLE `tnc_car`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `series` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系列',
  `quantity` int(11) NULL DEFAULT NULL COMMENT '数量',
  `residual` int(11) NULL DEFAULT NULL COMMENT '剩余',
  `year` int(11) NULL DEFAULT NULL COMMENT '年代款',
  `config_section` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置款',
  `seat_quantity` int(11) NULL DEFAULT NULL COMMENT '座 位 数',
  `door_quantity` int(11) NULL DEFAULT NULL COMMENT '车 门 数',
  `fuel_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃料类型',
  `transmission_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变速箱类型',
  `displacement` int(11) NULL DEFAULT NULL COMMENT '排量',
  `octane_rating` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃油标号',
  `driven_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驱动方式',
  `en_itk_form` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发动机进气形式',
  `skylight` tinyint(4) NULL DEFAULT NULL COMMENT '天窗 1-有 0-无',
  `tank_capacity` double(10, 0) NULL DEFAULT NULL COMMENT '油箱容量(升)',
  `speaker` int(11) NULL DEFAULT NULL COMMENT '音箱数量',
  `box_quantity` int(11) NULL DEFAULT NULL COMMENT '厢数',
  `seat` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座椅',
  `reversing_radar` tinyint(4) NULL DEFAULT NULL COMMENT '倒车雷达 1-有 0-无',
  `airbag` int(11) NULL DEFAULT NULL COMMENT '气囊数量',
  `dvd_cd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DVD / CD',
  `gps` tinyint(4) NULL DEFAULT NULL COMMENT 'GPS导航 1-有 0-无',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '车型 外键',
  `store_id` bigint(20) NULL DEFAULT NULL COMMENT '门店 外键',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌 外键',
  `access_times` bigint(20) NULL DEFAULT NULL COMMENT '访问次数',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-上架 2-下架',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_car_item
-- ----------------------------
DROP TABLE IF EXISTS `tnc_car_item`;
CREATE TABLE `tnc_car_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：0-可租 1-正在租赁 2-维修中 3-报废 4-其它',
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT '车（型号）外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_car_pic
-- ----------------------------
DROP TABLE IF EXISTS `tnc_car_pic`;
CREATE TABLE `tnc_car_pic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片1路径',
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT '车（型号） 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_car_type
-- ----------------------------
DROP TABLE IF EXISTS `tnc_car_type`;
CREATE TABLE `tnc_car_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_comment
-- ----------------------------
DROP TABLE IF EXISTS `tnc_comment`;
CREATE TABLE `tnc_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `point` int(11) NULL DEFAULT NULL COMMENT '评分',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价',
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT '车（型号） 外键',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '所属订单 外键',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '用户 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tnc_coupon`;
CREATE TABLE `tnc_coupon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '面值',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细说明',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：0-未使用 1-已使用 2-已过期',
  `begin_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '失效时间',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '所属用户 外键',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_customer
-- ----------------------------
DROP TABLE IF EXISTS `tnc_customer`;
CREATE TABLE `tnc_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号 唯一',
  `id_card` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号 唯一',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `last_access_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `addr_id` bigint(20) NULL DEFAULT NULL COMMENT '用户通信地址 外键',
  `emergency_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人 姓名',
  `emergency_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人 联系电话',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-可用，0-禁用',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_order
-- ----------------------------
DROP TABLE IF EXISTS `tnc_order`;
CREATE TABLE `tnc_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `credentials_type` tinyint(4) NULL DEFAULT NULL COMMENT '证件类型 1-身份证 2-台湾居民来往大陆通行证 3-港澳居民来往内地通行',
  `credentials_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `total_amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '总价 = (下单时)订单价格 + 其它费用',
  `order_amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '(下单时)订单价格=天数*(基础价 + 服务费)*折扣-优惠券面值',
  `base_amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '租赁费用 天数*基础价',
  `service_amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '服务费用 天数*服务费',
  `other_amount` decimal(20, 0) NULL DEFAULT NULL COMMENT '其它费用(租赁过程中产生的额外收费)',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券 外键',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，用于说明扣费项及其它特殊状况',
  `discount` decimal(20, 0) NULL DEFAULT NULL COMMENT '折扣, 0<折扣<=1, 默认为1',
  `deposit` decimal(20, 0) NULL DEFAULT NULL COMMENT '已收押金',
  `return_deposit` decimal(20, 0) NULL DEFAULT NULL COMMENT '退还押金',
  `is_deposit_returned` tinyint(4) NULL DEFAULT NULL COMMENT '是否已退押 0-未退 1-已退',
  `package_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐名',
  `get_store_id` bigint(20) NULL DEFAULT NULL COMMENT '取车门店',
  `return_store_id` bigint(20) NULL DEFAULT NULL COMMENT '还车门店',
  `start_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `return_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '应还时间',
  `real_return_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '实际归还时间',
  `pay_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '支付时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：0-提交订单 1-失效 2-已支付 3-用户取消（退款） 4-完成',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `car_item_id` bigint(20) NULL DEFAULT NULL COMMENT '车item 外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_package_scheme
-- ----------------------------
DROP TABLE IF EXISTS `tnc_package_scheme`;
CREATE TABLE `tnc_package_scheme`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐名',
  `days_min` int(11) NULL DEFAULT NULL COMMENT '天数下限',
  `days_max` int(11) NULL DEFAULT NULL COMMENT '天数上限',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_point
-- ----------------------------
DROP TABLE IF EXISTS `tnc_point`;
CREATE TABLE `tnc_point`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` bigint(20) NULL DEFAULT NULL COMMENT '用户 外键',
  `point` int(11) NULL DEFAULT NULL COMMENT '积分',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_point_log
-- ----------------------------
DROP TABLE IF EXISTS `tnc_point_log`;
CREATE TABLE `tnc_point_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '积分 外键',
  `resource` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分来源',
  `change` int(20) NULL DEFAULT NULL COMMENT '变动',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_price_scheme
-- ----------------------------
DROP TABLE IF EXISTS `tnc_price_scheme`;
CREATE TABLE `tnc_price_scheme`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `base_price` decimal(20, 1) NULL DEFAULT NULL COMMENT '基础价(天)',
  `service_price` decimal(20, 1) NULL DEFAULT NULL COMMENT '服务费(天)',
  `deposit` decimal(20, 0) NULL DEFAULT NULL COMMENT '押金',
  `discount` decimal(20, 1) NULL DEFAULT NULL COMMENT '折扣, 0<折扣<=1, 默认为1',
  `car_id` bigint(20) NULL DEFAULT NULL COMMENT '车（型号） 外键',
  `package_id` bigint(20) NULL DEFAULT NULL COMMENT '套餐 外键',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tnc_store
-- ----------------------------
DROP TABLE IF EXISTS `tnc_store`;
CREATE TABLE `tnc_store`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店名称',
  `addr_id` bigint(20) NULL DEFAULT NULL COMMENT '门店地址 外键',
  `service_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服电话',
  `manager_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店负责人手机号',
  `manager_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店负责人',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-营业，0-歇业',
  `is_deleted` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除 1-删除 ',
  `gmt_create` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
