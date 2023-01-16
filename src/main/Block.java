package main;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

public class Block {
	
	private final ArrayList<Integer> properties = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
	
	public void initializeProp(int startPosX,int startPosY, int widht, int height) {
		properties.set(0, startPosX);
		properties.set(1, startPosY);
		properties.set(2, widht);
		properties.set(3, height);
	}
	
	public int getProp(int index) {
		return properties.get(index);
	}
	
	public void setPosX(int pos) {
		properties.set(0, pos);
	}
	
	public void setPosY(int pos) {
		properties.set(1, pos);
	}
	
	public Rectangle bounds() {
		return (new Rectangle(getProp(0),getProp(1),getProp(2),getProp(3)));
	}
	
}
