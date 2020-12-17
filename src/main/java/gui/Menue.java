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

import main.java.enums.Variables;
import sum.komponenten.Etikett;

public class Menue implements Serializable {
	private static final long serialVersionUID = 2357879403419817644L;
	/** System language for Game */
	private String lang = System.getProperty("user.language");
	private String steps;
	private String swords;
	private String money;
	private String keys;
	private String masterkeys;
	
	/* Labels and their positions */
	private Etikett lblSteps = new Etikett(600, 575, 100, 50, getSteps());
	private Etikett lblSwords = new Etikett(600, 590, 100, 50, getSwords());
	private Etikett lblMoney = new Etikett(600, 605, 400, 50, getMoney());
	private Etikett lblKeys = new Etikett(600, 620, 100, 50, getKeys());
	private Etikett lblMasterkeys = new Etikett(600, 635, 100, 50, getMasterkeys());
	private Etikett lblText = new Etikett(300, 580, 250, 50, null);
	
	public Menue() {
		if (lang.equals("de")) {
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
	public void ctxt() {
		getLblText().setzeInhalt("");
	}

	/** output */
	public void ausgabe(String t) {
		JOptionPane.showMessageDialog(null, t);
	}

	public String cmdSwords() {
		if (lang.equals("de")) 
			return Variables.DE_TXT_CMD_SWORDS;
		else
			return Variables.EN_TXT_CMD_SWORDS;
	}
	
	public String cmdKeys() {
		if (lang.equals("de")) 
			return Variables.DE_TXT_CMD_KEYS;
		else
			return Variables.EN_TXT_CMD_KEYS;
	}
	
	/** input (int) */
	public int eingabeInt(String t) {
		return Integer.parseInt(JOptionPane.showInputDialog(t));
	}

	public void info() {
		if (lang.equals("de")) 
			ausgabe(Variables.DE_CONTROLLS);
		else
			ausgabe(Variables.EN_CONTROLLS);	
	}
	
	public String cmd() {
		if (lang.equals("de")) 
			return Variables.DE_TXT_COMMAND;
		else
			return Variables.EN_TXT_COMMAND;
	}

	/** get set */
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public Etikett getLblSteps() {
		return lblSteps;
	}

	public void setLblSteps(Etikett lblSteps) {
		this.lblSteps = lblSteps;
	}

	public Etikett getLblSwords() {
		return lblSwords;
	}

	public void setLblSwords(Etikett lblSwords) {
		this.lblSwords = lblSwords;
	}

	public Etikett getLblMoney() {
		return lblMoney;
	}

	public void setLblMoney(Etikett lblMoney) {
		this.lblMoney = lblMoney;
	}

	public Etikett getLblKeys() {
		return lblKeys;
	}

	public void setLblKeys(Etikett lblKeys) {
		this.lblKeys = lblKeys;
	}

	public Etikett getLblMasterkeys() {
		return lblMasterkeys;
	}

	public void setLblMasterkeys(Etikett lblMasterkeys) {
		this.lblMasterkeys = lblMasterkeys;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getSwords() {
		return swords;
	}

	public void setSwords(String swords) {
		this.swords = swords;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getMasterkeys() {
		return masterkeys;
	}

	public void setMasterkeys(String masterkeys) {
		this.masterkeys = masterkeys;
	}

	public Etikett getLblText() {
		return lblText;
	}

	public void setLblText(Etikett lblText) {
		this.lblText = lblText;
	}
}