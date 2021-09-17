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
import sum.komponenten.Etikett;

public class Menu implements Serializable {
	private static final long serialVersionUID = 2357879403419817644L;
	@Getter @Setter
	private String gameLang = System.getProperty("user.language");
	@Getter @Setter
	private String steps;
	@Getter @Setter
	private String swords;
	@Getter @Setter
	private String money;
	@Getter @Setter
	private String keys;
	@Getter @Setter
	private String masterkeys;
	
	/* Labels and their positions */
	@Getter @Setter
	private Etikett lblSteps = new Etikett(600, 575, 100, 50, getSteps());
	@Getter @Setter
	private Etikett lblSwords = new Etikett(600, 590, 100, 50, getSwords());
	@Getter @Setter
	private Etikett lblMoney = new Etikett(600, 605, 400, 50, getMoney());
	@Getter @Setter
	private Etikett lblKeys = new Etikett(600, 620, 100, 50, getKeys());
	@Getter @Setter
	private Etikett lblMasterkeys = new Etikett(600, 635, 100, 50, getMasterkeys());
	@Getter @Setter
	private Etikett lblText = new Etikett(300, 580, 250, 50, null);
	
	public Menu() {
		if (gameLang.equals("de")) {
			setSteps(Variables.DE_LBL_STEPS + ": ");
			setSwords(Variables.DE_LBL_SWORDS + ": ");
			setMoney(Variables.DE_LBL_MONEY + ": ");
			setKeys(Variables.DE_LBL_KEYS + ": ");
		}
		else {
			setSteps(Variables.EN_LBL_STEPS + ": ");
			setSwords(Variables.EN_LBL_SWORDS + ": ");
			setMoney(Variables.EN_LBL_MONEY + ": ");
			setKeys(Variables.EN_LBL_KEYS + ": ");
		}
		setMasterkeys(Variables.LBL_MKEYS + ": ");
		
	}
	
	/** clear text */
	public void clearText() {
		getLblText().setzeInhalt("");
	}

	public void output(String t) {
		JOptionPane.showMessageDialog(null, t);
	}

	public String cmdSwords() {
		if (gameLang.equals("de"))
			return Variables.DE_TXT_CMD_SWORDS;
		else
			return Variables.EN_TXT_CMD_SWORDS;
	}
	
	public String cmdKeys() {
		if (gameLang.equals("de"))
			return Variables.DE_TXT_CMD_KEYS;
		else
			return Variables.EN_TXT_CMD_KEYS;
	}

	/** input (int) */
	public int inputInt(String t) {
		return Integer.parseInt(JOptionPane.showInputDialog(t));
	}

	public void info() {
		if (gameLang.equals("de"))
			output(Variables.DE_CONTROLLS);
		else
			output(Variables.EN_CONTROLLS);
	}
	
	public String cmd() {
		if (gameLang.equals("de"))
			return Variables.DE_TXT_COMMAND;
		else
			return Variables.EN_TXT_COMMAND;
	}
}