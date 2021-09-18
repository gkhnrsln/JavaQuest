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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Player extends Objekte {
	private static final long serialVersionUID = -4986780372336177606L;
	@Getter @Setter
	private boolean isCheat = false;
	@Getter @Setter
	private static int cheatc = 0;
	@Getter
	private static int keys = 0;
	@Getter
	private static int masterKeys = 0;
	@Getter
	private static int money = 0;
	@Getter
	private static int steps = 0;
	@Getter
	private static int swords = 0;
	@Getter @Setter
	private static int posX = Variables.PLAYER_POS_X; // X Position from Player on GameField.
	@Getter @Setter
	private static int posY = Variables.PLAYER_POS_Y; // Y Position from Player on GameField.

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public Player() {
		super(posX, posY, Variables.IMG_PLAYER_DOWN);
	}

	public void cheatMode() {
		if (!isCheat) {
			setCheat(true);
			ladeBild(Variables.IMG_PLAYER_CHEAT);
			setCheatc(+1);
		} else {
			setCheat(false);
			ladeBild(Variables.IMG_PLAYER_DOWN);
		}
	}

	public void setSwords(int swords) {
		int oldValue = this.swords;
		this.swords = swords;
		changes.firePropertyChange("swords", oldValue, swords);
	}

	public void setKeys(int keys) {
		int oldValue = this.keys;
		this.keys = keys;
		changes.firePropertyChange("keys", oldValue, keys);
	}

	public void setSteps(int steps) {
		int oldValue = this.steps;
		this.steps = steps;
		changes.firePropertyChange("steps", oldValue, steps);
	}

	public void setMoney(int money) {
		int oldValue = this.money;
		this.money = money;
		changes.firePropertyChange("money", oldValue, money);
	}

	public void setMasterKeys(int masterKeys) {
		int oldValue = this.money;
		this.masterKeys = masterKeys;
		changes.firePropertyChange("masterKeys", oldValue, masterKeys);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
}