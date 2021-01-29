
/**
 * Author:  isaacrez
 * Created: Jan 11, 2021
 */
DROP DATABASE IF EXISTS `PokeDraft`;
CREATE DATABASE `PokeDraft`;
USE `PokeDraft`;

CREATE TABLE `League` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(60) NOT NULL,
    `admin` VARCHAR(30) NOT NULL
);

CREATE TABLE `Team` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `coachId` INT NOT NULL,
    `name` VARCHAR(60) NOT NULL,
    `acronym` VARCHAR(4) NOT NULL
);

CREATE TABLE `LeagueTeam` (
    `leagueId` INT NOT NULL,
    `teamId` INT NOT NULL,
    PRIMARY KEY(`leagueId`, `teamId`)
);

CREATE TABLE `Coach` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `discordName` VARCHAR(30) NOT NULL,
    `nickname` VARCHAR(30),
    `showdownName` VARCHAR(30)
);

CREATE TABLE `Roster` (
    `leagueId` INT NOT NULL,
    `teamId` INT NOT NULL,
    `pokeId` INT NOT NULL,
    PRIMARY KEY(`leagueId`, `teamId`, `pokeId`)
);

CREATE TABLE `Match` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `leagueId` INT NOT NULL,
    `statusId` INT NOT NULL,
    `scheduledWeek` INT NOT NULL, 
    `dateSubmitted` Date DEFAULT(CURDATE())
);

CREATE TABLE `MatchTeam` (
    `matchId` INT NOT NULL,
    `teamId` INT NOT NULL,
    PRIMARY KEY(`matchId`, `teamId`)
);

CREATE TABLE `MatchAttendee` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `matchId` INT NOT NULL,
    `pokeId` INT NOT NULL,
    `teamId` INT NOT NULL,
    `directKOs` INT DEFAULT(0),
    `indirectKOs` INT DEFAULT(0),
    `wasKOed` BOOL DEFAULT(FALSE)
);

CREATE TABLE `MatchStatus` (
    `id` INT PRIMARY KEY,
    `label` VARCHAR(20) NOT NULL
);

CREATE TABLE `Pokemon` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(16) NOT NULL,
    `form` VARCHAR(32)
);

ALTER TABLE `LeagueTeam`
    ADD CONSTRAINT `fk_leagueTeam_leagueId`
        FOREIGN KEY (`leagueId`)
    REFERENCES `League`(`id`),
    ADD CONSTRAINT `fk_leagueTeam_teamId`
        FOREIGN KEY (`teamId`)
    REFERENCES `team`(`id`);

ALTER TABLE `Team`
    ADD CONSTRAINT `fk_team_coachId`
        FOREIGN KEY (`coachId`)
    REFERENCES `Coach`(`id`);

ALTER TABLE `Match`
    ADD CONSTRAINT `fk_match_leagueId`
        FOREIGN KEY (`leagueId`)
    REFERENCES `League`(`id`),
    ADD CONSTRAINT `fk_match_statusId`
        FOREIGN KEY (`statusId`)
    REFERENCES `MatchStatus`(`id`);

ALTER TABLE `MatchTeam`
    ADD CONSTRAINT `fk_matchTeam_matchId`
        FOREIGN KEY(`matchId`)
    REFERENCES `Match`(`id`),
    ADD CONSTRAINT `fk_matchTeam_teamId`
        FOREIGN KEY(`teamId`)
        REFERENCES `Team`(`id`);

ALTER TABLE `MatchAttendee`
    ADD CONSTRAINT `fk_matchAttendee_matchId`
        FOREIGN KEY (`matchId`)
    REFERENCES `Match`(`id`),
    ADD CONSTRAINT `fk_matchAttendee_pokeId`
        FOREIGN KEY (`pokeId`)
    REFERENCES `Pokemon`(`id`),
    ADD CONSTRAINT `fk_matchAttendee_teamId`
        FOREIGN KEY (`teamId`)
    REFERENCES `Team`(`id`);

ALTER TABLE `Roster`
    ADD CONSTRAINT `fk_roster_leagueId`
        FOREIGN KEY (`leagueId`)
        REFERENCES `League`(`id`),
    ADD CONSTRAINT `fk_roster_teamId`
        FOREIGN KEY (`teamId`)
    REFERENCES `Team`(`id`),
    ADD CONSTRAINT `fk_roster_pokeId`
        FOREIGN KEY (`pokeId`)
    REFERENCES `Pokemon`(`id`);