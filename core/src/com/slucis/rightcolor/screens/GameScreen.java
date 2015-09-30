package com.slucis.rightcolor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.slucis.rightcolor.RightColorGame;
import com.slucis.rightcolor.game.Assets;
import com.slucis.rightcolor.game.WorldController;
import com.slucis.rightcolor.game.WorldRenderer;
import com.slucis.rightcolor.utils.GlobalReference;

public class GameScreen implements Screen {
	//private static final String TAG = MainMenuScreen.class.getName();

	private RightColorGame game;

	public WorldController worldController;
	public WorldRenderer worldRenderer;
	//public UIManager uiManager;

	private Color bgColor;

	public GameScreen(RightColorGame game) {
		this.game = game;
		
		if (GlobalReference.backgroundColor != null)
			bgColor = GlobalReference.backgroundColor;
	}

	@Override
	public void show() {
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);		

		// Set input processor
		InputMultiplexer multiInput = new InputMultiplexer(worldController,
				worldController.uiManager.getInputProcessor());
		Gdx.input.setInputProcessor(multiInput);

		// Catch back key press
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		// Updates		
		worldController.update(delta);
		
		// Render
		Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRenderer.render();
		worldController.uiManager.render();
	}

	@Override
	public void resume() {
		worldController.resume();
		worldController.uiManager.resume();
	}

	@Override
	public void hide() {
		worldController.dispose();
		worldRenderer.dispose();		

		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void dispose() {
		Assets.instance.dispose();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
		worldController.uiManager.resize(width, height);
	}

	@Override
	public void pause() {
		worldController.pause();
		worldController.uiManager.pause();
	}
}
