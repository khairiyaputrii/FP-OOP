package SnakeGame;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Food {

    private int foodX, foodY;
    private Image foodImg;
    Music sfx = new Music();

    public abstract void render(Graphics g);

    public abstract void giveCond(Snake snake);

    public boolean eatenBySnake(Snake snake) {
        if (snake.headX() == this.foodX && snake.headY() == this.foodY) {
            this.giveCond(snake);
            sfx.playSFX("src/assets/music/SFX/eat.wav");
            return true;
        }
        return false;
    }

    public Food(int foodX, int foodY) {
        this.foodX = foodX;
        this.foodY = foodY;
    }

    public int getFoodX() {
        return foodX;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    public Image getImage() {
        return this.foodImg;
    }

    public void setImg(Image foodImg) {
        this.foodImg = foodImg;
    }
}