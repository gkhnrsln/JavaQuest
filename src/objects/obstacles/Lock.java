package objects.obstacles;

import enums.Variables;
import objects.Obstacle;
/**
 * @author G. Arslan
 */
public class Lock extends Obstacle {
	int wert;

	public Lock(int x, int y) {
		super(x, y, Variables.IMG_LOCK_1);
	}

	public Lock(int x, int y, int z) {
		super(x, y, Variables.IMG_LOCK_2);
		wert = z;
	}
}