//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class musicStuff {
    public musicStuff() {
    }

    void playMusic(String musicLocation) {
        boolean status = false;

        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(-1);
                status = true;
                JOptionPane.showMessageDialog((Component)null, "press ok to pause playing");
                long clipTimePostion = clip.getMicrosecondPosition();
                clip.stop();
                JOptionPane.showMessageDialog((Component)null, "press ok to Resume");
                clip.setMicrosecondPosition(clipTimePostion);
                clip.start();
                JOptionPane.showMessageDialog((Component)null, "press ok to stop playing");
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }
}
