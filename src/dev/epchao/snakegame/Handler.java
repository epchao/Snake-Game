package dev.epchao.snakegame;

import dev.epchao.snakegame.audio.SoundManager;
import dev.epchao.snakegame.input.KeyManager;
import dev.epchao.snakegame.input.MouseManager;
import dev.epchao.snakegame.worlds.World;

public class Handler {

	private Game game;
	private World world;

	public Handler(Game game) {
		this.game = game;
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public SoundManager getSoundManager() {
		return game.getSoundManager();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
}
