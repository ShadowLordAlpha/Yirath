package yirath.main;

import yirath.state.MainMenuState;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;

public class Yirath extends Game {

	@Override
	public void init() {
		
		

		Display.setTitle("Yirath - SilenceEngine v" + SilenceEngine.getVersion());
		Display.setSize(1280, 720);
		Display.centerOnScreen();
		
		Yirath.setGameState(new MainMenuState());
	}

	@Override
	public void resize() {

	}

	@Override
	public void update(float delta) {
		Display.setTitle("Yirath - SilenceEngine v" + SilenceEngine.getVersion() + " | FPS: " + Game.getFPS());
	}

	@Override
	public void dispose() {

	}
}
