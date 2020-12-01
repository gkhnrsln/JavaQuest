package objects;

import gui.Menue;
import sum.multimedia.Bild;
/**
 * @author G. Arslan
 */
public abstract class Objekte extends Bild {
	Menue m = new Menue();
	
	public Objekte(int x, int y, String datei) {
		super(x * 34, y * 34, 34, 34, datei);
	}
	
	public void go(int x, int y) {
		setzePosition(x * 34, y * 34);
	}

	// Movements of Object
	public void moveUp() {
		setzePosition(links(), oben() - 34);
	}

	public void moveLeft() {
		setzePosition(links() - 34, oben());
	}

	public void moveRight() {
		setzePosition(links() + 34, oben());
	}

	public void moveDown() {
		setzePosition(links(), oben() + 34);
	}
}