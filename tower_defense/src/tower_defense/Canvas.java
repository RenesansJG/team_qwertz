package tower_defense;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = -4651859705730816296L;
	
	private GUI gui;
	BufferedImage backgroundImage;
	private double mouseX;
	private double mouseY;
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	private String[] imageFilenames = {
			"edTower",
			"greenTower",
			"blueTower",
			"redCrystal",
			"greenCrystal",
			"blueCrystal",
			"elf",
			"dwarf",
			"human",
			"hobbit",
			"damageTrap",
			"slowTrap",
			"fog"
	};
	
	public Canvas(int width, int height) {
		try {
			sprites.put("error", ImageIO.read(new File("src/pic/" + "error" + ".bmp")));
		} catch(IOException e) {
			sprites.put("error", new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
			System.exit(0);
		}
		for(String filename : imageFilenames) {
			try {
				BufferedImage img = ImageIO.read(new File("src/pic/" + filename + ".bmp"));
				if(img == null) System.exit(0);
				sprites.put(filename, img);
			} catch (IOException e) {
				BufferedImage errorImage = sprites.get("error");
				ColorModel cm = errorImage.getColorModel();
				boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
				WritableRaster raster = errorImage.copyData(null);
				BufferedImage bi = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
				Graphics g = bi.getGraphics();
				g.drawImage(errorImage, 0, 0, this);
				sprites.put(filename, bi);
			}
		}
		
		this.setPreferredSize(new Dimension(width, height));
		backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Point point = MouseInfo.getPointerInfo().getLocation();
		mouseX = point.getX();
		mouseY = point.getY();
	}
	
	public void drawBackground() {
		Graphics bgg = backgroundImage.getGraphics();
		bgg.setColor(Color.green);
		bgg.fillRect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
		List<Node> startingNodes = Game.getMap().getNodes();
		for(Node n : startingNodes) {
			drawRoad(n);
		}
	}
	
	private void drawRoad(Node node) {
		List<Node> nodeList = node.getNextNodes();
		Graphics2D g2d = (Graphics2D) backgroundImage.getGraphics();
		g2d.setColor(Color.yellow);
		g2d.setStroke(new BasicStroke(3));
		for(Node childNode : nodeList) {
			g2d.drawLine((int)node.getX(), (int)node.getY(), (int)childNode.getX(), (int)childNode.getY());
		}
	}
	
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	public void setMosueX(double value) {
		this.mouseX = value;
	}
	
	public void setMosueY(double value) {
		this.mouseY = value;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, this.getHeight() - gui.getHeight(), this.getWidth(), this.getHeight());
		BufferedImage commandImage = getCommandImage();
		if(commandImage != null) {
			g.drawImage(commandImage,
					(int)(mouseX - commandImage.getWidth()/2),
					(int)(mouseY - commandImage.getHeight()),
					this);
		} else {
			g.drawString(gui.getLastCommand().toString(), (int)mouseX, (int)mouseY);
		}
	}
	
	private BufferedImage getCommandImage() {
		switch(gui.getLastCommand()) {
			case buildRedTower:
				return sprites.get("redTower");
			case buildGreenTower:
				return sprites.get("greenTower");
			case buildBlueTower:
				return sprites.get("blueTower");
			case buildDamageTrap:
				return sprites.get("damageTrap");
			case buildSlowTrap:
				return sprites.get("slowTrap");
			case useRedCrystal:
				return sprites.get("redCrystal");
			case useBlueCrystal:
				return sprites.get("greenCrystal");
			case useGreenCrystal:
				return sprites.get("blueCrystal");
			case upgradeTower:
				return null;
			default:
				return null;
		}
	}
	
	private BufferedImage getImage(Object go) {
		if(go instanceof RedTower) {
			return sprites.get("redTower");
		}
		else if(go instanceof GreenTower) {
			return sprites.get("greenTower");
		}
		else if(go instanceof BlueTower) {
			return sprites.get("blueTower");
		}
		else if(go instanceof RedCrystalEffect) {
			return sprites.get("redCrystal");
		}
		else if(go instanceof GreenCrystalEffect) {
			return sprites.get("greenCrystal");
		}
		else if(go instanceof BlueCrystalEffect) {
			return sprites.get("blueCrystal");
		}
		else if(go instanceof Elf) {
			return sprites.get("elf");
		}
		else if(go instanceof Dwarf) {
			return sprites.get("dwarf");
		}
		else if(go instanceof Human) {
			return sprites.get("human");
		}
		else if(go instanceof Hobbit) {
			return sprites.get("hobbit");
		}
		else if(go instanceof DamageTrap) {
			return sprites.get("damageTrap");
		}
		else if(go instanceof SlowTrap) {
			return sprites.get("slowTrap");
		}
		else if(go instanceof FogEffect) {
			return sprites.get("fog");
		}
		return sprites.get("error");
	}
}
