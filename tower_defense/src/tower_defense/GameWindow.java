package tower_defense;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 7817024087343251889L;
	private Canvas canvas;
	private GUI gui;
	
	public GameWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tower defense");
		initComponents();
	}
	
	private void initComponents() {
		canvas = new Canvas();
		gui = new GUI();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame gameWindow = new GameWindow();
				gameWindow.setVisible(true);
			}
		});
	}
}
