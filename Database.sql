-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 07 Oct 2014 om 08:28
-- Serverversie: 5.1.44
-- PHP-Versie: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: 'pt4'
--
CREATE DATABASE pt4 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE pt4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Account'
--

DROP TABLE IF EXISTS Account;
CREATE TABLE IF NOT EXISTS Account (
  accountID int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  address varchar(100) NOT NULL,
  zipcode varchar(6) NOT NULL,
  city varchar(100) NOT NULL,
  email varchar(255) NOT NULL,
  telephone varchar(11) NOT NULL,
  PRIMARY KEY (accountID),
  UNIQUE KEY username (username)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Account'
--

INSERT INTO Account (accountID, username, password, name, address, zipcode, city, email, telephone) VALUES
(1, 'mike', '098f6bcd4621d373cade4e832627b4f6', 'Mike Rooijackers', 'Aldendriel 77', '5655PH', 'Eindhoven', 'm.rooijackers@student.fontys.nl', '614920555'),
(2, 'jin', '098f6bcd4621d373cade4e832627b4f6', 'jin tran', 'laagstraat 12', '3456OJ', 'Den Bosch', 'jin.tran@student.fontys.nl', '635421111'),
(3, 'luke', '701f33b8d1366cde9cb3822256a62c01', 'Luke van der Loop', 'kerkweg 23', '5643YH', 'Best', 'luke@student.fontys.nl', '645783409'),
(4, 'baya', '1a1dc91c907325c69271ddf0c944bc72', 'Baya ', 'kerkweg 11', '5651HJ', 'Veldhoven', 'baya@student.fontys.nl', '614928766'),
(5, 'jan', '5cc64c5b1aae5970e2378b48d452ba2b', 'mike rooijackers', 'aldendriel 75', '5653ph', 'eindhoven', 'mikerooijackers@gmail.vom', '614920555'),
(6, 'klaas', '2bf816b99ff17c075fb45fecceee9795', 'klaas jan', 'aaa 66', '4612PJ', 'eindhoven', 'mike@gmail.com', '975621111');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Order'
--

DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order` (
  orderID int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  accountID int(11) DEFAULT NULL,
  PRIMARY KEY (orderID),
  KEY accountID_idxfk (accountID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Order'
--


-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Order_Photo_Product'
--

DROP TABLE IF EXISTS Order_Photo_Product;
CREATE TABLE IF NOT EXISTS Order_Photo_Product (
  orderID int(11) NOT NULL DEFAULT '0',
  photoproductID int(11) NOT NULL DEFAULT '0',
  numberOf int(11) NOT NULL,
  PRIMARY KEY (orderID,photoproductID),
  KEY photoproductID_idxfk (photoproductID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel 'Order_Photo_Product'
--


-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Order_Status'
--

DROP TABLE IF EXISTS Order_Status;
CREATE TABLE IF NOT EXISTS Order_Status (
  orderID int(11) NOT NULL DEFAULT '0',
  statusID int(11) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  PRIMARY KEY (orderID,statusID),
  KEY statusID_idxfk (statusID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel 'Order_Status'
--


-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Photo'
--

DROP TABLE IF EXISTS Photo;
CREATE TABLE IF NOT EXISTS Photo (
  photoID int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  uploadDate datetime NOT NULL,
  price decimal(10,2) NOT NULL,
  size float NOT NULL,
  height int(11) NOT NULL,
  width int(11) NOT NULL,
  PRIMARY KEY (photoID)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Photo'
--

INSERT INTO Photo (photoID, name, uploadDate, price, size, height, width) VALUES
(1, 'Foto1', '2014-09-09 14:31:43', 10.00, 12, 1080, 1920),
(2, 'Foto2', '2014-09-12 14:34:35', 5.00, 12, 1920, 1080),
(3, 'Foto3', '2014-09-13 14:34:35', 2.50, 11, 1920, 1080);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'PhotoGroup'
--

DROP TABLE IF EXISTS PhotoGroup;
CREATE TABLE IF NOT EXISTS PhotoGroup (
  photogroupID int(11) NOT NULL AUTO_INCREMENT,
  accountID int(11) NOT NULL,
  `code` varchar(16) DEFAULT NULL,
  groupName varchar(100) NOT NULL,
  isPublic tinyint(1) NOT NULL,
  parentPhotogroupID int(11) DEFAULT NULL,
  PRIMARY KEY (photogroupID),
  KEY parentPhotogroupID_idxfk (parentPhotogroupID)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Gegevens worden uitgevoerd voor tabel 'PhotoGroup'
--

INSERT INTO PhotoGroup (photogroupID, accountID, code, groupName, isPublic, parentPhotogroupID) VALUES
(1, 4, '134567', 'Test', 1, NULL),
(2, 2, '23456789', 'Test2', 0, NULL);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'PhotoGroup_Photo'
--

DROP TABLE IF EXISTS PhotoGroup_Photo;
CREATE TABLE IF NOT EXISTS PhotoGroup_Photo (
  photogroupID int(11) NOT NULL DEFAULT '0',
  photoID int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (photogroupID,photoID),
  KEY photoID_idxfk (photoID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel 'PhotoGroup_Photo'
--

INSERT INTO PhotoGroup_Photo (photogroupID, photoID) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Photo_Product'
--

DROP TABLE IF EXISTS Photo_Product;
CREATE TABLE IF NOT EXISTS Photo_Product (
  photoproductID int(11) NOT NULL AUTO_INCREMENT,
  photoID int(11) NOT NULL,
  productID int(11) NOT NULL,
  PRIMARY KEY (photoproductID),
  KEY photoID_idxfk_1 (photoID),
  KEY productID_idxfk (productID)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Photo_Product'
--

INSERT INTO Photo_Product (photoproductID, photoID, productID) VALUES
(1, 1, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Photographer'
--

DROP TABLE IF EXISTS Photographer;
CREATE TABLE IF NOT EXISTS Photographer (
  accountID int(11) NOT NULL DEFAULT '0',
  companyname varchar(100) DEFAULT NULL,
  bankaccount varchar(40) NOT NULL,
  isActive tinyint(1) NOT NULL,
  PRIMARY KEY (accountID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel 'Photographer'
--

INSERT INTO Photographer (accountID, companyname, bankaccount, isActive) VALUES
(4, 'Duikfotograaf.nl', 'INGB0681212123123', 1),
(2, NULL, 'INGB456789072372736', 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Product'
--

DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product (
  productID int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  materialprice decimal(10,2) NOT NULL,
  PRIMARY KEY (productID),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Product'
--

INSERT INTO Product (productID, name, materialprice) VALUES
(1, 'T-shirt', 10.00);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel 'Status'
--

DROP TABLE IF EXISTS Status;
CREATE TABLE IF NOT EXISTS `Status` (
  statusID int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (statusID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Gegevens worden uitgevoerd voor tabel 'Status'
--



--
-- Tabelstructuur voor tabel code_redeemed_account
--

DROP TABLE IF EXISTS code_redeemed_account;
CREATE TABLE IF NOT EXISTS code_redeemed_account (
  ID int(11) NOT NULL AUTO_INCREMENT,
  accountID int(11) NOT NULL,
  photogroupID int(11) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;