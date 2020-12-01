import enums.Variables;
import gui.Menue;
import objects.Box;
import objects.Button;
import objects.Gegner;
import objects.Lock;
import objects.Objekte;
import objects.Opa;
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
	private Level<Visited> l = new Level<>();
	/** Background Image of Map */
	private Bild bg = new Bild(0, 0, 0, 0, Variables.IMG_BG);
	private Player s = new Player();
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		super(Variables.WINDOW_WIDTH, Variables.WINDOW_HEIGHT, true);
		l.setFeld(l.ladeL(l.getLvl()));
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
	 * @param t
	 * @param r
	 */
	public void hideItem(char t, int r) {
		int x = 0;
		int y = 0;

		switch (t) {
			case 'y': y = r; break;
			case 'x': x = r; break;
			default: break;
		}

		l.getListe().haengeAn(new Visited(s.getPosx() + x, s.getPosy() + y, l.getLvl()));
		l.getFeld()[s.getPosx() + x][s.getPosy() + y].verstecke();
		l.getFeld()[s.getPosx() + x][s.getPosy() + y] = null;
	}

	/**
	 * Here it is checked if the box can be moved further (i.e. if there are no obstacles). 
	 * @param c
	 * @param r
	 */
	public void checkKisteHindernis(char c, int r) {
		int x = 0;
		int y = 0;

		switch (c) {
			case 'y': y = r; break;
			case 'x': x = r; break;
			default: break;
		}

		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& l.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] instanceof Button) {
			l.getFeld()[s.getPosx() + x][s.getPosy() + y].moveDown();
			l.getFeld()[18][14].verstecke();
			l.getFeld()[18][14] = null;
			l.getFeld()[s.getPosx()][s.getPosy() + (y * 2)] = l.getFeld()[s.getPosx()][s.getPosy() + y];
			l.getFeld()[s.getPosx()][s.getPosy() + y] = null;
		}

		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& !(l.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] instanceof Objekte)) {
			if (c == 'x') {
				if (r == 1)
					l.getFeld()[s.getPosx() + x][s.getPosy() + y].moveRight();
				else if (r == -1)
					l.getFeld()[s.getPosx() + x][s.getPosy() + y].moveLeft();

			} else if (c == 'y') {
				if (r == -1)
					l.getFeld()[s.getPosx() + x][s.getPosy() + y].moveUp();
				else if (r == 1) {
					l.getFeld()[s.getPosx() + x][s.getPosy() + y].moveDown();
					/*
					 * if(l.getFeld()[s.get_posx()+x][s.get_posy()+2] instanceof Schalter){
					 * l.getFeld()[18][14].verstecke(); l.getFeld()[18][14] = null; }
					 */

				}
			}
			l.getFeld()[s.getPosx() + (x * 2)][s.getPosy() + (y * 2)] = l.getFeld()[s.getPosx() + x][s.getPosy()
					+ y];
			l.getFeld()[s.getPosx() + x][s.getPosy() + y] = null;
		}
	}
	
	/**
	 * 
	 */
	public void bearbeiteTaste(char c) {
		m.ctxt();
		//int [] pos = new int[]{0,0,0,0}; //to do
		int x = 0;
		int y = 0;
		int x2 = 0;
		int y2 = 0;

		switch (c) {
			case 'w':
				s.ladeBild(Variables.IMG_PLAYER_UP);
				y = -1;
				y2 = -2;
				checkKisteHindernis('y', -1);
				break;
			case 'a':
				s.ladeBild(Variables.IMG_PLAYER_LEFT);
				x = -1;
				x2 = -2;
				checkKisteHindernis('x', -1);
				break;
			case 's':
				s.ladeBild(Variables.IMG_PLAYER_DOWN);
				y = 1;
				y2 = 2;
				if (l.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Gegner && s.getSwords() < 1)
					m.getLblText().setzeInhalt(Gegner.getTxt()[0]);
				checkKisteHindernis('y', 1);
				break;
			case 'd':
				s.ladeBild(Variables.IMG_PLAYER_RIGHT);
				x = 1;
				x2 = 2;
				checkKisteHindernis('x', 1);
				break;
			case 'i': m.info(); break;
			case 'c':
				String cmd = m.cmd();
				if (cmd.equals("glitch")) {
					s.cheatmode();
				} else if (cmd.equals("swords")) {
					s.setSwords(m.eingabeInt("Gib die Anzahl an Schwertern an, die du willst...\nAmount swords"));
					m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
				} else if (cmd.equals("keys")) {
					s.setKeys(m.eingabeInt("Gib die Anzahl an Schl&uuml;ssel an, die du willst...\nAmount keys"));
					m.getLblKeys().setzeInhalt(s.getKeys());
				} else if (cmd.equals("highscore")) m.hiscore();
				break;
			default:
				break;
		}
		//if Player is in front of Button, hidden Area is accesible
		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Button) {
			l.getFeld()[18][14].zeige();
		}

		//if Player is in front of a person, like Princess/Opa
		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Princess || l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Opa) {
			playSound(Variables.SND_PRINCESS);
			if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Princess) {
				m.getLblText().setzeInhalt(Princess.getTxt()[0]);
			} else {
				m.getLblText().setzeInhalt(Opa.getTxt()[3]);
			}
		}

		if (c == 'w') {
			if (l.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Opa
					&& ((Opa) l.getFeld()[s.getPosx()][s.getPosy() + y]).getMasterkey() == 0) {
				playSound(Variables.SND_TEXT);
				m.getLblText().setzeInhalt(Opa.getTxt()[2]);
			} else if (l.getFeld()[s.getPosx()][s.getPosy() + y] instanceof Opa) {
				if (s.getMoney() < 450) {
					playSound(Variables.SND_TEXT);
					m.getLblText().setzeInhalt(Opa.getTxt()[0]);
				} else {
					l.getListe().haengeAn(new Visited(s.getPosx(), s.getPosy() + y, l.getLvl()));
					s.setMoney(s.getMoney() - 450);
					
					((Opa) l.getFeld()[s.getPosx()][s.getPosy() + y]).setMasterkey(Opa.getMasterkey()-1);
					m.getLblMoney().setzeInhalt(m.getMoney() + s.getMoney());
					s.setMasterkeys(s.getMasterkeys() + 1);
					m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
					m.getLblText().setzeInhalt(Opa.getTxt()[1]);
				}
			}
		}

		// Hier fehlt noch was
		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 1
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Gegner && s.getSwords() > 0
				|| (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 2)
				|| (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getMasterkeys() > 0)
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Money
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Sword
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Key
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Masterkey
				|| l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getKeys() > 0) {
			if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Gegner) {
				s.setSwords(s.getSwords() - 1);
				m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Money) {
				playSound(Variables.SND_MONEY);
				s.ladeBild(Variables.IMG_PLAYER_MONEY);
				s.setMoney(s.getMoney() + ((Money) l.getFeld()[s.getPosx() + x][s.getPosy() + y]).value);
				m.getLblMoney().setzeInhalt(m.getMoney() + s.getMoney());
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Sword) {
				s.setSwords(s.getSwords() + 1);
				s.ladeBild(Variables.IMG_PLAYER_SWORD);
				m.getLblSwords().setzeInhalt(m.getSwords() + s.getSwords());
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Key) {
				s.setKeys(s.getKeys() + 1);
				m.getLblKeys().setzeInhalt(m.getKeys() + s.getKeys());
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock) {
				m.getLblKeys().setzeInhalt(m.getKeys() + (s.getKeys() - 1));
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Masterkey) {
				playSound(Variables.SND_TEXT);
				s.setMasterkeys(s.getMasterkeys() + 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
			} else if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Lock && s.getMasterkeys() > 0) {
				s.setMasterkeys(s.getMasterkeys() - 1);
				m.getLblMasterkeys().setzeInhalt(m.getMasterkeys() + s.getMasterkeys());
			}

			switch (c) {
				case 'w': hideItem('y',-1); break;
				case 'a': hideItem('x',-1); break;
				case 's': hideItem('y', 1); break;
				case 'd': hideItem('x', 1); break;
				default:
					break;
			}

		}

		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Objekte && !s.getIsCheat())
			playSound(Variables.SND_OBSTACLE);

		if (s.getIsCheat() || !(l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Box
				&& (l.getFeld()[s.getPosx() + x2][s.getPosy() + y2] instanceof Objekte))
				&& !(l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Objekte)) {
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

		if (l.getFeld()[s.getPosx() + x][s.getPosy() + y] instanceof Stairs) {
			int newLvl = ((Stairs) l.getFeld()[s.getPosx() + x][s.getPosy() + y]).getLevel();
			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (l.getFeld()[i][j] != null)
						l.getFeld()[i][j].verstecke();
					l.getFeld()[i][j] = null;
				}
			}
			l.setFeld(l.ladeL(newLvl));
		}

		if (l.getFeld()[s.getPosx()][s.getPosy()] instanceof Portal) {
			s.go(((Portal) l.getFeld()[s.getPosx()][s.getPosy()]).getPosx(),
					((Portal) l.getFeld()[s.getPosx()][s.getPosy()]).getPosy());
			//int xt = s.getPosx();
			//int yt = s.getPosy();
			int xt = ((Portal) l.getFeld()[s.getPosx()][s.getPosy()]).getPosx();
			int yt = ((Portal) l.getFeld()[s.getPosx()][s.getPosy()]).getPosy();
			s.setPosx(xt);
			s.setPosy(yt);
		}
	}
}