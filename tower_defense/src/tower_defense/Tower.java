package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {
	private static final long serialVersionUID = 641853699471809737L;
	
	// torony tevékenysége
	@Override
	public final boolean action() throws IOException {
		// ha lõ a torony
		if (true) {
			// bekérjük a legközelebbi objektumot
			GameObject object = null;
			
			// ha ellenséges
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
	
	// torony fejlesztés
	public void upgrade() {
		
	}
}
