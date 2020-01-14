package entertheblack.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entertheblack.gui.Screen;
import entertheblack.menu.Assets;
import entertheblack.storage.Node;

// A map of systems.
// TODO: Use for automatic hyperspace navigation.

public class StarMap extends Screen {
	public List<Star> systems = new ArrayList<>();

	public StarMap(String str, String file) {
		char [] data = str.toCharArray();
		int depth = 0;
		StringBuilder stb = new StringBuilder();
		String name = "";
		for(int i = 0; i < data.length; i++) {
			switch(depth) {
			case 0:
				if(data[i] == '{') {
					name = stb.toString();
					stb = new StringBuilder();
					depth = 1;
				} else if(data[i] != ' ' && data[i] != '\n' && data[i] != '	') {
					stb.append(data[i]);
				}
				break;
			default:
				if(data[i] == '}') {
					depth--;
					if(depth == 0) {
						systems.add(new Star(name, stb.toString(), file));
						stb = new StringBuilder();
					}
					else
						stb.append(data[i]);
				} else if(data[i] != ' ' && data[i] != '	') {
					stb.append(data[i]);
					if(data[i] == '{')
						depth++;
				}
				break;
			}
		}
		if(depth != 0) {
			System.err.println("Error in "+file+":");
			System.err.println("Could not find \"}\"!");
		}
	}
	public StarMap(Node data, String file) {
		int depth = 0;
		String name = data.value;
		for(int i = 0; i < data.nextNodes.length; i++) {
			systems.add(new Star(name, data.nextNodes[i], file));
		}
		if(depth != 0) {
			System.err.println("Error in "+file+":");
			System.err.println("Could not find \"}\"!");
		}
	}
	
	// Save all data from this system:
	public void save(StringBuilder file) {
		for(Star star : systems) {
			file.append("{");
			star.save(file);
			file.append("}");
		}
	}
	
	Screen previous;
	public void activate(Screen prev) {
		previous = prev; // Store the previous screen upon opening the map.
	}
	
	private static int getRadius(Planet p) {
		double r = Math.sqrt(p.r/10);
		if(r > 10)
			r = 9+Math.log(r); // Prevent r from growing too fast.
		return (int)r;
	}
	
	public void paint(Graphics2D g) {
		g.translate(960, 540);
		for(Star system : systems) {
			int r = getRadius(system.planets[0]);
			g.drawImage(system.planets[0].img, system.x/100-r, system.y/100-r, r*2, r*2, null);
		}
		g.translate(-960, -540);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'm') {
			Assets.screen = previous;
		}
		if(e.getKeyCode() == 27) {
			Assets.screen = previous;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {} // Not needed.

	@Override
	public void mouseUpdate(int x, int y, boolean pressed) {
		// TODO: Show Information when hovering above with mouse.
	}
}
