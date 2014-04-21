package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

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
	
	// egy long egész szám beolvasása
	public static long readLong() throws IOException {
		try {
			return Long.parseLong(readLine());
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
			printlnMsg((i + 1) + " = " + choices[i]);
		}
		
		printMsg("Válasz: ");
		
		int choice = readInt();
		
		while (choice != -1 && choice < 1 || choice > choices.length) {
			printMsg("Rossz válasz. Válasz: ");
			choice = readInt();
		}
		
		return (choice != - 1) ? choice - 1 : choice;
	}
	
	// eldöntendõ kérdés
	// visszatérés: igaz, ha az igent választotta a felhasználó,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		println("Console.chooseYesNo()");
		indent();
		
		printlnMsg("1 = Igen");
		printlnMsg("2 = Nem");
		printMsg("Válasz: ");
		
		int choice = readInt();
		
		while (choice != 1 && choice != 2) {
			printMsg("Rossz válasz. Válasz: ");
			choice = readInt();
		}
		
		deIndent();
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
						"Véletlenszerûség ki/be",
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
						
		// bekérjük a koordinátákat
		double x = Console.readDouble();
		double y = Console.readDouble();
				
		// ha érvénytelenek a koordináták, kilépünk
		if (x < 0 || x > GameMap.maxX || y < 0 || y > GameMap.maxY) {
			println("Ellenség lerakása sikertelen: érvénytelen pozíció.");
			return;
		}
		
		switch (choice) {
		case 0: // human
			Game.getMap().addObject(new Human(x, y));
			break;
		case 1: // elf
			Game.getMap().addObject(new Elf(x, y));
			break;
		case 2: // dwarf
			Game.getMap().addObject(new Dwarf(x, y));
			break;
		case 3: // hobit
			Game.getMap().addObject(new Hobbit(x, y));
			break;
		}
	}
	
	// list objects menüpont
	public static void listObjects() {
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
	}
	
	/*
	// remove object menüpont
	public static void removeObject() throws IOException {
		printlnMsg("Írd be a törlendõ objektum ID-jét!");
		
		GameObject objectToRemove = getObjectFromUser();
		
		if (objectToRemove != null) {
			Game.getMap().removeObject(objectToRemove);
		}
	}
	*/
	
	/*
	// apply tick menüpont
	public static void applyTick() throws IOException {
		printlnMsg("Add meg az objektum ID-jét!");
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			// alkalmazzuk a ticket az objektumon, és eltároljuk, törlendõ-e
			boolean isToBeRemoved = object.applyTick();
			
			// ha törlendõ az objektum, töröljük
			if (isToBeRemoved) {
				Game.getMap().removeObject(object);
			}
		}
	}
	*/
	
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
			((Tower)object).upgrade();
		}
	}
	
	// apply effect menüpont
	public static void applyEffect() throws IOException {
		printlnMsg("Milyen effekt legyen?");
		
		int choice = choose("Piros kristály", "Zöld kristály", "Kék kristály", "Köd");
		
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
		case 3:
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
		/*
		{"7", "2", "1", "1", "1", "1", "3", "2", "4", "0", "1", "1", "1", "4", "2", "1", "1", "-1", "4", "1", "1", "2", "2", "1", "10"},
		{"7", "2", "1", "1", "1", "1", "1", "2", "1", "1", "3", "5", "0", "6", "3", "2", "4", "2", "2", "3", "1", "10"},
		{"7", "2", "1", "2", "1", "1", "3", "1", "1", "3", "3", "1", "3", "4", "4", "0", "2", "3", "-1", "4", "1", "2", "2", "4", "2", "2", "2", "4", "3", "2", "1", "10"},
		{"7", "2", "1", "2", "2", "1", "3", "1", "1", "3", "1", "4", "0", "1", "2", "-1", "4", "1", "2", "2", "4", "2", "2", "2", "10"},
		{"7", "2", "1", "3", "1", "1", "3", "1", "1", "3", "2", "4", "0", "1", "2", "2", "2", "4", "1", "1", "2", "2", "2", "4", "2", "1", "1", "2", "2", "10"},
		{"1", "3", "1", "1", "3", "2", "1", "3", "3", "1", "3", "4", "10"},
		{"1", "1", "1", "1", "1", "2", "1", "1", "3", "10"},
		{"1", "2", "1", "1", "2", "2", "10"}
		*/
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
		printlnMsg("Random seed: ");
		Game.rnd = new Random(readLong());
	}
}
