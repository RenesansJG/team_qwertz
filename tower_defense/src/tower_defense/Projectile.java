package tower_defense;

import java.util.List;

public class Projectile extends MovableGameObject {
	private static final long serialVersionUID = -3507357373445026085L;
	
	private Damage damage;
	private double AoE;
	
	//Alap konstruktor
	public Projectile(double x, double y, double destx,double desty, double speed, double Aoe,Damage damage ) {
		super(x,y,speed);
		targetX = destx;
		targetY = desty;
		this.AoE = Aoe;
		this.damage = damage.Clone();
	}
	
	// lövedék tevékenysége
	@Override
	public final boolean action() {
		//Lövedék mozgása (hasonló, mint az enemy-é)
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
	
	// lövedék robbanása
	public void explode(){
		List<GameObject> objects = Game.getMap().getObjects();
		
		//Az összes rangeben lévõ enemyt sebzi
		for(int i=0 ;i< objects.size();i++)
		{
			GameObject o = objects.get(i);
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
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "projectile#" + id;
	}
	
}
