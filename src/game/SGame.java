package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entertheblack.gui.Screen;

public class SGame extends Screen {
	World world;
	public SGame(int save) {
		if(save >= 0) {
			loadWorld();
		} else {
			generateWorld();
		}
	}

	private void generateWorld() {
		
	}

	private void loadWorld() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void paint(Graphics2D g) {
		
	}

}
