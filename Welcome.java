import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners

public class Welcome {
    private final int WIDTH = 1000, LENGTH = 1000;
    private Style style = new Style();
    private JFrame mainScreen = style.frame("BattleShip", WIDTH, LENGTH);

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Button/Labels Instantiation
    private JLabel welcome = style.label("Welcome to Battleship by Ardent");
    private JButton newGame = style.button("New Game");

    //Panel
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private JPanel welcomePanel = new JPanel(new GridBagLayout());
    private JPanel newGamePanel = new JPanel(new GridBagLayout());

    //Box Layout
    private BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);

    //Constructor Instantiates the Frame
    public Welcome(){
        setScreen();
        mainScreen.setVisible(true);
        String filePath = "C:\\Users\\yuntian chen\\IdeaProjects\\Ardent-Christopher Chen\\Music\\Heroic Feat (Main).wav";
        musicStuff musicObject = new musicStuff();
        musicObject.playMusic((filePath));
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
        mainPanel.setLayout(layout);
        mainPanel.add(welcomePanel);
        mainPanel.add(newGamePanel);

        welcomePanel.add(welcome);
        newGamePanel.add(newGame);

        mainScreen.add(mainPanel);
        mainScreen.setJMenuBar(mainBar);

        //Action Listeners
        exitItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        newGame.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Game();
                        mainScreen.dispose();
                    }
                }
        );
    }
}
