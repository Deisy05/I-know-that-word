package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.1 date: 12/02/2022
 */

public class Canvas  extends JPanel
{

    private int step;
    private ImageIcon imagenFrame,imagenPanel;

    /**
     * Constructor of class Canvas
     * @param option integer to determine which part of the paintComponent method to perform
     * */

    public Canvas(int option)
    {
        step = option;
    }

    /**
     * This method is responsible for painting the background of the JFrame and the panelGame
     * */
    public void paintComponent(Graphics g)
    {

        switch (step) {
            case 1 ->
                    {
                        imagenFrame = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/inicio.jpg")));
                        imagenFrame = new ImageIcon(imagenFrame.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH));
                        g.drawImage(imagenFrame.getImage(), 0, 0, getWidth(), getHeight(), null);
                        setOpaque(false);
                        super.paintComponent(g);
                    }
            case 2 ->
                    {
                        imagenPanel = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/fondojuego.jpg")));
                        imagenPanel = new ImageIcon(imagenPanel.getImage().getScaledInstance(700, 375, Image.SCALE_SMOOTH));
                        g.drawImage(imagenPanel.getImage(), 0, 0, getWidth(), getHeight(), null);
                        setOpaque(false);
                        super.paintComponent(g);
                    }
        }

    }
}