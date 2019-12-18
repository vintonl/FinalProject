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
  `created_at` DATE NULL,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` VARCHAR(45) NOT NULL DEFAULT 0,
  `image_url` TEXT NULL,
  `about` TEXT NULL,
  `address_id` INT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gear`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gear` ;

CREATE TABLE IF NOT EXISTS `gear` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `condition` VARCHAR(100) NULL,
  `price` DOUBLE NULL,
  `description` TEXT NULL,
  `image_url` TEXT NULL DEFAULT NULL,
  `available` TINYINT NOT NULL DEFAULT 1,
  `active` TINYINT NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gear_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_gear_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `gear_id` INT NOT NULL,
  `completed` TINYINT NOT NULL DEFAULT 0,
  `shopper_user_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_gear1_idx` (`gear_id` ASC),
  INDEX `fk_reservation_user1_idx` (`shopper_user_id` ASC),
  CONSTRAINT `fk_reservation_gear1`
    FOREIGN KEY (`gear_id`)
    REFERENCES `gear` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`shopper_user_id`)
    REFERENCES `user` (`id`)
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
-- Table `reservation_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation_message` ;

CREATE TABLE IF NOT EXISTS `reservation_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL,
  `message_date` DATETIME NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_message_reservation1_idx` (`reservation_id` ASC),
  CONSTRAINT `fk_reservation_message_reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gear_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gear_category` ;

CREATE TABLE IF NOT EXISTS `gear_category` (
  `category_id` INT NOT NULL,
  `gear_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `gear_id`),
  INDEX `fk_category_has_gear_gear1_idx` (`gear_id` ASC),
  INDEX `fk_category_has_gear_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_category_has_gear_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_gear_gear1`
    FOREIGN KEY (`gear_id`)
    REFERENCES `gear` (`id`)
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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (1, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (1, 'gear', 'silo', 'gearsilo@gmail.com', 'gear', '2019-12-17', '2019-12-17', '1', 'sdafasd', 'afdsadf', 1, '(555)555-5555');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gear`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `gear` (`id`, `name`, `condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`) VALUES (1, 'Surf Board', 'New', 50, NULL, NULL, DEFAULT, 1, 1);

COMMIT;

