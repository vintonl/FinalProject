-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema geardb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `geardb` ;

-- -----------------------------------------------------
-- Schema geardb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `geardb` DEFAULT CHARACTER SET utf8 ;
USE `geardb` ;

-- -----------------------------------------------------
-- Table `shopper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopper` ;

CREATE TABLE IF NOT EXISTS `shopper` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lender` ;

CREATE TABLE IF NOT EXISTS `lender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `gear` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `address_id` INT NULL,
  `created_at` DATE NULL,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `admin` TINYINT NULL DEFAULT 0,
  `shopper_id` INT NULL,
  `lender_id` INT NULL,
  `image_url` TEXT NULL,
  `about` TEXT NULL,
  `username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_shopper1_idx` (`shopper_id` ASC),
  INDEX `fk_user_lender1_idx` (`lender_id` ASC),
  CONSTRAINT `fk_user_shopper1`
    FOREIGN KEY (`shopper_id`)
    REFERENCES `shopper` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_lender1`
    FOREIGN KEY (`lender_id`)
    REFERENCES `lender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(100) NULL,
  `address2` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(45) NULL,
  `postal_code` INT NULL,
  `country` VARCHAR(100) NULL,
  `phone` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_address_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gear`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gear` ;

CREATE TABLE IF NOT EXISTS `gear` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `type` VARCHAR(100) NULL,
  `condition` VARCHAR(100) NULL,
  `price` DOUBLE NULL,
  `description` TEXT NULL,
  `image_url` TEXT NULL DEFAULT NULL,
  `lender_id` INT NOT NULL,
  `available` TINYINT NOT NULL DEFAULT 1,
  `active` TINYINT NOT NULL DEFAULT 1,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`, `category_id`),
  INDEX `fk_gear_lender1_idx` (`lender_id` ASC),
  INDEX `fk_gear_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_gear_lender1`
    FOREIGN KEY (`lender_id`)
    REFERENCES `lender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gear_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `open_date` DATE NULL,
  `close_date` DATE NULL,
  `shopper_id` INT NOT NULL,
  `lender_id` INT NOT NULL,
  `gear_id` INT NOT NULL,
  `completed` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_shopper1_idx` (`shopper_id` ASC),
  INDEX `fk_reservation_lender1_idx` (`lender_id` ASC),
  INDEX `fk_reservation_gear1_idx` (`gear_id` ASC),
  CONSTRAINT `fk_reservation_shopper1`
    FOREIGN KEY (`shopper_id`)
    REFERENCES `shopper` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_lender1`
    FOREIGN KEY (`lender_id`)
    REFERENCES `lender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_gear1`
    FOREIGN KEY (`gear_id`)
    REFERENCES `gear` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_of_lender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_of_lender` ;

CREATE TABLE IF NOT EXISTS `review_of_lender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `review` VARCHAR(300) NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_of_lender_reservation1_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_review_of_lender_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_of_shopper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_of_shopper` ;

CREATE TABLE IF NOT EXISTS `review_of_shopper` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `review` VARCHAR(300) NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_of_shopper_reservation1_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_review_of_shopper_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_of_gear`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_of_gear` ;

CREATE TABLE IF NOT EXISTS `review_of_gear` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `review` VARCHAR(300) NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_of_gear_reservation1_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_review_of_gear_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS gear@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gear'@'localhost' IDENTIFIED BY 'gear';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'gear'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `address_id`, `created_at`, `updated_at`, `admin`, `shopper_id`, `lender_id`, `image_url`, `about`, `username`) VALUES (1, 'gear', 'silo', 'gearsilo@aol.com', 'gear', 1, NULL, NULL, NULL, 1, 1, NULL, NULL, 'gear');

COMMIT;

