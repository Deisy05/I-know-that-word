package myProject;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Model {

    private Diccionario diccionario;
    private User user;

    FileManager fileManager = new FileManager();

    //private ArrayList <User> usuarioList= new ArrayList<User>(); //los usuarios registrados
    private ArrayList <String> palabrasAMostrar = new ArrayList<>();
    private String auxPalabraC, auxPalabraI;
    private int nivelUsuario, errores, indiceUsuario;
    private boolean auxExisteUsuario;
    private User miUsuario;



    String nombreUsuario, palabraEnPantalla;
    int nivelesAprobados, nivelActual, cantPalabrasDelNivel, aciertos, flagPalabrasCorrectas, flagNivel,
            flagPalabrasAleatorias;
    double porcentajeAciertos;
    private ArrayList<String> palabrasNivel = new ArrayList<>();
    private ArrayList<String> arraListPalabrasCorrectas = new ArrayList<>();
    private ArrayList<String> arraListPalabrasIncorrectas = new ArrayList<>();
    private ArrayList<String> arrayDePalabrasAleatorias = new ArrayList<>();
    boolean nuevoUsuario;



    /**
     * Constructor of class
     */
    public  Model(){

    }


    /**
     * Este método restringe la entra de datos en el Jtexfield a solo carácteres sin signos especiales
     */
    public boolean validarEntradaTexto(String entrada) {
        boolean valido = true;
        int ascii = 0;
        for (char aux : entrada.toCharArray()) {
            ascii = (int) aux;
            if (ascii < 97 || ascii > 122) {
                valido = false;
                break;
            }
        }
        return valido;
    }

    /**
     *
     * @param nombreJugador
     */
    public void buscarElUsuario(String nombreJugador)
    {
        diccionario = new Diccionario();
        nuevoUsuario = false;
        nombreUsuario = nombreJugador;
        miUsuario = new User(nombreUsuario);
        if (miUsuario.determinarExistenciaJugador()){
            nivelesAprobados = miUsuario.getNivelDelJugador();
        }else{
            miUsuario.registrarJugador();
            nuevoUsuario = true;
            nivelesAprobados = 0;
        }
        aciertos=0;
        flagPalabrasCorrectas =0;
        flagNivel=0;
        setNivelActual();
        if (nivelesAprobados<8) {
            nivelActual = nivelesAprobados + 1;
        }else {
            nivelActual = nivelesAprobados;
        }
    }


    private void setNivelActual()
    {
        aciertos=0;
        nivelActual = nivelesAprobados+1;
        asignarCantidadPalabrasPorNivel();
        palabraEnPantalla = "";
        arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel /2);
        arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel /2);

        generarArrayDePalabrasAleatoriaDelNivel();
    }

    private void setNivelesAprobados(){
        if(aciertos >= cantPalabrasDelNivel *porcentajeAciertos){
            nivelesAprobados= user.setNivelDelJugador();
            setNivelActual();
            flagNivel=0;
            flagPalabrasCorrectas=0;
        }
        else{
            flagNivel=0;
            flagPalabrasCorrectas=0;
            asignarCantidadPalabrasPorNivel();
            palabraEnPantalla="";
            arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel /2);
            arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel /2);
            palabrasNivel = new ArrayList<>();
            generarArrayDePalabrasAleatoriaDelNivel();
        }

    }

    private void asignarPorcentajesPorNivel(){
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

    private void asignarCantidadPalabrasPorNivel(){
        switch (nivelActual){
            case 1-> cantPalabrasDelNivel =20;
            case 2-> cantPalabrasDelNivel =40;
            case 3-> cantPalabrasDelNivel =50;
            case 4-> cantPalabrasDelNivel =60;
            case 5-> cantPalabrasDelNivel =70;
            case 6-> cantPalabrasDelNivel =80;
            case 7-> cantPalabrasDelNivel =100;
            case 8-> cantPalabrasDelNivel =120;
            case 9-> cantPalabrasDelNivel =140;
            case 10-> cantPalabrasDelNivel =200;
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


    public String getPalabrasNivel(){
        if (flagNivel<arrayDePalabrasAleatorias.size()){
            palabraEnPantalla = arrayDePalabrasAleatorias.get(flagNivel);
            flagNivel++;
        }else{
            setNivelesAprobados();
        }
        return palabraEnPantalla;
    }



    public boolean validarPalabraCorrecta(String palabra){

        boolean perteneceCorrecta= false;
        for (String elementoListCorrecta : arraListPalabrasCorrectas) {
            if (elementoListCorrecta.equals(palabra)) {
                perteneceCorrecta = true;
                break;
            }
        }
        return perteneceCorrecta;
    }


    public void setAciertos( boolean seleccionEnGUI){
        boolean selccionCorrecta = validarPalabraCorrecta(palabraEnPantalla);
        if (seleccionEnGUI == selccionCorrecta){
            aciertos++;
        }
    }//si no responde nada?


    public int  getAciertos(){
        return aciertos;
    }


    public boolean isNuevoUsuario() {
        return nuevoUsuario;
    }


    public int getNivelActual() {
        return nivelActual;
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
     * Retorna una palabra Aleatoria para la fase 2 del juego,puede ser una palabra correcta o errónea
     * @return String
     */

    public String getPalabrasAleatorias() {
        String palabraAleatoria = "";
        if(flagPalabrasAleatorias<arrayDePalabrasAleatorias.size()){
            palabraAleatoria= arrayDePalabrasAleatorias.get(flagPalabrasAleatorias);
            int auxIndice = arrayDePalabrasAleatorias.indexOf(palabraAleatoria);
            arrayDePalabrasAleatorias.remove(auxIndice);
        }
        return palabraAleatoria;
    }




 //utilizamos lo creado buscarElUsuario
    public void jugar() {
        //create a switch sentence
        switch (nivelActual) {
            case 1 -> {System.out.println("nivel 1");
                        // validarPalabraCorrecta();
                        //determinarAciertos
                        //vaciamos palabras correctas e incorrectas
                //borrarArreglosDePalabras();
                    }
            case 2 -> {

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
        for (int i = 0; i <10;i++)
        {
            System.out.println(arraListPalabrasCorrectas.get(i));
        }
        System.out.println("**Incorrectas**");
        for (int i = 0; i <10;i++)
        {
            System.out.println(arraListPalabrasIncorrectas.get(i));
        }

        System.out.println("mostrar palabras: "+arrayDePalabrasAleatorias.size());
        //por consola total de palabras a mostrar
        for (String s : arrayDePalabrasAleatorias) {
            System.out.println(s);
        }

    }


    private void borrarArreglosDePalabras() {
        arraListPalabrasCorrectas.clear();
        arraListPalabrasIncorrectas.clear();
    }

    private void determinarAciertos() {

    }

    private void reescribirNivel() {

    }



}
