package tower_defense;

import java.io.IOException;
import java.util.List;

public class Projectile extends MovableGameObject {
	private static final long serialVersionUID = -3507357373445026085L;
	
	private Damage damage;
	private double AoE;
	
	public Projectile(double x, double y, double destx,double desty ) {
		this.x=x;
		this.y=y;
		targetX=destx;
		targetY=desty;
		damage = new Damage();
	}
	
	// l�ved�k tev�kenys�ge
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
			//mozog a c�l fel�!
			return false;
		}
	}
	
	// l�ved�k robban�sa
	public void explode() throws IOException {
		
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			if(o.isEnemy() && getDistance(o)<AoE)
			{
				Enemy e = (Enemy)o;
				e.takeDamage(damage);
			}
		}
	}
	
	@Override
	public void affect(Effect effect) {
		
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
