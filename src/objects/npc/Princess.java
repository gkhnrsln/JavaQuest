package objects.npc;

import enums.Variables;
import objects.NPC;

/**
 * @author G. Arslan
 */
public class Princess extends NPC {
	/**
	 * Constructor of princess.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 */
	public Princess(int x, int y) {
		super(x, y, Variables.IMG_PRINCESS);
	}
	/** Messages from princess. */
	private static String [] txt = new String[]{Variables.DE_TXT_PRINCESS_001};
	
	//getter setter
	public static String [] getTxt() {
		return txt;
	}
	public static void setTxt(String [] txt) {
		Princess.txt = txt;
	}
}