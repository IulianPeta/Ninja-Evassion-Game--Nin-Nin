package com.tutorail.main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MenuNinja extends GameObject{
	
	Handler handler;
	Random r = new Random();
	
	private BufferedImage ninja_image;

	public MenuNinja(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 5;
		velY = 5;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		ninja_image = ss.grabImage(1, 1, 32, 32);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT -48) velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH -32) velX *= -1;
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(ninja_image, (int)x, (int)y , null);
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, 32, 32);
	}
	
}
