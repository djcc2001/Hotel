CREATE DATABASE  IF NOT EXISTS `hotel_alura` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_alura`;

--
-- Table structure for table `huespedes`
--

DROP TABLE IF EXISTS `huespedes`;
CREATE TABLE `huespedes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `fecha_de_nacimiento` date DEFAULT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `id_reservas` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `valor` decimal(8,2) DEFAULT NULL,
  `forma_pago` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `idusuarios` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) DEFAULT NULL,
  `pass` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idusuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Insert to `usuarios`
--

INSERT INTO `usuarios` VALUES (1,'admin','adm12345');
