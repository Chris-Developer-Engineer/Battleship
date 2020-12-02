import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*; //GUI
import java.awt.*; //Font
import java.awt.event.*; //Listeners
import java.io.IOException;

public class BoardSelection {
    private final int WIDTH = 200, LENGTH = 350;
    private final Style style = new Style();
    private final JFrame gameScreen = style.frame("Welcome", WIDTH, LENGTH);

    //Menu Instantiation
    private final JMenuBar mainBar = new JMenuBar();
    private final JMenu fileMenu = style.menu("File");
    private final JMenuItem exitItem = style.menuItem("Exit");

    //Images Instantiation....to update image replace file name
    Icon gameBoardSeven = new ImageIcon(getClass().getResource("7x71.png"));
    Icon gameBoardEight = new ImageIcon(getClass().getResource("8x81.png"));
    Icon gameBoardNine = new ImageIcon(getClass().getResource("9x91.png"));

    //Rollover images....to update image replace file name
    Icon SevenRollover = new ImageIcon(getClass().getResource("7x72.png"));
    Icon EightRollover = new ImageIcon(getClass().getResource("8x82.png"));
    Icon NineRollover = new ImageIcon(getClass().getResource("9x92.png"));

    //Button/Labels Instantiation
    private final JButton board1 = new JButton(gameBoardSeven);
    private final JButton board2 = new JButton(gameBoardEight);
    private final JButton board3 = new JButton(gameBoardNine);


    public BoardSelection() {
        setScreen();
        gameScreen.setVisible(true);
    }

    public void setScreen() {
        gameScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Background set
        ImageIcon selectionBackground = new ImageIcon(getClass().getResource("SelectionBG.png"));
        JLabel back = new JLabel(selectionBackground);
        back.setSize(199, 350);

        gameScreen.add(back);
        gameScreen.setLocationRelativeTo(null);
        gameScreen.getContentPane().setBackground(Color.black);
        gameScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        board1.setBounds(15, 10, 170, 78);
        board2.setBounds(15, 100, 170, 78);
        board3.setBounds(15, 190, 170, 78);

        //Rollovers for Buttons....this will NOT need to be changed if the image is updated
        board1.setRolloverIcon(SevenRollover);
        board2.setRolloverIcon(EightRollover);
        board3.setRolloverIcon(NineRollover);

        back.add(board1);
        back.add(board2);
        back.add(board3);

        gameScreen.setLocationRelativeTo(null);
        gameScreen.setLayout(new FlowLayout());

        mainBar.add(fileMenu);
        fileMenu.add(exitItem);

        gameScreen.setJMenuBar(mainBar);

        exitItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );

        board1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameScreen.dispose();
                        try {
                            new Game(7);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }
                    }
                }
        );

        board2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameScreen.dispose();
                        try {
                            new Game(8);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }
                    }
                }
        );

        board3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameScreen.dispose();
                        try {
                            new Game(9);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        }
                    }
                }
        );

    }
}
