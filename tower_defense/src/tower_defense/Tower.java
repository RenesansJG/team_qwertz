package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;

	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.printlnMsg("Lõjön a torony?");
		
		if (Console.chooseYesNo()) {
			Console.printlnMsg("Melyik a legközelebbi ellenség? (-1 = nincs)");
			Console.printMsg("ID: ");
			
			GameObject object = Console.getObjectFromUser();
			
			if (object != null && object.isEnemy()) {
				Console.printlnMsg("Benne van a hatókörben?");
				
				if (Console.chooseYesNo()) {
					Game.getMap().addObject(new Projectile());
				}
			}
		}
		
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
	
	public void upgrade() {
		Console.println(this + ".upgrade()");
	}
}
