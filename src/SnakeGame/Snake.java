package SnakeGame;

import java.io.File;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import javax.imageio.*;

public class Snake {

    private int boardRight = 850;
    private int boardLeft = 25;
    private int boardTop = 75;
    private int boardBot = 625;
    protected List<Integer> snakeX = new ArrayList<>();
    protected List<Integer> snakeY = new ArrayList<>();
    protected boolean left = false;
    protected boolean right = true;
    protected boolean up = false;
    protected boolean down = false;
    private Image snakeright;
    private Image snakeleft;
    private Image snakeup;
    private Image snakedown;
    private Image snakeBody;
    private int len = 3;
    private int hp;

    public Snake(int HPmodifier) {
        this.snakeX = new ArrayList<>();
        this.snakeY = new ArrayList<>();

        this.snakeX.add(250);
        this.snakeY.add(200);

        this.snakeX.add(225);
        this.snakeY.add(200);

        this.snakeX.add(200);
        this.snakeY.add(200);

        this.hp = (int) ((int) 100 * HPmodifier);
        this.len = 3;
        this.setDir(false, true, false, false);
        snakeright = getImage("src/assets/snakeright.png");
        snakeleft = getImage("src/assets/snakeleft.png");
        snakeup = getImage("src/assets/snakeup.png");
        snakedown = getImage("src/assets/snakedown.png");
        snakeBody = getImage("src/assets/snakebody.png");

    }

    public Image getImage(String loc) {
        try {
            return ImageIO.read(new File(loc));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getLen() {
        return len;
    }

    public void setDir(boolean left, boolean right, boolean up, boolean down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public void render(Graphics g) {
        for (int i = 1; i < this.len; i++) {
            g.drawImage(snakeBody, snakeX.get(i), snakeY.get(i), null);
        }

        if (up) {
            g.drawImage(snakeup, snakeX.get(0), snakeY.get(0), null);
        } else if (down) {
            g.drawImage(snakedown, snakeX.get(0), snakeY.get(0), null);
        } else if (left) {
            g.drawImage(snakeleft, snakeX.get(0), snakeY.get(0), null);
        } else if (right) {
            g.drawImage(snakeright, snakeX.get(0), snakeY.get(0), null);
        }
    }

    public void addBody() {
        this.snakeX.add(snakeX.get(this.len - 1));
        this.snakeY.add(snakeY.get(this.len - 1));
        this.len++;
    }

    public void delBody() {
        this.snakeX.remove(snakeX.get(this.len - 1));
        this.snakeY.remove(snakeY.get(this.len - 1));
        this.len--;
    }

    public int headX() {
        return this.snakeX.get(0);
    }

    public int headY() {
        return this.snakeY.get(0);
    }

    public void move() {
        for (int i = len - 1; i > 0; i--) {
            snakeX.set(i, snakeX.get(i - 1));
            snakeY.set(i, snakeY.get(i - 1));
        }
        if (up) {
            snakeY.set(0, snakeY.get(0) - 25);
            if (snakeY.get(0) < boardTop) {
                snakeY.set(0, boardBot);
            }
        } else if (down) {
            snakeY.set(0, snakeY.get(0) + 25);
            if (snakeY.get(0) > boardBot) {
                snakeY.set(0, boardTop);
            }
        } else if (left) {
            snakeX.set(0, snakeX.get(0) - 25);
            if (snakeX.get(0) < boardLeft) {
                snakeX.set(0, boardRight);
            }
        } else if (right) {
            snakeX.set(0, snakeX.get(0) + 25);
            if (snakeX.get(0) > boardRight) {
                snakeX.set(0, boardLeft);
            }
        }

        for (int i = 1; i < len; i++) {
            if (snakeX.get(i).equals(snakeX.get(0)) && snakeY.get(i).equals(snakeY.get(0))) {
                this.setDir(false, false, false, false);
                this.setHp(0);
                break;
            }
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}