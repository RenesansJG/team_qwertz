package tower_defense;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
			"redTower",
			"greenTower",
			"blueTower",
			"redCrystalEffect",
			"greenCrystalEffect",
			"blueCrystalEffect",
			"blueCrystalEffect",
			"slowEffect",
			"elf",
			"dwarf",
			"human",
			"hobbit",
			"damageTrap",
			"slowTrap",
			"fog",
			"cannonBall",
			"upgrade"
	};
	private Color roadColor = new Color(60,25,0);
	private Color grassColor = new Color(15,115,0);
	
	public Canvas(int width, int height) {
		try {
			sprites.put("error", ImageIO.read(new File("src/pic/" + "error" + ".bmp")));
		} catch(IOException e) {
			sprites.put("error", new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
			System.exit(0);
		}
		for(String filename : imageFilenames) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("src/pic/" + filename + ".bmp"));
			} catch (IOException e) {
				BufferedImage errorImage = sprites.get("error");
				ColorModel cm = errorImage.getColorModel();
				boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
				WritableRaster raster = errorImage.copyData(null);
				img = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
				Graphics g = img.getGraphics();
				g.drawImage(errorImage, 0, 0, this);
				g.setColor(Color.black);
				g.drawString(filename, 0, 0);//TODO nem �r�dik az error image-re a f�jln�v
			} finally {
				img = TransformToTransparent(img);
				sprites.put(filename, img);
			}
		}
		
		this.setPreferredSize(new Dimension(width, height));
		backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Point point = MouseInfo.getPointerInfo().getLocation();
		mouseX = point.getX();
		mouseY = point.getY();
	}
	
	public boolean isOnRoad(double x, double y){
		int color = backgroundImage.getRGB((int) x, (int) y);

		int  red = (color & 0x00ff0000) >> 16;
		int  green = (color & 0x0000ff00) >> 8;
		int  blue = color & 0x000000ff;
		
		return (roadColor.getRed() == red &&
				roadColor.getGreen() == green &&
				roadColor.getBlue() == blue);
	}
	
	private BufferedImage TransformToTransparent(BufferedImage image)
	{
		ImageFilter filter = new RGBImageFilter() {
			public final int filterRGB(int x, int y, int rgb) {
				if(rgb == 0xffff008A)
					return 0x00000000;
				return rgb;
			}
		};
		
	    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
	    Image img = Toolkit.getDefaultToolkit().createImage(ip);
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}
	
	public void drawBackground() {
		Graphics bgg = backgroundImage.getGraphics();
		
		bgg.setColor(grassColor);
		bgg.fillRect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
		
		/*try {
			bgg.drawImage(ImageIO.read(new File("src/pic/" + "map.jpg")), 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		List<Node> startingNodes = Game.getMap().getNodes();
		for(Node n : startingNodes) {
			drawRoad(n);
		} 
	}
	
	private void drawRoad(Node node) {
		List<Node> nodeList = node.getNextNodes();
		Graphics2D g2d = (Graphics2D) backgroundImage.getGraphics();
		g2d.setColor(roadColor);
		g2d.setStroke(new BasicStroke(25));
		for(Node childNode : nodeList) {
			g2d.drawLine((int)node.getX(), (int)node.getY(), (int)childNode.getX(), (int)childNode.getY());
			drawRoad(childNode);
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
		g.drawImage(backgroundImage, 0, 0, this);
		if(Game.getMap() != null) {
			List<GameObject> goList = Game.getMap().getObjects();
			Collections.sort(goList, new ObjectViewComparator());
			for(GameObject go : goList) {
				BufferedImage bi = getImage(go);
				double biOffsetX = - bi.getWidth()/2;
				double biOffsetY = - bi.getHeight();
				g.drawImage(bi, (int)(go.getX() + biOffsetX), (int)(go.getY() + biOffsetY), this);
				List<Effect> effects = go.getEffects();
				int effectsOffsetX = 0;
				for(Effect effect : effects) {
					effectsOffsetX += getImage(effect).getWidth();
				}
				effectsOffsetX /= -2;
				for(Effect effect : effects) {
					BufferedImage bie = getImage(effect);
					double bieOffsetX = effectsOffsetX;
					double bieOffsetY = - bie.getHeight() + biOffsetY;
					g.drawImage(bie, (int)(go.getX() + bieOffsetX), (int)(go.getY() + bieOffsetY), this);
					effectsOffsetX += bie.getWidth();
				}
			}
			BufferedImage commandImage = getCommandImage();
			if(commandImage != null) {
				g.drawImage(commandImage,
						(int)(mouseX - commandImage.getWidth()/2),
						(int)(mouseY - commandImage.getHeight()),
						this);
			} else {
				g.drawString(gui.getLastCommand().toString(), (int)mouseX, (int)mouseY);
			}
			g.drawString(Double.toString(Game.getMagicPower()), 0, 20);
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
				return sprites.get("redCrystalEffect");
			case useBlueCrystal:
				return sprites.get("blueCrystalEffect");
			case useGreenCrystal:
				return sprites.get("greenCrystalEffect");
			case upgradeTower:
				return sprites.get("upgrade");
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
			return sprites.get("redCrystalEffect");
		}
		else if(go instanceof GreenCrystalEffect) {
			return sprites.get("greenCrystalEffect");
		}
		else if(go instanceof BlueCrystalEffect) {
			return sprites.get("blueCrystalEffect");
		}
		else if(go instanceof SlowEffect) {
			return sprites.get("slowEffect");
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
		else if(go instanceof Projectile) {
			return sprites.get("cannonBall");
		}
		return sprites.get("error");
	}
}

class ObjectViewComparator implements Comparator<GameObject> {
	@Override
	public int compare(GameObject a, GameObject b) {
		double ay = a.getY();
		double by = b.getY();
		if(ay < by)
			return -1;
		if(ay > by)
			return  1;
		return 0;
	}
}
