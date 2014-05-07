package tower_defense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JPanel {
	private static final long serialVersionUID = 6388969414767899382L;
	
	ActionListener[] actionListeners;
	private Command lastCommand = Command.noCommand;
	public enum Command {
		buildRedTower,
		buildGreenTower,
		buildBlueTower,
		buildDamageTrap,
		buildSlowTrap,
		useRedCrystal,
		useBlueCrystal,
		useGreenCrystal,
		upgradeTower,
		noCommand
	}
	
	public GUI(final Canvas canvas) {
		final Command[] commandValues = Command.values();
		actionListeners = new ActionListener[commandValues.length - 1];
		for(int i = 0; i < actionListeners.length; ++i) {
			final int j = i;
			actionListeners[j] = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					lastCommand = commandValues[j];
				}
			};
		}
		String[] buttonNames = {
			"Piros Torony", 
			"Zöld Torony", 
			"Kék Torony", 
			"Sebzõ Csapda", 
			"Lassító Csapda",
			"Piros Kristály",
			"Zöld Kristály",
			"Kék Kristály",
			"Torony Fejlesztése"
		};
		JButton button;
		for(int i = 0; i < actionListeners.length; ++i) {
			button = new JButton(buttonNames[i]);
			button.addActionListener(actionListeners[i]);
			this.add(button);
		}
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)) {
					lastCommand = Command.noCommand;
				}
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				canvas.setMosueX(e.getX());
				canvas.setMosueY(e.getY());
			}
		});
	}
	
	public Command getLastCommand() {
		return lastCommand;
	}
	
	public void resetLastCommand() {
		this.lastCommand = Command.noCommand;
	}
}
