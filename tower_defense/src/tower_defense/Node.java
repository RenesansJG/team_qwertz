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
	private final List<Node> nextNodes; // következõ node-ok listája
	
	public Node() {
		Console.println("new Node()");
		id = count++;
		
		nextNodes = new ArrayList<Node>();
	}
	
	// lekéri a következõ node-okat
	public List<Node> getNextNodes() {
		Console.println(this + ".getNextNodes()");
		// módosíthatatlan verzióban adjuk vissza a listát
		return Collections.unmodifiableList(nextNodes);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "node#" + id;
	}
}
