package objects.obstacles;

import enums.Variables;
import objects.Obstacle;
/**
 * @author G. Arslan
 */
public class Wall extends Obstacle {
	public Wall(int x, int y) {
		super(x, y, Variables.IMG_WALL);
	}
}
