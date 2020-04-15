package dev.epchao.snakegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.epchao.snakegame.Handler;
import dev.epchao.snakegame.gfx.Assets;

public class Snake extends Creature{
	
	private Snake b;
	private ArrayList<Snake> snake;
	
	private int size = 35;
	
	private boolean dead = false;
	
	public Snake(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
				
		snake = new ArrayList<Snake>();
		
		bounds.x = 0;
		bounds.y = 4;
		bounds.width = 32;
		bounds.height = 26;
	}

	public void tick() {
		if(snake.size() == 0) {
			b = new Snake(handler, x, y);
			snake.add(b);
		}
		
		getInput();
		move();
		
		b = new Snake(handler, x, y);
		snake.add(b);
		
		if(snake.size() > size) {
			snake.remove(0);
			hitSelf();
		}
	}
	
	public void bodyRender(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).bodyRender(g);
		}
	}
	
	public void hitSelf() {
		for(int i = 5; i < snake.size(); i++) {
			if(snake.get(0).x == handler.getWorld().getSpawnX() && snake.get(0).y == handler.getWorld().getSpawnY() &&
			   snake.get(i).x == handler.getWorld().getSpawnX() && snake.get(i).y == handler.getWorld().getSpawnY()) {
				break;
			}
			if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
				die();
				break;
			}
		}
	}
	
	public void die() {
		dead = true;
		handler.getGame().getSoundManager().setFile(Assets.death);
		handler.getGame().getSoundManager().tick(Assets.death);
		handler.getGame().init();
	}
	
	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().upDirection) {
			xMove = idle;
			yMove = -speed;
		}
		
		if(handler.getKeyManager().downDirection) {
			xMove = idle;
			yMove = speed;
		}
		
		if(handler.getKeyManager().leftDirection) {
			yMove = idle; 
			xMove = -speed;
		}
		
		if(handler.getKeyManager().rightDirection) {
			yMove = idle;
			xMove = speed;
		}
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
