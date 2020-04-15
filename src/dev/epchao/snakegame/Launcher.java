package dev.epchao.snakegame;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Snake Game", 640, 512);
		game.start();
	}
}
