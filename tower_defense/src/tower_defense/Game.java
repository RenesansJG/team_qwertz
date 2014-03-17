package tower_defense;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = -2262641899325571201L;
	private static Game currentGame;
	private final GameMap gameMap;
	
	private Game() {
		Console.println("new Game()");
		Console.indent();
		
		gameMap = new GameMap();
		
		Console.deIndent();
	}
	
	// játék mapjének lekérése
	public static GameMap getMap() {
		Console.println("Game.getMap()");
		
		return currentGame.gameMap;
	}
	
	// új játék indítása
	public static void newGame() {
		Console.println("Game.newGame()");
		
		currentGame = new Game();
	}
	
	// játék betöltése fájlból
	public static boolean loadGame(String file) {
		Console.println("Game.loadGame(" + file + ")");
		
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
		Console.println("Game.saveGame(" + file + ")");
		
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
