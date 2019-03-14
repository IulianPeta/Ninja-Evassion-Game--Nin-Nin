package com.tutorail.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import com.tutorail.main.Game.STATE;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -7195308134928222389L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End,
		Won
	}
	
	public static STATE gameState = STATE.Menu;	
	
	public static BufferedImage sprite_sheet = null;

	
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			sprite_sheet = loader.loadImage("/NinjaRed.png");
			System.out.println("loaded");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		AudioPlayer.init();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Evasion Ninja Training", this);
		
		spawner = new Spawn(handler, hud, menu);
		r = new Random();
		
	
		if(gameState == STATE.Game) {			
			handler.addObject(new Player(WIDTH/2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.clearEnemys();
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
		}else{
			for(int i = 0; i < 2; i++) {
				handler.addObject(new MenuNinja(WIDTH/2 - 32, HEIGHT / 2 - 32, ID.MenuNinja, handler));
				
			}
		}
		
		
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;	
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(gameState == STATE.Game) {
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemys();
					for(int i = 0; i < 2; i++) {
						handler.addObject(new MenuNinja(WIDTH/2 - 32, HEIGHT / 2 - 32, ID.MenuNinja, handler));
					}
				}
				
				if(hud.getLevel() == 35) {
					HUD.HEALTH = 100;
					gameState = STATE.Won;
					handler.clearEnemys();
					for(int i = 0; i < 2; i++) {
						handler.addObject(new MenuNinja(WIDTH/2 - 32, HEIGHT / 2 - 32, ID.MenuNinja, handler));
					}
				}
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End|| gameState == STATE.Help || gameState == STATE.Won) {
			menu.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect((int)0, (int)0, WIDTH, HEIGHT);

		
		handler.render(g);
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 300, 100);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Won) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
				
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String atgs[]) {
		new Game();
		
	}

}
