package tower_defense;

import java.io.IOException;
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
		
		Console.println("Robbanjon a lövedék?");
		
		// Ha robban a lövedék
		if (Console.chooseYesNo()) {
			explode();
			ret = true;
		}
		
		Console.deIndent();
		return ret;
	}
	
	// lövedék robbanása
	public void explode() {
		Console.println(this + ".explode()");
		Console.indent();
		
		for (GameObject object : Game.getMap().getObjects()) {
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
		Console.indent();
		
		// nem ad ki effektet
		
		Console.deIndent();
	}

	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
