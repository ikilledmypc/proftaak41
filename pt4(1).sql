-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 13, 2015 at 12:36 PM
-- Server version: 5.5.40
-- PHP Version: 5.3.10-1ubuntu3.15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pt4`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `accountID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `zipcode` varchar(6) NOT NULL,
  `city` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(11) NOT NULL,
  PRIMARY KEY (`accountID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountID`, `username`, `password`, `name`, `address`, `zipcode`, `city`, `email`, `telephone`) VALUES
(1, 'mike', '98f6bcd4621d373cade4e832627b4f6', 'Mike Rooijackers', 'Aldendriel 77', '5655PH', 'Eindhoven', 'm.rooijackers@student.fontys.nl', '614920555'),
(2, 'jin', '98f6bcd4621d373cade4e832627b4f6', 'jin tran', 'laagstraat 12', '3456OJ', 'Den Bosch', 'jin.tran@student.fontys.nl', '635421111'),
(3, 'luke', '701f33b8d1366cde9cb3822256a62c01', 'Luke van der Loop', 'kerkweg 23', '5643YH', 'Best', 'luke@student.fontys.nl', '645783409'),
(4, 'baya', '1a1dc91c907325c69271ddf0c944bc72', 'Baya ', 'kerkweg 11', '5651HJ', 'Veldhoven', 'baya@student.fontys.nl', '614928766'),
(5, 'jan', '5cc64c5b1aae5970e2378b48d452ba2b', 'mike rooijackers', 'aldendriel 75', '5653ph', 'eindhoven', 'mikerooijackers@gmail.vom', '614920555'),
(6, 'klaas', '2bf816b99ff17c075fb45fecceee9795', 'klaas jan', 'aaa 66', '4612PJ', 'eindhoven', 'mike@gmail.com', '975621111'),
(7, 'ptest', '781df8f83e9488ddc09d34e9ac0e0930', 'tim smeets', 'akdjkdfj 21', '2331AB', 'kadjfl', 'lakjsd@alkjd.ak', '1232344543'),
(8, 'lukevdloop', '161ebd7d45089b3446ee4e0d86dbcf92', 'luke van der loop', 'staaijstraat 7', '5371PK', 'neerloon', 'lukevdloop@hotmail.com', '0655506710'),
(9, 'leoloop', '161ebd7d45089b3446ee4e0d86dbcf92', 'leo loop', 'staaijstraat 7', '5371PK', 'neerloon', 'leoloop@iets.nl', '0486416328');

-- --------------------------------------------------------

--
-- Table structure for table `code_redeemed_account`
--

CREATE TABLE IF NOT EXISTS `code_redeemed_account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `accountID` int(11) NOT NULL,
  `photogroupID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `accountID` (`accountID`),
  KEY `photogroupID` (`photogroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `code_redeemed_account`
--

INSERT INTO `code_redeemed_account` (`ID`, `accountID`, `photogroupID`) VALUES
(1, 7, 5),
(2, 8, 6),
(3, 9, 6);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `accountID` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `accountID_idxfk` (`accountID`),
  KEY `status` (`status`),
  KEY `accountID` (`accountID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`orderID`, `date`, `accountID`, `status`) VALUES
(1, '2014-12-08 09:23:26', 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_photo_product`
--

CREATE TABLE IF NOT EXISTS `order_photo_product` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `orderID` int(11) NOT NULL DEFAULT '0',
  `photoID` int(20) NOT NULL,
  `numberOf` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `sepia` tinyint(4) NOT NULL,
  `blackwhite` tinyint(4) NOT NULL,
  `cropx` bigint(20) DEFAULT NULL,
  `cropy` bigint(20) DEFAULT NULL,
  `cropwidth` bigint(20) DEFAULT NULL,
  `cropheight` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_photo_product_ibfk_1` (`orderID`),
  KEY `order_photo_product_ibfk_2` (`productID`),
  KEY `order_photo_product_ibfk_3` (`photoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

CREATE TABLE IF NOT EXISTS `photo` (
  `photoID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `uploadDate` datetime NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `size` float NOT NULL,
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`photoID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `photo`
--

INSERT INTO `photo` (`photoID`, `name`, `uploadDate`, `price`, `size`, `height`, `width`) VALUES
(4, 'KxUHaU0.jpg', '2014-12-04 19:41:28', 4.00, 12, 1080, 1920),
(5, 'KxUHaU0.jpg', '2014-12-04 19:44:46', 3.00, 12, 1080, 1920),
(6, 'KxUHaU0 - Copy (2).jpg', '2014-12-04 19:44:46', 3.00, 12, 1080, 1920),
(7, 'KxUHaU0 - Copy.jpg', '2014-12-04 19:44:46', 3.00, 12, 1080, 1920),
(8, '271655.jpg', '2014-12-08 22:20:48', 16.00, 12, 1156, 1762),
(9, '10295418_10154180176300556_2134959981199785255_o.jpg', '2014-12-08 22:20:51', 16.00, 12, 1080, 1920),
(10, '1000px-Irelia_NightbladeSkin_Ch.jpg', '2014-12-08 22:20:52', 16.00, 12, 590, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `photographer`
--

CREATE TABLE IF NOT EXISTS `photographer` (
  `accountID` int(11) NOT NULL DEFAULT '0',
  `companyname` varchar(100) DEFAULT NULL,
  `bankaccount` varchar(40) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `defaultPricePhoto` decimal(10,0) NOT NULL,
  PRIMARY KEY (`accountID`),
  KEY `accountID` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `photographer`
--

INSERT INTO `photographer` (`accountID`, `companyname`, `bankaccount`, `isActive`, `defaultPricePhoto`) VALUES
(2, 'test.nl', 'INGB456789072372736', 0, 0),
(4, 'Duikfotograaf.nl', 'INGB0681212123123', 1, 0),
(7, 'tesinc', '2343451352314', 1, 0),
(8, 'Beste bedrijfvan nederland', 'ahsrtuyfkilg;hiljjh', 1, 16);

-- --------------------------------------------------------

--
-- Table structure for table `photogroup`
--

CREATE TABLE IF NOT EXISTS `photogroup` (
  `photogroupID` int(11) NOT NULL AUTO_INCREMENT,
  `accountID` int(11) NOT NULL,
  `code` varchar(16) DEFAULT NULL,
  `groupName` varchar(100) NOT NULL,
  `isPublic` tinyint(1) NOT NULL,
  `parentPhotogroupID` int(11) DEFAULT NULL,
  PRIMARY KEY (`photogroupID`),
  KEY `parentPhotogroupID_idxfk` (`parentPhotogroupID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `photogroup`
--

INSERT INTO `photogroup` (`photogroupID`, `accountID`, `code`, `groupName`, `isPublic`, `parentPhotogroupID`) VALUES
(3, 7, '22489', 'baasd', 1, NULL),
(4, 7, 'c0c0a', 'rfghj', 1, NULL),
(5, 7, 'cc061', 'asdfsadf', 1, NULL),
(6, 8, 'ea876', 'Testfotos', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `photogroup_photo`
--

CREATE TABLE IF NOT EXISTS `photogroup_photo` (
  `photogroupID` int(11) NOT NULL DEFAULT '0',
  `photoID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`photogroupID`,`photoID`),
  KEY `photoID_idxfk` (`photoID`),
  KEY `photogroupID` (`photogroupID`),
  KEY `photoID` (`photoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `photogroup_photo`
--

INSERT INTO `photogroup_photo` (`photogroupID`, `photoID`) VALUES
(3, 4),
(5, 5),
(5, 6),
(5, 7),
(6, 8),
(6, 9),
(6, 10);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `productID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `materialprice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`productID`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `name`, `materialprice`) VALUES
(1, 'T-shirt', 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `statusID` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`statusID`, `status`) VALUES
(1, 'Accepted'),
(2, 'in behandeling'),
(3, 'betaald'),
(4, 'afgehandeld');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `code_redeemed_account`
--
ALTER TABLE `code_redeemed_account`
  ADD CONSTRAINT `account_accountPhotogroup` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`),
  ADD CONSTRAINT `photogroup_photogrouptAccount` FOREIGN KEY (`photogroupID`) REFERENCES `photogroup` (`photogroupID`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`statusID`),
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`);

--
-- Constraints for table `order_photo_product`
--
ALTER TABLE `order_photo_product`
  ADD CONSTRAINT `order_photo_product_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`),
  ADD CONSTRAINT `order_photo_product_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  ADD CONSTRAINT `order_photo_product_ibfk_3` FOREIGN KEY (`photoID`) REFERENCES `photo` (`photoID`);

--
-- Constraints for table `photographer`
--
ALTER TABLE `photographer`
  ADD CONSTRAINT `photographer_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`);

--
-- Constraints for table `photogroup_photo`
--
ALTER TABLE `photogroup_photo`
  ADD CONSTRAINT `photogroup_photo_ibfk_1` FOREIGN KEY (`photogroupID`) REFERENCES `photogroup` (`photogroupID`),
  ADD CONSTRAINT `photogroup_photo_ibfk_2` FOREIGN KEY (`photoID`) REFERENCES `photo` (`photoID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
