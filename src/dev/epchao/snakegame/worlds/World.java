package dev.epchao.snakegame.worlds;

import java.awt.Color;
import java.awt.Graphics;

import dev.epchao.snakegame.Handler;
import dev.epchao.snakegame.entities.EntityManager;
import dev.epchao.snakegame.entities.creatures.Snake;
import dev.epchao.snakegame.gfx.Assets;
import dev.epchao.snakegame.gfx.Text;
import dev.epchao.snakegame.items.Item;
import dev.epchao.snakegame.items.ItemManager;
import dev.epchao.snakegame.tiles.Tile;
import dev.epchao.snakegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	private EntityManager entityManager;
	private ItemManager itemManager; 
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Snake(handler, 100, 200));
		itemManager = new ItemManager(handler);
		itemManager.addItem(Item.apple.createNew(400, 200));
		
		loadWorld(path);
		
		entityManager.getSnake().setX(spawnX);
		entityManager.getSnake().setY(spawnY);
	}

	public void tick() {
		entityManager.tick();
		itemManager.tick();
		if(itemManager.getItems().isEmpty()) {
			itemManager.addItem(Item.apple.createNewRandom());
		}
	}
	
	public void render(Graphics g) {
		String scoreCount = String.valueOf(itemManager.getScore());
		
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
			entityManager.render(g);
			itemManager.render(g);
			Text.drawString(g, "Score: " + scoreCount, 300, 30, true, Color.WHITE, Assets.font48);
		}
	}

	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.groundTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.groundTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x ++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ItemManager getItemManager() {
		return itemManager;
	}


	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
