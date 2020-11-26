//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl.Type;

public class SoundEffect {
    static int page = 0; //1 for main, 2 for game;
    Clip play(String filename, double vol) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
        clip.start();
        this.setVol(vol, clip);
        return clip;
    }
    Clip playMain() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        return play("Heroic Feat (Main BGM).wav",Volume.x);
    }

    Clip playGameBGM() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        return play("Game_page_sound.wav",Volume.x);
    }

    Clip playClickSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        return play("Click Sound Effects.wav",Volume.x);
    }

    public static void setVol(double vol, Clip clip) {
        FloatControl gain = (FloatControl)clip.getControl(Type.MASTER_GAIN);
        float dB = (float)(Math.log(vol) / Math.log(10.0D) * 20.0D);
        gain.setValue(dB);
    }

}
