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
  `image_url2` TEXT NULL DEFAULT NULL,
  `image_url3` TEXT NULL DEFAULT NULL,
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
  `gear_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gear_id_idx` (`gear_id` ASC),
  CONSTRAINT `fk_gear_id`
    FOREIGN KEY (`gear_id`)
    REFERENCES `reservation` (`gear_id`)
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
  `shopper_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shopper_user_id_idx` (`shopper_user_id` ASC),
  CONSTRAINT `fk_shopper_user_id`
    FOREIGN KEY (`shopper_user_id`)
    REFERENCES `reservation` (`shopper_user_id`)
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
  `gear_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gear_id_idx` (`gear_id` ASC),
  CONSTRAINT `fk_gear_id`
    FOREIGN KEY (`gear_id`)
    REFERENCES `reservation` (`gear_id`)
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
  `shopper_user_id` INT NOT NULL,
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
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (2, '555 BackInTime Rd.', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (3, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (4, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (5, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (1, 'gear', 'silo', 'gearsilo@gmail.com', 'gear', '2019-12-17', '2019-12-17', 'admin', 'sdafasd', 'afdsadf', 1, '(555)555-5555');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (2, 'Marty', 'McFly', 'martymcfly@gmail.com', '88mph', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/PVxBA1f.jpg', 'Loves skateboarding and playing guitar. Don\'t call me chicken!', 2, '(444)444-4444');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (3, 'Kelly ', 'Slater', 'kellyslater@gmail.com', 'surfsup', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/y6f4lgf.jpg', 'American professional surfer widely considered the greatest surfer of all time.', 3, '(333)333-3333');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (4, 'Peyton', 'Manning', 'peytonmanning@gmail.com', 'peyton', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/78VZc9g.jpg', 'Considered to be one of the greatest quarterbacks of all time.', 4, '(222)222-2222');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (5, 'Shaun ', 'White', 'shaunwhite@gmail.com', 'shaunwhite', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/FAGZlWy.jpg', 'Professional snowboarder, skateboarder and musician. He is a three-time Olympic gold medalist', 5, '(111)111-1111');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`) VALUES (6, 'Larry', 'Larry', 'larry@larry.com', 'larry', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/oHjEQpe.png', 'Outdoorsman with tons of sporting equipment.', 6, '(555)555-4444');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gear`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `gear` (`id`, `name`, `condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (1, 'Mountain Bike', 'New', 50, 'Blackm 12 gear', 'https://i.imgur.com/vPbnSXC.jpg', 1, 1, 1, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (2, 'Hover Board', 'New', 9000, 'Pink, red, and green board. One foot strap.', 'https://i.imgur.com/CMqrSSq.jpg', 1, 1, 2, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (3, 'Surf Board', 'Solid', 100, 'Medium size board', 'https://i.imgur.com/43Dmmgz.jpg', 1, 1, 3, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (1, '2019-18-19', '2019-19-19', 1, 0, 6, '2019-18-19', '2019-18-19', 1);
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (2, '2019-18-19', '2019-19-19', 3, 0, 6, '2019-18-19', '2019-18-19', 1);
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (3, '2019-18-19', '2019-12-19', 2, 0, 5, '2019-18-19', '2019-18-19', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_lender`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `gear_id`) VALUES (1, 5, 'Lender was timely and had great tips!', 1);
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `gear_id`) VALUES (2, 3, 'Marty was late to our reservation appointment.', 2);
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `gear_id`) VALUES (3, 5, 'Wooah! I got to meet Kelly Slater! Radical!', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_shopper`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `shopper_user_id`) VALUES (1, 5, 'Larry showed up on time and took great care of the bike!', 6);
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `shopper_user_id`) VALUES (2, 5, 'Shaun was rad!', 5);
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `shopper_user_id`) VALUES (3, 5, 'Larry kept the Surf board in great condition and surfed like a pro! ', 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_gear`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `gear_id`) VALUES (1, 4, 'Mountain Bike road well!', 1);
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `gear_id`) VALUES (2, 1, 'Hoverboard didn\'t live up to the hype...', 2);
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `gear_id`) VALUES (3, 5, 'Surf Board was awesome! Held up on some gnarly swells! ', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Mountain biking');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Street Boarding');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Surf');

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (1, 'Great looking Bike! Excited to ride it!', '2019-18-19', 1, 6);
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (2, 'I\'ve been looking for one of these forever!  Stoked to ride a real hoverboard!', '2019-18-19', 3, 6);
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (3, 'Awesome looking board Kelly! ', '2019-18-19', 2, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gear_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (1, 1);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (2, 2);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (3, 3);

COMMIT;

