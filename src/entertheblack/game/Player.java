package entertheblack.game;

import entertheblack.storage.Inventory;

public class Player {
	public int techLevel = 0;
	public Inventory inv;
	public Player() { // TODO: Read from file.
		inv = new Inventory(); // TODO: Make finite!
	}
}