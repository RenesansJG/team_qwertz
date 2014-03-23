package tower_defense;

import java.io.Serializable;

public class HP implements Serializable {
	private static final long serialVersionUID = 8678684793491126409L;
	// private double HP;
	// resistMap (?)
	
	// sebzõdés
	public void takeDamage(Damage damage) {
		Console.println(this + ".damage(" + damage + ")");
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return ("hp");
	}
}
