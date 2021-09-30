/*
 * Copyright (C) 2020-2021  G. Arslan
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

package main.java.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import main.java.enums.PropertiesLoader;
import main.java.gameobject.Box;
import main.java.gameobject.villain.*;
import main.java.gameobject.GameObject;
import main.java.gameobject.npc.*;
import main.java.gameobject.Stairs;
import main.java.gameobject.obstacles.*;
import main.java.gameobject.item.*;
import main.java.gameobject.switches.*;

public class Level<T> implements Serializable {
	private static final long serialVersionUID = -5285864879474325429L;
	private Properties prop = new PropertiesLoader("config", false).getProperties();
	@java.lang.SuppressWarnings("java:S116")
	private final int SIZE_X = Integer.parseInt(prop.getProperty("field.length.x"));
	@java.lang.SuppressWarnings("java:S116")
	private final int SIZE_Y = Integer.parseInt(prop.getProperty("field.length.y"));
	// GameField
	@Getter @Setter
	private GameObject[][] gameField = new GameObject[SIZE_X][SIZE_Y];
	// List of visited fields
	@Getter @Setter
	private List<T> list = new ArrayList<>();
	//first level
	@Getter @Setter
	private int lvl = 1;
	private boolean isChecked = false;

	/* checks if player visited a field before */
	private void checker(int posX, int posY) {
		isChecked = false;
		for (T l : list) {
			if (((Visited) l).x == posX && ((Visited) l).y == posY && ((Visited) l).lvl == 1) {
				isChecked = true;
				break;
			}
		}
	}

	/* generates the level structure */
	private void generateLvl(int lvl) {
		switch (lvl) {
			case 1:
				objects1();
				break;
			case 2:
				objects2();
				break;
			case 3:
				objects3();
				break;
			case 4:
				objects4();
				break;
			case 5:
				objects5();
				break;
			default:
				break;
		}
	}

	private void buildField(int[][] sField) {
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[0].length; j++) {
				switch (sField[j][i]) {
					case 1:
						wall(i, j);
						break;
					case 2:
						box(i, j);
						break;
					case 3:
						sword(i, j);
						break;
					case 4:
						button(i, j);
						break;
					case 5:
						key(i, j);
						break;
					case 6:
						lock(i, j);
						break;
					case 7:
						wand(i, j);
						break;
					default:
						break;
				}
			}
		}
	}

	private void objects1() {
		buildField(LevelStructure.level1());
		stairs(20, 1, 2);
		money(5, 4, 85);
		money(12, 1, 135);
		money(15, 1, 200);
		villain(2, 4, "img.mon1");
		villain(10, 3, "img.mon2");
		villain(20, 3, "img.mon3");
	}

	private void objects2() {
		buildField(LevelStructure.level2());

		checker(5, 1);
		if (!isChecked) opa(5, 1);

		stairs(20, 2, 1);
		stairs(1, 1, 3);
		stairs(3, 14, 4);
		stairs(19, 14, 5);
		money(1, 8, 135);
		money(14, 12, 1000);
		money(13, 12, 2500);
		lock2(3, 9, 1);
		lock2(11, 9, 2);
		lock2(19, 9, 3);
		lock2(11, 12, 3);
	}

	private void objects3() {
		buildField(LevelStructure.level3());
		opa(6,1);
		masterKey(9, 8);
		stairs(9, 7, 2);
	}

	private void objects4() {
		buildField(LevelStructure.level4());
		masterKey(14, 14);
		stairs(3, 13, 2);
		stairs(13, 15, 2);
		portal(1, 15, 1, 1);
		portal(3, 1, 1, 7);
		portal(3, 3, 3, 5);
		portal(5, 3, 3, 5);
		portal(5, 13, 2, 13);
		portal(7, 1, 2, 13);
		portal(11, 7, 2, 6);
		portal(11, 5, 2, 13);
		portal(1, 3, 1, 11);
		portal(1, 5, 5, 1);
		portal(7, 5, 9, 3);
		portal(3, 7, 9, 7);
		portal(1, 9, 5, 15);
		portal(7, 3, 1, 13);
		portal(11, 3, 1, 13);
		portal(9, 1, 1, 13);
		portal(11, 1, 1, 13);
		portal(9, 9, 15, 1);
		portal(9, 11, 1, 13);
		portal(9, 13, 17, 9);
		portal(11, 15, 1, 13);
		portal(13, 11, 1, 13);
		portal(13, 1, 1, 13);
		portal(13, 3, 9, 15);
		portal(15, 7, 1, 13);
		portal(13, 9, 21, 5);
		portal(19, 9, 1, 13);
		portal(19, 11, 15, 9);
		portal(17, 11, 11, 9);
		portal(7, 13, 5, 7);
		portal(5, 5, 7, 11);
		portal(5, 9, 21, 1);
		portal(17, 1, 18, 6);
		portal(19, 1, 1, 13);
		portal(17, 3, 1, 13);
		portal(17, 5, 1, 13);
		portal(19, 5, 1, 13);
		portal(19, 7, 21, 9);
		portal(17, 13, 1, 13);
		portal(19, 13, 1, 13);
		portal(17, 15, 1, 13);
		portal(18, 15, 1, 13);
		portal(19, 15, 1, 13);
		portal(18, 13, 21, 13);
		portal(21, 3, 10, 10);
		portal(21, 7, 18, 2);
		portal(21, 11, 18, 14);
		portal(21, 15, 14, 13);
	}

	private void objects5() {
		buildField(LevelStructure.level5());
		princess(12, 1);
	}
	/**
	 * Loads Level.
	 * @param lvl Level 1 to 5
	 */
	public GameObject[][] loadLvl(int lvl) {
		switch (lvl) {
			case 1:
				generateLvl(1);
				break;
			case 2:
				generateLvl(2);
				break;
			case 3:
				generateLvl(3);
				break;
			case 4:
				generateLvl(4);
				break;
			case 5:
				generateLvl(5);
				break;
			default:
				break;
		}
		return gameField;
	}

	private void button(int x, int y) {
		gameField[x][y] = new Button(x, y);
	}

	private void wall(int x, int y) {
		gameField[x][y] = new Wall(x, y);
	}

	private void box(int x, int y) {
		gameField[x][y] = new Box(x, y);
	}

	private void stairs(int x, int y, int l) {
		gameField[x][y] = new Stairs(x, y, l);
	}

	private void portal(int x, int y, int x2, int y2) {
		gameField[x][y] = new Portal(x, y, x2, y2);
	}

	private void opa(int x, int y) {
		Opa opa = new Opa(x, y);
		gameField[x][y] = opa;
	}

	private void wand(int x, int y) {
		gameField[x][y] = new Wand(x, y);
	}

	private void princess(int x, int y) {
		gameField[x][y] = new Princess(x, y);
	}

	private void villain(int x, int y, String prop) {
		checker(x, y);
		if (!isChecked) gameField[x][y] = new Gegner(x, y, prop);
	}

	private void sword(int x, int y) {
		checker(x, y);
		if (!isChecked) gameField[x][y] = new Sword(x, y);
	}

	private void key(int x, int y) {
		checker(x, y);
		if (!isChecked)	gameField[x][y] = new Key(x, y);
	}

	private void lock(int x, int y) {
		checker(x, y);
		if (!isChecked)	gameField[x][y] = new Lock(x, y);
	}

	private void lock2(int x, int y, int z) {
		checker(x, y);
		if (!isChecked)	gameField[x][y] = new Lock(x, y, z);
	}

	private void money(int x, int y, int z) {
		checker(x, y);
		if (!isChecked)	gameField[x][y] = new Money(x, y, z);
	}

	private void masterKey(int x, int y) {
		checker(x, y);
		if (!isChecked) gameField[x][y] = new MasterKey(x, y);
	}
}

class LevelStructure {
	private LevelStructure() {
		throw new IllegalStateException("Utility class");
	}

	public static int[][] level1() {
		return new int[][]{
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 5, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1 },
				{ 1, 1, 6, 1, 1, 2, 1, 1, 1, 1, 0, 1, 0, 1, 5, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 1, 2, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 6, 1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 4, 1, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 1, 0, 3, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
	}

	public static int[][] level2() {
		return new int[][]{
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
	}

	public static int[][] level3() {
		return new int[][]{
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1 },
				{ 1, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 7, 0, 7, 7, 7, 0, 0, 0, 7, 7, 7, 7, 0, 7, 7, 7, 7, 7, 7, 0, 1 },
				{ 1, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 7, 7, 7, 7, 1 },
				{ 1, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 0, 1 },
				{ 1, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 7, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 1 },
				{ 1, 0, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 0, 0, 7, 0, 7, 7, 7, 7, 7, 0, 1 },
				{ 1, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 7, 0, 7, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 7, 7, 0, 7, 0, 1 },
				{ 1, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 7, 0, 7, 0, 1 },
				{ 1, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 7, 0, 0, 7, 0, 7, 0, 1 },
				{ 1, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 7, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
	}

	public static int[][] level4() {
		return new int[][]{
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
	}

	public static int[][] level5() {
		return new int[][]{
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
	}
}