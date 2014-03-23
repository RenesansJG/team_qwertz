package tower_defense;

import java.io.IOException;

public abstract class Enemy extends MovableGameObject {
	private static final long serialVersionUID = 6413219852626908584L;
	protected final HP hp;
	protected Node targetNode;
	
	protected Enemy() {
		hp = new HP();
		targetNode = Game.getMap().getNodes().get(0);
	}
	
	// sebz�d�s
	public final void takeDamage(Damage damage) {
		Console.println(this + ".takeDamage(" + damage + ")");
		Console.indent();
		
		// a HP objetum�n megh�vja a sebz�d�st
		hp.takeDamage(damage);
		
		Console.deIndent();
	}
	
	// ellens�g tev�kenys�ge
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.printlnMsg("Van m�g fennmarad� t�vols�g?");
		// van-e m�g fennmarad� t�vols�g a tickben?
		boolean remainingDistanceBiggerThanZero = Console.chooseYesNo();
		
		while (remainingDistanceBiggerThanZero) {
			Console.printlnMsg("El�rte m�r a k�vetkez� node-ot?");
			// el�rte-e m�r az enemy a targetNode-j�t?
			boolean reachedTarget = Console.chooseYesNo();
			
			if (reachedTarget) {
				// ha igen, lek�rj�k a k�vetkez� node-ok list�j�t
				targetNode.getNextNodes();
			}
			
			Console.printlnMsg("Van m�g fennmarad� t�vols�g?");
			// van-e m�g fennmarad� t�vols�g a tickben?
			remainingDistanceBiggerThanZero = Console.chooseYesNo();
		}
		
		Console.printlnMsg("Meghalt az ellens�g?");
		// meghalt-e az enemy?
		boolean isDead = Console.chooseYesNo();
		
		Console.deIndent();
		return isDead; // visszaadjuk, meghalt-e
	}
	
	@Override
	public final void affect(Effect effect) {
		Console.println(this + ".affect(" + effect + ")");
		Console.indent();
		
		effect.apply(this);
		
		Console.deIndent();
	}
	
	// override-oljuk az isEnemy f�ggv�nyt
	@Override
	public final boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return true; // az enemy ellens�ges
	}
}
