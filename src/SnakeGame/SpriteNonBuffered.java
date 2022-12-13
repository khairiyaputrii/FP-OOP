package SnakeGame;

import java.awt.Graphics;
import java.awt.Image;

public abstract class SpriteNonBuffered {

    private int spriteX, spriteY;
    private Image spriteImage;
    private int tick;
    int counterTick = 0;
    protected int loop = 0;

    public abstract void giveCond(Snake snake);

    public abstract void render(Graphics g);

    public abstract void framecounter();

    public int getLoop() {
        return this.loop;
    }

    public void tick(Snake snake) {
        for (int i = 0; i < snake.getLen(); i++) {
            if (snake.snakeX.get(i) == spriteX && snake.snakeY.get(i) == spriteY) {
                counterTick++;
                System.out.println(counterTick);
                System.out.println(tick);
                if (counterTick > tick) {
                    this.giveCond(snake);
                    counterTick = 0;
                }
            }
        }
    }

    public SpriteNonBuffered(int tick, int spriteX, int spriteY) {
        this.tick = tick;
        this.spriteX = spriteX;
        this.spriteY = spriteY;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void setSpriteY(int spriteY) {
        this.spriteY = spriteY;
    }

    public Image getCurrentImage() {
        return this.spriteImage;
    }

    public void setCurrentImage(Image spriteImage) {
        this.spriteImage = spriteImage;
    }

}
