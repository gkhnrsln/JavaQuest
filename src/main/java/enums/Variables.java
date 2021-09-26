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

package main.java.enums;

public class Variables {
    // Private Constructor
	private Variables() {
	}

	/** {@value #PLAYER_POS_X} */
	public static final int PLAYER_POS_X = 2;
	/** {@value #PLAYER_POS_Y} */
	public static final int PLAYER_POS_Y = 15;
	public static final String BUTTON = "Button";
	public static final String BOX = "Box";
	public static final String MONEY = "Money";
	public static final String MASTERKEY = "MasterKey";
	public static final String PRINCESS = "Princess";
	public static final String LOCK = "Lock";
	public static final String OBSTACLE = "Obstacle";
	public static final String OPA = "Opa";
	public static final String NPC = "NPC";
	public static final String KEY = "Key";
	public static final String STAIRS = "Stairs";
	public static final String SWORD = "Sword";
	public static final String VILLAIN = "Villain";
	// ------------------------SIZE-------------------------------------------
	/** {@value #WINDOW_WIDTH} */
	public static final int WINDOW_WIDTH = 780;
	/** {@value #WINDOW_HEIGHT} */
	public static final int WINDOW_HEIGHT = 680;
	/** {@value #FIELD_LENGTH_X} */
	public static final int FIELD_LENGTH_X = 23;
	/** {@value #FIELD_LENGTH_Y} */
	public static final int FIELD_LENGTH_Y = 17;
	// ------------------------IMAGES-------------------------------------------
	/** {@value #IMG_BG} */
	public static final String IMG_BG = "src/main/resources/img/bg.gif";
	/** {@value #IMG_PLAYER_UP} */
	public static final String IMG_PLAYER_UP = "src/main/resources/img/spieler_up.png";
	/** {@value #IMG_PLAYER_LEFT} */
	public static final String IMG_PLAYER_LEFT = "src/main/resources/img/spieler_left.png";
	/** {@value #IMG_PLAYER_DOWN} */
	public static final String IMG_PLAYER_DOWN = "src/main/resources/img/spieler_down.png";
	/** {@value #IMG_PLAYER_RIGHT} */
	public static final String IMG_PLAYER_RIGHT = "src/main/resources/img/spieler_right.png";
	/** {@value #IMG_PLAYER_SWORD} */
	public static final String IMG_PLAYER_SWORD = "src/main/resources/img/spieler_sword.png";
	/** {@value #IMG_PLAYER_MONEY} */
	public static final String IMG_PLAYER_MONEY = "src/main/resources/img/spieler_money.png";
	/** {@value #IMG_PLAYER_CHEAT} */
	public static final String IMG_PLAYER_CHEAT = "src/main/resources/img/spieler_cheat.png";
	/** {@value #IMG_PRINCESS} */
	public static final String IMG_PRINCESS = "src/main/resources/img/princess.png";
	/** {@value #IMG_MON_1} */
	public static final String IMG_MON_1 = "src/main/resources/img/mon1.png";
	/** {@value #IMG_MON_2} */
	public static final String IMG_MON_2 = "src/main/resources/img/mon2.png";
	/** {@value #IMG_MON_3} */
	public static final String IMG_MON_3 = "src/main/resources/img/mon3.png";
	/** {@value #IMG_BUTTON} */
	public static final String IMG_BUTTON = "src/main/resources/img/button.png";
	/** {@value #IMG_OPA} */
	public static final String IMG_OPA = "src/main/resources/img/opa.png";
	/** {@value #IMG_BOX} */
	public static final String IMG_BOX = "src/main/resources/img/box.png";
	/** {@value #IMG_LOCK_1} */
	public static final String IMG_LOCK_1 = "src/main/resources/img/lock1.png";
	/** {@value #IMG_LOCK_2} */
	public static final String IMG_LOCK_2 = "src/main/resources/img/lock2.png";
	/** {@value #IMG_STAIRS} */
	public static final String IMG_STAIRS = "src/main/resources/img/stairs.png";
	/** {@value #IMG_WALL} */
	public static final String IMG_WALL = "src/main/resources/img/wall.png";
	/** {@value #IMG_WALL} */
	public static final String IMG_INV_WALL = "src/main/resources/img/invisibleWall.png";
	/** {@value #IMG_PORTAL} */
	public static final String IMG_PORTAL = "src/main/resources/img/portal.png";
	/** {@value #IMG_SWORD} */
	public static final String IMG_SWORD = "src/main/resources/img/sword.png";
	/** {@value #IMG_MONEY} */
	public static final String IMG_MONEY = "src/main/resources/img/money.png";
	/** {@value #IMG_KEY} */
	public static final String IMG_KEY = "src/main/resources/img/key.png";
	/** {@value #IMG_MKEY} */
	public static final String IMG_MKEY = "src/main/resources/img/masterkey.png";
	// ------------------------SOUNDFILES--------------------------------------
	/** {@value #SND_PRINCESS} */
	public static final String SND_PRINCESS = "src/main/resources/wav/princess.wav";
	/** {@value #SND_OBSTACLE} */
	public static final String SND_OBSTACLE = "src/main/resources/wav/obstacle.wav";
	/** {@value #SND_MONEY} */
	public static final String SND_MONEY = "src/main/resources/wav/money.wav";
	/** {@value #SND_TEXT} */
	public static final String SND_TEXT = "src/main/resources/wav/text.wav";
	public static final String SFX_MENU_SELECT1 = "src/main/resources/wav/sfx_menu_select1.wav";
	public static final String SFX_MENU_MOVE1 = "src/main/resources/wav/sfx_menu_move1.wav";
	public static final String SFX_MOVEMENT_STAIRS4LOOP = "src/main/resources/wav/sfx_movement_stairs4loop.wav";
	public static final String SFX_SOUNDS_INTERACTION26 = "src/main/resources/wav/sfx_sounds_interaction26.wav";
	public static final String SFX_WPN_SWORD1 = "src/main/resources/wav/sfx_wpn_sword1.wav";

	// ------------------------MENU-------------------------------------------
	/** {@value #LBL_MKEYS} */
	public static final String LBL_MKEYS = "MASTERKEYS";
	/** {@value #DE_LBL_STEPS} */
	public static final String DE_LBL_STEPS = "SCHRITTE";
	/** {@value #DE_LBL_SWORDS} */
	public static final String DE_LBL_SWORDS = "SCHWERTER";
	/** {@value #DE_LBL_MONEY} */
	public static final String DE_LBL_MONEY = "GELD";
	/** {@value #DE_LBL_KEYS} */
	public static final String DE_LBL_KEYS = "SCHL\u00dcSSEL";
	/** {@value #DE_CONTROLLS} */
	public static final String DE_CONTROLLS = "Steuerung:\n[W] = Oben\n[A] = Links\n[S] = Unten\n[D] = Rechts\n[C] = Befehl";
	/** {@value #EN_LBL_STEPS} */
	public static final String EN_LBL_STEPS = "STEPS";
	/** {@value #EN_LBL_SWORDS} */
	public static final String EN_LBL_SWORDS = "SWORDS";
	/** {@value #EN_LBL_MONEY} */
	public static final String EN_LBL_MONEY = "MONEY";
	/** {@value #EN_LBL_KEYS} */
	public static final String EN_LBL_KEYS = "KEYS";
	/** {@value #EN_CONTROLLS} */
	public static final String EN_CONTROLLS = "Control:\n[W] = Up\n[A] = Left\n[S] = Down\n[D] = Right\n[C] = Command";
	/** {@value #DE_TXT_COMMAND} */
	public static final String DE_TXT_COMMAND = "Gib den Befehl ein.";
	/** {@value #EN_TXT_COMMAND} */
	public static final String EN_TXT_COMMAND = "Enter the command.";
	/** {@value #DE_TXT_CMD_SWORDS} */
	public static final String DE_TXT_CMD_SWORDS = "Gib die Anzahl an Schwertern an, die du willst.";
	/** {@value #EN_TXT_CMD_SWORDS} */
	public static final String EN_TXT_CMD_SWORDS = "Specify the number of swords you want.";
	/** {@value #DE_TXT_CMD_KEYS} */
	public static final String DE_TXT_CMD_KEYS = "Gib die Anzahl an Schl\u00fcssel an, die du willst.";
	/** {@value #EN_TXT_CMD_KEYS} */
	public static final String EN_TXT_CMD_KEYS = "Specify the number of keys you want.";

	public static final String DE_TXT_ERROR_NFE = "Bitte gib eine g\u00fcltige Zahl (>0) ein.";
	public static final String EN_TXT_ERROR_NFE = "Please enter a valid number (>0).";
	// ------------------------TEXT-------------------------------------------
	/** {@value #DE_TXT_GEGNER_001} */
	public static final String DE_TXT_GEGNER_001 = "Ha ha! Du willst ohne Waffen k\u00e4mpfen?";
	/** {@value #DE_TXT_OPA_001} */
	public static final String DE_TXT_OPA_001 = "<html>Hey, kleiner! Wenn du mehr als 450<p/>\""
			+ "Euro hast, sprich mich nochmal an!<p/>" + "Ich gib dir dann einen MasterKey!</html>";
	/** {@value #DE_TXT_OPA_002} */
	public static final String DE_TXT_OPA_002 = "Hier hast du deinen MasterKey!";
	/** {@value #DE_TXT_OPA_003} */
	public static final String DE_TXT_OPA_003 = "Hab keinen MasterKey mehr. Hau ab!";
	/** {@value #DE_TXT_OPA_004} */
	public static final String DE_TXT_OPA_004 = "<html>Hey! Dieser Ort ist komisch. Als<p/>"
			+ "ob es hier unsichtbare W\u00e4nde gibt.</html>";
	/** {@value #DE_TXT_PRINCESS_001} */
	public static final String DE_TXT_PRINCESS_001 = "<html>Danke das du mich gerettet hast.<p/>"
			+ "Lass uns schnell von hier weg!</html>";
}
