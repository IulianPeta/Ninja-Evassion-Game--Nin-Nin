package com.tutorail.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorail.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mousex = e.getX();
		int mousey = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			
			//Play Button
			if(mouseOver(mousex, mousey, 210, 150, 200, 64)){
				Game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
				
			}
			
			//Help Button
			if(mouseOver(mousex, mousey, 210, 250, 200, 64)){
				Game.gameState = STATE.Help;
			}
		}
		
		//Back Button
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mousex, mousey, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				return;
			}
		}
		//Try Again Button
		if(Game.gameState == STATE.End) {
				if(mouseOver(mousex, mousey, 210, 350, 200, 64)) {
					Game.gameState = STATE.Menu;
					hud.setLevel(1);
					hud.setScore(0);
					return;
				}
		}
		
		//Play Again Button
		if(Game.gameState == STATE.Won) {
			if(mouseOver(mousex, mousey, 210, 350, 200, 64)) {
					Game.gameState = STATE.Menu;
					hud.setLevel(1);
					hud.setScore(0);
					return;
			}
		}
	}
		
	
	
	public void mouseRelease() {
		
	}
	
	private boolean mouseOver(int mousex, int mousey, int x, int y, int width, int height) {
		if(mousex > x && mousex < x + width){
			if(mousey > y && mousey < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
		
	}

	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 26);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Evasion Ninja Training - also known as Nin-Nin", 34, 80);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 280, 290);
		}else if (Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 26);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 280, 80);
			
			g.setFont(fnt3);
			g.drawString("Use the Arrow keys to move the ninja and dodge.", 80, 160);
			g.drawString("Have fun!", 80, 240);
			g.drawString("Press the key P when you want to pause.", 80, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}else if (Game.gameState == STATE.End) {
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Game Over", 230, 80);
			
			g.setFont(fnt3);
			g.drawString("You died with a score of: " + hud.getScore() + ".", 180, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try again", 245, 390);
		}else if (Game.gameState == STATE.Won) {
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Congratulations!", 210, 80);
			
			g.setFont(fnt3);
			g.drawString("You have completed the game with the score of: " + hud.getScore() + ".", 60, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Play again", 245, 390);
		}
	}			
	
}
