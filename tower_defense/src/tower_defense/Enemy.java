package tower_defense;

import java.util.List;


public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected HP hp; // enemy hpja
	protected Node targetNode; // c�l node
	protected int level; // enemy szintje (�rte kapott var�zser�)

	// enemy ctor
	protected Enemy(Node startNode, double movementSpeed, int level) {
		super(startNode.getX(), startNode.getY(), movementSpeed);
		this.targetNode=startNode;
		targetX=startNode.getX();
		targetY=startNode.getY();
		this.level=level;
	}
	
	// enemy copy ctor
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
	
	// sebz�d�s
	public final void takeDamage(Damage damage, double modifier) {
		// a HP objetum�n megh�vja a sebz�d�st
		hp.takeDamage(damage, modifier);
		if (Game.rnd.nextDouble()<=0.05) // sz�tes�s
		{
			Game.getMap().addObject(Copy(this));
		}
	}
	
	// ellens�g tev�kenys�ge
	@Override
	public final boolean action() {
		// meghal�s ellen�rz�se
		if(hp.getHP()<=0){
			return true;
		}
		// kisz�m�tjuk mennyit kell mozognia
		double steps = movementSpeed*movementSpeedMultiplier;
		boolean canmove=true;
		// am�g nem �rte el a c�lt
		while(canmove){
			// c�lt�l val� t�vols�g kisz�m�t�sa
			double distance = Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
			// ha a t�vols�g a c�lt�l kisebb a megtehet� l�p�sekn�l
			if(distance<=steps)
			{
				// targetre helyez�s
				x=targetNode.getX();
				y=targetNode.getY();
				
				// stepek cs�kkent�se
				steps-=distance;
				// k�vetkez� node lek�r�se
				List<Node> nodes = targetNode.getNextNodes();
				// ha nincs, ez a c�l
				if(nodes.size()==0)
				{
					// elvesz�tj�k a j�t�kot
					Game.loseGame();
					return true;
				}
				// am�gy k�vetkez� node k�r�se
				targetNode= nodes.get(Game.rnd.nextInt(nodes.size()));
				// �j c�l
				targetX=targetNode.getX();
				targetY=targetNode.getY();
				// ha m�r lej�rtak a stepek nem mozoghat
				if(steps==0)
					canmove=false;
			}
			// am�gy mozg�s a c�lra
			else
			{
				// dx, dy kisz�m.
				double dx = targetNode.getX()-x;
				double dy = targetNode.getY()-y;
				double vector = Math.sqrt(dx*dx + dy*dy);
				dx/=vector;
				dy/=vector;
				// mozg�s 
				x+=dx*steps;
				y+=dy*steps;
				canmove=false;
			}
		}
		return false;
	}
	
	// ellens�g meg�l�sekor a kapott var�zser�t adja meg
	public abstract double getMagicPower();
	
	// effektek alkalmaz�sa enemy-n
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
