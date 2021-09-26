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
	/** {@value #IMG_PLAYER_SWORD} */
	public static final String IMG_PLAYER_SWORD = "src/main/resources/img/player_sword.png";
	/** {@value #IMG_PLAYER_MONEY} */
	public static final String IMG_PLAYER_MONEY = "src/main/resources/img/player_money.png";
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
