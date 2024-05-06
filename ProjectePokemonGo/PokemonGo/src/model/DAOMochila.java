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
                preparedQuery.setInt(1, pokeball.getId());
                preparedQuery.setInt(2, pokeball.getId_pokemon());
                preparedQuery.setInt(3, pokeball.getFuerza());
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
}

