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
    public Pokedex getPokemonRandom() {
        List<Pokedex> allPokedex = new ArrayList<>();
        int pokemons = 0;
        try {
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
            pokemons = allPokedex.size();
            Random rd = new Random();
            int valor = rd.nextInt(pokemons);
            return allPokedex.get(valor);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
