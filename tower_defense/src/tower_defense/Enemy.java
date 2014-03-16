package tower_defense;

import java.io.IOException;

public abstract class Enemy extends MovableGameObject {
	protected final HP hp;
	
	protected Enemy() {
		hp = new HP();
	}
	
	public HP getHP() {
		return hp;
	}
	
	@Override
	public void action() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affect(Effect effect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
