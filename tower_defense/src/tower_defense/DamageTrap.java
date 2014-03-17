package tower_defense;

import java.io.IOException;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	
	public DamageTrap() {
		Console.println(this + " = new DamageTrap()");
	}
	
	@Override
	public final boolean action() throws IOException {
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
		return "damageTrap#" + id;
	}
}
