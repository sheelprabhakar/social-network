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