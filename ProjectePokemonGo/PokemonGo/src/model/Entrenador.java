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
    private static int contadorEntrenadores = 1;
    private int id;
    private String nom;
    private String password;

    public Entrenador(String nom, String pasaword) {
        this.id = contadorEntrenadores++;
        this.nom = nom;
        this.password = pasaword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPasaword() {
        return password;
    }

    public void setPasaword(String pasaword) {
        this.password = pasaword;
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
        return this.nom == other.nom;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entrenador{");
        sb.append("nombre=").append(nom);
        sb.append("contraseña=").append(password);
        sb.append("}");
        return sb.toString();
    }
    
}
