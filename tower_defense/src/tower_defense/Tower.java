package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	// torony tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.printlnMsg("L�j�n a torony?");
		
		// ha l� a torony
		if (Console.chooseYesNo()) {
			Console.printlnMsg("Melyik a legk�zelebbi ellens�g?");
			
			// bek�rj�k a legk�zelebbi objektumot a usert�l
			GameObject object = Console.getObjectFromUser();
			
			// ha ellens�ges
			if (object != null && object.isEnemy()) {
				Console.printlnMsg("Benne van a hat�k�rben?");
				
				// �s ha benne van a hat�k�rben
				if (Console.chooseYesNo()) {
					// kil�v�nk egy l�ved�ket
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
	
	// torony fejleszt�s
	public void upgrade() {
		Console.println(this + ".upgrade()");
	}
}
