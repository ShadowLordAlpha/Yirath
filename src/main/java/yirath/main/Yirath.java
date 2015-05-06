package yirath.main;

import yirath.state.MenuState;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.utils.Logger;

public class Yirath extends Game {
	
	public static MenuState menuState = new MenuState();

	@Override
	public void init() {
		
		Display.setTitle("Yirath - SilenceEngine v" + SilenceEngine.getVersion());
		// Display.setIcon();
		Display.setSize(1280, 720);
		Display.centerOnScreen();
		Display.setVSync(false);
		Yirath.setGameState(menuState);
	}

	@Override
	public void resize() {
		// TODO 
		Logger.log("Display Resized");
	}

	@Override
	public void update(float delta) {
		Display.setTitle("Yirath - SilenceEngine v" + SilenceEngine.getVersion() + " FPS: " + Game.getFPS() + " | UPS: " + Game.getUPS() + " | RC: " + SilenceEngine.graphics.renderCallsPerFrame);
	}

	@Override
	public void render(float delta, Batcher batcher) {
		// TODO 
		
	}

	@Override
	public void dispose() {
		// TODO 
		Logger.log("Cleanup");
	}

}
