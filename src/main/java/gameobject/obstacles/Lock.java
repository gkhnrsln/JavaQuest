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

package main.java.gameobject.obstacles;

import lombok.Getter;
import lombok.Setter;
import main.java.gameobject.Obstacle;

/**
 * A lock must first be opened by a player to enter the area behind it.
 */
public class Lock extends Obstacle {
	private static final long serialVersionUID = 7550701273363704195L;
	@Getter @Setter
	private int id;

	/**
	 * Constructor for new Lock (Var. 1).
	 *
	 * @param x    Position (x coordinate).
	 * @param y    Position (y coordinate).
	 */
	public Lock(int x, int y) {
		super(x, y, "img.lock1");
	}
	/**
	 * Constructor for new Lock (Var. 2).
	 *
	 * @param x    Position (x coordinate).
	 * @param y    Position (y coordinate).
	 * @param id   ID
	 */
	public Lock(int x, int y, int id) {
		super(x, y, "img.lock2");
		this.id = id;
	}
}