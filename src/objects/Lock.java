package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
public class Lock extends Objekte {
	int wert;

	public Lock(int x, int y) {
		super(x, y, Variables.IMG_LOCK_1);
	}

	public Lock(int x, int y, int z) {
		super(x, y, Variables.IMG_LOCK_2);
		wert = z;
	}
}