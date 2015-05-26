package yirath.state;

import org.lwjgl.opengl.GL11;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.cameras.PerspCam;
import com.shc.silenceengine.graphics.opengl.GL3Context;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.Vector3;
import com.shc.silenceengine.math.geom3d.Cuboid;
import com.shc.silenceengine.models.Model;
import com.shc.silenceengine.scene.Scene;
import com.shc.silenceengine.scene.entity.ModelEntity;
import com.shc.silenceengine.scene.lights.DirectionalLight;
import com.shc.silenceengine.scene.lights.PointLight;

public class PlayState extends GameState {

	private PerspCam cam;
	private Model model;
	private Scene scene;
	private ModelEntity entity;
	private PointLight cameraLight;

	@Override
	public void onEnter() {
		cam = new PerspCam().initProjection(90, Display.getAspectRatio(), 0.01f, 1000f);
		cam.setPosition(new Vector3(0, 0, 0));
		
		GL3Context.enable(GL11.GL_CULL_FACE);
        GL3Context.cullFace(GL11.GL_BACK);
        
		//cam.rotateX(90);
		ResourceLoader loader = ResourceLoader.getInstance();
		int id = loader.defineModel("Data/Terrain/terrain0.obj");
		
		loader.startLoading();
		
		SilenceEngine.graphics.setClearColor(Color.DARK_SLATE_GRAY);
		
		model = loader.getModel(id);
		
		scene = new Scene();
		{
			// Add the ModelEntity to the scene
			scene.addChild(entity = new ModelEntity(model, new Cuboid(Vector3.ZERO, 1, 1, 1)));
			entity.rotate(90, 0, 0);
			// Add some lights, so that we can see the model
			scene.addComponent(new PointLight(new Vector3(1, 800, 80), Color.WHITE));
			scene.addComponent(new DirectionalLight(new Vector3(0, 1, 0), Color.WHITE, 3));
			scene.addComponent(cameraLight = new PointLight(cam.getPosition(), Color.WHITE, 1, 100));
		}
		scene.init();
		
		//entity.scale(1/512);
	}

	@Override
	public void update(float delta) {
		if (Keyboard.isPressed(Keyboard.KEY_ESCAPE))
			Game.end();

		scene.update(delta);
		
		float move = delta * 2;//512;

		if (Keyboard.isPressed(Keyboard.KEY_W))
			cam.moveForward(move);

		if (Keyboard.isPressed(Keyboard.KEY_S))
			cam.moveBackward(move);

		if (Keyboard.isPressed(Keyboard.KEY_A))
			cam.moveLeft(move);

		if (Keyboard.isPressed(Keyboard.KEY_D))
			cam.moveRight(move);

		if (Keyboard.isPressed(Keyboard.KEY_Q))
			cam.moveUp(move);

		if (Keyboard.isPressed(Keyboard.KEY_E))
			cam.moveDown(move);

		if (Keyboard.isPressed(Keyboard.KEY_UP))
			cam.rotateX(1);

		if (Keyboard.isPressed(Keyboard.KEY_DOWN))
			cam.rotateX(-1);

		if (Keyboard.isPressed(Keyboard.KEY_LEFT))
			cam.rotateY(1);

		if (Keyboard.isPressed(Keyboard.KEY_RIGHT))
			cam.rotateY(-1);
		//System.out.println(cam.getPosition());
		cameraLight.setPosition(cam.getPosition());
	}

	@Override
	public void render(float delta, Batcher batcher) {
		cam.apply();
		scene.render(delta, batcher);

	}

	@Override
	public void resize() {
		cam.initProjection(90, Display.getAspectRatio(), 0.01f, 1000f);
	}

	@Override
	public void onLeave() {
		cam = null;
		model.dispose();
	}

}
