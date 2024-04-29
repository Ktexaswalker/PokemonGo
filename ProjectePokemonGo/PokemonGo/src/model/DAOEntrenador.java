/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import BD.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            //Query
            String query = "INSERT INTO Entrenador (nom, password) VALUES (?,?)";
            System.out.println(query);
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            preparedQuery.setString(1, trainer.getNom());
            preparedQuery.setString(2, trainer.getPassword());
            rows = preparedQuery.executeUpdate();
        }
        return rows;
    }
    public boolean existeEntrenador(String name) {
        boolean existe = true;
        //Fer una consulta y si existe...
        String query = "SELECT nom FROM entrenador WHERE nom=?";
        System.out.println(query);
        try {
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            preparedQuery.setString(1, name);
            //devolver true
            
            //si no existe, devolver false
        } catch (SQLException ex) {
            System.out.println("Error de conexion " + ex.getMessage());
        }
        return existe;
    }
    
    
    public void cerrarConexion() throws SQLException {
        conn_principal.close();
    }
}
