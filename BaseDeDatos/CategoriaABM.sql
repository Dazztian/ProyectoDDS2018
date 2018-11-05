CREATE SCHEMA IF NOT EXISTS `entrega_persistencia` DEFAULT CHARACTER SET utf8 ;

USE entrega_persistencia;

DROP TABLE categoria;

CREATE TABLE IF NOT EXISTS `entrega_persistencia`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `cargoFijo` DOUBLE NOT NULL,
  `cargoVariable` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

INSERT INTO categoria(id, tipo, cargoFijo, cargoVariable)
Values(1,"R1", 12.5,19.85);

select * from categoria;