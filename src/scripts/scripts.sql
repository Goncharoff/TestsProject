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

CREATE TABLE `tester_app_prod_db`.`user_statistic` (
  `user_statistic_id` INT NOT NULL AUTO_INCREMENT,
  `correct_answered` INT NULL,
  `all_questions_passed` INT NOT NULL,
  `test_name` VARCHAR(250) NOT NULL,
  `date_recordet` DATE NOT NULL,
  PRIMARY KEY (`user_statistic_id`),
  UNIQUE INDEX `user_statistic_id_UNIQUE` (`user_statistic_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'table for user statistic';

CREATE TABLE `tester_app_prod_db`.`user_have_test` (
  `user_have_test_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`user_have_test_id`),
  UNIQUE INDEX `user_have_test_id_UNIQUE` (`user_have_test_id` ASC) VISIBLE);

  CREATE TABLE `tester_app_prod_db`.`test_item` (
  `test_item_id` INT NOT NULL AUTO_INCREMENT,
  `user_have_test_id` INT NULL,
  `language_id` INT NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `theme` VARCHAR(150) NOT NULL,
  `duration` LONGBLOB NOT NULL,
  `question_id` VARCHAR(45) NULL,
  PRIMARY KEY (`test_item_id`))
COMMENT = 'table for metadata about tests';

ALTER TABLE `tester_app_prod_db`.`user`
CHANGE COLUMN `user_have_tests` `user_have_tests_id` INT(11) NULL DEFAULT NULL ;


CREATE TABLE `tester_app_prod_db`.`language` (
  `language_id` INT NOT NULL AUTO_INCREMENT,
  `language_name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`language_id`),
  UNIQUE INDEX `language_id_UNIQUE` (`language_id` ASC) VISIBLE,
  UNIQUE INDEX `language_name_UNIQUE` (`language_name` ASC) VISIBLE)
COMMENT = 'domain table for setting languages of test';

CREATE TABLE `tester_app_prod_db`.`question` (
  `question_id` INT NOT NULL AUTO_INCREMENT,
  `question_text` VARCHAR(300) NOT NULL,
  `question_image_url` VARCHAR(512) NULL,
  PRIMARY KEY (`question_id`),
  UNIQUE INDEX `idquestion_UNIQUE` (`question_id` ASC) VISIBLE)
COMMENT = 'table for question';

CREATE TABLE `tester_app_prod_db`.`answers` (
  `answer_id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NOT NULL,
  `answer` TEXT NOT NULL,
  `isCorrect` TINYINT NOT NULL,
  `error_descr` VARCHAR(300) NULL,
  PRIMARY KEY (`answer_id`),
  UNIQUE INDEX `idanswers_UNIQUE` (`answer_id` ASC) VISIBLE)
COMMENT = 'Answers for questions';

ALTER TABLE `tester_app_prod_db`.`question`
ADD COLUMN `type_id` INT NOT NULL AFTER `question_image_url`;

CREATE TABLE `tester_app_prod_db`.`new_table` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(15) NOT NULL,
  UNIQUE INDEX `type_id_UNIQUE` (`type_id` ASC) VISIBLE,
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC) VISIBLE);

  ALTER TABLE `tester_app_prod_db`.`new_table`
RENAME TO  `tester_app_prod_db`.`type_question` ;

ALTER TABLE `tester_app_prod_db`.`language`
RENAME TO  `tester_app_prod_db`.`languages` ;

ALTER TABLE `tester_app_prod_db`.`type_question`
RENAME TO  `tester_app_prod_db`.`type_questions` ;

ALTER TABLE `tester_app_prod_db`.`question`
RENAME TO  `tester_app_prod_db`.`questions` ;

ALTER TABLE `tester_app_prod_db`.`test_item`
RENAME TO  `tester_app_prod_db`.`test_items` ;

ALTER TABLE `tester_app_prod_db`.`user`
RENAME TO  `tester_app_prod_db`.`users` ;

ALTER TABLE `tester_app_prod_db`.`user_have_test`
RENAME TO  `tester_app_prod_db`.`user_have_tests` ;

ALTER TABLE user_have_tests ADD FOREIGN KEY (`user_id`) REFERENCES users(`user_id`);

ALTER TABLE `tester_app_prod_db`.`users`
DROP COLUMN `user_have_tests_id`;

ALTER TABLE users ADD FOREIGN KEY (`user_role`) REFERENCES user_roles(`id_user_roles`);

ALTER TABLE user_have_tests ADD FOREIGN KEY (`test_id`) REFERENCES test_items(`test_item_id`);

ALTER TABLE test_items ADD FOREIGN KEY (`language_id`) REFERENCES languages(`language_id`);

ALTER TABLE questions ADD FOREIGN KEY (`question_id`) REFERENCES questions(`question_id`);

ALTER TABLE questions ADD FOREIGN KEY (`type_id`) REFERENCES type_questions(`type_id`);

ALTER TABLE `tester_app_prod_db`.`questions`
DROP FOREIGN KEY `questions_ibfk_1`;

ALTER TABLE answers ADD FOREIGN KEY (`question_id`) REFERENCES questions(`question_id`);

ALTER TABLE `tester_app_prod_db`.`user_statistic`
ADD COLUMN `user_id` INT NOT NULL AFTER `date_recordet`;

ALTER TABLE user_statistic ADD FOREIGN KEY (`user_id`) REFERENCES users(`user_id`);

ALTER TABLE `tester_app_prod_db`.`test_items`
DROP COLUMN `question_id`;

ALTER TABLE `tester_app_prod_db`.`questions`
ADD COLUMN `test_items_id` INT NOT NULL AFTER `type_id`;

ALTER TABLE questions ADD FOREIGN KEY (`test_items_id`) REFERENCES test_items(`test_item_id`);

ALTER TABLE `tester_app_prod_db`.`user_statistic`
CHANGE COLUMN `date_recordet` `date_recordet` DATE NOT NULL DEFAULT DEFAULT CURRENT_TIMESTAMP ;

ALTER TABLE `tester_app_prod_db`.`user_statistic`
CHANGE COLUMN `date_recordet` `date_recorded` DATE NOT NULL ;







