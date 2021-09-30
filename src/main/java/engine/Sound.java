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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.logging.Logger;

public class Sound {
	private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());
	private static Clip activeClip;

	private Sound(){
		throw new IllegalStateException("Utility class");
	}
	/**
	 * Plays a .wav-File.
	 * @param file Filename of the .wav-File
	 */
	public static void playSound(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			LOGGER.info(".wav File not found." + e.getMessage());
		}
	}

	public static void playSoundLoop(String file) {
		stopSoundLoop();
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			activeClip = clip;
		} catch (Exception e) {
			LOGGER.info(".wav File not found." + e.getMessage());
		}
	}

	public static void stopSoundLoop() {
		if (activeClip != null) activeClip.stop();
	}
}