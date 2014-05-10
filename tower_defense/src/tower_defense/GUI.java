package tower_defense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

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
			"Z�ld Torony", 
			"K�k Torony", 
			"Sebz� Csapda", 
			"Lass�t� Csapda",
			"Piros Krist�ly",
			"Z�ld Krist�ly",
			"K�k Krist�ly",
			"Torony Fejleszt�se"
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
				if(SwingUtilities.isLeftMouseButton(e)) {
					GameMap map = Game.getMap();
					double x = e.getX();
					double y = e.getY();
					switch(lastCommand) {
						case buildRedTower:
							if (canPlaceTower(x,y,canvas) && Game.takeMagicPower(50))
								map.addObject(new RedTower(x, y));
							break;
						case buildGreenTower:
							if (canPlaceTower(x,y,canvas) && Game.takeMagicPower(50))
								map.addObject(new GreenTower(x, y));
							break;
						case buildBlueTower:
							if (canPlaceTower(x,y,canvas) && Game.takeMagicPower(50))
								map.addObject(new BlueTower(x, y));
							break;
						case buildDamageTrap:
							if (canPlaceTrap(x,y,canvas) && Game.takeMagicPower(20))
								map.addObject(new DamageTrap(x, y));
							break;
						case buildSlowTrap:
							if (canPlaceTrap(x,y,canvas) && Game.takeMagicPower(20))
								map.addObject(new SlowTrap(x, y));
							break;
						case useRedCrystal:
							break;
						case useBlueCrystal:
							break;
						case useGreenCrystal:
							break;
						case upgradeTower:
							break;
						case noCommand:
							break;
					}
				}
				else if(SwingUtilities.isRightMouseButton(e)) {
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
	
	public boolean canPlaceTower(double x, double y, final Canvas canvas){
		boolean canplace = true;
		
		for (int i=-22; i < 22 && canplace; i++)
			for (int j=-44; j < 0 && canplace; j++)
				canplace = !canvas.isOnRoad(x+i, y+j);
				
		List<GameObject> objects = Game.getMap().getObjects();
		RedTower test = new RedTower(x,y);			//Distance ellen�rz�shez kell csin�lni egy teszt tornyot
		
		for (int i=0; i < objects.size() && canplace; i++){
			if (objects.get(i).isTower() && test.getDistance(objects.get(i)) < 44)
				canplace = false;
		}
		
		return canplace;
	}
	
	
	public boolean canPlaceTrap(double x, double y, final Canvas canvas){
		boolean canplace = true;
		
		for (int i=-5; i < 5 && canplace; i++)
			for (int j=-5; j < 0 && canplace; j++)
				canplace = canvas.isOnRoad(x+i, y+j-5);
				
		List<GameObject> objects = Game.getMap().getObjects();
		RedTower test = new RedTower(x,y);			//Distance ellen�rz�shez kell csin�lni egy teszt tornyot
		
		for (int i=0; i < objects.size() && canplace; i++){
			if (objects.get(i).isTrap() && test.getDistance(objects.get(i)) < 52)
				canplace = false;
		}
		
		return canplace;
	}
	
	
	public Command getLastCommand() {
		return lastCommand;
	}
	
	
	public void resetLastCommand() {
		this.lastCommand = Command.noCommand;
	}
}
