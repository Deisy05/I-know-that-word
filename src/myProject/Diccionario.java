package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is designed in order to determine the correct and incorrect words of the game from a .txt file
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.2 date: 12/02/2022
 */
public class Diccionario
{

    private FileManager fileManager;
    private ArrayList<String> miDiccionario;
    private ArrayList<String> palabrasCorrectas;
    private ArrayList<String> palabrasIncorrectas;


    /**
     * Constructor of Diccionario class
     */
    public Diccionario()
    {

        miDiccionario = new ArrayList<>();          // ArrayList of total words is declared
        palabrasCorrectas = new ArrayList<>();     // ArrayList of correct words is declared
        palabrasIncorrectas = new ArrayList<>();  // ArrayList of incorrect words is declared

        fileManager = new FileManager();
        //we generated the ArrayList called myDictionary with the list of all the words (200)
        miDiccionario = fileManager.leerArchivos("miListaDePalabras");

    }

    /**
     * This method generates the list of correct words of the level
     * @param nroPalabras number of correct words
     * @return ArrayList<String>
     */
    public ArrayList<String> generarPalabrasCorrectas(int nroPalabras)
    {
        return generadorDePalabras(nroPalabras, palabrasCorrectas);
    }

    /**
     * This method generates the list of incorrect words of the level
     * @param nroPalabras number of incorrect words
     * @return ArrayList<String>
     */
    public ArrayList<String> generarPalabrasIncorrectas(int nroPalabras)
    {
        return generadorDePalabras(nroPalabras, palabrasIncorrectas);
    }

    /**
     * This method selects a specified number of random words from a list
     * @param nroPalabras number of words according to the level
     * @param misPalabras ArrayList of words
     * @return ArrayList<String>
     */
    private ArrayList<String> generadorDePalabras(int nroPalabras, ArrayList<String> misPalabras)
    {
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