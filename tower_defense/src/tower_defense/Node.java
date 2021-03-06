package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Serializable {
	private static final long serialVersionUID = 6423495891562787370L;
	
	private final double x;
	private final double y;
	private final List<Node> nextNodes; // k�vetkez� node-ok list�ja
	
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
	
	// lek�ri a k�vetkez� node-okat
	public List<Node> getNextNodes() {
		// m�dos�thatatlan verzi�ban adjuk vissza a list�t
		return Collections.unmodifiableList(nextNodes);
	}
	
	//Hozz�ad egyet a k�vetkez� nodesekhez
	public void addNextNode(Node n){
		nextNodes.add(n);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "node";
	}
}
