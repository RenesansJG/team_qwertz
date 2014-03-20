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
	
	// felhaszn�l�nak sz�l� �zenet ki�r�sa (teljes sor)
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
		String line = commands.poll();
		
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
			printlnMsg((i + 1) + " = " + choices[i]);
		}
		
		printMsg("V�lasz: ");
		int choice = readInt();
		
		while (choice < 1 || choice > choices.length) {
			printMsg("Rossz v�lasz. V�lasz: ");
			choice = readInt();
		}
		
		return choice - 1;
	}
	
	// eld�ntend� k�rd�s
	// visszat�r�s: igaz, ha az igent v�lasztotta a felhaszn�l�,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		return choose("Igen", "Nem") == 0 ? true : false;
	}
	
	// objektum lek�r�se usert�l
	public static GameObject getObjectFromUser() throws IOException {
		GameObject object = null;
		
		do {
			printMsg("ID (-1 = m�gsem): ");
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
				case 6: // save game
					saveGame();
					break;
				case 7: // load game
					loadGame();
					break;
				case 8: // test
					test();
					break;
				case 9: // exit
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
		// �j j�t�k ind�t�sa
		Game.newGame();
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
		
		boolean isToBeRemoved = object.applyTick();
		
		if (isToBeRemoved) {
			Game.getMap().removeObject(object);
		}
		
		deIndent();
	}
	
	// upgrade tower men�pont
	public static void upgradeTower() throws IOException {
		println("Console.upgradeTower()");
		indent();
		
		GameObject object = getObjectFromUser();
		
		if (object instanceof Tower) {
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
		}
		
		deIndent();
	}
	
	// apply red crystal men�pont
	public static void applyRedCrystal() throws IOException {
		println("Console.applyRedCrystal()");
		indent();
		
		Effect effect = new RedCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
		
		deIndent();
	}
	
	// apply green crystal men�pont
	public static void applyGreenCrystal() throws IOException {
		println("Console.applyGreenCrystal()");
		indent();
		
		Effect effect = new GreenCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
		
		deIndent();
	}
		
	// apply blue crystal men�pont
	public static void applyBlueCrystal() throws IOException {
		println("Console.applyBlueCrystal()");
		indent();
		
		Effect effect = new BlueCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
		
		deIndent();
	}
	
	// save game men�pont
	public static void saveGame() throws IOException {
		println("Console.saveGame()");
		indent();
		
		while (true) {
			printlnMsg("Ment�s f�jlneve: ");
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
			printlnMsg("Bet�lt�s f�jlneve: ");
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
		{"1", "1", "2", "9", "1"}
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
		
		if (commands.isEmpty())
			commands.addAll(Arrays.asList(tests[choose(choices)]));
		else
			readLine();
		
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
