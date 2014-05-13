package tower_defense;

import java.util.List;

public class SlowTrap extends Trap {

	private static final long serialVersionUID = 8010869721215614573L;
	
	private static final double slow = 0.65;
	public double slowMultiplier = 1;
	
	// lassító csapda konstruktor
	protected SlowTrap(double x, double y) {
		super(x, y);
	}
	
	public void modifySlowMultiplier(double multiplier)
	{
		slowMultiplier += multiplier;
	}
	
	// lassító csapda tevékenysége
	@Override
	public final boolean action() {
		
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			//Enemy és range-ban van, akkor lassítani kell
			if(o.isEnemy() && getDistance(o) < range * rangeMultiplier)
			{
				Enemy e = (Enemy)o;
				Effect effect = new SlowEffect(slow*slowMultiplier);
				e.addEffect(effect);
				e.affect(effect);
			}
		}

		return false;
	}
	
	@Override	//Egy effect berakása
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "slowTrap";
	}
}
