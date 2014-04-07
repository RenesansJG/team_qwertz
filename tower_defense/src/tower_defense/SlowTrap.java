package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	// lassító csapda konstruktor
	public SlowTrap() {

	}
	
	// lassító csapda tevékenysége
	@Override
	public final boolean action() throws IOException {
		// lassítandó objektumok listája
		List<GameObject> objectsToSlow = new ArrayList<GameObject>();
		
		// objektumok bekérése a user-tõl
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// a bekért objektumot hozzáadjuk a listához
			objectsToSlow.add(object);
		}
		
		// végigmegyünk a lassítandó objektumokon
		for (GameObject object : objectsToSlow) {
			Effect effect = new SlowEffect();
			
			// mindegyiket lelassítjuk egy-egy SlowEffect-tel
			object.addEffect(effect);
			object.affect(effect);
		}
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		Console.indent();
		
		effect.apply(this);
		
		Console.deIndent();
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
