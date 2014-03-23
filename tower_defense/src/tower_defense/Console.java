package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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
		printi("Console.choose(");
		
		for (int i = 0; i < choices.length - 1; i++) {
			print(choices[i] + ", ");
		}
		
		if (choices.length > 0) {
			print(choices[choices.length - 1]);
		}
		
		print(")");
		println();
		indent();
		
		if (choices == null || choices.length == 0)
			return 0;
		
		for (int i = 0; i < choices.length; i++) {
			printlnMsg((i + 1) + " = " + choices[i]);
		}
		
		printMsg("V�lasz: ");
		
		int choice = readInt();
		
		while (choice != -1 && choice < 1 || choice > choices.length) {
			printMsg("Rossz v�lasz. V�lasz: ");
			choice = readInt();
		}
		
		deIndent();
		return (choice != - 1) ? choice - 1 : choice;
	}
	
	// eld�ntend� k�rd�s
	// visszat�r�s: igaz, ha az igent v�lasztotta a felhaszn�l�,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		println("Console.chooseYesNo()");
		indent();
		
		printlnMsg("1 = Igen");
		printlnMsg("2 = Nem");
		printMsg("V�lasz: ");
		
		int choice = readInt();
		
		while (choice != 1 && choice != 2) {
			printMsg("Rossz v�lasz. V�lasz: ");
			choice = readInt();
		}
		
		deIndent();
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
						"Objektum hozz�ad�sa",
						"Objektumok list�z�sa",
						"Objektum elt�vol�t�sa",
						"Objektum l�ptet�se",
						"Torony fejleszt�se",
						"Krist�ly alkalmaz�sa tornyon vagy akad�lyon",
						"�j j�t�k",
						"J�t�k ment�se",
						"M�sik j�t�k bet�lt�se",
						"Tesztesetek",
						"Kil�p�s");
				
				switch (choice) {
				case 0: // add object
					addObject();
					break;
				case 1: // list objects
					listObjects();
					break;
				case 2: // remove object
					removeObject();
					break;
				case 3: // apply tick
					applyTick();
					break;
				case 4: // upgrade tower
					upgradeTower();
					break;
				case 5: // apply crystal
					applyCrystal();
					break;
				case 6: // new game
					newGame();
					break;
				case 7: // save game
					saveGame();
					break;
				case 8: // load game
					loadGame();
					break;
				case 9: // test
					test();
					break;
				case 10: // exit
					if (exit()) {
						return;
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// inicializ�ci�
	public static void init() {
		println("Console.init()");
		indent();
		
		// �j j�t�k ind�t�sa
		Game.newGame();
		
		deIndent();
	}
	
	// add object men�pont
	public static void addObject() throws IOException {
		println("Console.addObject()");
		indent();
		
		printlnMsg("Milyen objektum legyen?");
		
		int choice = choose("Torony", "Akad�ly", "Ellens�g");
		
		switch (choice) {
		case 0: // tower
			addTower();
			break;
		case 1: // trap
			addTrap();
			break;
		case 2: // enemy
			addEnemy();
			break;
		default: // vissza
			break;
		}
		
		deIndent();
	}
	
	// add tower men�pont
	public static void addTower() throws IOException {
		println("Console.addTower()");
		indent();
		
		printlnMsg("Milyen torony legyen?");
		
		int choice = choose("Piros", "Z�ld", "K�k");
		
		switch (choice) {
		case 0: // redTower
			Game.getMap().addObject(new RedTower());
			break;
		case 1: // greenTower
			Game.getMap().addObject(new GreenTower());
			break;
		case 2: // blueTower
			Game.getMap().addObject(new BlueTower());
			break;
		default: // vissza
			break;
		}
		
		deIndent();
	}
	
	// add trap men�pont
	public static void addTrap() throws IOException {
		println("Console.addTrap()");
		indent();
		
		printlnMsg("Milyen akad�ly legyen?");
		
		int choice = choose("Sebz�", "Lass�t�");
		
		switch (choice) {
		case 0: // damageTrap
			Game.getMap().addObject(new DamageTrap());
			break;
		case 1: // slowTrap
			Game.getMap().addObject(new SlowTrap());
			break;
		default: // vissza
			break;
		}
		
		deIndent();
	}
	
	// add enemy men�pont
	public static void addEnemy() throws IOException {
		println("Console.addEnemy()");
		indent();
		
		printlnMsg("Milyen ellens�g legyen?");
		
		int choice = choose("Ember", "T�nde", "T�rp", "Hobbit");
		
		switch (choice) {
		case 0: // human
			Game.getMap().addObject(new Human());
			break;
		case 1: // elf
			Game.getMap().addObject(new Elf());
			break;
		case 2: // dwarf
			Game.getMap().addObject(new Dwarf());
			break;
		case 3: // hobit
			Game.getMap().addObject(new Hobbit());
			break;
		default: // vissza
			break;
		}
		
		deIndent();
	}
	
	// list objects men�pont
	public static void listObjects() {
		println("Console.listObjects()");
		indent();
		
		for (GameObject object : Game.getMap().getObjects()) {
			printlnMsg(object);
		}
		
		deIndent();
	}
	
	// remove object men�pont
	public static void removeObject() throws IOException {
		println("Console.removeObject()");
		indent();
		
		printlnMsg("�rd be a t�rlend� objektum ID-j�t!");
		
		GameObject objectToRemove = getObjectFromUser();
		
		if (objectToRemove != null) {
			Game.getMap().removeObject(objectToRemove);
		}
		
		deIndent();
	}
	
	// apply tick men�pont
	public static void applyTick() throws IOException {
		println("Console.applyTick()");
		indent();
		
		printlnMsg("Add meg az objektum ID-j�t!");
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			// alkalmazzuk a ticket az objektumon, �s elt�roljuk, t�rlend�-e
			boolean isToBeRemoved = object.applyTick();
			
			// ha t�rlend� az objektum, t�r�lj�k
			if (isToBeRemoved) {
				Game.getMap().removeObject(object);
			}
		}
		
		deIndent();
	}
	
	// upgrade tower men�pont
	public static void upgradeTower() throws IOException {
		println("Console.upgradeTower()");
		indent();
		
		GameObject object = getObjectFromUser();
		
		if (object != null && object instanceof Tower) {
			((Tower)object).upgrade();
		}
		
		deIndent();
	}
	
	// apply crystal men�pont
	public static void applyCrystal() throws IOException {
		println("Console.applyCrystal()");
		indent();
		
		printlnMsg("Milyen krist�ly legyen?");
		
		int choice = choose("Piros", "Z�ld", "K�k");
		
		switch (choice) {
		case 0: // redCrystal
			applyRedCrystal();
			break;
		case 1: // greenCrystal
			applyGreenCrystal();
			break;
		case 2: // blueCrystal
			applyBlueCrystal();
			break;
		default: // vissza
			break;
		}
		
		deIndent();
	}
	
	// apply red crystal men�pont
	public static void applyRedCrystal() throws IOException {
		println("Console.applyRedCrystal()");
		indent();
		
		Effect effect = new RedCrystalEffect();
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			object.addEffect(effect);
			object.affect(effect);
		}
		
		deIndent();
	}
	
	// apply green crystal men�pont
	public static void applyGreenCrystal() throws IOException {
		println("Console.applyGreenCrystal()");
		indent();
		
		Effect effect = new GreenCrystalEffect();
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			object.addEffect(effect);
			object.affect(effect);
		}
		
		deIndent();
	}
		
	// apply blue crystal men�pont
	public static void applyBlueCrystal() throws IOException {
		println("Console.applyBlueCrystal()");
		indent();
		
		Effect effect = new BlueCrystalEffect();
		GameObject object = getObjectFromUser();
		
		if (object != null) {
			object.addEffect(effect);
			object.affect(effect);
		}
		
		deIndent();
	}
	
	// new game men�pont
	public static void newGame() throws IOException {
		println("Console.newGame()");
		indent();
		
		printlnMsg("Mented a j�t�kot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		Game.newGame();
		
		deIndent();
	}
	
	// save game men�pont
	public static void saveGame() throws IOException {
		println("Console.saveGame()");
		indent();
		
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
		
		deIndent();
	}
	
	// load game men�pont
	public static void loadGame() throws IOException {
		println("Console.loadGame()");
		indent();
		
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
		
		deIndent();
	}
	
	// tesztesetek
	private static final String[][] tests = {
		{"7", "2", "1", "1", "1", "1", "3", "2", "4", "0", "1", "1", "1", "4", "2", "1", "1", "-1", "4", "1", "1", "2", "2", "1", "10"},
		{"7", "2", "1", "1", "1", "1", "1", "2", "1", "1", "3", "5", "0", "6", "3", "2", "4", "2", "2", "3", "1", "10"},
		{"7", "2", "1", "2", "1", "1", "3", "1", "1", "3", "3", "1", "3", "4", "4", "0", "2", "3", "-1", "4", "1", "2", "2", "4", "2", "2", "2", "4", "3", "2", "1", "10"},
		{"7", "2", "1", "2", "2", "1", "3", "1", "1", "3", "1", "4", "0", "1", "2", "-1", "4", "1", "2", "2", "4", "2", "2", "2", "10"},
		{"7", "2", "1", "3", "1", "1", "3", "1", "1", "3", "2", "4", "0", "1", "2", "2", "2", "4", "1", "1", "2", "2", "2", "4", "2", "1", "1", "2", "2", "10"},
		{"1", "3", "1", "1", "3", "2", "1", "3", "3", "1", "3", "4", "10"},
		{"1", "1", "1", "1", "1", "2", "1", "1", "3", "10"},
		{"1", "2", "1", "1", "2", "2", "10"}
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
		println("Console.test()");
		indent();
		
		if (commands.isEmpty()) {
			int choice = choose(choices);
			
			if (choice != -1) {
				commands.addAll(Arrays.asList(tests[choice]));
			}
		} else {
			choose(choices);
		}
		
		deIndent();
	}
	
	// exit men�pont
	public static boolean exit() throws IOException {
		println("Console.exit()");
		indent();
		
		printlnMsg("Biztosan kil�psz?");
		
		if (chooseYesNo()) {
			printlnMsg("K�sz�nj�k a j�t�kot!");
			
			br.close();
			return true;
		}
		
		deIndent();
		return false;
	}
}
