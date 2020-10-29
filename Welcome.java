import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners

public class Welcome {
    private final int WIDTH = 800, LENGTH = 500;
    private Style style = new Style();
    private JFrame mainScreen = style.frame("BattleShip", WIDTH, LENGTH);
    private ImageIcon background;
    private JLabel back;
    private ImageIcon battle;
    private JLabel battleship;

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Images Instantiation....to update image replace file name
    Icon newGameImage = new ImageIcon(getClass().getResource("newgame.png"));
    Icon settingsImage = new ImageIcon(getClass().getResource("settings.png"));
    Icon rulesImage = new ImageIcon(getClass().getResource("rules.png"));

    //Rollover images....to update image replace file name
    Icon newGameRollover = new ImageIcon(getClass().getResource("newgamerollover.png"));
    Icon settingsRollover = new ImageIcon(getClass().getResource("settingsrollover.png"));
    Icon rulesRollover = new ImageIcon(getClass().getResource("rulesrollover.png"));

    //Buttons
    private JButton newGame = new JButton(newGameImage);
    private JButton settings = new JButton(settingsImage);
    private JButton rules = new JButton(rulesImage);

    //Constructor Instantiates the Frame
    public Welcome(){
        setScreen();
        mainScreen.setVisible(true);
    }

    public void setScreen() {
        //Background set
        background = new ImageIcon(getClass().getResource("mainimage.jpg"));
        back = new JLabel(background);
        back.setSize(500,500);

        battle = new ImageIcon(getClass().getResource("battleship.png"));
        battleship = new JLabel(battle);
        battleship.setBounds(50, 10, 700,110);

        mainScreen.add(back);
        mainScreen.setLocationRelativeTo(null);
        mainScreen.getContentPane().setBackground(Color.black);
        mainScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Menu Instantiation
        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        //Button Instantiation....if image is updated adjust bounds
        newGame.setBounds(138,350,170,80);
        settings.setBounds(313, 350, 170, 80);
        rules.setBounds(487,350,170,80);

        //Rollovers for Buttons....this will NOT need to be changed if the image is updated
        newGame.setRolloverIcon(newGameRollover);
        settings.setRolloverIcon(settingsRollover);
        rules.setRolloverIcon(rulesRollover);

        //Set screen
        back.add(battleship);
        back.add(newGame);
        back.add(settings);
        back.add(rules);
        mainScreen.setJMenuBar(mainBar);

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

        rules.addActionListener( //Rules button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Settings();
                    }
                }
        );
    }
}