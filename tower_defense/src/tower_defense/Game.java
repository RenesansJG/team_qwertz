package tower_defense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

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
	
	// *** darabsz�mok protohoz ***
	private int objectCount;
	private int effectCount;
	private int nodeCount = 0;
	
	private long timeOfLastTick;
	private long tickInterval = 20;
	private static long ticks = 0;
	
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
		saruman.setMagicPower(250);
		
		random = true;
		nonRandomNextInt = 0;
		nonRandomNextBoolean = false;
		
		objectCount = 0;
		effectCount = 0;
		nodeCount = 0;
		
		this.timeOfLastTick = System.currentTimeMillis();
		new Timer((int) tickInterval, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.this.count();
				Game.this.spawn();
			}
		}).start();
	}
	
	private void spawn(){
		if(ticks%500==499){
			gameMap.addObject(new Elf(gameMap.getNodes().get(rndGen.nextInt(2)),1));
		}
	}
	
	private void count() {
		long currentTime = System.currentTimeMillis();
		long delta;
		delta = currentTime - timeOfLastTick;
		if(delta >= tickInterval) {
			do {
				Game.applyTicks();
				delta -= tickInterval;
				ticks++;
			} while(delta >= tickInterval);
			timeOfLastTick = currentTime;
		}
	}
	
	// j�t�k mapj�nek lek�r�se
	public static GameMap getMap() {
		if(currentGame == null)
			return null;
		return currentGame.gameMap;
	}
	
	// �j j�t�k ind�t�sa
	public static void newGame() {
		currentGame = new Game();
		
		lost=false;
		
		//T�RK�P L�TREHOZ�SA
	//Kezd� Nodek
		Node s1 = new Node(0,500);
		Node s2 = new Node(200,0);
		
	//K�ztes Nodek
		
		//Als� f��t
		Node n1 = new Node(50,500);
		Node n2 = new Node(200,500);
		Node n3 = new Node(300,400);
		Node n4 = new Node(300,600);
		Node n5 = new Node(500,400);
		Node n6 = new Node(500,600);
		Node n7 = new Node(600,500);
		
		//Join
		Node n8 = new Node(700,500);
		//Join 2
		Node n9 = new Node(900,500);
		
		//K�z�ps� f��t
		Node n10 = new Node(100,250);
		Node n11 = new Node(500,250);
		//Join
		Node n12 = new Node(700,300);
		
		//Fels� f��t
		Node n13 = new Node(250,100);
		Node n14 = new Node(700,100);
		Node n15 = new Node(800,100);
				
	//Finish Node
		Node f1 = new Node(1024,500);
		
	//T�rk�p l�trehoz�sa
		s1.addNextNode(n1);
		s2.addNextNode(n13);
		
		//Als� �t
		n1.addNextNode(n2);
		n1.addNextNode(n10);
		n2.addNextNode(n3);
		n2.addNextNode(n4);
		n3.addNextNode(n5);
		n4.addNextNode(n6);
		n5.addNextNode(n7);
		n6.addNextNode(n7);
		n7.addNextNode(n8);
		n8.addNextNode(n9);		
		n9.addNextNode(f1);
		
		//K�z�ps� �t
		n10.addNextNode(n11);
		n11.addNextNode(n12);
		n12.addNextNode(n8);
		
		//Fels� �t
		n13.addNextNode(n14);
		n14.addNextNode(n15);
		n14.addNextNode(n12);
		n15.addNextNode(n9);
		
		
		Game.getMap().addNode(s1);		//Startpontok hozz�ad�sa
		Game.getMap().addNode(s2);
		
		ticks = 0;
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
	
	// var�zser� lek�rdez�se
	public static double getMagicPower() {
		return currentGame.saruman.getMagicPower();
	}
	
	// var�zser� hozz�ad�sa
	public static void addMagicPower(double power) {
		currentGame.saruman.setMagicPower(currentGame.saruman.getMagicPower() + power);
	}
	
	public static boolean takeMagicPower(double power){
		double mana = currentGame.saruman.getMagicPower();
		
		if (mana - power >= 0){
			currentGame.saruman.setMagicPower(mana-power);
			return true;
		}
		else return false;
	
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
	
	// nem random k�vetkez� int lek�rdez�se
	public static int getNonRandomNextInt() {
		return currentGame.nonRandomNextInt;
	}
	
	// nem random k�vetkez� boolean lek�rdez�se
	public static boolean getNonRandomNextBoolean() {
		return currentGame.nonRandomNextBoolean;
	}
	
	// k�vetkez� (nem v�letlen) int be�ll�t�sa
	public static void setNonRandomNextInt(int n) {
		currentGame.nonRandomNextInt = n;
	}
	
	// k�vetkez� (nem v�letlen) boolean be�ll�t�sa
	public static void setNonRandomNextBoolean(boolean b) {
		currentGame.nonRandomNextBoolean = b;
	}
	
	public static boolean lost;
	
	public static void loseGame()
	{
		lost=true;
	}
	
	public static void applyTicks() {
		GameMap map = Game.getMap();
		List<GameObject> deadmanList = new ArrayList<GameObject>();
		for(GameObject go : map.getObjects()) {
			if(go.applyTick()) {
				deadmanList.add(go);
			}
		}
		for(GameObject go : deadmanList) {
			map.removeObject(go);
		}
		
		if(Game.lost) {
			loseGame();
		}
	}
			
	
}
