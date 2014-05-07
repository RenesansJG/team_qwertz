package tower_defense;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Container extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private GUI gui;
	private int statusBarHeight = 40;
	
	public Container(int width, int height) {
		setLayout(new BorderLayout());
		canvas = new Canvas(width, height - statusBarHeight);
		gui = new GUI(canvas);
		canvas.setGui(gui);
		gui.setPreferredSize(new Dimension(width, 80));
		this.add(canvas,  BorderLayout.NORTH);
		this.add(gui, BorderLayout.SOUTH);
	}
	
	public void gameLoaded() {
		canvas.drawBackground();
	}
	
}
