/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import BD.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hecto
 */
public class DAOMochila {
    Connection conn_principal;
    
    public DAOMochila() throws SQLException {
        conn_principal = DBConnect.getConnection(); //conexion abierta hasta el final
    }
    public boolean darCaptura(int id_coach, int num_pokemon, int CP) {
        int rows = 0;
        if (conn_principal != null) {
            try {
                Mochila pokeball = new Mochila();
                String query = "INSERT INTO Mochila (id, id_pokemon, fuerza) VALUES (?,?,?)";
                System.out.println(query);
                PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
                //preparedQuery.setInt(1, pokeball.getId());
                //preparedQuery.setInt(2, pokeball.getId_pokemon());
                //preparedQuery.setInt(3, pokeball.getFuerza());
                preparedQuery.setInt(1, id_coach);
                preparedQuery.setInt(2, num_pokemon);
                preparedQuery.setInt(3, CP);
                rows = preparedQuery.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOMochila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    
    public List<Pokedex> getPokemons() throws SQLException {
        List<Pokedex> allPokedex = new ArrayList<>();
        int pokemons;
        if (conn_principal != null) {
            Statement stmt = conn_principal.createStatement();
            String query = "SELECT * FROM pokedex";
            ResultSet cursor;
            cursor = stmt.executeQuery(query);
            while(cursor.next()) {
                int id = cursor.getInt("id_pokemon");
                String nom = cursor.getString("nom");
                String tipus = cursor.getString("tipus");
                Pokedex pokemon = new Pokedex(id, nom, tipus);
                allPokedex.add(pokemon);
            }
            cursor.close();
        }
        return allPokedex;
    }
    
    public List<Mochila> getCaza(int id_player) {
        List <Mochila> cazados = new ArrayList<>();
        int rows = 0;
        if (conn_principal != null) {
            try {
                String query = "SELECT id, id_pokemon, fuerza FROM mochila WHERE id = ? ";
                PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
                preparedQuery.setInt(1, id_player);
                ResultSet cursor = preparedQuery.executeQuery();
                while(cursor.next()) {
                    int id = cursor.getInt(1);
                    int id_pokemon = cursor.getInt(2);
                    int fuerza = cursor.getInt(3);
                    Mochila cazado = new Mochila(id, id_pokemon, fuerza);
                    cazados.add(cazado);
                }
                cursor.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOMochila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cazados;
    }
    
    public List<Mochila> getPokemonsCapturadosOrdenats(int id_trainer) {
        List<Mochila> mochila = new ArrayList<>();
        if (conn_principal != null) {
            try {
                //Juntar los campos comunes de id_pokemon de las tablas mochila y pokedex
                String query = "SELECT m.id, m.id_pokemon, m.fuerza, p.nom "
                        + "FROM mochila AS m "
                        + "INNER JOIN pokedex AS p "
                        + "ON m.id_pokemon = p.id_pokemon "
                        + "WHERE m.id = ? ORDER BY p.nom ASC, m.fuerza DESC";
                System.out.println(query);
                PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
                preparedQuery.setInt(1, id_trainer);
                ResultSet cursor = preparedQuery.executeQuery();
                while (cursor.next()) {
                    int id_pokemon = cursor.getInt("m.id_pokemon");
                    int fuerza = cursor.getInt("m.fuerza");
                    Mochila pokeball = new Mochila(id_trainer, id_pokemon, fuerza);
                    mochila.add(pokeball);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOMochila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mochila;
    }
    
    public Mochila SoltarPokemon(int id_entrenador, int id_pokemon, int id_fuerza) {
        if (conn_principal != null) {
            try {
                String query = "DELETE FROM mochila WHERE id = ? AND id_pokemon = ? AND fuerza = ?";
                PreparedStatement stmt = conn_principal.prepareStatement(query);
                stmt.setInt(1, id_entrenador);
                stmt.setInt(2, id_pokemon);
                stmt.setInt(3, id_fuerza);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Se eliminó una fila, entonces se devuelve un nuevo objeto Mochila
                    return new Mochila(id_entrenador, id_pokemon, id_fuerza);
                } else {
                    // No se eliminó ninguna fila
                    return null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOMochila.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void cerrarConexion() throws SQLException {
        conn_principal.close();
    }
}

