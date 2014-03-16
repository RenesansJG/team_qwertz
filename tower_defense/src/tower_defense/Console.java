package tower_defense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int indent = 0;
	
	// print Geri
	public static void printGeri(Object o) {
		System.out.print("Geri");
	}
	
	// print without indent
	public static void print(Object o) {
		System.out.print(o);
	}
	
	// print with indent
	public static void printi(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.print(o);
	}
	
	// print line with indent
	public static void println(Object o) {
		for (int i = 0; i < indent; i++) {
			System.out.print("  ");
		}
		
		System.out.println(o);
	}
	
	public static void indent() {
		indent++;
	}
	
	public static void deIndent() {
		if (indent > 0)
			indent--;
	}
	
	public static int readChoice(String... choices) throws IOException {
		if (choices == null || choices.length == 0)
			return 0;
		
		for (int i = 0; i < choices.length; i++) {
			println((i + 1) + " = " + choices[i]);
		}
		
		int choice;
		
		do {
			printi("Válasz: ");
			
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException nfe) {
				choice = 0;
			}
			
			if (choice < 1 || choice > choices.length) {
				print("Rossz válasz. ");
				choice = 0;
			}
		} while (choice == 0);
		
		return choice - 1;
	}
	
	public static void main(String[] args) {
		try {
			int mainMenuChoice = Console.readChoice("Új játék", "Kilépés");
			
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
