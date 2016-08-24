-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-08-2016 a las 07:36:05
-- Versión del servidor: 5.0.27-community-nt
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `web_service`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `frases`
--

CREATE TABLE IF NOT EXISTS `frases` (
  `idFrase` int(11) NOT NULL auto_increment,
  `autor` varchar(50) NOT NULL,
  `frase` text NOT NULL,
  `tipoFrase` varchar(50) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY  (`idFrase`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

