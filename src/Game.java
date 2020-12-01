import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.Color; //Color



public class Game {

    int gameSize;
    private static final int WIDTH = 1250, LENGTH = 700;
    private static final Style style = new Style();
    public static final JFrame gameScreen = style.frame("Game Play", WIDTH, LENGTH);
    private ImageIcon background;
    private JLabel back;

    //Menu Instantiation
    private final JMenuBar mainBar = new JMenuBar();
    private final JMenu fileMenu = style.menu("File");
    private final JMenuItem exitItem = style.menuItem("Exit");

    //Images Instantiation....to update image replace file name
    Icon mainMenuImage = new ImageIcon(getClass().getResource("MMenu1.png"));
    Icon settingsImage = new ImageIcon(getClass().getResource("settings1.png"));

    Icon mainMenuImageRollover = new ImageIcon(getClass().getResource("MMenu2.png"));
    Icon settingsImageRollover = new ImageIcon(getClass().getResource("settings2.png"));

    //Buttons
    private final JButton mainMenu = new JButton(mainMenuImage);
    private final JButton settings = new JButton(settingsImage);

    //Labels
    private final JLabel winLossLabel = new JLabel("Win/Loss Statistics");
    private final JLabel wins = new JLabel("Wins: " + WinLoss.win);
    private final JLabel loss = new JLabel("Losses: " + WinLoss.loss);
    private final JLabel rank = new JLabel(WinLoss.ranking());

    //Panel
    private final JPanel centerPanel = new JPanel(); //Game boards will be placed here
    private final JPanel centerPanel1 = new JPanel(); //Game boards will be placed here
    private final JPanel winLossPanel = new JPanel(); //Game boards will be placed here
    private final JPanel shipHoldingPanel = new JPanel(); //Holds ships on bottom left of window
    private final JPanel opponentShips = new JPanel(); //Opponent's ship tracking panel
    private final JPanel playerShips = new JPanel(); //Player's ship tracking panel
    private final JPanel turnTracker = new JPanel(); //Displays turn tracking

    //Borders
    Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED); //Raised Border

    //Background Data
    TurnTracker tracker = new TurnTracker();
    BoardTracker userBoard = new BoardTracker();
    BoardTracker oppBoard = new BoardTracker();

    //sound effect;
    static Clip clip; // BGM;
    Clip clip2; //clip sound;

    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setScreen();
        gameScreen.setVisible(true);
        SoundEffect.page = 2;
        clip = new SoundEffect().playGameBGM();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setScreen(){
        //Background set
        ImageIcon background = new ImageIcon(getClass().getResource("gameBG.gif"));
        JLabel back = new JLabel(background);
        back.setSize(1450,813);

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

        //Rollover Icons
        mainMenu.setRolloverIcon(mainMenuImageRollover);
        settings.setRolloverIcon(settingsImageRollover);

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
        centerPanel.setBounds(325,10,600,295);
        centerPanel1.setBorder(raisedBorder);
        centerPanel1.setBackground(new Color(0,0,0, 125)); //a is the transparency value
        centerPanel1.setBounds(325,315,600,295);

        //if(gameBoardSize == 7) {
        HitOrMiss buttons[]=new HitOrMiss[49];
        centerPanel.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 49; i++) {
            buttons[i] = new HitOrMiss();
            centerPanel.add(buttons[i]);
        }

        centerPanel1.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 49; i++) {
            buttons[i] = new HitOrMiss();
            centerPanel1.add(buttons[i]);
        }
        //}
        /*
        if(gameBoardSize == 8) {
            HitOrMiss buttons[]=new HitOrMiss[64];
            centerPanel.setLayout(new GridLayout(8, 8));
            for (int i = 0; i < 64; i++) {
                buttons[i] = new HitOrMiss();
                centerPanel.add(buttons[i]);
            }

            centerPanel1.setLayout(new GridLayout(8, 8));
            for (int i = 0; i < 64; i++) {
                buttons[i] = new HitOrMiss();
                centerPanel1.add(buttons[i]);
            }
        }

        if(gameBoardSize == 9) {
            HitOrMiss buttons[]=new HitOrMiss[81];
            centerPanel.setLayout(new GridLayout(9, 9));
            for (int i = 0; i < 81; i++) {
                buttons[i] = new HitOrMiss();
                centerPanel.add(buttons[i]);
            }

            centerPanel1.setLayout(new GridLayout(9, 9));
            for (int i = 0; i < 81; i++) {
                buttons[i] = new HitOrMiss();
                centerPanel1.add(buttons[i]);
            }
        }
         */

        //Player's ship tracking panel (middle right)
        playerShips.setBorder(raisedBorder);
        playerShips.setBackground(new Color(0,0,0,125)); //a is the transparency value
        playerShips.setBounds(935,235,305,155);

        //Turn Tracking Panel (bottom right)
        turnTracker.setBorder(raisedBorder);
        turnTracker.setBackground(new Color(0,0,0,125)); //a is the transparency value
        turnTracker.setBounds(935,400,305,220);
        tracker.updatePanel(turnTracker, style);

        //Opponent's ship tracking panel (upper right)
        opponentShips.setBorder(raisedBorder);
        opponentShips.setBackground(new Color(0,0,0,125)); //a is the transparency value
        opponentShips.setBounds(935,70,305,155);

        //Set Screen
        back.add(centerPanel); //Panel for player vs AI game boards
        back.add(centerPanel1); //Panel for player vs AI game boards
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
                e -> System.exit(0)
        );

        mainMenu.addActionListener( //Main from menu bar
                e -> {
                    try {
                        new Welcome();
                        clip2 = new SoundEffect().playClickSound();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    gameScreen.dispose();
                    clip.stop();
                }
        );


        settings.addActionListener(e ->
                {
                    new Settings();
                    try {
                        clip2 = new SoundEffect().playClickSound();
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                }
        );

    }

}
