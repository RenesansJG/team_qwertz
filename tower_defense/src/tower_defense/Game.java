package tower_defense;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = -2262641899325571201L;
	private static Game currentGame; // aktu�lis j�t�k
	private final GameMap gameMap;   // referencia a mapre
	
	// *** darabsz�mok szkelentonhoz ***
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
	
	// j�t�k konstruktor
	private Game() {
		Console.println("new Game()");
		Console.indent();
		
		gameMap = new GameMap();
		
		objectCount = 0;
		effectCount = 0;
		nodeCount = 0;
		
		Console.deIndent();
	}
	
	// j�t�k mapj�nek lek�r�se
	public static GameMap getMap() {
		Console.println("Game.getMap()");
		
		return currentGame.gameMap;
	}
	
	// �j j�t�k ind�t�sa
	public static void newGame() {
		
		currentGame = new Game();
		
		//Game.getMap().addNode(new Node());
	}
	
	// j�t�k bet�lt�se f�jlb�l
	public static boolean loadGame(String file) {
		// szerializ�l�st haszn�l
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
	
	// j�t�k ment�se f�jlba
	public static boolean saveGame(String file) {
		// szerializ�l�st haszn�l
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
