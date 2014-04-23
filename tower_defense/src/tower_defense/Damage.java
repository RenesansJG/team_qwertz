package tower_defense;

import java.io.Serializable;

// egy GameObject sebz�se
public class Damage implements Serializable {
	private static final long serialVersionUID = -1059220976860524923L;
	
	// red, green, blue sebz�sek
	protected double Red;
	protected double Green;
	protected double Blue;
	
	// konstr
	public Damage(double red, double green, double blue){
		Red=red;
		Green=green;
		Blue=blue;
	}
	
	// sebz�s m�sol�sa
	public Damage Clone()
	{
		// �j sebz�st k�sz�t�nk
		return new Damage(Red,Green,Blue);
	}
	
	// piros sebz�s getter
	public double getRedDamage()
	{
		return Red;
	}
	
	// z�ld sebz�s getter
	public double getGreenDamage()
	{
		return Green;
	}
	
	// k�k sebz�s getter
	public double getBlueDamage()
	{
		return Blue;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return "redDamage: " + Red + 
				" greenDamage: " + Green +
				" blueDamage: " + Blue;
	}
}
