package myProject;

import javax.management.openmbean.OpenType;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class GUI extends JFrame {

    private Header headerProject;
    private Model model;
    private Canvas canvas;
    private Escucha escucha;

    //Información en el botón de ayuda, en el panel de inicio: cuando se pide el user
    private static final String INFO1 = " Se requiere el nombre de usuario para guardar la partida."
            +"\n Si se confirma que has jugado anteriormente se retomará el nivel al que llegaste";
    //Información en el botón de ayuda, en el panel del juego.
    private static final String INFO2 = " Puedes salir en cualquier momento.\n"
            + "Sin embargo, si la partida no ha terminado la próxima vez que ingreses se iniciará la misma";

    private JPanel panelInicio, panelGame;
    private JTextField entradaUsuario;
    private JButton botonIniciar,BotonInstrucciones,botonSi,botonNo,botonContinuar,botonHelp,botonExit;
    private JLabel labelUsername,labelNivel,labelTiempo,labelPalabra;
    private ImageIcon  imageHelp, imageExit,imgUser,imagenFrame,imagenPanel;
    private boolean opcionHelp;
    private UIManager ui;

    /**
     * Constructor of GUI class
     */
    public GUI(){

        this.setContentPane(new Canvas1()); // Pinta la imagen del fondo del Frame
        initGUI();

        //Default JFrame configuration
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {

        //Set up JFrame Container's Layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints= new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        model = new Model();
        //Set up JComponents
        ui= new UIManager();
        ui.put("OptionPane.background",new ColorUIResource(203,39,106));


        headerProject = new Header("I KNOW THAT WORD", new Color(135,7,122));
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        botonHelp = new JButton();
        botonHelp.addMouseListener(escucha);
        imageHelp = new ImageIcon(getClass().getResource("/myProject/recursos/help1.png"));
        botonHelp.setIcon(new ImageIcon(imageHelp.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
        botonHelp.setBorderPainted(false);
        botonHelp.setFocusPainted(false);
        botonHelp.setContentAreaFilled(false);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(botonHelp,constraints);

        botonExit = new JButton();
        botonExit.addMouseListener(escucha);
        imageExit = new ImageIcon(getClass().getResource("/myProject/recursos/close1.png"));
        botonExit.setIcon(new ImageIcon(imageExit.getImage().getScaledInstance(60,40, Image.SCALE_SMOOTH)));
        botonExit.setBorderPainted(false);
        botonExit.setFocusPainted(false);
        botonExit.setContentAreaFilled(false);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(botonExit, constraints);

        panelInicio= new JPanel(new GridBagLayout());
        GridBagConstraints constra = new GridBagConstraints(); //Para el layout del panel de inicio
        panelInicio.setPreferredSize(new Dimension(900,500));
        panelInicio.setOpaque(false);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelInicio, constraints);

        labelUsername= new JLabel("USERNAME: ");
        labelUsername.setForeground(Color.WHITE);
        labelUsername.setBackground(new Color(130,54,134,150));
        labelUsername.setFont(new Font("Impact",Font.PLAIN,41));
        labelUsername.setOpaque(false);
        constra.gridx=0;
        constra.gridy=0;
        constra.gridwidth=1;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.LINE_END;
        panelInicio.add(labelUsername,constra);

        entradaUsuario= new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250,40));
        entradaUsuario.setFont(new Font("Arial ",Font.PLAIN,36));

        constra.gridx=1;
        constra.gridy=0;
        constra.gridwidth=1;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.CENTER;
        panelInicio.add(entradaUsuario, constra);

        botonIniciar= new JButton("OK");
        botonIniciar.addMouseListener(escucha);
        botonIniciar.setPreferredSize(new Dimension(65,40));
        botonIniciar.setBackground(new Color(19,15,136));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFont(new Font("Impact",Font.PLAIN,20));
        constra.gridx=2;
        constra.gridy=0;
        constra.gridwidth=1;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.LINE_START;
        panelInicio.add(botonIniciar, constra);

        opcionHelp=false;
    }

    /**
     * Esta clase crea el ícono del mensaje emergente del botón de ayuda
     * @param width  medida del ancho que se desea que tenga el icono
     * @param height  medida del alto  que se desea que tenga el icono
     * */

    public Icon iconoMessage (int width,int height){
        imgUser = new ImageIcon(getClass().getResource("/myProject/recursos/imageUser.png"));
        imgUser= new ImageIcon(imgUser.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));
        return imgUser;
    }
    /**
     * Esta clase se encarga de pintar el fondo del Jframe
     * Se llama automáticamente cada cierto tiempo
     */
    private class Canvas1 extends JPanel{

        public void paint(Graphics g){
            imagenFrame= new ImageIcon(getClass().getResource("/myProject/recursos/inicio.jpg"));
            imagenFrame= new ImageIcon(imagenFrame.getImage().getScaledInstance(900,600, Image.SCALE_SMOOTH));

            g.drawImage(imagenFrame.getImage(),0,0,getWidth(),getHeight(),null);
            setOpaque(false);
            super.paint(g);
        }
    }
    /**
     * Esta clase se encarga de pintar el fondo del panel del juego en donde se muestran las palabras
     * Se llama automáticamente cada cierto tiempo
     */
    private class Canvas2 extends JPanel{

        public void paint(Graphics g){
            imagenPanel= new ImageIcon(getClass().getResource("/myProject/recursos/fondojuego.jpg"));
            imagenPanel= new ImageIcon(imagenPanel.getImage().getScaledInstance(600,400, Image.SCALE_SMOOTH));

            g.drawImage(imagenFrame.getImage(),0,0,getWidth(),getHeight(),null);
            setOpaque(false);
            super.paint(g);
        }
    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is executed by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
           if(e.getSource()==botonExit){
               System.exit(0);
           }
            if(e.getSource()==botonHelp) {
              if(!opcionHelp){
                  JOptionPane.showMessageDialog(null,INFO1,"USERNAME",JOptionPane.PLAIN_MESSAGE,iconoMessage(50,50));
              }else{
                  JOptionPane.showMessageDialog(null,INFO2,null,JOptionPane.INFORMATION_MESSAGE);
              }
            }
            if(e.getSource()==botonIniciar){

            }


        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
