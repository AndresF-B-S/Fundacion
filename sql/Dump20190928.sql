CREATE DATABASE  IF NOT EXISTS `fundacion` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fundacion`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fundacion
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.31-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beneficiario`
--

DROP TABLE IF EXISTS `beneficiario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiario` (
  `idnivel_educativo` int(11) NOT NULL,
  `usuarios_idusuario` int(11) NOT NULL,
  `datos_clinicos_id` int(11) NOT NULL,
  `tutor_idTutor` int(11) NOT NULL,
  PRIMARY KEY (`usuarios_idusuario`),
  KEY `fk_beneficiario_nivel_educativo1_idx` (`idnivel_educativo`),
  KEY `fk_beneficiario_usuarios1_idx` (`usuarios_idusuario`),
  KEY `fk_beneficiario_datos_clinicos1_idx` (`datos_clinicos_id`),
  KEY `fk_beneficiario_tutor1_idx` (`tutor_idTutor`),
  CONSTRAINT `fk_beneficiario_datos_clinicos1` FOREIGN KEY (`datos_clinicos_id`) REFERENCES `datos_clinicos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_beneficiario_nivel_educativo1` FOREIGN KEY (`idnivel_educativo`) REFERENCES `nivel_educativo` (`idnivel_educativo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_beneficiario_tutor1` FOREIGN KEY (`tutor_idTutor`) REFERENCES `tutor` (`idTutor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_beneficiario_usuarios1` FOREIGN KEY (`usuarios_idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiario`
--

LOCK TABLES `beneficiario` WRITE;
/*!40000 ALTER TABLE `beneficiario` DISABLE KEYS */;
INSERT INTO `beneficiario` VALUES (4,13,4,1),(5,15,5,1),(2,18,6,2),(2,19,7,2),(1,20,8,1),(1,21,9,1),(3,22,10,2),(1,23,11,1),(5,24,12,1),(1,25,13,1),(2,28,16,2),(4,29,17,2);
/*!40000 ALTER TABLE `beneficiario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_clinicos`
--

DROP TABLE IF EXISTS `datos_clinicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datos_clinicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `nombre_servicio_salud` varchar(45) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `tipo_sangre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_clinicos`
--

LOCK TABLES `datos_clinicos` WRITE;
/*!40000 ALTER TABLE `datos_clinicos` DISABLE KEYS */;
INSERT INTO `datos_clinicos` VALUES (1,'1','1',1,'1'),(2,'sdfsdf','sdfsdf',1,'1'),(3,'sdfsf','sdfsdf',1,'sdf'),(4,'sdfgfds','zsxasxc',1,'o+'),(5,'No tiene ningún antecedente medico','Servicio occidental de salud',1,'o+'),(6,'wasdasdadadad','asdasdad',1,'A+'),(7,'asdasd','asdasd',1,'A+'),(8,'asdasd','asdasd',1,'A+'),(9,'asdasd','asdasd',1,'A+'),(10,'Tiene sisben','Cafesalud',0,'a-'),(11,'Tiene sisben','Cafesalud',1,'a-'),(12,'Tiene sisben','Cafesalud',1,'A+'),(13,'Tiene sisben','Cafesalud',1,'A+'),(14,'Tiene sisben','Cafesalud',1,'A+'),(15,'Tiene sisben','Cafesalud',1,'A-'),(16,'Tiene sisben','Cafesalud',1,'A-'),(17,'asdas','asdasd',1,'O+');
/*!40000 ALTER TABLE `datos_clinicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialvivienda`
--

DROP TABLE IF EXISTS `materialvivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialvivienda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_material` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialvivienda`
--

LOCK TABLES `materialvivienda` WRITE;
/*!40000 ALTER TABLE `materialvivienda` DISABLE KEYS */;
INSERT INTO `materialvivienda` VALUES (1,'Cemento y Bloque '),(2,'Solo bloque'),(3,'Tabique'),(4,'Madera'),(5,'Piedra'),(6,'Prefabricado'),(7,'Lamina '),(8,'Lamina de concreto');
/*!40000 ALTER TABLE `materialvivienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_educativo`
--

DROP TABLE IF EXISTS `nivel_educativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel_educativo` (
  `idnivel_educativo` int(11) NOT NULL AUTO_INCREMENT,
  `nivel_educativo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idnivel_educativo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_educativo`
--

LOCK TABLES `nivel_educativo` WRITE;
/*!40000 ALTER TABLE `nivel_educativo` DISABLE KEYS */;
INSERT INTO `nivel_educativo` VALUES (1,'Primaria'),(2,'Bachiller'),(3,'Tecnico'),(4,'Tecnologo'),(5,'Profesional');
/*!40000 ALTER TABLE `nivel_educativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `techos`
--

DROP TABLE IF EXISTS `techos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `techos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `techos`
--

LOCK TABLES `techos` WRITE;
/*!40000 ALTER TABLE `techos` DISABLE KEYS */;
INSERT INTO `techos` VALUES (1,'Cemento'),(2,'Madera'),(3,'Lamina'),(4,'Teja plastica'),(5,'Teja Asbesto'),(6,'Metalico');
/*!40000 ALTER TABLE `techos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_documento`
--

DROP TABLE IF EXISTS `tipo_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_documento` (
  `idtipos_documento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `sigla` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`idtipos_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_documento`
--

LOCK TABLES `tipo_documento` WRITE;
/*!40000 ALTER TABLE `tipo_documento` DISABLE KEYS */;
INSERT INTO `tipo_documento` VALUES (1,'Tarjeta de identidad','T.I'),(2,'Cedula de ciudadania','C.C');
/*!40000 ALTER TABLE `tipo_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_vivienda`
--

DROP TABLE IF EXISTS `tipo_vivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_vivienda` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_vivienda`
--

LOCK TABLES `tipo_vivienda` WRITE;
/*!40000 ALTER TABLE `tipo_vivienda` DISABLE KEYS */;
INSERT INTO `tipo_vivienda` VALUES (1,'Casa',NULL),(2,'Apartamento',NULL),(3,'Vivienda Colectiva',NULL);
/*!40000 ALTER TABLE `tipo_vivienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_usuarios`
--

DROP TABLE IF EXISTS `tipos_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_usuarios` (
  `idtipos_usuarios` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipos_usuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_usuarios`
--

LOCK TABLES `tipos_usuarios` WRITE;
/*!40000 ALTER TABLE `tipos_usuarios` DISABLE KEYS */;
INSERT INTO `tipos_usuarios` VALUES (1,'Administrador'),(2,'Beneficiario'),(3,'Tutor');
/*!40000 ALTER TABLE `tipos_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutor` (
  `idTutor` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idTutor`),
  KEY `fk_Tutor_usuarios1_idx` (`idusuario`),
  CONSTRAINT `fk_Tutor_usuarios1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES (1,10),(2,17);
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `documento` bigint(20) NOT NULL,
  `idtipos_documento` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `genero` tinyint(3) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `ruta_foto` varchar(100) DEFAULT NULL,
  `idtipos_usuarios` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuarios_tipo_documento1_idx` (`idtipos_documento`),
  KEY `fk_usuarios_tipos_usuarios1_idx` (`idtipos_usuarios`),
  CONSTRAINT `fk_usuarios_tipo_documento1` FOREIGN KEY (`idtipos_documento`) REFERENCES `tipo_documento` (`idtipos_documento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_tipos_usuarios1` FOREIGN KEY (`idtipos_usuarios`) REFERENCES `tipos_usuarios` (`idtipos_usuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Andres','Bustos',1,2,'andres@gmail.com','1',1,'1',NULL,1),(2,'Zoey ','Deutch',11,2,'zoey@gmail.com','123',1,'1',NULL,1),(3,'Madison ','Iseman',111,2,'madison@gmail.com','1234',2,'1',NULL,1),(4,'Madison ','Deutch',2323,2,'madisondeutch@gmail.com','2121',2,'1',NULL,1),(5,'cobie','smulders',5555,2,'cobie@gmail.com','12',2,'1',NULL,1),(6,'Melisa ','burska Pamuk',56,2,'melisa@gmail.com','222',2,'1',NULL,2),(7,'Melisa ','Hernandez',454545,2,'melisa01@gmail.com','4444',2,'1',NULL,1),(8,'Valentina ','Hernandez',1,2,'vale01@gmail.com','1',2,'1',NULL,1),(9,'cobie','smulders',1,2,'zoey@gmail.com','',1,'1',NULL,2),(10,'Paolo','Guitierrez',12,2,'sd','1',1,'1',NULL,3),(11,'vbnbvcxz','zxcvbv',11,2,'zoey@gmail.com','',1,'1',NULL,2),(12,'Emma','smulders',1,2,'emmastone@gmail.com','',2,'1',NULL,2),(13,'Emma','Deutch',11,2,'emmastone@gmail.com','',2,'1',NULL,2),(14,'Pedro','Saza',11,2,'asdasd','',1,'1',NULL,3),(15,'Hayley Elizabeth','Atwell',12345,2,'hayley@gmail.com','',2,'1',NULL,2),(16,'Zaida','Ojeda',123465,1,'zoey@gmail.com','123456',2,'0',NULL,3),(17,'David','Rivas',123465,2,'david@rivas.com','123456',1,'1',NULL,3),(18,'Juan','Perez',123456789,1,'juan@perez.com','',1,'1',NULL,3),(19,'John','Doe',2468,1,'john.doe@mail.com','',1,'1',NULL,3),(20,'John','Doe',34567,1,'john.doe@mail.com','',1,'1',NULL,3),(21,'Emma','Deutch',123456789,1,'zoey@gmail.com','',1,'2',NULL,3),(22,'Jane','Doe',12345678,1,'jane@doe.com','',2,'1',NULL,3),(23,'Pepe','Sanchez',234567,1,'jane@doe.com','',1,'1',NULL,3),(24,'Jane','Doe',1243567,1,'jane@doe.com','',2,'0',NULL,3),(25,'Jane','Doe',12345678,1,'jane@doe.com','',2,'0',NULL,3),(28,'Juan','Rodriguez',654321,2,'juan.rodriguez@gmail.com','juan',1,'1','juan-rodriguez-894527b3-01be.PNG',3),(29,'gustav','piñet',444,2,'gustav@gmail.com','',1,'1','gustav-piñet-08aacdb8.png',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vivienda`
--

DROP TABLE IF EXISTS `vivienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vivienda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) NOT NULL,
  `barrio` varchar(45) NOT NULL,
  `estado_vivienda` int(11) NOT NULL,
  `tipovivienda_id` int(11) NOT NULL,
  `materialvivienda_id` int(11) NOT NULL,
  `techos_id` int(11) NOT NULL,
  `idusuario_beneficiario` int(11) NOT NULL,
  `numerodormitorios` int(11) DEFAULT NULL,
  `sala` tinyint(4) DEFAULT NULL,
  `comedor` tinyint(4) DEFAULT NULL,
  `cocina` tinyint(4) DEFAULT NULL,
  `banioprivado` tinyint(4) DEFAULT NULL,
  `baniocolectivo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vivienda_tipovivienda1_idx` (`tipovivienda_id`),
  KEY `fk_vivienda_materialvivienda1_idx` (`materialvivienda_id`),
  KEY `fk_vivienda_techos1_idx` (`techos_id`),
  KEY `fk_vivienda_beneficiario1_idx` (`idusuario_beneficiario`),
  CONSTRAINT `fk_vivienda_beneficiario1` FOREIGN KEY (`idusuario_beneficiario`) REFERENCES `beneficiario` (`usuarios_idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vivienda_materialvivienda1` FOREIGN KEY (`materialvivienda_id`) REFERENCES `materialvivienda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vivienda_techos1` FOREIGN KEY (`techos_id`) REFERENCES `techos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vivienda_tipovivienda1` FOREIGN KEY (`tipovivienda_id`) REFERENCES `tipo_vivienda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vivienda`
--

LOCK TABLES `vivienda` WRITE;
/*!40000 ALTER TABLE `vivienda` DISABLE KEYS */;
INSERT INTO `vivienda` VALUES (1,'Av Mistral','Distrito de Sants-Mont Juic',0,3,3,4,0,1,0,0,0,1,1),(2,'Carrer de Cataluna','Sant Joan Despi',1,1,5,1,0,NULL,NULL,NULL,NULL,NULL,NULL),(3,'av planadas','Sant Joan Despi',1,2,1,1,13,NULL,NULL,NULL,NULL,NULL,NULL),(4,'av sagrada familia','Sant Joan Despi',1,1,5,1,13,NULL,NULL,NULL,NULL,NULL,NULL),(5,'av del paralelo','el raval ',1,1,1,1,13,NULL,NULL,NULL,NULL,NULL,NULL),(6,'av del paralelo','el raval ',0,2,6,1,13,NULL,NULL,NULL,NULL,NULL,NULL),(7,'King st','Coven Garden ',1,1,5,1,15,NULL,NULL,NULL,NULL,NULL,NULL),(8,'Carrer de Cataluna','el raval ',0,1,1,1,13,NULL,NULL,NULL,NULL,NULL,NULL),(9,'av del paralelo','Sant Joan Despi',0,1,1,1,15,NULL,NULL,NULL,NULL,NULL,NULL),(10,'King st','el raval ',1,1,5,1,15,NULL,NULL,NULL,NULL,NULL,NULL),(11,'Carrer de Cataluna','el raval ',0,1,1,1,15,1,0,0,0,1,0),(12,'av del paralelo','el raval ',0,1,1,1,13,1,1,0,1,0,1),(13,'av sagrada familia','Coven Garden ',1,3,5,6,13,10,1,1,1,0,1);
/*!40000 ALTER TABLE `vivienda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-28 18:40:49
