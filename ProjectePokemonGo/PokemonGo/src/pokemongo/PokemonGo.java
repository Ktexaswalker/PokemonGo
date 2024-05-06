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
import java.io.File;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 *
 * @author hema5364
 */
public class PokemonGo {
    DAOEntrenador entrenador;
    DAOPokedex pokedex;
    DAOMochila mochila;
    private int UserID;
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
            
            entrenador = new DAOEntrenador();
            pokedex = new DAOPokedex();
            boolean existe_nuevo = demanarUserPassword();
            if (existe_nuevo) {
                programa();
            } else {
                System.out.println("No has acertado el password ");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    private void mostrarMenu(MenuPokemon menu) {
        menu.addOpcion("0.- Salir");
        menu.addOpcion("1.- Dar de alta entrenador");
        menu.addOpcion("2.- Dar de baja entrenador");
        menu.addOpcion("3.- Consultar entrenador");
        menu.addOpcion("4.- Cazar pokemon");
        menu.addOpcion("5.- Mostrar pokedex");
        menu.addOpcion("6.- Listar entrenadores");
        menu.addOpcion("7.- Listar Pokemons cazados.");
        menu.addOpcion("8.- Listar tipos Pokemon existentes en juego.");
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
            insertado = entrenador.altaEntrenador(nuevo);
            if (insertado > 0) {
                System.out.println("Se ha insertado el nuevo entrenador");
            } else {
                System.out.println("Error insertando entrenador");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL insertando entrenador " + ex.getMessage());
        }
    }
    
    private void esborrarEntrenador() {
        try {
            Entrenador eliminado = null;
            Scanner sc = new Scanner(System.in);
            System.out.print("Escribe el nombre del entrenador que quiere borrrar: ");
            String nom = sc.nextLine();
            //comprobar alta
            if (entrenador.existeixEntrenador(nom)) {
                eliminado = entrenador.esborrarEntrenador(nom);
                if (eliminado != null) {
                    System.out.println("Entrenador " + nom + " eliminado");
                } else {
                    System.out.println("Error al eliminar el entrenador");
                }
            } else {
                System.out.println("Este entrenador no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void consultaEntrenador() {
        Entrenador trainer = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe el nombre del entrenador que quieres consultar: ");
        String name = sc.nextLine();
        try {
            trainer = entrenador.devolverEntrenador(name.toUpperCase());
            if (trainer != null) {
                System.out.println(trainer);
            } else {
                System.out.println("El entrenador no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void cazarPokemon() {
        try {
            DAOMochila inventario = new DAOMochila();
            Pokedex aparecido = pokedex.getPokemonRandom();
            System.out.println(aparecido);
            mostrarPoke(aparecido);
            //int id = UserID;
            //int id_pokemon = aparecido.getId_pokemon();
            Pokemon pokeball = new Pokemon(aparecido.getNom());
            pokeball.randomFuerza();
            int fuerza = pokeball.getFuerza();
            //inventario.darCaptura(id, id_pokemon, fuerza);
            inventario.darCaptura(UserID, aparecido.getId_pokemon(), fuerza);
            System.out.println("ENTRENADOR: " + UserID + "POKEDEX: " + aparecido.getId_pokemon() + " FUERZA: " + fuerza);
            //mochila.darCaptura(UserID, aparecido.getId_pokemon(), fuerza);
            //inventario.toString();
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listarMochila() {
        //Listar cazados
    }
    private void listarTodosPokemons() {    //Listar Pokemons existentes
        try {
            List<Pokedex> todos = pokedex.getPokemons();
            System.out.println("Todos los Pokemons");
            for (Object pokes : todos) {
                System.out.println(pokes);
            }
            System.out.println("Numero de Pokemons: " + todos.size());
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean demanarUserPassword() {
        try {        
            Scanner sc = new Scanner(System.in);
            System.out.print("USER: ");
            String user = sc.nextLine();
            System.out.print("PASSWORD: ");
            String password = sc.nextLine();
            //Entrenador comprobar = new Entrenador(user, password);
            if (!entrenador.existeixEntrenador(user)) { //Si no existe, dar de alta
                entrenador.altaEntrenador(new Entrenador(user, password));
                guardarEntrenador(user);
                return true;
            } else {
                //Si existe, comprobar password
                Entrenador comprobar = entrenador.devolverEntrenador(user);
                if (comprobar.getPassword().equals(password)) {
                    System.out.println("Password correcto");
                    System.out.println("Benvingut " + user);
                    guardarEntrenador(user);
                    return true;
                } else {
                    System.out.println("PASSWORD INCORRECTO");
                    //salir();
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
            /* mostrar por pantalla caratula recorrer arrayList Strings y mostrarlo por pantalla*/
            /*  OPCION A
                    for (String string : array) {
                        System.out.println(string);
                    }
                OPCION B */
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i));   //array.get(i);
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

    private void totsEntrenadors() {
        List<Entrenador> todos = entrenador.totsEntrenadors();
        System.out.println("Todos los entrenadores Pokemon");
        //informar usuari
        for (Object trainer : todos) {
            System.out.println(trainer);
        }
        System.out.println("Numero de entrenadores: " + todos.size());
    }

    private void programa() {
        boolean exit = false;
            MenuPokemon menu = new MenuPokemon();
            mostrarMenu(menu);
        do {
            int opcion = menu.escogerOption();
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
                    mostrarPokedex();
                    break;
                case 6:
                    totsEntrenadors();
                    break;
                case 7:
                    listarMochila();
                    break;
                case 8:
                    listarTodosPokemons();
                    break;
            }
        } while(!exit);
    }

    private void mostrarPoke(Pokedex poke) {
        try {
            Caratula dibujo = new Caratula("src/pokes/default.pok");
            String rutapoke = "src/pokes/" + poke.getNom().toLowerCase() + ".pok";
            File ruta = new File(rutapoke);
            if (ruta.exists()) {
                dibujo = new Caratula(rutapoke);
            }
            ArrayList<String> array = dibujo.recuperarDatos();
            for (String string : array) {
                System.out.println(string);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void mostrarPokedex() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Elige el pokemon que quieres mostrar(id): ");
        int id = sc.nextInt();
        Pokedex poke = pokedex.getPokemon(id);
        if ( poke != null) {
            mostrarPoke(poke);
            Pokemon pokefort = new Pokemon(poke.getNom());
            //System.out.println(pokefort.getFuerza());
            System.out.println(pokefort.toString());
        } else {
            try {
                System.out.println("No existe este Pokemon");
                Caratula dibujo = new Caratula("src/pokes/default.pok");
                ArrayList<String> array = dibujo.recuperarDatos();
                for (String string : array) {
                    System.out.println(string);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void guardarEntrenador(String user) {
        Entrenador trainer = new Entrenador(user);
        int id = trainer.getId();
        UserID = id;
    }
}
