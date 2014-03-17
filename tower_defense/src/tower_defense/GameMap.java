package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GameMap implements Serializable {
	private static final long serialVersionUID = -2497706162553103752L;
	private final List<Node> nodes;         // node-ok list�ja
	private final List<GameObject> objects; // objektumok list�ja
	
	// map konstruktor
	public GameMap() {
		Console.println("new GameMap()");
		
		nodes = new ArrayList<Node>();
		objects = new ArrayList<GameObject>();
	}
	
	// node hozz�ad�sa a maphez
	public void addNode(Node node) {
		Console.println(this + ".addNode(" + node + ")");
		
		nodes.add(node);
	}
	
	// az �sszes objektum list�j�nak lek�r�se
	public List<GameObject> getObjects() {
		Console.println(this + ".getObjects()");
		
		return Collections.unmodifiableList(objects);
	}
	
	// objektum hozz�ad�sa a maphez
	public void addObject(GameObject object) {
		Console.println(this + ".addObject(" + object + ")");
		
		objects.add(object);
	}
	
	// objektum elt�vol�t�sa a mapr�l
	public boolean removeObject(GameObject object) {
		Console.println(this + ".removeObject(" + object + ")");
		
		return objects.remove(object);
	}
	
	// adott id-j� objektum elt�vol�t�sa a mapr�l
	public boolean removeObject(int id) {
		Console.println(this + ".removeObject(" + id + ")");
		
		Iterator<GameObject> iter = objects.iterator();
		
		while (iter.hasNext()) {
			GameObject object = iter.next();
			
			if (object.id == id) {
				iter.remove();
				return true;
			}
		}
		
		return false;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "gameMap";
	}
}
