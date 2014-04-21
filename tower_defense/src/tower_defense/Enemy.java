package tower_defense;


public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected HP hp;
	protected Node targetNode;
	protected int level;

	protected Enemy(Node targetNode, double movementSpeed, int level) {
		super(targetNode.getX(), targetNode.getY(), movementSpeed);
		this.targetNode=targetNode;
		this.level=level;
	}
	
	
	// sebzõdés
	public final void takeDamage(Damage damage) {
		// a HP objetumán meghívja a sebzõdést
		hp.takeDamage(damage);
	}
	
	// ellenség tevékenysége
	@Override
	public final boolean action() {
		double steps = movementSpeed;
		boolean canmove=true;
		while(canmove){
			double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
			if(distance<=steps)
			{
				x=targetNode.getX();
				y=targetNode.getY();
				steps-=distance;
				targetNode.getNextNodes();
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
