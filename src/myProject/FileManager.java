package myProject;

import java.io.*;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class FileManager
{
    private FileReader fileReader;
    private BufferedReader input;//para hacer lectura
    private FileWriter fileWriter;
    private BufferedWriter output;//para escribir
    public static final String bancoDePalabras = "src/myProject/files/bancoDePalabras.txt"; //dirección constante
    public static final String usuariosListados = "src/myProject/files/usuariosListados.txt";//dirección constante

    /**
     * Este método lee el archivo 'bancoDePalabras.txt' y retorna el arrayList con cada palabra del archivo
     *
     * @return ArrayList lecturaWordFile
     */

    public ArrayList<String> leerArchivos(String _file)
    {

        ArrayList<String> texto = new ArrayList<>();

        String elArchivoLeido = "";
        if (Objects.equals(_file, "miListaDePalabras")) {
            elArchivoLeido = bancoDePalabras;
        } else if (Objects.equals(_file, "miListaDeUsuarios")) {
            elArchivoLeido = usuariosListados;
        }

        try {
            fileReader = new FileReader(elArchivoLeido);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                texto.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

    /**
     * Este método escribe en el archivo 'bancoDePalabras.txt' cada dato del usuario
     *
     * @return ArrayList lecturaWordFile
     */

    public void escribirTexto(String linea)
    {
        try {
            fileWriter = new FileWriter(usuariosListados, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Actualiza los datos del jugador
     * @param posicion
     * @param nivelNuevo
     */

    public void actualizarNivel(int posicion, int nivelNuevo)
    {
        try {
            ArrayList<String> usuariosActualizados = leerArchivos("miListaDeUsuarios");
            String usuarioAntiguo = usuariosActualizados.get(posicion);
            String usuarioActualizado = usuarioAntiguo.substring(0, usuarioAntiguo.lastIndexOf(":") + 2) + nivelNuevo;
            usuariosActualizados.remove(posicion);
            usuariosActualizados.add(posicion, usuarioActualizado);
            fileWriter = new FileWriter(usuariosListados, false);
            output = new BufferedWriter(fileWriter);
            for (String usuariosActualizado : usuariosActualizados) {
                output.write(usuariosActualizado);
                output.newLine();

            }
            output.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}