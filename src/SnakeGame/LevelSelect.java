package SnakeGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LevelSelect extends Screen {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final static String DEFAULT_LOCATION = "src/assets/";
    private Image backgroundMenu, easy, hard, snakeTitle;
    Gameplay gm;

    public LevelSelect(JFrame referred) { 
        super(referred);
        backgroundMenu = this.loadImg("background.png");
        easy = this.loadImg("easy.png");
        hard = this.loadImg("hard.png");
        snakeTitle = this.loadImg("gametitle.png");

        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getPoint().x >= 295 && e.getPoint().x <= 550 && e.getPoint().y >= 265 && e.getPoint().y <= 345) {
                    stateChange(1);
                }
                ;

                if (e.getPoint().x >= 295 && e.getPoint().x <= 550 && e.getPoint().y >= 355 && e.getPoint().y <= 435) {
                    stateChange(2);
                }
                ;
            }
        });
    }

    private Image loadImg(String filename) {
        try {
            return ImageIO.read(new File(DEFAULT_LOCATION + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(snakeTitle, 25, 11, null);
        g.drawImage(backgroundMenu, 25, 75, null);
        g.drawImage(easy, 295, 265, null);
        g.drawImage(hard, 295, 355, null);
    }

    @Override
    public void stateChange(int state) {
        Gameplay gm = new Gameplay(referred, state);
        referred.setContentPane(gm);
        referred.validate();
        referred.getContentPane().requestFocusInWindow();
    }

}
