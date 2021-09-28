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

package main.java.gui;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Properties;

import javax.swing.JOptionPane;

import main.java.enums.PropertiesLoader;
import main.java.gameobject.Player;
import main.java.engine.Sound;
import org.apache.commons.lang3.Validate;
import sum.komponenten.Etikett;

public class Menu implements Serializable {
	private static Menu instance;
	private static final long serialVersionUID = 2357879403419817644L;
	private static Properties configProp = new PropertiesLoader("config", false).getProperties();
	private static Properties txtProp = new PropertiesLoader("text", true).getProperties();
	private Etikett lblSteps;
	private Etikett lblSwords;
	private Etikett lblMoney;
	private Etikett lblKeys;
	private Etikett lblMasterKeys;
	private Etikett lblText;

	private Menu() {
		initializeMenu();
	}

	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}

	private void initializeMenu() {
		lblSteps = new Etikett(600, 575, 100, 50,
				formattedMsg("steps", Player.getInstance().getSteps()));
		lblSwords = new Etikett(600, 590, 100, 50,
				formattedMsg("swords", Player.getInstance().getSwords()));
		lblMoney = new Etikett(600, 605, 400, 50,
				formattedMsg("money", Player.getInstance().getMoney()));
		lblKeys = new Etikett(600, 620, 100, 50,
				formattedMsg("keys", Player.getInstance().getMasterKeys()));
		lblMasterKeys = new Etikett(600, 635, 100, 50,
				formattedMsg("masterKeys", Player.getInstance().getMasterKeys()));
		lblText = new Etikett(300, 580, 250, 50, null);
	}

	/**
	 * Listens to Changes of Player Values, to refresh Menu Values.
	 * @param player
	 */
	public void listenTo(Player player) {
		player.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("steps"))
				lblSteps.setzeInhalt(formattedMsg("steps", evt.getNewValue()));
			else if (evt.getPropertyName().equals("swords"))
				lblSwords.setzeInhalt(formattedMsg("swords", evt.getNewValue()));
			else if (evt.getPropertyName().equals("money"))
				lblMoney.setzeInhalt(formattedMsg("money", evt.getNewValue()));
			else if (evt.getPropertyName().equals("keys"))
				lblKeys.setzeInhalt(formattedMsg("keys", evt.getNewValue()));
			else if (evt.getPropertyName().equals("masterKeys"))
				lblMasterKeys.setzeInhalt(formattedMsg("masterKeys", evt.getNewValue()));
		});
	}

	private void output(String t) {
		Sound.playSound(configProp.getProperty("sfx.menu.move1"));
		JOptionPane.showMessageDialog(null, t);
		Sound.playSound(configProp.getProperty("sfx.menu.select1"));
	}

	private String input(String text){
		Sound.playSound(configProp.getProperty("sfx.menu.move1"));
		String s = JOptionPane.showInputDialog(text);
		Sound.playSound(configProp.getProperty("sfx.menu.select1"));
		return (s != null && ! s.isEmpty()) ? s : "0";
	}

	public Integer cmdSwords() {
		while (true) {
			try {
				String s = input(txtProp.getProperty("txt.cmd.swords"));
				int i = Integer.parseInt(s);
				Validate.inclusiveBetween(0, Integer.MAX_VALUE, i);
				return i;
			} catch (IllegalArgumentException iae) {
				output(txtProp.getProperty("txt.err.nfe"));
			}
		}
	}
	
	public String cmdKeys() {
		return txtProp.getProperty("txt.cmd.keys");
	}

	/**
	 * Display the control of the game in a Messagebox.
	 */
	public void info() {
		output(txtProp.getProperty("txt.controls"));
	}
	
	public String cmd() {
		return input(txtProp.getProperty("txt.cmd"));
	}

	/**
	 * Output Text from People, Villains, ...
	 * @param text
	 */
	public void text(String text) {
		lblText.setzeInhalt(text);
	}

	private String formattedMsg(String prop, Object value){
		return MessageFormat.format(txtProp.getProperty("txt.menu." + prop), value);
	}
}