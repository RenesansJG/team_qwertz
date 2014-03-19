package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	public SlowTrap() {
		Console.println(this + " = new SlowTrap()");
	}
	
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		List<GameObject> objectsToSlow = new ArrayList<GameObject>();
		
		Console.println("Melyik objektumok vannak az akadály hatókörében? (-1 = vége)");
		
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			objectsToSlow.add(object);
		}
		
		
		for (GameObject object : objectsToSlow) {
			Effect effect = new SlowEffect();
			
			object.addEffect(effect);
			object.affect(effect);
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
	
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
