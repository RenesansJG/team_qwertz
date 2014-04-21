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
	public void takeDamage(Damage damage) {

	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return ("hp");
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}
}
