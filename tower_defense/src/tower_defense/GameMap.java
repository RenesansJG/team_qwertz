package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -2497706162553103752L;
	private final List<Node> nodes;         // gyökér node-ok listája
	private final List<GameObject> objects; // objektumok listája
	
	// map konstruktor
	public GameMap() {
		Console.println("new GameMap()");
		Console.indent();
		
		nodes = new ArrayList<Node>();
		objects = new ArrayList<GameObject>();
		
		Console.deIndent();
	}
	
	// az összes gyökér node listájának lekérése
	public List<Node> getNodes() {
		Console.println(this + ".getNodes()");
		
		return Collections.unmodifiableList(nodes);
	}
	
	// node hozzáadása a maphez
	public void addNode(Node node) {
		Console.println(this + ".addNode(" + node + ")");
		
		nodes.add(node);
	}
	
	// az összes objektum listájának lekérése
	public List<GameObject> getObjects() {
		Console.println(this + ".getObjects()");
		
		return Collections.unmodifiableList(objects);
	}
	
	// objektum hozzáadása a maphez
	public void addObject(GameObject object) {
		Console.println(this + ".addObject(" + object + ")");
		
		objects.add(object);
	}
	
	// objektum eltávolítása a maprõl
	public boolean removeObject(GameObject object) {
		Console.println(this + ".removeObject(" + object + ")");
		
		return objects.remove(object);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "gameMap";
	}
}
