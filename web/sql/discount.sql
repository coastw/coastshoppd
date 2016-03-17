CREATE TABLE `dbcompany`.`discount` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `categorynum` VARCHAR(20) NOT NULL,
  `categorychar` VARCHAR(20) NOT NULL,
  `regex` VARCHAR(20) NOT NULL,
  `persent` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `category_UNIQUE` (`categorychar` ASC),
  UNIQUE INDEX `regex_UNIQUE` (`regex` ASC),
  UNIQUE INDEX `categorynum_UNIQUE` (`categorynum` ASC));