CREATE USER IF NOT EXISTS 'usrpokemon'@'localhost' IDENTIFIED BY 'pswpokemon';

CREATE DATABASE IF NOT EXISTS dbpokemongo 
	DEFAULT CHARACTER SET utf8 
	DEFAULT COLLATE utf8_general_ci;

GRANT SELECT, INSERT, UPDATE, DELETE ON dbpokemongo.* TO 'usrpokemon'@'localhost';

CREATE TABLE IF NOT EXISTS Entrenador (
	id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	nom VARCHAR(50) UNIQUE, 
	password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Pokedex (
	id_pokemon INT(4) NOT NULL PRIMARY KEY,
	nom VARCHAR(50) UNIQUE,
	tipus VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Mochila (
	id INT(4), 
	id_pokemon INT(4), 
	nom VARCHAR(50), 
	tipus VARCHAR(50),
	fuerza INT(20), 
	FOREIGN KEY (id) REFERENCES Entrenador(id),
	FOREIGN KEY (id_pokemon) REFERENCES Pokedex(id_pokemon)
);