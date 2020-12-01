package objects;

import enums.Variables;
/**
 * @author G. Arslan
 */
public class Opa extends Objekte {
	public Opa(int x, int y) {
		super(x, y, Variables.IMG_OPA);
	}
	private static int masterkey = 1;
	
	private static String [] txt = new String[]{
			Variables.DE_TXT_OPA_001,
			Variables.DE_TXT_OPA_002,
			Variables.DE_TXT_OPA_003,
			Variables.DE_TXT_OPA_004	
	};
	
	public void setMasterkey(int masterkey) {
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