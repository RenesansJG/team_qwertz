package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int indent = 0;
	
	// kiírás tabok nélkül (sor közepéhez, végéhez)
	public static void print(Object o) {
		System.out.print(o);
	}
	
	// kiírás elején tabokkal (sor elejéhez)
	public static void printi(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.print(o);
	}
	
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
			println((i + 1) + " = " + choices[i]);
		}
		
		printi("Válasz: ");
		int choice = readInt();
		
		while (choice < 1 || choice > choices.length) {
			printi("Rossz válasz. Válasz: ");
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
	
	// main
	public static void main(String[] args) {
		try {
			// welcome üzenet
			println("A két torony");
			println("© 2014 team_qwertz");
			println();
			
			// inicializáció
			init();
			
			while (true) {
				println();
				
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
				case 8: // exit
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
		println();
		println("Milyen objektum legyen?");
		
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
	}
	
	// add tower menüpont
	public static void addTower() throws IOException {
		println();
		println("Milyen torony legyen?");
		
		int choice = choose("Piros", "Kék", "Zöld");
		
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
	}
	
	// add trap menüpont
	public static void addTrap() throws IOException {
		println();
		println("Milyen akadály legyen?");
		
		int choice = choose("Sebzõ", "Lassító");
		
		switch (choice) {
		case 0: // damageTrap
			Game.getMap().addObject(new DamageTrap());
			break;
		case 1: // slowTrap
			Game.getMap().addObject(new SlowTrap());
			break;
		}
	}
	
	// add enemy menüpont
	public static void addEnemy() throws IOException {
		println();
		println("Milyen ellenség legyen?");
		
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
	}
	
	// list objects menüpont
	public static void listObjects() {
		println();
		
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
	}
	
	// remove object menüpont
	public static void removeObject() {
		// TODO method stub
	}
	
	// apply tick menüpont
	public static void applyTick() {
		// TODO method stub
	}
	
	// upgrade tower menüpont
	public static void upgradeTower() {
		// TODO method stub
	}
	
	// apply crystal menüpont
	public static void applyCrystal() {
		// TODO method stub
	}
	
	// save game menüpont
	public static void saveGame() throws IOException {
		while (true) {
			printi("Mentés fájlneve: ");
			String file = readLine();
			boolean saved = Game.saveGame(file);
			
			if (saved) {
				break;
			} else {
				println("Hiba a mentéskor!");
			}
		}
	}
	
	// load game menüpont
	public static void loadGame() throws IOException {
		println();
		println("Mented a játékot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		println();
		
		while (true) {
			printi("Betöltés fájlneve: ");
			String file = readLine();
			boolean loaded = Game.loadGame(file);
			
			if (loaded) {
				break;
			} else {
				println("Hiba a betöltéskor!");
			}
		}
	}
	
	public static boolean exit() throws IOException {
		println();
		println("Biztosan kilépsz?");
		
		if (chooseYesNo()) {
			println();
			println("Köszönjük a játékot!");
			
			br.close();
			return true;
		}
		
		return false;
	}
}
