package tower_defense;

import java.io.IOException;

public interface ITickable {
	boolean applyTick() throws IOException;
}
