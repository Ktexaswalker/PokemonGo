/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hecto
 */
public class Mochila {
    private List<Pokemon> listado;
    //ejercicio 7
    private int id;
    private int id_pokemon;
    private int fuerza;
    
    public Mochila() {
        listado = new ArrayList<>();
    }
    
    public Mochila(int id, int id_pokemon, int fuerza) {
        this.id = id;
        this.id_pokemon = id_pokemon;
        this.fuerza = fuerza;
    }
    
    public boolean addPokemon(Pokemon pokemon) {
        if (!listado.contains(pokemon)) {
            listado.add(pokemon);
            return true;
        } else {
            return false;
        }
    }

    public List<Pokemon> getListado() {
        return listado;
    }

    public void setListado(List<Pokemon> listado) {
        this.listado = listado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void darCaptura(int id, int id_pokemon, int fuerza) {
        this.id = id;
        this.id_pokemon = id_pokemon;
        this.fuerza = fuerza;
    }

    public void SoltarPokemon(int id, int id_pokemon, int fuerza) {
        this.id = id;
        this.id_pokemon = id_pokemon;
        this.fuerza = fuerza;
    }
    
    @Override
    public String toString() {
        return "Mochila{" + "listado=" + listado + ", id=" + id + ", id_pokemon=" + id_pokemon + ", fuerza=" + fuerza + '}';
    }
    
}
