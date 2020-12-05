package objects.switches;

import enums.Variables;
import objects.Switch;

/**
 * @author G. Arslan
 */
public class Portal extends Switch {
	private int posx;
	private int posy;

	/**
	 * Constructor of princess.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 * @param x2 Position to teleport (x coordinate).
	 * @param y2 Position to teleport (y coordinate).
	 */
	public Portal(int x, int y, int x2, int y2) {
		super(x, y, Variables.IMG_PORTAL);
		setPosx(x2);
		setPosy(y2);
	}

	//getter setter
	
	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
}