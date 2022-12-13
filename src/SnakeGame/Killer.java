package SnakeGame;

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Killer extends Food {
    private String loc = "src/assets/killer.png";

    public Killer(int foodX, int foodY) {
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
        snake.setHp(0);

    }
}
