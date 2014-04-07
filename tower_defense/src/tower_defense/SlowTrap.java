package tower_defense;

import java.util.List;

public class SlowTrap extends Trap {
	private static final long serialVersionUID = 8010869721215614573L;
	
	private double slow;
	private double slowMultiplier;
	
	// lass�t� csapda konstruktor
	public SlowTrap(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	// lass�t� csapda tev�kenys�ge
	@Override
	public final boolean action() {
		
		List<GameObject> objects = Game.getMap().getObjects();
		
		for(GameObject o : objects)
		{
			if(o.isEnemy() && getDistance(o)<range)
			{
				Enemy e = (Enemy)o;
				Effect effect = new SlowEffect();
				e.addEffect(effect);
				e.affect(effect);
			}
		}

		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "slowTrap#" + id;
	}
}
