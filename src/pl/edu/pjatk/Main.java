package pl.edu.pjatk;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WelcomeScr.window(true);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\barte\\Documents\\PJAKT\\OneDrive - Polsko-Japońska Akademia Technik Komputerowych\\POJ\\Projekt_POJ\\src\\pl\\edu\\pjatk\\Background.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (LineUnavailableException f) {
            f.printStackTrace();
        }
    }
}
