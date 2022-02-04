CREATE DATABASE segurapolice;
USE segurapolice;

CREATE TABLE `cliente` (
  `cpf` varchar(11) NOT NULL,
  `cidade` varchar(200) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `uf` varchar(50) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `apolice` (
  `numero` bigint(20) NOT NULL AUTO_INCREMENT,
  `placa` varchar(7) NOT NULL,
  `valor` double NOT NULL,
  `vigencia_fim` date NOT NULL,
  `vigencia_inicio` date NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
