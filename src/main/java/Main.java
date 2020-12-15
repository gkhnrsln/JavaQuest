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
import main.java.gui.Menue;
import main.java.objects.Box;
import main.java.objects.NPC;
import main.java.objects.villain.*;
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
import main.java.oyun.Visited;

import sum.ereignis.EBAnwendung;
import sum.multimedia.Bild;
import sum.multimedia.Ton;

public class Main extends EBAnwendung {
	
	private Menue m = new Menue();
	private Level<Visited> lvl = new Level<>();
	/** Background Image of Map */
	private Bild bg = new Bild(0, 0, 0, 0, Variables.IMG_BG);
	private Player player = new Player();

	//distance from player
	private static int x = 0;
	private static int y = 0;
	//distance from player +1
	private static int x2 = 0;
	private static int y2 = 0;
	
	/** main */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		super(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT, true);
		lvl.setFeld(lvl.ladeL(lvl.getLvl()));
		fuehreAus();
	}

	/**
	 * Plays a .wav-File.
	 * @param file Filename
	 */
	public void playSound(String file) {
		Ton sound = new Ton();
		//load Sound
		sound.ladeTon(file);
		//play Sound
		sound.spieleTon();
	}

	/**
	 * Hide an object from the world.
	 * @param t must be 'x' or 'y'
	 * @param r must be '-1' or '1'
	 */
	public void hideObject(char t, int r) {
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

		lvl.getListe().haengeAn(new Visited(player.getPosx() + x, player.getPosy() + y, lvl.getLvl()));
		//hide image from playground
		lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].verstecke();
		//remove object from field
		lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] = null;
	}


	
	//
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
					m.getLblText().setzeInhalt(Gegner.getTxt()[0]);
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
	
	/**
	 * 
	 * @param c
	 */
	public void commands(char c) {
		switch (c) {
			case 'i':
				m.info();
				break;
			case 'c':
				String strCmd = JOptionPane.showInputDialog(m.cmd());
				if (strCmd.equals("glitch")) {
					player.cheatmode();
				} else if (strCmd.equals("swords")) {
					player.setSwords(m.eingabeInt(m.cmdSwords()));
					m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
				} else if (strCmd.equals("keys")) {
					player.setKeys(m.eingabeInt(m.cmdKeys()));
					m.getLblKeys().setzeInhalt(player.getKeys());
				} else if (strCmd.equals("highscore"))
					m.hiscore();
				break;
			default:
				break;
		}
	}
	/**
	 * If Player is in front of an specified object.
	 * @param object specified object
	 * @return true, if Player is in front of a specified object
	 */
	public boolean isPlayerInFrontOf(String object) {
		if (object.equals("Button")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Button;
		} else if (object.equals("Box")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Box;
		} else if (object.equals("Sword")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Sword;
		} else if (object.equals("Money")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Money;
		} else if (object.equals("Princess")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Princess;
		} else if (object.equals("Lock")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock;
		} else if (object.equals("Opa")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Opa;
		} else if (object.equals("NPC")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof NPC;
		} else if (object.equals("Key")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Key;
		} else if (object.equals("Masterkey")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Masterkey;
		} else if (object.equals("Villain")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Villain;
		}  else if (object.equals("Obstacle")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Obstacle && !player.getIsCheat();
		} else if (object.equals("Stairs")) {
			return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Stairs;
		} else {
			return false;
		}
		
	}
	/**
	 * If Player is in front of a person, like Princess/Opa
	 * @return true, if Player is in front of a person
	 */
	public void playerInFrontOfPerson() {
		if (isPlayerInFrontOf("Princess")) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Princess.getTxt()[0]);
		} else if (isPlayerInFrontOf("Opa")) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Opa.getTxt()[3]);
		}
	}
	
	/** If player is on stairs. */
	private void isPlayerOnStairs() {
		if (isPlayerInFrontOf("Stairs")) {
			int newLvl = ((Stairs) lvl.getFeld()[player.getPosx() + x][player.getPosy() + y]).getLvl();
			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (lvl.getFeld()[i][j] != null)
						lvl.getFeld()[i][j].verstecke();
					lvl.getFeld()[i][j] = null;
				}
			}
			lvl.setFeld(lvl.ladeL(newLvl));
		}
	}
	
	/** If player is on a portal. */
	private void isPlayerOnPortal() {
		if (lvl.getFeld()[player.getPosx()][player.getPosy()] instanceof Portal) {
			//teleport player to another position
			player.go(((Portal) lvl.getFeld()[player.getPosx()][player.getPosy()]).getPosx(),
					((Portal) lvl.getFeld()[player.getPosx()][player.getPosy()]).getPosy());
			int xt = ((Portal) lvl.getFeld()[player.getPosx()][player.getPosy()]).getPosx();
			int yt = ((Portal) lvl.getFeld()[player.getPosx()][player.getPosy()]).getPosy();
			player.setPosx(xt);
			player.setPosy(yt);
		}
	}
	
	/**
	 * Here it is checked if the box can be moved further (i.e. if there are no
	 * obstacles).
	 * 
	 * @param c
	 * @param r
	 */
	public void checkBoxObstacle(char c, int r) {
		switch (c) {
			case 'y':
				y = r;
				break;
			case 'x':
				x = r;
				break;
			default:
				break;
		}

		if (isPlayerInFrontOf("Box") && lvl.getFeld()[player.getPosx() + (x * 2)][player.getPosy() + (y * 2)] instanceof Button) {
			lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveDown();
			lvl.getFeld()[18][14].verstecke();
			lvl.getFeld()[18][14] = null;
			lvl.getFeld()[player.getPosx()][player.getPosy() + (y * 2)] = lvl.getFeld()[player.getPosx()][player.getPosy() + y];
			lvl.getFeld()[player.getPosx()][player.getPosy() + y] = null;
		}

		if (isPlayerInFrontOf("Box") && !(lvl.getFeld()[player.getPosx() + (x * 2)][player.getPosy() + (y * 2)] instanceof Objekte)) {
			if (c == 'x') {
				if (r == 1) {
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveRight();
				} else if (r == -1) {
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveLeft();
				}
			} else if (c == 'y') {
				if (r == -1) {
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveUp();
				} else if (r == 1) {
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveDown();
					/*
					 * if(lvl.getFeld()[player.get_posx()+x][player.get_posy()+2] instanceof Schalter){
					 * lvl.getFeld()[18][14].verstecke(); lvl.getFeld()[18][14] = null; }
					 */
				}
			}
			lvl.getFeld()[player.getPosx() + (x * 2)][player.getPosy() + (y * 2)] = lvl.getFeld()[player.getPosx() + x][player.getPosy() + y];
			lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] = null;
		}
	}
	
	/**
	 * Handle Movement of player
	 * @param c Which key is pressed?
	 */
	@Override
	public void bearbeiteTaste(char c) {
		m.ctxt();
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
			playSound(Variables.SND_OBSTACLE);
		}
		
		playerInFrontOfPerson();

		if (c == 'w') {
			if (isPlayerInFrontOf("Opa") && Opa.getMasterkey() == 0) {
				playSound(Variables.SND_TEXT);
				m.getLblText().setzeInhalt(Opa.getTxt()[2]);
			} else if (isPlayerInFrontOf("Opa")) {
				if (player.getMoney() < 450) {
					playSound(Variables.SND_TEXT);
					m.getLblText().setzeInhalt(Opa.getTxt()[0]);
				} else {
					lvl.getListe().haengeAn(new Visited(player.getPosx(), player.getPosy() + y, lvl.getLvl()));
					player.setMoney(player.getMoney() - 450);
					Opa.setMasterkey(Opa.getMasterkey() - 1);
					m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
					player.setMasterkeys(player.getMasterkeys() + 1);
					m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
					m.getLblText().setzeInhalt(Opa.getTxt()[1]);
				}
			}
		}

		// not bulletproofed
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
				playSound(Variables.SND_MONEY);
				player.ladeBild(Variables.IMG_PLAYER_MONEY);
				player.setMoney(player.getMoney() + ((Money) lvl.getFeld()[player.getPosx() + x][player.getPosy() + y]).value);
				m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
			} else if (isPlayerInFrontOf("Sword")) {
				//add sword to items
				player.setSwords(player.getSwords() + 1);
				//change image from Player
				player.ladeBild(Variables.IMG_PLAYER_SWORD);
				//refresh Menue
				m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
			} else if (isPlayerInFrontOf("Key")) {
				//add key to items
				player.setKeys(player.getKeys() + 1);
				//refresh Menue
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (isPlayerInFrontOf("Lock")) {
				//remove key from items
				player.setKeys(player.getKeys() - 1);
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (isPlayerInFrontOf("Masterkey")) {
				playSound(Variables.SND_TEXT);
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

		//move Player Sprite
		if (player.getIsCheat() || !(isPlayerInFrontOf("Box")
				&& (lvl.getFeld()[player.getPosx() + x2][player.getPosy() + y2] instanceof Obstacle))
				&& !(isPlayerInFrontOf("NPC"))
				&& !(isPlayerInFrontOf("Obstacle"))
				&& !(isPlayerInFrontOf("Villain"))) {
			switch (c) {
				case 'w':
					player.moveUp();
					player.setPosy(player.getPosy() + y);
					break;
				case 'a':
					player.moveLeft();
					player.setPosx(player.getPosx() + x);
					break;
				case 's':
					player.moveDown();
					player.setPosy(player.getPosy() + y);
					break;
				case 'd':
					player.moveRight();
					player.setPosx(player.getPosx() + x);
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