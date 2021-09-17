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
		lvl.setFeld(lvl.ladeL(lvl.getLvl()));
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
		//hide image from gamefield
		lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].verstecke();
		//remove object from gamefield
		lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] = null;
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
				if (isPlayerInFrontOf("Villain") && player.getSwords() < 1) {
					m.getLblText().setzeInhalt(Variables.DE_TXT_GEGNER_001);
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

	public void commands(char c) {
		switch (c) {
			case 'i':
				m.info();
				break;
			case 'c':
				String strCmd = JOptionPane.showInputDialog(m.cmd());
				switch (strCmd) {
					case "glitch":
						player.cheatmode();
						break;
					case "swords":
						player.setSwords(m.inputInt(m.cmdSwords()));
						m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
						break;
					case "keys":
						player.setKeys(m.inputInt(m.cmdKeys()));
						m.getLblKeys().setzeInhalt(player.getKeys());
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
			case "Button":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Button;
			case "Box":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Box;
			case "Sword":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Sword;
			case "Money":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Money;
			case "Princess":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Princess;
			case "Lock":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Lock;
			case "Opa":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Opa;
			case "NPC":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof NPC;
			case "Key":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Key;
			case "Masterkey":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof MasterKey;
			case "Villain":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Villain;
			case "Obstacle":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Obstacle && !player.isCheat();
			case "Stairs":
				return lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] instanceof Stairs;
			default:
				return false;
		}
	}

	public void playerInFrontOfPerson() {
		if (isPlayerInFrontOf("Princess")) {
			Sound.playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Variables.DE_TXT_PRINCESS_001);
		} else if (isPlayerInFrontOf("Opa")) {
			Sound.playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Variables.DE_TXT_OPA_004);
		}
	}

	private void isPlayerOnStairs() {
		if (isPlayerInFrontOf("Stairs")) {
			//set lvl destination
			int newLvl = ((Stairs) lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y]).getLvl();
			//clear gamefield
			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (lvl.getFeld()[i][j] != null) {
						//hide image of object from gamefield
						lvl.getFeld()[i][j].verstecke();
					}
					//remove object from gamefield
					lvl.getFeld()[i][j] = null;
				}
			}
			//load new gamefield from another level
			lvl.setFeld(lvl.ladeL(newLvl));
		}
	}

	private void isPlayerOnPortal() {
		if (lvl.getFeld()[Player.getPosX()][Player.getPosY()] instanceof Portal) {
			//teleport player to another position
			player.go(((Portal) lvl.getFeld()[Player.getPosX()][Player.getPosY()]).getPosX(),
					((Portal) lvl.getFeld()[Player.getPosX()][Player.getPosY()]).getPosY());
			int xt = ((Portal) lvl.getFeld()[Player.getPosX()][Player.getPosY()]).getPosX();
			int yt = ((Portal) lvl.getFeld()[Player.getPosX()][Player.getPosY()]).getPosY();
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
		if (isPlayerInFrontOf("Box") && lvl.getFeld()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Button) {
			lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
			//lvl.getFeld()[18][14].verstecke();
			//lvl.getFeld()[18][14] = null; //TODO: why null?
			lvl.getFeld()[Player.getPosX()][Player.getPosY() + (y * 2)] = lvl.getFeld()[Player.getPosX()][Player.getPosY() + y];
			lvl.getFeld()[Player.getPosX()][Player.getPosY() + y] = null;
		}

		if (isPlayerInFrontOf("Box") && !(lvl.getFeld()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Objekte)) {
			if (coord == 'x') {
				if (r == 1) {
					lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].moveRight();
				} else if (r == -1) {
					lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].moveLeft();
				}
			} else {
				if (r == -1) {
					lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].moveUp();
				} else if (r == 1) {
					lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
				}
			}
			lvl.getFeld()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] = lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y];
			lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y] = null;
		}
	}
	
	/**
	 * Handle Movement of player
	 * @param c Which key is pressed?
	 */
	@Override
	public void bearbeiteTaste(char c) {
		m.clearText();
		//distance from player
		x = 0;
		y = 0;
		//distance from player +1
		x2 = 0;
		y2 = 0;

		move(c);
		commands(c);

		if (isPlayerInFrontOf("Button")) {
			lvl.getFeld()[18][14].zeige();
		}
		
		if (isPlayerInFrontOf("Obstacle")) {
			Sound.playSound(Variables.SND_OBSTACLE);
		}
		
		playerInFrontOfPerson();

		if (c == 'w') {
			if (isPlayerInFrontOf("Opa") && Opa.getMasterkey() == 0) {
				Sound.playSound(Variables.SND_TEXT);
				m.getLblText().setzeInhalt(Variables.DE_TXT_OPA_003);
			} else if (isPlayerInFrontOf("Opa")) {
				if (player.getMoney() < 450) {
					Sound.playSound(Variables.SND_TEXT);
					m.getLblText().setzeInhalt(Variables.DE_TXT_OPA_001);
				} else {
					lvl.getList().add(new Visited(Player.getPosX(), Player.getPosY() + y, lvl.getLvl()));
					player.setMoney(player.getMoney() - 450);
					Opa.setMasterkey(Opa.getMasterkey() - 1);
					m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
					player.setMasterkeys(player.getMasterkeys() + 1);
					m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
					m.getLblText().setzeInhalt(Variables.DE_TXT_OPA_002);
				}
			}
		}

		if (isPlayerInFrontOf("Lock") && player.getKeys() > 1
				|| isPlayerInFrontOf("Villain") && player.getSwords() > 0
				|| isPlayerInFrontOf("Lock") && player.getKeys() > 2
				|| isPlayerInFrontOf("Lock") && player.getMasterkeys() > 0
				|| isPlayerInFrontOf("Money") || isPlayerInFrontOf("Sword")
				|| isPlayerInFrontOf("Key") || isPlayerInFrontOf("Masterkey")
				|| isPlayerInFrontOf("Lock") && player.getKeys() > 0) {
			if (isPlayerInFrontOf("Villain")) {
				player.setSwords(player.getSwords() - 1);
				m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
			} else if (isPlayerInFrontOf("Money")) {
				Sound.playSound(Variables.SND_MONEY);
				player.ladeBild(Variables.IMG_PLAYER_MONEY);
				player.setMoney(player.getMoney() + ((Money) lvl.getFeld()[Player.getPosX() + x][Player.getPosY() + y]).getValue());
				m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
			} else if (isPlayerInFrontOf("Sword")) {
				//add sword to items
				player.setSwords(player.getSwords() + 1);
				//change image from Player
				player.ladeBild(Variables.IMG_PLAYER_SWORD);
				//refresh menue
				m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
			} else if (isPlayerInFrontOf("Key")) {
				//add key to items
				player.setKeys(player.getKeys() + 1);
				//refresh menue
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (isPlayerInFrontOf("Lock")) {
				//remove key from items
				player.setKeys(player.getKeys() - 1);
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (isPlayerInFrontOf("Masterkey")) {
				Sound.playSound(Variables.SND_TEXT);
				player.setMasterkeys(player.getMasterkeys() + 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
			} else if (isPlayerInFrontOf("Lock") && player.getMasterkeys() > 0) {
				player.setMasterkeys(player.getMasterkeys() - 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
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
		if (player.isCheat() || !(isPlayerInFrontOf("Box")
				&& (lvl.getFeld()[Player.getPosX() + x2][Player.getPosY() + y2] instanceof Objekte))
				&& !(isPlayerInFrontOf("NPC"))
				&& !(isPlayerInFrontOf("Obstacle"))
				&& !(isPlayerInFrontOf("Villain"))) {
			switch (c) {
				case 'w':
					player.moveUp();
					Player.setPosY(Player.getPosY() + y);
					break;
				case 'a':
					player.moveLeft();
					Player.setPosX(Player.getPosX() + x);
					break;
				case 's':
					player.moveDown();
					Player.setPosY(Player.getPosY() + y);
					break;
				case 'd':
					player.moveRight();
					Player.setPosX(Player.getPosX() + x);
					break;
				default:
					break;
			}
			if (player.getCheatc() < 1) {
				player.setSteps(player.getSteps() + 1);
				m.getLblSteps().setzeInhalt(m.getSteps() + player.getSteps());
			} else {
				player.setSteps(0);
				m.getLblSteps().setzeInhalt("");
			}
		}
	}
}