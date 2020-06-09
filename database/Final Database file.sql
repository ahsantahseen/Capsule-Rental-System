-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2020 at 09:08 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ok`
--

-- --------------------------------------------------------

--
-- Table structure for table `activerental`
--

CREATE TABLE `activerental` (
  `id` int(11) NOT NULL,
  `Order_ID` varchar(255) DEFAULT NULL,
  `Capsule_ID` varchar(255) NOT NULL,
  `Customer_Name` varchar(255) NOT NULL,
  `Rental_Fee` varchar(255) NOT NULL,
  `Order_Date` varchar(255) NOT NULL,
  `Usage_Time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activerental`
--

INSERT INTO `activerental` (`id`, `Order_ID`, `Capsule_ID`, `Customer_Name`, `Rental_Fee`, `Order_Date`, `Usage_Time`) VALUES
(1, 'RC001', 'CP001', 'Ali Shafique', '5400', '2020/05/24 22:55:37', '1'),
(2, 'RC002', 'CP002', 'Ahsan Tahseen', '800', '2020/05/24 22:56:16', '0.3'),
(3, 'RC003', 'CP004', 'Shakir Chao', '15000', '2020/05/24 22:58:04', '30');

-- --------------------------------------------------------

--
-- Table structure for table `capsuleregistration`
--

CREATE TABLE `capsuleregistration` (
  `id` int(11) NOT NULL,
  `Capsule_ID` varchar(255) DEFAULT NULL,
  `Capsule_Manufacturer` varchar(255) NOT NULL,
  `Capsule_Type` varchar(255) NOT NULL,
  `Capsule_Available_for_rent` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `capsuleregistration`
--

INSERT INTO `capsuleregistration` (`id`, `Capsule_ID`, `Capsule_Manufacturer`, `Capsule_Type`, `Capsule_Available_for_rent`) VALUES
(1, 'CP001', 'Mitsubishi', 'Gaming', 'Yes '),
(2, 'CP002', 'PanaTech', 'Normal', 'Yes '),
(3, 'CP003', 'C-Tech', 'Gaming', 'No'),
(4, 'CP004', 'Mitsubishi', 'Luxry', 'Yes '),
(5, 'CP005', 'PanaTech', 'Gaming', 'Yes ');

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`username`, `password`) VALUES
('retr0', 'admin'),
('root', 'toor');

-- --------------------------------------------------------

--
-- Table structure for table `customerregistration`
--

CREATE TABLE `customerregistration` (
  `id` int(255) NOT NULL,
  `Customer_ID` varchar(255) NOT NULL,
  `Customer_Name` varchar(255) NOT NULL,
  `Customer_Cnic` varchar(255) NOT NULL,
  `Customer_Contact` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customerregistration`
--

INSERT INTO `customerregistration` (`id`, `Customer_ID`, `Customer_Name`, `Customer_Cnic`, `Customer_Contact`) VALUES
(1, 'CS001', 'Ali Shafique', '4331-334-1343', '03311384208'),
(2, 'CS002', 'Ahsan Tahseen', '4220-131-4313', '03311437809'),
(3, 'CS003', 'Hallar Khalil', '2314-431-3131', '03003290456'),
(4, 'CS004', 'Shakir Chao', '3141-134-1341', '03452342441'),
(5, 'CS005', 'Azam Ali', '5414-341-1414', '03313438446');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activerental`
--
ALTER TABLE `activerental`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `capsuleregistration`
--
ALTER TABLE `capsuleregistration`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customerregistration`
--
ALTER TABLE `customerregistration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activerental`
--
ALTER TABLE `activerental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `capsuleregistration`
--
ALTER TABLE `capsuleregistration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customerregistration`
--
ALTER TABLE `customerregistration`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
