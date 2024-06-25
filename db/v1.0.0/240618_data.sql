-- 新建数据库
CREATE DATABASE IF NOT EXISTS by_integration_platform DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

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
DROP TABLE IF EXISTS `sys_dict_data`;
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
    `unified_credit_identifier` varchar(64) DEFAULT NULL COMMENT '统一信用代码',
    `type_id` int(11) DEFAULT NULL COMMENT '结构类型 字典id',
    `name` varchar(128) DEFAULT NULL COMMENT '机构名称',
    `contact_name` varchar(128) DEFAULT NULL COMMENT '机构联系人',
    `contact_title` varchar(128) DEFAULT NULL COMMENT '机构联系人职位',
    `contact_phone` varchar(128) DEFAULT NULL COMMENT '机构联系人电话',
    `district_dict_id` int(11) DEFAULT NULL COMMENT '市县 字典id',
    `county` varchar(512) DEFAULT NULL COMMENT '机构所属县镇',
    `address` varchar(512) DEFAULT NULL COMMENT '机构地址',
    `picturl` varchar(512) DEFAULT NULL COMMENT '机构图片',
    `content_type` varchar(128) DEFAULT NULL COMMENT '机构图片类型',
    `remark` varchar(512) DEFAULT NULL COMMENT '备注',
    `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否启用 1启用/0未启用',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `ORG_CODE_IDX` (`code`) USING BTREE,
    KEY `ORG_TYPE_ID_IDX` (`type_id`) USING BTREE,
    KEY `ORG_DISTRICT_ID_IDX` (`district_dict_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构信息表';

-- 字典数据
-- 职务 医生/护士/技师/药师/监督员
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('职务', 'sys_position', 0, 'system', 'system', '职务');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(1, '医生', '1', 'sys_position', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '护士', '2', 'sys_position', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '技师', '3', 'sys_position', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '药师', '4', 'sys_position', NULL, NULL, 0, 0, 'system', 'system', NULL),
(5, '监督员', '5', 'sys_position', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 白银市区县
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('区县', 'sys_base_area', 0, 'system', 'system', '区县');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(1, '白银区', '620402', 'sys_base_area', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '平川区', '620403', 'sys_base_area', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '靖远县', '620421', 'sys_base_area', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '会宁县', '620422', 'sys_base_area', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '景泰县', '620423', 'sys_base_area', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 机构类别
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('机构类别', 'sys_org_type', 0, 'system', 'system', '机构类别');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(1, '综合医院', 'A100', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '中医院', 'A210', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '精神卫生中心', 'A520', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '专科医院', 'A526', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(5, '社区卫生服务中心', 'B100', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(6, '社区卫生服务站', 'B200', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(7, '中心卫生院', 'C210', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(8, '卫生院', 'C220', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(9, '门诊部', 'D110', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(10, '村卫生室', 'D600', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(11, '普通诊所', 'D211', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(12, '妇幼保健院', 'G100', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(13, '妇幼保健站', 'G300', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(14, '卫健委', 'P9', 'sys_org_type', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 任职资格
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('任职资格', 'sys_qualifications', 0, 'system', 'system', '任职资格');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(1, '主任医师', '231', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '副主任医师', '232', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '主治医师', '233', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '医师', '234', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(5, '执业助理医师', '235', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(6, '主治中医师', '236', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(7, '副主任药师', '242', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL),
(8, '主管护师', '253', 'sys_qualifications', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 执业级别
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('执业级别', 'sys_practice_level', 0, 'system', 'system', '执业级别');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '执业医师', '1', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '主治中医师', '2', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '主治医师', '3', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(3, '副主任护师', '4', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(4, '执业助理医师', '5', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(5, '主管护师', '6', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(6, '副主任药师', '7', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL),
(7, '副主任医师', '8', 'sys_practice_level', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 执业范围
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('执业范围', 'sys_practice_item', 0, 'system', 'system', '执业范围');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '妇产科专业', '3', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '医学检验、病理专业', '10', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '医学影像和放射治疗专业', '9', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '职业病专业', '8', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '精神卫生专业', '7', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '皮肤病与性病专业', '6', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '眼耳鼻咽喉科专业', '5', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '急救医学专业', '12', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '康复医学专业', '13', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '预防保健专业', '14', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '特种医学与军事医学专业', '15', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '内科', '1', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '外科', '2', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '全科医学专业', '11', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '儿科', '4', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '省级以上卫生行政部门规定的其他专业', '17', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '计划生育技术服务专业', '16', 'sys_practice_item', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 执业类型
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('执业类型', 'sys_practice_type', 0, 'system', 'system', '执业类型');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '公共卫生', '3', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '中医', '4', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '口腔', '2', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '临床', '1', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '药学', '5', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '中医学', '6', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '妇产科护理', '7', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(0, '外科护理', '8', 'sys_practice_type', NULL, NULL, 0, 0, 'system', 'system', NULL);

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

-- 设备类别
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, update_by, remark) VALUES ('设备类别', 'sys_equipment_type', 0, 'system', 'system', '设备类别');
INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, update_by, remark) VALUES
(0, '诊断设备', '0', 'sys_equipment_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(1, '治疗设备', '1', 'sys_equipment_type', NULL, NULL, 0, 0, 'system', 'system', NULL),
(2, '监护设备', '2', 'sys_equipment_type', NULL, NULL, 0, 0, 'system', 'system', NULL);

-- 医师基本信息
DROP TABLE IF EXISTS `doctor_info`;
CREATE TABLE `doctor_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
    `name` varchar(32) NOT NULL COMMENT '姓名',
    `sex` tinyint(4) NOT NULL COMMENT '性别 1 男 2 女 9 未知',
    `position_id` int(11) DEFAULT NULL COMMENT '职务 字典id',
    `identity_no` varchar(32) NOT NULL COMMENT '身份证',
    `identification_photo` varchar(256) DEFAULT NULL COMMENT '证件照地址',
    `birthday` date NOT NULL COMMENT '出生日期',
    `ethnicity` varchar(10) DEFAULT NULL COMMENT '民族',
    `phone_number` varchar(32) DEFAULT NULL COMMENT '联系电话',
    `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
    `user_name` varchar(32) NOT NULL COMMENT '用户名（工号）',
    `org_code` varchar(128) NOT NULL COMMENT '机构id，org-code',
    `score` int(4) NOT NULL DEFAULT '10' COMMENT '当前分数',
    `is_expert` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否专家 0 不是 1 是',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `UK_USER_NAME_IDX` (`USER_NAME`) USING BTREE COMMENT '用户名(工号)',
    KEY `DOCTOR_ORG_CODE_IDX` (`org_code`) USING BTREE
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
    `practice_level_id` int(11) DEFAULT NULL COMMENT '执业级别 字典id',
    `practice_title_id` int(11) DEFAULT NULL COMMENT '任职资格 字典id',
    `practice_title_code` varchar(128) DEFAULT NULL COMMENT '发证机关',
    `practice_item_id` int(11) DEFAULT NULL COMMENT '执业范围 字典id',
    `practice_type_id` int(11) DEFAULT NULL COMMENT '执业类型 字典id',
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
    `is_general_practitioner` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否全科医生 0 不是 1 是',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `DOCTOR_ID_IDX` (`doctor_id`) USING BTREE,
    KEY `LEVEL_ID_IDX` (`practice_level_id`) USING BTREE,
    KEY `TITLE_ID_IDX` (`practice_title_id`) USING BTREE,
    KEY `ITME_ID_IDX` (`practice_item_id`) USING BTREE,
    KEY `TYPE_ID_IDX` (`practice_type_id`) USING BTREE
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

-- 医生评分历史
DROP TABLE IF EXISTS `doctor_score_history`;
CREATE TABLE `doctor_score_history` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `doctor_id` int(11) NOT NULL COMMENT '医生id',
    `event_type` tinyint(4) NOT NULL COMMENT '事件类型 0 减分 1 加分',
    `score` int(4) NOT NULL COMMENT '加减分值',
    `reason` TEXT COMMENT '加减分描述',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `DOCTOR_ID_IDX` (`doctor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生评分历史';

-- 专家标签
DROP TABLE IF EXISTS `expert_label`;
CREATE TABLE `expert_label` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `doctor_id` int(11) NOT NULL COMMENT '医生id',
     `dict_id` int(11) DEFAULT NULL COMMENT '标签id',
     `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `DICT_ID_IDX` (`dict_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家标签';

-- 医疗设备管理
DROP TABLE IF EXISTS `medical_equipment`;
CREATE TABLE `medical_equipment` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(32) DEFAULT NULL COMMENT '设备名称',
    `code` varchar(128) DEFAULT NULL COMMENT '设备编号',
    `equipment_model` varchar(64) DEFAULT NULL COMMENT '设备型号',
    `manufacturer` varchar(32) DEFAULT NULL COMMENT '生产厂家',
    `equipment_image` varchar(255) DEFAULT NULL COMMENT '设备图片文件名',
    `equipment_type_id` int(11) DEFAULT NULL COMMENT '设备类别 字典id',
    `purchase_price` decimal(10,2) DEFAULT NULL COMMENT '采购价格',
    `supplier` varchar(32) DEFAULT NULL COMMENT '供应商',
    `contract_number` varchar(64) DEFAULT NULL COMMENT '合同编号',
    `purchase_date` date DEFAULT NULL COMMENT '购置日期',
    `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '使用状态 0 正常使用 1 维修中 2 闲置',
    `asset_department` varchar(32) DEFAULT NULL COMMENT '资产归属部门',
    `asset_responsible_person` varchar(32) DEFAULT NULL COMMENT '资产责任人',
    `scrap_info` text COMMENT '折旧信息',
    `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `medical_equipment_name_IDX` (`name`) USING BTREE,
    KEY `medical_equipment_code_IDX` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医疗设备管理';

-- 设备附件
DROP TABLE IF EXISTS `medical_equipment_file`;
CREATE TABLE `medical_equipment_file` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `equipment_id` int(11) NOT NULL COMMENT '设备id',
     `file_type` varchar(256) NOT NULL DEFAULT '' COMMENT '文件类别 doc,pdf,xls等',
     `file_name` varchar(1000) NOT NULL DEFAULT '' COMMENT '文件名称',
     `file_path` varchar(1000) NOT NULL DEFAULT '' COMMENT '文件路径',
     `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `EQUIPMENT_ID_IDX` (`equipment_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='设备附件';
