package tower_defense;

import java.util.List;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	protected double range;
	protected double rangeMultiplier;
	protected double attackSpeed;
	protected double attackSpeedMultiplier;
	protected Damage projectileDamage;
	protected double projectileDamageMultiplier;
	protected double projectileSpeed;
	protected double projectileAoE;
	

	protected Tower(double x, double y) {
		super(x, y);
	}	
	
	public void modifyRangeMultiplier(double multiplier)
	{
		rangeMultiplier += multiplier;
	}
	
	public void modifyAttackspeedMultiplier(double multiplier)
	{
		attackSpeedMultiplier += multiplier;
	}
	
	public void modifyprojectileDamageMultiplier(double multiplier)
	{
		projectileDamageMultiplier += multiplier;
	}
	
	
	// torony tevékenysége
	@Override
	public final boolean action(){
		if(true){// attackspeed nincs megoldva 
			List<GameObject> objects = Game.getMap().getObjects();
	
			int closestindex=-1;
			for(int i=0;i<objects.size();i++){
				if(objects.get(i).isEnemy() && getDistance(objects.get(i))<getDistance(objects.get(closestindex)))
				{
					closestindex=i;
				}
			}
			
			if(closestindex!=-1){
				GameObject closest = objects.get(closestindex);
				if(getDistance(closest)<=range)
				{
					//fel kell még szorozni a damaget a multiplierrel
					Game.getMap().addObject(new Projectile(x,y,closest.x,closest.y,projectileSpeed,projectileAoE,projectileDamage));
				}
			}
		}
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
		Console.println(this + " megkapta " + effect + " effektet.");
	}
	
	// torony fejlesztés
	public void upgrade() {
		
	}
}
