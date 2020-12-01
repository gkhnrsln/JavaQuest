package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
public class Gegner extends Objekte {
	private static String [] txt = new String[]{
			Variables.DE_TXT_GEGNER_001
			};
	
	public Gegner(int x, int y, String bild) {
		super(x, y, bild);
	}
	public static String [] getTxt() {
		return txt;
	}
	public static void setTxt(String [] txt) {
		Gegner.txt = txt;
	}
}