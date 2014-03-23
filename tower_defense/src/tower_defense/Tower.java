package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	// torony tevékenysége
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.printlnMsg("Lõjön a torony?");
		
		// ha lõ a torony
		if (Console.chooseYesNo()) {
			Console.printlnMsg("Melyik a legközelebbi ellenség?");
			
			// bekérjük a legközelebbi objektumot a usertõl
			GameObject object = Console.getObjectFromUser();
			
			// ha ellenséges
			if (object != null && object.isEnemy()) {
				Console.printlnMsg("Benne van a hatókörben?");
				
				// és ha benne van a hatókörben
				if (Console.chooseYesNo()) {
					// kilövünk egy lövedéket
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
	
	// torony fejlesztés
	public void upgrade() {
		Console.println(this + ".upgrade()");
	}
}
