package myProject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class Diccionario {

    private FileManager fileManager;
    private ArrayList<String> miDiccionario;
    private ArrayList<String> palabrasCorrectas;
    private ArrayList<String> palabrasIncorrectas;


    /**
     * Constructor of Diccionario class
     */
    public Diccionario() {

        miDiccionario = new ArrayList<>();
        palabrasCorrectas = new ArrayList<>();
        palabrasIncorrectas = new ArrayList<>();

        fileManager = new FileManager();
        miDiccionario = fileManager.leerArchivos("miListaDePalabras");

    }

    /**
     * generar lista de palabras correctas del nivel
     * @param nroPalabras cantidad de palabras correctas
     * @return ArrayList<String> palabrasCorrectas
     */
    public ArrayList<String> generarPalabrasCorrectas(int nroPalabras) {
        return generadorDePalabras(nroPalabras, palabrasCorrectas);
    }

    /**
     * generar lista de palabras incorrectas del nivel
     * @param nroPalabras cantidad de palabras correctas
     * @return ArrayList palabrasIncorrectas
     */
    public ArrayList<String> generarPalabrasIncorrectas(int nroPalabras) {
        return generadorDePalabras(nroPalabras, palabrasIncorrectas);
    }

    /**
     *
     * @param nroPalabras
     * @param misPalabras
     * @return ArrayList<String>
     */
    private ArrayList<String> generadorDePalabras(int nroPalabras, ArrayList<String> misPalabras) {
        for (int i = 0; i < nroPalabras; i++)
        {
            Random random = new Random();
            int auxIndex = random.nextInt(miDiccionario.size());
            misPalabras.add(miDiccionario.get(auxIndex));
            miDiccionario.remove(auxIndex);
        }
        return misPalabras;
    }

    /**
     * determinar si existe un jugador
     * @return boolean existeUsuario
     */
//    public boolean determinarExistenciaJugador() {
//        if (buscarJugador()!=-1)
//            existeUsuario = true;
//        return existeUsuario;
//    }

    /**
     * ver si un jugador está registrado
     * @return posicion
     */
//    private int buscarJugador(){
//        int posicion = -1;
//        for (int i = 0; i < listaDeJugadores.size() && !Objects.equals(listaDeJugadores.get(i), " "); i++) {
//            String auxJugador = listaDeJugadores.get(i).substring(0, listaDeJugadores.get(i).lastIndexOf(":"));
//            if (auxJugador.equals(userName)){
//                posicion=i;
//                break;
//
//            }
//
//        }
//        return posicion;
//    }


    /**
     * registrar un jugador nuevo
     */
//    public void registrarJugador()
//    {
//        fileManager.escribirTexto(userName + ": " + 0);
//    }
//
//
//
//    /**
//     * nivel registrado del usuario
//     * @return int nivel
//     */
//    public int getNivelDelJugador(){
//        String usuario= listaDeJugadores.get(buscarJugador());
//        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
//        return Integer.parseInt(nivelesEnString);
//    }
//
//    /**
//     * reescribir el nivel actual
//     * @return the new level of the game
//     */
//    public int setNivelDelJugador(){
//        if(getNivelDelJugador()<10){
//            fileManager.actualizarNivel(buscarJugador(),getNivelDelJugador()+1);
//        }else{
//            fileManager.actualizarNivel(buscarJugador(),0);
//        }
//        return getNivelDelJugador();
//    }


}
