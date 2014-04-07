package tower_defense;


public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected final HP hp;
	protected Node targetNode;
	
	protected Enemy() {
		hp = new HP();
		targetNode = Game.getMap().getNodes().get(0);
	}
	
	// sebz�d�s
	public final void takeDamage(Damage damage) {
		// a HP objetum�n megh�vja a sebz�d�st
		hp.takeDamage(damage);
	}
	
	// ellens�g tev�kenys�ge
	@Override
	public final boolean action() {
		// van-e m�g fennmarad� t�vols�g a tickben?
		boolean remainingDistanceBiggerThanZero = true;
		
		while (remainingDistanceBiggerThanZero) {
			// el�rte-e m�r az enemy a targetNode-j�t?
			boolean reachedTarget = true;
			
			if (reachedTarget) {
				// ha igen, lek�rj�k a k�vetkez� node-ok list�j�t
				targetNode.getNextNodes();
			}
			// van-e m�g fennmarad� t�vols�g a tickben?
			remainingDistanceBiggerThanZero = true;
		}
		// meghalt-e az enemy?
		boolean isDead = true;
		
		return isDead; // visszaadjuk, meghalt-e
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// override-oljuk az isEnemy f�ggv�nyt
	@Override
	public final boolean isEnemy() {
		return true; // az enemy ellens�ges
	}
}
