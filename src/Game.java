import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.swing.*; //GUI
import javax.swing.border.*;
import java.awt.*; //Font
import java.awt.Color; //Color
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Game {
    private GameBoard gameBoard = null;
    int gameBoardSize;

    public static int[][] gameBoardMultiArray7 = new int[7][7];
    public static int[][] gameBoardMultiArray8 = new int[8][8];
    public static int[][] gameBoardMultiArray9 = new int[9][9];

    boolean gameBoardFillSentinel = false;

    String[] gameBoardArray7 = {"A1","A2","A3","A4","A5","A6","A7","B1","B2","B3","B4","B5","B6"
            ,"B7","C1","C2","C3","C4","C5","C6","C7","D1","D2","D3"
            ,"D4","D5","D6","D7","E1","E2","E3","E4","E5","E6","E7"
            ,"F1","F2","F3","F4","F5","F6","F7","G1","G2","G3","G4","G5","G6"
            ,"G7"};

    String[] gameBoardArray8 = {"A1","A2","A3","A4","A5","A6","A7","A8","B1","B2","B3","B4","B5","B6"
            ,"B7","B8","C1","C2","C3","C4","C5","C6","C7","C8","D1","D2","D3"
            ,"D4","D5","D6","D7","D8","E1","E2","E3","E4","E5","E6","E7","E8"
            ,"F1","F2","F3","F4","F5","F6","F7","F8","G1","G2","G3","G4","G5","G6"
            ,"G7","G8","H1","H2","H3","H4","H5","H6","H7","H8"};

    String[] gameBoardArray9 = {"A1","A2","A3","A4","A5","A6","A7","A8","A9","B1","B2","B3","B4","B5","B6"
            ,"B7","B8","B9","C1","C2","C3","C4","C5","C6","C7","C8","C9","D1","D2","D3"
            ,"D4","D5","D6","D7","D8","D9","E1","E2","E3","E4","E5","E6","E7","E8","E9"
            ,"F1","F2","F3","F4","F5","F6","F7","F8","F9","G1","G2","G3","G4","G5","G6"
            ,"G7","G8","G9","H1","H2","H3","H4","H5","H6","H7","H8","H9","I1","I2","I3"
            ,"I4","I5","I6","I7","I8","I9"};

    static HitOrMiss[] buttons;
    static HitOrMiss[] buttons1;


    private static final int WIDTH = 1250, LENGTH = 700;
    private static final Style style = new Style();
    public static JFrame gameScreen = style.frame("Game Play", WIDTH, LENGTH);
    private ImageIcon submarine;
    private JLabel twoboat;
    private ImageIcon cruiser;
    private JLabel threeboat1;
    private ImageIcon battleship;
    private JLabel threeboat2;
    private ImageIcon warMachine;
    private JLabel fourboat;
    private ImageIcon carrier;
    private JLabel fiveboat;

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
    public static JPanel centerPanel; //Game boards will be placed here
    public static JPanel centerPanel1; //Game boards will be placed here
    private final JPanel winLossPanel = new JPanel(); //Game boards will be placed here
    private final JPanel shipHoldingPanel = new JPanel(); //Holds ships on bottom left of window
    private final JPanel opponentShips = new JPanel(); //Opponent's ship tracking panel
    private final JPanel playerShips = new JPanel(); //Player's ship tracking panel
    private final JPanel turnTracker = new JPanel(); //Displays turn tracking

    //Borders
    Border raisedBorder = new EtchedBorder(EtchedBorder.RAISED); //Raised Border

    //Background Data
    TurnTracker tracker = new TurnTracker();

    //sound effect;
    static Clip clip; // BGM;
    Clip clip2; //clip sound;

    //Constructor
    public Game(){};
    public Game(int size) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameScreen = style.frame("Game Play", WIDTH, LENGTH);
        gameBoardSize = size;
        centerPanel = new JPanel(); //Game boards will be placed here
        centerPanel1 = new JPanel();

        if(gameBoardSize == 7) {
            buttons = new HitOrMiss[49];
            buttons1 = new HitOrMiss[49];
        }

        else if(gameBoardSize == 8){
            buttons = new HitOrMiss[64];
            buttons1 = new HitOrMiss[64];
        }

        else if(gameBoardSize == 9){
            buttons = new HitOrMiss[81];
            buttons1 = new HitOrMiss[81];
        }

        if(size == 7) {
            gameBoard = new GameBoard(7);
        }

        if(size == 8) {
            gameBoard = new GameBoard(8);
        }

        if(size == 9) {
            gameBoard = new GameBoard(9);
        }

        assert gameBoard != null;
        gameBoard.initialize();
        GameBoard.frame.setVisible(false);
        setScreen();
        gameScreen.setVisible(true);
        GameBoard.frame.setVisible(true);
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

        submarine = new ImageIcon(getClass().getResource("bsSubmarineimg.png"));
        twoboat = new JLabel(submarine);
        twoboat.setBounds(40, 310, 240,60);

        cruiser = new ImageIcon(getClass().getResource("bsCruiserimg.png"));
        threeboat1 = new JLabel(cruiser);
        threeboat1.setBounds(40,370 , 240,60);

        battleship = new ImageIcon(getClass().getResource("bsBattleshipimg.png"));
        threeboat2 = new JLabel(battleship);
        threeboat2.setBounds(40, 430, 240,60);

        warMachine = new ImageIcon(getClass().getResource("bsWarMachineimg.png"));
        fourboat = new JLabel(warMachine);
        fourboat.setBounds(40, 490, 240,60);

        carrier = new ImageIcon(getClass().getResource("bsCarrierimg.png"));
        fiveboat = new JLabel(carrier);
        fiveboat.setBounds(40, 550, 240,60);

        //Set Center Panel (Game boards)
        centerPanel.setBorder(raisedBorder);
        centerPanel.setBackground(new Color(0,0,0, 125)); //a is the transparency value
        centerPanel.setBounds(325,10,600,295);
        centerPanel1.setBorder(raisedBorder);
        centerPanel1.setBackground(new Color(0,0,0, 125)); //a is the transparency value
        centerPanel1.setBounds(325,315,600,295);

        //Select Board Size
        if(gameBoardSize == 7) {
            back.add(twoboat);
            back.add(threeboat1);
            back.add(threeboat2);
            centerPanel.setLayout(new GridLayout(7, 7));
            List<String> listArray1 = new ArrayList<String>();
            int j = 0;

            for(String s : GameBoard.array1) {
                if(s != null && s.length() > 0) {
                    listArray1.add(s);
                }
            }

            GameBoard.array1 = listArray1.toArray(new String[0]);

            Arrays.sort(GameBoard.array1);

            String[] array1Sorted = new String[100];

            System.arraycopy(GameBoard.array1, 0, array1Sorted, 0, 8);

            for(int t=8; t < 40; t++){
                array1Sorted[t] = "0";
            }

            for (int i = 0; i < 49; i++) {
                try{

                    if (array1Sorted[j].equals(gameBoardArray7[i])) {
                        buttons[i] = new HitOrMiss(gameBoardArray7[i], true, false);
                        centerPanel.add(buttons[i]);
                        j++;
                    } else {
                        buttons[i] = new HitOrMiss(gameBoardArray7[i], false, false);
                        centerPanel.add(buttons[i]);
                    }
                }catch(Exception ignored){}
            }

            ////////AI BOARD
            centerPanel1.setLayout(new GridLayout(7, 7));
            List<String> listArray2 = new ArrayList<String>();
            int r = 0;

            for(String s : GameBoard.arrayAI1) {
                if(s != null && s.length() > 0) {
                    listArray2.add(s);
                }
            }

            GameBoard.arrayAI1 = listArray2.toArray(new String[0]);

            Arrays.sort(GameBoard.arrayAI1);

            String[] array2Sorted = new String[100];

            System.arraycopy(GameBoard.arrayAI1, 0, array2Sorted, 0, 8);

            for(int t=8; t < 40; t++){
                array2Sorted[t] = "0";
            }

            for (int[] ints : gameBoardMultiArray7) {
                Arrays.fill(ints, 0);
            }

            int row = 0;
            int col = 0;

            for (int i = 0; i < 49; i++) {
                if(i == 7 || i == 14 || i == 21 || i == 28 || i == 35 || i == 42) {
                    row++;
                    col = 0;
                }
                try{

                    if (array2Sorted[r].equals(gameBoardArray7[i])) {
                        buttons1[i] = new HitOrMiss(gameBoardArray7[i], true, true);
                        centerPanel1.add(buttons1[i]);
                        r++;
                        gameBoardMultiArray7[row][col] = 1;
                    } else {
                        buttons1[i] = new HitOrMiss(gameBoardArray7[i], false, true);
                        centerPanel1.add(buttons1[i]);
                    }
                    col++;
                }catch(Exception ignored){}
            }
        }//End if(gameBoardSize == 7)

        if(gameBoardSize == 8) {
            back.add(twoboat);
            back.add(threeboat1);
            back.add(threeboat2);
            back.add(fourboat);
            centerPanel.setLayout(new GridLayout(8, 8));
            List<String> listArray1 = new ArrayList<String>();
            int j = 0;

            for(String s : GameBoard.array1) {
                if(s != null && s.length() > 0) {
                    listArray1.add(s);
                }
            }

            GameBoard.array1 = listArray1.toArray(new String[0]);

            Arrays.sort(GameBoard.array1);

            String[] array1Sorted = new String[100];

            System.arraycopy(GameBoard.array1, 0, array1Sorted, 0, 12);

            for(int t=12; t < 40; t++){
                array1Sorted[t] = "0";
            }
            for (int i = 0; i < 64; i++) {
                try{

                    if (array1Sorted[j].equals(gameBoardArray8[i])) {
                        buttons[i] = new HitOrMiss(gameBoardArray8[i], true, false);
                        centerPanel.add(buttons[i]);
                        j++;
                    } else {
                        buttons[i] = new HitOrMiss(gameBoardArray8[i], false, false);
                        centerPanel.add(buttons[i]);
                    }
                }catch(Exception ignored){}
            }

            ////////AI BOARD
            centerPanel1.setLayout(new GridLayout(8, 8));
            List<String> listArray2 = new ArrayList<String>();
            int r = 0;

            for(String s : GameBoard.arrayAI1) {
                if(s != null && s.length() > 0) {
                    listArray2.add(s);
                }
            }

            GameBoard.arrayAI1 = listArray2.toArray(new String[0]);

            Arrays.sort(GameBoard.arrayAI1);

            String[] array2Sorted = new String[100];

            System.arraycopy(GameBoard.arrayAI1, 0, array2Sorted, 0, 12);

            for(int t=12; t < 40; t++){
                array2Sorted[t] = "0";
            }

            for (int[] ints : gameBoardMultiArray8) {
                Arrays.fill(ints, 0);
            }

            int row = 0;
            int col = 0;

            for (int i = 0; i < 64; i++) {
                if(i == 8 || i == 16 || i == 24 || i == 32 || i == 40 || i == 48 || i == 56) {
                    row++;
                    col = 0;
                }
                try{

                    if (array2Sorted[r].equals(gameBoardArray8[i])) {
                        buttons1[i] = new HitOrMiss(gameBoardArray8[i], true, true);
                        centerPanel1.add(buttons1[i]);
                        r++;
                        gameBoardMultiArray8[row][col] = 1;
                    } else {
                        buttons1[i] = new HitOrMiss(gameBoardArray8[i], false, true);
                        centerPanel1.add(buttons1[i]);
                    }
                    col++;
                }catch(Exception ignored){}
            }
        }

        if(gameBoardSize == 9) {
            back.add(twoboat);
            back.add(threeboat1);
            back.add(threeboat2);
            back.add(fourboat);
            back.add(fiveboat);
            centerPanel.setLayout(new GridLayout(9, 9));
            List<String> listArray1 = new ArrayList<String>();
            int j = 0;

            for(String s : GameBoard.array1) {
                if(s != null && s.length() > 0) {
                    listArray1.add(s);
                }
            }

            GameBoard.array1 = listArray1.toArray(new String[0]);

            Arrays.sort(GameBoard.array1);

            String[] array1Sorted = new String[100];

            System.arraycopy(GameBoard.array1, 0, array1Sorted, 0, 16);

            for(int t=16; t < 40; t++){
                array1Sorted[t] = "0";
            }
            for (int i = 0; i < 81; i++) {
                try{

                    if (array1Sorted[j].equals(gameBoardArray9[i])) {
                        buttons[i] = new HitOrMiss(gameBoardArray9[i], true, false);
                        centerPanel.add(buttons[i]);
                        j++;
                    } else {
                        buttons[i] = new HitOrMiss(gameBoardArray9[i], false, false);
                        centerPanel.add(buttons[i]);
                    }
                }catch(Exception ignored){}
            }

            ////////AI BOARD
            centerPanel1.setLayout(new GridLayout(9, 9));
            List<String> listArray2 = new ArrayList<String>();
            int r = 0;

            for(String s : GameBoard.arrayAI1) {
                if(s != null && s.length() > 0) {
                    listArray2.add(s);
                }
            }

            GameBoard.arrayAI1 = listArray2.toArray(new String[0]);

            Arrays.sort(GameBoard.arrayAI1);

            String[] array2Sorted = new String[100];

            System.arraycopy(GameBoard.arrayAI1, 0, array2Sorted, 0, 16);

            for(int t=16; t < 40; t++){
                array2Sorted[t] = "0";
            }

            for (int[] ints : gameBoardMultiArray9) {
                Arrays.fill(ints, 0);
            }

            int row = 0;
            int col = 0;

            for (int i = 0; i < 81; i++) {
                if(i == 9 || i == 18 || i == 27 || i == 36 || i == 45 || i == 54 || i == 63 || i == 72) {
                    row++;
                    col = 0;
                }
                try{

                    if (array2Sorted[r].equals(gameBoardArray9[i])) {
                        buttons1[i] = new HitOrMiss(gameBoardArray9[i], true, true);
                        centerPanel1.add(buttons1[i]);
                        r++;
                        gameBoardMultiArray9[row][col] = 1;
                    } else {
                        buttons1[i] = new HitOrMiss(gameBoardArray9[i], false, true);
                        centerPanel1.add(buttons1[i]);
                    }
                    col++;
                }catch(Exception ignored){}
            }
        }

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
                    ////Restart the game//////
                    gameScreen.dispose();
                    gameScreen = null;
                    gameBoard.gameRestart();
                    gameBoard.dispose();
                    gameBoard = null;
                    buttons = null;
                    buttons1 = null;
                    GameBoard.closeGuessWindow();
                    /////////////////////////

                    try {
                        new Welcome();
                        clip2 = new SoundEffect().playClickSound();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    GameBoard.frame.dispose();
                    Winner.close();
                    Loser.close();
                    clip.stop();
                }
        );


        settings.addActionListener(e ->
                {
                    new Settings();
                    try {
                        clip2 = new SoundEffect().playClickSound();
                    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    gameScreen.setVisible(false);
                }
        );

    }

    public static void closeGameScreen(){
        gameScreen.dispose();
    }
}
