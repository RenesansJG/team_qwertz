package tower_defense;

import java.io.Serializable;

public class HP implements Serializable {
	private static final long serialVersionUID = 8678684793491126409L;
	private double HP;
	//resistMap (?)
	
	// sebz�d�s
	public void takeDamage(Damage damage) {

	}
	
	// toString f�ggv�ny ki�rat�shoz
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
