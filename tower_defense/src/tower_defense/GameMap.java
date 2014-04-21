package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -2497706162553103752L;
	private final List<Node> nodes;         // gy�k�r node-ok list�ja
	private final List<GameObject> objects; // objektumok list�ja
	
	public static final int maxX = 1000;
	public static final int maxY = 1000;
	
	// map konstruktor
	public GameMap() {
		nodes = new ArrayList<Node>();
		objects = new ArrayList<GameObject>();
	}
	
	// az �sszes gy�k�r node list�j�nak lek�r�se
	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}
	
	// node hozz�ad�sa a maphez
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	// az �sszes objektum list�j�nak lek�r�se
	public List<GameObject> getObjects() {
		return Collections.unmodifiableList(objects);
	}
	
	// objektum hozz�ad�sa a maphez
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	// objektum elt�vol�t�sa a mapr�l
	public boolean removeObject(GameObject object) {
		return objects.remove(object);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "gameMap";
	}
}
