package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;

	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.println("L�j�n a torony?");
		
		if (Console.chooseYesNo()) {
			Console.println("Melyik a legk�zelebbi ellens�g? (-1 = nincs)");
			Console.printi("ID: ");
			
			GameObject object = Console.getObjectFromUser();
			
			if (object != null && object.isEnemy()) {
				Console.println("Benne van a hat�k�rben?");
				
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
}
