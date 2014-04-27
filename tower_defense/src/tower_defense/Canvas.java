package tower_defense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = -4651859705730816296L;
	
	private GameWindow containingWindow;
	
	public Canvas(GameWindow containingWindow) {
		this.containingWindow = containingWindow;
		this.setBackground(new Color(0, 128, 0));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseEvent(e);
			}
		});
	}
	
	public void setCommand(GameWindow.Command command) {
		
	}
	
	private void mouseEvent(MouseEvent e) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
