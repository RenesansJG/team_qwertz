package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Serializable {
	private static final long serialVersionUID = 6423495891562787370L;
	private final List<Node> nextNodes;
	
	public Node() {
		nextNodes = new ArrayList<Node>();
	}
	
	public List<Node> getNextNodes() {
		return Collections.unmodifiableList(nextNodes);
	}
}
