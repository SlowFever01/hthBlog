-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: hth_blog
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `hth_article`
--

DROP TABLE IF EXISTS `hth_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_article`
--

LOCK TABLES `hth_article` WRITE;
/*!40000 ALTER TABLE `hth_article` DISABLE KEYS */;
INSERT INTO `hth_article` VALUES (1,'Docker实用篇','![docker](https://images.lusongsong.com/down/zb_users/upload/2018/05/201805263588_499.jpg)\n\n\n\n# Docker和虚拟机的差异：\n\n- docker是一个系统进程；虚拟机是在操作系统中的操作系统\n\n- docker体积小、启动速度快、性能好；虚拟机体积大、启动速度慢、性能一般\n# Docker架构\n## 镜像和容器\n### Docker中有几个重要的概念：\n\n- 镜像（Image）：Docker将应用程序及其所需的依赖、函数库、环境、配置等文件打包在一起，称为镜像。\n\n- 容器（Container）：镜像中的应用程序运行后形成的进程就是容器，只是Docker会给容器进程做隔离，对外不可见。\n\n一切应用最终都是代码组成，都是硬盘中的一个个的字节形成的文件。只有运行时，才会加载到内存，形成进程。\n\n而镜像，就是把一个应用在硬盘上的文件、及其运行环境、部分系统函数库文件一起打包形成的文件包。这个文件包是只读的。\n\n容器呢，就是将这些文件中编写的程序、函数加载到内存中允许，形成进程，只不过要隔离起来。因此一个镜像可以启动多次，形成多个容器进程。\n\n','微服务虽然具备各种各样的优势，但服务的拆分通用给部署带来了很大的麻烦。\n\n分布式系统中，依赖的组件非常多，不同组件之间部署时往往会产生一些冲突。\n在数百上千台服务中重复部署，环境不一定一致，会遇到各种问题',17,'http://sa19ynfc3.hd-bkt.clouddn.com/2024/03/20/f8867125f28244edaec65b8ba4b7c4d5.png','1','0',105,'0',NULL,'2022-01-23 23:20:11',NULL,'2024-03-20 16:21:19',0),(2,'weq','adadaeqe','adad',2,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/fd2e9460c58a4af3bbeae5d9ed581688.png','1','0',22,'0',NULL,'2022-01-21 14:58:30',NULL,NULL,1),(3,'dad','asdasda','sadad',1,'https://sg-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/737a0ed0b8ea430d8700a12e76aa1cd1.png','1','0',33,'0',NULL,'2022-01-18 14:58:34',NULL,NULL,1),(5,'MySQL知识点','![mysql.png](https://img1.baidu.com/it/u=536055084,2536565005&fm=253&fmt=auto&app=138&f=JPEG?w=950&h=475)\n\n**MySQL中，如何定位慢查询?**\n\n- 开启慢查询日志：在MySQL的配置文件中（通常是my.cnf或my.ini），启用慢查询日志（slow query log）。将slow_query_log设置为ON，并指定slow_query_log_file指向日志文件的路径。通过设置long_query_time参数来定义慢查询的阈值（以秒为单位），超过此阈值的查询将被记录在慢查询日志中。\n\n- 查看慢查询日志：通过查看慢查询日志文件，你可以找到执行时间超过阈值的查询语句。你可以使用文本编辑器、less、tail等工具来查看日志文件。\n\n- 使用MySQL的性能分析工具：MySQL提供了一些内置的工具来分析查询性能。其中包括EXPLAIN语句和SHOW PROFILE语句。使用EXPLAIN语句可以查看查询的执行计划，而SHOW PROFILE语句可以查看查询执行时各个阶段的性能数据。\n\n- 使用MySQL性能分析工具：MySQL还提供了一些性能分析工具，如mysqlsla、mysqldumpslow等，可以帮助你分析慢查询日志，找出执行时间最长的查询，以及最频繁的查询等。\n\n- 使用第三方监控工具：除了MySQL自带的工具外，还有许多第三方监控工具可以帮助你监控MySQL的性能并定位慢查询，如Percona Toolkit、MySQL Enterprise Monitor等。\n\n\n**SQL语句执行很慢, 如何分析呢**\n\n- 执行计划分析：使用数据库管理系统提供的执行计划分析工具（如MySQL的EXPLAIN语句），查看SQL查询的执行计划。执行计划会显示数据库系统执行查询时所采取的操作，如表扫描、索引使用情况等。通过分析执行计划，可以确定查询是否有效利用了索引，以及是否存在可能导致性能下降的操作。\n\n- 索引分析：检查查询涉及的表是否有适当的索引。缺少必要的索引或者索引设计不合理可能导致查询性能下降。可以使用数据库管理系统提供的索引分析工具（如MySQL的SHOW INDEX语句）来查看表的索引情况，并根据需要添加或调整索引。\n\n- 查询语句优化：审查SQL查询语句本身，查看是否存在可以优化的地方。例如，可以考虑优化WHERE子句、减少不必要的列、使用更有效的连接方式等。有时候，简单的调整查询语句就可以显著提高性能。\n\n',NULL,16,'','1','0',44,'0',NULL,'2022-01-17 14:58:37',NULL,'2024-03-20 16:21:31',0),(8,'Java集合','**为什么要进行复杂度分析**\n\n- 性能评估：复杂度分析可以帮助我们评估算法在不同输入规模下的性能表现。通过分析算法的时间复杂度和空间复杂度，我们可以预测算法在大规模数据集上的运行时间和内存占用情况，从而选择最适合特定场景的算法。\n\n- 比较算法：复杂度分析提供了一种客观的方式来比较不同算法之间的性能。通过比较算法的复杂度，我们可以确定哪个算法在特定情况下更有效率。例如，在排序算法中，我们可以通过比较它们的时间复杂度来选择最适合特定数据集的排序算法。\n\n- 优化算法：通过分析算法的复杂度，我们可以识别出性能瓶颈，并尝试优化算法以提高其效率。在设计和实现算法时，复杂度分析可以指导我们选择更有效率的算法策略，并在必要时进行算法优化。\n\n- 预测算法行为：复杂度分析可以帮助我们预测算法在不同情况下的行为。虽然它不能完全反映实际执行的精确时间或空间消耗，但它可以提供一个基本的理论参考，帮助我们理解算法的运行规律和趋势。\n\n- 优化资源利用：在计算资源有限的情况下，复杂度分析可以帮助我们优化资源利用，使得算法在给定的资源限制下能够处理更大规模的问题。\n\n\n\n**ArrayList源码分析**\n\n- 底层数据结构\n\nArrayList底层是用动态的数组实现的\n\n- 初始容量\n\nArrayList初始容量为0，当第一次添加数据的时候才会初始化容量为10\n\n- 扩容逻辑\n\nArrayList在进行扩容的时候是原来容量的1.5倍，每次扩容都需要拷贝数组\n\n- 添加逻辑\n\n  - 确保数组已使用长度（size）加1之后足够存下下一个数据 \n  - 计算数组的容量，如果当前数组已使用长度+1后的大于当前的数组长度，则调用grow方法扩容（原来的1.5倍）\n\n  - 确保新增的数据有地方存储之后，则将新元素添加到位于size的位置上。\n  - 返回添加成功布尔值。','',1,'','1','0',0,'0',14787164048663,'2024-03-20 11:17:55',NULL,'2024-03-20 16:21:49',0);
/*!40000 ALTER TABLE `hth_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hth_article_tag`
--

DROP TABLE IF EXISTS `hth_article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_article_tag` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_article_tag`
--

LOCK TABLES `hth_article_tag` WRITE;
/*!40000 ALTER TABLE `hth_article_tag` DISABLE KEYS */;
INSERT INTO `hth_article_tag` VALUES (1,5),(2,1),(2,4),(3,4),(3,5),(5,6),(8,4),(8,7);
/*!40000 ALTER TABLE `hth_article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hth_category`
--

DROP TABLE IF EXISTS `hth_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名',
  `pid` bigint DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_category`
--

LOCK TABLES `hth_category` WRITE;
/*!40000 ALTER TABLE `hth_category` DISABLE KEYS */;
INSERT INTO `hth_category` VALUES (1,'java',-1,'wsd','0',NULL,NULL,NULL,NULL,0),(2,'PHP',-1,'wsd','0',NULL,NULL,NULL,NULL,0),(15,'list集合',-1,NULL,'0',14787164048663,'2024-03-20 16:19:54',14787164048663,'2024-03-20 16:19:54',0),(16,'mysql',-1,NULL,'0',14787164048663,'2024-03-20 16:20:06',14787164048663,'2024-03-20 16:20:06',0),(17,'docker',-1,NULL,'0',14787164048663,'2024-03-20 16:20:14',14787164048663,'2024-03-20 16:20:14',0);
/*!40000 ALTER TABLE `hth_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hth_comment`
--

DROP TABLE IF EXISTS `hth_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `root_id` bigint DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_comment`
--

LOCK TABLES `hth_comment` WRITE;
/*!40000 ALTER TABLE `hth_comment` DISABLE KEYS */;
INSERT INTO `hth_comment` VALUES (35,'1',1,-1,'[挤眼]',-1,-1,1,'2024-03-22 10:17:00',1,'2024-03-22 10:17:00',0),(36,'1',1,35,'[嘻嘻]',1,35,1,'2024-03-22 10:17:07',1,'2024-03-22 10:17:07',0),(37,'0',1,-1,'first common\n',-1,-1,1,'2024-03-22 10:17:22',1,'2024-03-22 10:17:22',0),(38,'0',1,37,'child common',1,37,1,'2024-03-22 10:17:36',1,'2024-03-22 10:17:36',0);
/*!40000 ALTER TABLE `hth_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hth_link`
--

DROP TABLE IF EXISTS `hth_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
  `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_link`
--

LOCK TABLES `hth_link` WRITE;
/*!40000 ALTER TABLE `hth_link` DISABLE KEYS */;
INSERT INTO `hth_link` VALUES (1,'我的博客','https://pic3.zhimg.com/v2-7827197bfa0023932d428633d41372fd_250x0.jpg?source=172ae18b','','https://www.SlowFever01.top','0',NULL,'2022-01-13 08:25:47',NULL,'2024-03-20 15:52:30',0),(2,'我的GitHub','https://tx-free-imgs.acfun.cn/o_1egkgq58a6cl1occ1sk0s1d1qet0.jpeg?imageslim','','https://github.com/SlowFever01/SlowFever01.github.io.git','0',NULL,'2022-01-13 09:06:10',NULL,'2024-03-20 15:54:43',0),(3,'百度','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975','baidu','https://www.baidu.com','0',NULL,'2022-01-13 09:23:01',NULL,'2024-03-22 11:21:18',0);
/*!40000 ALTER TABLE `hth_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hth_tag`
--

DROP TABLE IF EXISTS `hth_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hth_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hth_tag`
--

LOCK TABLES `hth_tag` WRITE;
/*!40000 ALTER TABLE `hth_tag` DISABLE KEYS */;
INSERT INTO `hth_tag` VALUES (1,'Mybatis',NULL,NULL,NULL,'2024-03-20 16:20:41',0,'mybatis'),(2,'asdas',NULL,'2022-01-11 09:20:55',NULL,'2022-01-11 09:20:55',1,'weqw'),(3,'weqw',NULL,'2022-01-11 09:21:07',NULL,'2022-01-11 09:21:07',1,'qweqwe'),(4,'Java',NULL,'2022-01-13 15:22:43',NULL,'2024-03-20 16:20:47',0,'java'),(5,'docker',NULL,'2022-01-13 15:22:47',NULL,'2024-03-20 16:20:30',0,''),(6,'mysql',14787164048663,'2024-03-20 16:20:56',14787164048663,'2024-03-20 16:20:56',0,NULL),(7,'集合',14787164048663,'2024-03-20 16:21:03',14787164048663,'2024-03-20 16:21:03',0,NULL);
/*!40000 ALTER TABLE `hth_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2034 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,1,'M','0','0','','system',0,'2021-11-12 10:46:19',0,NULL,'系统管理目录','0'),(100,'用户管理',1,1,'user','system/user/index',1,'C','0','0','system:user:list','user',0,'2021-11-12 10:46:19',1,'2022-07-31 15:47:58','用户管理菜单','0'),(101,'角色管理',1,2,'role','system/role/index',1,'C','0','0','system:role:list','peoples',0,'2021-11-12 10:46:19',0,NULL,'角色管理菜单','0'),(102,'菜单管理',1,3,'menu','system/menu/index',1,'C','0','0','system:menu:list','tree-table',0,'2021-11-12 10:46:19',0,NULL,'菜单管理菜单','0'),(1001,'用户查询',100,1,'','',1,'F','0','0','system:user:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1002,'用户新增',100,2,'','',1,'F','0','0','system:user:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1003,'用户修改',100,3,'','',1,'F','0','0','system:user:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1004,'用户删除',100,4,'','',1,'F','0','0','system:user:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1005,'用户导出',100,5,'','',1,'F','0','0','system:user:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1006,'用户导入',100,6,'','',1,'F','0','0','system:user:import','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1007,'重置密码',100,7,'','',1,'F','0','0','system:user:resetPwd','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1008,'角色查询',101,1,'','',1,'F','0','0','system:role:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1009,'角色新增',101,2,'','',1,'F','0','0','system:role:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1010,'角色修改',101,3,'','',1,'F','0','0','system:role:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1011,'角色删除',101,4,'','',1,'F','0','0','system:role:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1012,'角色导出',101,5,'','',1,'F','0','0','system:role:export','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1013,'菜单查询',102,1,'','',1,'F','0','0','system:menu:query','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1014,'菜单新增',102,2,'','',1,'F','0','0','system:menu:add','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1015,'菜单修改',102,3,'','',1,'F','0','0','system:menu:edit','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(1016,'菜单删除',102,4,'','',1,'F','0','0','system:menu:remove','#',0,'2021-11-12 10:46:19',0,NULL,'','0'),(2017,'内容管理',0,4,'content',NULL,1,'M','0','0',NULL,'table',NULL,'2022-01-08 02:44:38',1,'2022-07-31 12:34:23','','0'),(2018,'分类管理',2017,1,'category','content/category/index',1,'C','0','0','content:category:list','example',NULL,'2022-01-08 02:51:45',NULL,'2022-01-08 02:51:45','','0'),(2019,'文章管理',2017,0,'article','content/article/index',1,'C','0','0','content:article:list','build',NULL,'2022-01-08 02:53:10',NULL,'2022-01-08 02:53:10','','0'),(2021,'标签管理',2017,6,'tag','content/tag/index',1,'C','0','0','content:tag:index','button',NULL,'2022-01-08 02:55:37',NULL,'2022-01-08 02:55:50','','0'),(2022,'友链管理',2017,4,'link','content/link/index',1,'C','0','0','content:link:list','404',NULL,'2022-01-08 02:56:50',NULL,'2022-01-08 02:56:50','','0'),(2023,'写博文',0,0,'write','content/article/write/index',1,'C','0','0','content:article:writer','build',NULL,'2022-01-08 03:39:58',1,'2022-07-31 22:07:05','','0'),(2024,'友链新增',2022,0,'',NULL,1,'F','0','0','content:link:add','#',NULL,'2022-01-16 07:59:17',NULL,'2022-01-16 07:59:17','','0'),(2025,'友链修改',2022,1,'',NULL,1,'F','0','0','content:link:edit','#',NULL,'2022-01-16 07:59:44',NULL,'2022-01-16 07:59:44','','0'),(2026,'友链删除',2022,1,'',NULL,1,'F','0','0','content:link:remove','#',NULL,'2022-01-16 08:00:05',NULL,'2022-01-16 08:00:05','','0'),(2027,'友链查询',2022,2,'',NULL,1,'F','0','0','content:link:query','#',NULL,'2022-01-16 08:04:09',NULL,'2022-01-16 08:04:09','','0'),(2028,'导出分类',2018,1,'',NULL,1,'F','0','0','content:category:export','#',NULL,'2022-01-21 07:06:59',NULL,'2022-01-21 07:06:59','','0');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','system',1,'0','0',0,'2021-11-12 10:46:19',0,'2024-03-22 10:28:04','超级管理员'),(2,'普通角色','common',2,'0','0',0,'2021-11-12 10:46:19',0,'2022-01-01 22:32:58','普通角色'),(11,'其他','other',5,'0','0',NULL,'2022-01-06 14:07:40',NULL,'2024-03-20 16:17:31','other'),(12,'友链审核员','link',1,'0','0',NULL,'2022-01-16 06:49:30',NULL,'2024-03-20 11:13:17',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (0,0),(1,1),(1,100),(1,101),(1,102),(1,1001),(1,1002),(1,1003),(1,1004),(1,1005),(1,1006),(1,1007),(1,1008),(1,1009),(1,1010),(1,1011),(1,1012),(1,1013),(1,1014),(1,1015),(1,1016),(1,2017),(1,2018),(1,2019),(1,2021),(1,2022),(1,2023),(1,2024),(1,2025),(1,2026),(1,2027),(1,2028),(2,1),(2,102),(2,1013),(2,1014),(2,1015),(2,1016),(2,2000),(3,2),(3,3),(3,4),(3,100),(3,101),(3,103),(3,104),(3,105),(3,106),(3,107),(3,108),(3,109),(3,110),(3,111),(3,112),(3,113),(3,114),(3,115),(3,116),(3,500),(3,501),(3,1001),(3,1002),(3,1003),(3,1004),(3,1005),(3,1006),(3,1007),(3,1008),(3,1009),(3,1010),(3,1011),(3,1012),(3,1017),(3,1018),(3,1019),(3,1020),(3,1021),(3,1022),(3,1023),(3,1024),(3,1025),(3,1026),(3,1027),(3,1028),(3,1029),(3,1030),(3,1031),(3,1032),(3,1033),(3,1034),(3,1035),(3,1036),(3,1037),(3,1038),(3,1039),(3,1040),(3,1041),(3,1042),(3,1043),(3,1044),(3,1045),(3,1046),(3,1047),(3,1048),(3,1049),(3,1050),(3,1051),(3,1052),(3,1053),(3,1054),(3,1055),(3,1056),(3,1057),(3,1058),(3,1059),(3,1060),(3,2000),(11,1),(11,100),(11,101),(11,102),(11,1001),(11,1002),(11,1003),(11,1004),(11,1005),(11,1006),(11,1007),(11,1008),(11,1009),(11,1010),(11,1011),(11,1012),(11,1013),(11,1014),(11,1015),(11,1016),(12,2017),(12,2022),(12,2024),(12,2025),(12,2026),(12,2027);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14787164048667 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'root','systemAdmin','$2a$10$Ix23.XBKEu9BaTIBC8Z3EOUMGc1hwIHan.EHebCqKXb/..UuNHEbG','1','0',NULL,NULL,'0',NULL,NULL,'2024-03-22 10:25:35',NULL,'2024-03-22 10:25:35',0),(2,'hth','hth','$2a$10$MCA.nAOy33O4w1M/MRd0nu8rrDaOBoKP9suR2OCThTsydUKwWElKW','0','0',NULL,NULL,'0',NULL,1,'2024-03-20 11:12:46',1,'2024-03-20 11:12:46',0),(3,'link','友链管理员','$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O','0','0',NULL,NULL,'0',NULL,NULL,'2022-01-05 13:28:43',NULL,'2024-03-20 16:15:57',0),(4,'common','普通角色','$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he','0','0','23412332@qq.com','19098790742','0',NULL,NULL,NULL,NULL,'2024-03-20 16:16:15',0),(14787164048666,'admin','admin','$2a$10$kbfz2DYbmg3CU07Ntn9/iOmCDPVe.I2mibSxESiJYt0m3IMQTYEi6','0','0',NULL,NULL,'0',NULL,1,'2024-03-22 10:33:12',1,'2024-03-22 10:33:12',0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(3,12),(4,2),(5,1),(14787164048666,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-22 12:39:07
