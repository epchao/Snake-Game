package dev.epchao.snakegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int WIDTH = 32, HEIGHT = 32;
	
	public static Font font48;
	
	public static BufferedImage apple, ground, wall;
	
	public static BufferedImage[] btn_start;
	
	public static String chomp, death;
	
	public static void init() {
		font48 = FontLoader.loadFont("/fonts/slkscr.ttf", 48);
		
		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		chomp = "sounds/chomp.wav";
		death = "sounds/death.wav";
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(WIDTH * 2, 0, WIDTH * 2, HEIGHT);
		btn_start[1] = sheet.crop(WIDTH * 4, 0, WIDTH * 2, HEIGHT);
		
		apple = sheet.crop(0, 0, WIDTH, HEIGHT);
		
		ground = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		wall = sheet.crop(WIDTH * 6, 0, WIDTH, HEIGHT);
		
	}
	
}
