package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;

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
}
