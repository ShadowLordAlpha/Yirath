package yirath.state;

import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Batcher;

public class LoadingState extends GameState implements Runnable {
	
	private boolean done = false;

	@Override
	public void onEnter() {
		
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(float delta, Batcher batcher) {
		
	}

	@Override
	public void onLeave() {
		
	}

	@Override
	public void run() {
		
		
		done = true;
	}
}
