package dev.epchao.snakegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	public boolean upDirection = false;
	public boolean downDirection = false;
	public boolean leftDirection = false;
	public boolean rightDirection = false;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick() {
		for(int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			}
			else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
	}

	public boolean keyJustPressed(int keyCode) {
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if((key == KeyEvent.VK_A) && (!rightDirection)) {
			leftDirection = true;
            upDirection = false;
            downDirection = false;
		}
        if ((key == KeyEvent.VK_D) && (!leftDirection)) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if ((key == KeyEvent.VK_W) && (!downDirection)) {
            upDirection = true;
            rightDirection = false;
            leftDirection = false;
        }

        if ((key == KeyEvent.VK_S) && (!upDirection)) {
            downDirection = true;
            rightDirection = false;
            leftDirection = false;
        }
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void resetDirections() {
		upDirection = false;
		downDirection = false;
		leftDirection = false;
		rightDirection = false;

	}
	
}
