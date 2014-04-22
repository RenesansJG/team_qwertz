package tower_defense;

import java.util.List;

public class DamageTrap extends Trap {

	private static final long serialVersionUID = 9047501422958390396L;
	private static final Damage damage = new Damage(3,3,3);
	private double damageMultiplier;
	
	
	public void modifyDamageMultiplier(double multiplier)
	{
		damageMultiplier += multiplier;
	}
	
	
	// sebzõ csapda konstruktor
	protected DamageTrap(double x, double y) {
		super(x, y);
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
				e.takeDamage(damage,damageMultiplier);
			}
		}
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
		Console.println(this + " megkapta " + effect + " effektet.");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damageTrap#" + id + " " + getPosString();
	}
}
