package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	private final Damage damage;
	
	public DamageTrap() {
		Console.println(this + " = new DamageTrap()");
		
		damage = new Damage();
	}
	
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		
		Console.println("Melyik objektumok vannak az akadály hatókörében? (-1 = vége)");
		
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			objectsToDamage.add(object);
		}
		
		
		for (GameObject object : objectsToDamage) {
			if (object.isEnemy()) {
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
	
	@Override
	public String toString() {
		return "damageTrap#" + id;
	}
}
