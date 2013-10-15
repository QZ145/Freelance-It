/*!40101 SET NAMES binary*/;
/*!40014 SET FOREIGN_KEY_CHECKS=0*/;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(35) DEFAULT NULL,
  `LastName` varchar(35) DEFAULT NULL,
  `Login` varchar(16) NOT NULL,
  `email` varchar(35) NOT NULL,
  `Birthday` date DEFAULT NULL,
  `DateOfRegistration` date DEFAULT NULL,
  `password` varchar(35) NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Login` (`Login`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*!40101 SET NAMES binary*/;
/*!40014 SET FOREIGN_KEY_CHECKS=0*/;

CREATE TABLE `Task` (
  `idtask` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1023) DEFAULT NULL,
  `title` varchar(35) NOT NULL,
  `id_executor` int(11) DEFAULT NULL,
  `id_owner` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `done` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtask`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET NAMES binary*/;
/*!40014 SET FOREIGN_KEY_CHECKS=0*/;

CREATE TABLE `Task_executors` (
  `id_task` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET NAMES binary*/;
/*!40014 SET FOREIGN_KEY_CHECKS=0*/;

CREATE TABLE `User_types` (
  `id_user` int(11) NOT NULL,
  `type` varchar(15) NOT NULL DEFAULT 'user',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
