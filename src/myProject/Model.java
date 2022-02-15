package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is designed in order to applies the game rules
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.1.1 date: 14/02/2022
 */
public class Model
{

    private Diccionario diccionario;
    private boolean flagNivel;
    private User miUsuario;
    String nombreUsuario;
    int nivelesAprobados, nivelActual, cantPalabrasDelNivel, aciertos, flagPalabrasCorrectas,flagPalabrasAleatorias;
    double porcentajeAciertos;
    private ArrayList<String> arraListPalabrasCorrectas;
    private ArrayList<String> arraListPalabrasIncorrectas;
    private ArrayList<String> arrayDePalabrasAleatorias;
    private ArrayList <String> palabrasAMostrar;


    /**
     * Constructor of class
     */
    public  Model()
    {
        arraListPalabrasCorrectas = new ArrayList<>();
        arraListPalabrasIncorrectas = new ArrayList<>();
        arrayDePalabrasAleatorias = new ArrayList<>();
        palabrasAMostrar = new ArrayList<>();
    }

    /**
     * This method restricts data entry in the JTexField to only characters without special signs.
     * @param entrada
     * @return valido
     */
    public boolean validarEntradaTexto(String entrada)
    {
        boolean valido = true;
        int ascii = 0;
        for (char aux : entrada.toCharArray())
        {
            ascii = (int) aux;
            if (ascii < 97 || ascii > 122) {
                valido = false;
                break;
            }
        }
        return valido;
    }

    /**
     * This method has the purpose of searching if a user exists and according to that it establishes the game level
     * @param nombreJugador
     */
    public void buscarElUsuario(String nombreJugador)
    {
        diccionario = new Diccionario();
        nombreUsuario = nombreJugador;
        miUsuario = new User(nombreUsuario);
        if (miUsuario.determinarExistenciaJugador())
        {
            nivelesAprobados = miUsuario.getNivelDelJugador();
        }
        else
        {
            miUsuario.registrarJugador();
            nivelesAprobados = 0;
        }

        flagPalabrasCorrectas = 0;
        flagNivel = false;
        if (nivelesAprobados<10)
        {
            nivelActual = nivelesAprobados + 1;
        }else
        {
            nivelActual = nivelesAprobados;
        }
        setNivelActual();

    }

    /**
     * This method sets all the words that will be in the game per level (corrects and incorrect)
     */
    private void setNivelActual()
    {
        aciertos=0;
        if(flagNivel){
            nivelActual++;
            flagNivel=false;
        }
        asignarCantidadPalabrasPorNivel();
        asignarPorcentajesPorNivel();
        arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel/2);
        arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel/2);
        generarArrayDePalabrasAleatoriaDelNivel();
    }

    /**
     * This method has the purpose of assigning the number of words that
     * should be displayed on the screen according to the current level of the player
     */
    private void asignarCantidadPalabrasPorNivel()
    {
//        switch (nivelActual){
//            case 1-> cantPalabrasDelNivel =20;
//            case 2-> cantPalabrasDelNivel =40;
//            case 3-> cantPalabrasDelNivel =50;
//            case 4-> cantPalabrasDelNivel =60;
//            case 5-> cantPalabrasDelNivel =70;
//            case 6-> cantPalabrasDelNivel =80;
//            case 7-> cantPalabrasDelNivel =100;
//            case 8-> cantPalabrasDelNivel =120;
//            case 9-> cantPalabrasDelNivel =140;
//            case 10-> cantPalabrasDelNivel =200;

//        }
        cantPalabrasDelNivel=2;
    }

    /**
     * Method to create an arrayList of random words containing
     * the correct and incorrect words to display on the screen
     */
    private void generarArrayDePalabrasAleatoriaDelNivel()
    {
        palabrasAMostrar.addAll(arraListPalabrasCorrectas);
        palabrasAMostrar.addAll(arraListPalabrasIncorrectas);
        ArrayList<String> auxPalabrasAMostrar = palabrasAMostrar;

        while (auxPalabrasAMostrar.size()>0)
        {
            Random random = new Random();
            String palabra = auxPalabrasAMostrar.get(random.nextInt(auxPalabrasAMostrar.size()));
            arrayDePalabrasAleatorias.add(palabra);
            auxPalabrasAMostrar.remove(palabra);
        }
    }

    /**
     * This method is responsible for assigning the necessary percentage to pass the current level
     */
    private void asignarPorcentajesPorNivel()
    {
        switch (nivelActual){
            case 1, 2 -> porcentajeAciertos = 0.7;
            case 3-> porcentajeAciertos = 0.75;
            case 4, 5 -> porcentajeAciertos = 0.8;
            case 6-> porcentajeAciertos = 0.85;
            case 7, 8 -> porcentajeAciertos = 0.9;
            case 9-> porcentajeAciertos = 0.95;
            case 10->porcentajeAciertos = 1;
        }
    }

    /**
     * Method that determines if the word received as parameter is in the arrayList of Correct words.
     * If the word if is found, it will be taken as a hit.
     * This parameter is the word that the player indicates when pressing the SI button
     * @param palabra
     */
    public void validarPalabraCorrecta(String palabra)
    {
        for (String elementoListCorrecta : arraListPalabrasCorrectas) {
            if (elementoListCorrecta.equals(palabra)) {
                aciertos++;
                break;
            }
        }
    }

    /**
     * Method that determines if the word received as parameter is in the arrayList of Incorrect words.
     * If the word if is found, it will be taken as a hit.
     * This parameter is the word that the player indicates when pressing the NO button
     * @param palabra
     */
    public void validarPalabraIncorrecta(String palabra)
    {
        for (String elementoListIncorrecta : arraListPalabrasIncorrectas) {
            if (elementoListIncorrecta.equals(palabra)) {
                aciertos++;
                break;
            }
        }
    }

    /**
     * This method returns a word to be memorized, extracts it from the arrayList of Correct Words
     * @return String palabraMemorizar
     */
    public String getPalabrasMemorizar()
    {
        String palabraMemorizar = "";
        if (flagPalabrasCorrectas < arraListPalabrasCorrectas.size())
        {
            palabraMemorizar = arraListPalabrasCorrectas.get(flagPalabrasCorrectas);
            flagPalabrasCorrectas++;
        }
        return palabraMemorizar;
    }

    /**
     * This method returns a word to display on the screen, it can be a correct or incorrect word.
     * This word comes from arrayDePalabrasAleatorias ArrayList
     * @return String palabraAleatoria
     */
    public String getPalabrasAleatorias()
    {
        String palabraAleatoria = "";
        if(flagPalabrasAleatorias < arrayDePalabrasAleatorias.size())
        {
            palabraAleatoria = arrayDePalabrasAleatorias.get(flagPalabrasAleatorias);
            arrayDePalabrasAleatorias.remove(palabraAleatoria);
        }
        return palabraAleatoria;
    }

    /**
     * Getter method of the hits number
     * @return int aciertos
     */
    public int  getAciertos()
    {
        return aciertos;
    }

    /**
     * This method returns the percentage that represents the number of hits
     * @return int
     */
    public int porcentajeAciertos()
    {
        return ((aciertos*100)/cantPalabrasDelNivel);
    }

    /**
     * Getter method of current level
     * @return int nivelActual
     */
    public int getNivelActual()
    {
        return nivelActual;
    }

    /**
     * This method determines whether or not the player passes the level
     * @return boolean flagNivel
     */
    public boolean getApruebaNivel(){
        if(aciertos >= (cantPalabrasDelNivel*porcentajeAciertos)){
            flagNivel = true;
        }
        return flagNivel;
    }

    /**
     * This method modifies approved levels and determines whether or not the player starts from the first level.
     * The repetir variable indicates whether or not the player wants to start from level 1
     * @param repetir
     */
    public void setNivelesAprobados(boolean repetir){
        borrarArreglosDePalabras();
        if(repetir && (nivelActual == 10)){
            nivelActual = 1;
            miUsuario.setNivelDelJugador(0);
            flagNivel= false;
        }
        else if(!repetir && nivelActual == 10){
            flagNivel=false;
            miUsuario.setNivelDelJugador(nivelActual);
        }else if(flagNivel)
        {
            miUsuario.setNivelDelJugador(nivelActual);
        }

        setNivelActual();
        flagPalabrasCorrectas = 0;

    }

    /**
     * Method created in order to delete the ArrayLists content used for the words of the level in play
     */
    private void borrarArreglosDePalabras()
    {
        arraListPalabrasCorrectas.clear();
        arraListPalabrasIncorrectas.clear();
        arrayDePalabrasAleatorias.clear();
    }


    public void hacerPruebasPorConsola()
    {

        switch (nivelActual) {
            case 1 -> System.out.println("nivel 1");
            case 2 -> System.out.println("nivel 2");
            case 3 -> System.out.println("nivel 3");
            case 4 -> System.out.println("nivel 4");
            case 5 -> System.out.println("nivel 5");
            case 6 -> System.out.println("nivel 6");
            case 7 -> System.out.println("nivel 7");
            case 8 -> System.out.println("nivel 8");
            case 9 -> System.out.println("nivel 9");
            case 10 -> System.out.println("nivel 10");
        }


        System.out.println("**Correctas**");
        for (String elementoC : arraListPalabrasCorrectas) {
            System.out.print(elementoC + " ");
        }
        System.out.println("\n**Incorrectas**");
        for (String elementoI : arraListPalabrasIncorrectas) {
            System.out.print(elementoI + " ");
        }

        System.out.println("\nPalabras aleatorias: "+arrayDePalabrasAleatorias.size());

    }
}
