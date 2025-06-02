-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2025 at 07:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartkitchen`
--

-- --------------------------------------------------------

--
-- Table structure for table `stok_bahan`
--

CREATE TABLE `stok_bahan` (
  `id` int(11) NOT NULL,
  `nama_bahan` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `kadaluarsa` date NOT NULL,
  `jenis_bahan` varchar(255) NOT NULL,
  `satuan` varchar(100) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `tanggal_masuk` date DEFAULT current_timestamp(),
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stok_bahan`
--

INSERT INTO `stok_bahan` (`id`, `nama_bahan`, `jumlah`, `kadaluarsa`, `jenis_bahan`, `satuan`, `lokasi`, `tanggal_masuk`, `keterangan`) VALUES
(8, 'AYam', 5, '2025-06-06', 'Contoh : Sayur. Buah, Dll', 'as', 'RUmah', '2025-06-01', 'qweqweqweqweqwe'),
(9, 'Kangkung', 15, '2025-06-06', 'Sayur', 'Batang', 'Kulkas', '2025-06-01', 'Ini makanan ya'),
(10, 'Kangkung', 13, '2025-06-06', 'Sayur', 'Batang', 'Kulkas', '2025-06-01', 'Ini makanan ya');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stok_bahan`
--
ALTER TABLE `stok_bahan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stok_bahan`
--
ALTER TABLE `stok_bahan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
