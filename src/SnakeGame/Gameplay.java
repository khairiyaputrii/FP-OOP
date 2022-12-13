package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Gameplay extends Screen implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame dp = new JFrame();
	private final static String DEFAULT_LOCATION = "src/assets/";
	private int[] foodX = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
			500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] foodY = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	// Music music = new Music();

	private ImageIcon titleImage;
	private ImageIcon background;
	private Image restart;
	private Image cLevel;
	private boolean isPaused = false;
	protected int difficulty = 2;
	private Snake snake = new Snake(1);
	long pauseTime = 0;
	private int width = 851;
	private int height = 55;
	private int x = 24;
	private int y = 10;
	List<Food> food = new ArrayList<>();
	List<SpriteNonBuffered> spriteNonBuffer = new ArrayList<>();

	public Gameplay(JFrame referred, int diff) {
		super(referred);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		difficulty = diff;
		startThread();
		spriteThread();
		restart = loadImg("restart.png");
		cLevel = loadImg("selectlevel.png");
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);

		titleImage = new ImageIcon("src/assets/gametitle.png");
		titleImage.paintIcon(this, g, x + 1, y + 1);

		g.setColor(Color.WHITE);
		g.drawRect(x, 74, width, height + 522);


		switch (difficulty) {
			case 1:
				background = new ImageIcon("src/assets/backgroundeasy.png");
				break;
			case 2:
				background = new ImageIcon("src/assets/backgroundhard.png");
				break;
		}
		background.paintIcon(this, g, x + 1, 75);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Monospaced", Font.BOLD, 16));
		g.drawString("Length: " + snake.getLen(), 770, 45);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Monospaced", Font.BOLD, 16));
		g.drawString("Press ESC to pause", 40, 45);
		for (Food i : food) {
			i.render(g);
		}
		snake.render(g);

		for (Food i : food) {
			i.render(g);
		}
		for (SpriteNonBuffered i : spriteNonBuffer) {
			i.render(g);
		}
		snake.render(g);
		if (isPaused) {
			System.out.println("Paused");
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial", Font.BOLD, 70));
			g.drawString("Paused", 315, 340);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.drawString("Press ESC to unpause", 260, 380);
		}
		if (snake.getHp() == 0) {
			g.drawImage(restart, 165, 390, null);
			g.drawImage(cLevel, 515, 390, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Monospaced", Font.BOLD, 100));
			g.drawString("GAME OVER", 140, 300);
			g.setFont(new Font("Monospaced", Font.BOLD, 30));
			g.drawString("Your Score: " + snake.getLen(), 360, 350);
			g.setFont(new Font("arial", Font.BOLD, 35));
		}
	}

	private Image loadImg(String filename) {
		try {
			return ImageIO.read(new File(DEFAULT_LOCATION + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void spriteThread() {
		Thread sprites = new Thread(new Runnable() {
			public void run() {
				while (snake.getHp() > 0) {
					for (SpriteNonBuffered i : spriteNonBuffer) {
						i.framecounter();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sprites.start();
	}

	public void startThread() {
		food.add(appleGen());
		Thread movement = new Thread(new Runnable() {
			public void run() {
				while (snake.getHp() > 0) {
					System.out.println(isPaused);
					if (isPaused == false) {
						System.out.println(difficulty + "Thread");
						snake.move();
						foodGen();
						checkBoard();
						if (difficulty == 2) {
							nonBufferGen();
						}
						repaint();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						repaint();
					}
				}
				if (snake.getHp() == 0) {
					gameOver();
				}
			}
		});
		movement.start();
	}

	private void gameOver() {
		snake.setHp(0);
		Gameplay temp = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() >= 665 && e.getX() <= 740 && e.getY() >= 335 && e.getY() <= 365) {
					stateChange(0);
				}
				else if (e.getX() >= 165 && e.getX() <= 365 && e.getY() >= 435 && e.getY() <= 515)
				{
					stateChange(1);
				}
				else if (e.getX() >= 515 && e.getX() <= 715 && e.getY() >= 435 && e.getY() <= 515)
				{
					stateChange(2);
				}
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && snake.down != true) {
			snake.setDir(false, false, true, false);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.right != true) {
			snake.setDir(true, false, false, false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.up != true) {
			snake.setDir(false, false, false, true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.left != true) {
			snake.setDir(false, true, false, false);
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void foodGen() {
		List<Integer> eaten = new ArrayList<>();

		for (int i = 0; i < food.size(); i++) {
			if (food.get(i).eatenBySnake(snake)) {
				eaten.add(i);

				if (food.get(i) instanceof Food) {
					Random rng = new Random();
					int Pink = rng.nextInt(3);
					System.out.printf("Pink Monster rng = %d\n", Pink);
					for (int z = 0; z < Pink; z++) {
						food.add(appleGen());
					}
					int killerRng = rng.nextInt(50);
					System.out.printf("Killer rng = %d\n", killerRng);
					if (killerRng <= 7)
						food.add(bombGen());
					int blueRng = rng.nextInt(50);
					System.out.printf("Blue Monster rng = %d\n", blueRng);
					if (blueRng <= 17)
						food.add(rottenGen());
				}
			}
		}

		for (int i : eaten) {
			food.remove(i);
		}

	}

	public void nonBufferGen() {
		List<Integer> dissapear = new ArrayList<>();
		for (int i = 0; i < spriteNonBuffer.size(); i++) {
			spriteNonBuffer.get(i).tick(snake);
			if (spriteNonBuffer.get(i).getLoop() > 2) {
				dissapear.add(i);
			}
		}
		for (int i : dissapear) {
			spriteNonBuffer.remove(i);
		}
	}

	public Pink appleGen() {
		Random rng = new Random();
		int x = rng.nextInt(34);
		int y = rng.nextInt(22);
		System.out.printf("Pink Monster on [%d][%d]\n", foodX[x], foodY[y]);
		return new Pink(foodX[x], foodY[y]);
	}

	public Blue rottenGen() {
		Random rng = new Random();
		int x = rng.nextInt(34);
		int y = rng.nextInt(22);
		System.out.printf("Blue Monster on [%d][%d]\n", foodX[x], foodY[y]);
		return new Blue(foodX[x], foodY[y]);
	}

	public Killer bombGen() {
		Random rng = new Random();
		int x = rng.nextInt(34);
		int y = rng.nextInt(22);
		System.out.printf("Killer on [%d][%d]\n", foodX[x], foodY[y]);
		return new Killer(foodX[x], foodY[y]);
	}

	public void checkBoard() {
		int z = 0;
		boolean flag = false;
		for (Food i : food) {
			if (i instanceof Blue) {
				z++;
			}
			if (i instanceof Pink) {
				flag = true;
			}
		}
		if (flag == false || food.size() == 0) {
			System.out.println("------genset------");
			food.add(appleGen());
		}
		if (z > 10) {
			for (Food i : food) {
				if (i instanceof Blue) {
					food.remove(i);
				}
			}
		}
	}

	public void restartGame() {
		food.clear();
		spriteNonBuffer.clear();
		snake = new Snake(1);
		startThread();
		if (difficulty == 2) {
			spriteThread();
		}
	}


	public void setDiff(int i) {
		System.out.println(i);
		this.difficulty = i;
	}

	@Override
	public void stateChange(int state) {
		switch (state) {
			case 0:
				referred.setContentPane(new MainMenu(referred));
				referred.validate();
				referred.getContentPane().requestFocusInWindow();
				break;
			case 1:
				referred.setContentPane(new Gameplay(referred, difficulty));
				referred.validate();
				referred.getContentPane().requestFocusInWindow();
				break;
			case 2:
				referred.setContentPane(new LevelSelect(referred));
				referred.validate();
				referred.getContentPane().requestFocusInWindow();
				break;
		}
	}

	
}
