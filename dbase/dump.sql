-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sportsportal
-- ------------------------------------------------------
-- Server version	5.6.34

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
-- Table structure for table `Playground`
--

DROP TABLE IF EXISTS `Playground`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Playground` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Playground_ID_uindex` (`ID`),
  UNIQUE KEY `Playground_Name_uindex` (`Name`),
  UNIQUE KEY `Playground_Address_uindex` (`Address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Playground`
--

LOCK TABLES `Playground` WRITE;
/*!40000 ALTER TABLE `Playground` DISABLE KEYS */;
/*!40000 ALTER TABLE `Playground` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlaygroundSpec`
--

DROP TABLE IF EXISTS `PlaygroundSpec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PlaygroundSpec` (
  `Playground_ID` int(11) NOT NULL,
  `Sport_ID` int(11) NOT NULL,
  PRIMARY KEY (`Playground_ID`,`Sport_ID`),
  KEY `fk_Playground_has_Sport_Sport1_idx` (`Sport_ID`),
  KEY `fk_Playground_has_Sport_Playground1_idx` (`Playground_ID`),
  CONSTRAINT `fk_Playground_has_Sport_Playground1` FOREIGN KEY (`Playground_ID`) REFERENCES `Playground` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Playground_has_Sport_Sport1` FOREIGN KEY (`Sport_ID`) REFERENCES `Sport` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaygroundSpec`
--

LOCK TABLES `PlaygroundSpec` WRITE;
/*!40000 ALTER TABLE `PlaygroundSpec` DISABLE KEYS */;
/*!40000 ALTER TABLE `PlaygroundSpec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sport`
--

DROP TABLE IF EXISTS `Sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sport` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Sport_ID_uindex` (`ID`),
  UNIQUE KEY `Sport_Name_uindex` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sport`
--

LOCK TABLES `Sport` WRITE;
/*!40000 ALTER TABLE `Sport` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Team`
--

DROP TABLE IF EXISTS `Team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Team` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` char(45) NOT NULL,
  `Captain_ID` int(11) NOT NULL,
  `Status_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Team_ID_uindex` (`ID`),
  UNIQUE KEY `Team_Name_uindex` (`Name`),
  KEY `Team_User_ID_fk` (`Captain_ID`),
  KEY `Team_TeamStatus_ID_fk` (`Status_ID`),
  CONSTRAINT `Team_TeamStatus_ID_fk` FOREIGN KEY (`Status_ID`) REFERENCES `TeamStatus` (`ID`),
  CONSTRAINT `Team_User_ID_fk` FOREIGN KEY (`Captain_ID`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Team`
--

LOCK TABLES `Team` WRITE;
/*!40000 ALTER TABLE `Team` DISABLE KEYS */;
INSERT INTO `Team` VALUES (1,'Good Team',1,2),(2,'Better Team',1,2),(3,'The Best Team',1,1),(4,'Fuck This Shit',1,1),(5,'Охуительная Команда Блядь',5,2),(9,'Нижние Подзалупки',5,1);
/*!40000 ALTER TABLE `Team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamComposition`
--

DROP TABLE IF EXISTS `TeamComposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamComposition` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Team_ID` int(11) NOT NULL,
  `Tourney_ID` int(11) NOT NULL,
  `TeamName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ShiftBalance` int(11) NOT NULL DEFAULT '3',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TeamComposition_ID_uindex` (`ID`),
  UNIQUE KEY `TeamComposition_Team_ID_Tourney_ID_uindex` (`Team_ID`,`Tourney_ID`),
  KEY `TeamComposition_Tourney_ID_fk` (`Tourney_ID`),
  KEY `TeamComposition_Team_ID_fk` (`Team_ID`),
  CONSTRAINT `TeamComposition_Team_ID_fk` FOREIGN KEY (`Team_ID`) REFERENCES `Team` (`ID`),
  CONSTRAINT `TeamComposition_Tourney_ID_fk` FOREIGN KEY (`Tourney_ID`) REFERENCES `Tourney` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamComposition`
--

LOCK TABLES `TeamComposition` WRITE;
/*!40000 ALTER TABLE `TeamComposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamComposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamMembershipForPlayer`
--

DROP TABLE IF EXISTS `TeamMembershipForPlayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMembershipForPlayer` (
  `TeamPlayer_ID` int(11) NOT NULL,
  `TeamComposition_ID` int(11) NOT NULL,
  PRIMARY KEY (`TeamPlayer_ID`,`TeamComposition_ID`),
  KEY `TeamMembership_TeamComposition_ID_fk` (`TeamComposition_ID`),
  KEY `TeamMembership_TeamPlayer_ID_index` (`TeamPlayer_ID`),
  CONSTRAINT `TeamMembership_TeamComposition_ID_fk` FOREIGN KEY (`TeamComposition_ID`) REFERENCES `TeamComposition` (`ID`),
  CONSTRAINT `TeamMembership_TeamPlayer_ID_fk` FOREIGN KEY (`TeamPlayer_ID`) REFERENCES `TeamPlayer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMembershipForPlayer`
--

LOCK TABLES `TeamMembershipForPlayer` WRITE;
/*!40000 ALTER TABLE `TeamMembershipForPlayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMembershipForPlayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamMembershipForUser`
--

DROP TABLE IF EXISTS `TeamMembershipForUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamMembershipForUser` (
  `TeamUser_ID` int(11) NOT NULL,
  `TeamComposition_ID` int(11) NOT NULL,
  PRIMARY KEY (`TeamUser_ID`,`TeamComposition_ID`),
  KEY `TeamMembershipForUser_TeamComposition_ID_fk` (`TeamComposition_ID`),
  KEY `TeamMembershipForUser_TeamUser_ID_index` (`TeamUser_ID`),
  CONSTRAINT `TeamMembershipForUser_TeamComposition_ID_fk` FOREIGN KEY (`TeamComposition_ID`) REFERENCES `TeamComposition` (`ID`),
  CONSTRAINT `TeamMembershipForUser_User_ID_fk` FOREIGN KEY (`TeamUser_ID`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamMembershipForUser`
--

LOCK TABLES `TeamMembershipForUser` WRITE;
/*!40000 ALTER TABLE `TeamMembershipForUser` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamMembershipForUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamPic`
--

DROP TABLE IF EXISTS `TeamPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamPic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Team_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TeamPic_ID_uindex` (`ID`),
  KEY `TeamPic_Team_ID_fk` (`Team_ID`),
  CONSTRAINT `TeamPic_Team_ID_fk` FOREIGN KEY (`Team_ID`) REFERENCES `Team` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamPic`
--

LOCK TABLES `TeamPic` WRITE;
/*!40000 ALTER TABLE `TeamPic` DISABLE KEYS */;
/*!40000 ALTER TABLE `TeamPic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamPlayer`
--

DROP TABLE IF EXISTS `TeamPlayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamPlayer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Surname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Patronymic` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Phone` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Player_ID_uindex` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamPlayer`
--

LOCK TABLES `TeamPlayer` WRITE;
/*!40000 ALTER TABLE `TeamPlayer` DISABLE KEYS */;
INSERT INTO `TeamPlayer` VALUES (3,'test2','test2','test2',NULL),(4,'test2','test2','test2',NULL);
/*!40000 ALTER TABLE `TeamPlayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeamStatus`
--

DROP TABLE IF EXISTS `TeamStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TeamStatus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` char(45) NOT NULL,
  `Description` char(90) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TeamStatus_Code_uindex` (`Code`),
  UNIQUE KEY `TeamStatus_ID_uindex` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeamStatus`
--

LOCK TABLES `TeamStatus` WRITE;
/*!40000 ALTER TABLE `TeamStatus` DISABLE KEYS */;
INSERT INTO `TeamStatus` VALUES (1,'TEAM_UNCONFIRMED','Команда ожидает подтверждения'),(2,'TEAM_CONFIRMED','Команда подтверждена'),(3,'TEAM_REJECTED','Команда отклонена'),(4,'TEAM_INVITED','Команда приглашена в турнир'),(5,'TEAM_INVOLVED','Команда участвует в турнире');
/*!40000 ALTER TABLE `TeamStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tourney`
--

DROP TABLE IF EXISTS `Tourney`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tourney` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Status_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Tourney_ID_uindex` (`ID`),
  UNIQUE KEY `Tourney_Name_uindex` (`Name`),
  KEY `Tourney_TourneyStatus_ID_fk` (`Status_ID`),
  CONSTRAINT `Tourney_TourneyStatus_ID_fk` FOREIGN KEY (`Status_ID`) REFERENCES `TourneyStatus` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tourney`
--

LOCK TABLES `Tourney` WRITE;
/*!40000 ALTER TABLE `Tourney` DISABLE KEYS */;
INSERT INTO `Tourney` VALUES (1,'Охуительно Важный Турнир',1),(2,'Турнир Чуть Поважнее',1);
/*!40000 ALTER TABLE `Tourney` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TourneyStatus`
--

DROP TABLE IF EXISTS `TourneyStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TourneyStatus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(90) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TourneyStatus_ID_uindex` (`ID`),
  UNIQUE KEY `TourneyStatus_Code_uindex` (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TourneyStatus`
--

LOCK TABLES `TourneyStatus` WRITE;
/*!40000 ALTER TABLE `TourneyStatus` DISABLE KEYS */;
INSERT INTO `TourneyStatus` VALUES (1,'TOURNEY_FORMED','Турнир набирает команды'),(2,'TOURNEY_READY','Команды набраны');
/*!40000 ALTER TABLE `TourneyStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserRole_ID` int(11) NOT NULL,
  `Login` varchar(45) NOT NULL,
  `Password` varchar(125) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `Patronymic` varchar(45) DEFAULT NULL,
  `EMail` varchar(45) NOT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `User_ID_uindex` (`ID`),
  UNIQUE KEY `User_Email_uindex` (`EMail`),
  UNIQUE KEY `User_Login_uindex` (`Login`),
  KEY `User_UserRole_ID_fk` (`UserRole_ID`),
  CONSTRAINT `User_UserRole_ID_fk` FOREIGN KEY (`UserRole_ID`) REFERENCES `UserRole` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,1,'artiow','$2a$10$X0QUCiRADuOthPN9MWwU3udzOBq21vCUt8QTOP8jXXAd.RTLJbUJO','Артем','Намеднев','Александрович','namednev_a@mail.ru','9204251258'),(5,2,'login','$2a$10$hiCEvfA8G/kfKG7qJLpTD.8/A7GUxCtfLlDdNaiQOHaEo2QsVz4a.','Name','Surname','Patronymic','email',NULL),(10,4,'test1','$2a$10$n.Lx3IBkcniRkMM4dwk1Fe4OZb53HMtNTsC5KDFt4o32xa.3p7OCq','test1','test1','test1','test1','test1'),(11,4,'test2','$2a$10$n.Lx3IBkcniRkMM4dwk1Fe4OZb53HMtNTsC5KDFt4o32xa.3p7OCq','test2','test2','test2','test2','test2'),(12,4,'test3','$2a$10$n.Lx3IBkcniRkMM4dwk1Fe4OZb53HMtNTsC5KDFt4o32xa.3p7OCq','test3','test3','test3','test3','test3');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPic`
--

DROP TABLE IF EXISTS `UserPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserPic_ID_uindex` (`ID`),
  KEY `UserPic_User_ID_fk` (`User_ID`),
  CONSTRAINT `UserPic_User_ID_fk` FOREIGN KEY (`User_ID`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPic`
--

LOCK TABLES `UserPic` WRITE;
/*!40000 ALTER TABLE `UserPic` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserPic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(45) NOT NULL,
  `Description` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserRole_Code_uindex` (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRole`
--

LOCK TABLES `UserRole` WRITE;
/*!40000 ALTER TABLE `UserRole` DISABLE KEYS */;
INSERT INTO `UserRole` VALUES (1,'ROLE_ADMIN','Администратор'),(2,'ROLE_USER','Пользователь'),(3,'ROLE_TEAMLEAD','Лидер команды (нужна ли?)'),(4,'ROLE_UNCONFIRMED','Неподтвержденный пользователь'),(5,'ROLE_REJECTED','Недостоверный пользоваетель');
/*!40000 ALTER TABLE `UserRole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-08  3:01:39
