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

package main.java.objects.obstacles;

import main.java.enums.Variables;
import main.java.objects.Obstacle;

public class Lock extends Obstacle {
	private static final long serialVersionUID = 7550701273363704195L;

	public Lock(int x, int y) {
		super(x, y, Variables.IMG_LOCK_1);
	}

	public Lock(int x, int y, int z) {
		super(x, y, Variables.IMG_LOCK_2);
	}
}