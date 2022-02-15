package myProject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is created in order to manipulate the user, his name and level
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.4 date: 15/02/2022
 */
public class User
{
    private FileManager fileManager;
    private ArrayList<String> listaDeJugadores;
    private int posicion;
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
        buscarJugador();
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
        if (posicion != -1)
            existeUsuario = true;
        return existeUsuario;
    }

    /**
     * Method to see if a player is registered and return his index
     */
    private void buscarJugador()
    {
        posicion = -1;
        for (int i = 0; i < listaDeJugadores.size() && !Objects.equals(listaDeJugadores.get(i), " "); i++)
        {
            String auxJugador = listaDeJugadores.get(i).substring(0, listaDeJugadores.get(i).lastIndexOf(":"));
            if (auxJugador.equals(userName))
            {
                posicion = i;
                break;

            }

        }
    }

    /**
     * Method to register a new player in usuariosListados.txt
     */
    public void registrarJugador()
    {
        fileManager.escribirTexto(userName + ": " + 0);
        listaDeJugadores.add(userName+ ": "+ 0);
        posicion = listaDeJugadores.size()-1;
    }

    /**
     * Getter method for the registered level of the user
     * @return int
     */
    public int getNivelDelJugador()
    {
        String usuario = listaDeJugadores.get(posicion);
        String nivelesEnString = usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.parseInt(nivelesEnString);
    }

    /**
     * Method to update and rewrite the current level in usuariosListados.txt file
     */
    public void setNivelDelJugador(int nivel)
    {
         fileManager.actualizarNivel(posicion, nivel);
    }

    /**
     * Getter method for the user name
     * @return userName
     */
    public String getNombre()
    {
        return userName;
    }


}
