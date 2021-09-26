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

package main.java.gameobject.item;

import main.java.gameobject.GameObject;

public class Item extends GameObject {
	private static final long serialVersionUID = -7883735804633227127L;

	public Item(int x, int y, String file) {
		super(x, y, file);
	}
}