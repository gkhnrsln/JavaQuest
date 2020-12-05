import javax.swing.JOptionPane;

import enums.Variables;
import gui.Menue;
import objects.Box;
import objects.villain.*;
import objects.Objekte;
import objects.Obstacle;
import objects.Stairs;
import objects.Villain;
import objects.Player;

import objects.switches.*;
import objects.npc.*;
import objects.obstacles.*;
import objects.items.*;

import oyun.Level;
import oyun.Visited;

import sum.ereignis.EBAnwendung;
import sum.multimedia.Bild;
import sum.multimedia.Ton;

/**
 * @author G. Arslan
 */
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

	/**
	 * Here it is checked if the box can be moved further (i.e. if there are no
	 * obstacles).
	 * 
	 * @param c
	 * @param r
	 */
	public void checkKisteHindernis(char c, int r) {
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

		if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Box
				&& lvl.getFeld()[player.getPosx() + (x * 2)][player.getPosy() + (y * 2)] instanceof Button) {
			lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveDown();
			lvl.getFeld()[18][14].verstecke();
			lvl.getFeld()[18][14] = null;
			lvl.getFeld()[player.getPosx()][player.getPosy() + (y * 2)] = lvl.getFeld()[player.getPosx()][player.getPosy() + y];
			lvl.getFeld()[player.getPosx()][player.getPosy() + y] = null;
		}

		if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Box
				&& !(lvl.getFeld()[player.getPosx() + (x * 2)][player.getPosy() + (y * 2)] instanceof Objekte)) {
			if (c == 'x') {
				if (r == 1)
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveRight();
				else if (r == -1)
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveLeft();

			} else if (c == 'y') {
				if (r == -1)
					lvl.getFeld()[player.getPosx() + x][player.getPosy() + y].moveUp();
				else if (r == 1) {
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
	
	//
	public void move(char c) {
		switch (c) {
			case 'w':
				//load img, player shows up
				player.ladeBild(Variables.IMG_PLAYER_UP);
				y--;
				y2 = y-1;
				checkKisteHindernis('y', -1);
				break;
			case 'a':
				player.ladeBild(Variables.IMG_PLAYER_LEFT);
				x--;
				x2 = x-1;
				checkKisteHindernis('x', -1);
				break;
			case 's':
				player.ladeBild(Variables.IMG_PLAYER_DOWN);
				y++;
				y2 = y+1;
				//no sword, no fight
				if (lvl.getFeld()[player.getPosx()][player.getPosy() + y] instanceof Gegner && player.getSwords() < 1)
					m.getLblText().setzeInhalt(Gegner.getTxt()[0]);
				checkKisteHindernis('y', 1);
				break;
			case 'd':
				player.ladeBild(Variables.IMG_PLAYER_RIGHT);
				x++;
				x2 = x+1;
				checkKisteHindernis('x', 1);
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
	 * if Player is in front of Box
	 * @return true, if Player is in front of Box
	 */
	public boolean isPlayerInFrontOfBox() {
		return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Box;
	}
	/**
	 * if Player is in front of Button, hidden Area is then accesible
	 * @return true, if Player is in front of Button
	 */
	public boolean isPlayerInFrontOfButton() {
		return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Button;
	}
	/**
	 * if Player is in front of Villain
	 * @return true, if Player is in front of Villain
	 */
	public boolean isPlayerInFrontOfVillain() {
		return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Villain;
	}
	/**
	 * if Player is in front of Obstacle, Cheat not activated.
	 * @return true, if Player is in front of Obstacle
	 */
	public boolean isPlayerInFrontOfObstacle() {
		return lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Obstacle && !player.getIsCheat();
	}
	/**
	 * if Player is in front of a person, like Princess/Opa
	 * @return true, if Player is in front of a person
	 */
	public void playerInFrontOfPerson() {
		
		if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Princess) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Princess.getTxt()[0]);

		} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Opa) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Opa.getTxt()[3]);
		
		}
		
	}
	
	/** If player is on stairs. */
	private void isPlayerOnStairs() {
		if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Stairs) {
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
	 * Handle Movement of player
	 * TODO: too complex
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

		if (isPlayerInFrontOfButton()) {
			lvl.getFeld()[18][14].zeige();
		}
		
		if (isPlayerInFrontOfObstacle()) {
			playSound(Variables.SND_OBSTACLE);
		}
		
		playerInFrontOfPerson();

		if (c == 'w') {
			if (lvl.getFeld()[player.getPosx()][player.getPosy() + y] instanceof Opa
					&& ((Opa) lvl.getFeld()[player.getPosx()][player.getPosy() + y]).getMasterkey() == 0) {
				playSound(Variables.SND_TEXT);
				m.getLblText().setzeInhalt(Opa.getTxt()[2]);
			} else if (lvl.getFeld()[player.getPosx()][player.getPosy() + y] instanceof Opa) {
				if (player.getMoney() < 450) {
					playSound(Variables.SND_TEXT);
					m.getLblText().setzeInhalt(Opa.getTxt()[0]);
				} else {
					lvl.getListe().haengeAn(new Visited(player.getPosx(), player.getPosy() + y, lvl.getLvl()));
					player.setMoney(player.getMoney() - 450);

					((Opa) lvl.getFeld()[player.getPosx()][player.getPosy() + y]).setMasterkey(Opa.getMasterkey() - 1);
					m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
					player.setMasterkeys(player.getMasterkeys() + 1);
					m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
					m.getLblText().setzeInhalt(Opa.getTxt()[1]);
				}
			}
		}

		// not bulletproofed
		if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock && player.getKeys() > 1
				|| isPlayerInFrontOfVillain() && player.getSwords() > 0
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock && player.getKeys() > 2
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock && player.getMasterkeys() > 0
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Money
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Sword
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Key
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Masterkey
				|| lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock && player.getKeys() > 0) {
			
			if (isPlayerInFrontOfVillain()) {
				player.setSwords(player.getSwords() - 1);
				m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Money) {
				playSound(Variables.SND_MONEY);
				player.ladeBild(Variables.IMG_PLAYER_MONEY);
				player.setMoney(player.getMoney() + ((Money) lvl.getFeld()[player.getPosx() + x][player.getPosy() + y]).value);
				m.getLblMoney().setzeInhalt(m.getMoney() + player.getMoney());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Sword) {
				//add sword to items
				player.setSwords(player.getSwords() + 1);
				//change image from Player
				player.ladeBild(Variables.IMG_PLAYER_SWORD);
				//refresh Menue
				m.getLblSwords().setzeInhalt(m.getSwords() + player.getSwords());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Key) {
				//add key to items
				player.setKeys(player.getKeys() + 1);
				//refresh Menue
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock) {
				//remove key from items
				player.setKeys(player.getKeys() - 1);
				m.getLblKeys().setzeInhalt(m.getKeys() + player.getKeys());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Masterkey) {
				playSound(Variables.SND_TEXT);
				player.setMasterkeys(player.getMasterkeys() + 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + player.getMasterkeys());
			} else if (lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Lock && player.getMasterkeys() > 0) {
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
		if (player.getIsCheat() || !(isPlayerInFrontOfBox()
				&& (lvl.getFeld()[player.getPosx() + x2][player.getPosy() + y2] instanceof Obstacle))
				&& !(lvl.getFeld()[player.getPosx() + x][player.getPosy() + y] instanceof Obstacle)
				&& !(isPlayerInFrontOfVillain())) {
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