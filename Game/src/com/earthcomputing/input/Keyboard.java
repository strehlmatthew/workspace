package com.earthcomputing.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[150];
	public boolean up, down, left, right;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; //key mapping. Also works with W/A/S/D
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}
	
	public void keyPressed(KeyEvent e) { //pressed key register
		keys[e.getKeyCode()] = true;
		
	}

	
	public void keyReleased(KeyEvent e) { //released key register
		keys[e.getKeyCode()] = false;
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
