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
	
	// sebzõdés
	public final void takeDamage(Damage damage) {
		Console.println(this + ".takeDamage(" + damage + ")");
		Console.indent();
		
		// a HP objetumán meghívja a sebzõdést
		hp.takeDamage(damage);
		
		Console.deIndent();
	}
	
	// ellenség tevékenysége
	@Override
	public final boolean action() throws IOException {
		Console.println(this + ".action()");
		Console.indent();
		
		Console.printlnMsg("Van még fennmaradó távolság?");
		// van-e még fennmaradó távolság a tickben?
		boolean remainingDistanceBiggerThanZero = Console.chooseYesNo();
		
		while (remainingDistanceBiggerThanZero) {
			Console.printlnMsg("Elérte már a következõ node-ot?");
			// elérte-e már az enemy a targetNode-ját?
			boolean reachedTarget = Console.chooseYesNo();
			
			if (reachedTarget) {
				// ha igen, lekérjük a következõ node-ok listáját
				targetNode.getNextNodes();
			}
			
			Console.printlnMsg("Van még fennmaradó távolság?");
			// van-e még fennmaradó távolság a tickben?
			remainingDistanceBiggerThanZero = Console.chooseYesNo();
		}
		
		Console.printlnMsg("Meghalt az ellenség?");
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
	
	// override-oljuk az isEnemy függvényt
	@Override
	public final boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return true; // az enemy ellenséges
	}
}
