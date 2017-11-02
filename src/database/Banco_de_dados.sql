/* @Creating user table*/
CREATE TABLE `db_sebo_virtual`.`tb_user` (`id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT, `name` VARCHAR(255) NOT NULL,
`email` VARCHAR(255) NOT NULL, `document` VARCHAR(20) NOT NULL, `profile` VARCHAR(45) NOT NULL COMMENT 'Com os tipos fisica ou juridica',
`password` VARCHAR(255) NULL, `blocked` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0, `activated` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
`excluded` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0, `celPhone` VARCHAR(50) NULL, `phone` VARCHAR(50) NULL,
`createdAt` DATETIME NOT NULL DEFAULT NOW(),
`updatedAt` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW(),
  PRIMARY KEY (`id`))
COMMENT = 'Tabela com os usuarios';

/* @Creating admin table */
CREATE TABLE `db_sebo_virtual`.`tb_admin` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT NOW(),
  `updatedAt` DATETIME NOT NULL default NOW() ON UPDATE NOW(),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

  /* @Creating book table*/
  CREATE TABLE `db_sebo_virtual`.`tb_book` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255) NOT NULL,
  `publisher` VARCHAR(255) NULL,
  `isbn10` VARCHAR(45) NULL,
  `isbn13` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

/*@create product table */
CREATE TABLE `db_sebo_virtual`.`tb_product` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userId` BIGINT(32) UNSIGNED NOT NULL,
  `bookId` BIGINT(32) UNSIGNED NOT NULL,
  `description` VARCHAR(500) NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `createdAt` DATETIME NOT NULL DEFAULT NOW(),
  `updatedAt` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW(),
  active     TINYINT(1) UNSIGNED NOT NULL DEFAULT 1,
  excluded TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY unique_key (`userId`, `bookId`),

  CONSTRAINT `fk_product_user`
    FOREIGN KEY (userId)
    REFERENCES `db_sebo_virtual`.`tb_user` (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,

  CONSTRAINT `fk_product_book`
    FOREIGN KEY (bookId)
    REFERENCES `db_sebo_virtual`.`tb_book` (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION);

 /* @Creating tb_periodicty_type */
 CREATE TABLE `db_sebo_virtual`.`tb_periodicitytype` (
  `id` INT(10) UNSIGNED NOT NULL,
  `periodicitytype` VARCHAR(50) NOT NULL,
   excluded TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
   PRIMARY KEY (`id`));

    /* @Creating tb_price_type */
 CREATE TABLE `db_sebo_virtual`.`tb_pricetype` (
  `id` INT(10) UNSIGNED NOT NULL,
  `pricetype` VARCHAR(50) NOT NULL,
   excluded TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
   PRIMARY KEY (`id`));

/* @Creating tb_product_price  */
 CREATE TABLE `db_sebo_virtual`.`tb_product_price` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `productId` BIGINT(32) UNSIGNED NOT NULL,
  `priceTypeId` INT(10) UNSIGNED NOT NULL,
  `periodicity` INT(10) UNSIGNED NOT NULL,
  `periodicityTypeId` INT(10) UNSIGNED NOT NULL,
  `price` DECIMAL(19,2) NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT NOW(),
  `updatedAt` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW(),
  UNIQUE KEY unique_key (`productId`,`priceTypeId`),
  PRIMARY KEY (`id`),
  
  INDEX `fk_tb_product_price_1_idx` (`productId` ASC),
  INDEX `fk_tb_product_price_2_idx` (`priceTypeId` ASC),
  INDEX `fk_tb_product_price_3_idx` (`periodicityTypeId` ASC),
  
  CONSTRAINT `fk_tb_product_price_1`
    FOREIGN KEY (`productId`)
    REFERENCES `db_sebo_virtual`.`tb_product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
  CONSTRAINT `fk_tb_product_price_2`
    FOREIGN KEY (`priceTypeId`)
    REFERENCES `db_sebo_virtual`.`tb_pricetype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    
  CONSTRAINT `fk_tb_product_price_3`
    FOREIGN KEY (`periodicityTypeId`)
    REFERENCES `db_sebo_virtual`.`tb_periodicitytype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

  /* @Create adresstype table */
  CREATE TABLE `db_sebo_virtual`.`tb_adresstype` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `adressType` VARCHAR(50) NOT NULL,
  `excluded` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

/* @Creating adress table */
  CREATE TABLE `db_sebo_virtual`.`tb_adress` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typeAdressId` BIGINT(32) UNSIGNED NOT NULL,
  `zipCode` VARCHAR(9) NOT NULL,
  `adress` VARCHAR(255) NOT NULL,
  `number` VARCHAR(15) NULL,
  `complement` VARCHAR(45) NULL,
  `reference` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_type_adress`
    FOREIGN KEY (typeAdressId)
    REFERENCES `db_sebo_virtual`.`tb_adresstype` (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
COMMENT = 'Tabela com os endereços para permitir multiplos cadastros de cep mesmo no pedido';

/* @Create order table */
CREATE TABLE `db_sebo_virtual`.`tb_order` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userId` BIGINT(32) UNSIGNED NOT NULL,
  `adressId` BIGINT(32) UNSIGNED NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT NOW(),
  `updatedAt` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW(),
  `evaluate` VARCHAR(500) UNSIGNED NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_user_order`
    FOREIGN KEY (userId)
    REFERENCES `db_sebo_virtual`.`tb_user` (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,

  CONSTRAINT `fk_adress_order`
    FOREIGN KEY (adressId)
    REFERENCES `db_sebo_virtual`.`tb_adress` (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION);

CREATE TABLE `db_sebo_virtual`.`tb_order_product` (
  `id` BIGINT(32) UNSIGNED NOT NULL AUTO_INCREMENT,
  `orderId` BIGINT(32) UNSIGNED NOT NULL,
  `productId` BIGINT(32) UNSIGNED NOT NULL,
  `price` DECIMAL(19,2) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_order_id_idx` (`orderId` ASC),
  INDEX `fk_order_productid_idx` (`productId` ASC),
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`orderId`)
    REFERENCES `db_sebo_virtual`.`tb_order` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_productid`
    FOREIGN KEY (`productId`)
    REFERENCES `db_sebo_virtual`.`tb_product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION);

 CREATE TABLE `db_sebo_virtual`.`tb_log` (
	id BIGINT(32) unsigned NOT NULL AUTO_INCREMENT,
    logType VARCHAR(50) NOT NULL,
    relationshipId BIGINT(32) NOT NULL DEFAULT 0,
    message VARCHAR(500) NOT NULL,
  `createdAt` DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`));

 CREATE TABLE `db_sebo_virtual`.`tb_error_log` (
	id BIGINT(32) unsigned NOT NULL AUTO_INCREMENT,
    errorTrace VARCHAR(5000) NOT NULL,
    controller VARCHAR(50) NOT NULL,
    method VARCHAR(50) NOT NULL,
    `createdAt` DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
    );

INSERT INTO `db_sebo_virtual`.`tb_adresstype` (adressType)
      VALUES ('residencial'), ('trabalho'), ('outro');

  /* @Insert into tb_periodicity_type */
INSERT INTO `db_sebo_virtual`.`tb_periodicitytype` (id,periodicitytype)
     VALUES (1,'unico'), (2,'diario'), (3,'semanal'), (4,'mensal'),(5,'semestral'), (6,'anual');

INSERT INTO `db_sebo_virtual`.`tb_pricetype` (id,pricetype)
     VALUES (1,'aluguel'), (2,'venda')
