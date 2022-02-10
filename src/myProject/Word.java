package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Word {

    private ArrayList <String> palabras = new ArrayList<String>();


    /**
     * Constructor of Words class
     */

    public Word (){
        FileManager fileManager= new FileManager();
        palabras = fileManager.lecturaWordFile();

    }

    /**
     * Método que elige una palabra random dentro del arrayList 'palabras'.
     * el arrayList 'palabras' es creado a partir del archivo bancoDePalabras
     * @return String, retorna la palabra elegida
     */

    public String generarPalabra(){
        Random random = new Random();
        return palabras.get(random.nextInt(palabras.size()));

    }

}
