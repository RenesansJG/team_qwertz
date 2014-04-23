package tower_defense;

import java.io.Serializable;

// egy GameObject sebzése
public class Damage implements Serializable {
	private static final long serialVersionUID = -1059220976860524923L;
	
	// red, green, blue sebzések
	protected double Red;
	protected double Green;
	protected double Blue;
	
	// konstr
	public Damage(double red, double green, double blue){
		Red=red;
		Green=green;
		Blue=blue;
	}
	
	// sebzés másolása
	public Damage Clone()
	{
		// új sebzést készítünk
		return new Damage(Red,Green,Blue);
	}
	
	// piros sebzés getter
	public double getRedDamage()
	{
		return Red;
	}
	
	// zöld sebzés getter
	public double getGreenDamage()
	{
		return Green;
	}
	
	// kék sebzés getter
	public double getBlueDamage()
	{
		return Blue;
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "redDamage: " + Red + 
				" greenDamage: " + Green +
				" blueDamage: " + Blue;
	}
}
