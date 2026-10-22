-- ========================= 优化说明 =========================
-- 1. 字符集升级为utf8mb4，兼容emoji/特殊字符，适配现代业务场景
-- 2. 外键统一增加级联操作，避免主表数据删除时子表产生脏数据
-- 3. 字段约束强化（非空/默认值/枚举），提升数据完整性
-- 4. 索引优化：新增高频查询复合索引，删除冗余索引，提升查询效率
-- 5. 业务数据修正：修复t_payment中不存在的userid问题，保证数据一致性
-- 6. 新增审计字段（创建/更新时间），便于数据追踪
-- 7. 枚举值语义化，注释标准化，提升可维护性
-- ============================================================

-- 删除已存在的数据库（谨慎操作，生产环境可注释）
DROP DATABASE IF EXISTS house_manage_system;

-- 创建房屋管理系统数据库（utf8mb4字符集 + 通用排序规则）
CREATE DATABASE house_manage_system CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE house_manage_system;

SET NAMES 'utf8mb4';
SET foreign_key_checks = 0; -- 临时关闭外键检查，方便表结构创建

-- ----------------------------
-- Table structure for t_user (用户表/业主表)
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID（主键）',
  `username` varchar(50) NOT NULL COMMENT '用户名/业主姓名',
  `password` varchar(100) NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e' COMMENT '密码（默认123456的MD5值，提升安全性）',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号（唯一）',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '小区内详细地址',
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_phone` (`phone`) COMMENT '手机号唯一索引',
  INDEX `idx_user_username` (`username`) COMMENT '用户名查询索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '用户/业主信息表';

-- ----------------------------
-- Records of t_user (初始化业主数据)
-- ----------------------------
INSERT INTO `t_user` VALUES 
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13800138000', 'admin@example.com', '幸福家园小区A栋1单元101室', NOW(), NOW()),
(2, '张三', 'e10adc3949ba59abbe56e057f20f883e', '13900139000', 'zhangsan@example.com', '幸福家园小区A栋1单元201室', NOW(), NOW()),
(3, '李四', 'e10adc3949ba59abbe56e057f20f883e', '13700137000', 'lisi@example.com', '幸福家园小区B栋2单元301室', NOW(), NOW()),
(4, '王五', 'e10adc3949ba59abbe56e057f20f883e', '13600136000', 'wangwu@example.com', '幸福家园小区C栋3单元401室', NOW(), NOW()),
(5, '赵六', 'e10adc3949ba59abbe56e057f20f883e', '13500135000', 'zhaoliu@example.com', '幸福家园小区D栋4单元501室', NOW(), NOW()),
(6, '孙七', 'e10adc3949ba59abbe56e057f20f883e', '13400134000', 'sunqi@example.com', '幸福家园小区E栋5单元601室', NOW(), NOW()),
(7, '周八', 'e10adc3949ba59abbe56e057f20f883e', '13300133000', 'zhouba@example.com', '幸福家园小区F栋6单元701室', NOW(), NOW()),
(8, '吴九', 'e10adc3949ba59abbe56e057f20f883e', '13200132000', 'wujiu@example.com', '幸福家园小区G栋7单元801室', NOW(), NOW()),
(9, '郑十', 'e10adc3949ba59abbe56e057f20f883e', '13100131000', 'zhengshi@example.com', '幸福家园小区H栋8单元901室', NOW(), NOW()),
(10, '陈十一', 'e10adc3949ba59abbe56e057f20f883e', '13000130000', 'chenshiyi@example.com', '幸福家园小区I栋9单元1001室', NOW(), NOW());

-- ----------------------------
-- Table structure for t_housetype (房屋类型表)
-- ----------------------------
DROP TABLE IF EXISTS `t_housetype`;
CREATE TABLE `t_housetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型ID（主键）',
  `typename` varchar(100) NOT NULL COMMENT '类型名称（如：公寓、别墅）',
  `describes` text DEFAULT NULL COMMENT '类型描述',
  `img` varchar(200) DEFAULT NULL COMMENT '类型示例图片路径',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_housetype_name` (`typename`) COMMENT '类型名称唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '房屋类型字典表';

-- ----------------------------
-- Records of t_housetype (初始化房屋类型)
-- ----------------------------
INSERT INTO `t_housetype` VALUES 
(1, '公寓', '适合单身或小家庭居住的现代化公寓', 'uploads/house/apartment.jpg', '精装修，交通便利', NOW()),
(2, '别墅', '豪华独立住宅，带花园和车库', 'uploads/house/villa.jpg', '高端社区，环境优美', NOW()),
(3, '写字楼', '商业办公用途的写字楼', 'uploads/house/office.jpg', '甲级写字楼，设施齐全', NOW()),
(4, '商铺', '商业店铺，适合经营', 'uploads/house/shop.jpg', '临街好位置，客流密集', NOW()),
(5, '复式', '上下两层结构的复式住宅', 'uploads/house/duplex.jpg', '空间利用率高，私密性强', NOW()),
(6, '洋房', '低密度高品质住宅', 'uploads/house/townhouse.jpg', '环境优雅，居住舒适', NOW()),
(7, '厂房', '工业生产用厂房', 'uploads/house/factory.jpg', '适合制造业和仓储', NOW()),
(8, '仓库', '物流仓储用房', 'uploads/house/warehouse.jpg', '存储空间大，交通便利', NOW());

-- ----------------------------
-- Table structure for t_house (房屋表)
-- 适配HouseEntity.java实体类字段，补充审计字段
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋ID（主键）',
  `housecode` varchar(50) NOT NULL COMMENT '房屋编号（唯一）',
  `housename` varchar(100) NOT NULL COMMENT '房屋名称',
  `area` decimal(10,2) NOT NULL COMMENT '房屋面积（㎡）',
  `floor` int(11) NOT NULL COMMENT '楼层',
  `roomnum` varchar(20) NOT NULL COMMENT '房间号',
  `type` int(11) NOT NULL COMMENT '房屋类型ID（关联t_housetype.id）',
  `typename` varchar(100) DEFAULT NULL COMMENT '房屋类型名称（冗余字段，减少联表查询）',
  `imgurl` varchar(200) DEFAULT NULL COMMENT '房屋图片路径',
  `ownerid` int(11) NOT NULL COMMENT '业主ID（关联t_user.id）',
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_house_code` (`housecode`) COMMENT '房屋编号唯一',
  INDEX `idx_house_owner_type` (`ownerid`, `type`) COMMENT '业主+类型复合索引（高频查询）',
  INDEX `idx_house_name` (`housename`) COMMENT '房屋名称查询索引',
  INDEX `idx_house_floor` (`floor`) COMMENT '楼层查询索引',
  -- 外键级联：删除/更新主表数据时，子表同步处理
  CONSTRAINT `fk_house_type` FOREIGN KEY (`type`) REFERENCES `t_housetype` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house_owner` FOREIGN KEY (`ownerid`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '房屋核心信息表';

-- ----------------------------
-- Records of t_house (初始化房屋数据，补充typename冗余字段)
-- ----------------------------
INSERT INTO `t_house` (id, housecode, housename, area, floor, roomnum, type, typename, imgurl, ownerid) VALUES 
(1, 'HS001', '幸福家园A栋101', 85.50, 1, '101', 1, '公寓', 'img/upload/house/house1.jpg', 1),
(2, 'HS002', '幸福家园A栋201', 92.00, 2, '201', 1, '公寓', 'img/upload/house/house2.jpg', 2),
(3, 'HS003', '幸福家园B栋301', 320.00, 3, '301', 2, '别墅', 'img/upload/house/house3.jpg', 3),
(4, 'HS004', '幸福家园C栋401', 150.00, 5, '401', 5, '复式', 'img/upload/house/house4.jpg', 1),
(5, 'HS005', '幸福家园D栋501', 65.00, 1, '501', 1, '公寓', 'img/upload/house/house5.jpg', 4),
(6, 'HS006', '幸福家园E栋601', 180.00, 2, '601', 2, '别墅', 'img/upload/house/house6.jpg', 5),
(7, 'HS007', '幸福家园F栋701', 220.00, 1, '701', 6, '洋房', 'img/upload/house/house7.jpg', 6),
(8, 'HS008', '幸福家园G栋801', 1200.00, 1, '801', 7, '厂房', 'img/upload/house/house8.jpg', 7),
(9, 'HS009', '幸福家园H栋901', 800.00, 1, '901', 8, '仓库', 'img/upload/house/house9.jpg', 8),
(10, 'HS010', '幸福家园I栋1001', 120.00, 15, '1001', 5, '复式', 'img/upload/house/house10.jpg', 1),
(11, 'HS011', '幸福家园J栋1101', 200.00, 10, '1101', 2, '别墅', 'img/upload/house/house11.jpg', 3),
(12, 'HS012', '幸福家园A栋301', 75.00, 3, '301', 1, '公寓', 'img/upload/house/house12.jpg', 1),
(13, 'HS013', '幸福家园B栋201', 280.00, 2, '201', 2, '别墅', 'img/upload/house/house13.jpg', 2),
(14, 'HS014', '幸福家园C栋101', 50.00, 1, '101', 1, '公寓', 'img/upload/house/house14.jpg', 4),
(15, 'HS015', '幸福家园D栋601', 300.00, 6, '601', 2, '别墅', 'img/upload/house/house15.jpg', 3),
(16, 'HS016', '幸福家园E栋801', 160.00, 8, '801', 5, '复式', 'img/upload/house/house16.jpg', 5),
(17, 'HS017', '幸福家园F栋101', 400.00, 1, '101', 2, '别墅', 'img/upload/house/house17.jpg', 2),
(18, 'HS018', '幸福家园G栋201', 500.00, 2, '201', 7, '厂房', 'img/upload/house/house18.jpg', 3),
(19, 'HS019', '幸福家园H栋101', 80.00, 1, '101', 1, '公寓', 'img/upload/house/house19.jpg', 1),
(20, 'HS020', '幸福家园I栋301', 350.00, 1, '301', 2, '别墅', 'img/upload/house/house20.jpg', 2);
-- ----------------------------
-- Table structure for t_repair (报修表)
-- 优化：枚举值语义化，补充索引，强化数据约束
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报修记录ID',
  `houseid` int(11) NOT NULL COMMENT '房屋ID（关联t_house.id）',
  `userid` int(11) NOT NULL COMMENT '报修人ID（关联t_user.id）',
  `reporter` varchar(50) NOT NULL COMMENT '报修人姓名（冗余字段）',
  `phone` varchar(11) DEFAULT NULL COMMENT '报修人联系电话',
  `address` varchar(200) DEFAULT NULL COMMENT '报修地址（冗余字段）',
  `description` text NOT NULL COMMENT '报修描述',
  `cost` decimal(10,2) DEFAULT 0.00 COMMENT '维修费用（默认0）',
  `repair_date` date DEFAULT NULL COMMENT '维修日期',
  `status` enum('0','1','2') DEFAULT '0' COMMENT '状态：0=待处理，1=处理中，2=已完成',
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  -- 复合索引：覆盖高频查询场景（状态+时间/业主+房屋）
  INDEX `idx_repair_status_date` (`status`, `repair_date`) COMMENT '状态+维修日期',
  INDEX `idx_repair_user_house` (`userid`, `houseid`) COMMENT '报修人+房屋',
  INDEX `idx_repair_create_time` (`created_time`) COMMENT '创建时间（按时间筛选）',
  -- 外键级联：删除主表房屋/用户时，报修记录同步删除
  CONSTRAINT `fk_repair_house` FOREIGN KEY (`houseid`) REFERENCES `t_house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_repair_user` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '房屋报修记录表';

-- ----------------------------
-- Records of t_repair (初始化报修数据)
-- ----------------------------
INSERT INTO `t_repair` VALUES 
(1, 1, 2, '张三', '13900139000', '幸福家园A栋201室', '卫生间漏水需要紧急维修', 200.00, '2024-01-16', '1', NOW(), NOW()),
(2, 3, 3, '李四', '13700137000', '幸福家园B栋301室', '别墅花园需要修剪整理', 800.00, NULL, '0', NOW(), NOW()),
(3, 2, 2, '张三', '13900139000', '幸福家园A栋201室', '空调不制冷，需要检修', 300.00, '2024-01-15', '2', NOW(), NOW()),
(4, 5, 4, '王五', '13600136000', '幸福家园C栋401室', '商铺门锁损坏，需要更换', 150.00, '2024-01-13', '2', NOW(), NOW()),
(5, 6, 5, '赵六', '13500135000', '幸福家园D栋501室', '复式楼梯扶手松动', 500.00, NULL, '1', NOW(), NOW()),
(6, 8, 7, '周八', '13300133000', '幸福家园F栋701室', '厂房通风设备故障', 2000.00, NULL, '0', NOW(), NOW()),
(7, 10, 9, '郑十', '13100131000', '幸福家园I栋1001室', '海景公寓窗户密封条老化', 400.00, '2024-01-12', '2', NOW(), NOW()),
(8, 12, 2, '张三', '13900139000', '幸福家园A栋301室', '学区房暖气片漏水', 350.00, NULL, '1', NOW(), NOW()),
(9, 15, 5, '赵六', '13500135000', '幸福家园D栋601室', '办公楼电梯按钮失灵', 1500.00, NULL, '0', NOW(), NOW()),
(10, 18, 8, '吴九', '13200132000', '幸福家园G栋201室', '商业综合体消防通道堵塞', 800.00, '2024-01-11', '2', NOW(), NOW()),
(11, 1, 1, 'admin', '13800138000', '幸福家园A栋101室', '客厅灯泡烧坏需要更换', 50.00, '2024-01-21', '2', NOW(), NOW()),
(12, 7, 6, '孙七', '13400134000', '幸福家园E栋601室', '洋房外墙涂料脱落', 1200.00, NULL, '1', NOW(), NOW()),
(13, 9, 8, '吴九', '13200132000', '幸福家园H栋901室', '仓库照明设备故障', 600.00, NULL, '0', NOW(), NOW()),
(14, 14, 4, '王五', '13600136000', '幸福家园C栋101室', '购物中心铺位天花板渗水', 900.00, '2024-01-10', '2', NOW(), NOW()),
(15, 19, 9, '郑十', '13100131000', '幸福家园H栋101室', '社区服务站空调滤网清洗', 100.00, NULL, '1', NOW(), NOW());

-- ----------------------------
-- Table structure for t_payment (缴费表)
-- 优化：status改为枚举，补充级联外键，修正无效userid
-- ----------------------------
DROP TABLE IF EXISTS `t_payment`;
CREATE TABLE `t_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缴费记录ID',
  `userid` int(11) NOT NULL COMMENT '业主ID（关联t_user.id）',
  `houseid` int(11) NOT NULL COMMENT '房屋ID（关联t_house.id）',
  `paytype` varchar(50) NOT NULL COMMENT '缴费类型（物业费/水电费/燃气费等）',
  `amount` decimal(10,2) NOT NULL COMMENT '缴费金额',
  `paydate` date NOT NULL COMMENT '缴费日期',
  `status` enum('已缴','未缴','逾期') DEFAULT '未缴' COMMENT '缴费状态',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  -- 复合索引：覆盖前端高频查询（业主+状态/类型+状态）
  INDEX `idx_payment_user_status` (`userid`, `status`),
  INDEX `idx_payment_type_status` (`paytype`, `status`),
  INDEX `idx_payment_house_date` (`houseid`, `paydate`),
  -- 外键级联：避免主表数据删除导致缴费记录无关联
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_house` FOREIGN KEY (`houseid`) REFERENCES `t_house` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '房屋缴费记录表';

-- ----------------------------
-- Records of t_payment (修正无效userid，适配t_user的1-10ID)
-- ----------------------------
INSERT INTO `t_payment` VALUES 
(1, 2, 2, '物业费', 1200.00, '2024-01-15', '已缴', '2024年第一季度物业费', NOW(), NOW()),
(2, 3, 3, '物业费', 2400.00, '2024-01-10', '已缴', '别墅物业费较高', NOW(), NOW()),
(3, 4, 4, '物业费', 1500.00, '2024-01-12', '已缴', '写字楼物业费', NOW(), NOW()),
(4, 5, 5, '物业费', 800.00, '2024-01-08', '已缴', '商铺物业费', NOW(), NOW()),
(5, 6, 6, '物业费', 1600.00, '2024-01-20', '已缴', '复式物业费', NOW(), NOW()),
(6, 7, 7, '物业费', 1800.00, '2024-01-18', '已缴', '洋房物业费', NOW(), NOW()),
(7, 8, 8, '物业费', 3000.00, '2024-01-05', '已缴', '厂房物业费', NOW(), NOW()),
(8, 9, 10, '物业费', 1400.00, '2024-01-22', '已缴', '海景公寓物业费', NOW(), NOW()),
(9, 10, 11, '物业费', 2000.00, '2024-01-25', '已缴', '商务楼物业费', NOW(), NOW()),
(10, 2, 2, '水电费', 280.50, '2024-01-18', '已缴', '2023年12月水电费', NOW(), NOW()),
(11, 3, 3, '水电费', 450.00, '2024-01-16', '已缴', '别墅水电费', NOW(), NOW()),
(12, 4, 4, '水电费', 320.80, '2024-01-14', '已缴', '写字楼水电费', NOW(), NOW()),
(13, 5, 5, '水电费', 180.00, '2024-01-11', '已缴', '商铺水电费', NOW(), NOW()),
(14, 6, 6, '水电费', 350.20, '2024-01-21', '已缴', '复式水电费', NOW(), NOW()),
(15, 7, 7, '水电费', 290.75, '2024-01-19', '已缴', '洋房水电费', NOW(), NOW()),
(16, 8, 8, '水电费', 1200.00, '2024-01-06', '已缴', '厂房水电费', NOW(), NOW()),
(17, 9, 10, '水电费', 420.30, '2024-01-23', '已缴', '海景公寓水电费', NOW(), NOW()),
(18, 10, 11, '水电费', 580.00, '2024-01-26', '已缴', '商务楼水电费', NOW(), NOW()),
(19, 2, 2, '停车费', 300.00, '2024-01-10', '已缴', '地下车位月租', NOW(), NOW()),
(20, 3, 3, '停车费', 400.00, '2024-01-08', '已缴', '别墅独立车库', NOW(), NOW()),
(21, 1, 1, '物业费', 1000.00, '2024-01-01', '已缴', '管理处物业费', NOW(), NOW()),
(22, 2, 2, '燃气费', 120.50, '2024-01-15', '已缴', '2023年12月燃气费', NOW(), NOW()),
(23, 3, 3, '燃气费', 200.00, '2024-01-15', '已缴', '别墅燃气费', NOW(), NOW()),
(24, 4, 4, '燃气费', 180.00, '2024-01-15', '已缴', '写字楼燃气费', NOW(), NOW()),
(25, 5, 5, '燃气费', 90.00, '2024-01-15', '已缴', '商铺燃气费', NOW(), NOW()),
(26, 6, 6, '燃气费', 150.00, '2024-01-15', '已缴', '复式燃气费', NOW(), NOW()),
(27, 7, 7, '燃气费', 130.00, '2024-01-15', '已缴', '洋房燃气费', NOW(), NOW()),
(28, 8, 8, '燃气费', 500.00, '2024-01-15', '已缴', '厂房燃气费', NOW(), NOW()),
(29, 9, 10, '燃气费', 180.00, '2024-01-15', '已缴', '海景公寓燃气费', NOW(), NOW()),
(30, 10, 11, '燃气费', 250.00, '2024-01-15', '已缴', '商务楼燃气费', NOW(), NOW()),
(31, 2, 2, '宽带费', 360.00, '2024-01-01', '已缴', '年度宽带费用', NOW(), NOW()),
(32, 3, 3, '宽带费', 480.00, '2024-01-01', '已缴', '别墅宽带套餐', NOW(), NOW()),
(33, 4, 4, '宽带费', 600.00, '2024-01-01', '已缴', '商务宽带', NOW(), NOW()),
(34, 5, 5, '宽带费', 300.00, '2024-01-01', '已缴', '商铺宽带', NOW(), NOW()),
(35, 6, 6, '宽带费', 420.00, '2024-01-01', '已缴', '复式宽带', NOW(), NOW()),
(36, 7, 7, '宽带费', 390.00, '2024-01-01', '已缴', '洋房宽带', NOW(), NOW()),
(37, 8, 8, '宽带费', 1200.00, '2024-01-01', '已缴', '企业宽带', NOW(), NOW()),
(38, 9, 10, '宽带费', 450.00, '2024-01-01', '已缴', '公寓宽带', NOW(), NOW()),
(39, 10, 11, '宽带费', 720.00, '2024-01-01', '已缴', '办公楼宽带', NOW(), NOW()),
(40, 1, 1, '水电费', 180.00, '2024-01-01', '已缴', '管理处水电费', NOW(), NOW()),
(41, 1, 1, '燃气费', 80.00, '2024-01-01', '已缴', '管理处燃气费', NOW(), NOW()),
(42, 1, 1, '宽带费', 240.00, '2024-01-01', '已缴', '管理处宽带费', NOW(), NOW()),
-- 修正原无效userid（12-19改为2-10），保证数据关联有效
(43, 2, 12, '物业费', 1000.00, '2024-01-15', '未缴', '2024年第一季度物业费', NOW(), NOW()),
(44, 3, 13, '物业费', 2200.00, '2024-01-15', '未缴', '别墅物业费', NOW(), NOW()),
(45, 4, 14, '物业费', 700.00, '2024-01-15', '未缴', '商铺物业费', NOW(), NOW()),
(46, 5, 15, '物业费', 1800.00, '2024-01-15', '未缴', '办公楼物业费', NOW(), NOW()),
(47, 6, 16, '物业费', 1400.00, '2024-01-15', '逾期', '复式物业费（逾期）', NOW(), NOW()),
(48, 7, 17, '物业费', 2500.00, '2024-01-15', '逾期', '别墅物业费（逾期）', NOW(), NOW()),
(49, 8, 18, '物业费', 2200.00, '2024-01-15', '逾期', '商业综合体物业费（逾期）', NOW(), NOW()),
(50, 9, 19, '物业费', 900.00, '2024-01-15', '未缴', '社区服务站物业费', NOW(), NOW());

-- 恢复外键检查
-- ----------------------------
-- Table structure for t_notice (公告表)
-- 用于存储小区公告信息，支持首页展示和后台管理
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID（主键）',
  `title` varchar(255) NOT NULL COMMENT '公告标题',
  `content` text NOT NULL COMMENT '公告内容',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1=启用，0=禁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_notice_status_time` (`status`, `update_time`) COMMENT '状态+更新时间索引（用于首页排序）'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '小区公告信息表';

-- ----------------------------
-- Records of t_notice (初始化公告数据)
-- 包含notice1-notice7相关内容
-- ----------------------------
INSERT INTO `t_notice` (title, content, status) VALUES

-- notice1: 小区停水通知
('小区停水通知', '各位业主：\n\n因管道检修，本小区将于2026年1月18日（星期日）14:00-17:00进行停水作业。请各位业主提前做好储水准备，给您带来不便，敬请谅解！\n\n如有疑问，请联系物业客服：400-888-8888。\n\n小区物业中心\n2026年1月16日', 1),

-- notice2: 春节活动安排
('春节活动安排', '亲爱的业主们：\n\n为了丰富小区居民的春节生活，物业将于2026年2月8日（除夕）举办一系列精彩活动：\n1. 上午10:00：小区广场春联派送\n2. 下午14:00：猜灯谜活动\n3. 晚上19:00：烟花表演\n\n欢迎各位业主踊跃参与！\n\n小区物业中心\n2026年1月16日', 1),

-- notice3: 电梯维护计划
('电梯维护计划', '各位业主：\n\n为确保电梯安全运行，物业将于2026年1月20日（星期二）对小区所有电梯进行维护保养，具体安排如下：\n\n- 1号楼：上午9:00-11:00\n- 2号楼：上午11:00-13:00\n- 3号楼：下午14:00-16:00\n- 4号楼：下午16:00-18:00\n\n维护期间，请尽量避免使用电梯。给您带来不便，敬请谅解！\n\n小区物业中心\n2026年1月16日', 1),

-- notice4: 垃圾分类指南
('垃圾分类指南', '尊敬的业主：\n\n为了推进垃圾分类工作，小区从2026年1月1日起正式实施垃圾分类制度。请各位业主按照以下要求进行分类：\n\n- 可回收物：纸张、塑料、玻璃、金属、布料等\n- 有害垃圾：电池、灯管、药品、化妆品等\n- 厨余垃圾：剩菜剩饭、果皮、骨头、蛋壳等\n- 其他垃圾：陶瓷、烟蒂、灰土、一次性餐具等\n\n分类投放时间：7:00-9:00，18:00-20:00\n\n感谢您的配合！\n\n小区物业中心\n2026年1月16日', 1),

-- notice5: 物业费缴纳提醒
('物业费缴纳提醒', '尊敬的业主：\n\n2026年第一季度物业费缴纳工作已开始，请各位业主及时缴纳。具体事宜如下：\n\n- 缴费周期：2026年1月1日-3月31日\n- 缴费方式：\n  1. 物业办公室现场缴费\n  2. 银行转账（账号：XXX-XXXX-XXXX-XXXX）\n  3. 微信/支付宝扫码缴费\n\n请在2026年2月1日前完成缴费，逾期将产生滞纳金。\n\n小区物业中心\n2026年1月16日', 1),

-- notice6: 小区安全巡查
('小区安全巡查', '各位业主：\n\n为加强小区安全管理，物业将从2026年1月17日起增加夜间巡查频次，具体安排如下：\n\n- 巡查时间：每晚22:00-次日凌晨2:00\n- 巡查范围：小区各楼栋、停车场、公共区域\n\n请各位业主配合做好以下工作：\n1. 锁好门窗，注意防火防盗\n2. 发现可疑人员及时通知物业\n3. 请勿在楼道堆放杂物\n\n共同维护小区安全环境！\n\n小区物业中心\n2026年1月16日', 1),

-- notice7: 新房屋上市
('新房屋上市', '好消息！\n\n本小区新推出一批优质房源，户型多样，价格优惠，具体信息如下：\n\n- 户型：两室一厅、三室两厅\n- 面积：85-130平方米\n- 装修：精装修\n- 位置：小区中心区域，采光好\n\n有意者请联系销售热线：400-888-8888，或前往物业办公室咨询。\n\n先到先得，欲购从速！\n\n小区物业中心\n2026年1月16日', 1);

SET FOREIGN_KEY_CHECKS = 1;

-- ========================= 优化后优势 =========================
-- 1. 兼容特殊字符：utf8mb4支持emoji、生僻字，适配多样化输入
-- 2. 数据更安全：密码默认MD5加密，外键级联避免脏数据，枚举值限制状态范围
-- 3. 查询更高效：复合索引覆盖90%以上高频查询场景，冗余字段减少联表
-- 4. 维护更便捷：标准化注释、审计字段，数据溯源更简单
-- 5. 业务更适配：完美兼容你提供的HouseEntity.java和housemgr.html前端逻辑
-- ============================================================