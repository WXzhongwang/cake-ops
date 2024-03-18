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
INSERT INTO `application` VALUES (1,'账户中心','uic-center','RBAC0','0','0','2023-08-31 21:45:29','2023-08-31 21:45:29','0','0'),(2,'测试引用','test-application','RBAC0','0','0','2023-08-31 22:00:52','2023-08-31 22:00:52','0','0'),(868802601775411200,'测试APP','ACL_12580','RBAC0','0','0','2023-10-02 20:38:12','2023-10-02 20:38:12','0','0');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
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
INSERT INTO `menu` VALUES (1,'uic-center',NULL,'账号管理','/uic-center/account/list',0,1,NULL,'0','0','0','2023-09-29 15:31:43','2023-09-29 15:31:45','0',2,NULL),(2,'uic-center',NULL,'租户管理','/uic-center/tenant/list',0,1,NULL,'0','0','0','2023-09-29 15:33:58','2023-09-29 15:34:01','0',1,NULL),(3,'uic-center',NULL,'ISV管理','/uic-center/isv/list',0,1,NULL,'0','0','0','2023-09-29 15:34:37','2023-09-29 15:34:39','0',0,NULL),(872080735849295872,'ACL_12580',NULL,'应用管理','/acl/app/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:20','2023-10-11 21:44:20','0',1,'0'),(872080737707372544,'ACL_12580',NULL,'菜单管理','/acl/menu/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(872080737736732672,'ACL_12580',NULL,'角色管理','/acl/role/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0'),(872080737774481408,'ACL_12580',NULL,'页面权限管理','/acl/permission/list',0,1,'https://avatars.githubusercontent.com/u/27359059?v=4','0',NULL,NULL,'2023-10-11 21:44:21','2023-10-11 21:44:21','0',2,'0');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
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
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'uic-center',NULL,'action','添加账户','uic:account:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(2,'uic-center',NULL,'action','更新账号','uic:account:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(3,'uic-center',NULL,'action','删除账号','uic:account:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',1,'0'),(4,'uic-center',NULL,'action','添加ISV','uic:isv:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(5,'uic-center',NULL,'action','更新ISV','uic:isv:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(6,'uic-center',NULL,'action','删除ISV','uic:isv:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',3,'0'),(7,'uic-center',NULL,'action','禁用ISV','uic:isv:disable','0','0','2023-09-29 15:38:28','2023-10-12 21:26:57','0',3,'0'),(8,'uic-center',NULL,'action','启用ISV','uic:isv:enable','0','0','2023-09-29 15:39:32','2023-10-12 21:26:57','0',3,'0'),(9,'uic-center',NULL,'action','添加租户','uic:tenant:add','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(10,'uic-center',NULL,'action','更新租户','uic:tenant:update','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(11,'uic-center',NULL,'action','删除租户','uic:tenant:delete','0','0','2023-08-31 21:40:52','2023-10-12 21:26:57','0',2,'0'),(12,'uic-center',NULL,'action','禁用租户','uic:tenant:disable','0','0','2023-09-29 15:38:28','2023-10-12 21:26:57','0',2,'0'),(13,'uic-center',NULL,'action','启用租户','uic:tenant:enable','0','0','2023-09-29 15:39:32','2023-10-12 21:26:57','0',2,'0'),(14,'uic-center',NULL,'action','启用账号','uic:account:enable','0','0','2023-09-29 15:42:05','2023-10-12 21:26:57','0',1,'0'),(15,'uic-center',NULL,'action','禁用账号','uic:account:disable','0','0','2023-09-29 15:42:08','2023-10-12 21:26:57','0',1,'0'),(872436577589145600,'ACL_12580',NULL,'action','创建应用','acl:app:add','0','0','2023-10-12 21:18:19','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438277603471360,'ACL_12580',NULL,'action','更新应用','acl:app:update','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278073233408,'ACL_12580',NULL,'action','启用应用','acl:app:enable','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278131953664,'ACL_12580',NULL,'action','禁用应用','acl:app:disable','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872438278203256832,'ACL_12580',NULL,'action','删除应用','acl:app:delete','0','0','2023-10-12 21:25:05','2023-10-12 21:26:57','0',872080735849295872,'0'),(872439245707554816,'ACL_12580',NULL,'action','创建菜单','acl:menu:add','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246215065600,'ACL_12580',NULL,'action','更新菜单','acl:menu:update','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246282174464,'ACL_12580',NULL,'action','启用菜单','acl:menu:enable','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246345089024,'ACL_12580',NULL,'action','禁用菜单','acl:menu:disable','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0'),(872439246412197888,'ACL_12580',NULL,'action','删除菜单','acl:menu:delete','0','0','2023-10-12 21:28:56','2023-10-12 21:28:56','0',872080737707372544,'0');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
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
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'uic-center',NULL,'UIC_APPLICATION','UIC_BASE_ROLE','账号系统管理员','0','0','2023-09-29 15:53:05','2023-10-11 22:25:30','0',NULL,NULL),(2,'uic-center',NULL,'UIC_BASE_ROLE','UIC_BASE_ROLE','账号基础角色','0','0','2023-09-29 15:53:48','2023-10-11 22:25:30','0',NULL,NULL),(872090360875200512,'ACL_12580',NULL,'应用超级管理员','SUPER_ADMINISTRATOR','应用超级管理员',NULL,NULL,'2023-10-11 22:23:51','2023-10-11 22:27:44','0',NULL,'0');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
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
  `gmt_modifier` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='角色菜单绑定关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,'uic-center',NULL,1,1,'0','0','0','2023-09-29 15:51:56','2023-09-29 15:51:57');
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

-- Dump completed on 2024-03-18 22:18:47
