package myProject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Diccionario {

    private FileManager fileManager;
    private ArrayList<String> miDiccionario;
    private ArrayList<String> palabrasCorrectas;
    private ArrayList<String> palabrasIncorrectas;


    /**
     * Constructor of Diccionario class
     */
    public Diccionario() {

        miDiccionario = new ArrayList<>(); // arreglo auxiliar para generar palabras
        palabrasCorrectas = new ArrayList<>();
        palabrasIncorrectas = new ArrayList<>();

        fileManager = new FileManager();
        miDiccionario = fileManager.leerArchivos("miListaDePalabras");

    }

    /**
     * Retorna lista de palabras correctas del nivel
     * @param nroPalabras cantidad de palabras correctas
     * @return ArrayList<String>
     */
    public ArrayList<String> generarPalabrasCorrectas(int nroPalabras) {
        return generadorDePalabras(nroPalabras, palabrasCorrectas);
    }

    /**
     * retorna lista de palabras incorrectas del nivel
     * @param nroPalabras cantidad de palabras correctas
     * @return ArrayList<String>
     */
    public ArrayList<String> generarPalabrasIncorrectas(int nroPalabras) {
        return generadorDePalabras(nroPalabras, palabrasIncorrectas);
    }

    /**
     * Genera lista de palabras
     * @param nroPalabras cantidad de palabras de acuerdo al nivel
     * @param misPalabras Lista de palabras en el archivo
     * @return ArrayList<String>
     */
    private ArrayList<String> generadorDePalabras(int nroPalabras, ArrayList<String> misPalabras) {
        for (int i = 0; i < nroPalabras; i++)
        {
            Random random = new Random();
            int auxIndex = random.nextInt(miDiccionario.size());
            misPalabras.add(miDiccionario.get(auxIndex));
            miDiccionario.remove(auxIndex);
        }
        return misPalabras;
    }


}
