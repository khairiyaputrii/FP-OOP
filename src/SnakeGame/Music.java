package SnakeGame;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Music {

    private AudioInputStream audioInput;
    private Clip clip;

    void playbgMusic(String loc) {
        File path = new File(loc);
        try {
            audioInput = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    void playSFX(String loc) {
        File path = new File(loc);
        try {
            audioInput = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    void stopAll() {
        clip.stop();
    }

    long pauseMusic() {
        long pausePos = clip.getMicrosecondPosition();
        clip.stop();
        return pausePos;
    }

    void playPausedMusic(long time, String loc) {

        File path = new File(loc);
        try {
            audioInput = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.setMicrosecondPosition(time);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
