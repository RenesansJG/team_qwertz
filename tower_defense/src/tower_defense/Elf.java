package tower_defense;

public class Elf extends Enemy {
	private static final long serialVersionUID = 8749863853199308399L;
	
	public Elf() {

	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "elf#" + id;
	}
}
