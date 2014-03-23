package tower_defense;

public class RedTower extends Tower {
	private static final long serialVersionUID = 3431245497354435731L;

	public RedTower() {
		Console.println(this + " = new RedTower()");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "redTower#" + id;
	}
}
