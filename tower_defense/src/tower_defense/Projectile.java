package tower_defense;

import java.util.List;

public class Projectile extends MovableGameObject {
	private static final long serialVersionUID = -3507357373445026085L;
	
	private Damage damage;
	private double AoE;
	
	public Projectile(double x, double y, double destx,double desty, double speed, double Aoe,Damage damage ) {
		super(x,y,speed);
		targetX = destx;
		targetY = desty;
		this.AoE = Aoe;
		this.damage = damage.Clone();
	}
	
	// l�ved�k tev�kenys�ge
	@Override
	public final boolean action() {
		double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
		if(distance<=movementSpeed)
		{
			x=targetX;
			y=targetY;
			explode();
			return true;
		}
		else
		{
			double dx = targetX-x;
			double dy = targetY-y;
			double vector = Math.sqrt(dx*dx + dy*dy);
			dx/=vector;
			dy/=vector;
			x+=dx*movementSpeed;
			y+=dy*movementSpeed;
			return false;
		}
	}
	
	// l�ved�k robban�sa
	public void explode(){
		Console.println(this + " felrobban." +
				"redDamage: " + damage.getRedDamage() + 
				" greenDamage: " + damage.getGreenDamage() +
				" blueDamage: " + damage.getBlueDamage());
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			if(o.isEnemy() && getDistance(o)<AoE)
			{
				Enemy e = (Enemy)o;
				e.takeDamage(damage,1);
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
