package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	// lass�t� csapda konstruktor
	public SlowTrap() {

	}
	
	// lass�t� csapda tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		// lass�tand� objektumok list�ja
		List<GameObject> objectsToSlow = new ArrayList<GameObject>();
		
		// objektumok bek�r�se a user-t�l
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// a bek�rt objektumot hozz�adjuk a list�hoz
			objectsToSlow.add(object);
		}
		
		// v�gigmegy�nk a lass�tand� objektumokon
		for (GameObject object : objectsToSlow) {
			Effect effect = new SlowEffect();
			
			// mindegyiket lelass�tjuk egy-egy SlowEffect-tel
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
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
