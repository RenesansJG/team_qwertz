package tower_defense;

public class Hobbit extends Enemy {
	private static final long serialVersionUID = -3408698338441606044L;

	public Hobbit() {
		Console.println(this + " = new Hobbit()");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "hobbit#" + id;
	}
}
