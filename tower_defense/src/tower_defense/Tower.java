package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	// torony tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		// ha l� a torony
		if (true) {
			// bek�rj�k a legk�zelebbi objektumot
			GameObject object = null;
			
			// ha ellens�ges
			if (object!=null && object.isEnemy()) {
				Game.getMap().addObject(new Projectile());
			}
		}
		return false;
	}
	
	@Override
	public final void affect(Effect effect) {
		effect.apply(this);
	}
	
	// torony fejleszt�s
	public void upgrade() {
		
	}
}
