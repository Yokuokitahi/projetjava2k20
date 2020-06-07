-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 07 juin 2020 à 11:26
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `IDC` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`IDC`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`IDC`, `Nom`) VALUES
(4, 'Analyse de Fourier'),
(2, 'Physique'),
(3, 'Informatique'),
(5, 'Probabilité'),
(6, 'Initiation réseau');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `IDProf` int(11) NOT NULL,
  `ID_Cours` int(11) NOT NULL,
  PRIMARY KEY (`IDProf`,`ID_Cours`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`IDProf`, `ID_Cours`) VALUES
(2, 3),
(2, 6),
(16, 4),
(16, 5),
(17, 3),
(17, 6),
(18, 2),
(18, 4);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `ID_Groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`ID`, `Nom`, `Prenom`, `ID_Groupe`) VALUES
(1, 'antoine', 'xavier', 1),
(5, 'goncalves', 'philippe', 1),
(6, 'abdoulnasir', 'yacin-malo', 2),
(7, 'zidane', 'zinedine', 2),
(8, 'pogba', 'paul', 3),
(9, 'lloris', 'hugo', 3),
(10, 'andrieu', 'nabil', 4),
(11, 'andrieu', 'tarik', 4),
(12, 'mbappe', 'kylian', 5),
(13, 'griezmann', 'antoine', 5),
(14, 'wayne', 'bruce', 6),
(15, 'stark', 'tony', 6);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `ID_Promotion` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`ID`, `Nom`, `ID_Promotion`) VALUES
(1, 'A', 2022),
(2, 'B', 2022),
(3, 'A', 2024),
(4, 'B', 2024),
(5, 'A', 2023),
(6, 'B', 2023);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `IDP` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDP`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`IDP`, `Nom`) VALUES
(1, 2022),
(3, 2023),
(4, 2024);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `IDSalle` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Capacite` int(255) NOT NULL,
  `ID_Site` int(11) NOT NULL,
  PRIMARY KEY (`IDSalle`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`IDSalle`, `Nom`, `Capacite`, `ID_Site`) VALUES
(1, 'Amphi A', 125, 1),
(2, 'Amphi B', 125, 1),
(3, 'Salle 415', 70, 2),
(4, 'Salle 435', 70, 2),
(5, 'Salle E300', 35, 3),
(6, 'Salle E310', 35, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Heure_debut` time NOT NULL,
  `Heure_fin` time NOT NULL,
  `Etat` int(11) NOT NULL DEFAULT '1',
  `ID_Cours` int(11) NOT NULL,
  `ID_Type` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`ID`, `Semaine`, `Date`, `Heure_debut`, `Heure_fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES
(1, 23, '2020-06-06', '10:15:00', '11:45:00', 1, 5, 1),
(2, 22, '2020-05-27', '15:30:00', '17:00:00', 1, 4, 2),
(10, 23, '2020-06-06', '10:15:00', '11:45:00', 1, 4, 1),
(4, 22, '2020-05-29', '08:30:00', '10:00:00', 1, 6, 4),
(5, 22, '2020-05-26', '08:30:00', '10:00:00', 1, 2, 5),
(6, 23, '2020-06-01', '10:15:00', '11:45:00', 1, 5, 6),
(7, 21, '2020-05-19', '15:30:00', '17:00:00', 1, 6, 1),
(11, 23, '2020-06-01', '12:00:00', '13:30:00', 1, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignant`
--

DROP TABLE IF EXISTS `seance_enseignant`;
CREATE TABLE IF NOT EXISTS `seance_enseignant` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Enseignant` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Enseignant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_enseignant`
--

INSERT INTO `seance_enseignant` (`ID_Seance`, `ID_Enseignant`) VALUES
(1, 16),
(2, 18),
(4, 2),
(5, 18),
(6, 16),
(7, 2),
(10, 16),
(11, 18),
(12, 18);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupes`
--

DROP TABLE IF EXISTS `seance_groupes`;
CREATE TABLE IF NOT EXISTS `seance_groupes` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_groupes`
--

INSERT INTO `seance_groupes` (`ID_Seance`, `ID_Groupe`) VALUES
(1, 2),
(1, 3),
(2, 2),
(4, 3),
(5, 2),
(6, 2),
(7, 1),
(7, 2),
(10, 5),
(10, 6),
(11, 2),
(12, 2);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salles`
--

DROP TABLE IF EXISTS `seance_salles`;
CREATE TABLE IF NOT EXISTS `seance_salles` (
  `ID_Seance` int(11) NOT NULL,
  `ID_Salle` int(11) NOT NULL,
  PRIMARY KEY (`ID_Seance`,`ID_Salle`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_salles`
--

INSERT INTO `seance_salles` (`ID_Seance`, `ID_Salle`) VALUES
(1, 1),
(2, 2),
(4, 4),
(5, 5),
(6, 2),
(7, 2),
(10, 1),
(11, 2),
(12, 2);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `IDSite` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` text NOT NULL,
  PRIMARY KEY (`IDSite`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`IDSite`, `Nom`) VALUES
(1, 'Eiffel 1'),
(2, 'Eiffel 2'),
(3, 'Eiffel 3');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

DROP TABLE IF EXISTS `type_cours`;
CREATE TABLE IF NOT EXISTS `type_cours` (
  `ID_TC` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_TC`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`ID_TC`, `Nom`) VALUES
(1, 'Cours Magistral'),
(2, 'TD'),
(3, 'TP'),
(4, 'Projet'),
(5, 'Soutien'),
(6, 'Cours interactif');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Nom` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Prenom` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `DroitAcces` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`ID`, `Email`, `Password`, `Nom`, `Prenom`, `DroitAcces`) VALUES
(1, 'antoine@edu.ece.fr', 'root', 'antoine', 'xavier', 4),
(2, 'jps@edu.ece.fr', 'jps', 'Segado', 'Jean-Pierre', 3),
(3, 'ref', 'ref', 'Paire', 'Benoit', 2),
(4, 'admin', 'admin', 'Admin', 'Philippe', 1),
(5, 'goncalves@edu.ece.fr', 'goncalves', 'Goncalves', 'Philippe', 4),
(6, 'malo', 'root', 'abdoulnasir', 'yacin-malo', 4),
(7, 'zidane@edu.ece.fr', 'zidane', 'zidane', 'zinedine', 4),
(8, 'pogba@edu.ece.fr', 'pogba', 'pogba', 'paul', 4),
(9, 'lloris@edu.ece.fr', 'lloris', 'lloris', 'hugo', 4),
(10, 'nabil@edu.ece.fr', 'nabil', 'andrieu', 'nabil', 4),
(11, 'tarik@edu.ece.fr', 'tarik', 'andrieu', 'tarik', 4),
(12, 'mbappe@edu.ece.fr', 'mbappe', 'mbappe', 'kylian', 4),
(13, 'griezmann@edu.ece.fr', 'griezmann', 'griezmann', 'antoine', 4),
(14, 'wayne@edu.ece.fr', 'wayne', 'wayne', 'bruce', 4),
(15, 'stark@edu.ece.fr', 'stark', 'stark', 'tony', 4),
(16, 'coudray@edu.ece.fr', 'coudray', 'coudray', 'fabienne', 3),
(17, 'hina@edu.ece.fr', 'hina', 'hina', 'manolo', 3),
(18, 'mouhali@gmail.com', 'mouhali', 'mouhali', 'waleed', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
