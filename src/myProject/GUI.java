package myProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
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
    private Escucha escucha;

    //Información en el botón de ayuda, en el panel de inicio: cuando se pide el user
    private static final String INFO1 = " El nombre usuario es importante para guardar la partida. "
            +"\n Si se confirma que has jugado anteriormente se retomará el nivel al que llegaste. ";
    //Información en el botón de ayuda, en el panel del juego.
    private static final String INFO2 = " Puedes salir en cualquier momento.\n"
            + "Sin embargo, si la partida no ha terminado la próxima vez que ingreses se iniciará la misma. ";

    private JPanel panelInicio, panelGame,panelBotones;
    private JTextField entradaUsuario;
    private JButton botonOK, botonHelp, botonExit, botonIniciar, botonInstrucciones, botonSi, botonNo, botonContinuar;
    private JLabel labelUsername, labelInstrucciones, labelNivel, labelTiempo, labelPalabra;
    private ImageIcon  image;
    private boolean opcionHelp;
    private String nombreJugador;

    private UIManager ui;
    private GridBagConstraints constraints; //componente del layout del JFrame

    /**
     * Constructor of GUI class
     */
    public GUI(){
        this.setContentPane(new Canvas(1)); // Pinta la imagen del fondo del Frame
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
        constraints= new GridBagConstraints();//GridBagConstraints constraints= new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        model = new Model();
        //Set up JComponents
        //ui= new UIManager();
        //ui.put("OptionPane.background",new ColorUIResource(203,39,106));

        opcionHelp=false; //Se usa para saber qué mensaje en el botón de ayuda se debe mostrar
        //Encabezado del frame
        headerProject = new Header("I KNOW THAT WORD", new Color(135,7,122));
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);
        //Creación de botones
        botonHelp = new JButton();
        botonHelp.addMouseListener(escucha);
        botonHelp.setPreferredSize(new Dimension(80,50));
        image = new ImageIcon(getClass().getResource("/myProject/recursos/help1.png"));
        botonHelp.setIcon(new ImageIcon(image.getImage().getScaledInstance(80,50, Image.SCALE_SMOOTH)));
        botonHelp.setBorderPainted(false);
        botonHelp.setContentAreaFilled(false);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(botonHelp,constraints);

        botonExit = new JButton();
        botonExit.addMouseListener(escucha);
        image = new ImageIcon(getClass().getResource("/myProject/recursos/close1.png"));
        botonExit.setPreferredSize(new Dimension(80,50));
        botonExit.setIcon(new ImageIcon(image.getImage().getScaledInstance(80,50, Image.SCALE_SMOOTH)));
        botonExit.setBorderPainted(false);
        botonExit.setContentAreaFilled(false);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(botonExit, constraints);
        //panel que contiene el label del usuario, la entrada de texto y el botón de confirmación
        panelInicio= new JPanel(new GridBagLayout()); //Set up JPanel Container's Layout
        panelInicio.setPreferredSize(new Dimension(900,500));
        panelInicio.setOpaque(false);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelInicio, constraints);
        componentesDelPanelInicio();

    }

    /**
     * Este método crea los siguientes componentes y los agrega al panel de inicio:
     * labelUsername: Etiqueta para indicar lo que se desea que ingrese el usuario en la caja de texto
     * entradaUsuario: Componente para la entrada del texto
     * botonOk: Botón de confirmación luego de ingresar el nombre de usuario
     */

    public void componentesDelPanelInicio(){

        GridBagConstraints constra = new GridBagConstraints(); //Componente del layout
        //etiqueta
        labelUsername= new JLabel();
        image=  new ImageIcon(getClass().getResource("/myProject/recursos/username.png"));
        labelUsername.setIcon(new ImageIcon(image.getImage().getScaledInstance(200,50, Image.SCALE_SMOOTH)));
        constra.gridx=0;
        constra.gridy=0;
        constra.gridwidth=2;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.CENTER;
        panelInicio.add(labelUsername,constra);
        //Cajón de entrada del texto
        entradaUsuario= new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250,40));
        entradaUsuario.setFont(new Font("Arial ",Font.PLAIN,30));
        constra.gridx=0;
        constra.gridy=1;
        constra.gridwidth=1;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.LINE_END;
        panelInicio.add(entradaUsuario, constra);
        //Boton de confirmación
        botonOK= new JButton();
        botonOK.addMouseListener(escucha);
        botonOK.setPreferredSize(new Dimension(57,57));
        image = new ImageIcon(getClass().getResource("/myProject/recursos/okB.png"));
        botonOK.setIcon(new ImageIcon(image.getImage().getScaledInstance(57,57, Image.SCALE_SMOOTH)));
        botonOK.setBorderPainted(false);
        botonOK.setContentAreaFilled(false);
        constra.gridx=1;
        constra.gridy=1;
        constra.gridwidth=1;
        constra.fill=GridBagConstraints.NONE;
        constra.anchor=GridBagConstraints.LINE_START;
        panelInicio.add(botonOK, constra);

    }

    /**
     * Esta clase crea el ícono del mensaje emergente del boton de ayuda
     * @param reference es la ubicacion de la imagen
     * @param width  medida del ancho que se desea que tenga el icono
     * @param height  medida del alto  que se desea que tenga el icono
     * */

    public Icon iconoMessage (String reference,int width,int height){
        image = new ImageIcon(getClass().getResource(reference));
        image= new ImageIcon(image.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));
        return image;
    }


    /**
     * Este método crea el panel de inicio del juego luego de la confirmación de usuario
     *
     */
    public void crearInicioJuego(){
        panelGame= new Canvas(2);//Crea el panel con la imagen
        //panelGame.setLayout(new GridBagLayout());//Set up JPanel Container's Layout
        //GridBagConstraints constraint= new GridBagConstraints(); //Componente del layout del panelGame
        panelGame.setPreferredSize(new Dimension(700,400));
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=2;
        constraints.fill= GridBagConstraints.NONE;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(panelGame,constraints);
        crearPanelBotones();
        crearComponentesPanelGame(nombreJugador);

        revalidate();
        repaint();

    }

    /**
     * Este método crea el panel en que se ubican los botones del juego.
     * Además, crea los componentes:
     * Botón de Instrucciones
     * Botón de Iniciar Partida
     */

    public void crearPanelBotones(){

        panelBotones= new JPanel();
        panelBotones.setPreferredSize(new Dimension(900,100));
        panelBotones.setOpaque(false);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelBotones, constraints);

        botonInstrucciones= new JButton();
        botonInstrucciones.addMouseListener(escucha);
        botonInstrucciones.setPreferredSize(new Dimension(200,65));
        image = new ImageIcon(getClass().getResource("/myProject/recursos/instruB.png"));
        botonInstrucciones.setIcon(new ImageIcon(image.getImage().getScaledInstance(200,65, Image.SCALE_SMOOTH)));
        botonInstrucciones.setBorderPainted(false);
        botonInstrucciones.setContentAreaFilled(false);
        panelBotones.add(botonInstrucciones);

        botonIniciar= new JButton();
        botonIniciar.addMouseListener(escucha);
        botonIniciar.setPreferredSize(new Dimension(200,65));
        image = new ImageIcon(getClass().getResource("/myProject/recursos/playB.png"));
        botonIniciar.setIcon(new ImageIcon(image.getImage().getScaledInstance(200,65, Image.SCALE_SMOOTH)));
        botonIniciar.setBorderPainted(false);
        botonIniciar.setContentAreaFilled(false);
        panelBotones.add(botonIniciar);


    }

    public void crearComponentesPanelGame(String nombreU){
        //labelNivel
        //labelTime,
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
               model.guardarRegistro();
               System.exit(0);
           }
            if(e.getSource()==botonHelp) {
              if(!opcionHelp){
                  JOptionPane.showMessageDialog(null,INFO1,"USERNAME",JOptionPane.PLAIN_MESSAGE,iconoMessage(
                          "/myProject/recursos/imageUser.png",50,50));
              }else{
                  JOptionPane.showMessageDialog(null,INFO2,null,JOptionPane.INFORMATION_MESSAGE);
              }
            }
            if(e.getSource()==botonOK){
                //confirmarNivel();
                if(!entradaUsuario.getText().isEmpty()){
                    nombreJugador=entradaUsuario.getText();
                    //validar que no tenga caracteres especiales
                    if( model.validarEntradaTexto(nombreJugador)){
                        model.registrar(nombreJugador,0); //para la prueba, se debe configurar bien

                        opcionHelp=true;
                        remove(panelInicio);
                        revalidate();
                        repaint();
                        crearInicioJuego();
                    } else{
                        JOptionPane.showMessageDialog(null,"No se aceptan caracteres especiales\n Intenta ingresar " +
                                "solo letras");
                    }

                }else{JOptionPane.showMessageDialog(null,"Debes ingresar el nombre de usuario");}

            }
            if(e.getSource()==botonInstrucciones){
                labelInstrucciones= new JLabel();
                image= new ImageIcon(getClass().getResource("/myProject/recursos/instrucciones.png"));
                labelInstrucciones.setIcon(new ImageIcon(image.getImage().getScaledInstance(500,450,
                        Image.SCALE_SMOOTH)));
                JOptionPane.showMessageDialog(null,labelInstrucciones,null,JOptionPane.PLAIN_MESSAGE);

            }
            if (e.getSource()== botonIniciar){
                model.mostrarUsuarios();

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
