/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.21 : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `crm`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `author` varchar(45) NOT NULL,
  `description` longtext,
  `sales_volume` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `mark` int DEFAULT NULL,
  `picture` longtext,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`book_id`,`name`,`price`,`author`,`description`,`sales_volume`,`score`,`mark`,`picture`) values (1,'第一行代码:Android(第2版)',53.8,'郭霖','本书被Android开发者誉为Android学习经典。',2,4,56,'Android第一行代码.jpg'),(2,'Android编程权威指南(第3版)',101.7,'比尔·菲利普斯 (Bill Phillips)','本书主要以其Android训练营教学课程为基础，融合了几位作者多年的心得体会，是一本完全面向实战的Android编程权 威指南。全书共36章，详细介绍了8个Android应用的开发过程。通过这些精心设计的应用，读者可掌握很多重要的理论知识和开发技巧，获得宝贵的开发经验。',2,5,145,'2018-06-04 19:50:12.jpg'),(3,'Android组件化架构',64.7,'苍王','本书首先介绍Android组件化开发的基础知识，剖析组件化的开发步骤和常见问题，探究组件化编译原理和编译优化措施。其次在项目架构上，介绍如何组织团队来使用组件化开发，并将业务和人力进行解耦。最后深入介绍组件化分发技术及运用，探讨组件化架构的演进及架构的思维',1,4,5,'1529491036859.jpg'),(4,'疯狂Android讲义(第3版)',78.8,'李刚','本书是《疯狂Android讲义》的第3版，本书基于最新的Android 5，并采用了Google推荐的IDE：Android Studio作为开发工具，书中每个案例、每个截图都全面升级到Android 5。',0,4,3,NULL),(5,'高性能Android应用开发',48.9,'道格·西勒斯 (Doug Sillars)','性能问题在很大程度上决定了用户是否会使用一 款App，本书正是Android性能方面的关键性指南。',1,5,23,NULL),(6,'计算机科学丛书：设计模式 可复用面向对象软件的基础',32.3,'Erich Gamma','《设计模式:可复用面向对象软件的基础》是引导读者走出软件设计迷宫的指路明灯，凝聚了软件开发界几十年设计经验的结晶。四位顶尖的面向对象领域专家精心选取了具有价值的设计实践，加以分类整理和命名，并用简洁而易于重用的形式表达出来。',0,4,54,NULL),(7,'Android源码设计模式解析与实战(第2版)',81.9,'何红辉','本书专门介绍Android源代码的设计模式，主要讲解面向对象的六大原则、主流的设计模式以及MVC和MVP模式。',3,5,11,NULL),(8,'Kotlin从零到精通Android开发',56,'欧阳燊','Kotlin从零到精通Android开发》是一部讲解Kotlin语言的入门书籍，从Kotlin语言的基本语法一直讲到如何将其运用于Android开发。由浅入深、从理论到实战，帮助读者快速掌握Kotlin开发技巧。',68,5,24,NULL);

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `number` int NOT NULL,
  `time` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_info` */

insert  into `order_info`(`order_id`,`user_id`,`book_id`,`number`,`time`) values (3,1,1,1,'2018-07-18 01:11:35'),(4,1,1,1,'2018-07-18 01:11:42'),(5,1,2,1,'2018-07-18 01:12:40'),(6,9,7,1,'2018-07-18 01:33:43'),(7,9,7,1,'2018-07-18 01:33:48'),(8,15,2,1,'2018-07-18 18:48:20'),(9,3,3,1,'2018-07-19 09:36:36'),(10,3,5,1,'2018-07-19 09:36:41'),(11,3,7,1,'2018-07-19 09:36:46');

/*Table structure for table `tbl_activity` */

DROP TABLE IF EXISTS `tbl_activity`;

CREATE TABLE `tbl_activity` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` char(10) DEFAULT NULL,
  `endDate` char(10) DEFAULT NULL,
  `cost` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_activity` */

insert  into `tbl_activity`(`id`,`owner`,`name`,`startDate`,`endDate`,`cost`,`description`,`createTime`,`createBy`,`editTime`,`editBy`) values ('0434be3fd9504977b56caeb55eee2a22','06f5fc056eac41558a964f96daa7f27c','test活动','2021-01-04','2021-01-05','10000','描述123','2021-01-03 21:45:53','张三',NULL,NULL),('0db857a8afa0465ebf996465cb2c336b','40f6cdea0bd34aceb77492a1656d9fb3','发传单1','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:03:52','张三','2020-12-16 10:59:50','张三'),('1237635b2711470b9320dc730ba777e4','40f6cdea0bd34aceb77492a1656d9fb3','发传单2','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:03:44','张三','2020-12-16 10:59:50','张三'),('248eca85384d46c980bb2ffaa8b68fc6','40f6cdea0bd34aceb77492a1656d9fb3','发传单3','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:03:39','张三','2020-12-16 10:59:50','张三'),('58fde2f202fb4ed387434ec187a9374c','40f6cdea0bd34aceb77492a1656d9fb3','发传单4','2020-12-14','2020-12-17','2000','已经修改了','2020-12-14 20:20:10','张三','2020-12-16 10:59:50','张三'),('64e21b80221e468bbd1a54589a98527e','40f6cdea0bd34aceb77492a1656d9fb3','发传单5','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:04:04','张三','2020-12-16 10:59:50','张三'),('6cb81a94ad5f4b2f850f9ffa19e012ac','40f6cdea0bd34aceb77492a1656d9fb3','发传单6','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 13:36:09','张三','2020-12-16 10:59:50','张三'),('7cc69938abd048ce99f9e3eb37cb79fd','40f6cdea0bd34aceb77492a1656d9fb3','发传单7','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:04:08','张三','2020-12-16 10:59:50','张三'),('9b7f1afd9a3742dd84ee484d39979a6c','40f6cdea0bd34aceb77492a1656d9fb3','推广222','2020-12-14','2020-12-17','1000','123','2020-12-16 11:34:31','张三','2020-12-16 11:35:13','张三'),('a6b5c788e9da4197a6bdb1da8c479d83','40f6cdea0bd34aceb77492a1656d9fb3','发传单8','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:03:47','张三','2020-12-16 10:59:50','张三'),('c18f51e271b746c18296d8e205a1a3f7','40f6cdea0bd34aceb77492a1656d9fb3','发传单9','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:03:35','张三','2020-12-16 10:59:50','张三'),('c7707463fc4f4dc4ac536aa057841215','40f6cdea0bd34aceb77492a1656d9fb3','发传单aaaa','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:04:11','张三','2020-12-16 11:13:11','张三'),('cd014d8a889144579ee890a630e1d3a3','40f6cdea0bd34aceb77492a1656d9fb3','发传单11','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 13:36:29','张三','2020-12-16 10:59:50','张三'),('dc26f751d41d4190ab61bf5b00218b4f','40f6cdea0bd34aceb77492a1656d9fb3','推广','2020-12-16','2020-12-25','1000','123','2020-12-16 11:20:57','张三',NULL,NULL),('fee34d6842d949a0b83c2d6fd2607b21','40f6cdea0bd34aceb77492a1656d9fb3','发传单12','2020-12-14','2020-12-17','2000','已经修改了','2020-12-15 15:04:00','张三','2020-12-16 10:59:50','张三');

/*Table structure for table `tbl_activity_remark` */

DROP TABLE IF EXISTS `tbl_activity_remark`;

CREATE TABLE `tbl_activity_remark` (
  `id` char(32) NOT NULL,
  `noteContent` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editFlag` char(1) DEFAULT NULL COMMENT '0表示未修改，1表示已修改',
  `activityId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_activity_remark` */

insert  into `tbl_activity_remark`(`id`,`noteContent`,`createTime`,`createBy`,`editTime`,`editBy`,`editFlag`,`activityId`) values ('86c2be51709346dc85489339e19ad94c','sadf','2021-03-11 10:46:59','张三',NULL,NULL,'0','0434be3fd9504977b56caeb55eee2a22'),('94e074c603d947e6a7c0d6c370b475d5','备注5','2020-12-15 17:03:39','张三',NULL,NULL,'0','a6b5c788e9da4197a6bdb1da8c479d83');

/*Table structure for table `tbl_clue` */

DROP TABLE IF EXISTS `tbl_clue`;

CREATE TABLE `tbl_clue` (
  `id` char(32) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `appellation` varchar(255) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `mphone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contactSummary` varchar(255) DEFAULT NULL,
  `nextContactTime` char(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue` */

insert  into `tbl_clue`(`id`,`fullname`,`appellation`,`owner`,`company`,`job`,`email`,`phone`,`website`,`mphone`,`state`,`source`,`createBy`,`createTime`,`editBy`,`editTime`,`description`,`contactSummary`,`nextContactTime`,`address`) values ('1bdbe3311dc04365a601ae9e959b3f92','马化腾','女士','40f6cdea0bd34aceb77492a1656d9fb3','网易云','乱拉拉','111@qq.com','123','111.com','321','虚假线索','web调研','张三','2020-12-17 21:46:24',NULL,NULL,'描述12345','纪要12345','2021-01-30','地址12345'),('bd48f8488016428e8f7e1b4d6e7f74cd','王健林','先生','40f6cdea0bd34aceb77492a1656d9fb3','万达','ceo','88@11.com','10086','www.wanda.com','13970000000','将来联系','合作伙伴研讨会','张三','2021-01-03 21:45:12',NULL,NULL,'描述123','纪要123','2021-01-27','地址123'),('ceb283943f4b4a99bdfa66fef8aac68a','老杨','博士','40f6cdea0bd34aceb77492a1656d9fb3','动力节点','法师','111@qq.com','123','111.com','321','虚假线索','外部介绍','张三','2020-12-17 21:45:31',NULL,NULL,'描述123','纪要123','2021-01-07','地址123');

/*Table structure for table `tbl_clue_activity_relation` */

DROP TABLE IF EXISTS `tbl_clue_activity_relation`;

CREATE TABLE `tbl_clue_activity_relation` (
  `id` char(32) NOT NULL,
  `clueId` char(32) DEFAULT NULL,
  `activityId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue_activity_relation` */

/*Table structure for table `tbl_clue_remark` */

DROP TABLE IF EXISTS `tbl_clue_remark`;

CREATE TABLE `tbl_clue_remark` (
  `id` char(32) NOT NULL,
  `noteContent` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editFlag` char(1) DEFAULT NULL,
  `clueId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_clue_remark` */

insert  into `tbl_clue_remark`(`id`,`noteContent`,`createBy`,`createTime`,`editBy`,`editTime`,`editFlag`,`clueId`) values ('1bdbe3311dc04365a601ae9e959b3f9a','备注4（马化腾）',NULL,NULL,NULL,NULL,NULL,'1bdbe3311dc04365a601ae9e959b3f92');

/*Table structure for table `tbl_contacts` */

DROP TABLE IF EXISTS `tbl_contacts`;

CREATE TABLE `tbl_contacts` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `customerId` char(32) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `appellation` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mphone` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `birth` char(10) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contactSummary` varchar(255) DEFAULT NULL,
  `nextContactTime` char(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts` */

insert  into `tbl_contacts`(`id`,`owner`,`source`,`customerId`,`fullname`,`appellation`,`email`,`mphone`,`job`,`birth`,`createBy`,`createTime`,`editBy`,`editTime`,`description`,`contactSummary`,`nextContactTime`,`address`) values ('2dc1c6042cc64d2eae798886dbbaa2e4','40f6cdea0bd34aceb77492a1656d9fb3','内部研讨会','ad767cf4071145d5af8ffcfdcf989e5a','姓名','先生','邮箱','321','职位',NULL,'张三','2021-06-24 11:14:04',NULL,NULL,'描述','纪要','2021-03-22','地址'),('31fe34971f0045d6b1ef9eb9dacfa8e9','40f6cdea0bd34aceb77492a1656d9fb3','在线商场','4d309bf3b9724ced85bfb7cde8021dd7','卢本伟','先生','生巅峰','123','是的方法生巅峰',NULL,'张三','2021-06-24 11:16:35',NULL,NULL,'生巅峰','独算法撒旦发射点发生的发到','2021-02-23','地址12士大夫士大夫大师傅法师'),('e7b6758e11bf47f98ccc9e33f81147d5','40f6cdea0bd34aceb77492a1656d9fb3','web下载','680eb285c06c4e74964e391db5dc38b7','马云','先生','111@qq.com','321','法师',NULL,'张三','2020-12-23 15:59:27',NULL,NULL,'描述1234','纪要1234','2021-01-29','地址1234');

/*Table structure for table `tbl_contacts_activity_relation` */

DROP TABLE IF EXISTS `tbl_contacts_activity_relation`;

CREATE TABLE `tbl_contacts_activity_relation` (
  `id` char(32) NOT NULL,
  `contactsId` char(32) DEFAULT NULL,
  `activityId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts_activity_relation` */

insert  into `tbl_contacts_activity_relation`(`id`,`contactsId`,`activityId`) values ('1dc19bc62d2645efb38643f33c4ce2ad','e7b6758e11bf47f98ccc9e33f81147d5','1237635b2711470b9320dc730ba777e4'),('3a8aa07a860d42b591a3c24faea706e4','e7b6758e11bf47f98ccc9e33f81147d5','64e21b80221e468bbd1a54589a98527e'),('66e6db63290d4cd79d0ea449102d3abe','e7b6758e11bf47f98ccc9e33f81147d5','0db857a8afa0465ebf996465cb2c336b'),('72e0b6c5122740e2abaaf88022452459','31fe34971f0045d6b1ef9eb9dacfa8e9','10aef134689b44fcbbcfbae8724d5ea4'),('9594eba757fd4e8da56c4fe7247f8435','e7b6758e11bf47f98ccc9e33f81147d5','248eca85384d46c980bb2ffaa8b68fc6'),('a4322cb459c142229d819bdf5639e2d3','e7b6758e11bf47f98ccc9e33f81147d5','58fde2f202fb4ed387434ec187a9374c');

/*Table structure for table `tbl_contacts_remark` */

DROP TABLE IF EXISTS `tbl_contacts_remark`;

CREATE TABLE `tbl_contacts_remark` (
  `id` char(32) NOT NULL,
  `noteContent` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editFlag` char(1) DEFAULT NULL,
  `contactsId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_contacts_remark` */

insert  into `tbl_contacts_remark`(`id`,`noteContent`,`createBy`,`createTime`,`editBy`,`editTime`,`editFlag`,`contactsId`) values ('11a078f174fd4a6f9a2b7956876ab71f','备注1（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7'),('8e83dbfa7feb41f0a4d3de073f2fbc25','备注2（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7'),('a3e27a0ca50243178a73ca59ee850c32','备注3（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7');

/*Table structure for table `tbl_customer` */

DROP TABLE IF EXISTS `tbl_customer`;

CREATE TABLE `tbl_customer` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `contactSummary` varchar(255) DEFAULT NULL,
  `nextContactTime` char(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_customer` */

insert  into `tbl_customer`(`id`,`owner`,`name`,`website`,`phone`,`createBy`,`createTime`,`editBy`,`editTime`,`contactSummary`,`nextContactTime`,`description`,`address`) values ('4d309bf3b9724ced85bfb7cde8021dd7','40f6cdea0bd34aceb77492a1656d9fb3','卢本伟','速度生巅峰生巅峰','123','张三','2021-06-24 11:16:35',NULL,NULL,'独算法撒旦发射点发生的发到','2021-02-23','生巅峰','地址12士大夫士大夫大师傅法师'),('680eb285c06c4e74964e391db5dc38b7','40f6cdea0bd34aceb77492a1656d9fb3','阿里巴巴','111.com','1231','张三','2020-12-23 15:59:27',NULL,NULL,'纪要1234','2021-01-29','描述1234','地址1234'),('680eb285c06c4e74964e391db5dc38ba','40f6cdea0bd34aceb77492a1656d9fb3','阿里巴巴2','222.com','1232','张三','2020-12-23 15:59:27',NULL,NULL,NULL,NULL,'描述1234','地址1234'),('680eb285c06c4e74964e391db5dc38bb','40f6cdea0bd34aceb77492a1656d9fb3','阿里巴巴3','333.com','1233','张三','2020-12-23 15:59:27',NULL,NULL,NULL,NULL,'描述1234','地址1234'),('680eb285c06c4e74964e391db5dc38bc','40f6cdea0bd34aceb77492a1656d9fb3','阿里巴巴4','444.com','1234','张三','2020-12-23 15:59:27',NULL,NULL,NULL,NULL,'描述1234','地址1234'),('680eb285c06c4e74964e391db5dc38bd','40f6cdea0bd34aceb77492a1656d9fb3','阿里巴巴5','555.com','1235','张三','2020-12-23 15:59:27',NULL,NULL,NULL,NULL,'描述1234','地址1234'),('680eb285c06c4e74964e391db5dc38be','40f6cdea0bd34aceb77492a1656d9fb3','1','666.com','1236','张三','2020-12-23 15:59:27',NULL,NULL,NULL,NULL,'描述1234','地址1234'),('7bea05b221c44c479840350c19d90408','40f6cdea0bd34aceb77492a1656d9fb3','客户名称',NULL,NULL,'张三','2020-12-25 11:41:28',NULL,NULL,'纪要123','2020-12-26',NULL,NULL),('ad767cf4071145d5af8ffcfdcf989e5a','40f6cdea0bd34aceb77492a1656d9fb3','公司','www','123','张三','2021-06-24 11:14:04',NULL,NULL,'纪要','2021-03-22','描述','地址');

/*Table structure for table `tbl_customer_remark` */

DROP TABLE IF EXISTS `tbl_customer_remark`;

CREATE TABLE `tbl_customer_remark` (
  `id` char(32) NOT NULL,
  `noteContent` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editFlag` char(1) DEFAULT NULL,
  `customerId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_customer_remark` */

insert  into `tbl_customer_remark`(`id`,`noteContent`,`createBy`,`createTime`,`editBy`,`editTime`,`editFlag`,`customerId`) values ('24b9000111fc49a5be4d40ebced3db87','备注1（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7'),('332b9dd68ea248279429286c42ce469c','备注3（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7'),('57dac2b56ad3414593b5a31bf822b1d2','备注2（属于马云）','张三','2020-12-23 15:59:27',NULL,NULL,'0','680eb285c06c4e74964e391db5dc38b7');

/*Table structure for table `tbl_dic_type` */

DROP TABLE IF EXISTS `tbl_dic_type`;

CREATE TABLE `tbl_dic_type` (
  `code` varchar(255) NOT NULL COMMENT '编码是主键，不能为空，不能含有中文。',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_dic_type` */

insert  into `tbl_dic_type`(`code`,`name`,`description`) values ('appellation','称呼',''),('clueState','线索状态',''),('returnPriority','回访优先级',''),('returnState','回访状态',''),('source','来源',''),('stage','阶段',''),('transactionType','交易类型','');

/*Table structure for table `tbl_dic_value` */

DROP TABLE IF EXISTS `tbl_dic_value`;

CREATE TABLE `tbl_dic_value` (
  `id` char(32) NOT NULL COMMENT '主键，采用UUID',
  `value` varchar(255) DEFAULT NULL COMMENT '不能为空，并且要求同一个字典类型下字典值不能重复，具有唯一性。',
  `text` varchar(255) DEFAULT NULL COMMENT '可以为空',
  `orderNo` varchar(255) DEFAULT NULL COMMENT '可以为空，但不为空的时候，要求必须是正整数',
  `typeCode` varchar(255) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_dic_value` */

insert  into `tbl_dic_value`(`id`,`value`,`text`,`orderNo`,`typeCode`) values ('06e3cbdf10a44eca8511dddfc6896c55','虚假线索','虚假线索','4','clueState'),('0fe33840c6d84bf78df55d49b169a894','销售邮件','销售邮件','8','source'),('12302fd42bd349c1bb768b19600e6b20','交易会','交易会','11','source'),('1615f0bb3e604552a86cde9a2ad45bea','最高','最高','2','returnPriority'),('176039d2a90e4b1a81c5ab8707268636','教授','教授111','5','appellation'),('1e0bd307e6ee425599327447f8387285','将来联系','将来联系','2','clueState'),('2173663b40b949ce928db92607b5fe57','丢失线索','丢失线索','5','clueState'),('2876690b7e744333b7f1867102f91153','未启动','未启动','1','returnState'),('29805c804dd94974b568cfc9017b2e4c','07成交','07成交','7','stage'),('310e6a49bd8a4962b3f95a1d92eb76f4','试图联系','试图联系','1','clueState'),('31539e7ed8c848fc913e1c2c93d76fd1','博士','博士','4','appellation'),('37ef211719134b009e10b7108194cf46','01资质审查','01资质审查','1','stage'),('391807b5324d4f16bd58c882750ee632','08丢失的线索','08丢失的线索','8','stage'),('3a39605d67da48f2a3ef52e19d243953','聊天','聊天','14','source'),('474ab93e2e114816abf3ffc596b19131','低','低','3','returnPriority'),('48512bfed26145d4a38d3616e2d2cf79','广告','广告','1','source'),('4d03a42898684135809d380597ed3268','合作伙伴研讨会','合作伙伴研讨会','9','source'),('59795c49896947e1ab61b7312bd0597c','先生','先生','1','appellation'),('5c6e9e10ca414bd499c07b886f86202a','高','高','1','returnPriority'),('67165c27076e4c8599f42de57850e39c','夫人','夫人','2','appellation'),('68a1b1e814d5497a999b8f1298ace62b','09因竞争丢失关闭','09因竞争丢失关闭','9','stage'),('6b86f215e69f4dbd8a2daa22efccf0cf','web调研','web调研','13','source'),('72f13af8f5d34134b5b3f42c5d477510','合作伙伴','合作伙伴','6','source'),('7c07db3146794c60bf975749952176df','未联系','未联系','6','clueState'),('86c56aca9eef49058145ec20d5466c17','内部研讨会','内部研讨会','10','source'),('9095bda1f9c34f098d5b92fb870eba17','进行中','进行中','3','returnState'),('954b410341e7433faa468d3c4f7cf0d2','已有业务','已有业务','1','transactionType'),('966170ead6fa481284b7d21f90364984','已联系','已联系','3','clueState'),('96b03f65dec748caa3f0b6284b19ef2f','推迟','推迟','2','returnState'),('97d1128f70294f0aac49e996ced28c8a','新业务','新业务','2','transactionType'),('9ca96290352c40688de6596596565c12','完成','完成','4','returnState'),('9e6d6e15232549af853e22e703f3e015','需要条件','需要条件','7','clueState'),('9ff57750fac04f15b10ce1bbb5bb8bab','02需求分析','02需求分析','2','stage'),('a70dc4b4523040c696f4421462be8b2f','等待某人','等待某人','5','returnState'),('a83e75ced129421dbf11fab1f05cf8b4','推销电话','推销电话','2','source'),('ab8472aab5de4ae9b388b2f1409441c1','常规','常规','5','returnPriority'),('ab8c2a3dc05f4e3dbc7a0405f721b040','05提案/报价','05提案/报价','5','stage'),('b924d911426f4bc5ae3876038bc7e0ad','web下载','web下载','12','source'),('c13ad8f9e2f74d5aa84697bb243be3bb','03价值建议','03价值建议','3','stage'),('c83c0be184bc40708fd7b361b6f36345','最低','最低','4','returnPriority'),('db867ea866bc44678ac20c8a4a8bfefb','员工介绍','员工介绍','3','source'),('e44be1d99158476e8e44778ed36f4355','04确定决策者','04确定决策者','4','stage'),('e5f383d2622b4fc0959f4fe131dafc80','女士','女士','3','appellation'),('e81577d9458f4e4192a44650a3a3692b','06谈判/复审','06谈判/复审','6','stage'),('fb65d7fdb9c6483db02713e6bc05dd19','在线商场','在线商场','5','source'),('fd677cc3b5d047d994e16f6ece4d3d45','公开媒介','公开媒介','7','source'),('ff802a03ccea4ded8731427055681d48','外部介绍','外部介绍','4','source');

/*Table structure for table `tbl_tran` */

DROP TABLE IF EXISTS `tbl_tran`;

CREATE TABLE `tbl_tran` (
  `id` char(32) NOT NULL,
  `owner` char(32) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `expectedDate` char(10) DEFAULT NULL,
  `customerId` char(32) DEFAULT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `activityId` char(32) DEFAULT NULL,
  `contactsId` char(32) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contactSummary` varchar(255) DEFAULT NULL,
  `nextContactTime` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran` */

insert  into `tbl_tran`(`id`,`owner`,`money`,`name`,`expectedDate`,`customerId`,`stage`,`type`,`source`,`activityId`,`contactsId`,`createBy`,`createTime`,`editBy`,`editTime`,`description`,`contactSummary`,`nextContactTime`) values ('782b922973dc42e8898377d5cd7dc787','40f6cdea0bd34aceb77492a1656d9fb3','1000','交易123','2020-12-24','680eb285c06c4e74964e391db5dc38b7','07成交','新业务','web下载','0db857a8afa0465ebf996465cb2c336b','e7b6758e11bf47f98ccc9e33f81147d5','张三','2020-12-23 15:59:27','张三','2021-06-24 11:21:09','描述1234','纪要1234','2021-01-29'),('934e099ee1974524b750c2bf8d0e1fa7','40f6cdea0bd34aceb77492a1656d9fb3','1000000','噜啦啦','2020-12-17','680eb285c06c4e74964e391db5dc38b7','03价值建议','新业务','员工介绍','0db857a8afa0465ebf996465cb2c336b','e7b6758e11bf47f98ccc9e33f81147d5','张三','2020-12-25 11:44:32',NULL,NULL,'描述111111','纪要11111111','2020-12-31'),('d3de74b781d74d57a65feb95816ce12d','40f6cdea0bd34aceb77492a1656d9fb3','1000','名称123','2020-12-25','7bea05b221c44c479840350c19d90408','01资质审查','已有业务','交易会','0db857a8afa0465ebf996465cb2c336b','e7b6758e11bf47f98ccc9e33f81147d5','张三','2020-12-25 11:41:28',NULL,NULL,'描述123','纪要123','2020-12-26'),('ed297e5b1a704326aadd01d20d3ab97b','40f6cdea0bd34aceb77492a1656d9fb3','2000','动力节点-','2021-06-26','4d309bf3b9724ced85bfb7cde8021dd7','03价值建议',NULL,'在线商场','0db857a8afa0465ebf996465cb2c336b','31fe34971f0045d6b1ef9eb9dacfa8e9','张三','2021-06-24 11:16:35',NULL,NULL,'生巅峰','独算法撒旦发射点发生的发到','2021-02-23');

/*Table structure for table `tbl_tran_history` */

DROP TABLE IF EXISTS `tbl_tran_history`;

CREATE TABLE `tbl_tran_history` (
  `id` char(32) NOT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `expectedDate` char(10) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `tranId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran_history` */

insert  into `tbl_tran_history`(`id`,`stage`,`money`,`expectedDate`,`createTime`,`createBy`,`tranId`) values ('01bb97b10bf44a5882f9b4c241544461','01资质审查','1000','2020-12-24','2020-12-28 16:23:33','张三','782b922973dc42e8898377d5cd7dc787'),('040594ed71a245209bdc177ea9786d91','06谈判/复审','1000','2020-12-24','2020-12-28 16:23:56','张三','782b922973dc42e8898377d5cd7dc787'),('0c89d580beee4ced8e28d239557754e9','09因竞争丢失关闭','1000','2020-12-24','2020-12-28 16:23:50','张三','782b922973dc42e8898377d5cd7dc787'),('20ec4fdac15442229690538ae9204866','03价值建议','2000','2021-06-26','2021-06-24 11:16:35','张三','ed297e5b1a704326aadd01d20d3ab97b'),('25f7e4db308c462592c6c9b247bb4f6c','07成交','1000','2020-12-24','2021-06-24 11:21:03','张三','782b922973dc42e8898377d5cd7dc787'),('2c9f25d34bee4da2ab145d583d4ff1b3','03价值建议','1000','2020-12-24','2020-12-28 16:26:10','张三','782b922973dc42e8898377d5cd7dc787'),('3411113f947f4486a04cdfcdc5983652','07成交','1000','2020-12-24','2021-06-24 11:21:09','张三','782b922973dc42e8898377d5cd7dc787'),('37f81090d28144cd89441c6c2ae6219f','02需求分析','1000','2020-12-24','2020-12-29 13:54:54','张三','782b922973dc42e8898377d5cd7dc787'),('3a15b2b5f2414f62a1181655127c6ad3','01资质审查','1000','2020-12-25','2020-12-25 11:41:28','张三','d3de74b781d74d57a65feb95816ce12d'),('3f3032e81e164286bad15c422cf3fdde','03价值建议','1000000','2020-12-17','2020-12-25 11:44:32','张三','934e099ee1974524b750c2bf8d0e1fa7'),('43a6b37d64c84b509cc010a2370a5432','08丢失的线索','1000','2020-12-24','2020-12-28 16:23:28','张三','782b922973dc42e8898377d5cd7dc787'),('4a9ee77481704fe588a65441ef0f05bf','05提案/报价','1000','2020-12-24','2020-12-28 16:23:51','张三','782b922973dc42e8898377d5cd7dc787'),('4d1d6d3b3b5b404b9069788dd82601d4','04确定决策者','1000','2020-12-24','2020-12-28 16:22:58','张三','782b922973dc42e8898377d5cd7dc787'),('4dbfa9c5b75a4b7a89b27a48d2435039','01资质审查','1000','2020-12-24','2021-03-16 14:10:46','张三','782b922973dc42e8898377d5cd7dc787'),('5aeaf8d031e7469a900fba3d63a5cf56','08丢失的线索','1000','2020-12-24','2020-12-23 15:59:27','张三','782b922973dc42e8898377d5cd7dc787'),('60334c75f9c341eaa2e0b68b31d8b811','08丢失的线索','1000','2020-12-24','2021-06-24 11:21:06','张三','782b922973dc42e8898377d5cd7dc787'),('65ff906716094bb5aa5a2beda5840df9','02需求分析','1000','2020-12-24','2021-03-16 14:09:36','张三','782b922973dc42e8898377d5cd7dc787'),('66e76dedd79448008755cbf11b3eacf1','02需求分析','1000','2020-12-24','2020-12-28 16:26:06','张三','782b922973dc42e8898377d5cd7dc787'),('726427d799ff4ca9ad372775f009a298','01资质审查','1000','2020-12-24','2020-12-28 16:26:07','张三','782b922973dc42e8898377d5cd7dc787'),('735b989ae8ae4ed190ab720e9b41b43c','03价值建议','1000','2020-12-24','2020-12-28 15:55:19','张三','782b922973dc42e8898377d5cd7dc787'),('7610c22d9290406c9b5100f7406c4c09','06谈判/复审','1000','2020-12-24','2020-12-28 16:23:43','张三','782b922973dc42e8898377d5cd7dc787'),('7973a55508864818b76c80090737ab09','04确定决策者','1000','2020-12-24','2021-03-16 14:10:01','张三','782b922973dc42e8898377d5cd7dc787'),('81aea764eab04195ba03cd2edfac561a','07成交','1000','2020-12-24','2021-03-14 19:58:18','张三','782b922973dc42e8898377d5cd7dc787'),('8aae67f44f8f49028caa2d71d05d8651','02需求分析','1000','2020-12-24','2020-12-28 16:23:35','张三','782b922973dc42e8898377d5cd7dc787'),('8dceebc604b34e1db0e5d47f1535d6f5','04确定决策者','1000','2020-12-24','2020-12-28 15:42:43','张三','782b922973dc42e8898377d5cd7dc787'),('9ac7b0c89c574deba41e0a551f7ee266','06谈判/复审','1000','2020-12-24','2021-06-24 11:21:00','张三','782b922973dc42e8898377d5cd7dc787'),('a5ac4c6e970a45ec8d831bcadb679618','03价值建议','1000','2020-12-24','2020-12-28 16:26:04','张三','782b922973dc42e8898377d5cd7dc787'),('b870f562a8d4420b9861c5712924c8eb','05提案/报价','1000','2020-12-24','2020-12-28 16:23:42','张三','782b922973dc42e8898377d5cd7dc787'),('b951b034649f4797be560e8afb2dd687','01资质审查','1000','2020-12-24','2021-03-16 14:09:20','张三','782b922973dc42e8898377d5cd7dc787'),('be7b01b882a945dfac93224e703bdc0d','07成交','1000','2020-12-24','2020-12-28 16:23:45','张三','782b922973dc42e8898377d5cd7dc787'),('c08f03781f9c446d83ed5bf8427dde90','03价值建议','1000','2020-12-24','2020-12-28 16:23:38','张三','782b922973dc42e8898377d5cd7dc787'),('ce60225bd69f4130a23fffe19eb28426','07成交','1000','2020-12-24','2021-06-24 11:20:56','张三','782b922973dc42e8898377d5cd7dc787'),('cf025687206041db8c62cd03b718ae66','04确定决策者','1000','2020-12-24','2020-12-28 18:34:26','张三','782b922973dc42e8898377d5cd7dc787'),('e5ee9dcd3ac64373a7f135b4dd617f16','05提案/报价','1000','2020-12-24','2020-12-28 16:23:05','张三','782b922973dc42e8898377d5cd7dc787'),('f459f8f391b94b698d8d2d5c1506d271','08丢失的线索','1000','2020-12-24','2020-12-28 16:23:47','张三','782b922973dc42e8898377d5cd7dc787'),('fc3f506fc49d40d9838d501c4abbb909','04确定决策者','1000','2020-12-24','2020-12-28 16:23:40','张三','782b922973dc42e8898377d5cd7dc787');

/*Table structure for table `tbl_tran_remark` */

DROP TABLE IF EXISTS `tbl_tran_remark`;

CREATE TABLE `tbl_tran_remark` (
  `id` char(32) NOT NULL,
  `noteContent` varchar(255) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editFlag` char(1) DEFAULT NULL,
  `tranId` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_tran_remark` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` char(32) NOT NULL COMMENT 'uuid\n            ',
  `loginAct` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `loginPwd` varchar(255) DEFAULT NULL COMMENT '密码不能采用明文存储，采用密文，MD5加密之后的数据',
  `email` varchar(255) DEFAULT NULL,
  `expireTime` char(19) DEFAULT NULL COMMENT '失效时间为空的时候表示永不失效，失效时间为2018-10-10 10:10:10，则表示在该时间之前该账户可用。',
  `lockState` char(1) DEFAULT NULL COMMENT '锁定状态为空时表示启用，为0时表示锁定，为1时表示启用。',
  `deptno` char(4) DEFAULT NULL,
  `allowIps` varchar(255) DEFAULT NULL COMMENT '允许访问的IP为空时表示IP地址永不受限，允许访问的IP可以是一个，也可以是多个，当多个IP地址的时候，采用半角逗号分隔。允许IP是192.168.100.2，表示该用户只能在IP地址为192.168.100.2的机器上使用。',
  `createTime` char(19) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `editTime` char(19) DEFAULT NULL,
  `editBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`loginAct`,`name`,`loginPwd`,`email`,`expireTime`,`lockState`,`deptno`,`allowIps`,`createTime`,`createBy`,`editTime`,`editBy`) values ('06f5fc056eac41558a964f96daa7f27c','ls','李四','202cb962ac59075b964b07152d234b70','ls@163.com','2022-11-27 21:50:05','1','A001','192.168.1.1','2018-11-22 12:11:40','李四',NULL,NULL),('40f6cdea0bd34aceb77492a1656d9fb3','zs','张三','202cb962ac59075b964b07152d234b70','zs@qq.com','2022-11-30 23:50:55','1','A001','192.168.1.1,192.168.1.2,127.0.0.1,1:0:1:0:0:0:0:0:0:0:1','2018-11-22 11:37:34','张三',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL DEFAULT '123456',
  `name` varchar(45) NOT NULL,
  `gender` int NOT NULL DEFAULT '1',
  `age` int NOT NULL DEFAULT '0',
  `phone` tinytext NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_name`,`password`,`name`,`gender`,`age`,`phone`) values (1,'xuexiang','123456','薛翔',1,24,'13813823453'),(2,'meixue','123456','美雪',2,20,'13817834234'),(3,'wangxiaoya','123456','王小丫',2,45,'17482954839'),(4,'xuexin','123456','薛欣',2,59,'1111112222233333');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
