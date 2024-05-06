/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import BD.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hema5364
 */
public class DAOPokedex {
    Connection conn_principal;

    public DAOPokedex() throws SQLException {
        this.conn_principal = DBConnect.getConnection();
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
    public Pokedex getPokemonRandom() throws SQLException {
        List<Pokedex> allPokedex = getPokemons();
        int max = allPokedex.size();
        int pokemons;
        pokemons = allPokedex.size();
        Random rd = new Random();
        int valor = rd.nextInt(pokemons);
        return allPokedex.get(valor);
    }
    public Pokedex getPokemon(int id) {
        if (conn_principal != null){
            try {
                Statement stmt = conn_principal.createStatement();
                String query = "SELECT * FROM Pokedex WHERE id_pokemon = " + id;
                System.out.println(query);
                ResultSet cursor = stmt.executeQuery(query);
                if (cursor.next()) {
                    int id_pokemon = cursor.getInt("id_pokemon");
                    String nom = cursor.getString("nom");
                    String tipus = cursor.getString("tipus");
                    Pokedex poke = new Pokedex(id_pokemon, nom, tipus);
                    return poke;
                } else {
                    return null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DAOPokedex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
