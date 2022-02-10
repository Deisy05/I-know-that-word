package myProject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class User {
    private FileManager fileManager;
    private ArrayList<String> listaDeJugadores;
    private int nivel;
    private String userName;
    private boolean existeUsuario;

    /**
     * constructor of User class
     */
    public User (String playerName){

        fileManager = new FileManager();
        listaDeJugadores = new ArrayList<>();
        listaDeJugadores = fileManager.leerArchivos("miListaDeUsuarios");
        userName = playerName;
        existeUsuario = false;

    }
    public ArrayList<String> getListaDeJugadores() {
        return listaDeJugadores;
    }


    /**
     * determinar si existe un jugador
     * @return boolean existeUsuario
     */
    public boolean determinarExistenciaJugador() {
        if (buscarJugador()!=-1)
            existeUsuario = true;
        return existeUsuario;
    }

    /**
     * ver si un jugador está registrado
     * @return posicion
     */
    private int buscarJugador(){
        int posicion = -1;
        for (int i = 0; i < listaDeJugadores.size() && !Objects.equals(listaDeJugadores.get(i), " "); i++) {
            String auxJugador = listaDeJugadores.get(i).substring(0, listaDeJugadores.get(i).lastIndexOf(":"));
            if (auxJugador.equals(userName)){
                posicion=i;
                break;

            }

        }
        return posicion;
    }


    /**
     * registrar un jugador nuevo
     */
    public void registrarJugador()
    {
        fileManager.escribirTexto(userName + ": " + 0);
    }



    /**
     * nivel registrado del usuario
     * @return int nivel
     */
    public int getNivelDelJugador(){
        String usuario= listaDeJugadores.get(buscarJugador());
        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.parseInt(nivelesEnString);
    }

    /**
     * reescribir el nivel actual
     * @return the new level of the game
     */
    public int setNivelDelJugador(){
        if(getNivelDelJugador()<10){
            fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador()+1);
        }else{
            fileManager.actualizarNivel(buscarJugador(),0);
        }
        return getNivelDelJugador();
    }




    public String getNombre(){ return userName; }

//    public int getNivelDelJugador(){ return nivel; }
//
//    public void setNivelDelJugador(int nivelJugador){
//
//        nivel=nivelJugador;
//    }

    /**
     * Este método registra los datos del usuario en el archivo .txt
     */

    public void guardar(){
        fileManager.escribirTexto(userName);
        fileManager.escribirTexto(String.valueOf(nivel));
    }

}
