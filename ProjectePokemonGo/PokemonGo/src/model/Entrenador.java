/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author hecto
 */
public class Entrenador {
    private int id;
    private String nom;
    private String password;

    public Entrenador(String nom) {
        this.id = 0;
        this.nom = nom;
        this.password = "password";
    }
    
    public Entrenador(String nom, String password) {
        this.id = 0;
        this.nom = nom;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }
        
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Entrenador)) {
            return false;
        }
        final Entrenador other = (Entrenador) obj;
        return this.nom.equalsIgnoreCase(nom);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entrenador{");
        sb.append(" nombre=").append(nom);
        sb.append(" contraseña=").append(password);
        sb.append("}");
        return sb.toString();
    }    
}
