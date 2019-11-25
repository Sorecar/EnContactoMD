-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: encontacto
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `administrador` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Contraseña` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'Admin','1234'),(2,'Root','1234'),(3,'Administrador','1234'),(4,'Pini','Pini');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `eventos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Contacto` varchar(50) NOT NULL,
  `Fecha` varchar(50) NOT NULL,
  `Hora` varchar(50) NOT NULL,
  `Lugar` varchar(50) NOT NULL,
  `Descripcion` varchar(200) NOT NULL,
  `Usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (1,'Proyecta-TC','FEI UV','2019-02-21','09:00','FEI-Autiorio','Proyecta tc donde alumnos de la carrera de tecnologias computacionales muestran diferentes proyectos finalesl','Carlos'),(6,'Miguel','2232244577','2019-12-20','19:00','FEI','Foro Proyecta TC','miguel20'),(7,'Miguel','1234567890','2020-01-19','13:00','Coatepec','Fiesta de Miki','miguel20');
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticia`
--

DROP TABLE IF EXISTS `noticia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `noticia` (
  `idnoticia` int(10) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(30) NOT NULL,
  `Noticia` varchar(500) NOT NULL,
  `Fecha` datetime NOT NULL,
  PRIMARY KEY (`idnoticia`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia`
--

LOCK TABLES `noticia` WRITE;
/*!40000 ALTER TABLE `noticia` DISABLE KEYS */;
INSERT INTO `noticia` VALUES (1,'Carlos','Amanecer en #Xalapa con algunas lloviznas combinándose con los primeros rayos del Sol, generando un doble arcoíris en dirección al norte y poniente de la ciudad.','2019-11-12 07:00:00'),(3,'Pablo','DESAPARECIDA #MayraSotoLeón el sábado 9/11/19 salió de #Rinconada, #EmilianoZapata a su escuela en #Cardel. Vestía pantalón azul marino, playera blanca, chaleco negro, tenis negro c/blanco. Info: 2791006331. #Xalapa','2019-11-12 13:30:00'),(4,'Yisus','Por los trabajos de reparación en el centro de la ciudad se provocó una fuga la esquina de Revolución y Altamirano. ?','2019-11-12 12:15:12'),(9,'miguel20','bandita, los quiero invitar a una fiesta en pacho denominado el pablofest, habra pambazos ','2019-11-22 12:35:29');
/*!40000 ALTER TABLE `noticia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(50) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Mascota` varchar(45) NOT NULL,
  `Estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Carlos','1234','5517716043','firulais','Bloqueado'),(2,'Luis','123456','2283172578','firulais','Activo'),(3,'Pablo','1234','1234567890','firulais','Bloqueado'),(4,'Yisus','1234','1234567890','firulais','Activo'),(10,'pop','1234','1','pop','Bloqueado'),(11,'lol','1234','123445','lol','Activo'),(12,'Jose','jose12','1234','pichi','Bloqueado'),(13,'miguel20','matematicas','8161528','chato','Activo');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-25  9:08:06
