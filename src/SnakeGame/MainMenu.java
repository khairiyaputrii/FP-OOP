package SnakeGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainMenu extends Screen {
    private static final long serialVersionUID = 1L;
    private final static String DEFAULT_LOCATION = "src/assets/";
    private Image backgroundMenu, newGameBtn, snakeTitle;

    public MainMenu(JFrame referred) {
        super(referred);
        backgroundMenu = this.loadImg("Background.png");
        newGameBtn = this.loadImg("start.png");
        snakeTitle = this.loadImg("gametitle.png");

        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getPoint().x >= 295 && e.getPoint().x <= 550 && e.getPoint().y >= 265 && e.getPoint().y <= 345) {
                    stateChange(1);
                }
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
        g.drawImage(snakeTitle, 25, 11, null); //polymorphism
        g.drawImage(backgroundMenu, 25, 75, null);
        g.drawImage(newGameBtn, 295, 300, null);
    }

    @Override
    public void stateChange(int state) {
        switch (state) {
            case 1:
                referred.setContentPane(new LevelSelect(referred));
                referred.validate();
                referred.getContentPane().requestFocusInWindow();
                break;
    }
    }

}
