-- MySQL dump 10.13  Distrib 8.0.34, for macos12.6 (arm64)
--
-- Host: 127.0.0.1    Database: rany_acl
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tenant_id` bigint NOT NULL,
  `is_admin` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `account_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `is_deleted` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `last_login_ip` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `feature` varchar(2000) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `head_image` varchar(512) DEFAULT NULL,
  `dingding` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `ding_union_id` varchar(255) DEFAULT NULL,
  `ding_user_id` varchar(255) DEFAULT NULL,
  `work_no` varchar(64) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=966905791770669057 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (768460662077796352,'hzrany租户管理员','18668485565','lovepet24@163.com',768460649511661568,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-30 23:08:05','2024-04-06 13:54:27',NULL,NULL,NULL,NULL,NULL,NULL,'cYtd7IupOcOUmobIBCZV6giEiE',NULL,NULL,NULL,NULL),(768821270497341440,'ybzkwov5租户管理员','18668485565','lovepet24@163.com',768821269968859136,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-30 23:08:05','2022-12-30 23:08:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(768821468053254144,'7tqss9cf租户管理员','18668485565','lovepet24@163.com',768460649511661568,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-30 23:08:52','2024-04-06 13:54:27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(768821902998384640,'qgfuq6o5租户管理员','18668485565','lovepet24@163.com',768821902381821952,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-30 23:10:36','2022-12-30 23:10:36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(768822866530676736,'716wgau9租户管理员','18668485565','lovepet24@163.com',768822865951862784,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-30 23:14:26','2022-12-30 23:14:26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(769008922186100736,'lrt7rvn4租户管理员','18668485565','lovepet24@163.com',769008916825780224,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-31 11:33:45','2022-12-31 11:33:45',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(769013248895889408,'sn5xnp5n租户管理员','18668485565','lovepet24@163.com',769013248086388736,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-31 11:50:56','2022-12-31 11:50:56',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(769013577418944512,'bgxg6ygi租户管理员','18668485565','lovepet24@163.com',769013576844324864,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-31 11:52:15','2022-12-31 11:52:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(769013727499530240,'bsv5f7v6租户管理员','18668485565','lovepet24@163.com',769013726908133376,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-31 11:52:50','2022-12-31 11:52:50',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(769106556057956352,'w5750nvx租户管理员','18668485565','lovepet24@163.com',769106555151986688,'1','BASIC','0','0',NULL,NULL,NULL,'2022-12-31 18:01:43','2022-12-31 18:01:43',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771009241870905344,'94xn9bt6租户管理员','18668485565','lovepet24@163.com',771009195330908160,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 00:02:18','2023-01-06 00:02:18',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771013953005432832,'rp7uzch2租户管理员','18668485565','lovepet24@163.com',771013952565030912,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 00:21:01','2023-01-06 00:21:01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771334626781310976,'ghobft7w租户管理员','18668485565','lovepet24@163.com',771334626173136896,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 21:35:16','2023-01-06 21:35:16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771342305708879872,'rm1db94h租户管理员','18668485565','lovepet24@163.com',771342305205563392,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 22:05:47','2023-01-06 22:05:47',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771353846718017536,'测试管理员01580','18668485565','1528683621@qq.com',771342305205563392,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 22:51:38','2023-01-06 22:51:38',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771354280383885312,'测试管理员01580','18668485565','1528683621@qq.com',771342305205563392,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-06 22:53:22','2023-01-06 22:53:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771358516215689216,'测试管理员01580','18668485565','1528683621@qq.com',771342305205563392,'1','BASIC','1','1',NULL,NULL,NULL,'2023-01-06 23:10:12','2023-02-03 21:40:36',NULL,NULL,'108719251','WxZhongWang',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771358806369251328,'测试管理员01580','18668485565','1528683621@qq.com',771342305205563392,'0','BASIC','1','0',NULL,NULL,NULL,'2023-01-06 23:12:01','2023-01-07 21:55:14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(771360471642157056,'测试管理员01580','18668485565','1528683621@qq.com',771342305205563392,'0','BASIC','1','0',NULL,NULL,NULL,'2023-01-06 23:18:48','2023-01-07 21:54:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(773539579109191680,'fntc8fsu租户管理员','18668485565','lovepet24@163.com',773539578027061248,'1','BASIC','0','0',NULL,NULL,NULL,'2023-01-12 23:36:58','2023-01-12 23:36:58',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(781488231601549312,'测试管理员01580','18668485565','1528683621@qq.com',768460649511661568,'0','BASIC','0','0',NULL,NULL,NULL,'2023-02-03 22:02:04','2024-04-06 13:54:27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(958343681365127168,'袁锦秀',NULL,NULL,768460649511661568,'0','BASIC','0','0',NULL,NULL,NULL,'2024-06-05 22:42:29','2024-06-05 22:42:29',NULL,NULL,NULL,NULL,NULL,NULL,'iPAmKWlHWwHmFmMkYYMSopQiEiE','J7AsIcBPNRsiPmhbd3HKMkwiEiE',NULL,NULL,NULL),(966905791770669056,'钟望',NULL,NULL,768460649511661568,'0','BASIC','0','0',NULL,NULL,NULL,'2024-06-29 13:45:15','2024-06-29 13:45:15',NULL,NULL,NULL,NULL,NULL,NULL,'wSkbaXRa6GFpaN068KBdwgiEiE','3YI9aF8R6IhXSSPGtQiSj4QiEiE',NULL,NULL,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_permission`
--

DROP TABLE IF EXISTS `acl_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acl_permission` (
  `id` bigint NOT NULL,
  `app_code` varchar(255) NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `resource_type` varchar(30) DEFAULT NULL,
  `resource_name` varchar(255) DEFAULT NULL,
  `resource_path` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT '0',
  `modifier` varchar(255) DEFAULT '0',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` char(1) NOT NULL,
  `ref_menu_id` bigint DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_permission`
--

LOCK TABLES `acl_permission` WRITE;
/*!40000 ALTER TABLE `acl_permission` DISABLE KEYS */;
INSERT INTO `acl_permission` VALUES (1,'uic-center',NULL,'action','添加账户','uic:account:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(2,'uic-center',NULL,'action','更新账号','uic:account:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(3,'uic-center',NULL,'action','删除账号','uic:account:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(4,'uic-center',NULL,'action','添加ISV','uic:isv:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(5,'uic-center',NULL,'action','更新ISV','uic:isv:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(6,'uic-center',NULL,'action','删除ISV','uic:isv:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(7,'uic-center',NULL,'action','禁用ISV','uic:isv:disable','0','0','2023-09-29 15:38:28','2023-10-12 21:26:57','0',3,'0'),(8,'uic-center',NULL,'action','启用ISV','uic:isv:enable','0','0','2023-09-29 15:39:32','2023-10-12 21:26:57','0',3,'0'),(9,'uic-center',NULL,'action','添加租户','uic:tenant:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(10,'uic-center',NULL,'action','更新租户','uic:tenant:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(11,'uic-center',NULL,'action','删除租户','uic:tenant:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(12,'uic-center',NULL,'action','禁用租户','uic:tenant:disable','0','0','2023-09-29 15:38:28','2023-10-12 21:26:57','0',2,'0'),(13,'uic-center',NULL,'action','启用租户','uic:tenant:enable','0','0','2023-09-29 15:39:32','2023-10-12 21:26:57','0',2,'0'),(14,'uic-center',NULL,'action','启用账号','uic:account:enable','0','0','2023-09-29 15:42:05','2023-10-12 21:26:57','0',1,'0'),(15,'uic-center',NULL,'action','禁用账号','uic:account:disable','0','0','2023-09-29 15:42:08','2023-10-12 21:26:57','0',1,'0'),(872436577589145600,'ACL_12580',NULL,'action','创建应用','acl:app:add','0','0','2023-10-12 21:18:19','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438277603471360,'ACL_12580',NULL,'action','更新应用','acl:app:update','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278073233408,'ACL_12580',NULL,'action','启用应用','acl:app:enable','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278131953664,'ACL_12580',NULL,'action','禁用应用','acl:app:disable','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278203256832,'ACL_12580',NULL,'action','删除应用','acl:app:delete','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872439245707554816,'ACL_12580',NULL,'action','创建菜单','acl:menu:add','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246215065600,'ACL_12580',NULL,'action','更新菜单','acl:menu:update','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246282174464,'ACL_12580',NULL,'action','启用菜单','acl:menu:enable','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246345089024,'ACL_12580',NULL,'action','禁用菜单','acl:menu:disable','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246412197888,'ACL_12580',NULL,'action','删除菜单','acl:menu:delete','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0');
/*!40000 ALTER TABLE `acl_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_role`
--

DROP TABLE IF EXISTS `acl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acl_role` (
  `id` bigint NOT NULL,
  `app_code` varchar(255) NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_key` varchar(255) NOT NULL,
  `role_desc` varchar(255) NOT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT NULL,
  `parent_role_id` bigint DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_role`
--

LOCK TABLES `acl_role` WRITE;
/*!40000 ALTER TABLE `acl_role` DISABLE KEYS */;
INSERT INTO `acl_role` VALUES (1,'uic-center',NULL,'UIC_APPLICATION','UIC_BASE_ROLE','账号系统管理员','0','0','2023-09-29 15:53:05','2024-06-08 13:57:59','0',NULL,'0'),(2,'uic-center',NULL,'UIC_BASE_ROLE','UIC_BASE_ROLE','账号基础角色','0','0','2023-09-29 15:53:48','2024-06-08 13:57:59','0',NULL,'0'),(3,'CAKE_DEVOPS',NULL,'应用超级管理员','SUPER_ADMINISTRATOR','应用超级管理员','0','0','2024-06-08 13:56:56','2024-06-08 13:56:59','0',NULL,'0'),(4,'ACL_12580',NULL,'应用超级管理员','SUPER_ADMINISTRATOR','应用超级管理员','0','0','2023-10-11 22:23:51','2024-06-08 13:57:59','0',NULL,'0');
/*!40000 ALTER TABLE `acl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint NOT NULL,
  `app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `app_code` varchar(255) NOT NULL,
  `auth_type` varchar(255) NOT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_app_code` (`app_code`) USING BTREE COMMENT '应用编码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'账户中心','uic-center','RBAC0','0','0','2023-08-31 21:45:29','2023-08-31 21:45:29','0','0'),(2,'测试引用','CAKE_DEVOPS','RBAC0','0','0','2023-08-31 22:00:52','2023-08-31 22:00:52','0','0'),(868802601775411200,'ACL中心','ACL_12580','RBAC0','0','0','2023-10-02 20:38:12','2023-10-02 20:38:12','0','0');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `isv`
--

DROP TABLE IF EXISTS `isv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `isv` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `status` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `is_deleted` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `register_ip` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `max_tenants` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `isv`
--

LOCK TABLES `isv` WRITE;
/*!40000 ALTER TABLE `isv` DISABLE KEYS */;
INSERT INTO `isv` VALUES (768060752375459840,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@173.com',NULL,NULL,'2022-12-28 20:46:03','2023-12-15 22:28:50',100),(770985187373887488,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@163.com',NULL,NULL,'2023-01-05 22:26:43','2023-01-05 22:26:43',100),(770992409172652032,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@163.com',NULL,NULL,'2023-01-05 22:55:24','2023-01-05 22:55:24',100),(771013953420668928,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@163.com',NULL,NULL,'2023-01-06 00:21:01','2023-01-06 00:21:01',100),(771015558316568576,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@163.com',NULL,NULL,'2023-01-06 00:27:24','2023-01-06 00:27:24',100),(781482826871746560,'杭州锐尼科技有限公司','www.ranytech.com','hzrany','中国','0','0','127.0.0.1','18668485565','18668485565@163.com',NULL,NULL,'2023-02-03 21:40:35','2023-02-03 21:40:35',100);
/*!40000 ALTER TABLE `isv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_code` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `parent_id` bigint NOT NULL DEFAULT '0',
  `level` int NOT NULL,
  `icon` varchar(512) COLLATE utf8mb3_bin DEFAULT NULL,
  `hidden` char(1) COLLATE utf8mb3_bin NOT NULL,
  `creator` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifier` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` char(1) COLLATE utf8mb3_bin NOT NULL DEFAULT '0',
  `sort` int NOT NULL,
  `status` char(1) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=872080737774481409 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'uic-center',NULL,'账号管理','/uic-center/account/list',0,1,NULL,'0','0','0','2023-09-29 15:31:43','2023-09-29 15:31:45','0',2,'0'),(2,'uic-center',NULL,'租户管理','/uic-center/tenant/list',0,1,NULL,'0','0','0','2023-09-29 15:33:58','2023-09-29 15:34:01','0',1,'0'),(3,'uic-center',NULL,'ISV管理','/uic-center/isv/list',0,1,NULL,'0','0','0','2023-09-29 15:34:37','2023-09-29 15:34:39','0',0,'0'),(4,'CAKE_DEVOPS',NULL,'应用中心','/apps',0,1,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',1,'0'),(5,'CAKE_DEVOPS',NULL,'运维中心','/ops',0,1,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(6,'CAKE_DEVOPS',NULL,'集群管理','/ops/cluster',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',3,'0'),(7,'CAKE_DEVOPS',NULL,'主机管理','/ops/host',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',1,'0'),(8,'CAKE_DEVOPS',NULL,'主机密钥','/ops/server-key',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(9,'CAKE_DEVOPS',NULL,'主机代理','/ops/server-proxy',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',3,'0'),(10,'CAKE_DEVOPS',NULL,'主机环境变量','/ops/server-env',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',4,'0'),(11,'CAKE_DEVOPS',NULL,'主机监控','/ops/server-monitor',5,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',5,'0'),(12,'CAKE_DEVOPS',NULL,'系统设置','/system',0,1,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',6,'0'),(13,'CAKE_DEVOPS',NULL,'Webhook','/system/webhook-config',12,2,NULL,'0','0','0','2024-06-08 13:54:34','2024-06-08 13:54:45','0',7,'0'),(14,'CAKE_DEVOPS',NULL,'告警组','/system/alarm-group',12,2,NULL,'0','0','0','2023-10-11 21:44:21','2024-06-08 13:54:43','0',8,'0'),(15,'CAKE_DEVOPS',NULL,'脚本管理','/system/script-template',12,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',9,'0'),(16,'CAKE_DEVOPS',NULL,'系统日志','/system/system-log',12,2,NULL,'0','0','0','2023-10-11 21:44:21','2023-10-11 21:44:21','0',10,'0'),(872080735849295872,'ACL_12580',NULL,'应用管理','/acl/app/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:20','2023-10-11 21:44:20','0',1,'0'),(872080737707372544,'ACL_12580',NULL,'菜单管理','/acl/menu/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(872080737736732672,'ACL_12580',NULL,'角色管理','/acl/role/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(872080737774481408,'ACL_12580',NULL,'页面权限管理','/acl/permission/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `id` bigint NOT NULL,
  `app_code` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  `is_deleted` char(1) COLLATE utf8mb3_bin NOT NULL,
  `creator` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `modifier` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='角色菜单绑定关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,'uic-center',NULL,1,1,'0','0','0','2023-09-29 15:51:56','2023-09-29 15:51:57'),(3,'CAKE_DEVOPS',NULL,3,4,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(4,'CAKE_DEVOPS',NULL,3,5,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(5,'CAKE_DEVOPS',NULL,3,6,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(6,'CAKE_DEVOPS',NULL,3,7,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(7,'CAKE_DEVOPS',NULL,3,8,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(8,'CAKE_DEVOPS',NULL,3,9,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(9,'CAKE_DEVOPS',NULL,3,10,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(10,'CAKE_DEVOPS',NULL,3,11,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(11,'CAKE_DEVOPS',NULL,3,12,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(12,'CAKE_DEVOPS',NULL,3,13,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(13,'CAKE_DEVOPS',NULL,3,14,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(14,'CAKE_DEVOPS',NULL,3,15,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26'),(15,'CAKE_DEVOPS',NULL,3,16,'0','0','0','2024-06-08 15:40:26','2024-06-08 15:40:26');
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL,
  `app_code` varchar(255) DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_deleted` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,'uic-center',NULL,1,1,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(2,'uic-center',NULL,1,2,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(3,'uic-center',NULL,1,3,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(4,'uic-center',NULL,1,4,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(5,'uic-center',NULL,1,5,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(6,'uic-center',NULL,1,6,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(7,'uic-center',NULL,1,7,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(8,'uic-center',NULL,1,8,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(9,'uic-center',NULL,1,9,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(10,'uic-center',NULL,1,10,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(11,'uic-center',NULL,1,11,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(12,'uic-center',NULL,1,12,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(13,'uic-center',NULL,1,13,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(14,'uic-center',NULL,1,14,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0'),(15,'uic-center',NULL,1,15,'0','0','2023-09-29 15:55:08','2023-09-29 15:55:09','0');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `safe_strategy`
--

DROP TABLE IF EXISTS `safe_strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `safe_strategy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint NOT NULL,
  `login_strategy` char(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `auth_code` varchar(255) NOT NULL,
  `auth_value` varchar(255) DEFAULT NULL,
  `block_at` datetime DEFAULT NULL,
  `expired_at` datetime DEFAULT NULL,
  `is_deleted` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `safe_strategy`
--

LOCK TABLES `safe_strategy` WRITE;
/*!40000 ALTER TABLE `safe_strategy` DISABLE KEYS */;
INSERT INTO `safe_strategy` VALUES (1,768460662077796352,'BASIC_AUTH','admin@hzrany','8cb88ef71b3cab359c5adb2d467518bb',NULL,NULL,'0',NULL,NULL,'2022-12-29 23:15:10','2022-12-29 23:15:10'),(2,768821270497341440,'BASIC_AUTH','admin@ybzkwov5','84349aa97f5483f230c7a677b809421f',NULL,NULL,'0',NULL,NULL,'2022-12-30 23:08:05','2022-12-30 23:08:05'),(3,768821468053254144,'BASIC_AUTH','admin@7tqss9cf','af1671277dbe97b991dbb1e5704bdf10',NULL,NULL,'0',NULL,NULL,'2022-12-30 23:08:52','2022-12-30 23:08:52'),(4,768821902998384640,'BASIC_AUTH','admin@qgfuq6o5','16780ff2f822cac1f507c0f11afb55e0',NULL,NULL,'0',NULL,NULL,'2022-12-30 23:10:36','2022-12-30 23:10:36'),(5,768822866530676736,'BASIC_AUTH','admin@716wgau9','9ace3748bd8314780825d396635807d0',NULL,NULL,'0',NULL,NULL,'2022-12-30 23:14:26','2022-12-30 23:14:26'),(6,769008922186100736,'BASIC_AUTH','admin@lrt7rvn4','8a3210289423250d22a95a215cbeccb2',NULL,NULL,'0',NULL,NULL,'2022-12-31 11:33:46','2022-12-31 11:33:46'),(7,769013248895889408,'BASIC_AUTH','admin@sn5xnp5n','bc7dc6dab8d2ce24684e28908ee68f2a',NULL,NULL,'0',NULL,NULL,'2022-12-31 11:50:56','2022-12-31 11:50:56'),(8,769013577418944512,'BASIC_AUTH','admin@bgxg6ygi','07cc2a5f089ac960bb3dbce5acd66fd',NULL,NULL,'0',NULL,NULL,'2022-12-31 11:52:15','2022-12-31 11:52:15'),(9,769013727499530240,'BASIC_AUTH','admin@bsv5f7v6','35fd9343de0e96012479018a3bfe1b0f',NULL,NULL,'0',NULL,NULL,'2022-12-31 11:52:50','2022-12-31 11:52:50'),(10,769106556057956352,'BASIC_AUTH','admin@w5750nvx','9f0e400d67b75f8f6a86d98deb26aa02',NULL,NULL,'0',NULL,NULL,'2022-12-31 18:01:43','2022-12-31 18:01:43'),(11,771009241870905344,'BASIC_AUTH','admin@94xn9bt6','1b1f59ab97a103b807e2f47156c296a6',NULL,NULL,'0',NULL,NULL,'2023-01-06 00:02:25','2023-01-06 00:02:25'),(12,771013953005432832,'BASIC_AUTH','admin@rp7uzch2','fd9c14d3a12b523ff54a652d5836d3c3',NULL,NULL,'0',NULL,NULL,'2023-01-06 00:21:01','2023-01-06 00:21:01'),(13,771334626781310976,'BASIC_AUTH','admin@ghobft7w','57ea3b0a963e68f2fc8b2c1e5972457c',NULL,NULL,'0',NULL,NULL,'2023-01-06 21:35:16','2023-01-06 21:35:16'),(14,771342305708879872,'BASIC_AUTH','admin@rm1db94h','9e785a6f77a9ebbf749bbae51eab612e',NULL,NULL,'0',NULL,NULL,'2023-01-06 22:05:47','2023-01-06 22:05:47'),(15,771360471642157056,'BASIC_AUTH','test@rm1db94h','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'0',NULL,NULL,'2023-01-06 23:18:48','2023-01-06 23:18:48'),(16,771358516215689216,'LDAP','1528683821@qq.com','test123123','2023-01-15 00:00:00','2023-01-10 00:00:00','0',NULL,NULL,'2023-01-08 16:51:44','2023-01-08 16:51:44'),(17,773539579109191680,'BASIC_AUTH','admin@fntc8fsu','14422d8a0bd460dfe5aa4213b58d8256',NULL,NULL,'0',NULL,NULL,'2023-01-12 23:36:58','2023-01-12 23:36:58'),(18,771358516215689216,'LDAP','1528683821@qq.com','test123','2023-01-15 00:00:00','2023-01-10 00:00:00','0',NULL,NULL,'2023-02-03 21:40:36','2023-02-03 21:40:36'),(19,781488231601549312,'BASIC_AUTH','test@7tqss9cf','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,'0',NULL,NULL,'2023-02-03 22:02:04','2023-02-03 22:02:04');
/*!40000 ALTER TABLE `safe_strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant` (
  `id` bigint NOT NULL,
  `isv_id` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `short_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `source` varchar(255) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `is_deleted` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address` varchar(255) DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (768460649511661568,768060752375459840,'杭州锐尼科技有限公司01','hzrany','lovepet24@163.com','dev','13554353449','0',NULL,NULL,'2022-12-29 23:15:06','2023-08-18 02:30:24','浙江省杭州市西湖区','0'),(768821269968859136,768060752375459840,'杭州锐尼科技有限公司02','ybzkwov5','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-30 23:08:05','2022-12-31 16:26:21',NULL,'0'),(768821467507994624,768060752375459840,'杭州锐尼科技有限公司','7tqss9cf','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-30 23:08:52','2022-12-30 23:08:52',NULL,'0'),(768821902381821952,768060752375459840,'h225nzw9ud6rlv6','qgfuq6o5','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-30 23:10:35','2022-12-30 23:10:35',NULL,'0'),(768822865951862784,768060752375459840,'x03q54d3o2n4hj7','716wgau9','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-30 23:14:25','2022-12-30 23:14:25',NULL,'0'),(769008916825780224,768060752375459840,'d2tgx4g0tcz4yr9','lrt7rvn4','lovepet24@163.com','dev','13554353449','0',NULL,NULL,'2022-12-31 11:33:43','2023-02-03 21:40:36','浙江省杭州市西湖区','0'),(769013248086388736,768060752375459840,'btoy4sinsokr4lq','sn5xnp5n','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-31 11:50:56','2022-12-31 11:50:56',NULL,'0'),(769013576844324864,768060752375459840,'c513v329n3fshdo','bgxg6ygi','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-31 11:52:14','2022-12-31 11:52:14',NULL,'0'),(769013726908133376,768060752375459840,'4cimkw7e2dq8jso','bsv5f7v6','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-31 11:52:50','2022-12-31 11:52:50',NULL,'0'),(769106555151986688,768060752375459840,'i6wj992r8htyatn','w5750nvx','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2022-12-31 18:01:42','2022-12-31 18:01:42',NULL,'0'),(771009195330908160,768060752375459840,'3cpusnxy1fgcvbc','94xn9bt6','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2023-01-06 00:02:14','2023-01-06 00:02:14',NULL,'0'),(771013952565030912,768060752375459840,'wdwb5qv3cw77f72','rp7uzch2','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2023-01-06 00:21:01','2023-01-06 00:21:01',NULL,'0'),(771334626173136896,768060752375459840,'h2la2jn7db7qxw3','ghobft7w','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2023-01-06 21:35:15','2023-01-06 21:35:15',NULL,'0'),(771342305205563392,768060752375459840,'u1jkpe0i71zxlp0','rm1db94h','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2023-01-06 22:05:46','2023-01-06 22:05:46',NULL,'0'),(773539578027061248,768060752375459840,'6v93rad0htge6u8','fntc8fsu','lovepet24@163.com','dev','18668485565','0',NULL,NULL,'2023-01-12 23:36:57','2023-01-12 23:36:57',NULL,'0');
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL,
  `app_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `modifier` varchar(255) DEFAULT '0' /*!80023 INVISIBLE */,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` char(1) NOT NULL DEFAULT '0',
  `status` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `app_code`, `tenant_id`, `user_id`, `role_id`, `creator`, `modifier`, `gmt_create`, `gmt_modified`, `is_deleted`, `status`) VALUES (1,'CAKE_DEVOPS',NULL,768460662077796352,3,'0','0','2024-06-08 14:00:45','2024-01-01 00:00:00','0','0');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-25  7:50:38
