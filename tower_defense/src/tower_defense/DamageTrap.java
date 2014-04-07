package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	private final Damage damage;
	
	// sebzõ csapda konstruktor
	public DamageTrap() {
		damage = new Damage();
	}
	
	// sebzõ csapda tevékenysége
	@Override
	public final boolean action() throws IOException {
		// sebzendõ objektumok listája
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		
		// objektumok bekérése a user-tõl
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// a bekért objektumot hozzáadjuk a listához
			objectsToDamage.add(object);
		}
		
		// végigmegyünk a sebzendõ objektumokon
		for (GameObject object : objectsToDamage) {
			if (object.isEnemy()) {
				// ha ellenséges, megsebezzük
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
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damageTrap#" + id;
	}
}
