package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	private final Damage damage;
	
	// sebz� csapda konstruktor
	public DamageTrap() {
		Console.println(this + " = new DamageTrap()");
		Console.indent();
		
		damage = new Damage();
		
		Console.deIndent();
	}
	
	// sebz� csapda tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		// sebzend� objektumok list�ja
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		
		Console.printlnMsg("Melyik objektumok vannak az akad�ly hat�k�r�ben?");
		
		// objektumok bek�r�se a user-t�l
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// a bek�rt objektumot hozz�adjuk a list�hoz
			objectsToDamage.add(object);
		}
		
		// v�gigmegy�nk a sebzend� objektumokon
		for (GameObject object : objectsToDamage) {
			if (object.isEnemy()) {
				// ha ellens�ges, megsebezz�k
				Enemy enemy = (Enemy) object;
				enemy.takeDamage(damage);
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
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "damageTrap#" + id;
	}
}
