package dev.epchao.snakegame.states;

import java.awt.Graphics;

import dev.epchao.snakegame.Handler;
import dev.epchao.snakegame.worlds.World;

public class GameState extends State{

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "/worlds/game.txt");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
