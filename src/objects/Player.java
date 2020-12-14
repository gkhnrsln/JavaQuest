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

package objects;

import enums.Variables;

public class Player extends Objekte {
	private boolean isCheat = false;
	private int cheatc = 0;
	/** Amount Keys from Player. */
	private int keys = 0;
	/** Amount Masterkeys from Player. */
	private int masterkeys = 0;
	/** Amount Money from Player. */
	private int money = 0;
	/** Amount Steps from Player. */
	private int steps = 0;
	/** Amount Swords from Player. */
	private int swords = 0;
	/** X Position from Player on GameField. */
	private static int posx = Variables.PLAYER_POS_X;
	/** Y Position from Player on GameField. */
	private static int posy = Variables.PLAYER_POS_Y;

	public Player() {
		super(posx, posy, Variables.IMG_PLAYER_DOWN);
	}

	public void setIsCheat(boolean isCheat) {
		this.isCheat = isCheat;
	}

	public void setCheatc(int cheatc) {
		this.cheatc = cheatc;
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public void setMasterkeys(int masterkeys) {
		this.masterkeys = masterkeys;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public void setSwords(int swords) {
		this.swords = swords;
	}

	public boolean getIsCheat() {
		return isCheat;
	}

	public int getCheatc() {
		return cheatc;
	}

	public int getKeys() {
		return keys;
	}

	public int getMasterkeys() {
		return masterkeys;
	}

	public int getMoney() {
		return money;
	}

	public int getSteps() {
		return steps;
	}

	public int getSwords() {
		return swords;
	}

	public void cheatmode() {
		if (!isCheat) {
			setIsCheat(true);
			ladeBild(Variables.IMG_PLAYER_CHEAT);
			setCheatc(+1);
		} else {
			setIsCheat(false);
			ladeBild(Variables.IMG_PLAYER_DOWN);
		}
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		Player.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		Player.posy = posy;
	}
	
}