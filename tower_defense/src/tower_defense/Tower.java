package tower_defense;

import java.util.List;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	protected double range;
	protected double rangeMultiplier;
	protected double attackSpeed;
	protected double attackSpeedMultiplier;
	protected Damage projectileDamage;			//Torony sebzése
	protected double projectileDamageMultiplier;
	protected double projectileSpeed;
	protected double projectileAoE;
	
	private double tickCount = 0;		//Számoljuk, az actionon hány tick ment át. (attackspeedhez kell)
	

	protected Tower(double x, double y) {
		super(x, y);
		rangeMultiplier = 1;
		attackSpeedMultiplier = 1;
		projectileDamageMultiplier = 1;
		
		range = 100;
		attackSpeed = 1;		//Másodpercenként ennyit lõ
		projectileSpeed = 50;
		projectileAoE = 10;
		
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
		if(true){ 
			
			//Target választás
			tickCount += 1;
			
			List<GameObject> objects = Game.getMap().getObjects();
	
			boolean targetfound = false;
			int closestindex=-1;
			for(int i=0;i<objects.size();i++){	//Ha még nincs valid target
				if(closestindex==-1 && objects.get(i).isEnemy())
				{
					closestindex=i;
					targetfound = true;
				}
												//Egyébként   (enemy, és közelebb van mint az eddigi)
				else if(objects.get(i).isEnemy() && getDistance(objects.get(i))<=getDistance(objects.get(closestindex)))
				{
					closestindex=i;
					targetfound = true;
				}
			}

			
			//Lövés
			if(targetfound)
			{												//TimerTick --> timer hány ms-ra van állítva
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
	}
	
	
	private Damage calculateProjectileDamage(Damage dmg, double mult){
		Damage temp = new Damage(dmg.getRedDamage()*mult,
								 dmg.getGreenDamage() * mult,
								 dmg.getBlueDamage() * mult);
			
		return temp;
	}
	
	
	// torony fejlesztés
	public void damageUpgrade() {
		Damage temp = new Damage((projectileDamage.getRedDamage() +10),
									  (projectileDamage.getGreenDamage() +10),
									  (projectileDamage.getBlueDamage()) +10);
		
		projectileDamage = temp;
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
	
	// override-oljuk az isTower függvényt
	@Override
	public final boolean isTower() {
		return true; // tower
	}

	
	
	
}
