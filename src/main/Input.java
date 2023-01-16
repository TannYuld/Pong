package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Input implements KeyListener {
	
	private ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	private ArrayList<Integer> oneTimePressedKeys = new ArrayList<Integer>();
	
	private boolean isKeyboardPressed = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyCode())) {
			pressedKeys.add(e.getKeyCode());
		}
		
		if(!oneTimePressedKeys.contains(e.getKeyCode()) && !isKeyboardPressed) {
			oneTimePressedKeys.add(e.getKeyCode());
		}
		
		isKeyboardPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode())) {
			pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode()));
		}
		
		if(oneTimePressedKeys.contains(e.getKeyCode())) {
			oneTimePressedKeys.remove(oneTimePressedKeys.indexOf(e.getKeyCode()));
		}
		
		isKeyboardPressed = false;
	}
	
	public boolean isKeyBeingPressed(int e) {
		if(pressedKeys == null) {
			return false;
		}
		
		return (pressedKeys.contains(e)) ? true : false;
	}
	
	public boolean isKeyPressed(int e) {
		if(oneTimePressedKeys.contains(e)){
			oneTimePressedKeys.remove(oneTimePressedKeys.indexOf(e));
			return true;
		}else {
			return false;
		}
	}
	
}
