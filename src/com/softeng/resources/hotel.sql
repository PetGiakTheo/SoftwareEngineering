-- MySQL dump 10.13  Distrib 5.7.16, for Win32 (AMD64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(10) unsigned NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `paymentType` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'name_1','surname_1','surname_1@gmail.com','9890022645','debit'),(2,'name_2','surname_2','surname_2@gmail.com','3476642432','debit'),(3,'name_3','surname_3','surname_3@gmail.com','7752533217','debit'),(4,'name_4','surname_4','surname_4@gmail.com','5196078297','credit'),(5,'name_5','surname_5','surname_5@gmail.com','6442187650','credit'),(6,'name_6','surname_6','surname_6@gmail.com','6144772424','credit'),(7,'name_7','surname_7','surname_7@gmail.com','6961451429','debit'),(8,'name_8','surname_8','surname_8@gmail.com','6656861814','credit'),(9,'name_9','surname_9','surname_9@gmail.com','0048733735','debit'),(10,'name_10','surname_10','surname_10@gmail.com','6099015712','credit'),(11,'name_11','surname_11','surname_11@gmail.com','6339304923','credit'),(12,'name_12','surname_12','surname_12@gmail.com','9315029969','debit'),(13,'name_13','surname_13','surname_13@gmail.com','8928800702','debit'),(14,'name_14','surname_14','surname_14@gmail.com','5622068229','credit'),(15,'name_15','surname_15','surname_15@gmail.com','6207556701','credit'),(16,'name_16','surname_16','surname_16@gmail.com','7636283432','credit'),(17,'name_17','surname_17','surname_17@gmail.com','3974104931','debit'),(18,'name_18','surname_18','surname_18@gmail.com','1899523181','credit'),(19,'name_19','surname_19','surname_19@gmail.com','0314807389','credit'),(20,'name_20','surname_20','surname_20@gmail.com','1610340673','credit'),(21,'name_21','surname_21','surname_21@gmail.com','7131950337','debit'),(22,'name_22','surname_22','surname_22@gmail.com','6706963005','debit'),(23,'name_23','surname_23','surname_23@gmail.com','9049174982','debit'),(24,'name_24','surname_24','surname_24@gmail.com','9505424716','credit'),(25,'name_25','surname_25','surname_25@gmail.com','8357484224','debit'),(26,'name_26','surname_26','surname_26@gmail.com','3852552759','debit'),(27,'name_27','surname_27','surname_27@gmail.com','7068424866','credit'),(28,'name_28','surname_28','surname_28@gmail.com','0362375513','debit'),(29,'name_29','surname_29','surname_29@gmail.com','7436459576','credit'),(30,'name_30','surname_30','surname_30@gmail.com','9089486108','credit'),(31,'name_31','surname_31','surname_31@gmail.com','8912970848','debit'),(32,'name_32','surname_32','surname_32@gmail.com','8865629746','debit'),(33,'name_33','surname_33','surname_33@gmail.com','8181050144','credit'),(34,'name_34','surname_34','surname_34@gmail.com','5428133248','credit'),(35,'name_35','surname_35','surname_35@gmail.com','8230744363','credit'),(36,'name_36','surname_36','surname_36@gmail.com','8361555977','credit'),(37,'name_37','surname_37','surname_37@gmail.com','7594007733','credit'),(38,'name_38','surname_38','surname_38@gmail.com','0856913945','debit'),(39,'name_39','surname_39','surname_39@gmail.com','8926729437','debit'),(40,'name_40','surname_40','surname_40@gmail.com','9178204076','credit'),(41,'name_41','surname_41','surname_41@gmail.com','3512708039','debit'),(42,'name_42','surname_42','surname_42@gmail.com','6874795592','credit'),(43,'name_43','surname_43','surname_43@gmail.com','4410899969','credit'),(44,'name_44','surname_44','surname_44@gmail.com','8565887821','credit'),(45,'name_45','surname_45','surname_45@gmail.com','2222392055','credit'),(46,'name_46','surname_46','surname_46@gmail.com','2261272279','debit'),(47,'name_47','surname_47','surname_47@gmail.com','9647092289','credit'),(48,'name_48','surname_48','surname_48@gmail.com','5255559221','credit'),(49,'name_49','surname_49','surname_49@gmail.com','9858485468','credit'),(50,'name_50','surname_50','surname_50@gmail.com','8985900758','debit'),(51,'name_51','surname_51','surname_51@gmail.com','2878874884','credit'),(52,'name_52','surname_52','surname_52@gmail.com','0517763680','credit'),(53,'name_53','surname_53','surname_53@gmail.com','5349690409','credit'),(54,'name_54','surname_54','surname_54@gmail.com','4975604800','debit'),(55,'name_55','surname_55','surname_55@gmail.com','0977764910','credit'),(56,'name_56','surname_56','surname_56@gmail.com','2258161341','debit'),(57,'name_57','surname_57','surname_57@gmail.com','3445915116','credit'),(58,'name_58','surname_58','surname_58@gmail.com','0502102563','debit'),(59,'name_59','surname_59','surname_59@gmail.com','9403632329','debit'),(60,'name_60','surname_60','surname_60@gmail.com','8052410754','credit'),(61,'name_61','surname_61','surname_61@gmail.com','1318216788','credit'),(62,'name_62','surname_62','surname_62@gmail.com','4060157177','debit'),(63,'name_63','surname_63','surname_63@gmail.com','2949504271','credit'),(64,'name_64','surname_64','surname_64@gmail.com','4921720647','debit'),(65,'name_65','surname_65','surname_65@gmail.com','4335721593','debit'),(66,'name_66','surname_66','surname_66@gmail.com','3475087754','debit'),(67,'name_67','surname_67','surname_67@gmail.com','1763442125','credit'),(68,'name_68','surname_68','surname_68@gmail.com','5968423126','credit'),(69,'name_69','surname_69','surname_69@gmail.com','7475156594','debit'),(70,'name_70','surname_70','surname_70@gmail.com','9926385694','credit'),(71,'name_71','surname_71','surname_71@gmail.com','3154875933','debit'),(72,'name_72','surname_72','surname_72@gmail.com','6763112002','credit'),(73,'name_73','surname_73','surname_73@gmail.com','3573740507','credit'),(74,'name_74','surname_74','surname_74@gmail.com','4698463204','credit'),(75,'name_75','surname_75','surname_75@gmail.com','5935199365','credit'),(76,'name_76','surname_76','surname_76@gmail.com','1470189831','debit'),(77,'name_77','surname_77','surname_77@gmail.com','8929615862','credit'),(78,'name_78','surname_78','surname_78@gmail.com','4676298973','debit'),(79,'name_79','surname_79','surname_79@gmail.com','3904046952','debit'),(80,'name_80','surname_80','surname_80@gmail.com','3222631598','credit'),(81,'name_81','surname_81','surname_81@gmail.com','6872120885','credit'),(82,'name_82','surname_82','surname_82@gmail.com','7585604200','credit'),(83,'name_83','surname_83','surname_83@gmail.com','5987184409','credit'),(84,'name_84','surname_84','surname_84@gmail.com','2288216480','credit'),(85,'name_85','surname_85','surname_85@gmail.com','7017209388','credit'),(86,'name_86','surname_86','surname_86@gmail.com','0586831567','credit'),(87,'name_87','surname_87','surname_87@gmail.com','7649357101','credit'),(88,'name_88','surname_88','surname_88@gmail.com','9607670687','debit'),(89,'name_89','surname_89','surname_89@gmail.com','1040983955','debit'),(90,'name_90','surname_90','surname_90@gmail.com','9674007350','credit'),(91,'name_91','surname_91','surname_91@gmail.com','5378511008','debit'),(92,'name_92','surname_92','surname_92@gmail.com','8732412333','credit'),(93,'name_93','surname_93','surname_93@gmail.com','4145905393','credit'),(94,'name_94','surname_94','surname_94@gmail.com','0127745802','credit'),(95,'name_95','surname_95','surname_95@gmail.com','1313268568','credit'),(96,'name_96','surname_96','surname_96@gmail.com','5069941112','debit'),(97,'name_97','surname_97','surname_97@gmail.com','3221458496','credit'),(98,'name_98','surname_98','surname_98@gmail.com','4874980959','credit'),(99,'name_99','surname_99','surname_99@gmail.com','7404512403','debit'),(100,'name_100','surname_100','surname_100@gmail.com','4441002326','credit');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discounts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hotel` tinyint(4) DEFAULT NULL,
  `strDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `percentage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (31,1,'2017-01-01','2017-01-31',1);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations1`
--

DROP TABLE IF EXISTS `reservations1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations1` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `status` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations1_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `reservations1_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations1`
--

LOCK TABLES `reservations1` WRITE;
/*!40000 ALTER TABLE `reservations1` DISABLE KEYS */;
INSERT INTO `reservations1` VALUES (1,'2017-01-02','2017-01-04',67,31,'active'),(2,'2017-02-03','2017-02-05',87,13,'active'),(3,'2017-03-03','2017-03-07',12,49,'active'),(4,'2017-04-02','2017-04-05',13,11,'cancelled'),(5,'2017-05-01','2017-05-02',70,13,'active'),(6,'2017-06-01','2017-06-02',29,45,'active'),(7,'2017-07-02','2017-07-03',31,20,'cancelled'),(8,'2017-08-03','2017-08-06',90,39,'active'),(9,'2017-09-02','2017-09-05',12,46,'active'),(10,'2017-10-03','2017-10-04',29,4,'active'),(11,'2017-11-02','2017-11-04',36,4,'active'),(12,'2017-12-03','2017-12-05',63,50,'active'),(13,'2017-01-13','2017-01-15',19,40,'active'),(14,'2017-02-11','2017-02-15',39,31,'active'),(15,'2017-03-13','2017-03-14',64,30,'cancelled'),(16,'2017-04-12','2017-04-13',12,23,'active'),(17,'2017-05-12','2017-05-14',38,50,'active'),(18,'2017-06-12','2017-06-16',31,21,'active'),(19,'2017-07-12','2017-07-16',75,33,'active'),(20,'2017-08-11','2017-08-15',64,26,'active'),(21,'2017-09-11','2017-09-13',41,18,'cancelled'),(22,'2017-10-12','2017-10-13',25,22,'active'),(23,'2017-11-13','2017-11-17',9,27,'active'),(24,'2017-12-12','2017-12-16',41,47,'active'),(25,'2017-01-23','2017-01-26',58,21,'active'),(26,'2017-02-23','2017-02-24',68,1,'active'),(27,'2017-03-21','2017-03-24',30,17,'cancelled'),(28,'2017-04-23','2017-04-27',19,2,'cancelled'),(29,'2017-05-22','2017-05-25',33,14,'cancelled'),(30,'2017-06-23','2017-06-26',78,21,'active'),(31,'2017-07-22','2017-07-25',53,35,'cancelled'),(32,'2017-08-22','2017-08-23',64,45,'active'),(33,'2017-09-23','2017-09-27',37,8,'active'),(34,'2017-10-23','2017-10-24',82,6,'active'),(35,'2017-11-22','2017-11-23',5,18,'cancelled'),(36,'2017-12-22','2017-12-24',95,12,'cancelled'),(1001,'2017-01-18','2017-01-21',10,43,'active'),(1002,'2017-01-17','2017-01-23',18,17,'active'),(1003,'2017-01-30','2017-02-02',1,2,'active'),(100000,'2017-02-02','2017-02-03',5,41,'active');
/*!40000 ALTER TABLE `reservations1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations2`
--

DROP TABLE IF EXISTS `reservations2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations2` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `status` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations2_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `reservations2_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations2`
--

LOCK TABLES `reservations2` WRITE;
/*!40000 ALTER TABLE `reservations2` DISABLE KEYS */;
INSERT INTO `reservations2` VALUES (1,'2017-01-03','2017-01-07',42,4,'active'),(2,'2017-02-01','2017-02-05',4,42,'active'),(3,'2017-03-03','2017-03-06',97,4,'active'),(4,'2017-04-02','2017-04-06',33,13,'active'),(5,'2017-05-02','2017-05-04',85,16,'active'),(6,'2017-06-03','2017-06-05',27,29,'cancelled'),(7,'2017-07-01','2017-07-04',95,10,'active'),(8,'2017-08-03','2017-08-04',3,39,'active'),(9,'2017-09-01','2017-09-04',66,28,'active'),(10,'2017-10-01','2017-10-03',27,39,'active'),(11,'2017-11-02','2017-11-03',47,38,'active'),(12,'2017-12-03','2017-12-05',37,6,'active'),(13,'2017-01-11','2017-01-13',70,17,'cancelled'),(14,'2017-02-11','2017-02-15',97,14,'cancelled'),(15,'2017-03-11','2017-03-14',34,21,'active'),(16,'2017-04-11','2017-04-12',79,38,'active'),(17,'2017-05-12','2017-05-15',32,34,'active'),(18,'2017-06-13','2017-06-17',77,12,'active'),(19,'2017-07-13','2017-07-16',88,31,'active'),(20,'2017-08-11','2017-08-15',11,48,'active'),(21,'2017-09-12','2017-09-14',58,21,'active'),(22,'2017-10-12','2017-10-15',95,37,'active'),(23,'2017-11-11','2017-11-14',18,33,'active'),(24,'2017-12-11','2017-12-12',6,46,'active'),(25,'2017-01-23','2017-01-25',71,50,'active'),(26,'2017-02-23','2017-02-27',50,46,'cancelled'),(27,'2017-03-21','2017-03-22',45,19,'active'),(28,'2017-04-21','2017-04-22',19,49,'cancelled'),(29,'2017-05-23','2017-05-24',51,8,'active'),(30,'2017-06-22','2017-06-24',27,11,'cancelled'),(31,'2017-07-23','2017-07-26',54,4,'active'),(32,'2017-08-22','2017-08-23',50,9,'active'),(33,'2017-09-22','2017-09-26',77,49,'active'),(34,'2017-10-23','2017-10-25',6,38,'active'),(35,'2017-11-23','2017-11-26',40,19,'cancelled'),(36,'2017-12-23','2017-12-25',10,37,'cancelled');
/*!40000 ALTER TABLE `reservations2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations3`
--

DROP TABLE IF EXISTS `reservations3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations3` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `status` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations3_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms3` (`id`),
  CONSTRAINT `reservations3_ibfk_2` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations3`
--

LOCK TABLES `reservations3` WRITE;
/*!40000 ALTER TABLE `reservations3` DISABLE KEYS */;
INSERT INTO `reservations3` VALUES (1,'2017-01-03','2017-01-04',8,49,'active'),(2,'2017-02-01','2017-02-04',16,36,'active'),(3,'2017-03-03','2017-03-07',62,5,'active'),(4,'2017-04-01','2017-04-03',75,3,'cancelled'),(5,'2017-05-02','2017-05-03',14,3,'active'),(6,'2017-06-03','2017-06-07',57,49,'active'),(7,'2017-07-03','2017-07-04',29,23,'active'),(8,'2017-08-01','2017-08-03',89,42,'active'),(9,'2017-09-01','2017-09-04',65,39,'active'),(10,'2017-10-02','2017-10-05',61,4,'active'),(11,'2017-11-01','2017-11-04',24,21,'cancelled'),(12,'2017-12-03','2017-12-06',98,43,'active'),(13,'2017-01-11','2017-01-14',95,13,'active'),(14,'2017-02-13','2017-02-15',52,20,'active'),(15,'2017-03-11','2017-03-15',77,45,'cancelled'),(16,'2017-04-11','2017-04-13',90,26,'active'),(17,'2017-05-12','2017-05-14',49,18,'active'),(18,'2017-06-12','2017-06-13',88,36,'cancelled'),(19,'2017-07-12','2017-07-16',79,1,'cancelled'),(20,'2017-08-13','2017-08-15',70,2,'active'),(21,'2017-09-11','2017-09-13',71,25,'active'),(22,'2017-10-12','2017-10-16',37,50,'active'),(23,'2017-11-12','2017-11-16',85,46,'active'),(24,'2017-12-12','2017-12-16',19,40,'cancelled'),(25,'2017-01-23','2017-01-26',96,8,'active'),(26,'2017-02-21','2017-02-24',7,40,'active'),(27,'2017-03-23','2017-03-25',100,37,'cancelled'),(28,'2017-04-22','2017-04-24',17,10,'cancelled'),(29,'2017-05-23','2017-05-26',40,1,'active'),(30,'2017-06-23','2017-06-26',93,49,'active'),(31,'2017-07-21','2017-07-24',34,21,'active'),(32,'2017-08-22','2017-08-26',14,15,'active'),(33,'2017-09-22','2017-09-23',11,16,'active'),(34,'2017-10-23','2017-10-25',63,18,'active'),(35,'2017-11-21','2017-11-22',87,16,'active'),(36,'2017-12-22','2017-12-24',69,4,'active');
/*!40000 ALTER TABLE `reservations3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations4`
--

DROP TABLE IF EXISTS `reservations4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations4` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `status` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations4_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms4` (`id`),
  CONSTRAINT `reservations4_ibfk_2` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations4`
--

LOCK TABLES `reservations4` WRITE;
/*!40000 ALTER TABLE `reservations4` DISABLE KEYS */;
INSERT INTO `reservations4` VALUES (1,'2017-01-03','2017-01-04',95,42,'active'),(2,'2017-02-01','2017-02-02',64,41,'active'),(3,'2017-03-01','2017-03-02',59,26,'active'),(4,'2017-04-01','2017-04-05',71,5,'active'),(5,'2017-05-02','2017-05-06',60,6,'cancelled'),(6,'2017-06-02','2017-06-06',35,46,'active'),(7,'2017-07-03','2017-07-07',40,36,'active'),(8,'2017-08-02','2017-08-04',84,19,'active'),(9,'2017-09-03','2017-09-07',87,27,'active'),(10,'2017-10-01','2017-10-03',37,1,'cancelled'),(11,'2017-11-03','2017-11-04',83,32,'cancelled'),(12,'2017-12-03','2017-12-04',73,16,'active'),(13,'2017-01-11','2017-01-12',62,46,'active'),(14,'2017-02-12','2017-02-13',47,43,'active'),(15,'2017-03-11','2017-03-14',23,15,'active'),(16,'2017-04-12','2017-04-15',87,19,'active'),(17,'2017-05-13','2017-05-17',19,27,'active'),(18,'2017-06-13','2017-06-17',62,8,'active'),(19,'2017-07-12','2017-07-15',73,3,'active'),(20,'2017-08-11','2017-08-13',60,27,'active'),(21,'2017-09-13','2017-09-17',69,14,'cancelled'),(22,'2017-10-11','2017-10-14',75,7,'cancelled'),(23,'2017-11-13','2017-11-17',81,3,'active'),(24,'2017-12-11','2017-12-12',44,32,'cancelled'),(25,'2017-01-22','2017-01-23',64,16,'cancelled'),(26,'2017-02-21','2017-02-24',6,32,'active'),(27,'2017-03-23','2017-03-26',10,26,'active'),(28,'2017-04-23','2017-04-25',95,28,'active'),(29,'2017-05-23','2017-05-24',94,37,'active'),(30,'2017-06-21','2017-06-24',91,33,'cancelled'),(31,'2017-07-23','2017-07-26',87,24,'active'),(32,'2017-08-22','2017-08-26',34,4,'active'),(33,'2017-09-23','2017-09-27',14,20,'active'),(34,'2017-10-23','2017-10-26',74,43,'active'),(35,'2017-11-22','2017-11-26',2,38,'active'),(36,'2017-12-23','2017-12-26',54,30,'active');
/*!40000 ALTER TABLE `reservations4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations5`
--

DROP TABLE IF EXISTS `reservations5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations5` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  `status` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations5_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms5` (`id`),
  CONSTRAINT `reservations5_ibfk_2` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations5`
--

LOCK TABLES `reservations5` WRITE;
/*!40000 ALTER TABLE `reservations5` DISABLE KEYS */;
INSERT INTO `reservations5` VALUES (1,'2017-01-03','2017-01-06',9,7,'active'),(2,'2017-02-03','2017-02-07',45,31,'cancelled'),(3,'2017-03-01','2017-03-03',97,10,'active'),(4,'2017-04-01','2017-04-05',7,30,'active'),(5,'2017-05-01','2017-05-05',32,22,'active'),(6,'2017-06-03','2017-06-07',4,30,'active'),(7,'2017-07-03','2017-07-04',24,21,'active'),(8,'2017-08-03','2017-08-05',42,24,'active'),(9,'2017-09-02','2017-09-04',11,49,'cancelled'),(10,'2017-10-03','2017-10-07',80,28,'cancelled'),(11,'2017-11-01','2017-11-05',77,42,'active'),(12,'2017-12-03','2017-12-06',45,15,'active'),(13,'2017-01-11','2017-01-15',92,32,'active'),(14,'2017-02-12','2017-02-14',61,5,'active'),(15,'2017-03-12','2017-03-16',21,34,'active'),(16,'2017-04-11','2017-04-14',4,14,'active'),(17,'2017-05-11','2017-05-15',69,36,'active'),(18,'2017-06-11','2017-06-15',96,31,'active'),(19,'2017-07-11','2017-07-14',96,3,'cancelled'),(20,'2017-08-11','2017-08-13',94,40,'active'),(21,'2017-09-12','2017-09-14',54,20,'active'),(22,'2017-10-11','2017-10-15',55,39,'active'),(23,'2017-11-11','2017-11-12',15,11,'active'),(24,'2017-12-11','2017-12-15',17,2,'cancelled'),(25,'2017-01-21','2017-01-24',42,15,'active'),(26,'2017-02-23','2017-02-24',21,21,'cancelled'),(27,'2017-03-23','2017-03-25',92,44,'cancelled'),(28,'2017-04-22','2017-04-26',52,33,'active'),(29,'2017-05-22','2017-05-25',2,40,'cancelled'),(30,'2017-06-23','2017-06-24',6,25,'cancelled'),(31,'2017-07-23','2017-07-24',33,20,'active'),(32,'2017-08-23','2017-08-27',74,50,'active'),(33,'2017-09-21','2017-09-23',32,19,'active'),(34,'2017-10-23','2017-10-24',4,38,'active'),(35,'2017-11-23','2017-11-24',40,32,'cancelled'),(36,'2017-12-22','2017-12-26',62,45,'active');
/*!40000 ALTER TABLE `reservations5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms1`
--

DROP TABLE IF EXISTS `rooms1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms1` (
  `id` int(10) unsigned NOT NULL,
  `singleBeds` tinyint(4) NOT NULL,
  `doubleBeds` tinyint(4) NOT NULL,
  `type` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms1`
--

LOCK TABLES `rooms1` WRITE;
/*!40000 ALTER TABLE `rooms1` DISABLE KEYS */;
INSERT INTO `rooms1` VALUES (1,2,2,'reg'),(2,0,1,'reg'),(3,3,0,'vip'),(4,3,1,'vip'),(5,2,3,'vip'),(6,2,3,'vip'),(7,2,0,'vip'),(8,3,3,'vip'),(9,0,2,'reg'),(10,1,2,'vip'),(11,0,3,'vip'),(12,3,0,'reg'),(13,0,1,'vip'),(14,1,0,'reg'),(15,3,1,'vip'),(16,3,3,'vip'),(17,3,2,'vip'),(18,2,0,'vip'),(19,0,2,'vip'),(20,3,2,'vip'),(21,2,3,'vip'),(22,0,3,'reg'),(23,0,3,'vip'),(24,3,2,'vip'),(25,0,3,'reg'),(26,1,0,'reg'),(27,2,1,'reg'),(28,3,0,'vip'),(29,1,0,'reg'),(30,2,3,'reg'),(31,2,2,'vip'),(32,3,2,'vip'),(33,1,3,'reg'),(34,1,0,'vip'),(35,0,3,'vip'),(36,3,0,'vip'),(37,1,2,'reg'),(38,1,0,'reg'),(39,3,0,'vip'),(40,0,3,'reg'),(41,1,2,'vip'),(42,3,2,'vip'),(43,3,0,'vip'),(44,3,3,'vip'),(45,3,2,'reg'),(46,2,1,'vip'),(47,2,0,'vip'),(48,3,0,'vip'),(49,1,0,'reg'),(50,3,2,'vip');
/*!40000 ALTER TABLE `rooms1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms2`
--

DROP TABLE IF EXISTS `rooms2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms2` (
  `id` int(10) unsigned NOT NULL,
  `singleBeds` tinyint(4) NOT NULL,
  `doubleBeds` tinyint(4) NOT NULL,
  `type` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms2`
--

LOCK TABLES `rooms2` WRITE;
/*!40000 ALTER TABLE `rooms2` DISABLE KEYS */;
INSERT INTO `rooms2` VALUES (1,3,1,'reg'),(2,1,3,'reg'),(3,0,2,'vip'),(4,1,2,'reg'),(5,3,0,'vip'),(6,2,1,'vip'),(7,2,0,'vip'),(8,1,2,'reg'),(9,3,0,'vip'),(10,2,1,'vip'),(11,0,2,'reg'),(12,0,2,'vip'),(13,1,2,'vip'),(14,1,1,'reg'),(15,2,0,'vip'),(16,2,3,'vip'),(17,2,1,'vip'),(18,2,1,'vip'),(19,2,0,'vip'),(20,2,1,'reg'),(21,3,1,'reg'),(22,2,3,'reg'),(23,1,2,'vip'),(24,1,2,'vip'),(25,1,1,'reg'),(26,3,1,'vip'),(27,1,2,'vip'),(28,0,3,'reg'),(29,0,1,'reg'),(30,3,3,'vip'),(31,0,1,'vip'),(32,0,3,'vip'),(33,2,3,'reg'),(34,3,1,'vip'),(35,3,2,'vip'),(36,3,0,'reg'),(37,1,0,'vip'),(38,3,1,'vip'),(39,2,3,'vip'),(40,0,1,'reg'),(41,1,1,'reg'),(42,3,1,'reg'),(43,0,2,'reg'),(44,1,3,'reg'),(45,3,3,'reg'),(46,0,2,'vip'),(47,0,2,'vip'),(48,0,2,'vip'),(49,0,1,'reg'),(50,0,1,'vip');
/*!40000 ALTER TABLE `rooms2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms3`
--

DROP TABLE IF EXISTS `rooms3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms3` (
  `id` int(10) unsigned NOT NULL,
  `singleBeds` tinyint(4) NOT NULL,
  `doubleBeds` tinyint(4) NOT NULL,
  `type` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms3`
--

LOCK TABLES `rooms3` WRITE;
/*!40000 ALTER TABLE `rooms3` DISABLE KEYS */;
INSERT INTO `rooms3` VALUES (1,3,0,'reg'),(2,1,1,'vip'),(3,1,3,'vip'),(4,1,0,'vip'),(5,3,1,'reg'),(6,0,1,'reg'),(7,3,0,'vip'),(8,1,2,'vip'),(9,1,1,'reg'),(10,1,2,'reg'),(11,3,1,'vip'),(12,0,3,'reg'),(13,0,2,'vip'),(14,0,3,'vip'),(15,2,1,'vip'),(16,3,1,'vip'),(17,1,2,'vip'),(18,2,0,'vip'),(19,0,1,'reg'),(20,2,2,'vip'),(21,2,2,'vip'),(22,1,1,'vip'),(23,3,1,'vip'),(24,3,2,'reg'),(25,1,1,'reg'),(26,1,3,'reg'),(27,3,2,'reg'),(28,3,3,'vip'),(29,1,1,'vip'),(30,1,0,'reg'),(31,2,1,'vip'),(32,3,1,'vip'),(33,1,1,'reg'),(34,1,2,'reg'),(35,1,0,'vip'),(36,0,1,'vip'),(37,0,1,'vip'),(38,0,3,'vip'),(39,2,2,'reg'),(40,0,1,'reg'),(41,2,0,'reg'),(42,0,1,'vip'),(43,1,3,'vip'),(44,1,1,'vip'),(45,0,3,'vip'),(46,3,1,'vip'),(47,3,3,'vip'),(48,2,2,'reg'),(49,2,3,'vip'),(50,1,2,'reg');
/*!40000 ALTER TABLE `rooms3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms4`
--

DROP TABLE IF EXISTS `rooms4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms4` (
  `id` int(10) unsigned NOT NULL,
  `singleBeds` tinyint(4) NOT NULL,
  `doubleBeds` tinyint(4) NOT NULL,
  `type` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms4`
--

LOCK TABLES `rooms4` WRITE;
/*!40000 ALTER TABLE `rooms4` DISABLE KEYS */;
INSERT INTO `rooms4` VALUES (1,2,3,'reg'),(2,3,0,'vip'),(3,2,1,'vip'),(4,1,3,'reg'),(5,0,3,'reg'),(6,2,3,'reg'),(7,2,3,'reg'),(8,1,0,'reg'),(9,3,3,'reg'),(10,1,3,'vip'),(11,2,3,'reg'),(12,1,3,'vip'),(13,3,3,'vip'),(14,3,0,'reg'),(15,3,3,'reg'),(16,1,2,'vip'),(17,3,0,'vip'),(18,0,2,'vip'),(19,1,0,'reg'),(20,1,2,'vip'),(21,2,2,'reg'),(22,0,1,'vip'),(23,1,0,'vip'),(24,0,1,'vip'),(25,1,3,'reg'),(26,3,3,'vip'),(27,0,3,'reg'),(28,1,0,'reg'),(29,2,3,'vip'),(30,0,1,'reg'),(31,2,2,'vip'),(32,1,0,'reg'),(33,1,2,'reg'),(34,0,1,'vip'),(35,1,2,'reg'),(36,2,0,'vip'),(37,2,2,'reg'),(38,2,2,'vip'),(39,3,1,'vip'),(40,1,0,'vip'),(41,1,2,'vip'),(42,3,1,'vip'),(43,1,3,'reg'),(44,1,1,'vip'),(45,3,1,'vip'),(46,1,1,'reg'),(47,3,0,'vip'),(48,3,0,'reg'),(49,1,1,'reg'),(50,1,0,'reg');
/*!40000 ALTER TABLE `rooms4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms5`
--

DROP TABLE IF EXISTS `rooms5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms5` (
  `id` int(10) unsigned NOT NULL,
  `singleBeds` tinyint(4) NOT NULL,
  `doubleBeds` tinyint(4) NOT NULL,
  `type` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms5`
--

LOCK TABLES `rooms5` WRITE;
/*!40000 ALTER TABLE `rooms5` DISABLE KEYS */;
INSERT INTO `rooms5` VALUES (1,0,3,'vip'),(2,3,2,'vip'),(3,1,0,'reg'),(4,0,2,'vip'),(5,0,1,'reg'),(6,1,2,'reg'),(7,2,1,'reg'),(8,3,2,'vip'),(9,1,1,'reg'),(10,3,1,'reg'),(11,0,1,'reg'),(12,3,0,'vip'),(13,2,3,'reg'),(14,3,0,'reg'),(15,3,0,'vip'),(16,2,0,'vip'),(17,1,3,'reg'),(18,2,2,'reg'),(19,2,1,'vip'),(20,1,0,'vip'),(21,0,1,'reg'),(22,2,2,'reg'),(23,2,3,'vip'),(24,0,1,'vip'),(25,1,0,'vip'),(26,3,3,'reg'),(27,0,1,'reg'),(28,3,1,'vip'),(29,2,2,'reg'),(30,1,1,'vip'),(31,0,1,'reg'),(32,1,0,'reg'),(33,1,0,'reg'),(34,2,2,'vip'),(35,1,1,'reg'),(36,2,1,'vip'),(37,3,0,'reg'),(38,3,0,'vip'),(39,1,3,'reg'),(40,2,2,'vip'),(41,0,3,'vip'),(42,3,3,'vip'),(43,2,3,'vip'),(44,2,1,'reg'),(45,1,2,'vip'),(46,2,2,'vip'),(47,1,2,'vip'),(48,1,1,'reg'),(49,3,1,'vip'),(50,1,0,'vip');
/*!40000 ALTER TABLE `rooms5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` char(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'stelios','ntou','staff'),(2,'marios','klania','admin'),(3,'theofilos','peos','admin'),(4,'kappas','kippos','staff'),(13,'Akis','Gamatos','admin'),(23,'GiatiToIdMou','Einai23Papari','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-24 20:00:20
