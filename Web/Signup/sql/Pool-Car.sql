-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Apr 09, 2018 alle 03:58
-- Versione del server: 5.5.38-0ubuntu0.14.04.1
-- Versione PHP: 5.5.9-1ubuntu4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Pool-Car`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `Autista`
--

CREATE TABLE IF NOT EXISTS `Autista` (
  `ID_autista` int(11) NOT NULL AUTO_INCREMENT,
  `Cognome` varchar(30) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `data_nascita` date NOT NULL,
  `sesso` varchar(1) NOT NULL,
  `nazionalita` varchar(30) NOT NULL,
  `Numero_patente` varchar(15) NOT NULL,
  `Scadenza_Patente` date NOT NULL,
  PRIMARY KEY (`ID_autista`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Telefono` (`Telefono`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `Autista`
--

INSERT INTO `Autista` (`ID_autista`, `Cognome`, `Nome`, `Email`, `Password`, `Telefono`, `data_nascita`, `sesso`, `nazionalita`, `Numero_patente`, `Scadenza_Patente`) VALUES
(1, 'Scifoni', 'Letizia', 'scifoni.letizia@alice.com', 'scifoniletizia', '0976543215', '1979-11-16', 'F', 'Italia', 'B', '2019-07-12');

-- --------------------------------------------------------

--
-- Struttura della tabella `Auto`
--

CREATE TABLE IF NOT EXISTS `Auto` (
  `Targa` varchar(7) NOT NULL,
  `Marca` varchar(15) NOT NULL,
  `Modello` varchar(15) NOT NULL,
  `Cilindrata` int(11) NOT NULL,
  `Potenza` int(11) NOT NULL,
  `id_autista` int(11) NOT NULL,
  PRIMARY KEY (`Targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `FeedBack`
--

CREATE TABLE IF NOT EXISTS `FeedBack` (
  `ID_pas` int(11) NOT NULL,
  `ID_autista` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Voto` int(11) NOT NULL,
  `Giudizio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Passegero`
--

CREATE TABLE IF NOT EXISTS `Passegero` (
  `ID_pas` int(11) NOT NULL AUTO_INCREMENT,
  `Cognome` varchar(30) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Anno_nascita` date NOT NULL,
  `Sesso` varchar(1) NOT NULL,
  `Nazionalita` varchar(30) NOT NULL,
  PRIMARY KEY (`ID_pas`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Telefono` (`Telefono`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `Passegero`
--

INSERT INTO `Passegero` (`ID_pas`, `Cognome`, `Nome`, `Email`, `Password`, `Telefono`, `Anno_nascita`, `Sesso`, `Nazionalita`) VALUES
(2, 'Mario', 'Rossi', 'mario.rossi@gmail.com', 'mariorossi', '3456789123', '1974-06-13', 'M', 'Italia');

-- --------------------------------------------------------

--
-- Struttura della tabella `Prenotazioni`
--

CREATE TABLE IF NOT EXISTS `Prenotazioni` (
  `ID_pas` int(11) NOT NULL,
  `ID_autista` int(11) NOT NULL,
  `ID_Viaggio` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Disponibile` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Viaggio`
--

CREATE TABLE IF NOT EXISTS `Viaggio` (
  `ID_viaggio` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Destinazione` varchar(30) NOT NULL,
  `Importo` double NOT NULL,
  `Ora_partenza` int(11) NOT NULL,
  `Ora_arrivo` int(11) NOT NULL,
  `Durata` int(11) NOT NULL COMMENT 'Minuti',
  PRIMARY KEY (`ID_viaggio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
