-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Mag 31, 2018 alle 07:22
-- Versione del server: 5.5.38-0ubuntu0.14.04.1
-- Versione PHP: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `DataCine`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `Attore`
--

CREATE TABLE IF NOT EXISTS `Attore` (
  `CodiceAttore` int(11) NOT NULL AUTO_INCREMENT,
  `Cognome` varchar(30) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Sesso` varchar(1) NOT NULL,
  `AnnoNascita` year(4) NOT NULL,
  `Nazionalita` varchar(25) NOT NULL,
  PRIMARY KEY (`CodiceAttore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Cinema`
--

CREATE TABLE IF NOT EXISTS `Cinema` (
  `CodCinema` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Posti` int(11) NOT NULL,
  `Citta` varchar(25) NOT NULL,
  PRIMARY KEY (`CodCinema`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `Cinema`
--

INSERT INTO `Cinema` (`CodCinema`, `Nome`, `Posti`, `Citta`) VALUES
(1, 'Cinema A', 100, 'Aria'),
(2, 'Cinema B', 200, 'Based'),
(3, 'Cinema C', 101, 'Cincinnati');

-- --------------------------------------------------------

--
-- Struttura della tabella `Film`
--

CREATE TABLE IF NOT EXISTS `Film` (
  `CodFilm` int(11) NOT NULL AUTO_INCREMENT,
  `Titolo` varchar(30) NOT NULL,
  `AnnoProduzione` year(4) NOT NULL,
  `LuogoProduzione` varchar(30) NOT NULL,
  `CognomeRegista` varchar(30) NOT NULL,
  `Genere` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CodFilm`),
  UNIQUE KEY `Titolo` (`Titolo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `Film`
--

INSERT INTO `Film` (`CodFilm`, `Titolo`, `AnnoProduzione`, `LuogoProduzione`, `CognomeRegista`, `Genere`) VALUES
(1, 'Film A', 2010, 'Ancona', 'Alberto', 'Azione'),
(2, 'Film B ', 2012, 'Berlino', 'Barba', 'Bio-grafico'),
(3, 'Film C', 2014, 'Catanzaro', 'Corona', 'Commedia'),
(4, 'Film D', 2009, 'Dolina', 'DHE Forest', 'Drammatico'),
(5, 'Film E', 2013, 'EA', 'Sport it''s only a game', 'E-commerce');

-- --------------------------------------------------------

--
-- Struttura della tabella `Interpreta`
--

CREATE TABLE IF NOT EXISTS `Interpreta` (
  `CodAttore` int(11) NOT NULL,
  `CodFilm` int(11) NOT NULL,
  `Personaggio` varchar(20) NOT NULL,
  KEY `CodFilm` (`CodFilm`),
  KEY `CodAttore` (`CodAttore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Programmato`
--

CREATE TABLE IF NOT EXISTS `Programmato` (
  `CodiceFilm` int(11) NOT NULL,
  `CodiceCinema` int(11) NOT NULL,
  `Incasso` double NOT NULL,
  `DataProiezione` date NOT NULL,
  KEY `CodiceFilm` (`CodiceFilm`),
  KEY `Programmato_ibfk_2` (`CodiceCinema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Programmato`
--

INSERT INTO `Programmato` (`CodiceFilm`, `CodiceCinema`, `Incasso`, `DataProiezione`) VALUES
(1, 2, 34.51, '2001-11-02'),
(1, 1, 300.5, '1993-10-03'),
(2, 3, 123.67, '1993-03-24'),
(2, 1, 1809.23, '2008-04-15');

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Interpreta`
--
ALTER TABLE `Interpreta`
  ADD CONSTRAINT `Interpreta_ibfk_1` FOREIGN KEY (`CodFilm`) REFERENCES `Film` (`CodFilm`),
  ADD CONSTRAINT `Interpreta_ibfk_2` FOREIGN KEY (`CodAttore`) REFERENCES `Cinema` (`CodCinema`);

--
-- Limiti per la tabella `Programmato`
--
ALTER TABLE `Programmato`
  ADD CONSTRAINT `Programmato_ibfk_2` FOREIGN KEY (`CodiceCinema`) REFERENCES `Cinema` (`CodCinema`),
  ADD CONSTRAINT `Programmato_ibfk_1` FOREIGN KEY (`CodiceFilm`) REFERENCES `Film` (`CodFilm`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
