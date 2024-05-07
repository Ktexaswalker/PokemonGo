DROP DATABASE dbpokemon;
DROP USER IF EXISTS 'usrpokemon'@'localhost';

CREATE USER IF NOT EXISTS 'usrpokemon'@'localhost' IDENTIFIED BY 'pswpokemon';

GRANT ALL PRIVILEGES ON dbpokemongo TO 'usrpokemon'@'localhost';

CREATE DATABASE IF NOT EXISTS dbpokemongo 
	DEFAULT CHARACTER SET utf8 
	DEFAULT COLLATE utf8_general_ci;

GRANT SELECT, INSERT, UPDATE, DELETE ON dbpokemongo.* TO 'usrpokemon'@'localhost';
USE dbpokemongo;
CREATE TABLE IF NOT EXISTS Entrenador (
	id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	nom VARCHAR(50) UNIQUE, 
	password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Pokedex (
	id_pokemon INT(4) NOT NULL,
	nom VARCHAR(50) UNIQUE,
	tipus VARCHAR(50) DEFAULT NULL,
        PRIMARY KEY (`id_pokemon`)
);

CREATE TABLE IF NOT EXISTS Mochila (
	id INT(4), 
	id_pokemon INT(4), 
	fuerza INT(20), 
	FOREIGN KEY (id) REFERENCES Entrenador(id),
	FOREIGN KEY (id_pokemon) REFERENCES Pokedex(id_pokemon)
);

--Insertar algunos datos en Entrenador, Pokedex y Mochila
INSERT INTO `Entrenador` ( `nom`, `password`) 
VALUES
(UPPER('user1'), 'user1'),
(UPPER('user2'), 'user2');

INSERT INTO `Pokedex` ( `id_pokemon`, `nom`, `tipus`) 
VALUES
(1, UPPER('Caterpie'), 'planta'),
(2, UPPER('Blastoise'), 'agua'),
(3, UPPER('Bulbasaur'), 'planta'),
(4, UPPER('Bulbasur'), 'planta'),
(5, UPPER('Charizard'), 'fuego'),
(6, UPPER('Charmaleon'), 'fuego'),
(7, UPPER('Charmander'), 'fuego'),
(8, UPPER('Ivisaur'), 'planta'),
(9, UPPER('Pikachu'), 'electrico'),
(10, UPPER('Squirtle'), 'agua'),
(11, UPPER('Venusaur'), 'planta'),
(12, UPPER('Wartortle'), 'agua'),
(13,UPPER('Dito'), 'raro');

INSERT INTO `Mochila` ( `id`, `id_pokemon`, `fuerza`) 
VALUES
(1,1,50),
(1,5,99);