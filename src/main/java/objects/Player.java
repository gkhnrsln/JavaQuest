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

import lombok.Getter;
import lombok.Setter;
import main.java.enums.Variables;

public class Player extends Objekte {
	private static final long serialVersionUID = -4986780372336177606L;
	@Getter @Setter
	private boolean isCheat = false;
	@Getter @Setter
	private int cheatc = 0;
	@Getter @Setter
	private int keys = 0;
	@Getter @Setter
	private int masterkeys = 0;
	@Getter @Setter
	private int money = 0;
	@Getter @Setter
	private int steps = 0;
	@Getter @Setter
	private int swords = 0;

	@Getter @Setter
	private static int posX = Variables.PLAYER_POS_X; // X Position from Player on GameField.

	@Getter @Setter
	private static int posY = Variables.PLAYER_POS_Y; // Y Position from Player on GameField.

	public Player() {
		super(posX, posY, Variables.IMG_PLAYER_DOWN);
	}

	public void cheatmode() {
		if (!isCheat) {
			setCheat(true);
			ladeBild(Variables.IMG_PLAYER_CHEAT);
			setCheatc(+1);
		} else {
			setCheat(false);
			ladeBild(Variables.IMG_PLAYER_DOWN);
		}
	}
}