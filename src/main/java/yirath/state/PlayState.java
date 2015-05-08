package yirath.state;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.shc.silenceengine.core.Display;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.Graphics2D;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.cameras.PerspCam;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.Transform;
import com.shc.silenceengine.math.Vector3;
import com.shc.silenceengine.math.geom3d.Cuboid;
import com.shc.silenceengine.models.Model;
import com.shc.silenceengine.scene.Scene;
import com.shc.silenceengine.scene.entity.ModelEntity;
import com.shc.silenceengine.scene.lights.DirectionalLight;
import com.shc.silenceengine.scene.lights.PointLight;

public class PlayState extends GameState {

	// private OrthoCam hudCamera;
	private PerspCam gameCam;
	private Texture hudHealth;
	private Model tempModel;
	private Scene scene;
	private ModelEntity entity;
	private PointLight cameraLight;

	@Override
	public void onEnter() {
		gameCam = new PerspCam().initProjection(70, Display.getAspectRatio(),
				0.01f, 100f);
		gameCam.setPosition(new Vector3(0, 0, 2.5f));

		tempModel = Model.load("models/CharacterTest.obj");

		scene = new Scene();
		{
			// Add the ModelEntity to the scene
			scene.addChild(entity = new ModelEntity(tempModel, new Cuboid(Vector3.ZERO, 1, 1, 1)));

			// Add some lights, so that we can see the model
			scene.addComponent(new PointLight(new Vector3(-1, -1, 1), Color.BLUE));
			scene.addComponent(new PointLight(new Vector3(+1, -1, 1), Color.RED));
			scene.addComponent(new PointLight(new Vector3(+1, +1, 1), Color.GREEN));
			scene.addComponent(new DirectionalLight(new Vector3(1, 0, 0), Color.AZURE));
			scene.addComponent(cameraLight = new PointLight(gameCam.getPosition(), Color.DARK_KHAKI));
		}
		scene.init();
	}

	@Override
	public void resize() {
		gameCam.initProjection(70, Display.getAspectRatio(), 0.01f, 100f);
	}

	@Override
	public void update(float delta) {
		if (Keyboard.isClicked(Keyboard.KEY_ESCAPE))
			Game.end();

		if (Keyboard.isPressed(Keyboard.KEY_W))
			gameCam.moveForward(delta);

		if (Keyboard.isPressed(Keyboard.KEY_S))
			gameCam.moveBackward(delta);

		if (Keyboard.isPressed(Keyboard.KEY_A))
			gameCam.moveLeft(delta);

		if (Keyboard.isPressed(Keyboard.KEY_D))
			gameCam.moveRight(delta);

		if (Keyboard.isPressed(Keyboard.KEY_Q))
			gameCam.moveUp(delta);

		if (Keyboard.isPressed(Keyboard.KEY_E))
			gameCam.moveDown(delta);

		if (Keyboard.isPressed(Keyboard.KEY_UP))
			gameCam.rotateX(1);

		if (Keyboard.isPressed(Keyboard.KEY_DOWN))
			gameCam.rotateX(-1);

		if (Keyboard.isPressed(Keyboard.KEY_LEFT))
			gameCam.rotateY(1);

		if (Keyboard.isPressed(Keyboard.KEY_RIGHT))
			gameCam.rotateY(-1);

		entity.rotate(90 * delta, 90 * delta, 90 * delta);
		cameraLight.setPosition(gameCam.getPosition());

		scene.update(delta);
	}

	@Override
	public void render(float delta, Batcher batcher) {
		gameCam.apply();
		scene.render(delta, batcher);
	}

	@Override
	public void onLeave() {
		tempModel.dispose();
	}

}
