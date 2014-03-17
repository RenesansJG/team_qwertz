package tower_defense;

public class Dwarf extends Enemy {
	private static final long serialVersionUID = -4887012896252201347L;

	public Dwarf() {
		Console.println(this + " = new Dwarf()");
	}
	
	@Override
	public String toString() {
		return "dwarf#" + id;
	}
}
