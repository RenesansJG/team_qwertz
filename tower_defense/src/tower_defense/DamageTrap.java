package tower_defense;

import java.util.List;

public class DamageTrap extends Trap {

	private static final long serialVersionUID = 9047501422958390396L;
	private static final Damage damage = new Damage(3,3,3);
	private double damageMultiplier = 1;
	
	
	public void modifyDamageMultiplier(double multiplier)
	{
		damageMultiplier += multiplier;
	}
	
	
	// sebz� csapda konstruktor
	protected DamageTrap(double x, double y) {
		super(x, y);
	}
	
	// sebz� csapda tev�kenys�ge
	@Override
	public final boolean action() {
		// gameobject lista lek�r�s
		List<GameObject> objects = Game.getMap().getObjects();
		
		// minden go-re
		for(GameObject o : objects)
		{
			// ha az enemy el�g k�zel van
			if(o.isEnemy() && getDistance(o) < range * rangeMultiplier)
			{
				Enemy e = (Enemy)o;
				// megsebz�s
				e.takeDamage(damage,damageMultiplier);
			}
		}
		// sebz� csapda nem pusztul el
		return false;
	}
	
	// effekt alkalmaz�sa
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "damageTrap";
	}
}
