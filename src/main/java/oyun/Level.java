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

package main.java.oyun;

import java.util.ArrayList;
import java.util.List;

import main.java.enums.Variables;
import main.java.objects.Box;
import main.java.objects.villain.*;
import main.java.objects.Objekte;
import main.java.objects.npc.*;
import main.java.objects.Stairs;
import main.java.objects.obstacles.*;
import main.java.objects.items.*;
import main.java.objects.switches.*;

public class Level<T> {
	/** GameField */
	private Objekte[][] feld = new Objekte[Variables.FIELD_LENGTH_X][Variables.FIELD_LENGTH_Y];
	/** List for visited fields */
	private List<T> list = new ArrayList<>();
	//first level
	private int lvl = 1;
	private boolean isChecked = false;

	private void button(int x, int y) {
		feld[x][y] = new Button(x, y);
	}

	private void wall(int x, int y) {
		feld[x][y] = new Wall(x, y);
	}

	private void box(int x, int y) {
		feld[x][y] = new Box(x, y);
	}

	private void stairs(int x, int y, int l) {
		feld[x][y] = new Stairs(x, y, l);
	}

	private void portal(int x, int y, int x2, int y2) {
		feld[x][y] = new Portal(x, y, x2, y2);
	}

	private void opa(int x, int y) {
		feld[x][y] = new Opa(x, y);
	}

	private void wand(int x, int y) {
		feld[x][y] = new Wand(x, y);
	}

	private void princess(int x, int y) {
		feld[x][y] = new Princess(x, y);
	}

	private void gegner(int x, int y, String bild) {
		checker(x, y);
		if (!isChecked) feld[x][y] = new Gegner(x, y, bild);
	}

	private void sword(int x, int y) {
		checker(x, y);
		if (!isChecked) feld[x][y] = new Sword(x, y);
	}

	private void key(int x, int y) {
		checker(x, y);
		if (!isChecked)	feld[x][y] = new Key(x, y);
	}

	private void lock(int x, int y) {
		checker(x, y);
		if (!isChecked)	feld[x][y] = new Lock(x, y);
	}

	private void lock2(int x, int y, int z) {
		checker(x, y);
		if (!isChecked)	feld[x][y] = new Lock(x, y, z);
	}

	private void money(int x, int y, int z) {
		checker(x, y);
		if (!isChecked)	feld[x][y] = new Money(x, y, z);
	}

	private void masterkey(int x, int y) {
		checker(x, y);
		if (!isChecked) feld[x][y] = new Masterkey(x, y);
	}

	/** checks if player visited an field before */
	private void checker(int posx, int posy) {
		isChecked = false;
		for (T l : list) {
			if (((Visited) l).x == posx && ((Visited) l).y == posy && ((Visited) l).lvl == 1) {
				isChecked = true;
			}
		}
	}

	private int[][] level1() {
		int[][] sfeld = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 1, 0, 7, 0, 1 },
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
		return sfeld;
	}

	private int[][] level2() {
		int[][] sfeld = {
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
		return sfeld;
	}

	private int[][] level3() {
		int[][] sfeld = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0, 1 },
			{ 1, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 2, 0, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 1 },
			{ 1, 0, 2, 0, 2, 0, 2, 0, 2, 5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 2, 0, 2, 0, 2, 0, 2, 4, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1 },
			{ 1, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 0, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 1 },
			{ 1, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 1 },
			{ 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		};
		return sfeld;
	}

	private int[][] level4() {
		int[][] sfeld = {
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
		return sfeld;
	}

	private int[][] level5() {
		int[][] sfeld = {
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
		return sfeld;
	}
	/* generates the level structure */
	private void generateLvl(int lvl) {
		if (lvl == 1) {
			int[][] sfeld = level1();

			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					switch (sfeld[j][i]) {
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
						stairs(i, j, 2);
						break;
					default:
						break;
					}
				}
			}
			money(5, 4, 85);
			money(12, 1, 135);
			money(15, 1, 200);
			gegner(2, 4, Variables.IMG_MON_1);
			gegner(10, 3, Variables.IMG_MON_2);
			gegner(20, 3, Variables.IMG_MON_3);
		} else if (lvl == 2) {
			int[][] sfeld = level2();

			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					if (sfeld[j][i] == 1) {
						wall(i, j);
					}
				}
			}
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
		} else if (lvl == 3) {
			int[][] sfeld = level3();

			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					switch (sfeld[j][i]) {
					case 1:
						wall(i, j);
						break;
					case 2:
						wand(i, j);
						break;
					case 3:
						opa(i, j);
						break;
					case 4:
						masterkey(i, j);
						break;
					case 5:
						stairs(i, j, 2);
						break;
					default:
						break;
					}
				}
			}

		} else if (lvl == 4) {
			int[][] sfeld = level4();

			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					switch (sfeld[j][i]) {
					case 1:
						wall(i, j);
						break;
					case 2:
						masterkey(i, j);
						break;
					default:
						break;
					}
				}
			}
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
		} else if (lvl == 5) {
			int[][] sfeld = level5();

			for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
				for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
					switch (sfeld[j][i]) {
					case 1:
						wall(i, j);
						break;
					case 2:
						princess(i, j);
						break;
					default:
						break;
					}
				}
			}
		}
	}
	/**
	 * Loads Level.
	 * @param lvl Level 1 to 5
	 */
	public Objekte[][] ladeL(int lvl) {
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
		return feld;
	}

	//getter setter
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public Objekte[][] getFeld() {
		return feld;
	}

	public void setFeld(Objekte[][] feld) {
		this.feld = feld;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}