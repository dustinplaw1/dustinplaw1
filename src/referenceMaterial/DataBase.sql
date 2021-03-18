-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 14, 2021 at 04:00 PM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toollib`
--

-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract` (
  `contract_id` char(20) NOT NULL DEFAULT '',
  `employee_id` char(30) DEFAULT NULL,
  `tool_id` char(20) DEFAULT NULL,
  `date_borrow` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `employee_id` (`employee_id`),
  KEY `tool_id` (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `dept_id` char(20) NOT NULL,
  `dept_name` varchar(20) NOT NULL,
  `dept_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` char(30) NOT NULL,
  `employee_name` varchar(50) NOT NULL,
  `dept_id` char(20) DEFAULT NULL,
  `employee_phoneno` char(15) DEFAULT NULL,
  `emp_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `dept_id` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `empid` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tool`
--

DROP TABLE IF EXISTS `tool`;
CREATE TABLE IF NOT EXISTS `tool` (
  `tool_id` char(20) NOT NULL,
  `tool_name` char(50) NOT NULL,
  `tool_description` char(30) DEFAULT NULL,
  PRIMARY KEY (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tool`
--

INSERT INTO `tool` (`tool_id`, `tool_name`, `tool_description`) VALUES
('t001', 'Lawn Mower', 'Used to cut grass'),
('t002', 'Screwdriver', 'Used to screw');

-- --------------------------------------------------------

--
-- Table structure for table `tool_status`
--

DROP TABLE IF EXISTS `tool_status`;
CREATE TABLE IF NOT EXISTS `tool_status` (
  `job_id` char(20) DEFAULT NULL,
  `employee_id` char(30) DEFAULT NULL,
  `tool_id` char(20) DEFAULT NULL,
  `status` char(20) DEFAULT NULL,
  KEY `job_id` (`job_id`),
  KEY `employee_id` (`employee_id`),
  KEY `tool_id` (`tool_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `contract_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `contract_ibfk_2` FOREIGN KEY (`tool_id`) REFERENCES `tool` (`tool_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`);

--
-- Constraints for table `tool_status`
--
ALTER TABLE `tool_status`
  ADD CONSTRAINT `tool_status_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `contract` (`contract_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tool_status_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tool_status_ibfk_3` FOREIGN KEY (`tool_id`) REFERENCES `tool` (`tool_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
