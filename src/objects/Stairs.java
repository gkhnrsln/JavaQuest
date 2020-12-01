package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
/* MAN KANN DRAUF */
public class Stairs extends Objekte {
	private int level;

	public Stairs(int x, int y, int l) {
		super(x, y, Variables.IMG_STAIRS);
		setLevel(l);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
