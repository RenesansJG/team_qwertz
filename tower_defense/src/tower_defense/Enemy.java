package tower_defense;

import java.util.List;


public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected HP hp;
	protected Node targetNode;
	protected int level;

	protected Enemy(Node startNode, double movementSpeed, int level) {
		super(startNode.getX(), startNode.getY(), movementSpeed);
		this.targetNode=startNode;
		this.level=level;
	}
	
	
	// sebzõdés
	public final void takeDamage(Damage damage, double multiplier) {
		// a HP objetumán meghívja a sebzõdést
		hp.takeDamage(damage, multiplier);
	}
	
	// ellenség tevékenysége
	@Override
	public final boolean action() {
		if(hp.getHP()<=0)
			return true;
		double steps = movementSpeed*movementSpeedMultiplier;
		boolean canmove=true;
		while(canmove){
			double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
			if(distance<=steps)
			{
				x=targetNode.getX();
				y=targetNode.getY();
				steps-=distance;
				List<Node> nodes = targetNode.getNextNodes();
				targetNode= nodes.get(Game.rnd.nextInt(nodes.size()));
				if(steps==0)
					canmove=false;
			}
			else
			{
				double dx = this.x - targetNode.getX();
				double dy = this.x - targetNode.getY();
				double vector = Math.sqrt(dx*dx + dy*dy);
				dx/=vector;
				dy/=vector;
				x+=dx*steps;
				y+=dy*steps;
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
