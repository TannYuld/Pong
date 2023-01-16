package main;

import java.awt.Rectangle;

public enum Collisions {
	BOTTOM(new Rectangle(0,GameScreen.WIN_HEIGHT-40,GameScreen.WIN_WIDHT,5)),
	TOP(new Rectangle(0,0,GameScreen.WIN_WIDHT,5)),LEFT(new Rectangle(0,0,2,GameScreen.WIN_HEIGHT)),
	RIGHT(new Rectangle(GameScreen.WIN_WIDHT-17,0,2,GameScreen.WIN_HEIGHT));
	
	private Rectangle rect;
	
	Collisions(Rectangle rect){
		this.rect = rect;
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
}
