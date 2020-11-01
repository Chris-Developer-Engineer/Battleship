import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.awt.Color; //Color


public class Game {
    private final int WIDTH = 1250, LENGTH = 700;
    private Style style = new Style();
    private JFrame gameScreen = style.frame("Game Play", WIDTH, LENGTH);
    private ImageIcon background;
    private JLabel back;

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Images Instantiation....to update image replace file name
    Icon mainMenuImage = new ImageIcon(getClass().getResource("menu.png"));
    Icon settingsImage = new ImageIcon(getClass().getResource("settingsGame.png"));

    //Buttons
    private JButton mainMenu = new JButton(mainMenuImage);
    private JButton settings = new JButton(settingsImage);

    //Labels
    private JLabel winLossLabel = new JLabel("Win/Loss Statistics");
    private JLabel wins = new JLabel("Wins: " + WinLoss.win);
    private JLabel loss = new JLabel("Losses: " + WinLoss.loss);
    private JLabel rank = new JLabel(WinLoss.ranking());

    //Panel
    private JPanel centerPanel = new JPanel(); //Game boards will be placed here
    private JPanel winLossPanel = new JPanel(); //Game boards will be placed here

    //Borders
    Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED); //Raised Border

    public Game() {
        setScreen();
        gameScreen.setVisible(true);
    }

    public void setScreen(){
        //Background set
        background = new ImageIcon(getClass().getResource("water.jpg"));
        back = new JLabel(background);
        back.setSize(1250,700);

        gameScreen.add(back);
        gameScreen.setLocationRelativeTo(null);
        gameScreen.getContentPane().setBackground(Color.black);
        gameScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Menu Instantiation
        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        //Set Center Panel
        centerPanel.setBorder(raisedBorder);
        centerPanel.setBackground(new Color(0,0,0, 125)); //a is the transparency value
        centerPanel.setBounds(325,10,600,610);

        //WinLoss Panel
        winLossPanel.setBorder(raisedBorder);
        winLossPanel.setLayout(new BoxLayout(winLossPanel, BoxLayout.Y_AXIS));
        winLossPanel.setBackground(new Color(0,0,0,125));
        winLossPanel.setBounds(935,400,305,220);

        /*winLossLabel.setFont(style.headlineFont());
        winLossLabel.setForeground(Color.WHITE);

        wins.setFont(style.bodyFont());
        wins.setForeground(Color.WHITE);

        loss.setFont(style.bodyFont());
        loss.setForeground((Color.WHITE));

        rank.setFont(style.bodyFont());
        rank.setForeground(Color.WHITE);

        winLossPanel.add(winLossLabel);
        winLossPanel.add(wins);
        winLossPanel.add(loss);
        winLossPanel.add(rank);*/

        //Button Instantiation....if image is updated adjust bounds
        mainMenu.setBounds(70,10,170,80);
        settings.setBounds(70,100,170,80);

        //Set Screen
        back.add(centerPanel); //Panel for player vs AI game boards
        back.add(winLossPanel); //Panel to display win loss statistics
        back.add(mainMenu);
        back.add(settings);
        gameScreen.setJMenuBar(mainBar);

        //Action Listeners
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

        settings.addActionListener( //Settings button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Settings();
                    }
                }
        );
    }
}
