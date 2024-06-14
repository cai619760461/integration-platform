CREATE DATABASE IF NOT EXISTS test_medical_manage DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

-- 数据字典类型
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name` varchar(100) DEFAULT NULL COMMENT '字典名称',
    `dict_type` varchar(100) DEFAULT NULL COMMENT '字典类型',
    `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- 数据字典数据
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_data` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_sort` int(11) DEFAULT '0' COMMENT '字典排序',
    `dict_label` varchar(100) DEFAULT NULL COMMENT '字典标签',
    `dict_value` varchar(100) DEFAULT NULL COMMENT '字典键值',
    `dict_type` varchar(100) DEFAULT NULL COMMENT '字典类型',
    `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
    `is_default` char(1) DEFAULT 0 COMMENT '是否默认（1是 0否）',
    `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- 机构
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `code` varchar(128) NOT NULL COMMENT '机构id',
    `position` int(11) DEFAULT NULL COMMENT '机构序号',
    `type_code` varchar(128) DEFAULT NULL COMMENT '机构类型编码',
    `type_name` varchar(128) NOT NULL COMMENT '机构类型名称',
    `name` varchar(128) DEFAULT NULL COMMENT '机构名称',
    `contact_name` varchar(128) DEFAULT NULL COMMENT '机构联系人',
    `contact_title` varchar(128) DEFAULT NULL COMMENT '机构联系人职位',
    `contact_phone` varchar(128) DEFAULT NULL COMMENT '机构联系人电话',
    `district` varchar(512) DEFAULT NULL COMMENT '机构所属区域',
    `county` varchar(512) DEFAULT NULL COMMENT '机构所属县镇',
    `address` varchar(512) DEFAULT NULL COMMENT '机构地址',
    `picturl` blob COMMENT '机构图片',
    `content_type` varchar(128) DEFAULT NULL COMMENT '机构图片类型',
    `remark` varchar(512) DEFAULT NULL COMMENT '备注',
    `enabled` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用 1启用/0未启用',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `org_code_IDX` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构信息表';


-- 字典数据
-- 专家科室
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('专家科室', 'sys_expert_dept', 0, 'system', 'system', '专家科室列表');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '内科专家', '0', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '外科专家', '1', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '妇产科专家', '2', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '儿科专家', '3', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '五官科专家', '4', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(5, '皮肤科专家', '5', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(6, '精神科专家', '6', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL),
(99, '其他', '99', 'sys_expert_dept', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 疾病 肿瘤专家，糖尿病专家，心血管疾病专家，神经系统疾病专家
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('专长疾病类型', 'sys_expert_disease', 0, 'system', 'system', '专长疾病类型列表');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '肿瘤专家', '0', 'sys_expert_disease', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '糖尿病专家', '1', 'sys_expert_disease', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '心血管疾病专家', '2', 'sys_expert_disease', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '神经系统疾病专家', '3', 'sys_expert_disease', NULL, NULL, 0, 0, 'system', 'system', NULL),
(99, '其他', '99', 'sys_expert_disease', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 治疗手段 手术专家，药物治疗专家
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('专长治疗手段', 'sys_expert_treat', 0, 'system', 'system', '专长治疗手段列表');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '手术专家', '0', 'sys_expert_treat', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '药物治疗专家', '1', 'sys_expert_treat', NULL, NULL, 0, 0, 'system', 'system', NULL),
(99, '其他', '99', 'sys_expert_treat', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 地区等级 省级专家，市级专家，县级专家
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('专家地区等级', 'sys_expert_region', 0, 'system', 'system', '专家地区等级');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '省级专家', '0', 'sys_expert_region', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '市级专家', '1', 'sys_expert_region', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '县级专家', '2', 'sys_expert_region', NULL, NULL, 0, 0, 'system', 'system', NULL),
(99, '其他', '99', 'sys_expert_region', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 医师基本信息
DROP TABLE IF EXISTS `doctor_info`;
CREATE TABLE `doctor_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
    `name` varchar(32) NOT NULL COMMENT '姓名',
    `sex` tinyint(4) NOT NULL COMMENT '性别 1 男 2 女 9 未知',
    `identity_no` VARCHAR(32) NOT NULL COMMENT '身份证',
    `birthday` date NOT NULL COMMENT '出生日期',
    `ethnicity` varchar(10) DEFAULT NULL COMMENT '民族',
    `phone_number` varchar(32) DEFAULT NULL COMMENT '联系电话',
    `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
    `user_name` varchar(32) NOT NULL COMMENT '用户名（工号）',
    `role_id` int(11) NOT NULL COMMENT '角色编码',
    `role_name` varchar(128) NOT NULL COMMENT '角色名称',
    `district_code` varchar(128) NOT NULL COMMENT '机构id，org-code',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UK_USER_NAME_IDX` (`USER_NAME`) USING BTREE COMMENT '用户名(工号)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医师基本信息';

-- 医生教育经历
DROP TABLE IF EXISTS `doctor_education`;
CREATE TABLE `doctor_education` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `doctor_id` int(11) NOT NULL COMMENT '医生id',
    `school` varchar(128) DEFAULT NULL COMMENT '医生毕业院校',
    `subject` varchar(128) DEFAULT NULL COMMENT '医生专业',
    `learn_time` int(11) DEFAULT NULL COMMENT '医生学制',
    `learn_level_code` varchar(128) DEFAULT NULL COMMENT '医生学历编码',
    `learn_level_name` varchar(128) DEFAULT NULL COMMENT '医生学历名称',
    `degree` varchar(128) DEFAULT NULL COMMENT '医生学位',
    `graduation_time` varchar(128) DEFAULT NULL COMMENT '医生毕业时间',
    `service_education` varchar(128) DEFAULT '' COMMENT '全日制学历',
    `credits` varchar(6) DEFAULT '' COMMENT '学分',
    `learn_item` text COMMENT '医生学习培训经历',
    `work_item` text COMMENT '医生工作经历',
    `continuous_educational` text COMMENT '医生连续教育',
    `winning_record` text COMMENT '获奖记录',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `DOCTOR_ID_IDX` (`doctor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生教育经历';

-- 医生资格信息表
DROP TABLE IF EXISTS `doctor_qualification`;
CREATE TABLE `doctor_qualification` (
    `id` int(11)NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `doctor_id` int(11) DEFAULT NULL COMMENT '医生Id',
    `code` varchar(128) NOT NULL COMMENT '资格证书编码',
    `type` varchar(128) NOT NULL COMMENT '医师类别',
    `qualification_year` varchar(128) NOT NULL COMMENT '获得资格年度',
    `send_documents_org` varchar(128) NOT NULL COMMENT '发证机关',
    `document1_avatar` text COMMENT '证书图片1',
    `document1_avatar_type` varchar(128) DEFAULT NULL COMMENT '证书图片1类型',
    `document2_avatar` text COMMENT '证书图片2',
    `document2_avatar_type` varchar(128) DEFAULT NULL COMMENT '证书图片2类型',
    `document3_avatar` text COMMENT '证书图片3',
    `document3_avatar_type` varchar(128) DEFAULT NULL COMMENT '证书图片3类型',
    `issue_date` date DEFAULT NULL COMMENT '签发日期',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `DOCTOR_ID_IDX` (`doctor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生资格信息表';

-- 医生执业信息表
DROP TABLE IF EXISTS `doctor_practicepoint`;
CREATE TABLE `doctor_practicepoint` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生执业信息id',
    `doctor_id` int(11) DEFAULT NULL COMMENT '医生Id',
    `code` varchar(128) DEFAULT NULL COMMENT '执业证书编码',
    `practice_level` varchar(128) DEFAULT NULL COMMENT '执业级别',
    `practice_title` varchar(128) DEFAULT NULL COMMENT '任职资格',
    `practice_title_code` varchar(128) DEFAULT NULL COMMENT '发证机关',
    `practice_item` varchar(128) DEFAULT NULL COMMENT '执业范围',
    `practice_type` varchar(128) DEFAULT NULL COMMENT '执业类型',
    `check_org` varchar(128) DEFAULT NULL COMMENT '审批机构',
    `check_date` date DEFAULT NULL COMMENT '审批日期',
    `practice_address` varchar(128) DEFAULT NULL COMMENT '执业地点',
    `practice_org` varchar(128) DEFAULT NULL COMMENT '主要执业机构',
    `practice_org_address` varchar(128) DEFAULT NULL COMMENT '执业机构地址',
    `practice_dept` varchar(128) DEFAULT NULL COMMENT '院内科室',
    `document1_avatar` text COMMENT '执业证书图片1',
    `document1_avatar_type` varchar(128) DEFAULT NULL COMMENT '执业证书图片1类型',
    `document2_avatar` text COMMENT '执业证书图片2',
    `document2_avatar_type` varchar(128) DEFAULT NULL COMMENT '执业证书图片2类型',
    `document3_avatar` text COMMENT '执业证书图片3',
    `document3_avatar_type` varchar(128) DEFAULT NULL COMMENT '执业证书图片3类型',
    `enabled` tinyint(11) NOT NULL DEFAULT '1' COMMENT '是否有效 1有效/0无效',
    `title_information` varchar(128) DEFAULT '' COMMENT '职称信息',
    `is_general_practitioner` char(1) DEFAULT '' COMMENT '是否全科医生',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `DOCTOR_ID_IDX` (`doctor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生执业信息表';

-- 医生执业项信息 多机构备案
DROP TABLE IF EXISTS `doctor_practicepoint_item`;
CREATE TABLE `doctor_practicepoint_item` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生执业信息id',
    `practicepoint_id` int(11) DEFAULT NULL COMMENT '医生执业Id',
    `name` varchar(128) DEFAULT NULL COMMENT '机构名称',
    `effective_start_date` date DEFAULT NULL COMMENT '有效开始时间',
    `effective_end_date` date DEFAULT NULL COMMENT '有效结束时间',
    `remark_org` varchar(128) DEFAULT NULL COMMENT '备案机构',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `PRACTICEPOINT_ID_IDX` (`practicepoint_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生执业项信息 多机构备案';