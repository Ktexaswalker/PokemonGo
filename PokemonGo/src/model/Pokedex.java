/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hecto
 */
public class Pokedex {
    private int id_pokemon;
    private String nom;
    private String tipus;

    public Pokedex(int id_pokemon, String nom) {
        this.id_pokemon = id_pokemon;
        this.nom = nom;
        this.tipus = "normal";
    }

    public Pokedex(int id_pokemon, String nom, String tipus) {
        this.id_pokemon = id_pokemon;
        this.nom = nom;
        this.tipus = tipus;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pokedex{");
        sb.append("id_pokemon=").append(id_pokemon);
        sb.append(" nom=").append(nom);
        sb.append(" tipus=").append(tipus);
        sb.append("}");
        return sb.toString();
    }

    public int getId_pokemon() {
        return id_pokemon;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
    
}
