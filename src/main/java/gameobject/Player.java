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

import lombok.Getter;
import lombok.Setter;
import main.java.enums.Variables;
import org.apache.commons.lang3.Validate;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Player extends GameObject {
	private static Player instance;
	private static final long serialVersionUID = -4986780372336177606L;
	@Getter
	private int keys;
	@Getter
	private int masterKeys;
	@Getter
	private int money;
	@Getter
	private int steps;
	@Getter
	private int swords;
	@Getter @Setter
	private static int posX = Variables.PLAYER_POS_X; // X Position from Player on GameField.
	@Getter @Setter
	private static int posY = Variables.PLAYER_POS_Y; // Y Position from Player on GameField.

	private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

	private Player() {
		super(posX, posY, "img.player.down");
		this.keys = 0;
		this.masterKeys = 0;
		this.money = 0;
		this.steps = 0;
		this.swords = 0;
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	public void setSwords(int swords) {
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, swords);
		int oldValue = this.swords;
		this.swords = swords;
		changes.firePropertyChange("swords", oldValue, swords);
	}

	public void setKeys(int keys) {
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, keys);
		int oldValue = this.keys;
		this.keys = keys;
		changes.firePropertyChange("keys", oldValue, keys);
	}

	public void setSteps(int steps) {
		Validate.inclusiveBetween(-1, Integer.MAX_VALUE, steps);
		int oldValue = this.steps;
		this.steps = steps;
		changes.firePropertyChange("steps", oldValue, steps);
	}

	public void setMoney(int money) {
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, money);
		int oldValue = this.money;
		this.money = money;
		changes.firePropertyChange("money", oldValue, money);
	}

	public void setMasterKeys(int masterKeys) {
		Validate.inclusiveBetween(0, Integer.MAX_VALUE, masterKeys);
		int oldValue = this.money;
		this.masterKeys = masterKeys;
		changes.firePropertyChange("masterKeys", oldValue, masterKeys);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
}