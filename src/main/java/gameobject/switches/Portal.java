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

package main.java.gameobject.switches;

import lombok.Getter;
import lombok.Setter;
import main.java.gameobject.Switch;

public class Portal extends Switch {
	private static final long serialVersionUID = 1865564520296574785L;
	@Getter @Setter
	private int posX;
	@Getter @Setter
	private int posY;

	/**
	 * Constructor of princess.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 * @param x2 Position to teleport (x coordinate).
	 * @param y2 Position to teleport (y coordinate).
	 */
	public Portal(int x, int y, int x2, int y2) {
		super(x, y, "img.portal");
		setPosX(x2);
		setPosY(y2);
	}
}