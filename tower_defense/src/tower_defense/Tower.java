package tower_defense;

import java.io.IOException;

public abstract class Tower extends GameObject {

	@Override
	public boolean action() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void affect(Effect effect) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public abstract String toString();
}
