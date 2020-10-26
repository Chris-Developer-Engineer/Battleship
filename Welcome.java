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

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Button/Labels Instantiation
    private JLabel welcome = style.label("Welcome to Battleship by Ardent");
    private JButton newGame = style.button("New Game");
    private JButton settings = style.button("Settings");
    private JButton rules = style.button("Rules");
    private JButton exitButton = style.button("Exit");

    //Panel
    private JPanel mainPanel = new JPanel(new GridBagLayout());
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
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Menu Instantiation
        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        //Frame Organization
        welcome.setFont(style.headlineFont());

        //Set Panel with Buttons in Box Layout
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        //mainPanel.setPreferredSize(new Dimension(490,400));
        mainPanel.setLayout(layout);
        mainPanel.add(welcomePanel, BorderLayout.PAGE_START);
        mainPanel.add(newGamePanel, BorderLayout.CENTER);
        mainPanel.add(settingsPanel, BorderLayout.CENTER);
        mainPanel.add(rulesPanel, BorderLayout.CENTER);
        mainPanel.add(exitPanel, BorderLayout.CENTER);

        //Welcome Panel
        welcomePanel.add(welcome); //Adds title to the page

        //New Game Panel
        newGamePanel.add(newGame); //Adds new game button

        //Settings Panel
        settingsPanel.add(settings); //Adds settings button

        //Rules panel
        rulesPanel.add(rules);

        //Exit
        exitPanel.add(exitButton);

        //Set main panel and menu bar to the JFrame
        mainScreen.add(mainPanel);
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
    }
}

