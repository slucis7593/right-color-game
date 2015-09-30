package com.slucis.rightcolor.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.slucis.rightcolor.utils.GlobalReference;

public class WorldRenderer {

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public WorldController worldController;

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}

	private void init() {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(GlobalReference.VIEWPORT_WIDTH,
				GlobalReference.VIEWPORT_HEIGHT);
		camera.update();		
	}

	public void render() {
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		worldController.level.render(batch);
		batch.end();
	}
	
	public void resize(int width, int height) {
		camera.viewportWidth = (GlobalReference.VIEWPORT_HEIGHT / (float) height)
				* (float) width;
		camera.update();
	}

	public void dispose() {
		batch.dispose();		
	}
}
