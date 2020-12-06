package objects.switches;

import enums.Variables;
import objects.Switch;
/**
 * @author G. Arslan
 */

public class Button extends Switch {
	public Button(int x, int y) {
		super(x, y, Variables.IMG_BUTTON);
	}
}