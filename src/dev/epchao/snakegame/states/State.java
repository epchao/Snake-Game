package dev.epchao.snakegame.states;

import java.awt.Graphics;

import dev.epchao.snakegame.Handler;

public abstract class State {

	private static State currentState = null;
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public static State getState() {
		return currentState;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
}
