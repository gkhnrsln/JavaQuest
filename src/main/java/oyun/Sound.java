/*
 * Copyright (C) 2021  G. Arslan
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

import sum.multimedia.Ton;

public class Sound {
	/**
	 * Plays a .wav-File.
	 * @param file Filename of the .wav-File
	 */
	public static void playSound(String file) {
		Ton sound = new Ton();
		//load sound
		sound.ladeTon(file);
		//play sound
		sound.spieleTon();
	}
}