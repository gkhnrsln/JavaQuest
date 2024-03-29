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

package main.java.gameobject.item;

public class Sword extends Item {
	private static final long serialVersionUID = 6078587262493481794L;

	/**
	 * Constructor for Sword object.
	 * @param x    Position (x coordinate).
	 * @param y    Position (y coordinate).
	 */
	public Sword(int x, int y) {
		super(x, y, "img.sword");
	}
}