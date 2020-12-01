package objects;

import enums.Variables;

/**
 * 
 * @author G. Arslan
 *
 */
public class Stairs extends Objekte {
	private int lvl;

	/**
	 * 
	 * @param x Position X
	 * @param y Position Y
	 * @param l Level
	 */
	public Stairs(int x, int y, int l) {
		super(x, y, Variables.IMG_STAIRS);
		setLvl(l);
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
