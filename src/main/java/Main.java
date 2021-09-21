package main.java;
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
import javax.swing.JOptionPane;

import main.java.enums.Variables;
import main.java.gui.Menu;
import main.java.objects.Box;
import main.java.objects.NPC;
import main.java.objects.Objekte;
import main.java.objects.Obstacle;
import main.java.objects.Stairs;
import main.java.objects.Villain;
import main.java.objects.Player;
import main.java.objects.switches.*;
import main.java.objects.npc.*;
import main.java.objects.obstacles.*;
import main.java.objects.items.*;

import main.java.oyun.Level;
import main.java.oyun.Sound;
import main.java.oyun.Visited;

import sum.ereignis.EBAnwendung;
import sum.multimedia.Bild;

public class Main extends EBAnwendung {
	private final Menu m; // Menu
	private final Level<Visited> lvl = new Level<>(); // Level
	private final Player player; // Player
	private int x = 0; // x-axis distance from player
	private int y = 0; // y-axis distance from player
	private int x2 = 0; // x-axis distance from player +1
	private int y2 = 0; // y-axis distance from player +1

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		super(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT, true);
		Bild bg = new Bild(0, 0, 0, 0, Variables.IMG_BG); //Background image of map
		player = new Player();
		m = new Menu();
		m.listenTo(player);
		lvl.setField(lvl.loadLvl(lvl.getLvl()));
		fuehreAus();
	}

	/*
	 * Hide an object from the world.
	 * @param t must be 'x' or 'y'
	 * @param r must be '-1' or '1'
	 */
	private void hideObject(char t, int r) {
		switch (t) {
			case 'x':
				x = r;
				break;
			case 'y':
				y = r;
				break;
			default:
				break;
		}
		//add new visited field to list
		lvl.getList().add(new Visited(Player.getPosX() + x, Player.getPosY() + y, lvl.getLvl()));
		//hide image from gameField
		lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].verstecke();
		//remove object from gameField
		lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] = null;
	}

	public void commands(char c) {
		switch (c) {
			case 'i':
				m.info();
				break;
			case 'c':
				switch (m.cmd()) {
					case "glitch":
						player.cheatMode();
						break;
					case "swords":
						player.setSwords(m.cmdSwords());
						break;
					case "keys":
						player.setKeys(Integer.parseInt(JOptionPane.showInputDialog(m.cmdKeys())));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}
	/**
	 * If Player is in front of a specified object.
	 * @param object specified object
	 * @return true, if Player is in front of a specified object
	 */
	private boolean isPlayerInFrontOf(String object) {
		switch (object) {
			case Variables.BUTTON:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Button;
			case Variables.BOX:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Box;
			case Variables.SWORD:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Sword;
			case Variables.MONEY:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Money;
			case Variables.PRINCESS:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Princess;
			case Variables.LOCK:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Lock;
			case Variables.OPA:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Opa;
			case Variables.NPC:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof NPC;
			case Variables.KEY:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Key;
			case Variables.MASTERKEY:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof MasterKey;
			case Variables.VILLAIN:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Villain;
			case Variables.OBSTACLE:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Obstacle && !player.isCheat();
			case Variables.STAIRS:
				return lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Stairs;
			default:
				return false;
		}
	}

	public void playerInFrontOfPerson() {
		if (isPlayerInFrontOf(Variables.PRINCESS)) {
			Sound.playSound(Variables.SND_PRINCESS);
			m.text(Variables.DE_TXT_PRINCESS_001);
		} else if (isPlayerInFrontOf(Variables.OPA)) {
			Sound.playSound(Variables.SND_PRINCESS);
			m.text(Variables.DE_TXT_OPA_004);
		}
	}

	private void isPlayerOnStairs() {
		if (isPlayerInFrontOf(Variables.STAIRS)) {
			Sound.playSound(Variables.SFX_MOVEMENT_STAIRS4LOOP);
			//set lvl destination
			int newLvl = ((Stairs) lvl.getField()[Player.getPosX() + x][Player.getPosY() + y]).getLvl();
			//clear gameField
			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (lvl.getField()[i][j] != null) {
						lvl.getField()[i][j].verstecke();
					}
					lvl.getField()[i][j] = null;
				}
			}
			lvl.setField(lvl.loadLvl(newLvl));
		}
	}

	private void isPlayerOnPortal() {
		if (lvl.getField()[Player.getPosX()][Player.getPosY()] instanceof Portal) {
			//teleport player to another position
			player.go(((Portal) lvl.getField()[Player.getPosX()][Player.getPosY()]).getPosX(),
					((Portal) lvl.getField()[Player.getPosX()][Player.getPosY()]).getPosY());
			int xt = ((Portal) lvl.getField()[Player.getPosX()][Player.getPosY()]).getPosX();
			int yt = ((Portal) lvl.getField()[Player.getPosX()][Player.getPosY()]).getPosY();
			Player.setPosX(xt);
			Player.setPosY(yt);
		}
	}

	/**
	 * Check, if box can be moved further (i.e. if there are no obstacles).
	 *
	 * @param coord, Either the X or Y coordinate.
	 * @param r, ??
	 */
	public void checkBoxObstacle(char coord, int r) {

		switch (coord) {
			case 'y':
				y = r;
				break;
			case 'x':
				x = r;
				break;
			default:
				return;
		}

		//player is in front of box, box is in front of button
		if (isPlayerInFrontOf(Variables.BOX) && lvl.getField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Button) {
			lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
			//remove block to enter next section in lvl 1
			lvl.getField()[18][14].verstecke();
			lvl.getField()[18][14] = null;
			lvl.getField()[Player.getPosX()][Player.getPosY() + (y * 2)] = lvl.getField()[Player.getPosX()][Player.getPosY() + y];
			lvl.getField()[Player.getPosX()][Player.getPosY() + y] = null;
		}

		if (isPlayerInFrontOf(Variables.BOX) && !(lvl.getField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Objekte)) {
			if (coord == 'x') {
				Sound.playSound(Variables.SFX_SOUNDS_INTERACTION26);
				if (r == 1) {
					lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].moveRight();
				} else if (r == -1) {
					lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].moveLeft();
				}
			} else {
				Sound.playSound(Variables.SFX_SOUNDS_INTERACTION26);
				if (r == -1) {
					lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].moveUp();
				} else if (r == 1) {
					lvl.getField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
				}
			}
			lvl.getField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] = lvl.getField()[Player.getPosX() + x][Player.getPosY() + y];
			lvl.getField()[Player.getPosX() + x][Player.getPosY() + y] = null;
		} else if (isPlayerInFrontOf(Variables.BOX) && lvl.getField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Objekte) {
			Sound.playSound(Variables.SND_OBSTACLE);
		}
	}

	/**
	 * Handle Movement of player
	 * @param c Which key is pressed?
	 */
	@Override
	public void bearbeiteTaste(char c) {
		m.text("");
		//distance from player
		x = 0;
		y = 0;
		//distance from player +1
		x2 = 0;
		y2 = 0;

		move(c);
		commands(c);

		if (isPlayerInFrontOf(Variables.BUTTON)) {
			lvl.getField()[18][14].verstecke();
		} else {
			lvl.getField()[18][14].zeige();
		}

		if (isPlayerInFrontOf(Variables.OBSTACLE)) {
			Sound.playSound(Variables.SND_OBSTACLE);
		}

		playerInFrontOfPerson();

		if (c == 'w') {
			if (isPlayerInFrontOf(Variables.OPA) && Opa.getMasterKey() == 0) {
				Sound.playSound(Variables.SND_TEXT);
				m.text(Variables.DE_TXT_OPA_003);
			} else if (isPlayerInFrontOf(Variables.OPA)) {
				if (player.getMoney() < 450) {
					Sound.playSound(Variables.SND_TEXT);
					m.text(Variables.DE_TXT_OPA_001);
				} else {
					lvl.getList().add(new Visited(Player.getPosX(), Player.getPosY() + y, lvl.getLvl()));
					player.setMoney(player.getMoney() - 450);
					Opa.setMasterKey(Opa.getMasterKey() - 1);
					player.setMasterKeys(player.getMasterKeys() + 1);
					m.text(Variables.DE_TXT_OPA_002);
				}
			}
		}

		if (isPlayerInFrontOf(Variables.LOCK) && player.getKeys() > 1
				|| isPlayerInFrontOf(Variables.VILLAIN) && player.getSwords() > 0
				|| isPlayerInFrontOf(Variables.LOCK) && player.getKeys() > 2
				|| isPlayerInFrontOf(Variables.LOCK) && player.getMasterKeys() > 0
				|| isPlayerInFrontOf(Variables.MONEY) || isPlayerInFrontOf(Variables.SWORD)
				|| isPlayerInFrontOf(Variables.KEY) || isPlayerInFrontOf(Variables.MASTERKEY)
				|| isPlayerInFrontOf(Variables.LOCK) && player.getKeys() > 0) {
			if (isPlayerInFrontOf(Variables.VILLAIN)) {
				Sound.playSound(Variables.SFX_WPN_SWORD1);
				player.setSwords(player.getSwords() - 1);
			} else if (isPlayerInFrontOf(Variables.MONEY)) {
				Sound.playSound(Variables.SND_MONEY);
				player.ladeBild(Variables.IMG_PLAYER_MONEY);
				player.setMoney(player.getMoney() + ((Money) lvl.getField()[Player.getPosX() + x][Player.getPosY() + y]).getValue());
			} else if (isPlayerInFrontOf(Variables.SWORD)) {
				player.setSwords(player.getSwords() + 1);
				player.ladeBild(Variables.IMG_PLAYER_SWORD);
			} else if (isPlayerInFrontOf(Variables.KEY)) {
				player.setKeys(player.getKeys() + 1);
			} else if (isPlayerInFrontOf(Variables.LOCK)) {
				player.setKeys(player.getKeys() - 1);
			} else if (isPlayerInFrontOf(Variables.MASTERKEY)) {
				Sound.playSound(Variables.SND_TEXT); //TODO use another sound
				player.setMasterKeys(player.getMasterKeys() + 1);
			} else if (isPlayerInFrontOf(Variables.LOCK) && player.getMasterKeys() > 0) {
				player.setMasterKeys(player.getMasterKeys() - 1);
			}

			switch (c) {
				case 'w':
					hideObject('y', -1);
					break;
				case 'a':
					hideObject('x', -1);
					break;
				case 's':
					hideObject('y', 1);
					break;
				case 'd':
					hideObject('x', 1);
					break;
				default:
					break;
			}
		}

		isPlayerOnStairs();
		isPlayerOnPortal();

		//move Player image
		movePlayerOnField(c);
	}

	public void move(char c) {
		switch (c) {
			case 'w':
				//load img, player shows up
				player.ladeBild(Variables.IMG_PLAYER_UP);
				y--;
				y2 = y - 1;
				checkBoxObstacle('y', -1);
				break;
			case 'a':
				player.ladeBild(Variables.IMG_PLAYER_LEFT);
				x--;
				x2 = x - 1;
				checkBoxObstacle('x', -1);
				break;
			case 's':
				player.ladeBild(Variables.IMG_PLAYER_DOWN);
				y++;
				y2 = y + 1;
				//no weapon, no fight
				if (isPlayerInFrontOf(Variables.VILLAIN) && player.getSwords() < 1) {
					m.text(Variables.DE_TXT_GEGNER_001);
				}
				checkBoxObstacle('y', 1);
				break;
			case 'd':
				player.ladeBild(Variables.IMG_PLAYER_RIGHT);
				x++;
				x2 = x + 1;
				checkBoxObstacle('x', 1);
				break;
			default:
				break;
		}
	}

	private void movePlayerOnField(char c) {
		if (player.isCheat() || !(isPlayerInFrontOf(Variables.BOX)
				&& (lvl.getField()[Player.getPosX() + x2][Player.getPosY() + y2] instanceof Objekte))
				&& !(isPlayerInFrontOf(Variables.OBSTACLE))) {
			switch (c) {
				case 'w':
					player.moveUp();
					Player.setPosY(Player.getPosY() + y);
					player.setSteps(player.getCheatCnt() < 1 ? player.getSteps() + 1: -1);
					break;
				case 'a':
					player.moveLeft();
					Player.setPosX(Player.getPosX() + x);
					player.setSteps(player.getCheatCnt() < 1 ? player.getSteps() + 1: -1);
					break;
				case 's':
					player.moveDown();
					Player.setPosY(Player.getPosY() + y);
					player.setSteps(player.getCheatCnt() < 1 ? player.getSteps() + 1: -1);
					break;
				case 'd':
					player.moveRight();
					Player.setPosX(Player.getPosX() + x);
					player.setSteps(player.getCheatCnt() < 1 ? player.getSteps() + 1: -1);
					break;
				default:
					break;
			}
		}
	}
}