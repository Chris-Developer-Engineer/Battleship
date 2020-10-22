import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners

public class Game {
    private final int WIDTH = 500, LENGTH = 500;
    private Style style = new Style();
    private JFrame gameScreen = style.frame("Welcome", WIDTH, LENGTH);
    private TurnTracker tracker = new TurnTracker();

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Button/Labels Instantiation

    //Panel
    private JPanel gamePanel = new JPanel(new GridBagLayout());

    //Box Layout
    private BoxLayout layout = new BoxLayout(gamePanel, BoxLayout.Y_AXIS);

    public Game() {
        setScreen();
        gameScreen.setVisible(true);
    }

    public void setScreen(){
        gameScreen.setLocationRelativeTo(null);
        gameScreen.setLayout(new FlowLayout());

        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        gameScreen.setJMenuBar(mainBar);

        exitItem.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                }
        );
    }
}
