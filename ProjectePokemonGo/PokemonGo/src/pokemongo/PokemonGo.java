/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemongo;

import fitxers.Caratula;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hema5364
 */
public class PokemonGo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PokemonGo app = new PokemonGo();
        app.run();
    }

    private void run() {
        String rutalogo = "src/pokes/logo.pok";
        try {
            /* mostrar caraturla */
            Caratula titulo = new Caratula(rutalogo);
            /* recuperar datos fichero de caratula */
            ArrayList<String> array = titulo.recuperarDatos();
            /* mostrar por pantalla caratula
            recorrer arrayList Strings y mostrarlo por pantalla*/
            /*  OPCION A
                    for (String string : array) {
                        System.out.println(string);
                    }
                OPCION B */
            for (int i = 0; i < array.size(); i++) {
                //array.get(i);
                System.out.println(array.get(i));
            }
            
            /* mostrar menu
            con 0 debe salir prorgama
            con otras opciones mostrar una frase "Has dado en la opcion {X}"
            */
            boolean exit = false;
            MenuPokemon menu = new MenuPokemon();
            mostrarMenu(menu);
            int opcion = menu.escogerOption();
            do {
                switch (opcion) {
                    case 0:
                        exit = true;
                        System.out.println("Saliendo...");
                        break;
                    case 1:
                        exit = true;
                        System.out.println("Aquesta es la opció 1");
                        break;
                    case 2:
                        exit = true;
                        System.out.println("Aquesta es la opció 2");
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Aquesta es la opció 3");
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Aquesta es la opció 4");
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Aquesta es la opció 5");
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Aquesta es la opció 6");
                        break;
                }
            } while(!exit);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    private void mostrarMenu(MenuPokemon menu) {
        menu.addOpcion("0.- Salir");
        menu.addOpcion("1.- Dar de alta entrenador");
        menu.addOpcion("2.- Dar de baja entrenador");
        menu.addOpcion("3.-Consultar entrenador");
        menu.addOpcion("4.-Cazar pokemon");
        menu.addOpcion("5.-Listar Pokemons cazados.");
        menu.addOpcion("6.-Listar tipos Pokemon existentes en juego.");
        menu.verCompleto(menu);
    }
}
