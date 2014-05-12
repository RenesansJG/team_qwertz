package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Serializable {
	private static final long serialVersionUID = 6423495891562787370L;
	
	private final double x;
	private final double y;
	private final List<Node> nextNodes; // következõ node-ok listája
	
	public Node(double x, double y) {
		this.x=x;
		this.y=y;
		nextNodes = new ArrayList<Node>();
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	// lekéri a következõ node-okat
	public List<Node> getNextNodes() {
		// módosíthatatlan verzióban adjuk vissza a listát
		return Collections.unmodifiableList(nextNodes);
	}
	
	//Hozzáad egyet a következõ nodesekhez
	public void addNextNode(Node n){
		nextNodes.add(n);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "node";
	}
}
