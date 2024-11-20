package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// s ovin izademo iz prozora na x
		window.setResizable(false);
		window.setTitle("2D igrica Duje Nizica");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack(); // omogucava da uzme sve iz game panela i napravi nam takav ekran u nasen
						// slucaju 4x3

		window.setLocationRelativeTo(null); // prozor ce bit prikazan na sri ekrana

		window.setVisible(true);

		gamePanel.startGameThread();

	}

}
