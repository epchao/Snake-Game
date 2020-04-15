package dev.epchao.snakegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.epchao.snakegame.audio.SoundManager;
import dev.epchao.snakegame.display.Display;
import dev.epchao.snakegame.gfx.Assets;
import dev.epchao.snakegame.input.KeyManager;
import dev.epchao.snakegame.input.MouseManager;
import dev.epchao.snakegame.states.GameState;
import dev.epchao.snakegame.states.MainMenuState;
import dev.epchao.snakegame.states.State;

public class Game implements Runnable{

	private Display display;
	private int width, height;
	private String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;

	public State mainMenuState;
	public State gameState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private SoundManager soundManager;
	
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		soundManager = new SoundManager();
	}
	
	public void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		soundManager.setFile(Assets.chomp);
		
		handler = new Handler(this);
		
		mainMenuState = new MainMenuState(handler);
		gameState = new GameState(handler);
		State.setState(mainMenuState);
		
		handler.getWorld().getEntityManager().getSnake().setDead(false);
		
		keyManager.resetDirections();
	}
	
	public void tick() {
		keyManager.tick();
		
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	public void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		if(State.getState() != null) {
			State.getState().render(g);
		}
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		//game loop
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		@SuppressWarnings("unused")
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}

	public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
}
