package yirath.state;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.utils.Logger;

public class MainMenuState extends GameState {
	
	private Texture overlay;

	@Override
	public void onEnter() {
		overlay = Texture.fromResource("Data/Textures/menu_overlay.png");
	}

	@Override
	public void update(float delta) {
		if(Keyboard.isAnyKeyPressed()) {
			Logger.log("Starting Game from Placeholder screen");
			
			Game.setGameState(new PlayState());
		}
	}

	@Override
	public void render(float delta, Batcher batcher) {
		Game.getGraphics2D().drawTexture(overlay, 0, 0, Display.getWidth(), Display.getHeight());
	}

	@Override
	public void resize() {
		
	}

	@Override
	public void onLeave() {
		overlay.dispose();
	}
}
