package tower_defense;

public class BlueCrystalEffect extends Effect {
	private static final long serialVersionUID = 2922468267291618074L;

	public BlueCrystalEffect() {
		Console.println(this + " = new BlueCrystalEffect()");
	}
	
	@Override
	public String toString() {
		return "blueCrystalEffect#" + id;
	}
}
