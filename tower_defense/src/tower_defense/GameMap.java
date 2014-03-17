package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -2497706162553103752L;
	private final List<Node> nodes;         // node-ok listája
	private final Map<Integer, GameObject> objects; // objektumok mapje, a kulcs az objektum id-je
	
	// map konstruktor
	public GameMap() {
		Console.println("new GameMap()");
		
		nodes = new ArrayList<Node>();
		objects = new HashMap<Integer, GameObject>();
	}
	
	// node hozzáadása a maphez
	public void addNode(Node node) {
		Console.println(this + ".addNode(" + node + ")");
		
		nodes.add(node);
	}
	
	// az összes objektum listájának lekérése
	public Collection<GameObject> getObjects() {
		Console.println(this + ".getObjects()");
		
		return Collections.unmodifiableCollection(objects.values());
	}
	
	// objektum hozzáadása a maphez
	public void addObject(GameObject object) {
		Console.println(this + ".addObject(" + object + ")");
		
		objects.put(object.id, object);
	}
	
	// objektum eltávolítása a maprõl
	public boolean removeObject(GameObject object) {
		Console.println(this + ".removeObject(" + object + ")");
		
		return objects.remove(object.id) != null;
	}
	
	// adott id-jû objektum eltávolítása a maprõl
	public boolean removeObject(int id) {
		Console.println(this + ".removeObject(" + id + ")");
		
		return objects.remove(id) != null;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "gameMap";
	}
}
