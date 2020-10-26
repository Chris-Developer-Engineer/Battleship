import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.io.IOException;
import java.awt.Color;

public class Game {
    private final int WIDTH = 1250, LENGTH = 700;
    private Style style = new Style();
    private JFrame gameScreen = style.frame("Game Play", WIDTH, LENGTH);

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Button/Labels Instantiation
    private JButton mainMenu = new JButton("Main Menu");

    //Panel
    private JPanel gamePanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel leftSubPanelTopPanel = new JPanel();
    private JPanel leftSubPanelBotPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel rightSubPanelTopPanel = new JPanel();
    private JPanel rightSubPanelBotPanel = new JPanel();

    //Borders
    Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED); //Raised Border
    Border loweredBorder = new EtchedBorder(EtchedBorder.LOWERED); //Lowered Border

    //Box Layout
    private BoxLayout leftLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout leftSubPanelTopLayout = new BoxLayout(leftSubPanelTopPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout leftSubPanelBotLayout = new BoxLayout(leftSubPanelBotPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout rightSubPanelTopLayout = new BoxLayout(rightSubPanelTopPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout rightSubPanelBotLayout = new BoxLayout(rightSubPanelBotPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout centerLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS); //Aligns contents vertically
    private BoxLayout rightLayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS); //Aligns contents vertically


    public Game() {
        setScreen();
        gameScreen.setVisible(true);
    }

    public void setScreen(){
        gameScreen.setLocationRelativeTo(null);
        gameScreen.setLayout(new FlowLayout());

        //Menu Instantiation
        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        //Add panels to page
        gamePanel.setPreferredSize(new Dimension(1240, 625));
        gamePanel.setBorder(loweredBorder);
        gamePanel.add(leftPanel, BorderLayout.LINE_START);
        gamePanel.add(centerPanel, BorderLayout.CENTER);
        gamePanel.add(rightPanel, BorderLayout.LINE_END);

        //Set Left Panel
        leftPanel.setLayout(leftLayout);
        leftPanel.setPreferredSize(new Dimension(250, 600));
        leftPanel.add(leftSubPanelTopPanel);
        leftSubPanelTopPanel.setMaximumSize(new Dimension(250, 300));
        leftSubPanelTopPanel.add(mainMenu);
        leftPanel.add(leftSubPanelBotPanel);
        leftSubPanelBotPanel.setMaximumSize(new Dimension(250, 300));

        //Set Center Panel
        //centerPanel.setLayout(centerLayout);
        centerPanel.setPreferredSize(new Dimension(600, 600));

        //Set Right Panel
        rightPanel.setLayout(rightLayout);
        rightPanel.setPreferredSize(new Dimension(250, 600));
        rightPanel.add(rightSubPanelTopPanel);
        rightSubPanelTopPanel.setMaximumSize(new Dimension(250, 300));
        rightPanel.add(rightSubPanelBotPanel);
        rightSubPanelBotPanel.setMaximumSize(new Dimension(250, 300));

        //Borders Set
        leftPanel.setBorder(BorderFactory.createTitledBorder("Left Panel"));
        leftSubPanelTopPanel.setBorder(raisedBorder);
        leftSubPanelBotPanel.setBorder(raisedBorder);
        rightSubPanelTopPanel.setBorder(raisedBorder);
        rightSubPanelBotPanel.setBorder(raisedBorder);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Center Panel"));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Right Panel"));

        //Set Panel
        gameScreen.setJMenuBar(mainBar);
        gameScreen.add(gamePanel);


        exitItem.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                }
        );

        mainMenu.addActionListener( //Main Menu button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Welcome();
                        gameScreen.dispose();
                    }
                }
        );
    }
}
