import javax.swing.JOptionPane;

import enums.Variables;
import gui.Menue;
import objects.Box;
import objects.Button;
import objects.Gegner;
import objects.Lock;
import objects.Objekte;
import objects.Opa;
import objects.Person;
import objects.Portal;
import objects.Princess;
import objects.Stairs;
import objects.items.Key;
import objects.items.Masterkey;
import objects.items.Money;
import objects.items.Sword;
import oyun.Level;
import oyun.Player;
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
	private Player s = new Player();

	//distance from player
	private static int x = 0;
	private static int y = 0;
	//distance from player +1
	private static int x2 = 0;
	private static int y2 = 0;
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		super(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT, true);
		lvl.setFeld(lvl.ladeL(lvl.getLvl()));
		fuehreAus();
	}

	/**
	 * Plays a .wav-File from the wav-Folder
	 * @param file Filename
	 */
	public void playSound(String file) {
		Ton sound = new Ton();
		sound.ladeTon("wav/" + file);
		sound.spieleTon();
	}

	/**
	 * Hide an Item if Player picks it Up!
	 * @param t must be 'x' or 'y'
	 * @param r must be '-1' or '1'
	 */
	public void hideItem(char t, int r) {
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

		lvl.getListe().haengeAn(new Visited(s.getPosx() + x, s.getPosy() + y, lvl.getLvl()));
		lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].verstecke();
		lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] = null;
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

		if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& lvl.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] instanceof Button) {
			lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].moveDown();
			lvl.getFeld()[18][14].verstecke();
			lvl.getFeld()[18][14] = null;
			lvl.getFeld()[s.getPosx()][s.getPosy() + (y * 2)] = lvl.getFeld()[s.getPosx()][s.getPosy() + y];
			lvl.getFeld()[s.getPosx()][s.getPosy() + y] = null;
		}

		if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& !(lvl.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] instanceof Objekte)) {
			if (c == 'x') {
				if (r == 1)
					lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].moveRight();
				else if (r == -1)
					lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].moveLeft();

			} else if (c == 'y') {
				if (r == -1)
					lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].moveUp();
				else if (r == 1) {
					lvl.getFeld()[s.getPosx() + x][s.getPosy() + y].moveDown();
					/*
					 * if(lvl.getFeld()[s.get_posx()+x][s.get_posy()+2] instanceof Schalter){
					 * lvl.getFeld()[18][14].verstecke(); lvl.getFeld()[18][14] = null; }
					 */

				}
			}
			lvl.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] = lvl.getFeld()[s.getPosx() + x][s.getPosy() + y];
			lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] = null;
		}
	}
	
	//
	public void move(char c) {
		switch (c) {
			case 'w':
				//load img, player shows up
				s.ladeBild(Variables.IMG_PLAYER_UP);
				y--;
				y2 = y-1;
				checkKisteHindernis('y', -1);
				break;
			case 'a':
				s.ladeBild(Variables.IMG_PLAYER_LEFT);
				x--;
				x2 = x-1;
				checkKisteHindernis('x', -1);
				break;
			case 's':
				s.ladeBild(Variables.IMG_PLAYER_DOWN);
				y++;
				y2 = y+1;
				//no sword, no fight
				if (lvl.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Gegner && s.getSwords() < 1)
					m.getLblText().setzeInhalt(Gegner.getTxt()[0]);
				checkKisteHindernis('y', 1);
				break;
			case 'd':
				s.ladeBild(Variables.IMG_PLAYER_RIGHT);
				x++;
				x2 = x+1;
				checkKisteHindernis('x', 1);
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
				if (strCmd.equals("glitch")) {
					s.cheatmode();
				} else if (strCmd.equals("swords")) {
					s.setSwords(m.eingabeInt(m.cmdSwords()));
					m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
				} else if (strCmd.equals("keys")) {
					s.setKeys(m.eingabeInt(m.cmdKeys()));
					m.getLblKeys().setzeInhalt(s.getKeys());
				} else if (strCmd.equals("highscore"))
					m.hiscore();
				break;
			default:
				break;
	}
	}
	/**
	 * if Player is in front of Button, hidden Area is then accesible
	 * @return true, if Player is in front of Button
	 */
	public boolean isPlayerInFrontOfButton() {
		return lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Button;
	}
	
	/**
	 * if Player is in front of Obstacle, Cheat not activated
	 * @return true, if Player is in front of Obstacle
	 */
	public boolean isPlayerInFrontOfObstacle() {
		return lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Objekte && !s.getIsCheat();
	}
	/**
	 * if Player is in front of a person, like Princess/Opa
	 * @return
	 */
	public void playerInFrontOfPerson() {
		
		if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Princess) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Princess.getTxt()[0]);

		} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Opa) {
			playSound(Variables.SND_PRINCESS);
			m.getLblText().setzeInhalt(Opa.getTxt()[3]);
		
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
			if (lvl.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Opa
					&& ((Opa) lvl.getFeld()[s.getPosx()][s.getPosy() + y]).getMasterkey() == 0) {
				playSound(Variables.SND_TEXT);
				m.getLblText().setzeInhalt(Opa.getTxt()[2]);
			} else if (lvl.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Opa) {
				if (s.getMoney() < 450) {
					playSound(Variables.SND_TEXT);
					m.getLblText().setzeInhalt(Opa.getTxt()[0]);
				} else {
					lvl.getListe().haengeAn(new Visited(s.getPosx(), s.getPosy() + y, lvl.getLvl()));
					s.setMoney(s.getMoney() - 450);

					((Opa) lvl.getFeld()[s.getPosx()][s.getPosy() + y]).setMasterkey(Opa.getMasterkey() - 1);
					m.getLblMoney().setzeInhalt(m.getMoney() + s.getMoney());
					s.setMasterkeys(s.getMasterkeys() + 1);
					m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
					m.getLblText().setzeInhalt(Opa.getTxt()[1]);
				}
			}
		}

		// not bulletproofed
		if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 1
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Gegner && s.getSwords() > 0
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 2
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getMasterkeys() > 0
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Money
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Sword
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Key
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Masterkey
				|| lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 0) {
			
			if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Gegner) {
				s.setSwords(s.getSwords() - 1);
				m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Money) {
				playSound(Variables.SND_MONEY);
				s.ladeBild(Variables.IMG_PLAYER_MONEY);
				s.setMoney(s.getMoney() + ((Money) lvl.getFeld()[s.getPosx() + x][s.getPosy() + y]).value);
				m.getLblMoney().setzeInhalt(m.getMoney() + s.getMoney());
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Sword) {
				//add sword to items
				s.setSwords(s.getSwords() + 1);
				//change image from Player
				s.ladeBild(Variables.IMG_PLAYER_SWORD);
				//refresh Menue
				m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Key) {
				//add key to items
				s.setKeys(s.getKeys() + 1);
				//refresh Menue
				m.getLblKeys().setzeInhalt(m.getKeys() + s.getKeys());
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock) {
				//remove key from items
				s.setKeys(s.getKeys() - 1);
				//refresh Menue
				m.getLblKeys().setzeInhalt(m.getKeys() + (s.getKeys() - 1));
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Masterkey) {
				playSound(Variables.SND_TEXT);
				s.setMasterkeys(s.getMasterkeys() + 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
			} else if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getMasterkeys() > 0) {
				s.setMasterkeys(s.getMasterkeys() - 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
			}

			switch (c) {
				case 'w':
					hideItem('y', -1);
					break;
				case 'a':
					hideItem('x', -1);
					break;
				case 's':
					hideItem('y', 1);
					break;
				case 'd':
					hideItem('x', 1);
					break;
				default:
					break;
			}

		}
	

		//move Player Sprite
		if (s.getIsCheat() || !(lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& (lvl.getFeld()[s.getPosx() + x2][s.getPosy() + y2] instanceof Objekte))
				&& !(lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Objekte)) {
			switch (c) {
				case 'w':
					s.moveUp();
					s.setPosy(s.getPosy() + y);
					break;
				case 'a':
					s.moveLeft();
					s.setPosx(s.getPosx() + x);
					break;
				case 's':
					s.moveDown();
					s.setPosy(s.getPosy() + y);
					break;
				case 'd':
					s.moveRight();
					s.setPosx(s.getPosx() + x);
					break;
				default:
					break;
			}
			if (s.getCheatc() < 1) {
				s.setSteps(s.getSteps() + 1);
				m.getLblSteps().setzeInhalt(m.getSteps() + s.getSteps());
			} else {
				s.setSteps(0);
				m.getLblSteps().setzeInhalt("");
			}
		}

		if (lvl.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Stairs) {
			int newLvl = ((Stairs) lvl.getFeld()[s.getPosx() + x][s.getPosy() + y]).getLvl();
			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (lvl.getFeld()[i][j] != null)
						lvl.getFeld()[i][j].verstecke();
					lvl.getFeld()[i][j] = null;
				}
			}
			lvl.setFeld(lvl.ladeL(newLvl));
		}
		
		
		if (lvl.getFeld()[s.getPosx()][s.getPosy()] instanceof Portal) {
			s.go(((Portal) lvl.getFeld()[s.getPosx()][s.getPosy()]).getPosx(),
					((Portal) lvl.getFeld()[s.getPosx()][s.getPosy()]).getPosy());
			int xt = ((Portal) lvl.getFeld()[s.getPosx()][s.getPosy()]).getPosx();
			int yt = ((Portal) lvl.getFeld()[s.getPosx()][s.getPosy()]).getPosy();
			s.setPosx(xt);
			s.setPosy(yt);
		}
	}
}