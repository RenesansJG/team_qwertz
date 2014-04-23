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
		targetX=startNode.getX();
		targetY=startNode.getY();
		this.level=level;
		this.movementSpeedMultiplier=1;
	}
	
	protected Enemy(Enemy source) {
		super(source.x + 3, source.y + 3, source.movementSpeed);
		this.targetX = source.targetX;
		this.targetY = source.targetY;
		this.targetNode=source.targetNode;
		this.level = source.level;
		this.movementSpeedMultiplier=source.movementSpeedMultiplier;
		this.hp = new HP(source.hp);
	}
	
	protected abstract Enemy Copy(Enemy source);
	
	// sebzõdés
	public final void takeDamage(Damage damage, double modifier) {
		// a HP objetumán meghívja a sebzõdést
		double a = hp.getHP();
		hp.takeDamage(damage, modifier);
		if (Game.nextBoolean(0.05)) // szétesés
		{
			Game.getMap().addObject(Copy(this));
			Console.println(this + " szétesett");
		}
		Console.println(this+" sebzõdött " + (a-hp.getHP())+"-vel jelenlegi HP: " +hp.getHP());
	}
	
	// ellenség tevékenysége
	@Override
	public final boolean action() {
		if(hp.getHP()<=0){
			Console.println(this + " meghalt");
			return true;
		}
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
				if(nodes.size()==0)
				{
					Game.loseGame();
					return true;
				}
				targetNode= nodes.get(Game.nextInt(nodes.size()));
				targetX=targetNode.getX();
				targetY=targetNode.getY();
				if(steps==0)
					canmove=false;
			}
			else
			{
				double dx = targetNode.getX()-x;
				double dy = targetNode.getY()-y;
				double vector = Math.sqrt(dx*dx + dy*dy);
				dx/=vector;
				dy/=vector;
				x+=dx*steps;
				y+=dy*steps;
				canmove=false;
			}
		}
		Console.println(this+ " mozgott az x=" + x + " y=" + y);
		return false;
	}
	
	// ellenség megölésekor a kapott varázserõt adja meg
	public abstract double getMagicPower();
	
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
