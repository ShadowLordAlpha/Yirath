package yirath.state;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.TrueTypeFont;
import com.shc.silenceengine.graphics.cameras.OrthoCam;

public class MenuState extends GameState {
	
	private OrthoCam camera = new OrthoCam();
	private TrueTypeFont guiFont;

	@Override
	public void onEnter() {
		// TODO Load images needed for first screen
		camera.initProjection(Display.getWidth(), Display.getHeight());
		guiFont = new TrueTypeFont("Times New Roman", TrueTypeFont.STYLE_NORMAL, 18);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(float delta, Batcher batcher) {
		// TODO 
		camera.apply();
		
		float alignBottom = Display.getHeight() - 75 - 5*guiFont.getHeight();
		
		guiFont.drawString(batcher, "CONTINUE", 50, alignBottom + 0*guiFont.getHeight(), Color.WHITE);
		guiFont.drawString(batcher, "OPTIONS", 50, alignBottom + 1*guiFont.getHeight(), Color.GRAY);
		guiFont.drawString(batcher, "NEW", 50, alignBottom + 2*guiFont.getHeight(), Color.GRAY);
		guiFont.drawString(batcher, "LOAD", 50, alignBottom + 3*guiFont.getHeight(), Color.GRAY);
		guiFont.drawString(batcher, "CREDITS", 50, alignBottom + 4*guiFont.getHeight(), Color.GRAY);
		guiFont.drawString(batcher, "EXIT", 50, alignBottom + 5*guiFont.getHeight(), Color.GRAY);
		
	}

	@Override
	public void resize() {
		camera.initProjection(Display.getWidth(), Display.getHeight());
	}

	@Override
	public void onLeave() {
		guiFont.dispose();
	}
}
