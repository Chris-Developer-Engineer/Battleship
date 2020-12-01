import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners

public class Welcome {
    private static int WIDTH = 800, LENGTH = 500;
    private static Style style = new Style();
    public static JFrame mainScreen = style.frame("BattleShip", WIDTH, LENGTH);
    private ImageIcon background;
    private JLabel back;
    private ImageIcon battle;
    private JLabel battleship;

    //Menu Instantiation
    private JMenuBar mainBar = new JMenuBar();
    private JMenu fileMenu = style.menu("File");
    private JMenuItem exitItem = style.menuItem("Exit");

    //Images Instantiation....to update image replace file name
    Icon newGameImage = new ImageIcon(getClass().getResource("play1.png"));
    Icon settingsImage = new ImageIcon(getClass().getResource("settings1.png"));
    Icon rulesImage = new ImageIcon(getClass().getResource("rules1.png"));

    //Rollover images....to update image replace file name
    Icon newGameRollover = new ImageIcon(getClass().getResource("play2.png"));
    Icon settingsRollover = new ImageIcon(getClass().getResource("settings2.png"));
    Icon rulesRollover = new ImageIcon(getClass().getResource("rules2.png"));

    //Buttons
    private JButton newGame = new JButton(newGameImage);
    private JButton settings = new JButton(settingsImage);
    private JButton rules = new JButton(rulesImage);

    //SoundEffect
    static Clip clip;
    //Constructor Instantiates the Frame
    public Welcome() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setScreen();
        mainScreen.setVisible(true);
        SoundEffect.page = 1;
        clip = new SoundEffect().playMain();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void setScreen() {
        //Background set
        background = new ImageIcon(getClass().getResource("MainMenuBG.gif"));
        back = new JLabel(background);
        back.setSize(500,500);

        battle = new ImageIcon(getClass().getResource("battleship.jpg"));
        battleship = new JLabel(battle);
        battleship.setBounds(10, 10, 420,66);

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
                        new BoardSelection();
                        try {
                            new Game();
                        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (LineUnavailableException lineUnavailableException) {
                            lineUnavailableException.printStackTrace();
                        }
                        new BoardSelection();
                        mainScreen.dispose();
                        clip.stop();
                    }
                }
        );

        settings.addActionListener( //Settings button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Clip clip2 = new SoundEffect().playClickSound();
                        } catch (LineUnavailableException lineUnavailableException) {
                            lineUnavailableException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }

                        new Settings();

                    }
                }
        );

        rules.addActionListener( //Rules button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Clip clip2 = new SoundEffect().playClickSound();
                        } catch (LineUnavailableException lineUnavailableException) {
                            lineUnavailableException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }

                        new Rules();
                    }
                }
        );

    }
}
