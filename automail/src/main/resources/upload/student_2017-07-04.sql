/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.17-log : Database - studentdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studentdb`;

/*Table structure for table `class` */

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `classNo` int(4) NOT NULL,
  `className` varchar(50) NOT NULL,
  PRIMARY KEY (`classNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class` */

insert  into `class`(`classNo`,`className`) values 
(1,'1班'),
(2,'2班'),
(3,'3班'),
(4,'4班');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stuNo` int(4) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(20) NOT NULL,
  `age` int(3) NOT NULL,
  `bornDate` datetime NOT NULL,
  `classNo` int(4) NOT NULL,
  PRIMARY KEY (`stuNo`),
  KEY `classNo` (`classNo`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classNo`) REFERENCES `class` (`classNo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`stuNo`,`stuName`,`age`,`bornDate`,`classNo`) values 
(6,'李明',18,'2017-05-14 00:00:57',1),
(7,'李峰',21,'2017-06-06 19:31:34',2),
(8,'李明',18,'2017-05-14 00:00:57',1),
(9,'李峰',21,'2017-06-06 19:31:34',2),
(14,'李峰',21,'2017-06-06 19:31:34',2),
(15,'李明',18,'2017-05-14 00:00:57',1),
(16,'李峰',21,'2017-06-06 19:31:34',2),
(17,'李明',18,'2017-05-14 00:00:57',1),
(18,'李峰',21,'2017-06-06 19:31:34',2),
(19,'李明',18,'2017-05-14 00:00:57',1),
(20,'陈胜',26,'2017-06-14 00:00:00',1),
(21,'桂鹏',23,'2017-06-09 00:00:00',2),
(22,'吴广',43,'2017-06-14 00:00:00',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
