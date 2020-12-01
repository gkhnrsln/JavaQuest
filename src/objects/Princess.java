package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
public class Princess extends Person {
	public Princess(int x, int y) {
		super(x, y, Variables.IMG_PRINCESS);
	}
	public static String [] getTxt() {
		return txt;
	}
	public static void setTxt(String [] txt) {
		Princess.txt = txt;
	}
	private static String [] txt = new String[]{Variables.DE_TXT_PRINCESS_001};
}