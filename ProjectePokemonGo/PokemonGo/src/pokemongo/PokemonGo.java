/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemongo;

import fitxers.Caratula;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import BD.DBConnect;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import model.*;

/**
 *
 * @author hema5364
 */
public class PokemonGo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PokemonGo app = new PokemonGo();
        app.run();
    }
    private void run() {
        String rutalogo = "src/pokes/logo.pok";
        try {
            /*Driver Conexion BASE DE DATOS*/
            DBConnect.loadDriver();
            crearDB();
            /* mostrar caraturla */
            Caratula titulo = new Caratula(rutalogo);
            /* recuperar datos fichero de caratula */
            ArrayList<String> array = titulo.recuperarDatos();
            /* mostrar por pantalla caratula
            recorrer arrayList Strings y mostrarlo por pantalla*/
            /*  OPCION A
                    for (String string : array) {
                        System.out.println(string);
                    }
                OPCION B */
            for (int i = 0; i < array.size(); i++) {
                //array.get(i);
                System.out.println(array.get(i));
            }
            
            /* mostrar menu
            con 0 debe salir prorgama
            con otras opciones mostrar una frase "Has dado en la opcion {X}"
            */
            boolean exit = false;
            MenuPokemon menu = new MenuPokemon();
            mostrarMenu(menu);
            int opcion = menu.escogerOption();
            do {
                switch (opcion) {
                    case 0:
                        exit = true;
                        System.out.println("Saliendo...");
                        break;
                    case 1:
                        opcionEntrenador();
                        break;
                    case 2:
                        System.out.println("Aquesta es la opció 2");
                        break;
                    case 3:
                        System.out.println("Aquesta es la opció 3");
                        break;
                    case 4:
                        System.out.println("Aquesta es la opció 4");
                        break;
                    case 5:
                        System.out.println("Aquesta es la opció 5");
                        break;
                    case 6:
                        System.out.println("Aquesta es la opció 6");
                        break;
                }
            } while(!exit);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void mostrarMenu(MenuPokemon menu) {
        menu.addOpcion("0.- Salir");
        menu.addOpcion("1.- Dar de alta entrenador");
        menu.addOpcion("2.- Dar de baja entrenador");
        menu.addOpcion("3.-Consultar entrenador");
        menu.addOpcion("4.-Cazar pokemon");
        menu.addOpcion("5.-Listar Pokemons cazados.");
        menu.addOpcion("6.-Listar tipos Pokemon existentes en juego.");
        menu.verCompleto(menu);
    }
    
    
    private void crearDB() {
        Connection conn;
        /*USUARIO GESTOR DE BASE DE DATOS*/
        
        String queryCreateUser = "CREATE USER IF NOT EXISTS 'usrpokemon'@'localhost' IDENTIFIED BY 'pswpokemon'";
        String permisosUser = "GRANT SELECT, INSERT, UPDATE, DELETE ON dbpokemongo.* TO 'usrpokemon'@'localhost'";
        String queryUSE = "USE dbpokemongo";
        int contar = 0;
        /*CREAR BASE DE DATOS*/
        String queryCreateDB = "CREATE DATABASE IF NOT EXISTS dbpokemongo DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci";
        /*Tabla entrenadores*/
            /*campos: id (autoincrement), nombre, password*/
        String queryTableEntrenador = "CREATE TABLE IF NOT EXISTS Entrenador (id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY, nom VARCHAR(50) UNIQUE, password VARCHAR(50)";
            /*nombre equals () = ERROR*/
        
        /*Tabla Pokedex*/
            /*campos: id_pokemon, nom, tipus*/
        String queryTablePokedex = "CREATE TABLE IF NOT EXISTS Pokedex (id_pokemon INT(4) NOT NULL PRIMARY KEY,nom VARCHAR(50) UNIQUE,tipus VARCHAR(50))";
            /*id_pokedex equals () = ERROR*/
            
        /*Tabla Pokemon Capturados*/
            /**/
        String queryTableCapturas = "CREATE TABLE IF NOT EXISTS Capturas (id_captura INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY, id_pokemon INT(4), nom VARCHAR(50), tipus VARCHAR(50) fuerza INT(20), FOREIGN KEY (id_pokemon) REFERENCES Pokedex(id_pokemon)";
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.executeQuery(queryCreateUser);
                stmt.executeQuery(permisosUser);
                stmt.executeQuery(queryCreateDB);
                stmt.executeQuery(queryUSE);
                stmt.executeQuery(queryTableEntrenador);
                stmt.executeQuery(queryTablePokedex);
                stmt.executeQuery(queryTableCapturas);
                stmt.close();
            } else {
                System.out.println("Conexion no establecida");
            }
            //Entrenador persona = new Entrenador("nombre", "password");
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertarEntrenador(int id, String nom, String password) {
        try {
            Connection conn;
            conn = DBConnect.getConnection();
            String queryInsertEntrenador = "INSERT INTO Entrenador"
                + "(id, nom, password) VALUES (?,?,?)";
            System.out.println(queryInsertEntrenador + "id=" + id + " nom=" + nom + " password=" + password);
            PreparedStatement preparedQuery = conn.prepareStatement(queryInsertEntrenador);
            preparedQuery.setInt(1, id);
            preparedQuery.setString(2, nom);
            preparedQuery.setString(3, password);
            int rows = preparedQuery.executeUpdate();
            System.out.println("Entrenador insertado... " + rows);
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void insertarDatosPokedex(int id_pokemon, String nom, String tipus) {
        try {
            Connection conn;
            conn = DBConnect.getConnection();
            String queryInsertEntrenador = "INSERT INTO 'Pokedex'"
                + "('id_pokemon', 'nom', 'tipus') VALUES (?,?,?)";
            System.out.println(queryInsertEntrenador);
            PreparedStatement preparedQuery = conn.prepareStatement(queryInsertEntrenador);
            preparedQuery.setInt(1, id_pokemon);
            preparedQuery.setString(2, nom);
            preparedQuery.setString(3, tipus);
            int rows = preparedQuery.executeUpdate();
            System.out.println("Pokemon insertado en la Pokedex... " + rows);
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void capturarPokemon(int id_pokemon, String nom, String tipus, int fuerza) {
        try {
            Connection conn;
            conn = DBConnect.getConnection();
            String queryInsertEntrenador = "INSERT INTO 'Capturas'"
                + "('id_pokemon', 'nom', 'tipus', 'fuerza') VALUES (?,?,?,?)";
            System.out.println(queryInsertEntrenador);
            PreparedStatement preparedQuery = conn.prepareStatement(queryInsertEntrenador);
            preparedQuery.setInt(1, id_pokemon);
            preparedQuery.setString(2, nom);
            preparedQuery.setString(3, tipus);
            preparedQuery.setInt(4, fuerza);
            int rows = preparedQuery.executeUpdate();
            System.out.println("Pokemon insertado en la Pokedex... " + rows);
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void opcionEntrenador() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserte un nombre: ");
        String nom = sc.nextLine();
        System.out.print("Inserte una contraseña: ");
        String password = sc.nextLine();
        Entrenador entrenador = new Entrenador(nom, password);
        int id = entrenador.getId();
        System.out.println("Entrenador creado con ID: " + id);
        insertarEntrenador(id, nom, password);
    }
}
