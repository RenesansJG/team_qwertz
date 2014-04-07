package tower_defense;


public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected final HP hp;
	protected Node targetNode;
	
	protected Enemy() {
		hp = new HP();
		targetNode = Game.getMap().getNodes().get(0);
	}
	
	// sebzõdés
	public final void takeDamage(Damage damage) {
		// a HP objetumán meghívja a sebzõdést
		hp.takeDamage(damage);
	}
	
	// ellenség tevékenysége
	@Override
	public final boolean action() {
		// van-e még fennmaradó távolság a tickben?
		boolean remainingDistanceBiggerThanZero = true;
		
		while (remainingDistanceBiggerThanZero) {
			// elérte-e már az enemy a targetNode-ját?
			boolean reachedTarget = true;
			
			if (reachedTarget) {
				// ha igen, lekérjük a következõ node-ok listáját
				targetNode.getNextNodes();
			}
			// van-e még fennmaradó távolság a tickben?
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
	
	// override-oljuk az isEnemy függvényt
	@Override
	public final boolean isEnemy() {
		return true; // az enemy ellenséges
	}
}
