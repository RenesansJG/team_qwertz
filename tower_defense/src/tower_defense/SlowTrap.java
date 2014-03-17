package tower_defense;

import java.io.IOException;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	public SlowTrap() {
		Console.println(this + " = new SlowTrap()");
	}
	
	@Override
	public boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		// TODO Auto-generated method stub
		
		Console.deIndent();
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		Console.indent();
		
		effect.apply(this);
		
		Console.deIndent();
	}
	
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
