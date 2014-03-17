package tower_defense;

public class RedCrystalEffect extends Effect {
	private static final long serialVersionUID = 3343707382553612861L;
	
	public RedCrystalEffect() {
		Console.println(this + " = new RedCrystalEffect()");
	}
	
	@Override
	public String toString() {
		return "redCrystalEffect#" + id;
	}
}
