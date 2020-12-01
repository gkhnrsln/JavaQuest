package objects.items;

import enums.Variables;

/**
 * @author G. Arslan
 */
public class Money extends Items {
	public int value;

	public Money(int x, int y, int z) {
		super(x, y, Variables.IMG_MONEY);
		value = z;
	}
}
