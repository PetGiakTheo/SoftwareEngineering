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
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `paymentType` varchar(6) DEFAULT NULL,
  `hotel` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Paparas','Paparakas','theofilos@paparas.gr','210555555090','debit',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (25,1,'2017-01-06','2017-01-13',23),(26,1,'2017-01-06','2017-01-06',23),(27,1,'2017-01-05','2017-01-03',34),(28,1,'2017-01-14','2017-01-20',23),(29,1,'2017-01-14','2017-01-27',4),(30,1,'2017-01-13','2017-01-20',3);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees1`
--

DROP TABLE IF EXISTS `employees1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees1` (
  `id` int(10) unsigned NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` char(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees1`
--

LOCK TABLES `employees1` WRITE;
/*!40000 ALTER TABLE `employees1` DISABLE KEYS */;
INSERT INTO `employees1` VALUES (1,'stelios','ntou','staff'),(2,'marios','klania','admin'),(3,'theofilos','peos','admin');
/*!40000 ALTER TABLE `employees1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `id` int(10) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `cust_id` int(10) unsigned NOT NULL,
  `room_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cust` (`cust_id`),
  KEY `fk_room` (`room_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2016-12-19','2016-12-23',1,5);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
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
  `children` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms1`
--

LOCK TABLES `rooms1` WRITE;
/*!40000 ALTER TABLE `rooms1` DISABLE KEYS */;
INSERT INTO `rooms1` VALUES (1,2,2,'reg',3),(2,0,1,'reg',0),(3,3,0,'vip',1),(4,3,1,'vip',3),(5,2,3,'vip',3),(6,2,3,'vip',3),(7,2,0,'vip',0),(8,3,3,'vip',3),(9,0,2,'reg',0),(10,1,2,'vip',3),(11,0,3,'vip',3),(12,3,0,'reg',2),(13,0,1,'vip',2),(14,1,0,'reg',2),(15,3,1,'vip',3),(16,3,3,'vip',1),(17,3,2,'vip',1),(18,2,0,'vip',2),(19,0,2,'vip',3),(20,3,2,'vip',0),(21,2,3,'vip',1),(22,0,3,'reg',1),(23,0,3,'vip',1),(24,3,2,'vip',2),(25,0,3,'reg',0),(26,1,0,'reg',3),(27,2,1,'reg',0),(28,3,0,'vip',2),(29,1,0,'reg',0),(30,2,3,'reg',0),(31,2,2,'vip',0),(32,3,2,'vip',2),(33,1,3,'reg',3),(34,1,0,'vip',0),(35,0,3,'vip',0),(36,3,0,'vip',2),(37,1,2,'reg',1),(38,1,0,'reg',3),(39,3,0,'vip',0),(40,0,3,'reg',0),(41,1,2,'vip',3),(42,3,2,'vip',0),(43,3,0,'vip',1),(44,3,3,'vip',2),(45,3,2,'reg',3),(46,2,1,'vip',0),(47,2,0,'vip',2),(48,3,0,'vip',3),(49,1,0,'reg',3),(50,1,1,'vip',1),(51,3,0,'vip',1),(52,1,0,'vip',1),(53,3,2,'reg',3),(54,1,1,'vip',3),(55,0,2,'vip',0),(56,3,0,'reg',0),(57,2,1,'vip',3),(58,1,2,'reg',3),(59,2,1,'vip',0),(60,1,0,'reg',2),(61,2,2,'reg',0),(62,1,3,'reg',0),(63,3,2,'vip',1),(64,0,1,'reg',0),(65,3,2,'vip',2),(66,1,0,'reg',0),(67,1,0,'reg',1),(68,1,1,'vip',0),(69,2,3,'vip',1),(70,1,0,'reg',0),(71,0,1,'reg',2),(72,0,2,'reg',3),(73,0,1,'vip',0),(74,0,2,'reg',1),(75,0,1,'vip',1),(76,3,2,'reg',1),(77,1,0,'reg',2),(78,0,3,'reg',3),(79,3,2,'vip',3),(80,3,1,'vip',2),(81,3,3,'reg',1),(82,2,3,'vip',2),(83,0,3,'vip',2),(84,1,2,'reg',2),(85,3,1,'reg',0),(86,0,2,'reg',2),(87,2,1,'reg',3),(88,2,3,'vip',2),(89,3,2,'vip',0),(90,1,2,'reg',2),(91,2,1,'reg',2),(92,0,2,'vip',1),(93,2,3,'reg',2),(94,2,1,'reg',1),(95,0,1,'vip',3),(96,3,1,'reg',2),(97,3,3,'vip',0),(98,2,2,'reg',3),(99,2,3,'vip',1),(100,1,2,'vip',3);
/*!40000 ALTER TABLE `rooms1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-17 19:34:55
