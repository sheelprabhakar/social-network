-- ref https://mysql.tutorials24x7.com/blog/guide-to-design-database-for-social-network-system-in-mysql
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(50) NULL,
  `passwordHash` VARCHAR(32) NOT NULL,
  `registeredAt` DATETIME NOT NULL,
  `lastLogin` DATETIME NULL DEFAULT NULL,
  `intro` TINYTEXT NULL DEFAULT NULL,
  `profile` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_username` (`username` ASC),
  UNIQUE INDEX `uq_mobile` (`mobile` ASC),
  UNIQUE INDEX `uq_email` (`email` ASC) );

  CREATE TABLE `user_friend` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `sourceId` BIGINT NOT NULL,
    `targetId` BIGINT NOT NULL,
    `type` SMALLINT NOT NULL DEFAULT 0,
    `status` SMALLINT NOT NULL DEFAULT 0,
    `createdAt` DATETIME NOT NULL,
    `updatedAt` DATETIME NULL DEFAULT NULL,
    `notes` TEXT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_friend_source` (`sourceId` ASC),
    CONSTRAINT `fk_friend_source`
      FOREIGN KEY (`sourceId`)
      REFERENCES `user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

  ALTER TABLE `user_friend`
  ADD INDEX `idx_friend_target` (`targetId` ASC);
  ALTER TABLE `user_friend`
  ADD CONSTRAINT `fk_friend_target`
    FOREIGN KEY (`targetId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

  ALTER TABLE `user_friend` ADD UNIQUE `uq_friend`(`sourceId`, `targetId`);

  CREATE TABLE `user_follower` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `sourceId` BIGINT NOT NULL,
    `targetId` BIGINT NOT NULL,
    `type` SMALLINT NOT NULL DEFAULT 0,
    `createdAt` DATETIME NOT NULL,
    `updatedAt` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_ufollower_source` (`sourceId` ASC),
    CONSTRAINT `fk_ufollower_source`
      FOREIGN KEY (`sourceId`)
      REFERENCES `user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

  ALTER TABLE `user_follower`
  ADD INDEX `idx_ufollower_target` (`targetId` ASC);
  ALTER TABLE `user_follower`
  ADD CONSTRAINT `fk_ufollower_target`
    FOREIGN KEY (`targetId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


  ALTER TABLE `user_follower` ADD UNIQUE `uq_ufollower`(`sourceId`, `targetId`, `type`);
  
  CREATE TABLE `user_post` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `userId` BIGINT NOT NULL,
    `senderId` BIGINT NOT NULL,
    `message` TINYTEXT NULL DEFAULT NULL,
    `createdAt` DATETIME NOT NULL,
    `updatedAt` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_upost_user` (`userId` ASC),
    CONSTRAINT `fk_upost_user`
      FOREIGN KEY (`userId`)
      REFERENCES `user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);
  
  ALTER TABLE `user_post` 
  ADD INDEX `idx_upost_sender` (`senderId` ASC);
  ALTER TABLE `user_post` 
  ADD CONSTRAINT `fk_upost_sender`
    FOREIGN KEY (`senderId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

  CREATE TABLE `user_comment` (
      `id` BIGINT NOT NULL AUTO_INCREMENT,
      `postId` BIGINT NOT NULL,
      `senderId` BIGINT NOT NULL,
      `message` TINYTEXT NULL DEFAULT NULL,
      `parentCommentId` BIGINT NULL DEFAULT NULL,
      `createdAt` DATETIME NOT NULL,
      `updatedAt` DATETIME NULL DEFAULT NULL,
      PRIMARY KEY (`id`),
      INDEX `idx_ucomment_post` (`postId` ASC),
      CONSTRAINT `fk_ucomment_post`
        FOREIGN KEY (`postId`)
        REFERENCES `user_post` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);

    ALTER TABLE `user_comment`
    ADD INDEX `idx_ucomment_sender` (`senderId` ASC);
    ALTER TABLE `user_comment`
    ADD CONSTRAINT `fk_ucomment_sender`
      FOREIGN KEY (`senderId`)
      REFERENCES `user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION;