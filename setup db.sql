CREATE DATABASE `url_shortener` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `url_shortener`;
CREATE TABLE `links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `long_url` varchar(2300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
