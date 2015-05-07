package yirath.state;

import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;
import com.shc.silenceengine.models.Model;

public class PlayState extends GameState {

	private Model model;
	@Override
	public void onEnter() {
		//model = OBJModel;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
	}

	@Override
	public void render(float delta, Batcher batcher) {
		
	}

	@Override
	public void resize() {
		
	}

	@Override
	public void onLeave() {
		model.dispose();
	}

}
