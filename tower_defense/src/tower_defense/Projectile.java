package tower_defense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Projectile extends MovableGameObject {
	private static final long serialVersionUID = -3507357373445026085L;
	
	
	
	private Damage damage;
	//private double AoE;
	
	public Projectile(double x, double y, double destx,double desty ) {
		this.x=x;
		this.y=y;
		targetX=destx;
		targetY=desty;
		damage = new Damage();
	}
	
	// lövedék tevékenysége
	@Override
	public final boolean action() throws IOException {
		double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
		if(distance<movementSpeed)
		{
			explode();
			return true;
		}
		else
		{
			//mozog a cél felé!
			return false;
		}
	}
	
	// lövedék robbanása
	public void explode() throws IOException {
		// megsebzendõ objektumok
		List<GameObject> objectsToDamage = new ArrayList<GameObject>();
		// objektumok kérése a user-tõl
		while (true) {
			GameObject object = Console.getObjectFromUser();
			
			if (object == null) {
				break;
			}
			
			// lekért objektum hozzáadása a listához
			objectsToDamage.add(object);
		}
		
		// minden sebzendõ objektumon végigmegyünk
		for (GameObject object : objectsToDamage) {
			// csak akkor sebzünk ha ellenséges
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
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
