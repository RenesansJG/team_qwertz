package tower_defense;

public class Human extends Enemy {
	private static final long serialVersionUID = 5166440464581339881L;

	public Human() {
		Console.println(this + " = new Human()");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "human#" + id;
	}
}
