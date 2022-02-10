package myProject;

import java.util.ArrayList;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Model {

    private Word word;
    private User user;

    FileManager fileManager = new FileManager();

    private ArrayList <User> usuarioList= new ArrayList<User>(); //los usuarios registrados
    private ArrayList <String> palabrasCorrectas;
    private ArrayList <String> palabrasIncorrectas;
    private ArrayList <String> palabrasAMostrar;
    private String auxPalabraC, auxPalabraI;
    private int nivelUsuario, aciertos, errores, indiceUsuario;
    private boolean auxExisteUsuario;

    /**
     * Constructor of class
     */

    public  Model(){

        usuarioList = fileManager.lecturaUserFile();
        palabrasCorrectas = new ArrayList<String>();
        palabrasIncorrectas = new ArrayList<String>();
        palabrasAMostrar= new ArrayList<String>();
        auxExisteUsuario = false;
        word = new Word();

    }

    public void mostrarUsuarios(){
        if(usuarioList.isEmpty()){
            System.out.println("no hay usuarios aun");
        }else{
            for(User aux: usuarioList){
                System.out.println("Usuario: "+ aux.getNombre() + "\n nivel: "+ aux.getNivelDelJugador());
            }
        }
    }




    /**
     * Este método registra el jugador:
     * Si la lista de usuarios está vacía significa que el usuario es nuevo.
     * Si la lista no está vacía se debe buscar el usuario en ella, si no está significa que el usuario es nuevo.
     * Si el usuario si está en la lista entonces simplemente se le modifica el nuevo nivel.
     * @param nombreJugador
     * @param nivel
     */

    public void registrar(String nombreJugador,int nivel) {
        boolean presente=false;
        if (!usuarioList.isEmpty())
        {
            for (User aux : usuarioList)
            {
                if (aux.getNombre() == nombreJugador) {
                    usuarioList.get(usuarioList.indexOf(aux)).setNivelDelJugador(nivel);
                    presente = true;
                }
            }
        }
        if(usuarioList.isEmpty() || !presente){
            usuarioList.add(new User(nombreJugador, nivel));
        }
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
     * Este método retorna el nivel del jugar:
     * Si la lista de usuarios está vacía significa que el usuario es nuevo, por tanto su nivel será 0
     * Si la lista no está vacía se debe buscar el usuario en ella, si no está significa que el usuario es nuevo, su
     * nivel será 0.
     * Si el usuario si está en la lista entonces simplemente se obtiene el nivel guardado.
     * @param nombreJugador
     * @return nivel del jugador.
     */

//este método se usa para poder iniciar el juego desde el ultimo nivel jugado
    public int nivelDelJugador (String nombreJugador){
        int nivel = 0;

        if (!usuarioList.isEmpty())
        {
            for(User auxi: usuarioList)
            {
                if(auxi.getNombre()==nombreJugador)
                {
                    nivel= usuarioList.get(usuarioList.indexOf(auxi)).getNivelDelJugador();

                }
            }
        }
        return nivel;
    }

    /**
     * Método que agrega una palabra correcta al arrayList palabrasCorrectas.
     * verifica que la palabra no se encuentre en el arrayList para lograr agregarla.
     */

    public void agregarPalabraCorrecta()
    {
        auxPalabraC = word.generarPalabra();
        if(palabrasCorrectas.contains(auxPalabraC))
        {
            agregarPalabraCorrecta();

        }else
        {
            palabrasCorrectas.add(auxPalabraC);
        }
    }

    /**
     * Método que agrega una palabra incorrecta al arrayList palabrasIncorrectas.
     * Primero verifica que la palabra no se encuentre en el arrayList 'palabrasCorrectas'
     * Luego verifica que la palabra tampoco esté en el arrayList 'palabrasIncorrectas'
     * una vez verificadas estas dos condiciones la agrega al arraylist 'palabrasIncorrectas'
     */

    public void agregarPalabraIncorrecta()
    {
        auxPalabraC = word.generarPalabra();
        if(palabrasCorrectas.contains(auxPalabraC))
        {
            agregarPalabraIncorrecta();
        }
        if(palabrasIncorrectas.contains(auxPalabraC))
        {
            agregarPalabraIncorrecta();
        }
        else
        {
            palabrasIncorrectas.add(auxPalabraC);
        }
    }


    /**
     * Este método guarda el usuario en usuariosListados.txt
     */

//este método registra al usuario en el archivo .txt cuando se cierra el juego
    public void guardarRegistro() {
        if (!usuarioList.isEmpty()) {
            for(User jugador: usuarioList){
                jugador.guardar();
            }
        }
    }

    /**
     * This method is for searching if a user exists
     * @param userName
     * @return boolean
     */
    public void buscarElUsuario(String userName) {

        for (User value : usuarioList) {
            if (value.getNombre().equals(userName)) {
                nivelUsuario = value.getNivelDelJugador();
                auxExisteUsuario = true;
                indiceUsuario = usuarioList.indexOf(value);
                System.out.println("Jugador: "+userName+" - Existe: "+auxExisteUsuario+" - En Nivel: "+nivelUsuario);
                break;
            }
        }if(!auxExisteUsuario){
            nivelUsuario = 0;

            System.out.println("Jugador: "+userName+" - No Existe - En Nivel: "+nivelUsuario);
            registrar(userName,nivelUsuario);
        }
        //return auxExisteUsuario;
    }


    public void jugar() {
        //create a switch sentence
        switch (nivelUsuario+1) {
            case 1 ->
                    {

                        System.out.println("nivel 1");
                        for (int i = 0; i <4;i++)
                        {
                            agregarPalabraCorrecta();
                            agregarPalabraIncorrecta();
                        }
                        System.out.println("**Correctas**");
                        for (int i = 0; i <4;i++)
                        {
                            System.out.println(palabrasCorrectas.get(i));
                        }
                        System.out.println("**Incorrectas**");
                        for (int i = 0; i <4;i++)
                        {
                            System.out.println(palabrasIncorrectas.get(i));
                        }

                        palabrasAMostrar.addAll(palabrasCorrectas);
                        palabrasAMostrar.addAll(palabrasIncorrectas);
                        System.out.println("mostrar palabras: "+palabrasAMostrar.size());
                        //por consola total de palabras a mostrar
                        for (String s : palabrasAMostrar) {
                            System.out.println(s);
                        }

                        //vaciamos palabras correctas e incorrectas
                        borrarArreglosDePalabras();

                        //determinarAciertos
                        if(aciertos>=7){
                            usuarioList.get(indiceUsuario).setNivelDelJugador(nivelUsuario+1);
                        }

                    }
            case 2 -> {
                System.out.println("nivel 2");
                for (int i = 0; i <8;i++)
                {
                    agregarPalabraCorrecta();
                    agregarPalabraIncorrecta();
                }
                System.out.println("**Correctas**");
                for (int i = 0; i <8;i++) {
                    System.out.println(palabrasCorrectas.get(i));
                }
                System.out.println("**Incorrectas**");
                for (int i = 0; i <8;i++) {

                    System.out.println(palabrasIncorrectas.get(i));
                }

                borrarArreglosDePalabras();
                //determinarAciertos();
                if(aciertos>=14){
                    usuarioList.get(indiceUsuario).setNivelDelJugador(nivelUsuario+1);
                    //función que borra el arrayList de palabras correctas e incorrectas
                    //y reescribe en el .txt el usuario con su nuevo nivel
                    reescribirNivel();
                }

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

    }
    public ArrayList<String> getPalabrasAMostrar() {
        return palabrasAMostrar;
    }

    private void borrarArreglosDePalabras() {
        palabrasCorrectas.clear();
        palabrasIncorrectas.clear();
    }

    private void determinarAciertos() {

    }

    private void reescribirNivel() {

    }

//    public void contadorErrores() {
//    }

    public void verificarAcierto(){
       // if(){}
    }

}
