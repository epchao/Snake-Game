package dev.epchao.snakegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.epchao.snakegame.Handler;
import dev.epchao.snakegame.gfx.Assets;

public class Item {

	//HANDLER
	public static Item[] items = new Item[256];
	public static Item apple = new Item(Assets.apple, "Apple", 0);
	
	//CLASS
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x, y;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		
		items[id] = this;
	}
	
	public void tick() {
		if(handler.getWorld().getEntityManager().getSnake().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			playChomp();
			increaseSize();
		}
	}
	
	public void render(Graphics g) {
		if(handler == null)
			return;
		render(g, (int)x, (int)y);
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	//TESTING & DEBUGGING
	
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	public Item createNewRandom() {
		int maxX = 530;
		int maxY = 405;
		int min = 100;
		int rangeX = (maxX - min) + 1;
		int rangeY = (maxY - min) + 1;
		
		Item i = new Item(texture, name, id);
		i.setPosition((int)(Math.random() * rangeX) + min,(int)(Math.random() * rangeY) + min);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	public void increaseSize() {
		int size = handler.getWorld().getEntityManager().getSnake().getSize();
		handler.getWorld().getEntityManager().getSnake().setSize(size + 5);
	}
	
	public void playChomp() {
		handler.getGame().getSoundManager().tick(Assets.chomp);
	}

	//GETTERS AND SETTERS
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

}