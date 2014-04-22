package tower_defense;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
	private static final long serialVersionUID = -2262641899325571201L;
	private static Game currentGame; // aktuális játék
	
	// random seed
	private static Random rndGen = new Random();
	// direkt privát, kérlek ne módosítsátok, magyarázatért engem kérdezzetek (Máté)
	
	private final GameMap gameMap;   // referencia a mapre
	private final Saruman saruman;
	
	private boolean random; // véletlenszerûség be van-e kapcsolva
	private int nonRandomNextInt; // ha nem, ez adja meg a következõ int-et
	private boolean nonRandomNextBoolean; // ha nem, ez adja meg a következõ boolean-t
	
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
		gameMap = new GameMap();
		saruman = new Saruman();
		
		random = true;
		nonRandomNextInt = 0;
		nonRandomNextBoolean = false;
		
		objectCount = 0;
		effectCount = 0;
		nodeCount = 0;
	}
	
	// játék mapjének lekérése
	public static GameMap getMap() {
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
	
	// random vagy elõre specifikált egész szám
	public static int nextInt(int n) {
		if (!currentGame.random) {
			int nonRandomNextInt = currentGame.nonRandomNextInt;
			nonRandomNextInt = Math.max(0, nonRandomNextInt);
			nonRandomNextInt = Math.min(nonRandomNextInt, n);
			
			return nonRandomNextInt;
		} else {
			return rndGen.nextInt(n);
		}
	}
	
	// random vagy elõre specifikált boolean érték
	// paraméter: true esélye (0 = nincs esély, 1 = biztos)
	public static boolean nextBoolean(double chanceOfTrue) {
		if (!currentGame.random) {
			return currentGame.nonRandomNextBoolean;
		}
		
		chanceOfTrue = Math.max(0.0, chanceOfTrue);
		chanceOfTrue = Math.min(chanceOfTrue, 1.0);
		
		return rndGen.nextDouble() <= chanceOfTrue;
	}
	
	// be van-e kapcsolva a véletlenszerûség
	public static boolean isRandom() {
		return currentGame.random;
	}
	
	// véletlenszerûség beállítása
	public static void setRandom(boolean random) {
		currentGame.random = random;
	}
	
	// következõ (nem véletlen) int beállítása
	public static void setNonRandomNextInt(int n) {
		currentGame.nonRandomNextInt = n;
	}
	
	// következõ (nem véletlen) boolean beállítása
	public static void setNonRandomNextBoolean(boolean b) {
		currentGame.nonRandomNextBoolean = b;
	}
}
