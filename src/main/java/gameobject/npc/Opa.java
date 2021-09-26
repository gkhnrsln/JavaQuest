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

package main.java.gameobject.npc;

import lombok.Getter;
import lombok.Setter;
import main.java.enums.Variables;
import main.java.gameobject.NPC;

public class Opa extends NPC {
	private static final long serialVersionUID = 5642323314026519582L;
	@Getter @Setter
	private static int masterKey = 1;
	/**
	 * Constructor of opa.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 */
	public Opa(int x, int y) {
		super(x, y, Variables.IMG_OPA);
	}
}