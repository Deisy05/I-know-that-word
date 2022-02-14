package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is designed in order to applies the game rules
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.1.0 date: 12/02/2022
 */
public class Model
{

    private Diccionario diccionario;

    private ArrayList <String> palabrasAMostrar = new ArrayList<>();
    private boolean flagNivel;

    private User miUsuario;

    String nombreUsuario;
    int nivelesAprobados, nivelActual, cantPalabrasDelNivel, aciertos, flagPalabrasCorrectas,flagPalabrasAleatorias;
    double porcentajeAciertos;
    private ArrayList<String> arraListPalabrasCorrectas;
    private ArrayList<String> arraListPalabrasIncorrectas;
    private ArrayList<String> arrayDePalabrasAleatorias;

    /**
     * Constructor of class
     */
    public  Model()
    {
        arraListPalabrasCorrectas = new ArrayList<>();
        arraListPalabrasIncorrectas = new ArrayList<>();
        arrayDePalabrasAleatorias = new ArrayList<>();
    }

    /**
     * This method restricts data entry in the JTexField to only characters without special signs.
     * @param entrada no debe ser null
     * @return valido
     */
    public boolean validarEntradaTexto(String entrada)
    {
        boolean valido = true;
        int ascii;
        for (char aux : entrada.toCharArray())
        {
            ascii = aux;
            if (ascii < 97 || ascii > 122) {
                valido = false;
                break;
            }
        }
        return valido;
    }

    /**
     * This method has the purpose of searching if a user exists and according to that it establishes the game level
     * @param nombreJugador String
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
        if (nivelesAprobados<8)
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
     * Asigna la cantidad de palabras que se deben mostrar en pantalla de acuerdo al nivel actual del jugador
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
     * Crea un arrayList de palabras aleatorias entre las palabras correctas e incorrectas para asi mostrarlas en
     * pantalla
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
     * Se encarga de asignar el porcentaje necesario para aprobar el nivel
     */
    private void asignarPorcentajesPorNivel()
    {
        switch (nivelActual){
            case 1, 2 -> porcentajeAciertos=0.7;
            case 3-> porcentajeAciertos=0.75;
            case 4, 5 -> porcentajeAciertos=0.8;
            case 6-> porcentajeAciertos=0.85;
            case 7, 8 -> porcentajeAciertos=0.9;
            case 9-> porcentajeAciertos=0.95;
            case 10->porcentajeAciertos=1;
        }
    }

    /**
     * Determina si la palabra indicada se encuentra en el arrayList de palabras Correctas
     * Si la palabra si se encuentra se tomara como un acierto
     * @param palabra String, es la palabra que el jugador indica al presionar el botonSI
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
     * Determina si la palabra indicada se encuentra en el arrayList de palabras incorrectas.
     *  Si la palabra si se encuentra se tomara como un acierto
     * @param palabra String, es la palabra que el jugador a indicado al presionar el botonNO
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
     * Retorna una palabra que se debe memorizar, la extrae del arrayList de Palabras Correctas
     * @return String
     */
    public String getPalabrasMemorizar() {
        String palabraMemorizar="";
        if (flagPalabrasCorrectas < arraListPalabrasCorrectas.size()){
            palabraMemorizar = arraListPalabrasCorrectas.get(flagPalabrasCorrectas);
            flagPalabrasCorrectas++;
        }

        return palabraMemorizar;
    }

    /**
     * Retorna una palabra que podría o no, haber estado en la lista de las palabras a memorizar.
     * @return String
     */
    public String getPalabrasAleatorias() {
        String palabraAleatoria = "";
        if(flagPalabrasAleatorias<arrayDePalabrasAleatorias.size()){
            palabraAleatoria= arrayDePalabrasAleatorias.get(flagPalabrasAleatorias);
            arrayDePalabrasAleatorias.remove(palabraAleatoria);
        }
        return palabraAleatoria;
    }

    /**
     * Retorna la cantidad de aciertos
     * @return int
     */
    public int  getAciertos()
    {
        return aciertos;
    }

    /**
     * Retorna el porcentaje que representan la cantidad de aciertos
     * @return int
     */

    public int porcentajeAciertos()
    {
        return ((aciertos*100)/cantPalabrasDelNivel);
    }

    /**
     * Retorna el nivel actual del jugador
     * @return int
     */
    public int getNivelActual()
    {
        return nivelActual;
    }

    /**
     * Determina si el jugador aprueba o no el nivel
     * @return boolean flagNivel
     */
    public boolean getApruebaNivel(){
        if(aciertos >= (cantPalabrasDelNivel*porcentajeAciertos)){
            flagNivel=true;
        }
        return flagNivel;
    }

    /**
     *Modifica los niveles aprobados y determina si el jugador inicia o  no desde el primer nivel
     * @param repetir boolean, indica si el jugador desea o no empezar desde el nivel 1
     */

    public void setNivelesAprobados(boolean repetir){
        borrarArreglosDePalabras();
        if(repetir && (nivelActual == 10)){
            nivelActual=1;
            miUsuario.setNivelDelJugador(0);
            flagNivel= false;
        }
        //else if(repetir && (nivelActual == 10)){ flagNivel=false;}
        else if(!repetir && nivelActual==10){
            flagNivel=false;
            miUsuario.setNivelDelJugador(nivelActual);
        }else {
            miUsuario.setNivelDelJugador(nivelActual);
        }

        setNivelActual();
        flagPalabrasCorrectas=0;

    }

    /**
     * Elimina el contenido de los arreglos creados
     */

    private void borrarArreglosDePalabras()
    {
        arraListPalabrasCorrectas.clear();
        arraListPalabrasIncorrectas.clear();
        arrayDePalabrasAleatorias.clear();
    }

    //utilizamos lo creado buscarElUsuario
    public void jugar()
    {
        //create a switch sentence
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
        //por consola total de palabras a mostrar
        for (String elementoX : arrayDePalabrasAleatorias) {
            System.out.print(elementoX+" ");
        }

        System.out.println("\nPALABRAS DEL ARREGLO PALABRAS A MOSTRAR");
        for (String palabritaM: palabrasAMostrar){
            System.out.print(palabritaM +" ");
        }

    }

}
