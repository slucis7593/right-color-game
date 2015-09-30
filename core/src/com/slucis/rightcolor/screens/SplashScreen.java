package com.slucis.rightcolor.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.slucis.rightcolor.RightColorGame;
import com.slucis.rightcolor.game.Assets;
import com.slucis.rightcolor.utils.GlobalReference;

public class SplashScreen implements Screen {
	private static final String TAG = SplashScreen.class.getName();

	RightColorGame game;

	private Stage stage;
	private Skin skinSplashScreen;

	private Image imgLogo;
	private Image imgSplash;

	private LabelStyle labelStyle;
	private Label labelLoading;

	private Table layerLogo;

	private boolean isFinish;

	public SplashScreen(RightColorGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(
				GlobalReference.VIEWPORT_GUI_WIDTH,
				GlobalReference.VIEWPORT_GUI_HEIGHT));

		// Add asset to manager to update manager
		if (Assets.instance != null)
			Assets.instance.addAssets();

		//
		isFinish = false;

		// Rebuild Stage
		rebuildStage();
	}

	private void rebuildStage() {
		skinSplashScreen = new Skin(
				Gdx.files.internal(GlobalReference.SKIN_RIGHT_COLOR_UI),
				new TextureAtlas(GlobalReference.TEXTURE_ATLAS_UI));
		skinSplashScreen.getRegion("slucis-logo").getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		labelStyle = new LabelStyle(Assets.instance.assetBradyFonts.smallSize,
				Color.BLACK);

		// Assemble stage
		stage.clear();

		// ++ Logo layer
		layerLogo = buildLogoLayer();
		stage.addActor(layerLogo);

		// ++ Layer Splash Image
		imgSplash = new Image(skinSplashScreen, GlobalReference.SPLASH_IMAGE_NAME);
		imgSplash.setColor(Color.WHITE);
		imgSplash.setFillParent(true);

		stage.addActor(imgSplash);

		// ++ First actions
		imgSplash.addAction(alpha(0f, 0.5f));
	}

	private Table buildLogoLayer() {
		Table layer = new Table();
		layer.setFillParent(true);

		imgLogo = new Image(skinSplashScreen, "slucis-logo");
		layer.add(imgLogo);

		labelLoading = new Label("LOADING...", labelStyle);
		layer.row();
		layer.add(labelLoading).padTop(20f);

		// layer.debug();

		return layer;
	}

	private void update(float delta) {
		if (Assets.instance.assetManager.update()) {
			if (!isFinish) {
				imgSplash.addAction(sequence(delay(2f), alpha(1f, 0.5f),
						run(new Runnable() {
							@Override
							public void run() {
								game.setScreen(new MainMenuScreen(game));
								Gdx.input.setCatchBackKey(false);
							}
						})));
				isFinish = true;
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		stage.dispose();
		skinSplashScreen.dispose();
	}

	@Override
	public void dispose() {

		Gdx.app.log(TAG, "Dispose");

		Assets.instance.dispose();
	}
}
