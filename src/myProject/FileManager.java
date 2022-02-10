package myProject;

import java.io.*;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class FileManager {
    private PrintWriter vaciar;
    private FileReader fileReader;
    private BufferedReader input;//para hacer lectura
    private FileWriter fileWriter;
    private BufferedWriter output, outputt;//para escribir

    /**
     * Este método lee el archivo 'bancoDePalabras.txt' y retorna el arrayList con cada palabra del archivo
     * @return ArrayList lecturaWordFile
     */

    public ArrayList <String> lecturaWordFile() {

        ArrayList <String> arrayListPalabras = new ArrayList<String>();

        try {
            fileReader = new FileReader("src/myProject/files/bancoDePalabras.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                arrayListPalabras.add(line);
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayListPalabras;
    }

    /**
     * Este método lee el archivo 'usuariosListados.txt' y retorna el arrayList con los datos de cada usuario del
     * archivo
     * @return ArrayList lecturaUserFile
     */

    public ArrayList <User> lecturaUserFile() {
        ArrayList <User> usuariosRegistrados = new ArrayList<User>();
        try {
            fileReader = new FileReader("src/myProject/files/usuariosListados.txt");
            input = new BufferedReader(fileReader);
            String name = input.readLine();
            String nivel= input.readLine();
            while(name!=null && nivel!=null){
                int level= Integer.parseInt(nivel);
                usuariosRegistrados.add(new User(name,level));
                name=input.readLine();
                nivel=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuariosRegistrados;
    }

    /**
     * Este método escribe en el archivo 'bancoDePalabras.txt' cada dato del usuario
     * @return ArrayList lecturaWordFile
     */

    public void escribirTexto(String linea){
        try {

            fileWriter = new FileWriter("src/myProject/files/usuariosListados.txt",true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void vaciarArchivo(){
        try {
            vaciar= new PrintWriter("src/myProject/files/usuariosListados.txt");
            vaciar.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            vaciar.close();
        }
    }


}