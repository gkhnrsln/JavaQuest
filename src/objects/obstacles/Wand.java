package objects.obstacles;

import enums.Variables;
import objects.Obstacle;
/**
 * @author G. Arslan
 */
public class Wand extends Obstacle {
	public Wand(int x, int y) {
		super(x, y, Variables.IMG_INV_WALL);
	}
}