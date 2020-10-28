import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import javax.swing.border.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.File;
import java.io.IOException;


public class Welcome {
    private final int WIDTH = 500, LENGTH = 500;
    private Style style = new Style();
    private JFrame mainScreen = style.frame("BattleShip", WIDTH, LENGTH);
    private ImageIcon image;
    private JLabel label;

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Button/Labels Instantiation
    Icon newGameImage = new ImageIcon(getClass().getResource("newgame.png"));
    Icon settingsImage = new ImageIcon(getClass().getResource("settings.png"));
    Icon rulesImage = new ImageIcon(getClass().getResource("rules.png"));
    Icon exitImage = new ImageIcon(getClass().getResource("exit.png"));
    Icon newGameRollover = new ImageIcon(getClass().getResource("newgamerollover.png"));
    Icon settingsRollover = new ImageIcon(getClass().getResource("settingsrollover.png"));
    Icon rulesRollover = new ImageIcon(getClass().getResource("rulesrollover.png"));
    Icon exitRollover = new ImageIcon(getClass().getResource("exitrollover.png"));
    private JLabel welcome = style.label("Welcome to Battleship by Ardent");
    private JButton newGame = new JButton(newGameImage);
    private JButton settings = new JButton(settingsImage);
    private JButton rules = new JButton(rulesImage);
    private JButton exitButton = new JButton(exitImage);

    //Panel
    private JPanel mainPanel = new JPanel();
    private JPanel welcomePanel = new JPanel(new GridBagLayout());
    private JPanel newGamePanel = new JPanel(new GridBagLayout());
    private JPanel settingsPanel = new JPanel(new GridBagLayout());
    private JPanel rulesPanel = new JPanel(new GridBagLayout());
    private JPanel exitPanel = new JPanel(new GridBagLayout());

    //Borders
    Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED); //Raised Border

    //Box Layout
    private BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
    private BoxLayout newGamePaneLayout = new BoxLayout(newGamePanel, BoxLayout.PAGE_AXIS);


    //Constructor Instantiates the Frame
    public Welcome(){
        setScreen();
        mainScreen.setVisible(true);
    }

    public void setScreen() {
        image = new ImageIcon(getClass().getResource("ship.jpg"));
        label = new JLabel(image);
        label.setSize(500,500);
        mainScreen.add(label);
        mainScreen.setLocationRelativeTo(null);
        mainScreen.getContentPane().setBackground(Color.black);
        mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Menu Instantiation
        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        //Frame Organization
        welcome.setFont(style.headlineFont()); //Title of page

        //Set Panel with Buttons in Box Layout
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBorder(raisedBorder);
        mainPanel.setBackground(Color.DARK_GRAY);
        //mainPanel.setPreferredSize(new Dimension(350,400));
        mainPanel.setBounds(80, 10, 350, 420);
        mainPanel.add(welcomePanel, BorderLayout.PAGE_START);
        mainPanel.add(newGamePanel, BorderLayout.CENTER);
        mainPanel.add(settingsPanel, BorderLayout.CENTER);
        mainPanel.add(rulesPanel, BorderLayout.CENTER);
        mainPanel.add(exitPanel, BorderLayout.CENTER);

        //Welcome Panel
        welcomePanel.add(welcome); //Adds title to the page
        welcomePanel.setBackground(Color.DARK_GRAY);

        //New Game Panel
        newGamePanel.add(newGame); //Adds new game button
        newGame.setRolloverIcon(newGameRollover);
        newGamePanel.setBackground(Color.DARK_GRAY);

        //Settings Panel
        settingsPanel.add(settings); //Adds settings button
        settings.setRolloverIcon(settingsRollover);
        settingsPanel.setBackground(Color.DARK_GRAY);

        //Rules panel
        rulesPanel.add(rules);
        rules.setRolloverIcon(rulesRollover);
        rulesPanel.setBackground(Color.DARK_GRAY);

        //Exit
        exitPanel.add(exitButton);
        exitButton.setRolloverIcon(exitRollover);
        exitPanel.setBackground(Color.DARK_GRAY);

        //Set main panel and menu bar to the JFrame
        label.add(mainPanel);
        mainScreen.setJMenuBar(mainBar);

        //Border
        //mainPanel.setBorder(raisedBorder);
        //welcomePanel.setBorder(raisedBorder);
        //newGamePanel.setBorder(raisedBorder);

        /*Image
        try {
            mainScreen.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("/Users/brandonwion/Downloads/20170612-TWINCH-uss-north-carolina.jpg")))));
        } catch(IOException e){
            e.printStackTrace();
        }*/

        //Action Listeners
        exitItem.addActionListener( //Exit from the menu
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        newGame.addActionListener( //New Game button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Game();
                        mainScreen.dispose();
                    }
                }
        );

        settings.addActionListener( //Settings button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Settings();
                    }
                }
        );

        exitButton.addActionListener( //Exit button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}

