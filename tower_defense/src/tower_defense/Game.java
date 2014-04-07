package tower_defense;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = -2262641899325571201L;
	private static Game currentGame; // aktuális játék
	private final GameMap gameMap;   // referencia a mapre
	
	// *** darabszámok szkelentonhoz ***
	private int objectCount;
	private int effectCount;
	private int nodeCount;
	
	public static int getNextObjectId() {
		return currentGame.objectCount++;
	}
	
	public static int getNextEffectId() {
		return currentGame.effectCount++;
	}
	
	public static int getNextNodeId() {
		return currentGame.nodeCount++;
	}
	// *********************************
	
	// játék konstruktor
	private Game() {
		Console.println("new Game()");
		Console.indent();
		
		gameMap = new GameMap();
		
		objectCount = 0;
		effectCount = 0;
		nodeCount = 0;
		
		Console.deIndent();
	}
	
	// játék mapjének lekérése
	public static GameMap getMap() {
		Console.println("Game.getMap()");
		
		return currentGame.gameMap;
	}
	
	// új játék indítása
	public static void newGame() {
		
		currentGame = new Game();
		
		//Game.getMap().addNode(new Node());
	}
	
	// játék betöltése fájlból
	public static boolean loadGame(String file) {
		// szerializálást használ
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			try {
				currentGame = (Game)ois.readObject();
			} catch (ClassNotFoundException e) {
				ois.close();
				return false;
			}
			
			ois.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	// játék mentése fájlba
	public static boolean saveGame(String file) {
		// szerializálást használ
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(currentGame);
			oos.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
