CREATE DATABASE IF NOT EXISTS coffee_clicker CHARACTER SET utf8mb4;
USE coffee_clicker;

CREATE TABLE `game` (
  `id_game` int(11) NOT NULL,
  `currency_count` float NOT NULL,
  `user` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `finished` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `generator` (
  `id_generator` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `n_gens` int(11) NOT NULL,
  `n_currencies` float NOT NULL,
  `id_game` int(11) NOT NULL,
  `improvement` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `improvements` (
  `id_improvement` int(11) NOT NULL,
  `improvement_type` varchar(255) NOT NULL,
  `improvement_level` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

CREATE TABLE `statistics` (
  `id_stats` int(11) NOT NULL,
  `game_min` int(11) NOT NULL,
  `current_currency` int(11) NOT NULL,
  `id_game` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

CREATE TABLE `user` (
  `nickname` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`),
  ADD KEY `game_ibfk_1` (`user`);

ALTER TABLE `generator`
  ADD PRIMARY KEY (`id_generator`),
  ADD KEY `generator_ibfk_1` (`id_game`),
  ADD KEY `improvement` (`improvement`);

ALTER TABLE `improvements`
  ADD PRIMARY KEY (`id_improvement`);

ALTER TABLE `statistics`
  ADD PRIMARY KEY (`id_stats`),
  ADD KEY `game` (`id_game`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`mail`);

ALTER TABLE `game`
  MODIFY `id_game` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `generator`
  MODIFY `id_generator` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `improvements`
  MODIFY `id_improvement` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `statistics`
  MODIFY `id_stats` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`mail`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `generator`
  ADD CONSTRAINT `generator_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `generator_ibfk_2` FOREIGN KEY (`improvement`) REFERENCES `improvements` (`id_improvement`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `statistics`
  ADD CONSTRAINT `game` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE;
