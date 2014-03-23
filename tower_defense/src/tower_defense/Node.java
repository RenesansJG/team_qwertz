package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Serializable {
	private static final long serialVersionUID = 6423495891562787370L;
	private static int count;
	protected final int id;
	
	//private final double x;
	//private final double y;
	private final List<Node> nextNodes; // k�vetkez� node-ok list�ja
	
	public Node() {
		Console.println("new Node()");
		id = count++;
		
		nextNodes = new ArrayList<Node>();
	}
	
	// lek�ri a k�vetkez� node-okat
	public List<Node> getNextNodes() {
		Console.println(this + ".getNextNodes()");
		// m�dos�thatatlan verzi�ban adjuk vissza a list�t
		return Collections.unmodifiableList(nextNodes);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "node#" + id;
	}
}
