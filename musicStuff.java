import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class musicStuff {
    void playMusic(String musicLocation){
        int status = 0;
        try{
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                status = 1;

                while(status ==1) {
                    JOptionPane.showMessageDialog(null, "press ok to pause playing");
                    long clipTimePostion = clip.getMicrosecondPosition();
                    clip.stop();

                    JOptionPane.showMessageDialog(null, "press ok to Resume");
                    clip.setMicrosecondPosition(clipTimePostion);
                    clip.start();
                }
            }

            else{
                System.out.println("Can't find file");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

