package yirath.state;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.lwjgl.glfw.GLFW;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.glfw.Window;
import com.shc.silenceengine.core.glfw.callbacks.IKeyCallback;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.TrueTypeFont;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.geom2d.Rectangle;

public class MenuState extends GameState {
	
	private OrthoCam camera = new OrthoCam();
	private TrueTypeFont guiFont;
	private Texture menuBackground;
	private Rectangle rect;
	private IKeyCallback keyCallback = new IKeyCallback() {

		@Override
		public void invoke(Window window, int key, int scanCode, int action, int mods) {
			if(Display.getWindow() == window) {
				switch(menuID) {
				case 0:
					if(key == Keyboard.KEY_UP && (action == GLFW.GLFW_RELEASE || action == GLFW.GLFW_REPEAT)) {
						//System.out.println("UP");
						button--;
						if(button < 0) {
							button = 4;
						}
					}
					
					if(key == Keyboard.KEY_DOWN && (action == GLFW.GLFW_RELEASE || action == GLFW.GLFW_REPEAT)) {
						//System.out.println("DOWN");
						button++;
						if(button > 4) {
							button = 0;
						}
					}
					
					if(key == Keyboard.KEY_ENTER && action == GLFW.GLFW_RELEASE) {
						menuID = button;
						if(button == 0) {
							// TODO new game
						}
					}
					break;
				case 1:
					// TODO load primary game
					if(Keyboard.isAnyKeyPressed()) {
						menuID = 0;
					}
					break;
				case 2:
					// TODO menu
					if(Keyboard.isAnyKeyPressed()) {
						menuID = 0;
					}
					break;
				case 3:
					if(Keyboard.isAnyKeyPressed()) {
						menuID = 0;
					}
					break;
				case 4:
					Game.end();
				}
			}
		}
	};

	@Override
	public void onEnter() {
		// TODO Load images needed for first screen
		camera.initProjection(Display.getWidth(), Display.getHeight());
		guiFont = new TrueTypeFont("Times New Roman", TrueTypeFont.STYLE_NORMAL, 18);
		try {
			menuBackground = Texture.fromInputStream(new FileInputStream("src/main/resources/Sprite/menu_image.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		float size = Math.min(Display.getWidth(), Display.getHeight());
		rect = new Rectangle(size, size);
		rect.setPosition(Display.getWidth() - size, Display.getHeight() - size);
		Display.getWindow().setKeyCallback(keyCallback);
	}

	private float angle = 0;
	private int menuID = 0;
	private int button = 0;
	
	@Override
	public void update(float delta) {
		angle += -5.0f * delta;
		rect.rotate(-5.0f * delta);
	}

	@Override
	public void render(float delta, Batcher batcher) {
		// TODO
		camera.apply();
		
		Graphics2D g2d = Game.getGraphics2D();
		
		g2d.drawTexturedPolygon(menuBackground, rect);
		drawGui(batcher);
	}
	
	private void drawGui(Batcher batcher) {
		
		switch(menuID) {
		case 0:
			float alignBottom = Display.getHeight() - 100 - 4*guiFont.getHeight();
			guiFont.drawString(batcher, "NEW", 75, alignBottom + 0*guiFont.getHeight(), (button == 0) ? Color.WHITE : Color.GRAY);
			guiFont.drawString(batcher, "LOAD", 75, alignBottom + 1*guiFont.getHeight(), (button == 1) ? Color.WHITE : Color.GRAY);
			guiFont.drawString(batcher, "OPTIONS", 75, alignBottom + 2*guiFont.getHeight(), (button == 2) ? Color.WHITE : Color.GRAY);
			guiFont.drawString(batcher, "CREDITS", 75, alignBottom + 3*guiFont.getHeight(), (button == 3) ? Color.WHITE : Color.GRAY);
			guiFont.drawString(batcher, "EXIT", 75, alignBottom + 4*guiFont.getHeight(), (button == 4) ? Color.WHITE : Color.GRAY);
			break;
		case 1:
			guiFont.drawString(batcher, "WIP - NOT YET IN GAME!", 0, 0, Color.RED);
			break;
		case 2:
			guiFont.drawString(batcher, "WIP - NOT YET IN GAME!", 0, 0, Color.RED);
			break;
		case 3:
			guiFont.drawString(batcher, "WIP - NOT YET IN GAME!", 0, 0, Color.RED);
			break;
		case 4:
			Game.end();
		}
		
	}

	@Override
	public void resize() {
		camera.initProjection(Display.getWidth(), Display.getHeight());
		float size = Math.min(Display.getWidth(), Display.getHeight());
		rect.setWidth(size);
		rect.setHeight(size);
		rect.setPosition(Display.getWidth() - size, Display.getHeight() - size);
		rect.rotate(angle);
	}

	@Override
	public void onLeave() {
		Display.getWindow().setKeyCallback(null);
		guiFont.dispose();
	}
}
