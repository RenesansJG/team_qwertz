package tower_defense;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	
	public DamageTrap() {
		Console.println(this + " = new DamageTrap()");
	}
	
	@Override
	public String toString() {
		return "damageTrap#" + id;
	}
}
