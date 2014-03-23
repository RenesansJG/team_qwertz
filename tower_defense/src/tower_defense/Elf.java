package tower_defense;

public class Elf extends Enemy {
	private static final long serialVersionUID = 8749863853199308399L;
	
	public Elf() {
		Console.println(this + " = new Elf()");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "elf#" + id;
	}
}
