package objects.npc;

import enums.Variables;
import objects.NPC;
/**
 * @author G. Arslan
 */
public class Opa extends NPC {
	private static int masterkey = 1;
	/**
	 * Constructor of opa.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 */
	public Opa(int x, int y) {
		super(x, y, Variables.IMG_OPA);
	}
	
	/** Messages from Opa. */
	private static String [] txt = new String[]{
			Variables.DE_TXT_OPA_001,
			Variables.DE_TXT_OPA_002,
			Variables.DE_TXT_OPA_003,
			Variables.DE_TXT_OPA_004	
	};
	
	//getter setter
	public static void setMasterkey(int masterkey) {
		Opa.masterkey = masterkey;
	}

	public static int getMasterkey() {
		return masterkey;
	}

	public static String [] getTxt() {
		return txt;
	}

	public static void setTxt(String [] txt) {
		Opa.txt = txt;
	}
}