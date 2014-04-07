package tower_defense;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Damage implements Serializable {
	private static final long serialVersionUID = -1059220976860524923L;
	
	public enum DamageType
	{
		red,
		green,
		blue
	}
	
	Map<DamageType, Double> damageMap = new HashMap<DamageType,Double>();
	
	public Damage(){
		
	}
	
	// toString függvény kiíratáshoz
	@Override
	public String toString() {
		return "damage";
	}
}
