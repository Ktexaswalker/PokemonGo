/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitxers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
/**
 *
 * @author hema5364
 */
public class Caratula {
    /* Atributs necessaris per llegir un fitzer */
    File ruta;
    FileReader reader;
    BufferedReader fichero;
    /* constructor necessari */
    public Caratula(String ruta_relativa) throws FileNotFoundException {
        ruta = new File(ruta_relativa);
        reader = new FileReader(ruta);
        fichero = new BufferedReader(reader);
    }
    /* recuperarDatos */
    public ArrayList<String> recuperarDatos(/*no se sabe si se pasan o no parametros...*/) throws IOException {
        //abrir fichero
        //--//ArrayList<String> array = (ArrayList<String>) fichero.lines();
        String linea;
        ArrayList<String> array = new ArrayList<>();
        //recuperar linea a linea y guardarlo en un arrayList<String>
        while ((linea = fichero.readLine()) != null ){
            array.add(linea);
        }
        //no souts!
        reader.close();
        fichero.close();
        return array;
    }

    public File getRuta() {
        return ruta;
    }

    public void setRuta(File ruta) {
        this.ruta = ruta;
    }

    public FileReader getReader() {
        return reader;
    }

    public void setReader(FileReader reader) {
        this.reader = reader;
    }

    public BufferedReader getFichero() {
        return fichero;
    }

    public void setFichero(BufferedReader fichero) {
        this.fichero = fichero;
    }
}
