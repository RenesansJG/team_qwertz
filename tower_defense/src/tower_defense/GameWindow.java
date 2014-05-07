package tower_defense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameWindow extends JFrame {
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
	
	private static final long serialVersionUID = 7817024087343251889L;
	private Container container = new Container(1024, 768);

	private long frameRate = 20;
	
	public GameWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tower defense");
		setResizable(false);
		this.add(container);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Játék");
		menuBar.add(gameMenu);
		
		JMenuItem newGameMenuItem = new JMenuItem("Új játék");
		newGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.newGame();
				container.gameLoaded();
			}});
		gameMenu.add(newGameMenuItem);
		
		JMenuItem loadGameMenuItem = new JMenuItem("Játék betöltése");
		loadGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setSelectedFile(new File("fileToLoad.tds"));
				jFileChooser.showOpenDialog(container);
				File file = jFileChooser.getSelectedFile();
				if(file != null) {
					Game.loadGame(file.getAbsolutePath());
					container.gameLoaded();
				}
			}});
		gameMenu.add(loadGameMenuItem);
		
		JMenuItem saveGameMenuItem = new JMenuItem("Játék mentése");
		saveGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setSelectedFile(new File("fileToSave.tds"));
				jFileChooser.showSaveDialog(container);
				File file = jFileChooser.getSelectedFile();
				Game.saveGame(file.getAbsolutePath());
			}});
		gameMenu.add(saveGameMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Kilépés");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
		gameMenu.add(exitMenuItem);
		pack();
		new Timer((int) frameRate, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow.this.repaint();
			}
		}).start();
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
