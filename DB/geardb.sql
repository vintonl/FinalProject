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
  `password` VARCHAR(300) NOT NULL,
  `created_at` DATE NULL,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` VARCHAR(45) NOT NULL DEFAULT 0,
  `image_url` TEXT NULL,
  `about` TEXT NULL,
  `address_id` INT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `enabled` TINYINT(4) NULL DEFAULT NULL,
  `username` VARCHAR(150) NOT NULL,
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
  `gear_condition` VARCHAR(100) NULL,
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
  `open_date` DATETIME NULL,
  `close_date` DATETIME NULL,
  `gear_id` INT NOT NULL,
  `completed` TINYINT(4) NOT NULL DEFAULT 0,
  `shopper_user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved` TINYINT(4) NOT NULL,
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
  `rating` INT NULL DEFAULT 5,
  `review` VARCHAR(500) NULL DEFAULT NULL,
  `reservation_id` INT NOT NULL,
  `active` TINYINT(4) NOT NULL,
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
  `rating` INT NULL DEFAULT 5,
  `review` VARCHAR(500) NULL DEFAULT NULL,
  `reservation_id` INT NOT NULL,
  `active` TINYINT(4) NULL,
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
  `rating` INT NULL DEFAULT 5,
  `review` VARCHAR(500) NULL DEFAULT NULL,
  `reservation_id` INT NOT NULL,
  `active` TINYINT(4) NOT NULL,
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
  INDEX `fk_category_has_gear_gear1_idx` (`gear_id` ASC),
  INDEX `fk_category_has_gear_category1_idx` (`category_id` ASC),
  PRIMARY KEY (`category_id`, `gear_id`),
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
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (2, '1701 Bryant St', NULL, 'Denver', 'Colorado', 80204, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (3, '8678 Park Meadows Center Dr', NULL, 'Lone Tree', 'Colorado', 80124, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (4, '3995 S Colorado Blvd', NULL, 'Englewood', 'Colorado', 80113, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (5, '3122 S Parker Rd', NULL, 'Aurora', 'Colorado', 80114, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (6, '1510 N Clarkson St', NULL, 'Denver', 'Colorado', 80218, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (7, '9331 E Arapahoe Rd', NULL, 'Greenwood Village', 'Colorado', 80112, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (8, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (9, '8437 Park Meadows Center Dr', NULL, 'Lone Tree', 'Colorado', 80124, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (10, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (11, '7555 E Peakview Ave', NULL, 'Centennial', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (12, '7555 E Peakview Ave', NULL, 'Centennial', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (13, '7400 E Orchard Rd #1450n', NULL, 'Greenwood Village', 'Colorado', 80111, 'USA');
INSERT INTO `address` (`id`, `address`, `address2`, `city`, `state`, `postal_code`, `country`) VALUES (14, '7555 E Peakview Ave', NULL, 'Centennial', 'Colorado', 80111, 'USA');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (1, 'gear', 'silo', 'gearsilo@gmail.com', '$2a$10$BKe0NjmOywOYa6s7lux5oe3YsEfwnun5YDXzAVstY1KQTJD8a/vf.', '2019-12-17', '2019-12-17', 'admin', 'https://i.imgur.com/zVdNnTx.png', 'GearSilo OG', 1, '(555)555-5555', 1, 'gearsilo@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (2, 'Marty', 'McFly', 'martymcfly@gmail.com', '$2a$10$KQq.v/Fbsum3EuSD/VpLuOc9ANh4gExeA.Exak8DKgN68NZNCEU5K', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/PVxBA1f.jpg', 'Loves skateboarding and playing guitar. Don\'t call me chicken!', 2, '(444)444-4444', 1, 'martymcfly@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (3, 'Kelly ', 'Slater', 'kellyslater@gmail.com', '$2a$10$w3r24NRhPDJE8VTlpu/Tj.Gl8ZpPDUUp80Z2i4emq/4EpGiVEvsW2', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/y6f4lgf.jpg', 'American professional surfer widely considered the greatest surfer of all time.', 3, '(333)333-3333', 1, 'kellyslater@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (4, 'Peyton', 'Manning', 'peytonmanning@gmail.com', '$2a$10$Q8JDd.dMhYfXVfHozDzFv.M80MUuISrRS9PIjwNhj6aWptgNlwOeG', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/78VZc9g.jpg', 'Considered to be one of the greatest quarterbacks of all time.', 4, '(222)222-2222', 1, 'peytonmanning@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (5, 'Shaun ', 'White', 'shaunwhite@gmail.com', '$2a$10$XUPcE7ETOLrXEKVV95WIuOQs2PhJdZqtNHXIFa0JL987ECPidMWoC', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/FAGZlWy.jpg', 'Professional snowboarder, skateboarder and musician. He is a three-time Olympic gold medalist', 5, '(111)111-1111', 1, 'shaunwhite@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (6, 'Larry', 'Larry', 'larry@larry.com', '$2a$10$J4qcsxGxt38slljzWyOY0eFohfQz93.pcDTVTf7Vc9jTn5/Z4kQ9S', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/oHjEQpe.png', 'Outdoorsman with tons of sporting equipment.', 6, '(555)555-4444', 1, 'larry@larry.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (7, 'Vinton', 'Lee', 'vintonlee@gear.com ', '$2a$10$XNS61r1PqFc8U5u75Y5mI.sqwXSCwMC2UcnpH6efC.X7HNf505TGe', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/QKUbJU4.jpg', 'Gear Silo Development Team', 7, '(555)555-5555', 1, 'vintonlee@gear.com ');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (8, 'Jerry', 'Rogers', 'jerryrogers@gear.com', '$2a$10$Uz2u9UDLuODIEp4nNlgl0u/JvgRJk1AmH6kcqSK5kXYID/.akTS.e', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/KiTB0Np.jpg', 'Gear Silo Development Team', 8, '(555)555-5555', 1, 'jerryrogers@gear.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (9, 'Colt', 'Looper', 'coltlooper@gear.com', '$2a$10$BwE5fiEtUu.nWxXvqpVXuubDw3L3qV.FqYYhN5B2btRDT44wc8UJG', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/0ZnpWda.jpg', 'Gear Silo Development Team', 9, '(555)555-5555', 1, 'coltlooper@gear.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (10, 'Adam', 'Onwan', 'adamonwan@gear.com', '$2a$10$Wh0gxqTK6a0RPGkOjRe9tet1kQVHuqLaMiDXw75XmRa3r40p6EVsC', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/MhQ8xwV.jpg', 'Gear Silo Development Team', 10, '(555)555-5555', 1, 'adamonwan@gear.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (11, 'Brian', 'Streetman', 'brianstreetman@gear.com', '$2a$10$FJsg2Rpqm0Zfmg1QxlGaAeh.5/dtvuWRHka6JMHdZ8VdMBkR1G9i.', '2019-12-18', '2019-12-18', 'user', 'https://i.imgur.com/eYmPwPH.png', 'Gear Silo Development Team', 11, '(555)555-5555', 1, 'brianstreetman@gear.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (12, 'Chloe ', 'Kim', 'chloekim@gmail.com', '$2a$10$FJsg2Rpqm0Zfmg1QxlGaAeh.5/dtvuWRHka6JMHdZ8VdMBkR1G9i.', '2020-01-03', '2020-01-03', 'user', 'https://i.imgur.com/uqCkAPx.jpg', 'Pro Snowboarder', 12, '(777)777-7777', 1, 'chloekim@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (13, 'Kelly ', 'Clark', 'kellyclark@gmail.com', '$2a$10$FJsg2Rpqm0Zfmg1QxlGaAeh.5/dtvuWRHka6JMHdZ8VdMBkR1G9i.', '2020-01-03', '2020-01-03', 'user', 'https://i.imgur.com/1MQMchr.jpg', 'Pro Snowboarder', 13, '(888)888-8888', 1, 'kellyclark@gmail.com');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `created_at`, `updated_at`, `role`, `image_url`, `about`, `address_id`, `phone`, `enabled`, `username`) VALUES (14, 'Maria', 'Watson', 'mariawatson@gmail.com', '$2a$10$FJsg2Rpqm0Zfmg1QxlGaAeh.5/dtvuWRHka6JMHdZ8VdMBkR1G9i.', '2020-01-03', '2020-01-03', 'user', 'https://i.imgur.com/c8Rqus5.jpg', 'Tennis lover', 14, '(888)888-8889', 1, 'mariawatson@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gear`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (1, 'Mountain Bike', 'New', 50.00, 'Black,10 gear', 'https://i.imgur.com/vPbnSXC.jpg', 1, 1, 1, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (2, 'Hover Board', 'New', 9000.00, 'Pink, red, and green board. One foot strap.', 'https://i.imgur.com/CMqrSSq.jpg', 1, 1, 2, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (3, 'Surf Board', 'Solid', 100.00, 'Medium size board', 'https://i.imgur.com/43Dmmgz.jpg', 1, 1, 3, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (4, 'Snow Shoes', 'New', 30.00, 'Mens, Mountain terrain, Aluminum-frame', 'https://i.imgur.com/3wwDK0L.jpg', 1, 1, 6, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (5, 'Kayak', 'Slightly Used', 40.00, 'Orange', 'https://i.imgur.com/fn77owl.jpg', 1, 1, 4, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (6, 'Wake Surf Board', 'New', 30.00, 'Medium, grey, black, and lime green', 'https://i.imgur.com/fvTD0Os.jpg', 1, 1, 5, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (7, 'Rock Climbing Rope', 'Slightly Used', 10.00, 'Strong, red and purple', 'https://i.imgur.com/U2Sl7WV.jpg', 1, 1, 1, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (8, 'Skis', 'Good', 30.00, 'White and black', 'https://i.imgur.com/a99gARQ.jpg', 1, 1, 1, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (9, 'Snowboard', 'Almost New', 40.00, 'Freestyle, traditional camber.', 'https://i.imgur.com/7YFS4E1.jpg', 1, 1, 5, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (10, 'Wingsuit', 'Great condition', 80.00, 'Blue', 'https://i.imgur.com/NzKIe7G.jpg', 1, 1, 1, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (11, 'Electric Scooter', 'New', 15.00, 'Black', 'https://i.imgur.com/fyWAxNh.jpg', 1, 1, 10, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (12, 'Snowboard', 'Seasoned', 75.00, 'Red, Burton, and artsy', 'https://i.imgur.com/UrNiVgn.jpg', 1, 1, 12, 'https://i.imgur.com/qA87gYW.jpg', 'https://i.imgur.com/EDpzhWA.jpg');
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (13, 'Snowbaord', 'Slightly Used', 30.00, 'Freestyle, traditional camber, and floral design', 'https://i.imgur.com/jzs2voP.jpg', 1, 1, 13, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (14, 'Tennis Ball Machine', 'Great', 20.00, 'Remote control, lobster, red and white', 'https://i.imgur.com/f5gqZRv.jpg', 1, 1, 14, 'https://i.imgur.com/KE9Jbzv.jpg', NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (15, 'Electric Unicycle', 'New', 25.00, 'White, blue, and black', 'https://i.imgur.com/btOaQpU.jpg', 1, 1, 7, NULL, NULL);
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (16, 'Snowboard', 'Excellent', 30.00, 'Beautiful design, fresstlye, traditional', 'https://i.imgur.com/K7YuD8E.jpg', 1, 1, 13, '', '');
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (17, 'Snowboard', 'Excellent', 35.00, '146cm, All-Mtn, Yes brand, and Burton Escapade Bindings', 'https://i.imgur.com/q6c3xEy.jpg', 1, 1, 11, 'https://i.imgur.com/zMMkJkS.jpg', 'https://i.imgur.com/PQdYxOw.jpg');
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (18, 'Skis', 'Slightly Used', 30.00, '150cm with rad design', 'https://i.imgur.com/QNcDOSK.jpg', 1, 1, 8, 'https://i.imgur.com/gfillHd.jpg', 'https://i.imgur.com/QNcDOSK.jpg');
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (19, 'Snow Shoes', 'New', 15.00, 'Black snow shoes with poles', 'https://i.imgur.com/PcrxJWg.jpg', 1, 1, 9, 'https://i.imgur.com/RyqYLqH.jpg', 'https://i.imgur.com/fCT3aoi.jpg');
INSERT INTO `gear` (`id`, `name`, `gear_condition`, `price`, `description`, `image_url`, `available`, `active`, `user_id`, `image_url2`, `image_url3`) VALUES (20, 'Bike', 'Almost New', 20.00, 'Fixed Gear. Black and Teal', 'https://imgur.com/1yMAh12', 1, 1, 6, 'https://i.imgur.com/iDtLkpC.jpg', 'https://i.imgur.com/1X1d35m.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (1, '2019-12-19', '2019-12-19', 1, 1, 6, '2019-12-19', '2019-12-19', 1);
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (2, '2019-12-19', '2019-12-19', 2, 1, 4, '2019-12-19', '2019-12-19', 1);
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (3, '2019-12-19', '2019-12-19', 3, 1, 5, '2019-12-19', '2019-12-19', 1);
INSERT INTO `reservation` (`id`, `open_date`, `close_date`, `gear_id`, `completed`, `shopper_user_id`, `created_at`, `updated_at`, `approved`) VALUES (4, '2019-12-19', '2019-12-19', 4, 0, 7, '2019-12-19', '2019-12-19', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_lender`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (1, 5, 'Lender was timely and had great tips!', 1, 1);
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (2, 3, 'Marty was late to our reservation appointment.', 2, 1);
INSERT INTO `review_of_lender` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (3, 5, 'Wooah! I got to meet Kelly Slater! Radical!', 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_shopper`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (1, 5, 'Larry showed up on time and took great care of the bike!', 1, 1);
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (2, 5, 'Shaun was rad!', 2, 1);
INSERT INTO `review_of_shopper` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (3, 5, 'Peyton kept the Surf board in great condition and surfed like a pro! ', 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_of_gear`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (1, 4, 'Mountain Bike road well!', 1, 1);
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (2, 1, 'Hoverboard didn\'t live up to the hype...', 2, 1);
INSERT INTO `review_of_gear` (`id`, `rating`, `review`, `reservation_id`, `active`) VALUES (3, 5, 'Surf Board was awesome! Held up on some gnarly swells! ', 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Mountain Biking');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Skating');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Surf');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Hiking');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Kayaking');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Water Sports');
INSERT INTO `category` (`id`, `name`) VALUES (7, 'Rock Climbing');
INSERT INTO `category` (`id`, `name`) VALUES (8, 'Skiing');
INSERT INTO `category` (`id`, `name`) VALUES (9, 'Snowboarding');
INSERT INTO `category` (`id`, `name`) VALUES (10, 'Freefalling');
INSERT INTO `category` (`id`, `name`) VALUES (11, 'Wakeboarding');
INSERT INTO `category` (`id`, `name`) VALUES (12, 'Snow');
INSERT INTO `category` (`id`, `name`) VALUES (13, 'Water');
INSERT INTO `category` (`id`, `name`) VALUES (14, 'Mountain');
INSERT INTO `category` (`id`, `name`) VALUES (15, 'Sky');
INSERT INTO `category` (`id`, `name`) VALUES (16, 'Biking');
INSERT INTO `category` (`id`, `name`) VALUES (17, 'Winter');

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (1, 'Great looking Bike! Excited to ride it!', '2019-12-18', 1, 6);
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (2, 'I\'ve been looking for one of these forever!  Stoked to ride a real hoverboard!', '2019-12-18', 3, 6);
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (3, 'Awesome looking board Kelly! ', '2019-12-18', 2, 5);
INSERT INTO `reservation_message` (`id`, `message`, `message_date`, `reservation_id`, `shopper_user_id`) VALUES (4, 'Can\'t wait to try out them snow shoes!!', '2019-12-19', 4, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gear_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `geardb`;
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (1, 1);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (2, 2);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (3, 3);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (4, 4);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (5, 5);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (6, 6);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (7, 7);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (8, 8);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (9, 9);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (10, 10);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (11, 6);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (12, 4);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (12, 8);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (12, 9);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (13, 3);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (13, 5);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (14, 1);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (14, 8);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (14, 4);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (14, 9);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (15, 10);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (17, 8);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (6, 3);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (6, 5);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (17, 4);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (17, 9);
INSERT INTO `gear_category` (`category_id`, `gear_id`) VALUES (16, 1);

COMMIT;

