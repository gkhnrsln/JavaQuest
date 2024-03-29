/*
 * Copyright (C) 2020-2021  G. Arslan
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

package main.java.gameobject;

import lombok.Getter;
import lombok.Setter;

public class Stairs extends GameObject {
	private static final long serialVersionUID = 6695837635306162543L;
	@Getter @Setter
	private int lvl;

	/**
	 * 
	 * @param x Position X, from Level lvl
	 * @param y Position Y, from Level lvl
	 * @param lvl Sets Destination Level
	 */
	public Stairs(int x, int y, int lvl) {
		super(x, y, "img.stairs");
		setLvl(lvl);
	}
}
