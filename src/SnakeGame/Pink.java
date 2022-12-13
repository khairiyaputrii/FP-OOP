package SnakeGame;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Image;

public class Pink extends Food {

    public Pink(int foodX, int foodY) {
        super(foodX, foodY);
        super.setImg(this.getImage("src/assets/pink.png"));
    }

    public Image getImage(String loc) {
        try {
            return ImageIO.read(new File(loc));
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(super.getImage(), super.getFoodX(), super.getFoodY(), null);
    }

    @Override
    public void giveCond(Snake snake) {
        snake.addBody();
    }

}
