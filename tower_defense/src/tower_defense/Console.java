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
	
	// teljes sor kiírása elején tabokkal
	public static void println(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.println(o);
	}
	
	// üres sor kiírása
	public static void println() {
		System.out.println();
	}
	
	// kiírás elején tabokkal
	public static void printi(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.print(o);
	}
	
	// kiírás tabok nélkül
	public static void print(Object o) {
		System.out.print(o);
	}
	
	// felhasználónak szóló üzenet kiírása (teljes sor, elején tabokkal)
	public static void printlnMsg(Object o) {
		if (commands.isEmpty()) {
			for (int i = 0; i < indent; i++) {
				System.out.print("  ");
			}
			
			System.out.println(o);
		}
	}
	
	// felhasználónak szóló üzenet kiírása (elején tabokkal)
	public static void printMsg(Object o) {
		if (commands.isEmpty()) {
			for (int i = 0; i < indent; i++) {
				System.out.print("  ");
			}
			
			System.out.print(o);
		}
	}
	
	// eggyel beljebb kezdés (+tab)
	public static void indent() {
		indent++;
	}
	
	// eggyel kijjeb kezdés (-tab)
	public static void deIndent() {
		if (indent > 0)
			indent--;
	}
	
	// egy egész szám beolvasása
	public static int readInt() throws IOException {
		try {
			return Integer.parseInt(readLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// egy valós szám beolvasása
	public static double readDouble() throws IOException {
		try {
			return Double.parseDouble(readLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// egy sor beolvasása
	public static String readLine() throws IOException {
		// ha van a commands sorban elem, azt olvassuk be
		String line = commands.poll();
		
		// ha nincs, a felhasználótól kérünk egy sort
		if (line == null)
			line = br.readLine();
		
		return line;
	}
	
	// listából választás
	// paraméterek: a lista elemei
	// visszatérés: hanyadik elemet választotta a felhasználó (0-tól indexelünk)
	public static int choose(String... choices) throws IOException {
		if (choices == null || choices.length == 0)
			return 0;
		
		for (int i = 0; i < choices.length; i++) {
			if (choices[i] != null) { // csak a létezõ (nem null) választásokat írjuk ki
				printlnMsg((i + 1) + " = " + choices[i]);
			}
		}
		
		printMsg("Válasz: ");
		
		int choice = readInt();
		
		// akkor jó a válasz, ha az -1 (ez a "vissza" parancs),
		// vagy 1 és a válaszok hossza között van,
		// és létezik is az adott válasz (a válaszlista adott eleme nem null)
		while (choice != -1 && choice < 1 || choice > choices.length || choices[choice - 1] == null) {
			printMsg("Rossz válasz. Válasz: ");
			choice = readInt();
		}
		
		return (choice != - 1) ? choice - 1 : -1;
	}
	
	// eldöntendõ kérdés
	// visszatérés: igaz, ha az igent választotta a felhasználó,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		printlnMsg("1 = Igen");
		printlnMsg("2 = Nem");
		printMsg("Válasz: ");
		
		int choice = readInt();
		
		while (choice != 1 && choice != 2) {
			printMsg("Rossz válasz. Válasz: ");
			choice = readInt();
		}
		
		return choice == 1;
	}
	
	// objektum lekérése usertõl
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
			// welcome üzenet
			printlnMsg("A két torony");
			printlnMsg("© 2014 team_qwertz");
			
			// inicializáció
			init();
			
			while (true) {
				// fõmenü
				int choice = choose(
						"Új játék",
						"Játék mentése",
						"Játék betöltése",
						"Kilépés",
						"Objektumok listázása",
						"Játék léptetése",
						"Torony lerakása",
						"Akadály lerakása",
						"Ellenség generálása",
						"Torony fejlesztése",
						"Effekt alkalmazása",
						"Véletlenszerûség " + (Game.isRandom() ? "ki" : "be"),
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
	
	// inicializáció
	public static void init() {
		// új játék indítása
		Game.newGame();
	}
	
	// add tower menüpont
	public static void addTower() throws IOException {
		GameMap map = Game.getMap();
		
		printlnMsg("Milyen torony legyen?");
		
		// bekérjük a torony típusát
		int choice = choose("Piros", "Zöld", "Kék");
		
		// ha -1, visszatérünk
		if (choice < 0)
			return;
		
		// bekérjük a koordinátákat
		double x = Console.readDouble();
		double y = Console.readDouble();
		
		// ha érvénytelenek a koordináták, kilépünk
		if (x < 0 || x > GameMap.maxX || y < 0 || y > GameMap.maxY) {
			println("Torony lerakása sikertelen: érvénytelen pozíció.");
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
	
	// add trap menüpont
	public static void addTrap() throws IOException {
		printlnMsg("Milyen akadály legyen?");
		
		int choice = choose("Sebzõ", "Lassító");
		
		// ha -1, visszatérünk
		if (choice < 0)
			return;
				
		// bekérjük a koordinátákat
		double x = Console.readDouble();
		double y = Console.readDouble();
		
		// ha érvénytelenek a koordináták, kilépünk
		if (x < 0 || x > GameMap.maxX || y < 0 || y > GameMap.maxY) {
			println("Akadály lerakása sikertelen: érvénytelen pozíció.");
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
	
	// add enemy menüpont
	public static void addEnemy() throws IOException {
		printlnMsg("Milyen ellenség legyen?");
		
		int choice = choose("Ember", "Tünde", "Törp", "Hobbit");
		
		// ha -1, visszatérünk
		if (choice < 0)
			return;
		
		printMsg("szint: ");
		int level = readInt();  // bekérjük a szintet
		
		printMsg("belépési pont sorszáma: ");
		int nodeId = readInt(); // bekérjük a kezdõ node-ot
		
		List<Node> rootNodes = Game.getMap().getNodes(); // gyökér nodeok
		int numOfRootNodes = rootNodes.size();           // gyökér nodeok száma
		
		// ha érvénytelen a kezdõ node id, kilépünk
		if (nodeId < 0 || nodeId >= numOfRootNodes) {
			println("Ellenség lerakása sikertelen: érvénytelen pozíció.");
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
	
	// list objects menüpont
	public static void listObjects() {
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
	}
	
	// objektumok léptetése
	public static void applyTicks() throws IOException {
		GameMap map = Game.getMap();
		
		printlnMsg("Hány lépés legyen?");
		int ticks = readInt();
		
		// annyiszor, ahány tick van
		for (int i = 0; i < ticks; i++) {
			
			// minden objektumra...
			for (GameObject object : map.getObjects()) {
				// alkalmazzuk a tick-et
				boolean objectIsToBeRemoved = object.applyTick();
				
				// ha kell, töröljük az objektumot
				if (objectIsToBeRemoved) {
					map.removeObject(object);
				}
			}
		}
	}
	
	// upgrade tower menüpont
	public static void upgradeTower() throws IOException {
		// bekérünk egy objektumot
		GameObject object = getObjectFromUser();
		
		// ha torony, upgradeljük
		if (object != null && object instanceof Tower) {
			
			Console.printlnMsg("Add meg az upgrade típusát!");
			Console.choose("damge", "range", "attack speed", "projectile speed");
			
			// TODO
			((Tower)object).upgrade();
		}
	}
	
	// apply effect menüpont
	public static void applyEffect() throws IOException {
		printlnMsg("Milyen effekt legyen?");
		
		int choice = choose("Piros kristály", "Zöld kristály", "Kék kristály", null, "Köd");
		
		// ha -1, visszatérünk
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
	
	// new game menüpont
	public static void newGame() throws IOException {
		printlnMsg("Mented a játékot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		Game.newGame();
	}
	
	// save game menüpont
	public static void saveGame() throws IOException {
		while (true) {
			printMsg("Mentés fájlneve: ");
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
				printlnMsg("Hiba a mentéskor!");
			}
		}
	}
	
	// load game menüpont
	public static void loadGame() throws IOException {
		printlnMsg("Mented a játékot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		while (true) {
			printMsg("Betöltés fájlneve: ");
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
				printlnMsg("Hiba a betöltéskor!");
			}
		}
	}
	
	// tesztesetek
	private static final String[][] tests = {
		/*  1. Torony meglõ egy Enemy-t        */ {"7", "3", "100", "100.5", "9", "3", "1", "0", "5", "6", "4", "13"},
		/*  2. DamageTrap-re futás             */ {"8", "1", "100", "10.5", "9", "3", "1", "0", "5", "6", "2", "13"},
		/*  3. SlowTrap-re futás               */ {"8", "2", "100", "10.5", "9", "3", "1", "0", "5", "6", "2", "13"},
		/*  4. BlueTower RedCrystal-t kap      */ {"7", "3", "100", "100.5", "11", "1", "5", "13"},
		/*  5. DamageTrap RedCrystal-t kap     */ {"8", "1", "100", "10.5", "", "11", "1", "5", "13"},
		/*  6. Egy enemy beér a végzet hegyébe */ {"9", "2", "1", "0", "5", "6", "9999", "13"},
		/*  7. Torony megöl egy enemyt         */ {"7", "3", "100", "100.5", "9", "3", "1", "0", "5", "6", "6", "5", "13"},
		/*  8. Fog effect tesztje              */ {"7", "3", "100", "100.5", "11", "5", "5", "13"},
		/*  9. Range upgrade teszt             */ {"7", "3", "100", "100.5", "10", "2", "5", "13"},
		/* 10. Damage upgrade teszt            */ {"7", "3", "100", "100.5", "10", "1", "5", "13"},
		/* 11. AttackSpeed upgrade teszt       */ {"7", "3", "100", "100.5", "10", "3", "5", "13"},
		/* 12. Projectile speed upgrade teszt  */ {"7", "3", "100", "100.5", "10", "4", "5", "13"},
		/* 13. Resistek tesztje                */ {"7", "1", "100", "50", "7", "2", "100", "60", "7", "3", "100", "70", "9", "3", "10", "0", "5", "6", "3", "13"}
	};
	
	// választási lehetõségek a tesztesetekhez
	private static final String[] choices = new String[tests.length];
	
	static {
		for (int i = 0; i < choices.length; i++) {
			choices[i] = (i + 1) + ". teszteset";
		}
	}
	
	// teszt menüpont
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
	
	// exit menüpont
	public static boolean exit() throws IOException {
		printlnMsg("Biztosan kilépsz?");
		
		if (chooseYesNo()) {
			printlnMsg("Köszönjük a játékot!");
			
			br.close();
			return true;
		}
		
		return false;
	}
	
	// random seed menüpont
	public static void randomSeed() throws IOException {
		// ha be van kapcsolva a véletlenszerûség...
		if (Game.isRandom()) {
			// kikapcsoljuk
			Game.setRandom(false);
			// kilépünk
			return;
		// ha ki van kapcsolva a véletlenszerûség...
		} else {
			// választhatunk, hogy bekapcsoljuk,
			// megadunk egy nem véletlen intet, ami ezentúl a véletlen intet helyett generálódik
			// vagy megadunk egy nem véletlen booleant, ami ezentúl a véletlen booleanok helyett generálódik
			int choice = choose(
					"Véletlenszerûség be",
					"Nem véletlen egész megadása (jelenleg: " + Game.getNonRandomNextInt() + ")",
					"Nem véletlen logikai érték megadása (jelenleg: "+ Game.getNonRandomNextBoolean() + ")");
			
			switch (choice) {
			case 0:
				// bekapcsoljuk a véletlenszerûséget
				Game.setRandom(true);
				return;
			case 1:
				printMsg("érték: ");
				// bekérünk egy egészet
				int iChoice = readInt();
				
				// ha nem -1 és kisebb, mint 0, újat kérünk
				while (iChoice != -1 && iChoice < 0) {
					iChoice = readInt();
				}
				
				// ha nem -1-et választott a felhasználó, azaz nem visszalépett...
				if (iChoice != -1) {
					// beállítjuk a nem random intet
					Game.setNonRandomNextInt(readInt());
				}
				
				return;
			case 2:
				// választunk, hogy true vagy false legyen a nem random érték
				int bChoice = choose("true", "false");
				
				// ha nem -1-et választott a felhasználó, azaz nem visszalépett...
				if (bChoice != -1) {
					// beállítjuk a nem random booleant
					Game.setNonRandomNextBoolean(bChoice == 1);
				}
				
				return;
			}
		}
	}
}
