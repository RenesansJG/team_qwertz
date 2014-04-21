package tower_defense;

import java.io.Serializable;

public class Damage implements Serializable {
	private static final long serialVersionUID = -1059220976860524923L;
	
	double Red;
	double Green;
	double Blue;
	
	public Damage(double red, double green, double blue){
		Red=red;
		Green=green;
		Blue=blue;
	}
	
	public double getRedDamage()
	{
		return Red;
	}
	
	public double getGreenDamage()
	{
		return Green;
	}
	
	public double getBlueDamage()
	{
		return Blue;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damage";
	}
}
