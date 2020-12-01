package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
/* MAN KANN DRAUF */
public class Portal extends Objekte {
	private int posx;
	private int posy;

	public Portal(int x, int y, int x2, int y2) {
		super(x, y, Variables.IMG_PORTAL);
		setPosx(x2);
		setPosy(y2);
	}

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