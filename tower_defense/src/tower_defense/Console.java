package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Console {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final Deque<String> commands = new ArrayDeque<String>();
	private static int indent = 0;
	
	// teljes sor ki�r�sa elej�n tabokkal
	public static void println(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.println(o);
	}
	
	// �res sor ki�r�sa
	public static void println() {
		System.out.println();
	}
	
	// ki�r�s elej�n tabokkal
	public static void printi(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.print(o);
	}
	
	// ki�r�s tabok n�lk�l
	public static void print(Object o) {
		System.out.print(o);
	}
	
	// felhaszn�l�nak sz�l� �zenet ki�r�sa (teljes sor, elej�n tabokkal)
	public static void printlnMsg(Object o) {
		if (commands.isEmpty()) {
			for (int i = 0; i < indent; i++) {
				System.out.print("  ");
			}
			
			System.out.println(o);
		}
	}
	
	// felhaszn�l�nak sz�l� �zenet ki�r�sa (elej�n tabokkal)
	public static void printMsg(Object o) {
		if (commands.isEmpty()) {
			for (int i = 0; i < indent; i++) {
				System.out.print("  ");
			}
			
			System.out.print(o);
		}
	}
	
	// eggyel beljebb kezd�s (+tab)
	public static void indent() {
		indent++;
	}
	
	// eggyel kijjeb kezd�s (-tab)
	public static void deIndent() {
		if (indent > 0)
			indent--;
	}
	
	// egy eg�sz sz�m beolvas�sa
	public static int readInt() throws IOException {
		try {
			return Integer.parseInt(readLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// egy val�s sz�m beolvas�sa
	public static double readDouble() throws IOException {
		try {
			return Double.parseDouble(readLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// egy sor beolvas�sa
	public static String readLine() throws IOException {
		// ha van a commands sorban elem, azt olvassuk be
		String line = commands.poll();
		
		// ha nincs, a felhaszn�l�t�l k�r�nk egy sort
		if (line == null)
			line = br.readLine();
		
		return line;
	}
	
	// list�b�l v�laszt�s
	// param�terek: a lista elemei
	// visszat�r�s: hanyadik elemet v�lasztotta a felhaszn�l� (0-t�l indexel�nk)
	public static int choose(String... choices) throws IOException {
		if (choices == null || choices.length == 0)
			return 0;
		
		for (int i = 0; i < choices.length; i++) {
			if (choices[i] != null) { // csak a l�tez� (nem null) v�laszt�sokat �rjuk ki
				printlnMsg((i + 1) + " = " + choices[i]);
			}
		}
		
		printMsg("V�lasz: ");
		
		int choice = readInt();
		
		// akkor j� a v�lasz, ha az -1 (ez a "vissza" parancs),
		// vagy 1 �s a v�laszok hossza k�z�tt van,
		// �s l�tezik is az adott v�lasz (a v�laszlista adott eleme nem null)
		while (choice != -1 && choice < 1 || choice > choices.length || choices[choice - 1] == null) {
			printMsg("Rossz v�lasz. V�lasz: ");
			choice = readInt();
		}
		
		return (choice != - 1) ? choice - 1 : -1;
	}
	
	// eld�ntend� k�rd�s
	// visszat�r�s: igaz, ha az igent v�lasztotta a felhaszn�l�,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		printlnMsg("1 = Igen");
		printlnMsg("2 = Nem");
		printMsg("V�lasz: ");
		
		int choice = readInt();
		
		while (choice != 1 && choice != 2) {
			printMsg("Rossz v�lasz. V�lasz: ");
			choice = readInt();
		}
		
		return choice == 1;
	}
	
	// objektum lek�r�se usert�l
	public static GameObject getObjectFromUser() throws IOException {
		GameObject object = null;
		
		do {
			printMsg("ID (-1 = vissza): ");
			int id = readInt();
			
			if (id == -1)
				return null;
			
			for (GameObject currObject : Game.getMap().getObjects()) {
				if (currObject.id == id) {
					object = currObject;
					break;
				}
			}
		} while (object == null);
		
		return object;
	}
	
	// main
	public static void main(String[] args) {
		try {
			// welcome �zenet
			printlnMsg("A k�t torony");
			printlnMsg("� 2014 team_qwertz");
			
			// inicializ�ci�
			init();
			
			while (true) {
				// f�men�
				int choice = choose(
						"�j j�t�k",
						"J�t�k ment�se",
						"J�t�k bet�lt�se",
						"Kil�p�s",
						"Objektumok list�z�sa",
						"J�t�k l�ptet�se",
						"Torony lerak�sa",
						"Akad�ly lerak�sa",
						"Ellens�g gener�l�sa",
						"Torony fejleszt�se",
						"Effekt alkalmaz�sa",
						"V�letlenszer�s�g " + (Game.isRandom() ? "ki" : "be"),
						"Tesztesetek");
				
				switch (choice) {
				case 0: // new game
					newGame();
					break;
				case 1: // save game
					saveGame();
					break;
				case 2: // load game
					loadGame();
					break;
				case 3: // exit game
					if (exit()) {
						return;
					}
					break;
				case 4: // list objects
					listObjects();
					break;
				case 5: // apply ticks
					applyTicks();
					break;
				case 6: // add tower
					addTower();
					break;
				case 7: // add trap
					addTrap();
					break;
				case 8: // add enemy
					addEnemy();
					break;
				case 9: // upgrade tower
					upgradeTower();
					break;
				case 10: // apply effect
					applyEffect();
					break;
				case 11: // random seed
					randomSeed();
					break;
				case 12: // tests
					test();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// inicializ�ci�
	public static void init() {
		// �j j�t�k ind�t�sa
		Game.newGame();
	}
	
	// add tower men�pont
	public static void addTower() throws IOException {
		GameMap map = Game.getMap();
		
		printlnMsg("Milyen torony legyen?");
		
		// bek�rj�k a torony t�pus�t
		int choice = choose("Piros", "Z�ld", "K�k");
		
		// ha -1, visszat�r�nk
		if (choice < 0)
			return;
		
		// bek�rj�k a koordin�t�kat
		double x = Console.readDouble();
		double y = Console.readDouble();
		
		// ha �rv�nytelenek a koordin�t�k, kil�p�nk
		if (x < 0 || x > GameMap.maxX || y < 0 || y > GameMap.maxY) {
			println("Torony lerak�sa sikertelen: �rv�nytelen poz�ci�.");
			return;
		}
		
		switch (choice) {
		case 0: // redTower
			map.addObject(new RedTower(x, y));
			break;
		case 1: // greenTower
			map.addObject(new GreenTower(x, y));
			break;
		case 2: // blueTower
			map.addObject(new BlueTower(x, y));
			break;
		}
	}
	
	// add trap men�pont
	public static void addTrap() throws IOException {
		printlnMsg("Milyen akad�ly legyen?");
		
		int choice = choose("Sebz�", "Lass�t�");
		
		// ha -1, visszat�r�nk
		if (choice < 0)
			return;
				
		// bek�rj�k a koordin�t�kat
		double x = Console.readDouble();
		double y = Console.readDouble();
		
		// ha �rv�nytelenek a koordin�t�k, kil�p�nk
		if (x < 0 || x > GameMap.maxX || y < 0 || y > GameMap.maxY) {
			println("Akad�ly lerak�sa sikertelen: �rv�nytelen poz�ci�.");
			return;
		}
		
		switch (choice) {
		case 0: // damageTrap
			Game.getMap().addObject(new DamageTrap(x, y));
			break;
		case 1: // slowTrap
			Game.getMap().addObject(new SlowTrap(x, y));
			break;
		}
	}
	
	// add enemy men�pont
	public static void addEnemy() throws IOException {
		printlnMsg("Milyen ellens�g legyen?");
		
		int choice = choose("Ember", "T�nde", "T�rp", "Hobbit");
		
		// ha -1, visszat�r�nk
		if (choice < 0)
			return;
		
		printMsg("szint: ");
		int level = readInt();  // bek�rj�k a szintet
		
		printMsg("bel�p�si pont sorsz�ma: ");
		int nodeId = readInt(); // bek�rj�k a kezd� node-ot
		
		List<Node> rootNodes = Game.getMap().getNodes(); // gy�k�r nodeok
		int numOfRootNodes = rootNodes.size();           // gy�k�r nodeok sz�ma
		
		// ha �rv�nytelen a kezd� node id, kil�p�nk
		if (nodeId < 0 || nodeId >= numOfRootNodes) {
			println("Ellens�g lerak�sa sikertelen: �rv�nytelen poz�ci�.");
			return;
		}
		
		Node startNode = rootNodes.get(nodeId); // start Node
		
		switch (choice) {
		case 0: // human
			Game.getMap().addObject(new Human(startNode, level));
			break;
		case 1: // elf
			Game.getMap().addObject(new Elf(startNode, level));
			break;
		case 2: // dwarf
			Game.getMap().addObject(new Dwarf(startNode, level));
			break;
		case 3: // hobit
			Game.getMap().addObject(new Hobbit(startNode, level));
			break;
		}
	}
	
	// list objects men�pont
	public static void listObjects() {
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
	}
	
	// objektumok l�ptet�se
	public static void applyTicks() throws IOException {
		GameMap map = Game.getMap();
		
		printlnMsg("H�ny l�p�s legyen?");
		int ticks = readInt();
		
		// annyiszor, ah�ny tick van
		for (int i = 0; i < ticks; i++) {
			
			// minden objektumra...
			for (GameObject object : map.getObjects()) {
				// alkalmazzuk a tick-et
				boolean objectIsToBeRemoved = object.applyTick();
				
				// ha kell, t�r�lj�k az objektumot
				if (objectIsToBeRemoved) {
					map.removeObject(object);
				}
			}
		}
	}
	
	// upgrade tower men�pont
	public static void upgradeTower() throws IOException {
		// bek�r�nk egy objektumot
		GameObject object = getObjectFromUser();
		
		// ha torony, upgradelj�k
		if (object != null && object instanceof Tower) {
			
			Console.printlnMsg("Add meg az upgrade t�pus�t!");
			Console.choose("damge", "range", "attack speed", "projectile speed");
			
			// TODO
			((Tower)object).upgrade();
		}
	}
	
	// apply effect men�pont
	public static void applyEffect() throws IOException {
		printlnMsg("Milyen effekt legyen?");
		
		int choice = choose("Piros krist�ly", "Z�ld krist�ly", "K�k krist�ly", null, "K�d");
		
		// ha -1, visszat�r�nk
		if (choice < 0)
			return;
		
		Effect effect = null;
		
		switch (choice) {
		case 0: // redCrystal
			effect = new RedCrystalEffect();
			break;
		case 1: // greenCrystal
			effect = new GreenCrystalEffect();
			break;
		case 2: // blueCrystal
			effect = new BlueCrystalEffect();
			break;
		case 4:
			effect = new FogEffect();
			break;
		}
		
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			object.addEffect(effect);
			object.affect(effect);
		}
	}
	
	// new game men�pont
	public static void newGame() throws IOException {
		printlnMsg("Mented a j�t�kot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		Game.newGame();
	}
	
	// save game men�pont
	public static void saveGame() throws IOException {
		while (true) {
			printMsg("Ment�s f�jlneve: ");
			String file = readLine();
			
			boolean saved;
			
			if (file.equals("")) {
				saved = true;
			} else {
				saved = Game.saveGame(file);
			}
			
			if (saved) {
				break;
			} else {
				printlnMsg("Hiba a ment�skor!");
			}
		}
	}
	
	// load game men�pont
	public static void loadGame() throws IOException {
		printlnMsg("Mented a j�t�kot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		while (true) {
			printMsg("Bet�lt�s f�jlneve: ");
			String file = readLine();
			
			boolean loaded;
			
			if (file.equals("")) {
				loaded = true;
			} else {
				loaded = Game.loadGame(file);
			}
			
			if (loaded) {
				break;
			} else {
				printlnMsg("Hiba a bet�lt�skor!");
			}
		}
	}
	
	// tesztesetek
	private static final String[][] tests = {
		/*  1. Torony megl� egy Enemy-t        */ {"7", "3", "100", "100.5", "9", "3", "1", "0", "5", "6", "4", "13"},
		/*  2. DamageTrap-re fut�s             */ {"8", "1", "100", "10.5", "9", "3", "1", "0", "5", "6", "2", "13"},
		/*  3. SlowTrap-re fut�s               */ {"8", "2", "100", "10.5", "9", "3", "1", "0", "5", "6", "2", "13"},
		/*  4. BlueTower RedCrystal-t kap      */ {"7", "3", "100", "100.5", "11", "1", "5", "13"},
		/*  5. DamageTrap RedCrystal-t kap     */ {"8", "1", "100", "10.5", "", "11", "1", "5", "13"},
		/*  6. Egy enemy be�r a v�gzet hegy�be */ {"9", "2", "1", "0", "5", "6", "9999", "13"},
		/*  7. Torony meg�l egy enemyt         */ {"7", "3", "100", "100.5", "9", "3", "1", "0", "5", "6", "6", "5", "13"},
		/*  8. Fog effect tesztje              */ {"7", "3", "100", "100.5", "11", "5", "5", "13"},
		/*  9. Range upgrade teszt             */ {"7", "3", "100", "100.5", "10", "2", "5", "13"},
		/* 10. Damage upgrade teszt            */ {"7", "3", "100", "100.5", "10", "1", "5", "13"},
		/* 11. AttackSpeed upgrade teszt       */ {"7", "3", "100", "100.5", "10", "3", "5", "13"},
		/* 12. Projectile speed upgrade teszt  */ {"7", "3", "100", "100.5", "10", "4", "5", "13"},
		/* 13. Resistek tesztje                */ {"7", "1", "100", "50", "7", "2", "100", "60", "7", "3", "100", "70", "9", "3", "10", "0", "5", "6", "3", "13"}
	};
	
	// v�laszt�si lehet�s�gek a tesztesetekhez
	private static final String[] choices = new String[tests.length];
	
	static {
		for (int i = 0; i < choices.length; i++) {
			choices[i] = (i + 1) + ". teszteset";
		}
	}
	
	// teszt men�pont
	public static void test() throws IOException {
		if (commands.isEmpty()) {
			int choice = choose(choices);
			
			if (choice != -1) {
				commands.addAll(Arrays.asList(tests[choice]));
			}
		} else {
			choose(choices);
		}
	}
	
	// exit men�pont
	public static boolean exit() throws IOException {
		printlnMsg("Biztosan kil�psz?");
		
		if (chooseYesNo()) {
			printlnMsg("K�sz�nj�k a j�t�kot!");
			
			br.close();
			return true;
		}
		
		return false;
	}
	
	// random seed men�pont
	public static void randomSeed() throws IOException {
		// ha be van kapcsolva a v�letlenszer�s�g...
		if (Game.isRandom()) {
			// kikapcsoljuk
			Game.setRandom(false);
			// kil�p�nk
			return;
		// ha ki van kapcsolva a v�letlenszer�s�g...
		} else {
			// v�laszthatunk, hogy bekapcsoljuk,
			// megadunk egy nem v�letlen intet, ami ezent�l a v�letlen intet helyett gener�l�dik
			// vagy megadunk egy nem v�letlen booleant, ami ezent�l a v�letlen booleanok helyett gener�l�dik
			int choice = choose(
					"V�letlenszer�s�g be",
					"Nem v�letlen eg�sz megad�sa (jelenleg: " + Game.getNonRandomNextInt() + ")",
					"Nem v�letlen logikai �rt�k megad�sa (jelenleg: "+ Game.getNonRandomNextBoolean() + ")");
			
			switch (choice) {
			case 0:
				// bekapcsoljuk a v�letlenszer�s�get
				Game.setRandom(true);
				return;
			case 1:
				printMsg("�rt�k: ");
				// bek�r�nk egy eg�szet
				int iChoice = readInt();
				
				// ha nem -1 �s kisebb, mint 0, �jat k�r�nk
				while (iChoice != -1 && iChoice < 0) {
					iChoice = readInt();
				}
				
				// ha nem -1-et v�lasztott a felhaszn�l�, azaz nem visszal�pett...
				if (iChoice != -1) {
					// be�ll�tjuk a nem random intet
					Game.setNonRandomNextInt(readInt());
				}
				
				return;
			case 2:
				// v�lasztunk, hogy true vagy false legyen a nem random �rt�k
				int bChoice = choose("true", "false");
				
				// ha nem -1-et v�lasztott a felhaszn�l�, azaz nem visszal�pett...
				if (bChoice != -1) {
					// be�ll�tjuk a nem random booleant
					Game.setNonRandomNextBoolean(bChoice == 1);
				}
				
				return;
			}
		}
	}
}
