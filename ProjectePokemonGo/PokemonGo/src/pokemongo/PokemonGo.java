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
    DAOEntrenador entrenador;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PokemonGo app = new PokemonGo();
        app.run();
    }
    private void run() {
        try {
            /*Driver Conexion BASE DE DATOS*/
            DBConnect.loadDriver();
            crearDB();
            mostrarLogo();
            /* mostrar menu con 0 debe salir prorgama con otras opciones mostrar una frase "Has dado en la opcion {X}"*/
            boolean exit = false;
            MenuPokemon menu = new MenuPokemon();
            demanarUserPassword();
            mostrarMenu(menu);
            int opcion = menu.escogerOption();
            entrenador = new DAOEntrenador();
            do {
                switch (opcion) {
                    case 0:
                        exit = true;
                        salir();
                        System.out.println("Saliendo...");
                        break;
                    case 1:
                        altaEntrenador();
                        break;
                    case 2:
                        esborrarEntrenador();
                        break;
                    case 3:
                        consultaEntrenador();
                        break;
                    case 4:
                        cazarPokemon();
                        break;
                    case 5:
                        listarMochila();
                        break;
                    case 6:
                        listarTodosPokemons();
                        break;
                }
            } while(!exit);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
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
    private void altaEntrenador() {
        try {
            Scanner sc = new Scanner(System.in);
            int insertado;
            System.out.print("Pon el nombre del nuevo entrenador: ");
            String nom = sc.nextLine();
            System.out.print("Pon el password: ");
            String password = sc.nextLine();
            
            Entrenador nuevo = new Entrenador(nom, password);
            //comprobar alta
            if (!(entrenador.existeixEntrenador(nom))) {
                //llamar al DAO
                insertado = entrenador.altaEntrenador(nuevo);
                if (insertado > 0) {
                    System.out.println("Se ha insertado el nuevo entrenador");
                } else {
                    System.out.println("Error insertando entrenador");
                }
            } else {
                System.out.println("Este entrenador ya existe");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL insertando entrenador " + ex.getMessage());
        }
    }
    
    private void esborrarEntrenador() {
        boolean eliminado;
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe el nombre del entrenador que quiere borrrar: ");
        String nom = sc.nextLine();
        Entrenador antiguo = new Entrenador(nom);
            //comprobar alta
            if (entrenador.existeixEntrenador(nom)) {
                //llamar al DAO
                eliminado = entrenador.esborrarEntrenador(antiguo);
                if (eliminado == true) {
                    System.out.println("Entrenador " + nom + " eliminado");
                } else {
                    System.out.println("Error al eliminar el entrenador");
                }
            } else {
                System.out.println("Este entrenador no existe");
            }
    }
    private void consultaEntrenador() {
        
    }
    private void cazarPokemon() {
        
    }
    private void listarMochila() {
        
    }
    private void listarTodosPokemons() {
        
    }
    
    private void demanarUserPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("USER: ");
        String user = sc.nextLine();
        System.out.print("PASSWORD: ");
        String password = sc.nextLine();
        Entrenador comprobar = new Entrenador(user, password);
        int insertado;
        try {
            if (!(entrenador.existeixEntrenador(user))) {
                //Si no existe, dar de alta
                insertado = entrenador.altaEntrenador(comprobar);
                if (insertado > 0) {
                    System.out.println("Benvingut " + user);
                } else {
                    System.out.println("Error al insertar el usuario");
                }   
            } else {
                //Si existe, comprobar password
                if (comprobar.getPassword().equals(password)) {
                    System.out.println("Password correcto");
                    System.out.println("Benvingut " + user);
                } else {
                    System.out.println("PASSWORD INCORRECTO");
                    salir();
                }
                System.out.println("Este entrenador ya existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearDB() {
        /*El usuario inserta el script en la base de datos MySQL manualmente */
    }
    /*
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
    */
    private void mostrarLogo() {
        String rutalogo = "src/pokes/logo.pok";
        /* mostrar caraturla */
        try {
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
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void salir() {
        try {
            System.out.println("Cerrando conexion Entrenadores");
            entrenador.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
