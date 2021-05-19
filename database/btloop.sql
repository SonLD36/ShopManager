-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2021 at 08:26 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `btloop`
--
CREATE DATABASE IF NOT EXISTS `btloop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `btloop`;

-- --------------------------------------------------------

--
-- Table structure for table `dienthoai`
--

CREATE TABLE `dienthoai` (
  `MaSP` int(11) NOT NULL,
  `TenSP` varchar(50) NOT NULL,
  `GiaBR` double NOT NULL,
  `GiaNV` double NOT NULL,
  `HangSX` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `TGBH` int(11) NOT NULL,
  `ChongNuoc` varchar(10) NOT NULL,
  `DPGCamera` varchar(50) NOT NULL,
  `KTMH` varchar(50) NOT NULL,
  `ThoiLuongPin` double NOT NULL,
  `SLSP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dienthoai`
--

INSERT INTO `dienthoai` (`MaSP`, `TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `ChongNuoc`, `DPGCamera`, `KTMH`, `ThoiLuongPin`, `SLSP`) VALUES
(1, 'iPhone 13 Promax', 2000, 1505.2, 'Apple', 'SmartPhone', 36, 'Co', '3 camera 12 MP', 'OLED,6.7\",Super Retina XDR', 7, 200),
(2, 'Realme 9', 500, 324.6, 'Xiaomi', 'SmartPhone', 12, 'Khong', 'Cam 108 MP ', 'Super AMOLED, 6.4\", Full HD+', 4, 500),
(3, 'OPPO Reno5', 360, 300, 'OPPO', 'SmartPhone', 12, 'Khong', '16MP', 'AMOLED,6.43\",Full HD+', 4310, 1250);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `NgayTaoHoaDon` timestamp NOT NULL DEFAULT current_timestamp(),
  `LoiNhuan` double NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `NgayTaoHoaDon`, `LoiNhuan`, `SoLuong`) VALUES
(4, '2021-05-11 11:09:08', 1125.6, 5),
(5, '2021-05-11 11:11:27', 455.4, 3),
(6, '2021-05-11 11:13:39', 630.8, 4),
(7, '2021-05-11 11:23:54', 1827.7999999999997, 10),
(8, '2021-05-11 11:25:28', 2774.7999999999997, 12),
(9, '2021-05-11 11:41:41', 2968.7999999999997, 6),
(10, '2021-05-11 11:42:52', 60, 1),
(11, '2021-05-11 11:42:57', 360, 6);

-- --------------------------------------------------------

--
-- Table structure for table `laptop`
--

CREATE TABLE `laptop` (
  `MaSP` int(11) NOT NULL,
  `TenSP` varchar(50) NOT NULL,
  `GiaBR` double NOT NULL,
  `GiaNV` double NOT NULL,
  `HangSX` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `TGBH` int(11) NOT NULL,
  `Ram` varchar(50) NOT NULL,
  `ViXuLy` varchar(50) NOT NULL,
  `OCung` varchar(50) NOT NULL,
  `KL` double NOT NULL,
  `SLSP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `laptop`
--

INSERT INTO `laptop` (`MaSP`, `TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `Ram`, `ViXuLy`, `OCung`, `KL`, `SLSP`) VALUES
(5, 'Laptop Lenovo ThinkBook 15IIL', 600, 400, 'Lenovo', 'ThinkBook', 36, '4 GB, DDR4 (1 khe), 2666 MHz', 'Intel Core i3 Ice Lake, 1005G1, 1.20 GHz', 'SSD 512GB NVMe PCIe, HDD SATA', 1.8, 200),
(6, 'Laptop Acer Aspire 5 A514', 700, 504.6, 'Acer', 'Aspire', 24, '4 GB, DDR4 (On board +1 khe), 2666 MHz', 'Intel Core i3 Tiger Lake, 1115G4, 3.00 GHz', 'SSD 512GB NVMe PCIe, HDD SATA', 1.46, 150),
(7, 'Laptop Apple MacBook Air 2017', 1000, 800, 'Apple', 'MQD32SA/A', 12, '8 GB, DDR3L (On board),1600 MHz', 'Intel Core i5 Broadwell, 1.80 GHz', 'SSD 128 GB', 1.35, 1000),
(8, 'Laptop LG Gram 16 2021', 3000, 2457, 'LG', '16Z90P-G.AH75A5', 36, '16 GB, LPDDR4X (On board), 4266 MHz', 'Intel Core i7 Tiger Lake, 1165G7, 2.80 GHz', 'SSD 512GB NVMe PCIe, SSD M.2 PCIe', 1.19, 50);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dienthoai`
--
ALTER TABLE `dienthoai`
  ADD PRIMARY KEY (`MaSP`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`);

--
-- Indexes for table `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`MaSP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dienthoai`
--
ALTER TABLE `dienthoai`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `laptop`
--
ALTER TABLE `laptop`
  MODIFY `MaSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
