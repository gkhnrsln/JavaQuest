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

import lombok.Getter;
import lombok.Setter;

public class Money extends Item {
	private static final long serialVersionUID = 43776595159763730L;
	@Getter
	@Setter
	private int value;

	/**
	 * Constructor for Money object.
	 * @param x    Position (x coordinate).
	 * @param y    Position (y coordinate).
	 * @param value Value of Money.
	 */
	public Money(int x, int y, int value) {
		super(x, y, "img.money");
		setValue(value);
	}
}
