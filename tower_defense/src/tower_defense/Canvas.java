package tower_defense;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = -4651859705730816296L;
	
	private GUI gui;
	BufferedImage backgroundImage;
	private double mouseX;
	private double mouseY;
	
	public Canvas(int width, int height) {
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
		g.fillRect(0, this.getHeight()-40, this.getWidth(), this.getHeight());
		GUI.Command command = this.gui.getLastCommand();
		g.drawString(command.toString(), (int)mouseX, (int)mouseY);
	}
}
