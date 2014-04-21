package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -2497706162553103752L;
	private final List<Node> nodes;         // gyökér node-ok listája
	private final List<GameObject> objects; // objektumok listája
	
	public static final int maxX = 1000;
	public static final int maxY = 1000;
	
	// map konstruktor
	public GameMap() {
		nodes = new ArrayList<Node>();
		objects = new ArrayList<GameObject>();
	}
	
	// az összes gyökér node listájának lekérése
	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}
	
	// node hozzáadása a maphez
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	// az összes objektum listájának lekérése
	public List<GameObject> getObjects() {
		return Collections.unmodifiableList(objects);
	}
	
	// objektum hozzáadása a maphez
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	// objektum eltávolítása a maprõl
	public boolean removeObject(GameObject object) {
		return objects.remove(object);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "gameMap";
	}
}
