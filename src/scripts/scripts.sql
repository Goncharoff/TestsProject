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

ALTER TABLE `tester_app_prod_db`.`questions`
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

ALTER TABLE `tester_app_prod_db`.`test_items`
DROP FOREIGN KEY `test_items_ibfk_1`;
ALTER TABLE `tester_app_prod_db`.`test_items`
DROP INDEX `language_id` ;
;

ALTER TABLE `tester_app_prod_db`.`test_items`
CHANGE COLUMN `duration` `duration` BIGINT(150) NOT NULL ;

INSERT INTO `tester_app_prod_db`.`type_questions`
(`type_id`,
`type_name`)
VALUES
(1,
'one');
SELECT * FROM tester_app_prod_db.type_questions;

INSERT INTO `tester_app_prod_db`.`type_questions`
(`type_id`,
`type_name`)
VALUES
(2,
'multy');
SELECT * FROM tester_app_prod_db.type_questions;


INSERT INTO `tester_app_prod_db`.`questions`
(`question_id`,
`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
(1,
'1 quest',
'some url',
'1',
'1');
SELECT * FROM tester_app_prod_db.questions;

ALTER TABLE `tester_app_prod_db`.`test_items`
DROP COLUMN `user_have_test_id`;

INSERT INTO `tester_app_prod_db`.`test_items`
(
`language_id`,
`name`,
`description`,
`theme`,
`duration`)
VALUES
(1,
'Advanced java',
'Test for checking advanced topics in java.',
'Java',
15);

INSERT INTO `tester_app_prod_db`.`test_items` ( `language_id`, `name`, `description`, `theme`, `duration`) VALUES (1, 'Java Simple Test', 'Test which covers basics of java programm language.', 'Java', 15)

INSERT INTO `tester_app_prod_db`.`test_items` ( `language_id`, `name`, `description`, `theme`, `duration`) VALUES (2, 'English language test', 'Basic english grammar and vocabruary test.', 'English language', 10)

INSERT INTO `tester_app_prod_db`.`test_items` ( `language_id`, `name`, `description`, `theme`, `duration`) VALUES (2, 'Шаблоны проектирования.', 'Тест для проверки знаний о шаблонах проектирования.', 'Программирование', 10)

INSERT INTO `tester_app_prod_db`.`test_items` ( `language_id`, `name`, `description`, `theme`, `duration`) VALUES (1, 'Advanced english.', 'Test for checking advancde knowladge in enlish.', 'English language', 15)


INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(5,
'Whole',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(5,
'All',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(5,
'Real',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(5,
'Exact',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(4,
'Same',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(4,
'Similiar',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(4,
'As',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(4,
'Like',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(3,
'Watched',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(3,
'Looked',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(3,
'Saw',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(3,
'Viewed',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(2,
'Already',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(2,
'Till',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(2,
'Still',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(2,
'Yet',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(1,
'Talking',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(1,
'Telling',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(1,
'Speaking',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(1,
'Saying',
true,
'');

ALTER TABLE questions ADD COLUMN test_items_id INT NOT NULL;

UPDATE `tester_app_prod_db`.`questions` SET `test_items_id` = 2 WHERE `question_id` = 1;
UPDATE `tester_app_prod_db`.`questions` SET `test_items_id` = 2 WHERE `question_id` = 2;
UPDATE `tester_app_prod_db`.`questions` SET `test_items_id` = 2 WHERE `question_id` = 3;
UPDATE `tester_app_prod_db`.`questions` SET `test_items_id` = 2 WHERE `question_id` = 4;
UPDATE `tester_app_prod_db`.`questions` SET `test_items_id` = 2 WHERE `question_id` = 5;


INSERT INTO `tester_app_prod_db`.`questions`
(`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
('How often do you play tennis?',
'',
1,
5);

INSERT INTO `tester_app_prod_db`.`questions`
(`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
('Where do you usually eat lunch?',
'',
1,
5);

INSERT INTO `tester_app_prod_db`.`questions`
(`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
('How long did you study last night?',
'',
1,
5);

INSERT INTO `tester_app_prod_db`.`questions`
(`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
('What kind of novels do you like?',
'',
1,
5);

INSERT INTO `tester_app_prod_db`.`questions`
(`question_text`,
`question_image_url`,
`type_id`,
`test_items_id`)
VALUES
('What kind of work do you do?',
'',
1,
5);

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(11,
'On Tuesday.',
false,
'');


INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(12,
'In the cafeteria.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(12,
'Sandwich.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(12,
'With Jane.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(12,
'At 12:00.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(13,
'With Bob.',
false,
'');


INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(13,
'In my room.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(13,
'English.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(13,
'For three hours.',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(14,
'I like spy novels.',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(15,
'I\'m a piano teacher.',
true,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(15,
'I work every day.',
false,
'');

INSERT INTO `tester_app_prod_db`.`answers`
(`question_id`,
`answer`,
`isCorrect`,
`error_descr`)
VALUES
(15,
'I worked for two hours.',
false,
'');




