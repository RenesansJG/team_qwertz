package tower_defense;

public class GreenTower extends Tower {
	private static final long serialVersionUID = 9017818506188373134L;

	public GreenTower() {
		Console.println(this + " = new GreenTower()");
	}
	
	@Override
	public String toString() {
		return "greenTower#" + id;
	}
}
