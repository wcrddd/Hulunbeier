/*
 Navicat Premium Data Transfer

 Source Server         : dzh2
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : 58.87.74.112:3306
 Source Schema         : hulunbeier2020

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 13/08/2020 20:04:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for affix
-- ----------------------------
DROP TABLE IF EXISTS `affix`;
CREATE TABLE `affix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目名称',
  `contract_id` int(11) DEFAULT NULL COMMENT '合同名称',
  `affix_name` varchar(255) DEFAULT '' COMMENT '附件名',
  `affix_path` varchar(255) DEFAULT '' COMMENT '附件路径',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for construction
-- ----------------------------
DROP TABLE IF EXISTS `construction`;
CREATE TABLE `construction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `construction_name` varchar(255) DEFAULT '' COMMENT '建设单位名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` int(255) DEFAULT '0' COMMENT '删除标志',
  `operator` varchar(255) DEFAULT '' COMMENT '操作者',
  `operator_ip` varchar(255) DEFAULT '' COMMENT '操作人ip',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for construction_progress
-- ----------------------------
DROP TABLE IF EXISTS `construction_progress`;
CREATE TABLE `construction_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '对应项目表id',
  `contract_id` int(11) DEFAULT NULL COMMENT '对应合同表id',
  `report_time` varchar(255) DEFAULT '' COMMENT '上报时间',
  `expend_money` varchar(255) DEFAULT '' COMMENT '花费金额（万元）',
  `progress` varchar(255) DEFAULT '' COMMENT '施工进度',
  `approve` int(255) DEFAULT '0' COMMENT '审核标志位（0未审核，1审核通过，2审核未通过）',
  `creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0，未删除，1删除）',
  `operator` varchar(255) DEFAULT '' COMMENT '操作者(用户id)',
  `operator_ip` varchar(255) DEFAULT '' COMMENT '操作者主机ip',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for construction_unit
-- ----------------------------
DROP TABLE IF EXISTS `construction_unit`;
CREATE TABLE `construction_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '单位名称',
  `unit_nature` varchar(255) DEFAULT '' COMMENT '单位性质',
  `legal_person_number` varchar(11) DEFAULT '' COMMENT '法人证书编号',
  `paper_name` varchar(255) DEFAULT '' COMMENT '证件名称',
  `paper_id` varchar(255) DEFAULT '' COMMENT '证书编号',
  `legal_address` varchar(255) DEFAULT '' COMMENT '法定地址',
  `person_name` varchar(255) DEFAULT '' COMMENT '法人姓名',
  `person_address` varchar(255) DEFAULT '' COMMENT '通讯地址',
  `person_phone` varchar(255) DEFAULT '' COMMENT '电话',
  `person_email` varchar(255) DEFAULT '' COMMENT '电子邮箱',
  `postcode` varchar(255) DEFAULT '' COMMENT '邮政编码',
  `year_income` varchar(255) DEFAULT '' COMMENT '年度收入',
  `year_expenditure` varchar(255) DEFAULT '' COMMENT '年度支出',
  `total_assets` varchar(255) DEFAULT '' COMMENT '资产总额',
  `total_liabilities` varchar(255) DEFAULT '' COMMENT '负债总额',
  `money_source` varchar(255) DEFAULT '' COMMENT '经费来源',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract_information
-- ----------------------------
DROP TABLE IF EXISTS `contract_information`;
CREATE TABLE `contract_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `contract_type` varchar(255) DEFAULT '' COMMENT '合同类别',
  `contract_name` varchar(255) DEFAULT '' COMMENT '合同名称',
  `party_a` varchar(255) DEFAULT '' COMMENT '甲方',
  `party_b` varchar(255) DEFAULT '' COMMENT '乙方',
  `party_c` varchar(255) DEFAULT '' COMMENT '丙方',
  `contract_range` varchar(255) DEFAULT '' COMMENT '合同范围',
  `contract_term` varchar(255) DEFAULT '' COMMENT '合同期限',
  `signing_time` varchar(255) DEFAULT '' COMMENT '签订时间',
  `contrscy_money` varchar(255) DEFAULT '' COMMENT '合同金额（万元）',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1审核通过，2不通过）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（为0：未删除，为id:删除）',
  `operator` varchar(255) DEFAULT '' COMMENT '操作者（登陆者的id）',
  `operator_ip` varchar(255) DEFAULT '' COMMENT '操作人的设备ip地址',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `opinion` varchar(900) DEFAULT '' COMMENT '审核意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract_tender_relation
-- ----------------------------
DROP TABLE IF EXISTS `contract_tender_relation`;
CREATE TABLE `contract_tender_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `tender_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feasibility_research_report
-- ----------------------------
DROP TABLE IF EXISTS `feasibility_research_report`;
CREATE TABLE `feasibility_research_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '对应建设单位id',
  `construction_address` varchar(255) DEFAULT '' COMMENT '建设地点',
  `construction_content` varchar(255) DEFAULT '' COMMENT '建设内容',
  `investment_amount` varchar(255) DEFAULT NULL COMMENT '投资金额',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2不通过）',
  `examine` int(11) DEFAULT '0' COMMENT '审批（0未审核，1通过，2不通过）',
  `approve_opinion` varchar(900) DEFAULT '' COMMENT '审核意见',
  `examine_opinion` varchar(900) DEFAULT '' COMMENT '审批意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guide
-- ----------------------------
DROP TABLE IF EXISTS `guide`;
CREATE TABLE `guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '' COMMENT '项目标题',
  `document_id` varchar(255) DEFAULT '' COMMENT '发布文号',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '申报截止时间',
  `content` varchar(255) DEFAULT '' COMMENT '正文',
  `appendix` varchar(255) DEFAULT '' COMMENT '附件',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guide_unit_relation
-- ----------------------------
DROP TABLE IF EXISTS `guide_unit_relation`;
CREATE TABLE `guide_unit_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guide_id` int(11) DEFAULT NULL COMMENT '项目id',
  `unit_id` int(11) DEFAULT NULL COMMENT '建设单位id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modal
-- ----------------------------
DROP TABLE IF EXISTS `modal`;
CREATE TABLE `modal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目名称',
  `contract_id` int(11) DEFAULT NULL COMMENT '合同名称',
  `begin_time` varchar(255) DEFAULT '' COMMENT '开工时间',
  `end_time` varchar(255) DEFAULT '' COMMENT '完工时间',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `money` varchar(255) DEFAULT '' COMMENT '实际金额',
  `opinion` varchar(900) DEFAULT '' COMMENT '审核意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for plan_guide
-- ----------------------------
DROP TABLE IF EXISTS `plan_guide`;
CREATE TABLE `plan_guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '' COMMENT '指南标题',
  `content` varchar(255) DEFAULT '' COMMENT '指南内容',
  `enclosure` varchar(255) DEFAULT '' COMMENT '附件（存储路径）',
  `build_year` varchar(255) DEFAULT '' COMMENT '建设年份',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) DEFAULT '' COMMENT '备注内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(255) DEFAULT '' COMMENT '岗位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for predesign_report
-- ----------------------------
DROP TABLE IF EXISTS `predesign_report`;
CREATE TABLE `predesign_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '对应建设单位id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2不通过）',
  `examine` int(11) DEFAULT '0' COMMENT '审批（0未审核，1通过，2不通过）',
  `approve_opinion` varchar(900) DEFAULT '' COMMENT '审核意见',
  `examine_opinion` varchar(900) DEFAULT '' COMMENT '审批意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for predesign_report_appendix
-- ----------------------------
DROP TABLE IF EXISTS `predesign_report_appendix`;
CREATE TABLE `predesign_report_appendix` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `appendix_name` varchar(255) DEFAULT '',
  `appendix` varchar(255) DEFAULT '',
  `uploader` varchar(255) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approve` int(11) DEFAULT '0',
  `examine` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_plan
-- ----------------------------
DROP TABLE IF EXISTS `project_plan`;
CREATE TABLE `project_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `content` varchar(255) DEFAULT '' COMMENT '申报内容',
  `project_estimate` float(11,0) DEFAULT NULL COMMENT '项目预算（万元）',
  `money_source` varchar(11) DEFAULT '' COMMENT '资金来源（1为财政项目，2为自筹）',
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2未通过）',
  `examine` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2未通过）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间（默认为CURRENT_TIMESTAMP，且不勾选根据当前时间戳更新）',
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_id` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approve_opinion` varchar(900) DEFAULT '',
  `examine_opinion` varchar(900) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_store
-- ----------------------------
DROP TABLE IF EXISTS `project_store`;
CREATE TABLE `project_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(255) DEFAULT '' COMMENT '项目id，按照规则生成',
  `apply_type` int(11) DEFAULT NULL COMMENT '申报类型（1为指南，2为自主)',
  `guide_id` int(11) DEFAULT NULL COMMENT '对应的guide指南id',
  `project_name` varchar(255) DEFAULT '' COMMENT '项目名称',
  `department` varchar(255) DEFAULT '' COMMENT '主管部门',
  `build_year` varchar(255) DEFAULT '' COMMENT '建设年度',
  `place` varchar(255) DEFAULT '' COMMENT '建设地点',
  `project_type` int(11) DEFAULT NULL COMMENT '**',
  `invest_direction` varchar(255) DEFAULT '' COMMENT '投资方向',
  `industry_direction` varchar(255) DEFAULT '',
  `implement_dept` varchar(255) DEFAULT '',
  `invest_estimate` float(11,0) DEFAULT NULL COMMENT '投资估算（万元）',
  `content` varchar(255) DEFAULT '' COMMENT '建设内容',
  `appendix` varchar(255) DEFAULT '' COMMENT '附件（地址）',
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2未通过）',
  `approve_time` varchar(255) DEFAULT '' COMMENT '审核时间',
  `opinion` varchar(255) DEFAULT '' COMMENT '审批意见',
  `constru_unit_id` int(11) DEFAULT NULL COMMENT '申报该项目的建设单位id',
  `planed_flag` int(255) DEFAULT '0' COMMENT '项目是否申报(默认是0没有申报，1申报过了)',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project_type
-- ----------------------------
DROP TABLE IF EXISTS `project_type`;
CREATE TABLE `project_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_type` varchar(255) DEFAULT '' COMMENT '岗位',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_button
-- ----------------------------
DROP TABLE IF EXISTS `right_button`;
CREATE TABLE `right_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_id` int(11) DEFAULT NULL,
  `button_name` varchar(255) DEFAULT '',
  `del_flag` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for right_role
-- ----------------------------
DROP TABLE IF EXISTS `right_role`;
CREATE TABLE `right_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=983 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_name` varchar(255) DEFAULT '',
  `last_id` int(11) DEFAULT '0' COMMENT '上一级权限id（如果为第一级则为0）',
  `url` varchar(255) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(0) DEFAULT '',
  `operator_id` varchar(0) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(0) DEFAULT '',
  `operator_ip` varchar(0) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for study_report
-- ----------------------------
DROP TABLE IF EXISTS `study_report`;
CREATE TABLE `study_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `appendix_name` varchar(255) DEFAULT '',
  `appendix` varchar(255) DEFAULT '',
  `uploader` varchar(255) DEFAULT '',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approve` int(11) DEFAULT '0' COMMENT '审核（0未审核，1通过，2不通过）',
  `examine` int(11) DEFAULT '0' COMMENT '审批（0未审核，1通过，2不通过）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tender_information
-- ----------------------------
DROP TABLE IF EXISTS `tender_information`;
CREATE TABLE `tender_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `tender_type` varchar(255) DEFAULT '' COMMENT '招标类型',
  `tender_document_name` varchar(255) DEFAULT '' COMMENT '招标文件名称',
  `tender_content` varchar(255) DEFAULT '' COMMENT '招标内容',
  `tender_money` float(255,0) DEFAULT NULL COMMENT '招标金额',
  `organization_form` varchar(255) DEFAULT '' COMMENT '组织（采购）组织形式',
  `tender_way` varchar(255) DEFAULT '' COMMENT '招标方式',
  `approve_unit` varchar(255) DEFAULT '' COMMENT '邀请招标批准单位',
  `agency_name` varchar(255) DEFAULT '' COMMENT '代理单位名称',
  `agency_qualification` varchar(255) DEFAULT '' COMMENT '代理资质',
  `bid_number` int(11) DEFAULT NULL COMMENT '招标人数量',
  `bid_winner_name` varchar(255) DEFAULT '' COMMENT '中标单位名称',
  `bid_winner_qualification` varchar(255) DEFAULT '' COMMENT '中标单位资质',
  `bid_money` float(255,0) DEFAULT NULL COMMENT '中标金额（万元）',
  `contract_money` float(255,0) DEFAULT NULL COMMENT '合同金额（万元）',
  `open_tender_time` varchar(255) DEFAULT '' COMMENT '开标时间',
  `approve` int(255) DEFAULT '0' COMMENT '招标审核标志（0，未审核，1审核通过，2不通过）',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opinion` varchar(900) DEFAULT '' COMMENT '审核意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tender_type
-- ----------------------------
DROP TABLE IF EXISTS `tender_type`;
CREATE TABLE `tender_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) DEFAULT NULL COMMENT '行业名称行业名称（农业，林业，建筑业，信息业等）\n',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `role_id` int(11) DEFAULT '0',
  `user_type` int(11) DEFAULT NULL,
  `department_unit_id` int(11) DEFAULT NULL COMMENT 'user_type为1时，此字段为岗位部门的id，user_type为2时，此字段为建设单位的id',
  `email` varchar(255) DEFAULT '',
  `creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0',
  `operator` varchar(255) DEFAULT '',
  `operator_ip` varchar(255) DEFAULT '',
  `operator_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for view_rights
-- ----------------------------
DROP VIEW IF EXISTS `view_rights`;
CREATE ALGORITHM=UNDEFINED DEFINER=`manage`@`%` SQL SECURITY DEFINER VIEW `view_rights` AS select `a`.`id` AS `id`,`a`.`right_name` AS `right_name`,`a`.`last_id` AS `last_id`,`a`.`url` AS `url`,`a`.`create_time` AS `create_time`,`a`.`del_flag` AS `del_flag`,`a`.`operator` AS `operator`,`a`.`operator_id` AS `operator_id`,`a`.`operator_time` AS `operator_time`,`b`.`right_name` AS `last_right_name` from (`rights` `a` left join `rights` `b` on((`a`.`last_id` = `b`.`id`)));

-- ----------------------------
-- View structure for view_rights_id_role_id
-- ----------------------------
DROP VIEW IF EXISTS `view_rights_id_role_id`;
CREATE ALGORITHM=UNDEFINED DEFINER=`manage`@`%` SQL SECURITY DEFINER VIEW `view_rights_id_role_id` AS select `view_right_role`.`role_id` AS `role_id`,`view_right_role`.`role_name` AS `role_name`,group_concat(`view_right_role`.`right_name` separator ',') AS `rights_name`,group_concat(`view_right_role`.`right_id` separator ',') AS `rights_id` from `view_right_role` where (`view_right_role`.`del_flag` = 0) group by `view_right_role`.`role_id`;

-- ----------------------------
-- View structure for view_rights_role
-- ----------------------------
DROP VIEW IF EXISTS `view_rights_role`;
CREATE ALGORITHM=UNDEFINED DEFINER=`manage`@`%` SQL SECURITY DEFINER VIEW `view_rights_role` AS select `view_right_role`.`role_id` AS `role_id`,`view_right_role`.`role_name` AS `role_name`,group_concat(`view_right_role`.`right_name` separator ',') AS `rights_name` from `view_right_role` where (`view_right_role`.`del_flag` = 0) group by `view_right_role`.`role_id`;

-- ----------------------------
-- View structure for view_right_role
-- ----------------------------
DROP VIEW IF EXISTS `view_right_role`;
CREATE ALGORITHM=UNDEFINED DEFINER=`manage`@`%` SQL SECURITY DEFINER VIEW `view_right_role` AS select `right_role`.`id` AS `id`,`right_role`.`right_id` AS `right_id`,`right_role`.`role_id` AS `role_id`,`right_role`.`create_time` AS `create_time`,`right_role`.`del_flag` AS `del_flag`,`right_role`.`operator` AS `operator`,`right_role`.`operator_ip` AS `operator_ip`,`right_role`.`operator_time` AS `operator_time`,`a`.`role_name` AS `role_name`,`b`.`right_name` AS `right_name` from ((`right_role` left join `role` `a` on((`a`.`id` = `right_role`.`role_id`))) left join `rights` `b` on((`b`.`id` = `right_role`.`right_id`)));

-- ----------------------------
-- View structure for view_study_report
-- ----------------------------
DROP VIEW IF EXISTS `view_study_report`;
CREATE ALGORITHM=UNDEFINED DEFINER=`manage`@`%` SQL SECURITY DEFINER VIEW `view_study_report` AS select `study_report`.`id` AS `id`,`study_report`.`project_id` AS `project_id`,`study_report`.`appendix_name` AS `appendix_name`,`study_report`.`appendix` AS `appendix`,`study_report`.`uploader` AS `uploader`,`study_report`.`create_time` AS `create_time`,`study_report`.`del_flag` AS `del_flag`,`study_report`.`operator` AS `operator`,`study_report`.`operator_ip` AS `operator_ip`,`study_report`.`operator_time` AS `operator_time`,`study_report`.`approve` AS `approve`,`study_report`.`examine` AS `examine`,`feasibility_research_report`.`construction_address` AS `construction_address`,`feasibility_research_report`.`construction_content` AS `construction_content`,`feasibility_research_report`.`investment_amount` AS `investment_amount`,`feasibility_research_report`.`remark` AS `remark` from (`study_report` left join `feasibility_research_report` on((`feasibility_research_report`.`project_id` = `study_report`.`project_id`)));

SET FOREIGN_KEY_CHECKS = 1;
