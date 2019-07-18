CREATE SCHEMA `tester_app_prod_db` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `tester_app_prod_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(254) NOT NULL,
  `user_password` VARCHAR(25) NOT NULL,
  `user_role` INT NOT NULL,
  `user_name` VARCHAR(75) NOT NULL,
  `user_surname` VARCHAR(150) NOT NULL,
  `user_have_tests` INT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'database for user info ';

CREATE TABLE `tester_app_prod_db`.`user_roles` (
  `id_user_roles` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_user_roles`));

INSERT INTO `tester_app_prod_db`.`user_roles`
(`id_user_roles`,
`role_name`)
VALUES
(1,
'ADMIN');

INSERT INTO `tester_app_prod_db`.`user_roles`
(`id_user_roles`,
`role_name`)
VALUES
(2,
'USER');

ALTER TABLE `tester_app_prod_db`.`user`
CHANGE COLUMN `user_role` `user_role` INT(11) NOT NULL DEFAULT 2 ;

ALTER TABLE `tester_app_prod_db`.`user`
ADD UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
ADD UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE;
;

ALTER TABLE `tester_app_prod_db`.`user`
CHANGE COLUMN `user_password` `user_password` VARCHAR(500) NOT NULL ;
