package tower_defense;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 7817024087343251889L;
	private Canvas canvas;
	private GUI gui;
	
	private long lastTimeCheck;
	private long refreshInterval;
	private long tickInterval;
	private long timeOfLastRefresh;
	private long timeOfLastTick;
	
	public GameWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tower defense");
		setMinimumSize(new Dimension(1024, 768));
		initComponents();
	}
	
	private void initComponents() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Játék");
		menuBar.add(gameMenu);
		
		JMenuItem newGameMenuItem = new JMenuItem("Új játék");
		newGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
		gameMenu.add(newGameMenuItem);
		
		JMenuItem loadGameMenuItem = new JMenuItem("Játék betöltése");
		loadGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
		gameMenu.add(loadGameMenuItem);
		
		JMenuItem saveGameMenuItem = new JMenuItem("Játék mentése");
		saveGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
		gameMenu.add(saveGameMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Kilépés");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
		gameMenu.add(exitMenuItem);
		
		canvas = new Canvas();
		this.add(canvas, BorderLayout.CENTER);
		
		gui = new GUI();
		this.add(gui, BorderLayout.PAGE_END);
	}
	
	private void count() {
		
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
