package tower_defense;

import javax.swing.JPanel;

public class GUI extends JPanel {
	private static final long serialVersionUID = 6388969414767899382L;
	
	private GameWindow containingWindow;
	
	public GUI(GameWindow containingWindow) {
		this.containingWindow = containingWindow;
		
	}
}
