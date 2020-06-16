-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 10, 2020 at 04:45 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Helpdesk1`
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
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `CommentID` int(11) NOT NULL,
  `Comment` varchar(100) DEFAULT NULL,
  `CommentTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `TicketID` varchar(10) NOT NULL,
  `EmpID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`CommentID`, `Comment`, `CommentTime`, `TicketID`, `EmpID`) VALUES
(1, 'Hi Admin, display issues occuring in old laptop.', '2020-06-08 09:41:50', 'TCKT002', 'EMP1'),
(2, 'Hi Employee, Will look into the matter.', '2020-06-08 09:44:15', 'TCKT002', 'admin');

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

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`FirstName`, `LastName`, `EmpID`, `Gender`, `Role`, `ManagerID`) VALUES
('Naman', 'Bhatia', 'EMP1', 'M', 'Developer', 'EMP123');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `EmpID` varchar(10) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `EmpType` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`EmpID`, `Password`, `EmpType`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN'),
('EMP1', 'ac8be4aee61f5f6e21b8c5afffb52939', 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `table1_seq`
--

CREATE TABLE `table1_seq` (
  `Tid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `table1_seq`
--

INSERT INTO `table1_seq` (`Tid`) VALUES
(2),
(3),
(4);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `UserID` varchar(10) NOT NULL,
  `UserName` varchar(15) NOT NULL,
  `TicketID` varchar(10) NOT NULL,
  `Priority` varchar(10) NOT NULL,
  `TicketApproverID` varchar(30) NOT NULL,
  `TicketStatus` varchar(15) NOT NULL,
  `TicketCatg` varchar(15) NOT NULL,
  `TicketDesc` varchar(100) NOT NULL,
  `IssueDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `ResolveDate` timestamp NULL DEFAULT NULL,
  `ResolvedBy` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`UserID`, `UserName`, `TicketID`, `Priority`, `TicketApproverID`, `TicketStatus`, `TicketCatg`, `TicketDesc`, `IssueDate`, `ResolveDate`, `ResolvedBy`) VALUES
('EMP1', 'Username', 'TCKT002', 'Medium', 'Rahul Bajaj', 'Open', 'Technical', 'Issue new Laptop', '2020-06-08 09:11:07', NULL, NULL),
('EMP1', 'Username', 'TCKT003', 'High', 'Rakesh Arora', 'Open', 'Technical', 'Server Connectivity Issue', '2020-06-08 09:13:08', NULL, NULL),
('EMP1', 'Username', 'TCKT004', 'Low', 'Raj Aryan', 'Open', 'Finance', 'Salary', '2020-06-10 14:42:11', NULL, NULL);

--
-- Triggers `ticket`
--
DELIMITER $$
CREATE TRIGGER `ticket_seq` BEFORE INSERT ON `ticket` FOR EACH ROW BEGIN
  INSERT INTO `table1_seq` VALUES (NULL);
  SET NEW.TicketID = CONCAT('TCKT', LPAD(LAST_INSERT_ID(), 3, '0'));
END
$$
DELIMITER ;

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
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`CommentID`),
  ADD KEY `comment_ibfk_1` (`TicketID`);

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
-- Indexes for table `table1_seq`
--
ALTER TABLE `table1_seq`
  ADD PRIMARY KEY (`Tid`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`TicketID`),
  ADD UNIQUE KEY `TicketID` (`TicketID`),
  ADD KEY `ticket_ibfk_1` (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `table1_seq`
--
ALTER TABLE `table1_seq`
  MODIFY `Tid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`TicketID`) REFERENCES `ticket` (`TicketID`);

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
