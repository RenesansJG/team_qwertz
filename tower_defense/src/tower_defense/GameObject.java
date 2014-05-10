package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	protected final int id;
	
	protected double x;
	protected double y;
	protected final List<Effect> effects;
	
	//Alap konstruktor mindenkire
	protected GameObject(double x, double y) {
		id = Game.getNextObjectId();
		this.x=x;
		this.y=y;
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action(); // tevékenység
	public abstract void affect(Effect effect);          // accept függvény visitor patternhez
	
	// összes effekt léptetése (applyTick minden effecten)
	public final void effect() {
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
	}
	
	// effekt hozzáadása
	public final void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	// tick alkalmazása
	@Override
	public final boolean applyTick(){
		// effektek léptetése és az objektum tevékenységének meghívása
		effect();                         // léptetés
		boolean isToBeRemoved = action(); // tevékenység, visszatér, hogy törlendõ-e az objektum
		
		return isToBeRemoved; // visszatérünk, hogy törlendõ-e
	}
	
	// távolság számítása
	public final double getDistance(GameObject object) {
		return Math.sqrt(Math.pow((x-object.x), 2)+Math.pow((y-object.y), 2)); // egyelõre nem számolunk távolságot, 0-val tér vissza
	}
	
	// ellenséges-e a gameObject
	public boolean isEnemy() {
		return false; // alapból egy GameObject nem ellenséges
	}
	
	// trap-e a gameObject
	public boolean isTrap() {
		return false; // alapból egy GameObject nem trap
	}
		
	// tower-e a gameObject
	public boolean isTower() {
		return false; // alapból egy GameObject nem tower
	}
	
	@Override
	public abstract String toString();
	
	public final String getPosString() {
		return "(" + x + ", " + y + ")";
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public List<Effect> getEffects() {
		return new ArrayList<Effect>(effects);
	}
}
