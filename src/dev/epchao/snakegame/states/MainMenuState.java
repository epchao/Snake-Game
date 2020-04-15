package dev.epchao.snakegame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.epchao.snakegame.Handler;
import dev.epchao.snakegame.gfx.Assets;
import dev.epchao.snakegame.gfx.Text;
import dev.epchao.snakegame.ui.ClickListener;
import dev.epchao.snakegame.ui.UIImageButton;
import dev.epchao.snakegame.ui.UIManager;

public class MainMenuState extends State{
	
	private UIManager uiManager;
	
	public MainMenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(240, 230, 128, 64, Assets.btn_start, new ClickListener() {

			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
	}
	
	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		Text.drawString(g, "SNAKE GAME", 300, 150, true, Color.BLACK, Assets.font48);
		uiManager.render(g);
	}


}
