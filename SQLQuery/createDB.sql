CREATE DATABASE personalblog;
USE personalblog;

CREATE TABLE `user` (
	`id` BIGINT NOT NULL auto_increment,
    `firstname` VARCHAR(30) NULL DEFAULT NULL,
    `lastname` VARCHAR(30) NULL DEFAULT NULL,
    `mobile` VARCHAR(15) NULL,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(32) NOT NULL,
    `image` blob,
    `registerAt` VARCHAR(20) NOT NULL,
    `lastLogin` VARCHAR(20) NULL DEFAULT NULL,
    `activate` VARCHAR(1) NOT NULL DEFAULT '0',
    `hashcode` VARCHAR(100) NULL DEFAULT NULL,
	primary key(`id`),
    unique index `uq_mobile` (`mobile` ASC),
    unique index `uq_email` (`email` ASC),
    unique index `uq_hashcode` (`hashcode` ASC)
);

CREATE TABLE `post` (
	`id` BIGINT NOT NULL auto_increment,
    `authorId` BIGINT NOT NULL,
    `title` VARCHAR(75) NOT NULL,
	`updatedAt` DATETIME NULL DEFAULT NULL,
	`publishedAt` DATETIME NULL DEFAULT NULL,
	`content` TEXT NULL DEFAULT NULL,
    PRIMARY KEY(`id`),
    INDEX `idx_post_user` (`authorId` ASC),
    CONSTRAINT `fk_post_user`
    FOREIGN KEY (`authorId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `comment` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
  `postId` BIGINT NOT NULL,
  `content` TEXT NULL default null,
  `publishedAt` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_comment_post` (`postId` ASC),
   CONSTRAINT `fk_comment_post`
    FOREIGN KEY (`postId`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `category`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
  `parentId` BIGINT NULL DEFAULT NULL,
  `categoryName` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `category` 
ADD INDEX `idx_category_parent` (`parentId` ASC);
ALTER TABLE `category` 
ADD CONSTRAINT `fk_category_parent`
  FOREIGN KEY (`parentId`)
  REFERENCES `category` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
CREATE TABLE `post_category` (
	`postId` BIGINT NOT NULL,
    `categoryId` BIGINT NOT NULL,
    PRIMARY KEY (`postId`, `categoryId`),
  INDEX `idx_pc_category` (`categoryId` ASC),
  INDEX `idx_pc_post` (`postId` ASC),
  CONSTRAINT `fk_pc_post`
    FOREIGN KEY (`postId`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pc_category`
    FOREIGN KEY (`categoryId`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);