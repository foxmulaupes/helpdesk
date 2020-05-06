-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 01:50 PM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `helpdesk`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryID` varchar(10) NOT NULL,
  `Catg_Name` char(15) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `TicketApproverID` varchar(10) NOT NULL,
  `AssignedEmpID` varchar(10) NOT NULL,
  `No. of tickets pending` int(11) NOT NULL,
  `No. of tickets resolved` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `FirstName` varchar(10) NOT NULL,
  `LastName` varchar(10) NOT NULL,
  `EmpID` varchar(10) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Role` char(15) NOT NULL,
  `ManagerID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `EmpID` varchar(10) NOT NULL,
  `Password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `UserID` varchar(10) NOT NULL,
  `UserName` varchar(15) NOT NULL,
  `TicketID` varchar(10) NOT NULL,
  `TicketApproverID` varchar(10) NOT NULL,
  `TicketStatus` varchar(10) NOT NULL,
  `TicketCatg` varchar(15) NOT NULL,
  `TicketDesc` varchar(100) NOT NULL,
  `IssueDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `ResolveDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `ResolvedBy` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`),
  ADD UNIQUE KEY `CategoryID` (`CategoryID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmpID`),
  ADD UNIQUE KEY `EmpID` (`EmpID`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`EmpID`),
  ADD UNIQUE KEY `EmpID` (`EmpID`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`UserID`,`TicketID`),
  ADD UNIQUE KEY `UserID` (`UserID`),
  ADD UNIQUE KEY `TicketID` (`TicketID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`EmpID`) REFERENCES `login` (`EmpID`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `employee` (`EmpID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
