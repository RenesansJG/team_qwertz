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
	
	
	// sebzõ csapda konstruktor
	protected DamageTrap(double x, double y) {
		super(x, y);
	}
	
	// sebzõ csapda tevékenysége
	@Override
	public final boolean action() {
		// gameobject lista lekérés
		List<GameObject> objects = Game.getMap().getObjects();
		
		// minden go-re
		for(GameObject o : objects)
		{
			// ha az enemy elég közel van
			if(o.isEnemy() && getDistance(o) < range * rangeMultiplier)
			{
				Enemy e = (Enemy)o;
				// megsebzés
				e.takeDamage(damage,damageMultiplier);
			}
		}
		// sebzõ csapda nem pusztul el
		return false;
	}
	
	// effekt alkalmazása
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damageTrap";
	}
}
