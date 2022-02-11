package myProject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version @version v.1.0.0 date: 02/02/2022
 */
public class GUI extends JFrame {

    private Header headerProject;
    private Model model;
    private Escucha escucha;
    //Información en el botón de ayuda, en el panel de inicio: cuando se pide el user
    private static final String INFO1 = "Con el nombre de usuario podremos guardar tus avances\n Ingresa sólo " +
            "caracteres en minúsculas, evita el uso de la Ñ y/o espacios en blanco";
    //Información en el botón de ayuda, en el panel del juego.
    private static final String INFO2 = " Puedes salir en cualquier momento.\n"
            + "Sin embargo, si la partida no ha terminado la próxima vez que ingreses se iniciará la misma. ";
    private JPanel panelInicio, panelGame, panelBotones, panelPalabras, panelMensaje;
    private JTextField entradaUsuario;
    private JTextArea intro;
    private JButton botonOK, botonHelp, botonExit, botonIniciar, botonInstrucciones, botonSI, botonNO, botonContinuar;
    private JLabel labelUsername, labelInstrucciones, labelNivel, labelTiempo, labelPalabra;
    private ImageIcon image;
    private boolean opcionHelp;
    private String nombreJugador;
    private Timer timer;
    private GridBagConstraints constraints, layoutPanelGame; //componente del layout del JFrame y del panelGame

    /**
     * Constructor of GUI class
     */
    public GUI() {
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
        constraints = new GridBagConstraints();//GridBagConstraints constraints= new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        model = new Model();
        //Set up JComponents
        opcionHelp = false; //Se usa para saber qué mensaje en el botón de ayuda se debe mostrar
        //Encabezado del frame
        headerProject = new Header("I KNOW THAT WORD", new Color(135, 7, 122));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);
        //Creación de botones
        botonHelp = new JButton();
        botonHelp.addActionListener(escucha);
        botonHelp.setPreferredSize(new Dimension(80, 50));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/help1.png")));
        botonHelp.setIcon(new ImageIcon(image.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));
        botonHelp.setBorderPainted(false);
        botonHelp.setContentAreaFilled(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(botonHelp, constraints);

        botonExit = new JButton();
        botonExit.addActionListener(escucha);
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/close1.png")));
        botonExit.setPreferredSize(new Dimension(80, 50));
        botonExit.setIcon(new ImageIcon(image.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));
        botonExit.setBorderPainted(false);
        botonExit.setContentAreaFilled(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(botonExit, constraints);
        //panel que contiene el label del usuario, la entrada de texto y el botón de confirmación
        panelInicio = new JPanel(new GridBagLayout()); //Set up JPanel Container's Layout
        panelInicio.setPreferredSize(new Dimension(900, 500));
        panelInicio.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelInicio, constraints);
        componentesDelPanelInicio();
        revalidate();
        repaint();
    }

    /**
     * Este método crea y agrega al panel de inicio los componentes:
     * Jlabel labelUsername; Etiqueta para indicar lo que se desea que ingrese el usuario en la caja de texto
     * JtextField entradaUsuario; Componente para la entrada del texto
     * Jbutton botonOk; Botón de confirmación luego de ingresar el nombre de usuario
     */
    public void componentesDelPanelInicio() {

        GridBagConstraints layoutPanelInicio = new GridBagConstraints(); //Componente del layout
        //etiqueta
        labelUsername = new JLabel();
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/username.png")));
        labelUsername.setIcon(new ImageIcon(image.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        layoutPanelInicio.gridx = 0;
        layoutPanelInicio.gridy = 0;
        layoutPanelInicio.gridwidth = 2;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.CENTER;
        panelInicio.add(labelUsername, layoutPanelInicio);
        //Cajón de entrada del texto
        entradaUsuario = new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250, 40));
        entradaUsuario.setFont(new Font("Arial ", Font.PLAIN, 30));
        layoutPanelInicio.gridx = 0;
        layoutPanelInicio.gridy = 1;
        layoutPanelInicio.gridwidth = 1;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.LINE_END;
        panelInicio.add(entradaUsuario, layoutPanelInicio);
        //Boton de confirmación
        botonOK = new JButton();
        botonOK.addActionListener(escucha);
        botonOK.setPreferredSize(new Dimension(57, 57));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/okB.png")));
        botonOK.setIcon(new ImageIcon(image.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
        botonOK.setBorderPainted(false);
        botonOK.setContentAreaFilled(false);
        layoutPanelInicio.gridx = 1;
        layoutPanelInicio.gridy = 1;
        layoutPanelInicio.gridwidth = 1;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.LINE_START;
        panelInicio.add(botonOK, layoutPanelInicio);
        revalidate();
        repaint();
    }

    /**
     * Este método retorna un objeto de tipo Icon.
     * Es usado para el icono del mensaje emergente del botón de ayuda
     * @param reference  ubicación de la imagen
     * @param width     ancho  del icono
     * @param height    alto del icono
     */
    public Icon iconoMessage(String reference, int width, int height) {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(reference)));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return image;
    }

    /**
     * Después de ingresar el usuario, crea los componentes:
     * Jpanel panelGame: en él se lleva acabo el juego
     * JtextArea intro: mensaje introductorio
     * Invoca el método para agregar los botones de instrucciones y play
     */
    public void crearPanelGame() {
        panelGame = new Canvas(2);//Crea el panel con la imagen
        panelGame.setLayout(new GridBagLayout());//Set up JPanel Container's Layout
        layoutPanelGame = new GridBagConstraints(); //Componente del layout del panelGame
        panelGame.setPreferredSize(new Dimension(700, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelGame, constraints);

        // crearComponentesPanelGame(nombreJugador);
        intro = new JTextArea("   ¡HOLA " + nombreJugador.toUpperCase() + "!\n" +
                "   Estas en el nivel " + model.getNivelActual() + "\n   Presiona PLAY para iniciar");
        intro.setEditable(false);
        intro.setLineWrap(true);
        intro.setWrapStyleWord(true);
        intro.setBackground(new Color(186,186,252,130));
        intro.setOpaque(true);
        intro.setPreferredSize(new Dimension(400, 150));
        intro.setFont(new Font("Impact", Font.PLAIN, 28));
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        panelGame.add(intro, layoutPanelGame);
        crearPanelBotonesInicio();
        revalidate();
        repaint();
    }

    /**
     * Este método crea el panel en que se ubican los botones del juego y los componentes:
     * Botón de Instrucciones
     * Botón de Iniciar Partida
     */
    public void crearPanelBotonesInicio() {

        panelBotones = new JPanel();
        panelBotones.setPreferredSize(new Dimension(900, 100));
        panelBotones.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelBotones, constraints);

        botonInstrucciones = new JButton();
        botonInstrucciones.addActionListener(escucha);
        botonInstrucciones.setPreferredSize(new Dimension(200, 65));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/instruB.png")));
        botonInstrucciones.setIcon(new ImageIcon(image.getImage().getScaledInstance(200, 65, Image.SCALE_SMOOTH)));
        botonInstrucciones.setBorderPainted(false);
        botonInstrucciones.setContentAreaFilled(false);
        panelBotones.add(botonInstrucciones);

        botonIniciar = new JButton();
        botonIniciar.addActionListener(escucha);
        botonIniciar.setPreferredSize(new Dimension(200, 65));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/playB.png")));
        botonIniciar.setIcon(new ImageIcon(image.getImage().getScaledInstance(200, 65, Image.SCALE_SMOOTH)));
        botonIniciar.setBorderPainted(false);
        botonIniciar.setContentAreaFilled(false);
        panelBotones.add(botonIniciar);
    }

    /**
     * Crea los componentes para iniciar la partida:
     * etiqueta del tiempo
     * etiqueta del nivel
     * etiqueta de las palabras que se deben memorizar
     */
    public void crearComponentesPanelGame() {

        labelNivel = new JLabel("NIVEL: " + Integer.toString(model.getNivelActual()));
        labelNivel.setFont(new Font("Impact", Font.PLAIN, 24));
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_START;
        panelGame.add(labelNivel, layoutPanelGame);

        labelTiempo = new JLabel("00:00");
        labelTiempo.setFont(new Font("Impact", Font.PLAIN, 24));
        layoutPanelGame.gridx = 1;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_END;
        panelGame.add(labelTiempo, layoutPanelGame);

        panelPalabras = new JPanel(new GridBagLayout());
        GridBagConstraints layoutPanelPalabras = new GridBagConstraints();
        panelPalabras.setPreferredSize(new Dimension(690, 350));
        panelPalabras.setOpaque(false);
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 2;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        panelGame.add(panelPalabras, layoutPanelGame);

        labelPalabra = new JLabel();
        labelPalabra.setFont(new Font("Impact", Font.PLAIN, 50));
        layoutPanelPalabras.gridx = 0;
        layoutPanelPalabras.gridy = 0;
        layoutPanelPalabras.gridwidth= 1;
        layoutPanelPalabras.fill = GridBagConstraints.NONE;
        layoutPanelPalabras.anchor = GridBagConstraints.CENTER;
        panelPalabras.add(labelPalabra, layoutPanelPalabras);
        timer = new Timer(1000, escucha);
        revalidate();
        repaint();
    }

    /**
     * Genera la opción de continuar a la siguiente fase del juego, crea:
     * Jlabel  mensaje informativo
     * Jbutton continuar: intentar completar los aciertos requeridos
     */
    public void inicioFase2(){
        intro.setText("\n               ¡Es hora de la verdad! \n   Demuestra cuánto has logrado\n   " +
                "memorizar ");
        intro.setBackground(new Color(0,0,0,130));
        intro.setPreferredSize(new Dimension(400, 180));
        //finFase.setFont(new Font("Impact",Font.PLAIN,28));
        intro.setForeground(Color.WHITE);
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        panelGame.add(intro,layoutPanelGame);

        botonContinuar= new JButton();
        botonContinuar.addActionListener(escucha);
        botonContinuar.setPreferredSize(new Dimension(200, 65));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/continuar.png")));
        botonContinuar.setIcon(new ImageIcon(image.getImage().getScaledInstance(200, 65, Image.SCALE_SMOOTH)));
        botonContinuar.setBorderPainted(false);
        botonContinuar.setContentAreaFilled(false);
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 1;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.LINE_END;
        panelGame.add(botonContinuar,layoutPanelGame);
        revalidate();
        repaint();

    }

    /**
     * Crea los componentes necesarios para intentar completar los aciertos requeridos:
     * Jbutton opcion SI
     * Jbutton opcion NO
     */
    public void crearComponentesFase2() {
        JPanel panelOpciones= new JPanel();
        panelOpciones.setPreferredSize(new Dimension(690,90));
        panelOpciones.setOpaque(false);
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 2;
        layoutPanelGame.gridwidth = 2;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        panelGame.add(panelOpciones,layoutPanelGame);

        botonSI = new JButton();
        botonSI.addActionListener(escucha);
        botonSI.setPreferredSize(new Dimension(85, 85));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/si.png")));
        botonSI.setIcon(new ImageIcon(image.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
        botonSI.setBorderPainted(false);
        botonSI.setContentAreaFilled(false);
        panelOpciones.add(botonSI);

        botonNO = new JButton();
        botonNO.addActionListener(escucha);
        botonNO.setPreferredSize(new Dimension(85, 85));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/no.png")));
        botonNO.setIcon(new ImageIcon(image.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
        botonNO.setBorderPainted(false);
        botonNO.setContentAreaFilled(false);
        panelOpciones.add(botonNO);
        revalidate();
        repaint();
    }


    /**
     *
     */
    public void continuarNivel(){

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is executed by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        private int counter, fase;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                labelTiempo.setText("00:0" + counter);
                counter++;

                if(counter>5 && fase==1){
                    labelPalabra.setText(model.getPalabrasMemorizar());
                    counter=1;
                }
                if(labelPalabra.getText()== "" && fase ==1 ){
                    fase=2;
                    timer.stop();
                    panelGame.remove(labelNivel);
                    panelGame.remove(labelTiempo);
                    panelGame.remove(panelPalabras);
                    revalidate();
                    repaint();
                    inicioFase2();
                }
                if(counter>7 && fase == 2){
                    labelPalabra.setText(model.getPalabrasAleatorias());
                    counter=1;
                }
                if (labelPalabra.getText()==""&& fase==2 ){
                    continuarNivel();

                }


            }
            if (e.getSource() == botonExit) {
                //model.guardarRegistro();
                System.exit(0);
            }
            if (e.getSource() == botonHelp) {
                if (!opcionHelp) {
                    JOptionPane.showMessageDialog(null, INFO1, "USERNAME", JOptionPane.PLAIN_MESSAGE, iconoMessage(
                            "/myProject/recursos/imageUser.png", 50, 50));
                } else {
                    JOptionPane.showMessageDialog(null, INFO2, null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource() == botonOK) {
                //confirmarNivel();
                if (!entradaUsuario.getText().isEmpty()) {
                    nombreJugador = entradaUsuario.getText();
                    //validar que no tenga caracteres especiales
                    if (model.validarEntradaTexto(nombreJugador)) {
                        opcionHelp = true;
                        remove(panelInicio);
                        crearPanelGame();
                        revalidate();
                        repaint();

                        //busca el usuario y determina su nivel
                        model.buscarElUsuario(nombreJugador);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se aceptan caracteres especiales");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes ingresar el nombre de usuario", "Username is required"
                            , JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == botonInstrucciones) {
                labelInstrucciones = new JLabel();
                image = new ImageIcon(getClass().getResource("/myProject/recursos/instrucciones.png"));
                labelInstrucciones.setIcon(new ImageIcon(image.getImage().getScaledInstance(600, 480,
                        Image.SCALE_SMOOTH)));
                JOptionPane.showMessageDialog(null, labelInstrucciones, null, JOptionPane.PLAIN_MESSAGE);

            }
            if (e.getSource() == botonIniciar) {
                panelGame.remove(intro);
                crearComponentesPanelGame();
                labelPalabra.setText(model.getPalabrasMemorizar());
                model.jugar();
                botonIniciar.setVisible(false);
                fase=1;
                counter = 1;
                timer.start();

            }
            if (e.getSource() == botonSI) {
                model.validarPalabraCorrecta(labelPalabra.getText());
            }
            if (e.getSource() == botonNO) {
                model.validarPalabraCorrecta(labelPalabra.getText());
            }
            if (e.getSource() == botonContinuar) {
                panelGame.remove(intro);
                panelGame.remove(botonContinuar);
                revalidate();
                repaint();
                crearComponentesPanelGame();
                panelPalabras.setPreferredSize(new Dimension(690,260));
                crearComponentesFase2();
                labelPalabra.setText(model.getPalabrasAleatorias());
                timer.start();
            }
        }
    }
}

