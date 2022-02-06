package myProject;

import javax.swing.*;
import java.awt.*;
/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 05/02/2022
 */

public class Canvas  extends JPanel {

    private int step;
    private ImageIcon imagenFrame,imagenPanel;

/**
 * Constructor of class
 * @param option entero para determinar qué parte del método paintComponent se debe realizar
 * */

    public Canvas(int option){
        step= option;

    }
/**
 * Esta clase se encarga de pintar el fondo del JFrame y del panelGame
 *
 */
    public void paintComponent(Graphics g){

        switch (step){
            case 1:
                imagenFrame= new ImageIcon(getClass().getResource("/myProject/recursos/inicio.jpg"));
                imagenFrame= new ImageIcon(imagenFrame.getImage().getScaledInstance(900,600, Image.SCALE_SMOOTH));
                g.drawImage(imagenFrame.getImage(),0,0,getWidth(),getHeight(),null);
                setOpaque(false);
                super.paintComponent(g);

                break;
            case 2:
                imagenPanel= new ImageIcon(getClass().getResource("/myProject/recursos/fondojuego.jpg"));
                imagenPanel= new ImageIcon(imagenPanel.getImage().getScaledInstance(700,375, Image.SCALE_SMOOTH));
                g.drawImage(imagenPanel.getImage(),0,0,getWidth(),getHeight(),null);
                setOpaque(false);
                super.paintComponent(g);
                break;
        }

    }
}
