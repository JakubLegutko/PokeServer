-- MySQL Script generated by MySQL Workbench
-- Mon Jun 12 18:33:00 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema userbase
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema userbase
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `userbase` DEFAULT CHARACTER SET utf8mb3 ;
USE `userbase` ;

-- -----------------------------------------------------
-- Table `userbase`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userbase`.`roles` (
                                                  `role_id` INT NOT NULL AUTO_INCREMENT,
                                                  `role` VARCHAR(255) NULL DEFAULT NULL,
                                                  PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `userbase`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userbase`.`users` (
                                                  `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                  `email` VARCHAR(45) NOT NULL,
                                                  `first_name` VARCHAR(20) NOT NULL,
                                                  `last_name` VARCHAR(20) NOT NULL,
                                                  `password` VARCHAR(64) NOT NULL,
                                                  PRIMARY KEY (`id`),
                                                  UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `userbase`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userbase`.`users_roles` (
                                                        `user_id` BIGINT NOT NULL,
                                                        `role_id` INT NOT NULL,
                                                        PRIMARY KEY (`user_id`, `role_id`),
                                                        INDEX `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id` ASC) VISIBLE,
                                                        CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa`
                                                            FOREIGN KEY (`user_id`)
                                                                REFERENCES `userbase`.`users` (`id`),
                                                        CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy`
                                                            FOREIGN KEY (`role_id`)
                                                                REFERENCES `userbase`.`roles` (`role_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `userbase`.`leagues`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userbase`.`leagues` (
                                                    `id` INT NOT NULL,
                                                    `name` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `userbase`.`leagues_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userbase`.`leagues_users` (
                                                          `league_id` INT NOT NULL,
                                                          `user_id` BIGINT NOT NULL,
                                                          PRIMARY KEY (`league_id`, `user_id`),
                                                          INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
                                                          CONSTRAINT `user_id`
                                                              FOREIGN KEY (`user_id`)
                                                                  REFERENCES `userbase`.`users` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION,
                                                          CONSTRAINT `league_id`
                                                              FOREIGN KEY (`league_id`)
                                                                  REFERENCES `userbase`.`leagues` (`id`)
                                                                  ON DELETE NO ACTION
                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
