
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

CREATE TABLE `DraftType` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
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
    `imgId` VARCHAR(7) NOT NULL,
    `name` VARCHAR(32) NOT NULL,
    `HP` TINYINT UNSIGNED NOT NULL,
    `Atk` TINYINT UNSIGNED NOT NULL,
    `Def` TINYINT UNSIGNED NOT NULL,
    `SpA` TINYINT UNSIGNED NOT NULL,
    `SpD` TINYINT UNSIGNED NOT NULL,
    `Spe` TINYINT UNSIGNED NOT NULL    
);

CREATE TABLE `Type` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(8) NOT NULL
);

CREATE TABLE `PokemonType` (
    `pokemonId` INT NOT NULL,
    `typeId` INT NOT NULL,
    PRIMARY KEY(`pokemonId`, `typeId`)
);

CREATE TABLE `Ability` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(16) NOT NULL
);

CREATE TABLE `PokemonAbility` (
    `pokemonId` INT NOT NULL,
    `abilityId` INT NOT NULL,
    PRIMARY KEY(`pokemonId`, `abilityId`)
);

CREATE TABLE `PokemonTier` (
    `tier` SMALLINT NOT NULL,
    `pokemonId` INT NOT NULL,
    `leagueId` INT NOT NULL,
    PRIMARY KEY(`pokemonId`, `leagueId`)
);

ALTER TABLE `League`
    ADD CONSTRAINT `fk_league_draftTypeId`
        FOREIGN KEY (`draftTypeId`)
        REFERENCES `DraftType`(`id`);

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
        
ALTER TABLE `PokemonType`
    ADD CONSTRAINT `fk_pokemonType_pokemonId`
        FOREIGN KEY (`pokemonId`)
    REFERENCES `pokemon`(`id`),
    ADD CONSTRAINT `fk_pokemonType_typeId`
        FOREIGN KEY (`typeId`)
    REFERENCES `type`(`id`);
        
ALTER TABLE `PokemonAbility`
    ADD CONSTRAINT `fk_pokemonAbility_pokemonId`
        FOREIGN KEY (`pokemonId`)
    REFERENCES `pokemon`(`id`),
    ADD CONSTRAINT `fk_pokemonAbility_abilityId`
        FOREIGN KEY (`abilityId`)
    REFERENCES `ability`(`id`);

ALTER TABLE `PokemonTier`
    ADD CONSTRAINT `fk_pokemonTier_pokemonId`
        FOREIGN KEY (`pokemonId`)
    REFERENCES `pokemon`(`id`),
    ADD CONSTRAINT `fk_pokemonLeague_leagueId`
        FOREIGN KEY (`leagueId`)
    REFERENCES `league`(`id`);