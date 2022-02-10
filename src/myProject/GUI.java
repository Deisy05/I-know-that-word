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

    private JPanel panelInicio, panelGame,panelBotones,panelPalabras;
    private JTextField entradaUsuario;
    private JTextArea intro;
    private JButton botonOK, botonHelp, botonExit, botonIniciar, botonInstrucciones, botonSi, botonNo, botonContinuar;
    private JLabel labelUsername, labelInstrucciones, labelNivel, labelTiempo, labelPalabra;
    private ImageIcon  image;
    private boolean opcionHelp;
    private String nombreJugador;

    private UIManager ui;
    private GridBagConstraints constraints, layoutPanelGame; //componente del layout del JFrame y del panelGame


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
        revalidate();
        repaint();

    }

    /**
     * Este método crea los siguientes componentes y los agrega al panel de inicio:
     * labelUsername: Etiqueta para indicar lo que se desea que ingrese el usuario en la caja de texto
     * entradaUsuario: Componente para la entrada del texto
     * botonOk: Botón de confirmación luego de ingresar el nombre de usuario
     */

    public void componentesDelPanelInicio(){

        GridBagConstraints layoutPanelInicio = new GridBagConstraints(); //Componente del layout
        //etiqueta
        labelUsername= new JLabel();
        image=  new ImageIcon(getClass().getResource("/myProject/recursos/username.png"));
        labelUsername.setIcon(new ImageIcon(image.getImage().getScaledInstance(200,50, Image.SCALE_SMOOTH)));
        layoutPanelInicio .gridx=0;
        layoutPanelInicio .gridy=0;
        layoutPanelInicio .gridwidth=2;
        layoutPanelInicio .fill=GridBagConstraints.NONE;
        layoutPanelInicio .anchor=GridBagConstraints.CENTER;
        panelInicio.add(labelUsername,layoutPanelInicio );
        //Cajón de entrada del texto
        entradaUsuario= new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250,40));
        entradaUsuario.setFont(new Font("Arial ",Font.PLAIN,30));
        layoutPanelInicio .gridx=0;
        layoutPanelInicio .gridy=1;
        layoutPanelInicio .gridwidth=1;
        layoutPanelInicio .fill=GridBagConstraints.NONE;
        layoutPanelInicio .anchor=GridBagConstraints.LINE_END;
        panelInicio.add(entradaUsuario, layoutPanelInicio );
        //Boton de confirmación
        botonOK= new JButton();
        botonOK.addMouseListener(escucha);
        botonOK.setPreferredSize(new Dimension(57,57));
        image = new ImageIcon(getClass().getResource("/myProject/recursos/okB.png"));
        botonOK.setIcon(new ImageIcon(image.getImage().getScaledInstance(57,57, Image.SCALE_SMOOTH)));
        botonOK.setBorderPainted(false);
        botonOK.setContentAreaFilled(false);
        layoutPanelInicio .gridx=1;
        layoutPanelInicio .gridy=1;
        layoutPanelInicio .gridwidth=1;
        layoutPanelInicio .fill=GridBagConstraints.NONE;
        layoutPanelInicio .anchor=GridBagConstraints.LINE_START;
        panelInicio.add(botonOK, layoutPanelInicio );
        revalidate();
        repaint();

    }

    /**
     * Esta clase crea el ícono del mensaje emergente del botón de ayuda
     * @param reference es la ubicación de la imagen
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
    public void crearPanelGame(){
        panelGame= new Canvas(2);//Crea el panel con la imagen
        panelGame.setLayout(new GridBagLayout());//Set up JPanel Container's Layout
        layoutPanelGame= new GridBagConstraints(); //Componente del layout del panelGame
        panelGame.setPreferredSize(new Dimension(700,400));
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=2;
        constraints.fill= GridBagConstraints.NONE;
        constraints.anchor= GridBagConstraints.CENTER;
        this.add(panelGame,constraints);

        intro= new JTextArea(" ¡HOLA "+nombreJugador.toUpperCase()+"!\n Estas en el nivel "+ model.nivelDelJugador(nombreJugador)+
                "\n Presiona 'PLAY' para iniciar la partida");
        intro.setEditable(false);
        intro.setOpaque(false);
        intro.setLineWrap(true);
        intro.setWrapStyleWord(true);
        intro.setPreferredSize(new Dimension(400,150));
        intro.setFont(new Font("Impact", Font.PLAIN, 28));
        intro.setForeground(Color.BLACK);
        layoutPanelGame.gridx=0;
        layoutPanelGame.gridy=0;
        layoutPanelGame.gridwidth=1;
        layoutPanelGame.fill= GridBagConstraints.NONE;
        layoutPanelGame.anchor= GridBagConstraints.CENTER;
        panelGame.add(intro,layoutPanelGame);

        crearPanelBotonesInicio();
        revalidate();
        repaint();

    }

    /**
     * Este método crea el panel en que se ubican los botones del juego.
     * Además, crea los componentes:
     * Botón de Instrucciones
     * Botón de Iniciar Partida
     */

    public void crearPanelBotonesInicio (){

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
        revalidate();
        repaint();


    }

    public void crearComponentesPanelGame(String nombreU){
       panelGame.remove(intro);

       labelNivel= new JLabel("NIVEL: "+Integer.toString(model.nivelDelJugador(nombreJugador)));
       labelNivel.setFont(new Font("Impact",Font.PLAIN,22));
       labelNivel.setBackground(new Color(186,186,252,110));
       labelNivel.setOpaque(true);
       layoutPanelGame.gridx=0;
       layoutPanelGame.gridy=0;
       layoutPanelGame.gridwidth=1;
       layoutPanelGame.fill= GridBagConstraints.NONE;
       layoutPanelGame.anchor= GridBagConstraints.LINE_START;
       panelGame.add(labelNivel,layoutPanelGame);

       labelTiempo= new JLabel("00:00:00");
       labelTiempo.setFont(new Font("Impact",Font.PLAIN,22));
       labelTiempo.setBackground(new Color(186,186,252,110));
       labelTiempo.setOpaque(true);
       layoutPanelGame.gridx=1;
       layoutPanelGame.gridy=0;
       layoutPanelGame.gridwidth=1;
       layoutPanelGame.fill= GridBagConstraints.NONE;
       layoutPanelGame.anchor= GridBagConstraints.LINE_END;
       panelGame.add(labelTiempo,layoutPanelGame);

       panelPalabras= new JPanel(new GridBagLayout());
       GridBagConstraints layoutPanelPalabras= new GridBagConstraints();
       panelPalabras.setPreferredSize(new Dimension(690,300));
       panelPalabras.setOpaque(false);
       panelPalabras.setBorder(BorderFactory.createTitledBorder("Palabras"));
       layoutPanelGame.gridx=0;
       layoutPanelGame.gridy=1;
       layoutPanelGame.gridwidth=2;
       layoutPanelGame.fill= GridBagConstraints.NONE;
       layoutPanelGame.anchor= GridBagConstraints.CENTER;
       panelGame.add(panelPalabras,layoutPanelGame);

       labelPalabra= new JLabel("PALABRA MEMORY XD");
       labelPalabra.setPreferredSize(new Dimension(400,60));
       labelPalabra.setFont(new Font("Impact",Font.PLAIN,50));
       layoutPanelPalabras.gridx=0;
       layoutPanelPalabras.gridy=0;
       layoutPanelPalabras.fill= GridBagConstraints.NONE;
       layoutPanelPalabras.anchor= GridBagConstraints.CENTER;
       labelPalabra.setOpaque(true);
       labelPalabra.setBorder(BorderFactory.createTitledBorder("etiqueta"));
       panelPalabras.add(labelPalabra,layoutPanelPalabras);

       revalidate();
       repaint();
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
                        model.buscarElUsuario(nombreJugador); //busca al jugador y si no está, lo registra

                        opcionHelp=true;
                        remove(panelInicio);
                        revalidate();
                        repaint();
                        crearPanelGame();

                    } else{
                        JOptionPane.showMessageDialog(null,"No se aceptan caracteres especiales\n Intenta ingresar " +
                                "solo letras minúsculas");
                    }

                }else{JOptionPane.showMessageDialog(null,"Debes ingresar el nombre de usuario");}

            }
            if(e.getSource()==botonInstrucciones){
                labelInstrucciones= new JLabel();
                image= new ImageIcon(getClass().getResource("/myProject/recursos/instrucciones.png"));
                labelInstrucciones.setIcon(new ImageIcon(image.getImage().getScaledInstance(600,480,
                        Image.SCALE_SMOOTH)));
                JOptionPane.showMessageDialog(null,labelInstrucciones,null,JOptionPane.PLAIN_MESSAGE);

            }
            if (e.getSource()== botonIniciar){
                model.mostrarUsuarios();
                crearComponentesPanelGame(nombreJugador);
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
