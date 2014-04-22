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
	private static Game currentGame; // aktu�lis j�t�k
	
	// random seed
	private static Random rndGen = new Random();
	// direkt priv�t, k�rlek ne m�dos�ts�tok, magyar�zat�rt engem k�rdezzetek (M�t�)
	
	private final GameMap gameMap;   // referencia a mapre
	private final Saruman saruman;
	
	private boolean random; // v�letlenszer�s�g be van-e kapcsolva
	private int nonRandomNextInt; // ha nem, ez adja meg a k�vetkez� int-et
	private boolean nonRandomNextBoolean; // ha nem, ez adja meg a k�vetkez� boolean-t
	
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
		gameMap = new GameMap();
		saruman = new Saruman();
		
		random = true;
		nonRandomNextInt = 0;
		nonRandomNextBoolean = false;
		
		objectCount = 0;
		effectCount = 0;
		nodeCount = 0;
	}
	
	// j�t�k mapj�nek lek�r�se
	public static GameMap getMap() {
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
	
	// random vagy el�re specifik�lt eg�sz sz�m
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
	
	// random vagy el�re specifik�lt boolean �rt�k
	// param�ter: true es�lye (0 = nincs es�ly, 1 = biztos)
	public static boolean nextBoolean(double chanceOfTrue) {
		if (!currentGame.random) {
			return currentGame.nonRandomNextBoolean;
		}
		
		chanceOfTrue = Math.max(0.0, chanceOfTrue);
		chanceOfTrue = Math.min(chanceOfTrue, 1.0);
		
		return rndGen.nextDouble() <= chanceOfTrue;
	}
	
	// be van-e kapcsolva a v�letlenszer�s�g
	public static boolean isRandom() {
		return currentGame.random;
	}
	
	// v�letlenszer�s�g be�ll�t�sa
	public static void setRandom(boolean random) {
		currentGame.random = random;
	}
	
	// k�vetkez� (nem v�letlen) int be�ll�t�sa
	public static void setNonRandomNextInt(int n) {
		currentGame.nonRandomNextInt = n;
	}
	
	// k�vetkez� (nem v�letlen) boolean be�ll�t�sa
	public static void setNonRandomNextBoolean(boolean b) {
		currentGame.nonRandomNextBoolean = b;
	}
}
