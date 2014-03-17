package tower_defense;

import java.io.IOException;
import java.util.List;

public class Projectile extends MovableGameObject {
	private static int cnt;
	private final int id;
	
	private Damage damage;
	private double AoE;
	
	public Projectile() {
		id = cnt++;
		
		damage = new Damage();
	}
	
	@Override
	public boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		boolean ret = false;
		
		Console.println("Robbanjon a l�ved�k?");
		int choice = Console.choose("igen", "nem");
		
		// Ha robban a l�ved�k
		if (choice == 0) {
			explode();
			ret = true;
		}
		
		Console.deIndent();
		return ret;
	}
	
	// l�ved�k robban�sa
	public void explode() {
		Console.println(this + ".explode()");
		Console.indent();
		
		// objektumok list�j�nak lek�r�se
		List<GameObject> objects = Game.getMap().getObjects();
		
		for (GameObject object : objects) {
			if (object.isEnemy()) {
				Enemy enemy = (Enemy) object;
				enemy.getHP().takeDamage(damage);
			}
		}
		
		Console.deIndent();
	}
	
	@Override
	public void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		// nem ad ki effektet
	}

	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
