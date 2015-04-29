package yirath.state;

import yirath.main.Yirath;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.TrueTypeFont;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.input.Keyboard;

public class CreditsState extends GameState {
	
	private OrthoCam camera = new OrthoCam();
	private TrueTypeFont creditHeaderFont;
	private TrueTypeFont creditFont;

	@Override
	public void onEnter() {
		camera.initProjection(Display.getWidth(), Display.getHeight());
		creditHeaderFont = new TrueTypeFont("Times New Roman", TrueTypeFont.STYLE_NORMAL, 24);
		creditFont = new TrueTypeFont("Times New Roman", TrueTypeFont.STYLE_NORMAL, 18);
	}

	@Override
	public void update(float delta) {
		if(Keyboard.isAnyKeyPressed()) {
			System.out.println("Exiting Credits");
			Yirath.setGameState(Yirath.menuState);
		}
	}

	@Override
	public void render(float delta, Batcher batcher) {
		camera.apply();
		creditHeaderFont.drawString(batcher, "CREDITS", 5, 0);
	}
	
	@Override
	public void resize() {
		camera.initProjection(Display.getWidth(), Display.getHeight());
	}

	@Override
	public void onLeave() {
		creditHeaderFont.dispose();
		creditFont.dispose();
	}

}
