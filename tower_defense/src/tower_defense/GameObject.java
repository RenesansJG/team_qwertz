package tower_defense;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements ITickable, Serializable {
	private static final long serialVersionUID = 8508068704731173934L;
	
	protected double x;
	protected double y;
	protected final List<Effect> effects;
	
	//Alap konstruktor mindenkire
	protected GameObject(double x, double y) {
		this.x=x;
		this.y=y;
		effects = new ArrayList<Effect>();
	}
	
	public abstract boolean action(); // tev�kenys�g
	public abstract void affect(Effect effect);          // accept f�ggv�ny visitor patternhez
	
	// �sszes effekt l�ptet�se (applyTick minden effecten)
	public final void effect() {
		List<Effect> expiredEffects = new ArrayList<Effect>();
		for(Effect e : effects) {
			if(e.applyTick()) {
				expiredEffects.add(e);
			}
		}
		for(Effect e : expiredEffects) {
			if(this instanceof Tower) {
				e.restore((Tower)this);
			} else if(this instanceof DamageTrap) {
				e.restore((DamageTrap)this);
			} else if(this instanceof SlowTrap) {
				e.restore((DamageTrap)this);
			}
			 else if(this instanceof Enemy) {
					e.restore((Enemy)this);
				}
			effects.remove(e);
		}
	}
	
	// effekt hozz�ad�sa
	public final void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	// tick alkalmaz�sa
	@Override
	public final boolean applyTick(){
		// effektek l�ptet�se �s az objektum tev�kenys�g�nek megh�v�sa
		boolean isToBeRemoved = action(); // tev�kenys�g, visszat�r, hogy t�rlend�-e az objektum
		effect();                         // l�ptet�s
		return isToBeRemoved; // visszat�r�nk, hogy t�rlend�-e
	}
	
	// t�vols�g sz�m�t�sa
	public final double getDistance(GameObject object) {
		return Math.sqrt(Math.pow((x-object.x), 2)+Math.pow((y-object.y), 2)); // egyel�re nem sz�molunk t�vols�got, 0-val t�r vissza
	}
	
	// ellens�ges-e a gameObject
	public boolean isEnemy() {
		return false; // alapb�l egy GameObject nem ellens�ges
	}
	
	// trap-e a gameObject
	public boolean isTrap() {
		return false; // alapb�l egy GameObject nem trap
	}
		
	// tower-e a gameObject
	public boolean isTower() {
		return false; // alapb�l egy GameObject nem tower
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
