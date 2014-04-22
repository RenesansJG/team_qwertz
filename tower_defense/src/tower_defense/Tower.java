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
	
	private double tickCount = 0;
	

	protected Tower(double x, double y) {
		super(x, y);
	}	
	
	public void modifyRange(double constant)
	{
		range += constant;
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
			tickCount += 1;
			
			List<GameObject> objects = Game.getMap().getObjects();
	
			int closestindex=-1;
			for(int i=0;i<objects.size();i++){
				if(objects.get(i).isEnemy() && getDistance(objects.get(i))<getDistance(objects.get(closestindex)))
				{
					closestindex=i;
				}
			}
			
			//Lövés
			if(closestindex!=-1){												//TimerTick --> timer hány ms-ra van állítva
				GameObject closest = objects.get(closestindex);					//  1000/TimerTick/attackspeed
				if(getDistance(closest)<=range * rangeMultiplier && 
						tickCount >= 1000/1000/(attackSpeed*attackSpeedMultiplier))
				{
					Game.getMap().addObject(new Projectile(x,y,
							closest.x,closest.y,
							projectileSpeed,projectileAoE,
							calculateProjectileDamage(projectileDamage, projectileDamageMultiplier)));
					tickCount = 0;   //Mivel lõttünk, újraindul a számlálás
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
	
	
	private Damage calculateProjectileDamage(Damage dmg, double mult){
		Damage temp = new Damage(dmg.getRedDamage()*mult,
								 dmg.getGreenDamage() * mult,
								 dmg.getBlueDamage() * mult);
			
		return temp;
	}
	
	
	// torony fejlesztés
	public void damageUpgrade() {
		projectileDamage = new Damage(projectileDamage.getRedDamage(),
									  projectileDamage.getGreenDamage(),
									  projectileDamage.getBlueDamage());
	}
	
	
	public void rangeUpgrade() {
		range += 10;
	}

	public void attackSpeedUpgrade() {
		attackSpeed += 0.2;
	}
	
	public void projectileSpeedUpgrade() {
		projectileSpeed += 10;
	}
	
	public void AoEUpgrade() {
		projectileAoE += 10;
	}

	
	
	
}
