/*
Navicat MySQL Data Transfer
Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : gupao-live
Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001
Date: 2020-10-13 22:55:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Table structure for sys_cron_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_cron_task`;
CREATE TABLE `sys_cron_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL,
  `demo` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_deal_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_deal_log`;
CREATE TABLE `sys_deal_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1382 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_deal_log
-- ----------------------------
INSERT INTO `sys_deal_log` VALUES ('1333', 'admin', '新增或修改菜单', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.MenuController.saveOrUpdate()', '{\"id\":1002046,\"icon\":\"layui-icon-star-fill\",\"menuName\":\"岗位管理\",\"url\":\"/sys/position/list\",\"parentId\":1001,\"sort\":5,\"isShow\":0,\"permission\":\"sys:postion:list\",\"menuType\":\"menu\",\"isSysMenu\":\"1\",\"isLink\":\"1\",\"open\":true,\"page\":1,\"limit\":10}', '7', '127.0.0.1', '2020-08-14 07:24:40');
INSERT INTO `sys_deal_log` VALUES ('1334', 'admin', '保存菜单权限', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.MenuController.saveMenuPermission()', '1', '27', '127.0.0.1', '2020-08-14 07:24:48');
INSERT INTO `sys_deal_log` VALUES ('1335', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '40', '127.0.0.1', '2020-08-14 08:32:52');
INSERT INTO `sys_deal_log` VALUES ('1336', 'admin', '删除岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.delete()', '4', '7', '127.0.0.1', '2020-08-14 08:48:49');
INSERT INTO `sys_deal_log` VALUES ('1337', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '40', '127.0.0.1', '2020-08-14 08:51:18');
INSERT INTO `sys_deal_log` VALUES ('1338', 'admin', '删除岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.delete()', '4', '7', '127.0.0.1', '2020-08-14 08:51:24');
INSERT INTO `sys_deal_log` VALUES ('1339', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '40', '127.0.0.1', '2020-08-14 08:55:35');
INSERT INTO `sys_deal_log` VALUES ('1340', 'admin', '批量删除用户', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.batchDelete()', '[2,3]', '13', '127.0.0.1', '2020-08-14 08:56:23');
INSERT INTO `sys_deal_log` VALUES ('1341', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"password\":\"UUKHSDDI5KPA43A8VL06V0TU2\",\"status\":0,\"createTime\":\"Aug 14, 2020 4:57:11 PM\",\"isSysUser\":\"1\",\"page\":1,\"limit\":10}', '54', '127.0.0.1', '2020-08-14 08:57:12');
INSERT INTO `sys_deal_log` VALUES ('1342', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 4:57:51 PM\",\"remark\":\"财务总监\",\"page\":1,\"limit\":10}', '5', '127.0.0.1', '2020-08-14 08:57:51');
INSERT INTO `sys_deal_log` VALUES ('1343', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"cfo\",\"postName\":\"财务\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 4:58:44 PM\",\"remark\":\"财务\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 08:58:45');
INSERT INTO `sys_deal_log` VALUES ('1344', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"cfo\",\"postName\":\"财务\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 4:59:16 PM\",\"remark\":\"财务\",\"page\":1,\"limit\":10}', '4', '127.0.0.1', '2020-08-14 08:59:16');
INSERT INTO `sys_deal_log` VALUES ('1345', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"NY\",\"postName\":\"普通员工\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 4:59:51 PM\",\"remark\":\"\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 08:59:51');
INSERT INTO `sys_deal_log` VALUES ('1346', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"as\",\"postName\":\"助理\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 5:00:07 PM\",\"remark\":\"助理\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 09:00:07');
INSERT INTO `sys_deal_log` VALUES ('1347', 'admin', '批量删除用户', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.batchDelete()', '[7,6]', '5', '127.0.0.1', '2020-08-14 09:00:14');
INSERT INTO `sys_deal_log` VALUES ('1348', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"cfo\",\"postName\":\"财务\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 5:00:32 PM\",\"remark\":\"财务总监\",\"page\":1,\"limit\":10}', '42', '127.0.0.1', '2020-08-14 09:00:33');
INSERT INTO `sys_deal_log` VALUES ('1349', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postCode\":\"cfo\",\"postName\":\"测试\",\"status\":\"0\",\"createBy\":\"admin\",\"createTime\":\"Aug 14, 2020 5:00:43 PM\",\"remark\":\"\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 09:00:43');
INSERT INTO `sys_deal_log` VALUES ('1350', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postId\":9,\"postCode\":\"cfo\",\"postName\":\"测试\",\"status\":\"0\",\"updateBy\":\"admin\",\"updateTime\":\"Aug 14, 2020 5:01:53 PM\",\"remark\":\"测试\",\"page\":1,\"limit\":10}', '4', '127.0.0.1', '2020-08-14 09:01:53');
INSERT INTO `sys_deal_log` VALUES ('1351', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postId\":9,\"postCode\":\"test\",\"postName\":\"测试\",\"status\":\"0\",\"updateBy\":\"admin\",\"updateTime\":\"Aug 14, 2020 5:02:00 PM\",\"remark\":\"测试\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 09:02:01');
INSERT INTO `sys_deal_log` VALUES ('1352', 'admin', '保存岗位', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.saveOrUpdate()', '{\"postId\":1,\"postCode\":\"ceo\",\"postName\":\"董事长\",\"status\":\"0\",\"updateBy\":\"admin\",\"updateTime\":\"Aug 14, 2020 5:02:09 PM\",\"remark\":\"ceoi\",\"page\":1,\"limit\":10}', '3', '127.0.0.1', '2020-08-14 09:02:09');
INSERT INTO `sys_deal_log` VALUES ('1353', 'admin', '批量删除用户', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.PositionController.batchDelete()', '[9,8]', '5', '127.0.0.1', '2020-08-14 09:02:16');
INSERT INTO `sys_deal_log` VALUES ('1354', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '52', '127.0.0.1', '2020-08-14 09:19:10');
INSERT INTO `sys_deal_log` VALUES ('1355', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"id\":1,\"postId\":5,\"username\":\"admin\",\"realName\":\"系统管理员\",\"status\":1,\"email\":\"285688338@qq.com\",\"phone\":\"15566668888\",\"sex\":1,\"city\":\"湖南\",\"address\":\"娄底\",\"deptId\":1001002,\"roleIds\":[],\"page\":1,\"limit\":10}', '5', '127.0.0.1', '2020-08-14 09:22:07');
INSERT INTO `sys_deal_log` VALUES ('1356', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '36', '127.0.0.1', '2020-08-14 09:25:00');
INSERT INTO `sys_deal_log` VALUES ('1357', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '38', '127.0.0.1', '2020-08-14 09:29:12');
INSERT INTO `sys_deal_log` VALUES ('1358', null, '用户登出', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.logout()', null, '1', '127.0.0.1', '2020-08-14 09:30:28');
INSERT INTO `sys_deal_log` VALUES ('1359', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '14', '127.0.0.1', '2020-08-14 09:30:32');
INSERT INTO `sys_deal_log` VALUES ('1360', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"id\":1,\"postId\":5,\"username\":\"admin\",\"realName\":\"系统管理员\",\"status\":1,\"email\":\"285688338@qq.com\",\"phone\":\"15566668888\",\"sex\":1,\"city\":\"湖南\",\"address\":\"娄底\",\"deptId\":1001002,\"roleIds\":[],\"page\":1,\"limit\":10}', '84382', '127.0.0.1', '2020-08-14 09:32:36');
INSERT INTO `sys_deal_log` VALUES ('1361', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"id\":1,\"postId\":1,\"username\":\"admin\",\"realName\":\"系统管理员\",\"status\":1,\"email\":\"285688338@qq.com\",\"phone\":\"15566668888\",\"sex\":1,\"city\":\"湖南\",\"address\":\"娄底\",\"deptId\":1001002,\"roleIds\":[1,2],\"page\":1,\"limit\":10}', '3131', '127.0.0.1', '2020-08-14 09:42:41');
INSERT INTO `sys_deal_log` VALUES ('1362', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '42', '127.0.0.1', '2020-08-14 09:46:38');
INSERT INTO `sys_deal_log` VALUES ('1363', null, '用户登出', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.logout()', null, '0', '127.0.0.1', '2020-08-14 09:46:55');
INSERT INTO `sys_deal_log` VALUES ('1364', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '12', '127.0.0.1', '2020-08-14 09:46:59');
INSERT INTO `sys_deal_log` VALUES ('1365', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"id\":210,\"postId\":1,\"username\":\"sys_admin\",\"realName\":\"Mic\",\"status\":1,\"email\":\"285688338@qq.com\",\"phone\":\"16607491862\",\"sex\":0,\"city\":\"河北\",\"address\":\"河北\",\"deptId\":1002004,\"roleIds\":[2],\"page\":1,\"limit\":10}', '8', '127.0.0.1', '2020-08-14 09:47:16');
INSERT INTO `sys_deal_log` VALUES ('1366', 'admin', '保存用户信息', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.UserController.saveOrUpdate()', '{\"id\":210,\"postId\":1,\"username\":\"sys_admin\",\"realName\":\"Mic\",\"status\":1,\"email\":\"285688338@qq.com\",\"phone\":\"16607491862\",\"sex\":0,\"city\":\"河北\",\"address\":\"河北\",\"deptId\":1002004,\"roleIds\":[2],\"page\":1,\"limit\":10}', '5', '127.0.0.1', '2020-08-14 09:47:24');
INSERT INTO `sys_deal_log` VALUES ('1367', null, '用户登出', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.logout()', null, '0', '127.0.0.1', '2020-08-14 09:57:05');
INSERT INTO `sys_deal_log` VALUES ('1368', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '8', '127.0.0.1', '2020-08-14 09:57:09');
INSERT INTO `sys_deal_log` VALUES ('1369', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '37', '127.0.0.1', '2020-08-14 09:58:37');
INSERT INTO `sys_deal_log` VALUES ('1370', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '42', '127.0.0.1', '2020-08-15 06:36:34');
INSERT INTO `sys_deal_log` VALUES ('1371', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '37', '127.0.0.1', '2020-08-15 06:56:37');
INSERT INTO `sys_deal_log` VALUES ('1372', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '8', '127.0.0.1', '2020-08-15 08:26:45');
INSERT INTO `sys_deal_log` VALUES ('1373', null, '用户登出', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.logout()', null, '0', '127.0.0.1', '2020-08-15 09:01:00');
INSERT INTO `sys_deal_log` VALUES ('1374', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '5', '127.0.0.1', '2020-08-15 09:01:27');
INSERT INTO `sys_deal_log` VALUES ('1375', 'admin', '新增或修改菜单', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.MenuController.saveOrUpdate()', '{\"id\":1002024,\"icon\":\"glyphicon glyphicon-heart\",\"menuName\":\"Druid监听\",\"url\":\"http://47.112.26.69:9009/druid/index.html\",\"parentId\":1002014,\"sort\":5,\"isShow\":1,\"permission\":\"druid:index\",\"menuType\":\"menu\",\"isLink\":\"1\",\"open\":true,\"page\":1,\"limit\":10}', '9', '127.0.0.1', '2020-08-15 09:06:04');
INSERT INTO `sys_deal_log` VALUES ('1376', 'admin', '新增或修改菜单', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.MenuController.saveOrUpdate()', '{\"id\":1002025,\"icon\":\"layui-icon-engine\",\"menuName\":\"首页\",\"parentId\":0,\"sort\":0,\"isShow\":0,\"menuType\":\"direc\",\"isLink\":\"1\",\"open\":true,\"page\":1,\"limit\":10}', '4', '127.0.0.1', '2020-08-15 09:10:15');
INSERT INTO `sys_deal_log` VALUES ('1377', null, '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"admin\",\"page\":1,\"limit\":10}', '47', '127.0.0.1', '2020-10-13 21:33:58');
INSERT INTO `sys_deal_log` VALUES ('1378', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '17', '127.0.0.1', '2020-10-13 21:34:02');
INSERT INTO `sys_deal_log` VALUES ('1379', null, '用户登出', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.logout()', null, '1', '127.0.0.1', '2020-10-13 21:35:17');
INSERT INTO `sys_deal_log` VALUES ('1380', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '11', '127.0.0.1', '2020-10-13 21:35:22');
INSERT INTO `sys_deal_log` VALUES ('1381', 'admin', '用户登录', 'com.gupaoedu.live.gupaoedulive.modules.sys.controller.LoginController.login()', '{\"username\":\"admin\",\"password\":\"123456\",\"page\":1,\"limit\":10}', '38', '127.0.0.1', '2020-10-13 21:39:31');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级部门id',
  `ancestors` varchar(50) DEFAULT NULL COMMENT '祖级列表',
  `sort` int(11) DEFAULT NULL,
  `leader` varchar(20) DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '总公司', null, '0', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1001', '北京分公司', '1', '0,1', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1002', '广州分公司', '1', '0,1', '2', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1003', '长沙分公司', '1', '0,1', '3', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1001001', '规划部', '1001', '0,1,1001', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1001002', '总经办', '1001', '0,1,1001', '3', '草草草', '13822222222', '11@qq.com', null, 'admin', null, '2020-08-12 09:44:59');
INSERT INTO `sys_dept` VALUES ('1002001', '人事部', '1001', '0,1,1001', '3', 'mic', '13822222222', '123@qq.com', null, 'admin', null, '2020-08-12 10:12:49');
INSERT INTO `sys_dept` VALUES ('1002002', '开发部', '1003', '0,1,1003', '4', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1002003', '需求组', '1002002', '0,1,1003,1002002', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1002004', '前端组', '1002002', '0,1,1003,1002002', '3', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1002006', '开发部', '1002002', '0,1,1003,1002002', '1', '111', '', '11@qq.com', null, 'admin', null, '2020-08-12 10:27:38');
INSERT INTO `sys_dept` VALUES ('1002008', '大数据研发', '1003', '0,1,1003', '1', null, null, null, null, null, null, null);
INSERT INTO `sys_dept` VALUES ('1002010', '搬砖部', '1002', '0,1,1002', '2', '存储', '13722882828', 'ww@qq.com', null, 'admin', null, '2020-08-12 09:57:31');
INSERT INTO `sys_dept` VALUES ('1002011', '财务部', '1001', '0,1,1001', '4', 'mic', '13211111111', 'qq@qq.com', '2020-08-12 10:21:28', null, 'admin', null);

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `ismaster` int(11) DEFAULT '1' COMMENT '是否是主管 0是 1否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept_user
-- ----------------------------
INSERT INTO `sys_dept_user` VALUES ('7', '10', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('8', '1', '1001001', '0');
INSERT INTO `sys_dept_user` VALUES ('9', '9', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('12', '14', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('13', '13', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('14', '12', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('15', '11', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('16', '26', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('17', '25', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('18', '24', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('19', '15', '1001001', '1');
INSERT INTO `sys_dept_user` VALUES ('20', '1', '1001002', '0');
INSERT INTO `sys_dept_user` VALUES ('21', '10', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('22', '9', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('23', '12', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('24', '14', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('25', '24', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('26', '202', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('27', '197', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('28', '200', '1001002', '1');
INSERT INTO `sys_dept_user` VALUES ('29', '33', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('30', '9', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('31', '36', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('32', '38', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('33', '43', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('34', '201', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('35', '34', '1001', '1');
INSERT INTO `sys_dept_user` VALUES ('36', '35', '1001', '1');
INSERT INTO `sys_dept_user` VALUES ('37', '46', '1001', '1');
INSERT INTO `sys_dept_user` VALUES ('38', '52', '1001', '1');
INSERT INTO `sys_dept_user` VALUES ('39', '200', '1001', '0');
INSERT INTO `sys_dept_user` VALUES ('40', '198', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('41', '188', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('42', '181', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('43', '48', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('44', '43', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('48', '1', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('49', '57', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('51', '12', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('52', '9', '1002008', '1');
INSERT INTO `sys_dept_user` VALUES ('53', '12', '1002008', '0');
INSERT INTO `sys_dept_user` VALUES ('54', '188', '1002008', '1');
INSERT INTO `sys_dept_user` VALUES ('55', '187', '1002008', '1');
INSERT INTO `sys_dept_user` VALUES ('56', '11', '1002008', '1');
INSERT INTO `sys_dept_user` VALUES ('57', '206', '1002009', '0');
INSERT INTO `sys_dept_user` VALUES ('58', '194', '1002009', '1');
INSERT INTO `sys_dept_user` VALUES ('59', '9', '1002009', '1');
INSERT INTO `sys_dept_user` VALUES ('60', '187', '1002009', '1');
INSERT INTO `sys_dept_user` VALUES ('61', '12', '1002009', '1');
INSERT INTO `sys_dept_user` VALUES ('62', '195', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('63', '12', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('64', '188', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('65', '1', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('66', '209', '1002007', '1');
INSERT INTO `sys_dept_user` VALUES ('67', '210', '1002007', '0');
INSERT INTO `sys_dept_user` VALUES ('68', '206', '1', '0');
INSERT INTO `sys_dept_user` VALUES ('69', '209', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('70', '211', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('71', '208', '1', '1');
INSERT INTO `sys_dept_user` VALUES ('74', '206', '1002003', '1');
INSERT INTO `sys_dept_user` VALUES ('75', '206', '1002001', '1');
INSERT INTO `sys_dept_user` VALUES ('76', '206', '1002006', '1');
INSERT INTO `sys_dept_user` VALUES ('77', '1', '1002010', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(200) NOT NULL,
  `dict_name` varchar(100) DEFAULT NULL COMMENT '数据字典名称',
  `dict_value` varchar(100) DEFAULT NULL COMMENT '数据字典值',
  `dict_level` int(4) DEFAULT NULL COMMENT '数据字典类型',
  `dict_state` int(4) DEFAULT NULL COMMENT '数据字典状态',
  `parent_id` int(200) DEFAULT NULL COMMENT '父级ID',
  `dict_sort` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` int(100) NOT NULL AUTO_INCREMENT,
  `log_level` int(10) DEFAULT NULL COMMENT 'debug(10000),info(20000),warn(30000),error(40000)',
  `url` varchar(100) DEFAULT NULL,
  `user_id` int(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `result` longtext,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:05:57');
INSERT INTO `sys_log` VALUES ('2', '40000', '/sys/user/delete/212', '1', 'admin', 'java.lang.IllegalStateException: getWriter() has already been called for this response', '2020-08-13 14:05:57');
INSERT INTO `sys_log` VALUES ('3', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:07:51');
INSERT INTO `sys_log` VALUES ('4', '40000', '/sys/user/delete/212', '1', 'admin', 'java.lang.IllegalStateException: getWriter() has already been called for this response', '2020-08-13 14:08:25');
INSERT INTO `sys_log` VALUES ('5', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:14:42');
INSERT INTO `sys_log` VALUES ('6', '40000', '/sys/user/delete/212', '1', 'admin', 'java.lang.IllegalStateException: getWriter() has already been called for this response', '2020-08-13 14:14:45');
INSERT INTO `sys_log` VALUES ('7', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:14:53');
INSERT INTO `sys_log` VALUES ('8', '40000', '/sys/user/delete/212', '1', 'admin', 'java.lang.IllegalStateException: getWriter() has already been called for this response', '2020-08-13 14:14:53');
INSERT INTO `sys_log` VALUES ('9', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:15:13');
INSERT INTO `sys_log` VALUES ('10', '40000', '/sys/user/delete/212', '1', 'admin', 'java.lang.IllegalStateException: getWriter() has already been called for this response', '2020-08-13 14:15:29');
INSERT INTO `sys_log` VALUES ('11', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:16:38');
INSERT INTO `sys_log` VALUES ('12', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:17:03');
INSERT INTO `sys_log` VALUES ('13', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:17:14');
INSERT INTO `sys_log` VALUES ('14', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:18:34');
INSERT INTO `sys_log` VALUES ('15', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:20:00');
INSERT INTO `sys_log` VALUES ('16', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:20:21');
INSERT INTO `sys_log` VALUES ('17', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:27:48');
INSERT INTO `sys_log` VALUES ('18', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:31:57');
INSERT INTO `sys_log` VALUES ('19', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:32:34');
INSERT INTO `sys_log` VALUES ('20', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:33:15');
INSERT INTO `sys_log` VALUES ('21', '40000', '/sys/user/delete/212', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:delete]', '2020-08-13 14:33:58');
INSERT INTO `sys_log` VALUES ('22', '40000', '/sys/menu/saveMenuPermission', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:role:auth]', '2020-08-14 06:57:07');
INSERT INTO `sys_log` VALUES ('23', '40000', '/sys/menu/saveMenuPermission', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:role:auth]', '2020-08-14 06:57:14');
INSERT INTO `sys_log` VALUES ('24', '40000', '/sys/menu/saveMenuPermission', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:role:auth]', '2020-08-14 06:58:00');
INSERT INTO `sys_log` VALUES ('25', '40000', '/sys/menu/saveMenuPermission', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:role:auth]', '2020-08-14 06:58:21');
INSERT INTO `sys_log` VALUES ('26', '40000', '/sys/user/saveOrUpdate', '1', 'admin', 'org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [sys:user:edit]', '2020-08-14 07:01:35');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `parent_id` int(11) DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序号',
  `permission` varchar(255) DEFAULT NULL,
  `is_show` tinyint(4) DEFAULT '0' COMMENT '是否显示  0显示 1不显示',
  `menu_type` varchar(20) DEFAULT '' COMMENT '菜单类型 button按钮 menu菜单 api 请求接口',
  `is_sys_menu` varchar(2) DEFAULT NULL COMMENT '是否系统菜单  0系统菜单  1非系统菜单   系统菜单不可删除',
  `is_link` varchar(2) DEFAULT NULL COMMENT '是否外链',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002047 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1001', 'layui-icon-snowflake', '系统管理', '', '0', '1', '', '0', 'direc', '1', null);
INSERT INTO `sys_menu` VALUES ('1002', 'layui-icon-component', '基础数据管理', '', '0', '2', '', '0', 'direc', '0', null);
INSERT INTO `sys_menu` VALUES ('1001001', 'layui-icon-user', '用户管理', '/sys/user/list', '1001', '1', 'sys:user:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1001002', 'layui-icon-group', '角色管理', '/sys/role/list', '1001', '2', 'sys:role:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1001003', 'layui-icon-senior', '菜单管理', '/sys/menu/list', '1001', '3', 'sys:menu:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1001004', 'layui-icon-list', '部门管理', '/sys/dept/list', '1001', '4', 'sys:dept:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1002001', 'layui-icon-util', '数据字典管理', '/data/dic/list', '1002', '1', 'data:dic:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1002002', 'layui-icon-fire', '定时器管理', '/data/scheduler/list', '1002', '2', 'data:scheduler:list', '0', 'menu', '1', null);
INSERT INTO `sys_menu` VALUES ('1002014', 'layui-icon-console', '系统监控', '', '0', '5', null, '0', 'direc', '0', null);
INSERT INTO `sys_menu` VALUES ('1002015', 'glyphicon glyphicon-dashboard', '服务监控', '/monitor/server', '1002014', null, 'monitor:server', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1002016', 'glyphicon glyphicon-list-alt', '系统日志', '/sys/log/list', '1002014', null, 'sys:log:list', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1002019', 'glyphicon glyphicon-heart', 'API', 'http://47.112.26.69:9009/swagger-ui.html', '1002014', null, 'data:swagger', '0', 'menu', '0', null);
INSERT INTO `sys_menu` VALUES ('1002024', 'glyphicon glyphicon-heart', 'Druid监听', 'http://47.112.26.69:9009/druid/index.html', '1002014', '5', 'druid:index', '1', 'menu', '1', '1');
INSERT INTO `sys_menu` VALUES ('1002025', 'layui-icon-engine', '首页', '', '0', '0', '', '0', 'direc', '1', '1');
INSERT INTO `sys_menu` VALUES ('1002026', 'layui-icon-star-fill', '总面板', '/main', '1002025', '1', 'main:dashboard1', '0', 'menu', '1', '1');
INSERT INTO `sys_menu` VALUES ('1002029', 'layui-icon-star-fill', '添加用户', null, '1001001', '1', 'sys:user:add', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002030', 'layui-icon-star-fill', '修改用户', null, '1001001', '2', 'sys:user:edit', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002031', 'layui-icon-star-fill', '删除用户', null, '1001001', '3', 'sys:user:delete', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002032', 'layui-icon-star-fill', '用户详情', null, '1001001', '4', 'sys:user:view', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002034', 'layui-icon-star-fill', '添加角色', null, '1001002', '1', 'sys:role:add', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002035', 'layui-icon-star-fill', '修改角色', null, '1001002', '2', 'sys:role:update', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002036', 'layui-icon-star-fill', '删除角色', null, '1001002', '3', 'sys:role:delete', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002037', 'layui-icon-star-fill', '角色赋权', null, '1001002', '4', 'sys:role:auth', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002038', 'layui-icon-star-fill', '批量删除', null, '1001001', '5', 'sys:user:batchDel', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002039', 'layui-icon-star-fill', '用户列表', null, '1001002', '5', 'sys:role:userlist', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002040', 'layui-icon-star-fill', '添加菜单', null, '1001003', '1', 'sys:menu:add', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002041', 'layui-icon-star-fill', '修改菜单', null, '1001003', '2', 'sys:menu:update', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002042', 'layui-icon-star-fill', '删除菜单', null, '1001003', '3', 'sys:menu:delete', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002043', 'layui-icon-star-fill', '添加部门', null, '1001004', '1', 'sys:dept:add', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002044', 'layui-icon-star-fill', '修改部门', null, '1001004', '2', 'sys:dept:update', '0', 'api', '1', null);
INSERT INTO `sys_menu` VALUES ('1002046', 'layui-icon-star-fill', '岗位管理', '/sys/position/list', '1001', '5', 'sys:postion:list', '0', 'menu', '1', '1');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1096 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('979', '1002025', '4');
INSERT INTO `sys_menu_role` VALUES ('980', '1002026', '4');
INSERT INTO `sys_menu_role` VALUES ('981', '1001', '4');
INSERT INTO `sys_menu_role` VALUES ('982', '1001001', '4');
INSERT INTO `sys_menu_role` VALUES ('983', '1001002', '4');
INSERT INTO `sys_menu_role` VALUES ('984', '1001003', '4');
INSERT INTO `sys_menu_role` VALUES ('985', '1001004', '4');
INSERT INTO `sys_menu_role` VALUES ('1065', '1002025', '1');
INSERT INTO `sys_menu_role` VALUES ('1066', '1002026', '1');
INSERT INTO `sys_menu_role` VALUES ('1067', '1001', '1');
INSERT INTO `sys_menu_role` VALUES ('1068', '1001001', '1');
INSERT INTO `sys_menu_role` VALUES ('1069', '1002029', '1');
INSERT INTO `sys_menu_role` VALUES ('1070', '1002030', '1');
INSERT INTO `sys_menu_role` VALUES ('1071', '1002031', '1');
INSERT INTO `sys_menu_role` VALUES ('1072', '1002032', '1');
INSERT INTO `sys_menu_role` VALUES ('1073', '1002038', '1');
INSERT INTO `sys_menu_role` VALUES ('1074', '1001002', '1');
INSERT INTO `sys_menu_role` VALUES ('1075', '1002034', '1');
INSERT INTO `sys_menu_role` VALUES ('1076', '1002035', '1');
INSERT INTO `sys_menu_role` VALUES ('1077', '1002036', '1');
INSERT INTO `sys_menu_role` VALUES ('1078', '1002037', '1');
INSERT INTO `sys_menu_role` VALUES ('1079', '1002039', '1');
INSERT INTO `sys_menu_role` VALUES ('1080', '1001003', '1');
INSERT INTO `sys_menu_role` VALUES ('1081', '1002040', '1');
INSERT INTO `sys_menu_role` VALUES ('1082', '1002041', '1');
INSERT INTO `sys_menu_role` VALUES ('1083', '1002042', '1');
INSERT INTO `sys_menu_role` VALUES ('1084', '1001004', '1');
INSERT INTO `sys_menu_role` VALUES ('1085', '1002043', '1');
INSERT INTO `sys_menu_role` VALUES ('1086', '1002044', '1');
INSERT INTO `sys_menu_role` VALUES ('1087', '1002046', '1');
INSERT INTO `sys_menu_role` VALUES ('1088', '1002', '1');
INSERT INTO `sys_menu_role` VALUES ('1089', '1002001', '1');
INSERT INTO `sys_menu_role` VALUES ('1090', '1002002', '1');
INSERT INTO `sys_menu_role` VALUES ('1091', '1002014', '1');
INSERT INTO `sys_menu_role` VALUES ('1092', '1002015', '1');
INSERT INTO `sys_menu_role` VALUES ('1093', '1002016', '1');
INSERT INTO `sys_menu_role` VALUES ('1094', '1002019', '1');
INSERT INTO `sys_menu_role` VALUES ('1095', '1002024', '1');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) DEFAULT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-08-14 09:02:09', 'ceoi');
INSERT INTO `sys_post` VALUES ('5', 'cfo', '财务', null, '0', 'admin', '2020-08-14 08:59:16', null, null, '财务');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `role_type` tinyint(4) DEFAULT '1' COMMENT '角色类型 0系统角色  1其他角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员', '0', '2020-08-06 00:32:49', '0');
INSERT INTO `sys_role` VALUES ('2', '管理员', '管理员', '0', '2020-08-14 22:50:01', '1');
INSERT INTO `sys_role` VALUES ('3', '普通用户', '普通用户', '0', '2020-08-14 22:50:44', '1');
INSERT INTO `sys_role` VALUES ('4', '系统管理员', '系统管理员', '0', '2020-08-14 22:51:07', '0');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(20) DEFAULT '1' COMMENT '任务状态',
  `job_group` varchar(20) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `dept_id` int(20) DEFAULT NULL COMMENT '所属部门',
  `post_id` int(20) DEFAULT NULL COMMENT '所属岗位id',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `sex` tinyint(2) DEFAULT '1' COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL,
  `birth` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  `address` varchar(255) DEFAULT NULL COMMENT '常用住址',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `status` int(8) NOT NULL DEFAULT '0' COMMENT '0启用，1停用',
  `is_sys_user` varchar(2) DEFAULT NULL COMMENT '是否系统用户 0 是 1否  系统用户不可删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1001002', '1', 'admin', 'UUKHSDDI5KPA43A8VL06V0TU2', '系统管理员', '2020-08-14 19:03:50', '2082233439@qq.com', '15566668888', '1', '1558549283111.png', '2020-08-14 19:03:50', '长沙', '湖南', '1', '1');
INSERT INTO `sys_user` VALUES ('210', '1002004', '1', 'sys_admin', 'UUKHSDDI5KPA43A8VL06V0TU2', 'Mic', '2020-08-14 19:03:53', '2082233439@qq.com', '16607491862', '0', '1559129754517.png', '2020-08-14 19:03:53', '长沙', '河北', '1', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('133', '1', '1');
INSERT INTO `sys_user_role` VALUES ('136', '210', '2');
