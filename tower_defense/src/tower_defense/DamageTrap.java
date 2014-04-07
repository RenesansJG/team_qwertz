package tower_defense;

import java.util.List;

public class DamageTrap extends Trap {
	private static final long serialVersionUID = 9047501422958390396L;
	private final Damage damage;
	private double damageMultiplier;
	
	// sebzõ csapda konstruktor
	public DamageTrap(double x, double y) {
		this.x=x;
		this.y=y;
		damage = new Damage();
	}
	
	// sebzõ csapda tevékenysége
	@Override
	public final boolean action() {
		
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			if(o.isEnemy() && getDistance(o)<range)
			{
				Enemy e = (Enemy)o;
				e.takeDamage(damage);
			}
		}
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damageTrap#" + id;
	}
}
