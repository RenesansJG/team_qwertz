package tower_defense;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	protected final int id;
	
	//protected double x;
	//protected double y;
	protected final List<Effect> effects;
	
	protected GameObject() {
		id = Game.getNextObjectId();
		
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action() throws IOException; // tevékenység
	public abstract void affect(Effect effect);          // accept függvény visitor patternhez
	
	// összes effekt léptetése (applyTick minden effecten)
	public final void effect() {
		Console.println(this + ".effect()");
		Console.indent();
		
		Iterator<Effect> effectIt = effects.iterator();
		
		// minden effektre a listában
		while (effectIt.hasNext()) {
			Effect effect = effectIt.next();
			
			// alkalmazzuk a ticket, és megnézzük hogy lejárt-e
			boolean effectIsToBeRemoved = effect.applyTick();
			
			// ha lejárt, töröljük
			if (effectIsToBeRemoved) {
				effectIt.remove();
			}
		}
		
		Console.deIndent();
	}
	
	// effekt hozzáadása
	public final void addEffect(Effect effect) {
		Console.println(this + ".addEffect(" + effect + ")");
		
		effects.add(effect);
	}
	
	// tick alkalmazása
	@Override
	public final boolean applyTick() throws IOException {
		Console.println(this + ".applyTick()");
		Console.indent();
		
		// effektek léptetése és az objektum tevékenységének meghívása
		effect();                         // léptetés
		boolean isToBeRemoved = action(); // tevékenység, visszatér, hogy törlendõ-e az objektum
		
		Console.deIndent();
		return isToBeRemoved; // visszatérünk, hogy törlendõ-e
	}
	
	// távolság számítása
	public final double getDistance(GameObject object) {
		Console.println(this + ".getDistance(" + object + ")");
		
		return 0; // egyelõre nem számolunk távolságot, 0-val tér vissza
	}
	
	// ellenséges-e a gameObject
	public boolean isEnemy() {
		Console.println(this + ".isEnemy()");
		
		return false; // alapból egy GameObject nem ellenséges
	}
	
	@Override
	public abstract String toString();
}
