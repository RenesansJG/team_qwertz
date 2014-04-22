package tower_defense;

import java.io.Serializable;

public class Saruman implements Serializable {
	private static final long serialVersionUID = 4476727615006013244L;
	
	private double magicPower;

	public double getMagicPower() {
		return magicPower;
	}

	public void setMagicPower(double magicPower) {
		this.magicPower = magicPower;
	}
}
