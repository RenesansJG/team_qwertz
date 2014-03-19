package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int indent = 0;
	private static boolean silent = false;
	
	// teljes sor kiírása elején tabokkal
	public static void println(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.println(o);
	}
	
	// felhasználónak szóló üzenet kiírása (teljes sor)
	public static void printlnMsg(Object o) {
		if (!silent) {
			for (int i = 0; i < indent; i++) {
				System.out.print("  ");
			}
			
			System.out.println(o);
		}
	}
	
	// felhasználónak szóló üzenet kiírása (elején tabokkal)
	public static void printMsg(Object o) {
		if (!silent) {
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
	
	// egy sor beolvasása
	public static String readLine() throws IOException {
		return br.readLine();
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
		
		while (choice < 1 || choice > choices.length) {
			printMsg("Rossz válasz. Válasz: ");
			choice = readInt();
		}
		
		return choice - 1;
	}
	
	// eldöntendõ kérdés
	// visszatérés: igaz, ha az igent választotta a felhasználó,
	//              hamis ha a nemet
	public static boolean chooseYesNo() throws IOException {
		return choose("Igen", "Nem") == 0 ? true : false;
	}
	
	// objektum lekérése usertõl
	public static GameObject getObjectFromUser() throws IOException {
		GameObject object = null;
		
		do {
			printMsg("ID (-1 = mégsem): ");
			int id = Console.readInt();
			
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
						"Objektum hozzáadása",
						"Objektumok listázása",
						"Objektum eltávolítása",
						"Objektum léptetése",
						"Torony fejlesztése",
						"Kristály alkalmazása tornyon vagy akadályon",
						"Játék mentése",
						"Másik játék betöltése",
						"Silent mód",
						"Kilépés");
				
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
				case 8: // silent mode switch
					silent = !silent;
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
	
	// inicializáció
	public static void init() {
		// új játék indítása
		Game.newGame();
	}
	
	// add object menüpont
	public static void addObject() throws IOException {
		Console.println("Console.addObject()");
		Console.indent();
		
		printlnMsg("Milyen objektum legyen?");
		
		int choice = choose("Torony", "Akadály", "Ellenség");
		
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
		
		Console.deIndent();
	}
	
	// add tower menüpont
	public static void addTower() throws IOException {
		Console.println("Console.addTower()");
		Console.indent();
		
		printlnMsg("Milyen torony legyen?");
		
		int choice = choose("Piros", "Zöld", "Kék");
		
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
		
		Console.deIndent();
	}
	
	// add trap menüpont
	public static void addTrap() throws IOException {
		Console.println("Console.addTrap()");
		Console.indent();
		
		printlnMsg("Milyen akadály legyen?");
		
		int choice = choose("Sebzõ", "Lassító");
		
		switch (choice) {
		case 0: // damageTrap
			Game.getMap().addObject(new DamageTrap());
			break;
		case 1: // slowTrap
			Game.getMap().addObject(new SlowTrap());
			break;
		}
		
		Console.deIndent();
	}
	
	// add enemy menüpont
	public static void addEnemy() throws IOException {
		Console.println("Console.addObject()");
		Console.indent();
		
		printlnMsg("Milyen ellenség legyen?");
		
		int choice = choose("Ember", "Tünde", "Törp", "Hobbit");
		
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
		
		Console.deIndent();
	}
	
	// list objects menüpont
	public static void listObjects() {
		Console.println("Console.listObjects()");
		Console.indent();
		
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
		
		Console.deIndent();
	}
	
	// remove object menüpont
	public static void removeObject() throws IOException {
		Console.println("Console.removeObject()");
		Console.indent();
		
		printlnMsg("Írd be a törlendõ objektum ID-jét!");
		
		GameObject objectToRemove = getObjectFromUser();
		
		if (objectToRemove != null) {
			Game.getMap().removeObject(objectToRemove);
		}
		
		Console.deIndent();
	}
	
	// apply tick menüpont
	public static void applyTick() throws IOException {
		Console.println("Console.applyTick()");
		Console.indent();
		
		printlnMsg("Add meg az objektum ID-jét!");
		GameObject object = getObjectFromUser();
		
		boolean isToBeRemoved = object.applyTick();
		
		if (isToBeRemoved) {
			Game.getMap().removeObject(object);
		}
		
		Console.deIndent();
	}
	
	// upgrade tower menüpont
	public static void upgradeTower() throws IOException {
		Console.println("Console.upgradeTower()");
		Console.indent();
		
		GameObject object = getObjectFromUser();
		
		if (object instanceof Tower) {
			((Tower)object).upgrade();
		}
		
		Console.deIndent();
	}
	
	// apply crystal menüpont
	public static void applyCrystal() throws IOException {
		Console.println("Console.applyCrystal()");
		Console.indent();
		
		printlnMsg("Milyen kristály legyen?");
		
		int choice = choose("Piros", "Zöld", "Kék");
		
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
		
		Console.deIndent();
	}
	
	// apply red crystal menüpont
	public static void applyRedCrystal() throws IOException {
		Effect effect = new RedCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
	}
	
	// apply green crystal menüpont
	public static void applyGreenCrystal() throws IOException {
		Effect effect = new GreenCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
	}
		
	// apply blue crystal menüpont
	public static void applyBlueCrystal() throws IOException {
		Effect effect = new BlueCrystalEffect();
		GameObject object = getObjectFromUser();
		object.addEffect(effect);
		object.affect(effect);
	}
	
	// save game menüpont
	public static void saveGame() throws IOException {
		Console.println("Console.saveGame()");
		Console.indent();
		
		while (true) {
			printlnMsg("Mentés fájlneve: ");
			String file = readLine();
			boolean saved = Game.saveGame(file);
			
			if (saved) {
				break;
			} else {
				printlnMsg("Hiba a mentéskor!");
			}
		}
		
		Console.deIndent();
	}
	
	// load game menüpont
	public static void loadGame() throws IOException {
		Console.println("Console.loadGame()");
		Console.indent();
		
		printlnMsg("Mented a játékot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		while (true) {
			printlnMsg("Betöltés fájlneve: ");
			String file = readLine();
			boolean loaded = Game.loadGame(file);
			
			if (loaded) {
				break;
			} else {
				printlnMsg("Hiba a betöltéskor!");
			}
		}
		
		Console.deIndent();
	}
	
	// exit menüpont
	public static boolean exit() throws IOException {
		Console.println("Console.exit()");
		
		printlnMsg("Biztosan kilépsz?");
		
		if (chooseYesNo()) {
			printlnMsg("Köszönjük a játékot!");
			
			br.close();
			return true;
		}
		
		Console.deIndent();
		return false;
	}
}
