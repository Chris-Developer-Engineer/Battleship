import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class HitOrMiss extends JButton implements ActionListener{
    static ImageIcon X,O,S;

    public HitOrMiss(String number, boolean xOrO, boolean player){
        if(xOrO && player) {
            X = new ImageIcon(this.getClass().getResource("X.png"));
            S = new ImageIcon((this.getClass().getResource("gray square.png")));
            setIcon(S);
        }

        else if(xOrO && player == false) {
            X = new ImageIcon(this.getClass().getResource("X.png"));
        }

        else{
            O = new ImageIcon(this.getClass().getResource("O.png"));
        }
        this.setText(number);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(GameBoard.clickSwitch && GameBoard.playerTurnTracker) {
            if(GameBoard.doClickSentHit) {
                setIcon(X);
                try {
                    new SoundEffect().playGameHit();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }
            else if(GameBoard.doClickSentMiss) {
                setIcon(O);
                try {
                    new SoundEffect().playGameMiss();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }

        }

        else if(GameBoard.clickSwitch && GameBoard.AITurnTracker) {
            if(GameBoard.doClickSentHitAI) {
                setIcon(X);
                try {
                    new SoundEffect().playGameHit();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }
            else if(GameBoard.doClickSentMissAI) {
                setIcon(O);
                try {
                    new SoundEffect().playGameMiss();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
            }

        }
    }

}
