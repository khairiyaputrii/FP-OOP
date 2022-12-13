package SnakeGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Screen extends JPanel implements Constant{
    private static final long serialVersionUID = 1L;
    JFrame referred;
    protected Image screenImg;

    public Screen(JFrame referred) {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.referred = referred;

    }

    public abstract void render(Graphics g);

    public abstract void stateChange(int state);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public Image getImage() {
        return this.screenImg;
    }

    public void setImg(Image screenImg) {
        this.screenImg = screenImg;
    }

}
