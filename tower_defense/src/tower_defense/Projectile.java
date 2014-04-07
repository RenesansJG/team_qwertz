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
	
	// l�ved�k tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		explode();
		return true;
	}
	
	// l�ved�k robban�sa
	public void explode() throws IOException {
		Console.println(this + ".explode()");
		Console.indent();
		
		// megsebzend� objektumok
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		
		Console.printlnMsg("Melyik objektumok vannak a l�ved�k AoE-j�ben?");
		
		// objektumok k�r�se a user-t�l
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// lek�rt objektum hozz�ad�sa a list�hoz
			objectsToDamage.add(object);
		}
		
		// minden sebzend� objektumon v�gigmegy�nk
		for (GameObject object : objectsToDamage) {
			// csak akkor sebz�nk ha ellens�ges
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
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
