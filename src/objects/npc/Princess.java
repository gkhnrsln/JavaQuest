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

package objects.npc;

import enums.Variables;
import objects.NPC;

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