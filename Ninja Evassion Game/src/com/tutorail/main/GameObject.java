package com.tutorail.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y; // it can only be access by which  object inharence the object
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); // rectangle = collision 
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
}
