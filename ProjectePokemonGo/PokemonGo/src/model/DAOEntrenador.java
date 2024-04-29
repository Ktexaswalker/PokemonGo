/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import BD.DBConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
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
            String query = "INSERT INTO entrenador (nom, password) VALUES (?,?)";
            System.out.println(query);
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            preparedQuery.setString(1, trainer.getNom());
            preparedQuery.setString(2, trainer.getPassword());
            rows = preparedQuery.executeUpdate();
        }
        return rows;
    }
    public Boolean esborrarEntrenador(Entrenador antiguo) {
        Boolean borrado = null;
        try {
            String query = "DELETE FROM `entrenador` WHERE `entrenador`.`nom` = '?';";
            System.out.println(query);
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            preparedQuery.setString(1, antiguo.getNom());
            preparedQuery.executeUpdate();
            borrado = true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEntrenador.class.getName()).log(Level.SEVERE, null, ex);
            borrado = null;
        }
        return borrado;
    }
    public boolean existeixEntrenador(String name) {
        boolean existe = false;
        //Fer una consulta y si existe...
        String query = "CALL existeixNomEntrenador(?,?); SELECT @existe";
        System.out.println(query);
        try {
            CallableStatement callabledQuery = conn_principal.prepareCall(query);
            callabledQuery.setString(1, name);
            existe = callabledQuery.getBoolean(2);
            //devolver true
            return existe;
            //si no existe, devolver false
        } catch (SQLException ex) {
            System.out.println("Error de conexion " + ex.getMessage());
        }
        return existe;
    }
    public List<Entrenador> totsEntrenadors() {
        List<Entrenador> listado = new ArrayList<>();
        try {
            String query = "SELECT nom, password FROM entrenador";
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            ResultSet rs = preparedQuery.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String password = rs.getString("password");
                Entrenador trainer = new Entrenador(nom, password);
                listado.add(trainer);
            }
            return listado;
        } catch (SQLException ex) {
            Logger.getLogger(DAOEntrenador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }
    public Entrenador devolverEntrenador(String name) {
        String query = "CALL existeixNomEntrenador(?,?); SELECT @existe";
        System.out.println(query);
        try {
            CallableStatement callabledQuery = conn_principal.prepareCall(query);
            callabledQuery.setString(1, name);
            if (callabledQuery.getBoolean(2)) {
                String queryExiste = "SELECT * FROM entrenador";
                PreparedStatement preparedQuery = conn_principal.prepareStatement(queryExiste);
                ResultSet rs = preparedQuery.executeQuery();
                String nom = rs.getString("nom");
                String password = rs.getString("password");
                Entrenador trainer = new Entrenador(nom, password);
                return trainer;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion " + ex.getMessage());
        }
        return null;
    }
    public void cerrarConexion() throws SQLException {
        conn_principal.close();
    }
}
