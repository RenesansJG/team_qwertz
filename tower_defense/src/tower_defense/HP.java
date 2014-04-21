package tower_defense;

import java.io.Serializable;

public class HP implements Serializable {
	private static final long serialVersionUID = 8678684793491126409L;
	private double HP;
	private double Red;
	private double Green;
	private double Blue;
	
	public HP(double hp, double red, double green, double blue)
	{
		HP=hp;
		Red=red;
		Green=green;
		Blue=blue;
	}
	
	// sebzõdés
	public void takeDamage(Damage damage, double modifier) {
		HP-=damage.getRedDamage()*(1-Red)*modifier;
		HP-=damage.getGreenDamage()*(1-Green)*modifier;
		HP-=damage.getBlueDamage()*(1-Blue)*modifier;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return ("hp");
	}

	public double getHP() {
		return HP;
	}
}
