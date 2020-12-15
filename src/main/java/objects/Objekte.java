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

package main.java.objects;

import main.java.gui.Menue;
import sum.multimedia.Bild;

public abstract class Objekte extends Bild {
	Menue m = new Menue();
	
	/**
	 * Constructor for new objects.
	 * @param x Position (x coordinate).
	 * @param y Position (y coordinate).
	 * @param file Filename of the image.
	 */
	public Objekte(int x, int y, String file) {
		super(x * 34, y * 34, 34, 34, file);
	}
	/**
	 * Changes position of object.
	 * @param x new Position (x coordinate).
	 * @param y new Position (y coordinate).
	 */
	public void go(int x, int y) {
		setzePosition(x * 34, y * 34);
	}

	/** Moves the object 34px up. */
	public void moveUp() {
		setzePosition(links(), oben() - 34);
	}
	/** Moves the object 34px left. */
	public void moveLeft() {
		setzePosition(links() - 34, oben());
	}
	/** Moves the object 34px right. */
	public void moveRight() {
		setzePosition(links() + 34, oben());
	}
	/** Moves the object 34px down. */
	public void moveDown() {
		setzePosition(links(), oben() + 34);
	}
}