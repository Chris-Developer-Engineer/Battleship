import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Winner {
    private final int WIDTH = 650, LENGTH = 600;
    private Style style = new Style();
    private JFrame winScreen = style.frame("Winner", WIDTH, LENGTH);
    private ImageIcon winBackground;
    private JLabel back;
    private ImageIcon winPlaque;
    private JLabel overhead;

    //Images Instantiation
    Icon rtmImage = new ImageIcon(getClass().getResource("MMenu1.png"));

    //Rollover images
    Icon rtmImageRollover = new ImageIcon(getClass().getResource("MMenu2.png"));

    //Buttons
    private JButton returnToMenu = new JButton(rtmImage);

    public Winner(){
        setScreen();
        winScreen.setVisible(true);
    }

    public void setScreen(){
        //Background set
        winBackground = new ImageIcon(getClass().getResource("winBG.gif"));
        back = new JLabel(winBackground);
        back.setSize(650,600);

        winPlaque = new ImageIcon(getClass().getResource("winPlate.png"));
        overhead = new JLabel(winPlaque);
        overhead.setBounds(025, 10, 600,208);

        winScreen.add(back);
        winScreen.setLocationRelativeTo(null);
        winScreen.getContentPane().setBackground(Color.black);
        winScreen.setLayout(new FlowLayout(FlowLayout.CENTER));

        returnToMenu.setBounds(240,480,170,75);

        //Rollovers for Buttons
        returnToMenu.setRolloverIcon(rtmImageRollover);

        back.add(returnToMenu);
        back.add(overhead);


        //Action Listener
        returnToMenu.addActionListener( //Main Menu button
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new Welcome();
                        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                            unsupportedAudioFileException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (LineUnavailableException lineUnavailableException) {
                            lineUnavailableException.printStackTrace();
                        }
                        winScreen.dispose();
                        ////Restart the game//////
                        Game game = new Game();
                        GameBoard gameBoard = new GameBoard();
                        game.gameScreen.dispose();
                        game.gameScreen = null;
                        gameBoard.gameRestart();
                        gameBoard.dispose();
                        gameBoard = null;
                        game.buttons = null;
                        game.buttons1 = null;
                        GameBoard.closeGuessWindow();
                        /////////////////////////
                        game.clip.stop();
                        Tracker.clearPanel();
                    }
                }
        );
    }

    public static void close(){
        Winner win = new Winner();
        win.winScreen.dispose();
    }
}
