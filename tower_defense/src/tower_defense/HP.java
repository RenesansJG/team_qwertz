package tower_defense;

import java.io.Serializable;

public class HP implements Serializable {
	private static final long serialVersionUID = 8678684793491126409L;
	private double HP;
	private double Red;
	private double Green;		//Sebz�s le van bontva elementekre
	private double Blue;
	
	//K�l�n meg kell adni minden komponenst
	public HP(double hp, double red, double green, double blue)
	{
		HP=hp;
		Red=red;
		Green=green;
		Blue=blue;
	}
	
	//Sz�tes�shez kell
	public HP(HP hp)
	{
		HP=hp.HP;
		Red=hp.Red;
		Green=hp.Green;
		Blue=hp.Blue;
	}
	
	// sebz�d�s
	public void takeDamage(Damage damage, double modifier) {
		HP-=damage.getRedDamage()*(1-Red)*modifier;
		HP-=damage.getGreenDamage()*(1-Green)*modifier;
		HP-=damage.getBlueDamage()*(1-Blue)*modifier;
	}
	
	// toString f�ggv�ny ki�rat�shoz
	@Override
	public String toString() {
		return ("hp");
	}

	public double getHP() {
		return HP;
	}
}
