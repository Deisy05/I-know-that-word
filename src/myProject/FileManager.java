package myProject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class allows to manipulate .txt files to be read or to write to them
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.2.1 date: 15/02/2022
 */
public class FileManager
{
    private FileReader fileReader;
    private BufferedReader input;  // it allows to read the text of an input stream
    private FileWriter fileWriter;
    private BufferedWriter output; // it allows to writes text to a character-output stream
    public static final String bancoDePalabras = "src/myProject/files/bancoDePalabras.txt";   //constant location
    public static final String usuariosListados = "src/myProject/files/usuariosListados.txt"; //constant location

    /**
     * This method reads a .txt file and returns the arrayList with each word of the file
     * @param _file
     * @return ArrayList
     */
    public ArrayList<String> leerArchivos(String _file)
    {

        ArrayList<String> texto = new ArrayList<>();

        String elArchivoLeido = "";
        if (Objects.equals(_file, "miListaDePalabras"))
            elArchivoLeido = bancoDePalabras;
        else if (Objects.equals(_file, "miListaDeUsuarios"))
            elArchivoLeido = usuariosListados;

        try
        {
            //To allows reading of UTF-8. Unicode and ISO 10646 character encoding format
            fileReader = new FileReader(elArchivoLeido, StandardCharsets.UTF_8);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null)
            {
                texto.add(line);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

    /**
     * This method allows writing to the usuariosListados.txt file
     * @param linea
     */
    public void escribirTexto(String linea)
    {
        try
        {
            fileWriter = new FileWriter(usuariosListados, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                output.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method allows writing to the usuariosListados.txt file in order to update the level
     * @param posicion
     * @param nivelNuevo
     */
    public void actualizarNivel(int posicion, int nivelNuevo)
    {
        try
        {
            ArrayList<String> usuariosActualizados = leerArchivos("miListaDeUsuarios");
            String usuarioAntiguo = usuariosActualizados.get(posicion);
            String usuarioActualizado = usuarioAntiguo.substring(0, usuarioAntiguo.lastIndexOf(":") + 2) + nivelNuevo;
            usuariosActualizados.remove(posicion);
            usuariosActualizados.add(posicion, usuarioActualizado);
            fileWriter = new FileWriter(usuariosListados, false);
            output = new BufferedWriter(fileWriter);
            for (String usuariosActualizado : usuariosActualizados)
            {
                output.write(usuariosActualizado);
                output.newLine();

            }
            output.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}