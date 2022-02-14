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
    private User user;

    FileManager fileManager = new FileManager();

    private ArrayList <String> palabrasAMostrar = new ArrayList<>();
    private String auxPalabraC, auxPalabraI;
    private int nivelUsuario, errores, indiceUsuario;
    private boolean flagNivel;

    private User miUsuario;



    String nombreUsuario;
    int nivelesAprobados, nivelActual, cantPalabrasDelNivel, aciertos, flagPalabrasCorrectas,flagPalabrasAleatorias;
    double porcentajeAciertos;
    private ArrayList<String> palabrasNivel = new ArrayList<>();
    private ArrayList<String> arraListPalabrasCorrectas;
    private ArrayList<String> arraListPalabrasIncorrectas;
    private ArrayList<String> arrayDePalabrasAleatorias;
    boolean nuevoUsuario;



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
        nuevoUsuario = false;
        nombreUsuario = nombreJugador;
        miUsuario = new User(nombreUsuario);
        if (miUsuario.determinarExistenciaJugador())
        {
            nivelesAprobados = miUsuario.getNivelDelJugador();
        }
        else
        {
            miUsuario.registrarJugador();
            nuevoUsuario = true;//-------------------------------------------------------no se si es util
            nivelesAprobados = 0;
        }

        flagPalabrasCorrectas = 0;
        flagNivel = false;
        if (nivelesAprobados<8)
        {
            nivelActual = nivelesAprobados + 1;
        }
//        if (nivelesAprobados>=8 && nivelesAprobados<=10)
//        {
//            nivelActual = 8;
//        }
        else
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
        if(getApruebaNivel() & nivelesAprobados<10){
            nivelActual++;
        }
        asignarCantidadPalabrasPorNivel();
        asignarPorcentajesPorNivel();
        arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel/2);
        arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel/2);
        generarArrayDePalabrasAleatoriaDelNivel();

//        if(getApruebaNivel() & nivelActual ==10){
//            nivelActual = 0;
//            asignarCantidadPalabrasPorNivel();
//            asignarPorcentajesPorNivel();
//            arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel/2);
//            arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel/2);
//            generarArrayDePalabrasAleatoriaDelNivel();
//        }
    }

    private void asignarCantidadPalabrasPorNivel()
    {
        switch (nivelActual){
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

            case 1-> cantPalabrasDelNivel = 2;
            case 2-> cantPalabrasDelNivel = 2;
            case 3-> cantPalabrasDelNivel = 2;
            case 4-> cantPalabrasDelNivel = 2;
            case 5-> cantPalabrasDelNivel = 2;
            case 6-> cantPalabrasDelNivel = 2;
            case 7-> cantPalabrasDelNivel = 2;
            case 8-> cantPalabrasDelNivel = 2;
            case 9-> cantPalabrasDelNivel = 2;
            case 10-> cantPalabrasDelNivel = 2;
        }
    }

    private void generarArrayDePalabrasAleatoriaDelNivel()
    {

        palabrasAMostrar.addAll(arraListPalabrasCorrectas);
        palabrasAMostrar.addAll(arraListPalabrasIncorrectas);
        ArrayList<String> auxPalabrasAMostrar = palabrasAMostrar;

        while (auxPalabrasAMostrar.size()>0)
        {
            Random random = new Random();
            String palabra = auxPalabrasAMostrar.get(random.nextInt(auxPalabrasAMostrar.size()));
            int auxIndice = auxPalabrasAMostrar.indexOf(palabra);
            arrayDePalabrasAleatorias.add(palabra);
            auxPalabrasAMostrar.remove(auxIndice);
        }
    }

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

    public void validarPalabraCorrecta(String palabra)
    {

        for (String elementoListCorrecta : arraListPalabrasCorrectas) {
            if (elementoListCorrecta.equals(palabra)) {
                aciertos++;
                break;
            }
        }
    }

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

    public String getPalabrasAleatorias() {
        String palabraAleatoria = "";
        if(flagPalabrasAleatorias<arrayDePalabrasAleatorias.size()){
            palabraAleatoria= arrayDePalabrasAleatorias.get(flagPalabrasAleatorias);
            int auxIndice = arrayDePalabrasAleatorias.indexOf(palabraAleatoria);
            arrayDePalabrasAleatorias.remove(auxIndice);
        }
        return palabraAleatoria;
    }

    public int  getAciertos()
    {
        return aciertos;
    }

    public boolean getApruebaNivel()
    {
        return flagNivel;
    }

    public int porcentajeAciertos()
    {
        return ((aciertos*100)/cantPalabrasDelNivel);
    }

    public int getNivelActual()
    {
        return nivelActual;
    }

    public void setNivelesAprobados(){
        borrarArreglosDePalabras();
        if(aciertos >= cantPalabrasDelNivel*porcentajeAciertos){
            nivelesAprobados = miUsuario.setNivelDelJugador();
            flagNivel=true;
            setNivelActual();
            flagPalabrasCorrectas=0;
        }
        else{
            flagNivel=false;
            flagPalabrasCorrectas=0;
            setNivelActual();
        }

    }

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
            case 1 -> {
                System.out.println("nivel 1");

            }
            case 2 -> {
                System.out.println("nivel 2");

            }
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

    }

    public void guardaPartidaConstante()
    {
        miUsuario.setNivelDelJugador();
    }

    public User getMiUsuario() {
        return miUsuario;
    }

}
