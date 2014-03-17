package tower_defense;

import java.io.IOException;

public abstract class Trap extends GameObject {
	private static final long serialVersionUID = -1174807768236727847L;

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
