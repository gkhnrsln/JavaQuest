package main.java;
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
import main.java.engine.GamePlay;
import main.java.enums.PropertiesLoader;
import main.java.gui.Menu;
import main.java.gameobject.Player;
import main.java.engine.Level;
import main.java.engine.Visited;

import sum.ereignis.EBAnwendung;
import sum.multimedia.Bild;

import java.util.Properties;

public class Main extends EBAnwendung {
	private static final Properties PROP = new PropertiesLoader("config", false).getProperties();
	private static final int WIDTH = Integer.parseInt(PROP.getProperty("window.width"));
	private static final int HEIGHT = Integer.parseInt(PROP.getProperty("window.height"));

	public static void main(String[] args) {
		new Main();
	}

	@java.lang.SuppressWarnings("squid:S1481")
	public Main() {
		super(WIDTH, HEIGHT,false);
		//Background image of map
		Bild bg = new Bild(0, 0, 0, 0, PROP.getProperty("img.bg"));
		Menu.getInstance().listenTo(Player.getInstance());
		Level<Visited> lvl = GamePlay.getLVL();
		lvl.setGameField(lvl.loadLvl(lvl.getLvl()));
		fuehreAus();
	}

	/**
	 * Handle Movement of player
	 * @param c Which key is pressed?
	 */
	@Override
	public void bearbeiteTaste(char c) {
		Menu.getInstance().text("");
		GamePlay.control(c);
	}
}