-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: entreculturas
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `delegaciones`
--

DROP TABLE IF EXISTS `delegaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delegaciones` (
                                `idDelegacion` int NOT NULL AUTO_INCREMENT,
                                `nombre` varchar(45) DEFAULT NULL,
                                `direccion` text,
                                `telefono` int DEFAULT NULL,
                                `email` varchar(45) DEFAULT NULL,
                                `prefijo` int DEFAULT NULL,
                                PRIMARY KEY (`idDelegacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delegaciones`
--

LOCK TABLES `delegaciones` WRITE;
/*!40000 ALTER TABLE `delegaciones` DISABLE KEYS */;
INSERT INTO `delegaciones` VALUES (1,'Barcelona','Balmes 234',934225687,'barcelona@entreculturas.org',NULL);
/*!40000 ALTER TABLE `delegaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineas`
--

DROP TABLE IF EXISTS `lineas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineas` (
                          `idLinea` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(45) NOT NULL,
                          `lineaPadreId` varchar(45) DEFAULT NULL,
                          `idProyecto` int DEFAULT NULL,
                          PRIMARY KEY (`idLinea`),
                          KEY `idProyecto_idx` (`idProyecto`),
                          KEY `lineaPadreId` (`idLinea`),
                          CONSTRAINT `idProyecto_linea` FOREIGN KEY (`idProyecto`) REFERENCES `proyectos` (`idProyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas`
--

LOCK TABLES `lineas` WRITE;
/*!40000 ALTER TABLE `lineas` DISABLE KEYS */;
INSERT INTO `lineas` VALUES (6,'Acción humanitaria','0',8),(7,'Acción humanitaria','0',9),(8,'Acción humanitaria','0',10),(9,'Acción humanitaria','0',11),(10,'Acción humanitaria','0',12),(11,'Acción humanitaria','0',13),(12,'Acción humanitaria','0',14),(13,'Acción humanitaria','0',15),(14,'Acción humanitaria','0',16),(15,'Acción humanitaria','0',17),(16,'Acción humanitaria','0',20),(17,'Agua para todos','0',3),(18,'Mejorar la vida en las barracas','16',3),(19,'Agua para todos','0',3),(20,'Aguas turbias','16',3);
/*!40000 ALTER TABLE `lineas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
                          `idPais` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(45) NOT NULL,
                          PRIMARY KEY (`idPais`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (1,'Dinamarca'),(2,'Sevilla'),(3,'Sevilla'),(4,'Sevilla'),(5,'Sevilla'),(6,'Sevilla'),(7,'Sevilla'),(8,'Sevilla'),(9,'Sevilla');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos` (
                             `idProyecto` int NOT NULL AUTO_INCREMENT,
                             `nombre` varchar(50) NOT NULL,
                             `fechaInicio` datetime NOT NULL,
                             `fechaFin` datetime NOT NULL,
                             `financiador` varchar(45) NOT NULL,
                             `financiacion` int NOT NULL,
                             `socioLocal` int NOT NULL,
                             `idDelegacion` int NOT NULL,
                             `idPais` int NOT NULL,
                             PRIMARY KEY (`idProyecto`),
                             KEY `idSocio_idx` (`socioLocal`),
                             KEY `idDelegacion_idx` (`idDelegacion`),
                             KEY `idPais_ix` (`idPais`),
                             CONSTRAINT `idDelegacion_proyecto` FOREIGN KEY (`idDelegacion`) REFERENCES `delegaciones` (`idDelegacion`),
                             CONSTRAINT `idSocio_proyecto` FOREIGN KEY (`socioLocal`) REFERENCES `socios` (`idSocio`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (3,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(4,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(5,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(6,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(7,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(8,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(9,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(10,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(11,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(12,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(13,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(14,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(15,'Agua para todos','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(16,'Manos unidas','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(17,'Manos unidas','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(18,'Manos unidas','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(19,'Manos unidas','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1),(20,'Manos unidas','2022-08-10 00:00:00','2023-10-15 00:00:00','Caixa Bank',1000000,1,1,1);
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socios` (
                          `idSocio` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(45) NOT NULL,
                          `DNI` varchar(45) NOT NULL,
                          `tipoCuota` varchar(45) DEFAULT NULL,
                          `idProyecto` int DEFAULT NULL,
                          PRIMARY KEY (`idSocio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'ferran','47652026M','mensual',1);
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-11 21:25:27
