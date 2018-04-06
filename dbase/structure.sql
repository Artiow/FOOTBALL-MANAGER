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
  KEY `TeamComposition_Tourney_ID_fk` (`Tourney_ID`),
  KEY `TeamComposition_Team_ID_fk` (`Team_ID`),
  CONSTRAINT `TeamComposition_Team_ID_fk` FOREIGN KEY (`Team_ID`) REFERENCES `Team` (`ID`),
  CONSTRAINT `TeamComposition_Tourney_ID_fk` FOREIGN KEY (`Tourney_ID`) REFERENCES `Tourney` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tourney`
--

DROP TABLE IF EXISTS `Tourney`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tourney` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Tourney_ID_uindex` (`ID`),
  UNIQUE KEY `Tourney_Name_uindex` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserRole_Code_uindex` (`Code`),
  UNIQUE KEY `UserRole_Name_uindex` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-06 22:45:31
