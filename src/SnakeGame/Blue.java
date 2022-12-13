package SnakeGame;

import java.awt.Graphics;
import java.awt.Image;

import java.io.File;

import javax.imageio.ImageIO;

public class Blue extends Food {
    private String loc = "src/assets/blue.png";

    public Blue(int foodX, int foodY) {
        super(foodX, foodY);
        super.setImg(this.getImage(loc));
    }

    public Image getImage(String loc) {
        try {
            return ImageIO.read(new File(loc));
        } catch (Exception e) {
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
        if (snake.getLen() > 1) {
            snake.addBody();
            snake.addBody();
        } else {
            snake.setHp(0);
        }

    }
}
