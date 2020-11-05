import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.awt.Color; //Color
import java.awt.event.*; //Popup


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
    final JPopupMenu popup = new JPopupMenu();

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
    private JPanel shipHoldingPanel = new JPanel(); //Holds ships on bottom left of window
    private JPanel opponentShips = new JPanel(); //Opponent's ship tracking panel
    private JPanel playerShips = new JPanel(); //Player's ship tracking panel
    private JPanel turnTracker = new JPanel(); //Displays turn tracking

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

        //Button Instantiation....if image is updated adjust bounds
        mainMenu.setBounds(70,10,170,80);
        settings.setBounds(70,100,170,80);

        //Win Loss Statistics
        winLossPanel.setBorder(raisedBorder);
        winLossPanel.setLayout(new BoxLayout(winLossPanel, BoxLayout.Y_AXIS));
        winLossPanel.setBackground(new Color(0,0,0,125)); //a is the transparency value
        winLossPanel.setBounds(10,190,305,100);

        winLossLabel.setFont(style.headlineFont());
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
        winLossPanel.add(rank);

        //Ship Holding Panel (bottom left of screen)
        shipHoldingPanel.setBorder(raisedBorder);
        shipHoldingPanel.setBackground(new Color(0,0,0,125)); //a is the transparency value
        shipHoldingPanel.setBounds(10,300,305,320);

        //Set Center Panel (Game boards)
        centerPanel.setBorder(raisedBorder);
        centerPanel.setBackground(new Color(0,0,0, 125)); //a is the transparency value
        centerPanel.setBounds(325,10,600,610);

        //Player's ship tracking panel (middle right)
        playerShips.setBorder(raisedBorder);
        playerShips.setBackground(new Color(0,0,0,125)); //a is the transparency value
        playerShips.setBounds(935,235,305,155);

        //Turn Tracking Panel (bottom right)
        turnTracker.setBorder(raisedBorder);
        turnTracker.setBackground(new Color(0,0,0,125)); //a is the transparency value
        turnTracker.setBounds(935,400,305,220);

        //Opponent's ship tracking panel (upper right)
        opponentShips.setBorder(raisedBorder);
        opponentShips.setBackground(new Color(0,0,0,125)); //a is the transparency value
        opponentShips.setBounds(935,70,305,155);

        //Set Screen
        back.add(centerPanel); //Panel for player vs AI game boards
        back.add(turnTracker); //Panel to display turn history
        back.add(shipHoldingPanel); //Panel to show available ships to place
        back.add(opponentShips); //Panel to show opponents ships
        back.add(playerShips); //Panel to show players ships
        back.add(winLossPanel); //Panel to display win loss statistics
        back.add(mainMenu);
        back.add(settings);
        gameScreen.setJMenuBar(mainBar);

        //Action Listeners
        exitItem.addActionListener( //Exit button
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                }
        );

        mainMenu.addActionListener( //Main from menu bar
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Welcome();
                        gameScreen.dispose();
                    }
                }
        );

        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Settings();
            }
        });
    }
}
