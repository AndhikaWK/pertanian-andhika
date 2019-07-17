-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17 Jul 2019 pada 07.01
-- Versi Server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pertanian`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_beli`
--

CREATE TABLE IF NOT EXISTS `tbl_beli` (
  `nm_barang` varchar(60) NOT NULL,
  `harga_satuan` int(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `total` varchar(30) NOT NULL,
  `uang_byr` varchar(30) NOT NULL,
  `kembalian` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_beli`
--

INSERT INTO `tbl_beli` (`nm_barang`, `harga_satuan`, `jumlah`, `total`, `uang_byr`, `kembalian`) VALUES
('CANGKUL', 2, 75000, '150000', '200000', '50000'),
('BENIH PADI ROJO LELE', 4, 120000, '480000', '500000', '20000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_kategori`
--

CREATE TABLE IF NOT EXISTS `tbl_kategori` (
  `kd_kategori` char(15) NOT NULL,
  `kd_kode` char(15) NOT NULL,
  `stock` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_produk`
--

CREATE TABLE IF NOT EXISTS `tbl_produk` (
  `kd_produk` char(15) NOT NULL,
  `kd_kategori` char(15) NOT NULL,
  `nm_produk` varchar(25) NOT NULL,
  `hrg_beli` int(15) NOT NULL,
  `hrg_jual` int(15) NOT NULL,
  `stock` int(15) NOT NULL,
  `satuan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_produk`
--

INSERT INTO `tbl_produk` (`kd_produk`, `kd_kategori`, `nm_produk`, `hrg_beli`, `hrg_jual`, `stock`, `satuan`) VALUES
('003', 'ALAT', 'GOLOK', 80000, 100000, 25, 'BUAH'),
('113', 'BENIH', 'PALAWIJA', 30000, 50000, 40, 'PACK'),
('330', 'PUPUK', 'PUPUK NPK', 125000, 140000, 7, 'KARUNG'),
('006', 'ALAT', 'GOLOK', 50000, 70000, 19, 'BUAH'),
('113', 'BENIH', 'PALAWIJA', 30000, 50000, 40, 'PACK'),
('114', 'ALAT', 'CLURIT', 30000, 45000, 30, 'BUAH'),
('115', 'ALAT', 'CANGKUL', 50000, 70000, 20, 'BUAH'),
('116', 'ALAT', 'CAPING', 10000, 15000, 40, 'BUAH'),
('331', 'BENIH', 'JAGUNG SUPER', 95000, 120000, 25, 'PACK'),
('333', 'PUPUK', 'PUPUK UREA', 120000, 150000, 10, 'KARUNG'),
('334', 'PUPUK', 'PUPUK PHONSKA', 125000, 140000, 8, 'KARUNG'),
('002', 'BENIH', 'BENIH PADI ROJO LELE', 114000, 117000, 29, 'PACK');

-- --------------------------------------------------------

--
-- Struktur dari tabel `usrlogin`
--

CREATE TABLE IF NOT EXISTS `usrlogin` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `level` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `usrlogin`
--

INSERT INTO `usrlogin` (`username`, `password`, `level`) VALUES
('admin', 'admin', NULL),
('user', '12345', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
