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
		double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
		boolean canmove=true;
		while(canmove){
			if(distance<movementSpeed)
			{
				//új node
				movementSpeed-=distance;
			}
			else
			{
				//mozog a cél felé!
				canmove=false;
			}
		}
		return false;
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
