/*
 * Copyright (C) 2020  G. Arslan
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms and conditions of the GNU General Public License,
 * version 2, as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.objects.npc;

import main.java.enums.Variables;
import main.java.objects.NPC;

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