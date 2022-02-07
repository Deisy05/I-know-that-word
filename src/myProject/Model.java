package myProject;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Model {

    private Word word;
    private User user;
    FileManager fileManager= new FileManager();
    private ArrayList <User> usuarioList; //los usuarios registrados
    private ArrayList <String> palabrasCorrectas;
    private ArrayList <String> palabrasIncorrectas;
    private ArrayList <String> palabrasAMostrar;
    private String auxPalabraC, auxPalabraI;
    private int nivelUsuario;
    private boolean flag;

    /**
     * Constructor of class
     */

    public  Model(){

        usuarioList = new ArrayList<User>();
        //actualizarRegistros(); crear arraList a partir de la lectura de datos en el archivo .txt

        palabrasCorrectas = new ArrayList<String>();
        palabrasIncorrectas = new ArrayList<String>();
        palabrasAMostrar= new ArrayList<String>();
        flag=false;

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
        if (!usuarioList.isEmpty()) {
            for (User aux : usuarioList) {
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

        if (!usuarioList.isEmpty()){
            for(User auxi: usuarioList){
                if(auxi.getNombre()==nombreJugador){
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

    public void agregarPalabraCorrecta(){
        auxPalabraC=word.generarPalabra();
        if(!palabrasCorrectas.isEmpty()){
            for(String word: palabrasCorrectas){
                if(word==auxPalabraC){ agregarPalabraCorrecta(); }
            }
            flag=true; //La palabra no se encuentra en la lista de palabrasCorrectas por tanto se puede agregar.
        }
        if(palabrasCorrectas.isEmpty()|| flag){
            palabrasCorrectas.add(auxPalabraC);
            flag=false;
        }
    }

    /**
     * Método que agrega una palabra incorrecta al arrayList palabrasIncorrectas.
     * Primero verifica que la palabra no se encuentre en el arrayList 'palabrasCorrectas'
     * Luego verifica que la palabra tampoco esté en el arrayList 'palabrasIncorrectas'
     * una vez verificadas estas dos condiciones la agrega al arraylist 'palabrasIncorrectas'
     */

    public void agregarPalabraIncorrecta(){
        auxPalabraI=word.generarPalabra();

        for (String wordC: palabrasCorrectas){
            if(auxPalabraI==wordC){ agregarPalabraIncorrecta(); }
        }
        //cuando sea una palabra diferente a las que ya están en palabrasCorrectas, ejecuta este bloque
        if(!palabrasIncorrectas.isEmpty()){
            for(String wordInc: palabrasIncorrectas){
                if(wordInc==auxPalabraC){ agregarPalabraIncorrecta(); }
            }
            flag=true; //La palabra no se encuentra en la lista de palabrasIncorrectas por tanto se puede agregar.
        }
        if(palabrasIncorrectas.isEmpty()|| flag){
            palabrasIncorrectas.add(auxPalabraC);
            flag=false;
        }
    }









    /**
     * Este método guarda el usuario en usuariosListados.txt
     */


//este método registra al usuario en el archivo .txt cuando se cierra el juego
    public void guardarRegistro(){

        try{
            User aux;
            for (int i=0; i< usuarioList.size(); i++){
                aux= usuarioList.get(i);
                aux.guardar();
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
