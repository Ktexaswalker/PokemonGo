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
    
    public Mochila() {
        listado = new ArrayList<>();
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
    
}
