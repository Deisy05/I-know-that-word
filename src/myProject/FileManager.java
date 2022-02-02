package myProject;

import java.io.*;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;//para hacer lectura
    private FileWriter fileWriter;//para escribir
    private BufferedWriter output;


    public String lecturaFile() {
        String texto="";

        try {
            fileReader = new FileReader("src/myProject/files/diccionario.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                texto+=line;
                texto+="\n";
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
        return texto;
    }

    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter("src/myProject/files/diccionario.txt",true);
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

}