SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `commentsDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `commentsDB` ;

-- -----------------------------------------------------
-- Table `commentsDB`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `commentsDB`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `commentsDB`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `commentsDB`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(80) NOT NULL ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_role_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id` )
    REFERENCES `commentsDB`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `commentsDB`.`comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `commentsDB`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(500) NOT NULL ,
  `confirmed` TINYINT(1) NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_comment_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `commentsDB`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `commentsDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
