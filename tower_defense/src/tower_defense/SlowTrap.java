package tower_defense;

import java.util.List;

public class SlowTrap extends Trap {

	private static final long serialVersionUID = 8010869721215614573L;
	
	private static final double slow = 0.65;
	public double slowMultiplier = 1;
	
	// lass�t� csapda konstruktor
	protected SlowTrap(double x, double y) {
		super(x, y);
	}
	
	public void modifySlowMultiplier(double multiplier)
	{
		slowMultiplier += multiplier;
	}
	
	// lass�t� csapda tev�kenys�ge
	@Override
	public final boolean action() {
		
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			//Enemy �s range-ban van, akkor lass�tani kell
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
	
	@Override	//Egy effect berak�sa
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowTrap";
	}
}
