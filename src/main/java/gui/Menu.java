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

import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;
import main.java.enums.Variables;
import main.java.gameobject.Player;
import main.java.engine.Sound;
import sum.komponenten.Etikett;

public class Menu implements Serializable {
	private static Menu instance;
	private static final long serialVersionUID = 2357879403419817644L;
	@Getter @Setter
	private String gameLang;
	@Getter @Setter
	private String steps;
	@Getter @Setter
	private String swords;
	@Getter @Setter
	private String money;
	@Getter @Setter
	private String keys;
	@Getter @Setter
	private String masterKeys;
	/* Labels and their positions */
	@Getter @Setter
	private Etikett lblSteps;
	@Getter @Setter
	private Etikett lblSwords;
	@Getter @Setter
	private Etikett lblMoney;
	@Getter @Setter
	private Etikett lblKeys;
	@Getter @Setter
	private Etikett lblMasterKeys;
	@Getter @Setter
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
		gameLang = System.getProperty("user.language");

		if (gameLang.equals("de")) {
			steps = Variables.DE_LBL_STEPS + ": ";
			swords = Variables.DE_LBL_SWORDS + ": " ;
			money = Variables.DE_LBL_MONEY + ": ";
			keys = Variables.DE_LBL_KEYS + ": ";
		}
		else {
			steps = Variables.EN_LBL_STEPS + ": ";
			swords = Variables.EN_LBL_SWORDS + ": ";
			money = Variables.EN_LBL_MONEY + ": ";
			keys = Variables.EN_LBL_KEYS + ": ";
		}
		masterKeys = Variables.LBL_MKEYS + ": ";

		lblSteps = new Etikett(600, 575, 100, 50, steps + Player.getInstance().getSteps());
		lblSwords = new Etikett(600, 590, 100, 50, swords + Player.getInstance().getSwords());
		lblMoney = new Etikett(600, 605, 400, 50, money + Player.getInstance().getMoney());
		lblKeys = new Etikett(600, 620, 100, 50, keys + Player.getInstance().getKeys());
		lblMasterKeys = new Etikett(600, 635, 100, 50, masterKeys + Player.getInstance().getMasterKeys());
		lblText = new Etikett(300, 580, 250, 50, null);
	}

	/**
	 * Listens to Changes of Player Values, to refresh Menu Values.
	 * @param player
	 */
	public void listenTo(Player player) {
		player.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("steps"))
				lblSteps.setzeInhalt(steps + evt.getNewValue());
			else if (evt.getPropertyName().equals("swords"))
				lblSwords.setzeInhalt(swords + evt.getNewValue());
			else if (evt.getPropertyName().equals("money"))
				lblMoney.setzeInhalt(money + evt.getNewValue());
			else if (evt.getPropertyName().equals("keys"))
				lblKeys.setzeInhalt(keys + evt.getNewValue());
			else if (evt.getPropertyName().equals("masterKeys"))
				lblMasterKeys.setzeInhalt(masterKeys + evt.getNewValue());
		});
	}

	private void output(String t) {
		Sound.playSound(Variables.SFX_MENU_MOVE1);
		JOptionPane.showMessageDialog(null, t);
		Sound.playSound(Variables.SFX_MENU_SELECT1);
	}

	private String input(String text){
		Sound.playSound(Variables.SFX_MENU_MOVE1);
		String s = JOptionPane.showInputDialog(text);
		Sound.playSound(Variables.SFX_MENU_SELECT1);
		return (s != null && ! s.isEmpty()) ? s : "0";
	}

	public Integer cmdSwords() {
		while (true) {
			try {
				String s;
				if (gameLang.equals("de"))
					s = input(Variables.DE_TXT_CMD_SWORDS);
				else
					s = input(Variables.EN_TXT_CMD_SWORDS);

				int i = Integer.parseInt(s);

				if (i < 0) throw new NumberFormatException();

				return i;
			} catch (NumberFormatException nfe) {
				if (gameLang.equals("de"))
					output(Variables.DE_TXT_ERROR_NFE);
				else
					output(Variables.EN_TXT_ERROR_NFE);
			}
		}
	}
	
	public String cmdKeys() {
		if (gameLang.equals("de"))
			return Variables.DE_TXT_CMD_KEYS;
		else
			return Variables.EN_TXT_CMD_KEYS;
	}

	/**
	 * Display the control of the game in a Messagebox.
	 */
	public void info() {
		output(gameLang.equals("de") ? Variables.DE_CONTROLLS : Variables.EN_CONTROLLS);
	}
	
	public String cmd() {
		if (gameLang.equals("de"))
			return input(Variables.DE_TXT_COMMAND);
		else
			return input(Variables.EN_TXT_COMMAND);
	}

	/**
	 * Output Text from People, Villains, ...
	 * @param text
	 */
	public void text(String text) {
		lblText.setzeInhalt(text);
	}
}