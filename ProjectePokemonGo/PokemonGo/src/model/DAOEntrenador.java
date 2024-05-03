/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import BD.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.*;
/**
 *
 * @author hema5364
 */
public class DAOEntrenador {    //Data Access Object
    //Atributo para la conexion
    Connection conn_principal;
    
    public DAOEntrenador() throws SQLException {
        conn_principal = DBConnect.getConnection(); //conexion abierta hasta el final
    }
    public int altaEntrenador(Entrenador trainer) throws SQLException {
        int rows = 0;
        if (conn_principal != null) {
            if (!existeixEntrenador(trainer.getNom())) {
                String query = "INSERT INTO Entrenador (nom, password) VALUES (?,?)";
                System.out.println(query);
                PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
                preparedQuery.setString(1, trainer.getNom().toUpperCase());
                preparedQuery.setString(2, trainer.getPassword().toUpperCase());
                rows = preparedQuery.executeUpdate();
            }
            else {
                rows = 0;
            }
        }
        return rows;
    }
    public Entrenador esborrarEntrenador(String nom) {
        //Modificar esta parte en CASA //Si el entrenador no existe = NULL
        //Si existe, devuelve el entrenador
        try {
            if (conn_principal != null) {
                if (existeixEntrenador(nom)) {
                    Entrenador antiguo = devolverEntrenador(nom);
                    String query = "DELETE FROM Entrenador WHERE UPPER(nom) = ?;";
                    System.out.println(query);
                    PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
                    preparedQuery.setString(1, antiguo.getNom());
                    int row = preparedQuery.executeUpdate();
                    if (row > 0) {
                        return antiguo;
                    }
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEntrenador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    
    }
    public boolean existeixEntrenador(String name) throws SQLException {
        if (conn_principal != null){
            Statement stmt = conn_principal.createStatement();
            String query = "SELECT id, nom, password FROM Entrenador WHERE UPPER(nom) = '" + name.toUpperCase() + "'";
            System.out.println(query);
            String query2 = "SELECT COUNT(*) FROM Entrenador WHERE UPPER(nom) = '" + name.toUpperCase() + "'";
            System.out.println(query2);
            //ResultSet cursor = stmt.executeQuery(query);
            ResultSet cursor = stmt.executeQuery(query2);
            if (cursor.next()) {
                int registros = cursor.getInt(1);
                if (registros  == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    public List<Entrenador> totsEntrenadors() {
        List<Entrenador> listado = new ArrayList<>();
        try {
            if (conn_principal != null) {
                String query = "SELECT id, nom, password FROM entrenador";
                Statement stmt = conn_principal.createStatement();
                ResultSet cursor = stmt.executeQuery(query);
                while (cursor.next()) {
                    String nom = cursor.getString("nom");
                    String password = cursor.getString("password");
                    int id = cursor.getInt("id");
                    Entrenador trainer = new Entrenador(nom, password);
                    listado.add(trainer);
                }
                cursor.close();
            }
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEntrenador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    public Entrenador devolverEntrenador(String name) throws SQLException {
        if (conn_principal != null) {
            Statement stmt = conn_principal.createStatement();
            String query = "SELECT id, nom, password FROM Entrenador WHERE UPPER(nom) = '" + name.toUpperCase() + "'";
            ResultSet cursor = stmt.executeQuery(query);
            if (cursor.next()) {    //existe //FETCH
                int id = cursor.getInt("id");
                String nom = cursor.getString("nom");
                String password = cursor.getString("password");
                Entrenador trainer = new Entrenador(nom, password);
                return trainer;
            } else {
                return null;
            }
        }
        return null;
    }
    public void cerrarConexion() throws SQLException {
        conn_principal.close();
    }
}
