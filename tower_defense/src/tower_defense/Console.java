package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int indent = 0;
	
	// ki�r�s tabok n�lk�l (sor k�zep�hez, v�g�hez)
	public static void print(Object o) {
		System.out.print(o);
	}
	
	// ki�r�s elej�n tabokkal (sor elej�hez)
	public static void printi(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.print(o);
	}
	
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
		return br.readLine();
	}
	
	// list�b�l v�laszt�s
	// param�terek: a lista elemei
	// visszat�r�s: hanyadik elemet v�lasztotta a felhaszn�l� (0-t�l indexel�nk)
	public static int choose(String... choices) throws IOException {
		if (choices == null || choices.length == 0)
			return 0;
		
		for (int i = 0; i < choices.length; i++) {
			println((i + 1) + " = " + choices[i]);
		}
		
		printi("V�lasz: ");
		int choice = readInt();
		
		while (choice < 1 || choice > choices.length) {
			printi("Rossz v�lasz. V�lasz: ");
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
	
	// main
	public static void main(String[] args) {
		try {
			// welcome �zenet
			println("A k�t torony");
			println("� 2014 team_qwertz");
			println();
			
			// inicializ�ci�
			init();
			
			while (true) {
				println();
				
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
	
	// inicializ�ci�
	public static void init() {
		// �j j�t�k ind�t�sa
		Game.newGame();
	}
	
	// add object men�pont
	public static void addObject() throws IOException {
		println();
		println("Milyen objektum legyen?");
		
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
	}
	
	// add tower men�pont
	public static void addTower() throws IOException {
		println();
		println("Milyen torony legyen?");
		
		int choice = choose("Piros", "K�k", "Z�ld");
		
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
	
	// add trap men�pont
	public static void addTrap() throws IOException {
		println();
		println("Milyen akad�ly legyen?");
		
		int choice = choose("Sebz�", "Lass�t�");
		
		switch (choice) {
		case 0: // damageTrap
			Game.getMap().addObject(new DamageTrap());
			break;
		case 1: // slowTrap
			Game.getMap().addObject(new SlowTrap());
			break;
		}
	}
	
	// add enemy men�pont
	public static void addEnemy() throws IOException {
		println();
		println("Milyen ellens�g legyen?");
		
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
	}
	
	// list objects men�pont
	public static void listObjects() {
		println();
		
		for (GameObject object : Game.getMap().getObjects()) {
			println(object);
		}
	}
	
	// remove object men�pont
	public static void removeObject() {
		// TODO method stub
	}
	
	// apply tick men�pont
	public static void applyTick() {
		// TODO method stub
	}
	
	// upgrade tower men�pont
	public static void upgradeTower() {
		// TODO method stub
	}
	
	// apply crystal men�pont
	public static void applyCrystal() {
		// TODO method stub
	}
	
	// save game men�pont
	public static void saveGame() throws IOException {
		while (true) {
			printi("Ment�s f�jlneve: ");
			String file = readLine();
			boolean saved = Game.saveGame(file);
			
			if (saved) {
				break;
			} else {
				println("Hiba a ment�skor!");
			}
		}
	}
	
	// load game men�pont
	public static void loadGame() throws IOException {
		println();
		println("Mented a j�t�kot?");
		
		if (chooseYesNo()) {
			saveGame();
		}
		
		println();
		
		while (true) {
			printi("Bet�lt�s f�jlneve: ");
			String file = readLine();
			boolean loaded = Game.loadGame(file);
			
			if (loaded) {
				break;
			} else {
				println("Hiba a bet�lt�skor!");
			}
		}
	}
	
	public static boolean exit() throws IOException {
		println();
		println("Biztosan kil�psz?");
		
		if (chooseYesNo()) {
			println();
			println("K�sz�nj�k a j�t�kot!");
			
			br.close();
			return true;
		}
		
		return false;
	}
}
