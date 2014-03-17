package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Projectile extends MovableGameObject {
	private static final long serialVersionUID = -3507357373445026085L;
	
	private Damage damage;
	//private double AoE;
	
	public Projectile() {
		damage = new Damage();
	}
	
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		boolean ret = false;
		
		Console.println("Robbanjon a l�ved�k?");
		
		// Ha robban a l�ved�k
		if (Console.chooseYesNo()) {
			explode();
			ret = true;
		}
		
		Console.deIndent();
		return ret;
	}
	
	// l�ved�k robban�sa
	public void explode() throws IOException {
		Console.println(this + ".explode()");
		Console.indent();
		
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		
		Console.println("Melyik objektumok vannak a l�ved�k AoE-j�ben? (-1 = v�ge)");
		
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
	}
	
	@Override
	public void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		Console.indent();
		
		// nem ad ki effektet
		
		Console.deIndent();
	}

	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
