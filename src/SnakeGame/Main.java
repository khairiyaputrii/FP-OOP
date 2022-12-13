// Casting/Conversion --> Screen
// Constructor --> almost in every class
// Overloading -->
// Overriding --> main menu, level select, bomb
// Encapsulation (get set)--> in food class, screen class
// Inheritance --> Almost in every class
// Polymorphism --> MainMenu, LevelSelect
// ArrayList --> making the sanke body
// Exception Handling(try catch) -->Almost in every class
// GUI --> JFrame
// Interface --> constant class
// Abstract Class --> food class
// Generics 
// Collection --> Snake class
// Input Output --> input: main class, output: GamePlay class


package SnakeGame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Attack the Candy Land");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Screen gs = new MainMenu(frame);
		frame.setContentPane(gs);
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);

		String name = JOptionPane.showInputDialog(null,"Enter your Name:");
		System.out.println("Player name is: " + name);

	}
}
