/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hecto
 */
public class MenuPokemon {
    private List<String> opciones;
    public MenuPokemon() {
        opciones = new ArrayList<String>();
    }
    public void addOpcion(String opcion) {
        if(!opciones.contains(opcion)) {
            opciones.add(opcion);
        } else {
            System.out.println("Opcion repetida");
        }
    }
    public int escogerOption() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        if (opciones.size() == 0) {
            System.out.println("No se han especificado las opciones");
        }
        try {
            do {
                System.out.print("Escoge una opcion: ");
                opcion = sc.nextInt();
                System.out.println("OPCION : " + opcion);
                System.out.println("Opcion escogida: " + opciones.get(opcion)); 
            } while (opcion < 0 || opcion > opciones.size());
            return opcion;
        } catch (IndexOutOfBoundsException ex) {
            ex.getMessage();
            System.out.println("--Opcion fuera de rango--");
            escogerOption();
        }
        return opcion;
    }

    void verCompleto(MenuPokemon menu) {
        StringBuilder sb = new StringBuilder();
        for (String opt : opciones) {
            sb.append(opt).append("\n");
        }
        System.out.println(sb);
    }
}
