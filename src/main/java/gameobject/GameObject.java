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

package main.java.gameobject;

import main.java.enums.PropertiesLoader;
import sum.multimedia.Bild;

import java.util.Properties;

@java.lang.SuppressWarnings("java:S2184")
public abstract class GameObject extends Bild {
	private static Properties properties = new PropertiesLoader().getProperties();
	/**
	 * Constructor for new game object.
	 * @param x    Position (x coordinate).
	 * @param y    Position (y coordinate).
	 * @param prop Property of the image.
	 */
	protected GameObject(int x, int y, String prop) {
		super(x * 34, y * 34, 34, 34, properties.getProperty(prop));
	}
	/**
	 * Changes position of game object.
	 * @param x new Position (x coordinate).
	 * @param y new Position (y coordinate).
	 */
	public void go(int x, int y) {
		setzePosition(x * 34, y * 34);
	}

	/** Moves the game object 34px up. */
	public void moveUp() {
		setzePosition(links(), oben() - 34);
	}
	/** Moves the game object 34px left. */
	public void moveLeft() {
		setzePosition(links() - 34, oben());
	}
	/** Moves the game object 34px right. */
	public void moveRight() {
		setzePosition(links() + 34, oben());
	}
	/** Moves the game object 34px down. */
	public void moveDown() {
		setzePosition(links(), oben() + 34);
	}
}