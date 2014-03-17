package tower_defense;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	public SlowTrap() {
		Console.println(this + " = new SlowTrap()");
	}
	
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
