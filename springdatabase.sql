-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2018 at 09:39 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `approval_request`
--

CREATE TABLE `approval_request` (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `motivation` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_skill_level_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval_request`
--

INSERT INTO `approval_request` (`id`, `creation_time`, `motivation`, `status`, `user_skill_level_id`) VALUES
(20, '2018-10-06 09:30:47', 'I am better at it :)', 1, 19),
(28, '2018-10-06 13:14:58', 'hehehhdsfjksdhfkjsdhkjfsdhkjfhsdjkfhsdjkhfjksdhfkjsdhfjsdkhfjdskhfsjdkhfjskdhfsdjkhfsdkjfhsdkjhfsdkjhf', 1, 27),
(36, '2018-10-06 13:17:19', 'dfgdfgdfgdfgdfg', 1, 35),
(44, '2018-10-07 14:59:03', ':)', 1, 43),
(70, '2018-10-08 21:35:12', 'Me good :)))', 0, 69),
(86, '2018-10-09 00:02:34', 'please :))', 0, 85),
(106, '2018-10-09 21:21:55', 'pleaseeeee', 2, 105),
(122, '2018-10-09 21:29:07', 'trtrtrt', 2, 121),
(138, '2018-10-09 23:04:54', 'wwwwww', 2, 137),
(158, '2018-10-11 20:11:43', 'qwer', 2, 157),
(177, '2018-10-11 20:30:41', '445454', 0, 176),
(193, '2018-10-15 20:05:17', 'qwerqwer', 2, 192),
(227, '2018-10-16 21:57:21', 'rerer', 2, 226),
(250, '2018-10-16 22:12:19', '147', 2, 249),
(269, '2018-10-17 22:03:41', '565656', 0, 268),
(293, '2018-10-18 20:05:53', 'eeee', 2, 292),
(320, '2018-10-18 20:48:21', 'heyooooo', 0, 319),
(343, '2018-10-21 15:16:43', 'yoyoyo', 2, 342);

-- --------------------------------------------------------

--
-- Table structure for table `approval_request_approvers`
--

CREATE TABLE `approval_request_approvers` (
  `approval_request_id` bigint(20) NOT NULL,
  `approvers_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval_request_approvers`
--

INSERT INTO `approval_request_approvers` (`approval_request_id`, `approvers_id`) VALUES
(106, 119),
(122, 135),
(138, 151),
(44, 153),
(158, 171),
(158, 172),
(193, 206),
(193, 207),
(227, 245),
(227, 246),
(269, 287),
(293, 311),
(293, 312),
(250, 315),
(250, 316),
(343, 369),
(343, 370);

-- --------------------------------------------------------

--
-- Table structure for table `approval_request_disapprovers`
--

CREATE TABLE `approval_request_disapprovers` (
  `approval_request_id` bigint(20) NOT NULL,
  `disapprovers_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval_request_disapprovers`
--

INSERT INTO `approval_request_disapprovers` (`approval_request_id`, `disapprovers_id`) VALUES
(70, 83),
(86, 103),
(177, 190),
(269, 288),
(320, 338);

-- --------------------------------------------------------

--
-- Table structure for table `approval_request_request_notifications`
--

CREATE TABLE `approval_request_request_notifications` (
  `approval_request_id` bigint(20) NOT NULL,
  `request_notifications_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval_request_request_notifications`
--

INSERT INTO `approval_request_request_notifications` (`approval_request_id`, `request_notifications_id`) VALUES
(20, 21),
(20, 22),
(20, 23),
(20, 24),
(20, 25),
(20, 26),
(28, 29),
(28, 30),
(28, 31),
(28, 32),
(28, 33),
(28, 34),
(36, 37),
(36, 38),
(36, 39),
(36, 40),
(36, 41),
(36, 42),
(44, 45),
(44, 46),
(44, 47),
(44, 48),
(44, 49),
(44, 50),
(70, 84),
(86, 104),
(106, 120),
(122, 136),
(138, 152),
(158, 173),
(177, 191),
(193, 208),
(227, 248),
(250, 318),
(269, 289),
(293, 314),
(320, 339),
(343, 372);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `division_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`, `division_id`) VALUES
(1, 'Swedish channels', 1),
(2, 'Baltic channels', 2);

-- --------------------------------------------------------

--
-- Table structure for table `division`
--

CREATE TABLE `division` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `division`
--

INSERT INTO `division` (`id`, `name`) VALUES
(1, 'Swedish'),
(2, 'Baltic');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379),
(379);

-- --------------------------------------------------------

--
-- Table structure for table `news_feed`
--

CREATE TABLE `news_feed` (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `skill_id` bigint(20) DEFAULT NULL,
  `skill_level_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news_feed`
--

INSERT INTO `news_feed` (`id`, `creation_time`, `type`, `user_id`, `skill_id`, `skill_level_id`) VALUES
(210, '2018-10-16 19:43:56', 0, 211, NULL, NULL),
(212, '2018-10-16 19:54:02', 0, 213, NULL, NULL),
(215, '2018-10-16 19:55:47', 0, 214, NULL, NULL),
(217, '2018-10-16 20:04:02', 0, 216, NULL, NULL),
(247, '2018-10-16 21:57:42', 1, 53, 7, 3),
(313, '2018-10-18 20:38:58', 1, 53, 10, 2),
(317, '2018-10-18 20:39:45', 1, 53, 7, 4),
(378, '2018-11-03 11:44:01', 0, 377, NULL, NULL),
(371, '2018-10-28 10:43:52', 1, 53, 154, 2);

-- --------------------------------------------------------

--
-- Table structure for table `request_notification`
--

CREATE TABLE `request_notification` (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `approval_request_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request_notification`
--

INSERT INTO `request_notification` (`id`, `creation_time`, `receiver_id`, `status`, `approval_request_id`) VALUES
(21, '2018-10-06 09:30:47', 1, 3, 20),
(22, '2018-10-06 09:30:47', 2, 3, 20),
(23, '2018-10-06 09:30:47', 3, 3, 20),
(24, '2018-10-06 09:30:47', 4, 3, 20),
(25, '2018-10-06 09:30:47', 5, 3, 20),
(26, '2018-10-06 09:30:47', 16, 3, 20),
(29, '2018-10-06 13:14:58', 1, 3, 28),
(30, '2018-10-06 13:14:58', 2, 3, 28),
(31, '2018-10-06 13:14:58', 3, 3, 28),
(32, '2018-10-06 13:14:58', 4, 3, 28),
(33, '2018-10-06 13:14:58', 5, 3, 28),
(34, '2018-10-06 13:14:58', 16, 3, 28),
(37, '2018-10-06 13:17:19', 1, 3, 36),
(38, '2018-10-06 13:17:19', 2, 3, 36),
(39, '2018-10-06 13:17:19', 3, 3, 36),
(40, '2018-10-06 13:17:19', 4, 3, 36),
(41, '2018-10-06 13:17:19', 5, 3, 36),
(42, '2018-10-06 13:17:19', 16, 3, 36),
(45, '2018-10-07 14:59:03', 1, 3, 44),
(46, '2018-10-07 14:59:03', 2, 3, 44),
(47, '2018-10-07 14:59:03', 3, 3, 44),
(48, '2018-10-07 14:59:03', 4, 3, 44),
(49, '2018-10-07 14:59:03', 5, 3, 44),
(50, '2018-10-07 14:59:03', 6, 2, 44),
(71, '2018-10-08 21:35:12', 1, 4, 70),
(72, '2018-10-08 21:35:12', 2, 4, 70),
(73, '2018-10-08 21:35:12', 3, 4, 70),
(74, '2018-10-08 21:35:12', 4, 4, 70),
(75, '2018-10-08 21:35:12', 5, 4, 70),
(76, '2018-10-08 21:35:12', 6, 4, 70),
(77, '2018-10-08 21:35:12', 16, 4, 70),
(78, '2018-10-08 21:35:12', 51, 4, 70),
(79, '2018-10-08 21:35:12', 52, 4, 70),
(80, '2018-10-08 21:35:12', 53, 4, 70),
(81, '2018-10-08 21:35:12', 54, 4, 70),
(82, '2018-10-08 21:35:12', 55, 4, 70),
(84, '2018-10-08 23:53:13', 68, 3, 70),
(87, '2018-10-09 00:02:34', 1, 4, 86),
(88, '2018-10-09 00:02:34', 2, 4, 86),
(89, '2018-10-09 00:02:34', 3, 4, 86),
(90, '2018-10-09 00:02:34', 4, 4, 86),
(91, '2018-10-09 00:02:34', 5, 4, 86),
(92, '2018-10-09 00:02:34', 6, 4, 86),
(93, '2018-10-09 00:02:34', 16, 4, 86),
(94, '2018-10-09 00:02:34', 51, 4, 86),
(95, '2018-10-09 00:02:34', 52, 4, 86),
(96, '2018-10-09 00:02:34', 53, 4, 86),
(97, '2018-10-09 00:02:34', 54, 4, 86),
(98, '2018-10-09 00:02:34', 55, 4, 86),
(104, '2018-10-09 00:30:14', 68, 3, 86),
(107, '2018-10-09 21:21:55', 1, 4, 106),
(108, '2018-10-09 21:21:55', 2, 4, 106),
(109, '2018-10-09 21:21:55', 3, 4, 106),
(110, '2018-10-09 21:21:55', 4, 4, 106),
(111, '2018-10-09 21:21:55', 5, 4, 106),
(112, '2018-10-09 21:21:55', 6, 4, 106),
(113, '2018-10-09 21:21:55', 16, 4, 106),
(114, '2018-10-09 21:21:55', 51, 4, 106),
(115, '2018-10-09 21:21:55', 52, 4, 106),
(116, '2018-10-09 21:21:55', 53, 4, 106),
(117, '2018-10-09 21:21:55', 54, 4, 106),
(118, '2018-10-09 21:21:55', 55, 4, 106),
(120, '2018-10-09 21:25:00', 68, 3, 106),
(123, '2018-10-09 21:29:07', 1, 4, 122),
(124, '2018-10-09 21:29:07', 2, 4, 122),
(125, '2018-10-09 21:29:07', 3, 4, 122),
(126, '2018-10-09 21:29:07', 4, 4, 122),
(127, '2018-10-09 21:29:07', 5, 4, 122),
(128, '2018-10-09 21:29:07', 6, 4, 122),
(129, '2018-10-09 21:29:07', 16, 4, 122),
(130, '2018-10-09 21:29:07', 51, 4, 122),
(131, '2018-10-09 21:29:07', 52, 4, 122),
(132, '2018-10-09 21:29:07', 53, 4, 122),
(133, '2018-10-09 21:29:07', 54, 4, 122),
(134, '2018-10-09 21:29:07', 55, 4, 122),
(136, '2018-10-09 21:29:44', 68, 3, 122),
(139, '2018-10-09 23:04:54', 1, 4, 138),
(140, '2018-10-09 23:04:54', 2, 4, 138),
(141, '2018-10-09 23:04:54', 3, 4, 138),
(142, '2018-10-09 23:04:54', 4, 4, 138),
(143, '2018-10-09 23:04:54', 5, 4, 138),
(144, '2018-10-09 23:04:54', 6, 4, 138),
(145, '2018-10-09 23:04:54', 16, 4, 138),
(146, '2018-10-09 23:04:54', 51, 4, 138),
(147, '2018-10-09 23:04:54', 52, 4, 138),
(148, '2018-10-09 23:04:54', 53, 4, 138),
(149, '2018-10-09 23:04:54', 54, 4, 138),
(150, '2018-10-09 23:04:54', 55, 4, 138),
(152, '2018-10-09 23:05:19', 68, 3, 138),
(159, '2018-10-11 20:11:43', 1, 4, 158),
(160, '2018-10-11 20:11:43', 2, 4, 158),
(161, '2018-10-11 20:11:43', 3, 4, 158),
(162, '2018-10-11 20:11:43', 4, 4, 158),
(163, '2018-10-11 20:11:43', 5, 4, 158),
(164, '2018-10-11 20:11:43', 6, 4, 158),
(165, '2018-10-11 20:11:43', 51, 4, 158),
(166, '2018-10-11 20:11:43', 52, 4, 158),
(167, '2018-10-11 20:11:43', 53, 4, 158),
(168, '2018-10-11 20:11:43', 54, 4, 158),
(169, '2018-10-11 20:11:43', 55, 4, 158),
(170, '2018-10-11 20:11:43', 56, 4, 158),
(173, '2018-10-11 20:12:51', 156, 3, 158),
(178, '2018-10-11 20:30:41', 1, 4, 177),
(179, '2018-10-11 20:30:41', 2, 4, 177),
(180, '2018-10-11 20:30:41', 3, 4, 177),
(181, '2018-10-11 20:30:41', 4, 4, 177),
(182, '2018-10-11 20:30:41', 5, 4, 177),
(183, '2018-10-11 20:30:41', 6, 4, 177),
(184, '2018-10-11 20:30:41', 16, 4, 177),
(185, '2018-10-11 20:30:41', 51, 4, 177),
(186, '2018-10-11 20:30:41', 52, 4, 177),
(187, '2018-10-11 20:30:41', 53, 4, 177),
(188, '2018-10-11 20:30:41', 54, 4, 177),
(189, '2018-10-11 20:30:41', 56, 4, 177),
(191, '2018-10-11 20:42:43', 175, 3, 177),
(194, '2018-10-15 20:05:17', 1, 4, 193),
(195, '2018-10-15 20:05:17', 2, 4, 193),
(196, '2018-10-15 20:05:17', 3, 4, 193),
(197, '2018-10-15 20:05:17', 4, 4, 193),
(198, '2018-10-15 20:05:17', 5, 4, 193),
(199, '2018-10-15 20:05:17', 6, 4, 193),
(200, '2018-10-15 20:05:17', 16, 4, 193),
(201, '2018-10-15 20:05:17', 52, 4, 193),
(202, '2018-10-15 20:05:17', 53, 4, 193),
(203, '2018-10-15 20:05:17', 54, 4, 193),
(204, '2018-10-15 20:05:17', 55, 4, 193),
(205, '2018-10-15 20:05:17', 56, 4, 193),
(208, '2018-10-15 20:08:35', 58, 3, 193),
(228, '2018-10-16 21:57:21', 1, 4, 227),
(229, '2018-10-16 21:57:21', 2, 4, 227),
(230, '2018-10-16 21:57:21', 3, 4, 227),
(231, '2018-10-16 21:57:21', 4, 4, 227),
(232, '2018-10-16 21:57:21', 5, 4, 227),
(233, '2018-10-16 21:57:21', 6, 4, 227),
(234, '2018-10-16 21:57:21', 16, 4, 227),
(235, '2018-10-16 21:57:21', 51, 4, 227),
(236, '2018-10-16 21:57:21', 52, 4, 227),
(237, '2018-10-16 21:57:21', 54, 4, 227),
(238, '2018-10-16 21:57:21', 55, 4, 227),
(239, '2018-10-16 21:57:21', 56, 4, 227),
(240, '2018-10-16 21:57:21', 209, 4, 227),
(241, '2018-10-16 21:57:21', 211, 4, 227),
(242, '2018-10-16 21:57:21', 213, 4, 227),
(243, '2018-10-16 21:57:21', 214, 4, 227),
(244, '2018-10-16 21:57:21', 216, 4, 227),
(248, '2018-10-16 21:57:42', 62, 3, 227),
(251, '2018-10-16 22:12:19', 1, 4, 250),
(252, '2018-10-16 22:12:19', 2, 4, 250),
(253, '2018-10-16 22:12:19', 3, 4, 250),
(254, '2018-10-16 22:12:19', 4, 4, 250),
(255, '2018-10-16 22:12:19', 5, 4, 250),
(256, '2018-10-16 22:12:19', 6, 4, 250),
(257, '2018-10-16 22:12:19', 16, 4, 250),
(258, '2018-10-16 22:12:20', 51, 4, 250),
(259, '2018-10-16 22:12:20', 52, 4, 250),
(260, '2018-10-16 22:12:20', 54, 4, 250),
(261, '2018-10-16 22:12:20', 55, 4, 250),
(262, '2018-10-16 22:12:20', 56, 4, 250),
(263, '2018-10-16 22:12:20', 209, 4, 250),
(264, '2018-10-16 22:12:20', 211, 4, 250),
(265, '2018-10-16 22:12:20', 213, 4, 250),
(266, '2018-10-16 22:12:20', 214, 4, 250),
(267, '2018-10-16 22:12:20', 216, 4, 250),
(270, '2018-10-17 22:03:41', 1, 4, 269),
(271, '2018-10-17 22:03:41', 2, 4, 269),
(272, '2018-10-17 22:03:41', 3, 4, 269),
(273, '2018-10-17 22:03:41', 4, 4, 269),
(274, '2018-10-17 22:03:41', 5, 4, 269),
(275, '2018-10-17 22:03:41', 6, 4, 269),
(276, '2018-10-17 22:03:41', 16, 4, 269),
(277, '2018-10-17 22:03:41', 51, 4, 269),
(278, '2018-10-17 22:03:41', 52, 4, 269),
(279, '2018-10-17 22:03:41', 53, 4, 269),
(280, '2018-10-17 22:03:41', 54, 4, 269),
(281, '2018-10-17 22:03:41', 55, 4, 269),
(282, '2018-10-17 22:03:41', 209, 4, 269),
(283, '2018-10-17 22:03:41', 211, 4, 269),
(284, '2018-10-17 22:03:41', 213, 4, 269),
(285, '2018-10-17 22:03:41', 214, 4, 269),
(286, '2018-10-17 22:03:41', 216, 4, 269),
(289, '2018-10-17 22:04:22', 68, 3, 269),
(294, '2018-10-18 20:05:53', 1, 4, 293),
(295, '2018-10-18 20:05:53', 2, 4, 293),
(296, '2018-10-18 20:05:53', 3, 4, 293),
(297, '2018-10-18 20:05:53', 4, 4, 293),
(298, '2018-10-18 20:05:53', 5, 4, 293),
(299, '2018-10-18 20:05:53', 6, 4, 293),
(300, '2018-10-18 20:05:53', 16, 4, 293),
(301, '2018-10-18 20:05:53', 51, 4, 293),
(302, '2018-10-18 20:05:53', 52, 4, 293),
(303, '2018-10-18 20:05:53', 54, 4, 293),
(304, '2018-10-18 20:05:53', 55, 4, 293),
(305, '2018-10-18 20:05:53', 56, 4, 293),
(306, '2018-10-18 20:05:53', 209, 4, 293),
(307, '2018-10-18 20:05:53', 211, 4, 293),
(308, '2018-10-18 20:05:53', 213, 4, 293),
(309, '2018-10-18 20:05:53', 214, 4, 293),
(310, '2018-10-18 20:05:53', 216, 4, 293),
(314, '2018-10-18 20:38:58', 291, 3, 293),
(318, '2018-10-18 20:39:45', 62, 3, 250),
(321, '2018-10-18 20:48:21', 1, 4, 320),
(322, '2018-10-18 20:48:21', 2, 4, 320),
(323, '2018-10-18 20:48:21', 3, 4, 320),
(324, '2018-10-18 20:48:21', 4, 4, 320),
(325, '2018-10-18 20:48:21', 5, 4, 320),
(326, '2018-10-18 20:48:21', 6, 4, 320),
(327, '2018-10-18 20:48:21', 16, 4, 320),
(328, '2018-10-18 20:48:21', 51, 4, 320),
(329, '2018-10-18 20:48:21', 52, 4, 320),
(330, '2018-10-18 20:48:21', 54, 4, 320),
(331, '2018-10-18 20:48:21', 55, 4, 320),
(332, '2018-10-18 20:48:21', 56, 4, 320),
(333, '2018-10-18 20:48:21', 209, 4, 320),
(334, '2018-10-18 20:48:21', 211, 4, 320),
(335, '2018-10-18 20:48:21', 213, 4, 320),
(336, '2018-10-18 20:48:21', 214, 4, 320),
(337, '2018-10-18 20:48:21', 216, 4, 320),
(339, '2018-10-20 23:18:17', 291, 3, 320),
(344, '2018-10-21 15:16:43', 1, 4, 343),
(345, '2018-10-21 15:16:43', 2, 4, 343),
(346, '2018-10-21 15:16:43', 3, 4, 343),
(347, '2018-10-21 15:16:43', 4, 4, 343),
(348, '2018-10-21 15:16:43', 5, 4, 343),
(349, '2018-10-21 15:16:43', 6, 4, 343),
(350, '2018-10-21 15:16:43', 16, 4, 343),
(351, '2018-10-21 15:16:43', 51, 4, 343),
(352, '2018-10-21 15:16:43', 52, 4, 343),
(353, '2018-10-21 15:16:43', 54, 4, 343),
(354, '2018-10-21 15:16:43', 55, 4, 343),
(355, '2018-10-21 15:16:43', 56, 4, 343),
(356, '2018-10-21 15:16:43', 209, 4, 343),
(357, '2018-10-21 15:16:43', 211, 4, 343),
(358, '2018-10-21 15:16:43', 213, 4, 343),
(359, '2018-10-21 15:16:43', 214, 4, 343),
(360, '2018-10-21 15:16:43', 216, 4, 343),
(372, '2018-10-28 10:43:52', 341, 3, 343);

-- --------------------------------------------------------

--
-- Table structure for table `reviewer`
--

CREATE TABLE `reviewer` (
  `id` bigint(20) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviewer`
--

INSERT INTO `reviewer` (`id`, `message`, `user_id`) VALUES
(83, 'No good :))))', 16),
(99, 'oki dki ;))))', 51),
(100, 'suuuuuuuuure', 52),
(101, 'yyy', 52),
(102, ';0000', 54),
(103, 'trhrth', 54),
(119, 'ok', 55),
(135, 'yes', 55),
(151, 'rererer', 51),
(153, '132', 6),
(171, '789', 51),
(172, '132213213', 52),
(190, '44444', 6),
(206, 'rrrrrrr', 52),
(207, 'iuuiiuuiiu', 53),
(245, 'eeeee', 6),
(246, 'ererer', 16),
(287, '6767', 51),
(288, '77777', 52),
(311, '777777', 54),
(312, '354345435435', 56),
(315, 'wwwwwww', 6),
(316, '4545', 55),
(338, 'No kebab sir', 51),
(369, 'Yes I approve', 6),
(370, 'Approved.', 16);

-- --------------------------------------------------------

--
-- Table structure for table `skill`
--

CREATE TABLE `skill` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `skill_header_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skill`
--

INSERT INTO `skill` (`id`, `title`, `skill_header_id`) VALUES
(7, 'Java 6', 1),
(10, 'Zephyr', 2),
(13, 'Jira', 4),
(154, 'Payroll', 3);

-- --------------------------------------------------------

--
-- Table structure for table `skill_header`
--

CREATE TABLE `skill_header` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skill_header`
--

INSERT INTO `skill_header` (`id`, `title`) VALUES
(1, 'Development'),
(2, 'Testing'),
(3, 'Domain Knowledge'),
(4, 'Service Management'),
(5, 'Analysis/Testing');

-- --------------------------------------------------------

--
-- Table structure for table `skill_level`
--

CREATE TABLE `skill_level` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skill_level`
--

INSERT INTO `skill_level` (`id`, `description`, `level`, `title`) VALUES
(5, 'Know the skill very well, be able to do any given task and can teach others', 5, 'Expert'),
(4, 'Be able to work on your own, you know where to find missing information. Support is needed for specific cases only', 4, 'Proeficient'),
(1, 'New to this skill, know basics.', 1, 'Novice'),
(2, 'Be familiar with the skill, read books, articles etc. but your experience using skill is limited., know basics', 2, 'Advanced beginner'),
(3, 'Have worked with my collegues help, able to work with this skill but support is still needed, I can not work on my own', 3, 'Competent');

-- --------------------------------------------------------

--
-- Table structure for table `skill_template`
--

CREATE TABLE `skill_template` (
  `id` bigint(20) NOT NULL,
  `team_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skill_template`
--

INSERT INTO `skill_template` (`id`, `team_id`) VALUES
(364, 363);

-- --------------------------------------------------------

--
-- Table structure for table `skill_template_skills`
--

CREATE TABLE `skill_template_skills` (
  `skill_template_id` bigint(20) NOT NULL,
  `skills_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skill_template_skills`
--

INSERT INTO `skill_template_skills` (`skill_template_id`, `skills_id`) VALUES
(364, 7),
(364, 10),
(364, 13),
(364, 154);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `value_stream_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `name`, `department_id`, `value_stream_id`) VALUES
(363, 'Scorpions', 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `team_skill`
--

CREATE TABLE `team_skill` (
  `id` bigint(20) NOT NULL,
  `creation_time` datetime DEFAULT NULL,
  `skill_count` int(11) DEFAULT NULL,
  `skill_level_average` double DEFAULT NULL,
  `skill_id` bigint(20) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team_skill`
--

INSERT INTO `team_skill` (`id`, `creation_time`, `skill_count`, `skill_level_average`, `skill_id`, `team_id`) VALUES
(365, '2018-10-24 20:54:22', 5, 2.6, 7, 363),
(366, '2018-10-24 20:54:22', 2, 1.5, 10, 363),
(367, '2018-10-24 20:54:22', 0, 0, 13, 363),
(368, '2018-10-24 20:54:22', 1, 2, 154, 363);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `auth_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `role`, `surname`, `team_id`, `password`, `auth_id`) VALUES
(1, 'k@gmail.com', 'K', 'EMPLOYEE', 'G', NULL, NULL, NULL),
(2, 'g@gmail.com', 'G', 'EMPLOYEE', 'G', NULL, NULL, NULL),
(3, 'ka@gmail.com', 'K', 'EMPLOYEE', 'K', NULL, '$2a$10$Eemog.yksrM5iBKcvGCMt.nfhhdcmmJLB8vssAhnEMjL7HzKFkGAa', NULL),
(4, 'karo@gmail.com', 'k', 'EMPLOYEE', 'k', NULL, '$2a$10$J12OUSnDOE5JeaVdUNYZterc9Bs8RMBVS4zu6wg/ZB7PtGDd0W1Sy', NULL),
(5, 'demo@mail.com', 'demo', 'EMPLOYEE', 'demo', NULL, '$2a$10$HBhx43K2q5ls0bOuVA0EE.Tx7ikSqPg5GZXlDrOiVDuRlHH/EniDG', NULL),
(6, 'email@email.com', 'name', 'EMPLOYEE', 'surname', NULL, '$2a$10$mQslo9Oe2nUhoS3z5p.0NeDOZhcPF29lsuOssBDsqkwQqk6EVGG4K', NULL),
(16, 'mymail@email.com', 'Vardenis', 'EMPLOYEE', 'Pavardenis', NULL, '$2a$10$xOBND69.6/2.5IKBIM4ysuNwM2qq92ssICmIzEbatgyNZ4LwoYWEi', NULL),
(51, 'name1@mail.com', 'name1', 'EMPLOYEE', 'surname1', 363, '$2a$10$tCYCEKNQ2T38yNh3863N8OPwDCT/pYOE.S1qhkFJuml4.AYL2Mf06', NULL),
(52, 'name2@mail.com', 'name2', 'EMPLOYEE', 'surname2', 363, '$2a$10$JXc/9./YoyGs8si7ft9DTeuwsLiQkCaF6tRlW1vLnuJBLDVhQhPMy', NULL),
(53, 'name3@mail.com', 'name3', 'EMPLOYEE', 'name3', 363, '$2a$10$LfY8598CwVtqoRZ7XZWW1O5MuBcxMvhdg.6mXx/uuf5eW4l1Y0qH6', NULL),
(54, 'name4@mail.com', 'name4', 'EMPLOYEE', 'name4', 363, '$2a$10$v8OGW9ppcWBYYZs5ARW67etO7Wm3ebp006d2SUdOVzTPMHLQawdES', NULL),
(55, 'name5@mail.com', 'name5', 'MANAGER', 'name5', 363, '$2a$10$b0ZmuwYugXL1JfYL3JqkHOocjVVy.BO2G3ygvGLdiD//PVIm2YTrS', NULL),
(56, 'name6@mail.com', 'name6', 'EMPLOYEE', 'name6', NULL, '$2a$10$dkfyXY5jn7/jR/QXeSk6vOfxaKfWC8XpHCqjlFtvsvMaj/r2HAB4G', NULL),
(209, 'hello@mail.com', 'kdshfiudshfkuh', 'EMPLOYEE', 'ihdiuewhfiewfoiw', NULL, '$2a$10$wPcheXOcNZnygtRIV7BQaOcSd9Zl0BF8n4IopUEdR9Pgy/4J4ocRu', NULL),
(211, 'zx@mail.com', 'xz', 'EMPLOYEE', 'zx', NULL, '$2a$10$RzUMmgMMGdARHyg7K6INA.X0TFtFfTd7BIZt5svmu5bhUTdeCfHSm', NULL),
(213, 'eeee@mail.com', 'eee', 'EMPLOYEE', 'eee', NULL, '$2a$10$4bhLYqwux3XHwFwzhLjSqukbye1adgmFU6J/BKrEsfjFiLqMVH9di', NULL),
(214, 'rtrt@mail.com', 'rtrt', 'EMPLOYEE', 'rtrt', NULL, '$2a$10$xBMPqtOe8ikoK6F3sFxYIuRdeF3qMxmnsdck5Tym2FPSXW/CJbt5K', NULL),
(216, 'ttt@mail.com', 'ttt', 'EMPLOYEE', 'ttt', NULL, '$2a$10$9Z.Gq2o6uUyWetwGK6d8JOCZMC4qq8UJC74vbxqga1a/Bih6ZaDYi', NULL),
(377, 'name7@mail.com', 'name7', 'EMPLOYEE', 'name7', NULL, '$2a$10$0kvYeBDoDt6QrYOU5Y7WYOhPZYjryTNyV8v/grWfrq866KHX4EZ/G', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_skill`
--

CREATE TABLE `user_skill` (
  `id` bigint(20) NOT NULL,
  `skill_status` varchar(255) DEFAULT NULL,
  `skill_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_skill`
--

INSERT INTO `user_skill` (`id`, `skill_status`, `skill_id`, `user_id`) VALUES
(9, 'ACTIVE', 7, 6),
(12, 'ACTIVE', 10, 6),
(15, 'ACTIVE', 13, 6),
(18, 'ACTIVE', 7, 16),
(58, 'ACTIVE', 7, 51),
(60, 'ACTIVE', 7, 52),
(62, 'ACTIVE', 7, 53),
(64, 'ACTIVE', 7, 54),
(66, 'ACTIVE', 7, 55),
(68, 'ACTIVE', 7, 56),
(156, 'ACTIVE', 154, 16),
(175, 'ACTIVE', 10, 55),
(219, 'ACTIVE', 7, 216),
(221, 'ACTIVE', 10, 216),
(223, 'ACTIVE', 13, 216),
(225, 'ACTIVE', 154, 216),
(291, 'ACTIVE', 10, 53),
(341, 'ACTIVE', 154, 53);

-- --------------------------------------------------------

--
-- Table structure for table `user_skill_level`
--

CREATE TABLE `user_skill_level` (
  `id` bigint(20) NOT NULL,
  `motivation` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `valid_from` datetime DEFAULT NULL,
  `valid_until` datetime DEFAULT NULL,
  `skill_level_id` bigint(20) DEFAULT NULL,
  `user_skill_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_skill_level`
--

INSERT INTO `user_skill_level` (`id`, `motivation`, `status`, `valid_from`, `valid_until`, `skill_level_id`, `user_skill_id`) VALUES
(8, NULL, 2, '2018-09-30 23:09:48', NULL, 4, 9),
(11, NULL, 2, '2018-10-01 21:25:56', NULL, 3, 12),
(14, NULL, 2, '2018-10-01 21:26:12', NULL, 1, 15),
(17, NULL, 2, '2018-10-01 21:53:00', NULL, 1, 18),
(19, 'I am better at it :)', 1, '2018-10-06 09:30:47', NULL, 2, 15),
(27, 'hehehhdsfjksdhfkjsdhkjfsdhkjfhsdjkfhsdjkhfjksdhfkjsdhfjsdkhfjdskhfsjdkhfjskdhfsdjkhfsdkjfhsdkjhfsdkjhf', 1, '2018-10-06 13:14:58', NULL, 4, 12),
(35, 'dfgdfgdfgdfgdfg', 1, '2018-10-06 13:17:19', NULL, 5, 9),
(43, ':)', 1, '2018-10-07 14:59:03', NULL, 2, 18),
(57, NULL, 2, '2018-10-08 21:27:37', NULL, 2, 58),
(59, NULL, 2, '2018-10-08 21:28:37', NULL, 2, 60),
(61, NULL, 2, '2018-10-08 21:28:48', NULL, 2, 62),
(63, NULL, 2, '2018-10-08 21:29:01', NULL, 2, 64),
(65, NULL, 2, '2018-10-08 21:29:10', NULL, 2, 66),
(67, NULL, 2, '2018-10-08 21:35:02', NULL, 1, 68),
(69, 'Me good :)))', 0, '2018-10-08 21:35:12', NULL, 2, 68),
(85, 'please :))', 0, '2018-10-09 00:02:34', NULL, 2, 68),
(105, 'pleaseeeee', 2, '2018-10-09 21:21:55', NULL, 2, 68),
(121, 'trtrtrt', 2, '2018-10-09 21:29:07', NULL, 3, 68),
(137, 'wwwwww', 2, '2018-10-09 23:04:54', NULL, 4, 68),
(155, NULL, 2, '2018-10-11 20:11:32', NULL, 1, 156),
(157, 'qwer', 2, '2018-10-11 20:11:43', NULL, 2, 156),
(174, NULL, 2, '2018-10-11 20:30:34', NULL, 1, 175),
(176, '445454', 0, '2018-10-11 20:30:41', NULL, 2, 175),
(192, 'qwerqwer', 2, '2018-10-15 20:05:17', NULL, 3, 58),
(218, NULL, 2, '2018-10-16 20:04:26', NULL, 1, 219),
(220, NULL, 2, '2018-10-16 20:04:29', NULL, 1, 221),
(222, NULL, 2, '2018-10-16 20:04:33', NULL, 1, 223),
(224, NULL, 2, '2018-10-16 20:04:38', NULL, 1, 225),
(226, 'rerer', 2, '2018-10-16 21:57:21', NULL, 3, 62),
(249, '147', 2, '2018-10-16 22:11:28', NULL, 4, 62),
(268, '565656', 0, '2018-10-17 22:03:27', NULL, 5, 68),
(290, NULL, 2, '2018-10-18 20:05:46', NULL, 1, 291),
(292, 'eeee', 2, '2018-10-18 20:05:53', NULL, 2, 291),
(319, 'heyooooo', 0, '2018-10-18 20:48:21', NULL, 3, 291),
(340, NULL, 2, '2018-10-21 15:16:33', NULL, 1, 341),
(342, 'yoyoyo', 2, '2018-10-21 15:16:43', NULL, 2, 341);

-- --------------------------------------------------------

--
-- Table structure for table `value_stream`
--

CREATE TABLE `value_stream` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `value_stream`
--

INSERT INTO `value_stream` (`id`, `name`) VALUES
(1, 'Corporate and Cash Management'),
(2, 'Digital Lending'),
(3, 'Cards'),
(4, 'WEB');

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE `vote` (
  `id` bigint(20) NOT NULL,
  `message` varchar(500) DEFAULT NULL,
  `user_skill_level_id` bigint(20) DEFAULT NULL,
  `voter_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `approval_request`
--
ALTER TABLE `approval_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7n6mfjashalqhsaoakxdc1hsb` (`user_skill_level_id`);

--
-- Indexes for table `approval_request_approvers`
--
ALTER TABLE `approval_request_approvers`
  ADD UNIQUE KEY `UK_2mjjf1sbdse9wg31nykjtmmaf` (`approvers_id`),
  ADD KEY `FKnbfch89fg4bwus32td46mb7vo` (`approval_request_id`);

--
-- Indexes for table `approval_request_disapprovers`
--
ALTER TABLE `approval_request_disapprovers`
  ADD UNIQUE KEY `UK_gigv4cuf41s21x59k5rrxg5wy` (`disapprovers_id`),
  ADD KEY `FKkrlat4x37474rx639ixq6mvhr` (`approval_request_id`);

--
-- Indexes for table `approval_request_request_notifications`
--
ALTER TABLE `approval_request_request_notifications`
  ADD UNIQUE KEY `UK_pewa0vju1h6nkn892jhdanj38` (`request_notifications_id`),
  ADD KEY `FKl6ht6p22g48wdtucqaexyek82` (`approval_request_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhip77j4jffqyhpg8rwj3n7bxx` (`division_id`);

--
-- Indexes for table `division`
--
ALTER TABLE `division`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `news_feed`
--
ALTER TABLE `news_feed`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqmgxaeq51m9lnrqwxpah4hoib` (`skill_id`),
  ADD KEY `FK6wxj8x3o785o98ut2yp36bm22` (`skill_level_id`);

--
-- Indexes for table `request_notification`
--
ALTER TABLE `request_notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9bb3pum0woxyuphnbs7uegybv` (`approval_request_id`);

--
-- Indexes for table `reviewer`
--
ALTER TABLE `reviewer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKds2283g9vk4ij3l48h78agmmc` (`skill_header_id`);

--
-- Indexes for table `skill_header`
--
ALTER TABLE `skill_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skill_level`
--
ALTER TABLE `skill_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skill_template`
--
ALTER TABLE `skill_template`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r5prqwssceiyvyv69nhwarj87` (`team_id`);

--
-- Indexes for table `skill_template_skills`
--
ALTER TABLE `skill_template_skills`
  ADD KEY `FK7gluyimuf2c3falogb66bo9mc` (`skills_id`),
  ADD KEY `FK8xsrnyina77amouw89d8gfag5` (`skill_template_id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcitsl0ygrf7nbmydhlcqorb3p` (`department_id`),
  ADD KEY `FKs93q7gr1allytjcngv3f1uwuv` (`value_stream_id`);

--
-- Indexes for table `team_skill`
--
ALTER TABLE `team_skill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4dpwwb5u29bsodrih7pj6wj6d` (`skill_id`),
  ADD KEY `FKgmhd7axk5ys20prse1ywyydw7` (`team_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbmqm8c8m2aw1vgrij7h0od0ok` (`team_id`);

--
-- Indexes for table `user_skill`
--
ALTER TABLE `user_skill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj53flyds4vknyh8llw5d7jdop` (`skill_id`),
  ADD KEY `FKfixgsonf2ev168mfck7co17u1` (`user_id`);

--
-- Indexes for table `user_skill_level`
--
ALTER TABLE `user_skill_level`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt9x76tlrmgi5get5w8xsit4lx` (`skill_level_id`),
  ADD KEY `FKt75fu9x3gpiow8vxdnb6ubugi` (`user_skill_id`);

--
-- Indexes for table `value_stream`
--
ALTER TABLE `value_stream`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6bfhhmh7f2g9rpodigwfvtixg` (`voter_id`,`user_skill_level_id`),
  ADD KEY `FKcqj06x9fojueknqyurmbd9eiv` (`user_skill_level_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
