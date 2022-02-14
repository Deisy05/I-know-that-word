package myProject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * This class is created in order to manipulate the user, his name and level
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.3 date: 12/02/2022
 */
public class User
{
    private FileManager fileManager;
    private ArrayList<String> listaDeJugadores;
    private int nivel;
    private String userName;
    private boolean existeUsuario;

    /**
     * constructor of User class
     */
    public User (String playerName)
    {

        fileManager = new FileManager();
        listaDeJugadores = new ArrayList<>();
        //we generated the ArrayList called listaDeJugadores with the list of all the users in usuariosListados.txt file
        listaDeJugadores = fileManager.leerArchivos("miListaDeUsuarios");
        userName = playerName;
        existeUsuario = false;

    }

    /**
     * Getter method for player list
     * @return ArrayList<String>
     */
    public ArrayList<String> getListaDeJugadores()
    {
        return listaDeJugadores;
    }


    /**
     * Method to determine if a player exists
     * @return boolean existeUsuario
     */
    public boolean determinarExistenciaJugador()
    {
        if (buscarJugador()!=-1)
            existeUsuario = true;
        return existeUsuario;
    }

    /**
     * Method to see if a player is registered and return his index
     * @return posicion
     */
    private int buscarJugador()
    {
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
     * Method to register a new player in usuariosListados.txt
     */
    public void registrarJugador()
    {
        fileManager.escribirTexto(userName + ": " + 0);
        listaDeJugadores.add(userName+ ": "+ 0);
    }

    /**
     * Getter method for the registered level of the user
     * @return int nivel
     */
    public int getNivelDelJugador()
    {
        String usuario= listaDeJugadores.get(buscarJugador());
        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.parseInt(nivelesEnString);
    }

    /**
     * Method to update and rewrite the current level in usuariosListados.txt file
     * @return int - the new level of the user
     */
//    public int setNivelDelJugador()
//    {
//        if(getNivelDelJugador()<10){
//            fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador()+1);
//        }
//        else{
//            fileManager.actualizarNivel(buscarJugador(),0);
//        }
//        return getNivelDelJugador();
//    }

    public int setNivelDelJugador()
    {
        if(getNivelDelJugador()<10){
            fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador()+1);
        }
        else{
            fileManager.actualizarNivel(buscarJugador(),0);
        }
        return getNivelDelJugador();
    }

    /**
     * Getter method for the user name
     * @return userName
     */
    public String getNombre()
    {
        return userName;
    }

    public void guardarPartidaConstante(){
        System.out.println("guarda partida en txt");
        fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador());
    }

}
