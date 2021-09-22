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

package main.java.engine;

public class Visited {
	/** Level */
	protected int lvl;
	/** Position X */
	protected int x;
	/** Position Y */
	protected int y;

	/**
	 *
	 * @param x, x coordinate
	 * @param y, y coordinate
	 * @param lvl, Level
	 */
	public Visited(int x, int y, int lvl) {
		this.x = x;
		this.y = y;
		this.lvl = lvl;
	}
}