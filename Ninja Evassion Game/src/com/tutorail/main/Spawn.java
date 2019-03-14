package com.tutorail.main;

import java.util.Random;

public class Spawn {
	
	private Menu menu;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud, Menu menu){
		this.handler = handler;
		this.hud = hud;
		this.menu = menu;

	}
	
	public void tick() {
		
		
		scoreKeep++;
		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel((int)hud.getLevel() + 1);
			
			if(hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 5) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy, handler));
			}else if(hud.getLevel() == 10) {
				handler.clearEnemys();
				handler.addObject(new BossEnemy((Game.WIDTH/2)- 48, -170, ID.BossEnemy, handler));
			}else if(hud.getLevel() == 14) {
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 15) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 16) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 17) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 19) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy, handler));
			}else if(hud.getLevel() == 22) {
				handler.clearEnemys();
				handler.addObject(new BossEnemy((Game.WIDTH/2)- 48, -190, ID.BossEnemy, handler));
			}else if(hud.getLevel() == 26) {
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));	
			}else if(hud.getLevel() == 27) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 28) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 29) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 30) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 32) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.SmartEnemy, handler));
			}
				
		}
	}
}



