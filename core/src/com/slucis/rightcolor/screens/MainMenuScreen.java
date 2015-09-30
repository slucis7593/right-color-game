package com.slucis.rightcolor.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.slucis.rightcolor.RightColorGame;
import com.slucis.rightcolor.game.Assets;
import com.slucis.rightcolor.utils.GlobalReference;
import com.slucis.rightcolor.utils.UIBuilder;

public class MainMenuScreen implements Screen {
	protected static final String TAG = MainMenuScreen.class.getName();

	// Objects
	RightColorGame game;

	private Stage stage;
	
	private Skin skinMenu;

	// Styles
	private LabelStyle labelStyle;
	private LabelStyle labelStyleSmall;

	// UI Actors
	private Image imgSplash;
	
	private Label lblGameTitle;
	private Label lblInfoAuthor;	
	
	private Button btnPlay;
	private Button btnSound;
	private Button btnAchievement;
	private Button btnRate;
	private Button btnMoreGames;

	// Tables
	private Table layerMenu;

	private Color backgroundColor = GlobalReference.backgroundColor;	

	public MainMenuScreen(RightColorGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(
				GlobalReference.VIEWPORT_GUI_WIDTH,
				GlobalReference.VIEWPORT_GUI_HEIGHT));

		// Set up input processor
		Gdx.input.setInputProcessor(stage);

		rebuildStage();
	}

	private void rebuildStage() {
		skinMenu = new Skin(
				Gdx.files.internal(GlobalReference.SKIN_RIGHT_COLOR_UI),
				new TextureAtlas(GlobalReference.TEXTURE_ATLAS_UI));
		skinMenu.getRegion("slucis-logo").getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);		

		labelStyle = new LabelStyle(Assets.instance.assetBradyFonts.normalSize,
				Color.WHITE);
		
		labelStyleSmall = new LabelStyle(Assets.instance.assetBradyFonts.smallSize,
				Color.WHITE);

		// Build all layers
		layerMenu = buildMenuLayer();

		// Assemble stage
		stage.clear();

		stage.addActor(layerMenu);

		// Draw final
		imgSplash = new Image(skinMenu, GlobalReference.SPLASH_IMAGE_NAME);
		imgSplash.setColor(backgroundColor);
		imgSplash.setFillParent(true);
		stage.addActor(imgSplash);

		// First action
		// You need to set image.visible = false to available click on Button
		// Because image layer is in upper of button layer
		// U can't click button if image layer is still visible
		imgSplash.addAction(sequence(alpha(0f, 0.5f), visible(false)));
	}

	private Table buildMenuLayer() {
		//final float padSpace = 30f;
		
		Table layer = new Table();
		layer.setFillParent(true);		

		// + Window Title Game
		lblGameTitle = new Label(" FOLLOW\nTHE RULE", labelStyle);		
		lblGameTitle.setColor(44f / 255f, 44f / 255f, 88f / 255f, 255f / 255f);		
		Table tblWindow = new Table();
		tblWindow.setBackground(skinMenu.getDrawable("message-window-small"));
		tblWindow.add(lblGameTitle).expand();
		layer.add(tblWindow).colspan(3).expand();
		
		// + New line
		layer.row();
		
		// Layer Menu
		Table layerMenu = new Table();		
		//layerMenu.debug();
		
		// + Button Sound
		btnSound = UIBuilder.buildImageButton(skinMenu, "style_02", "image-sound");
		btnSound.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.log(TAG, "Click Sound Button");			
			}
		});
		layerMenu.add(btnSound).padRight(30f);
		
		// + Button Play
		btnPlay = UIBuilder.buildImageButton(skinMenu, "style_01", "image-play");
		btnPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {				
				imgSplash.addAction(sequence(visible(true), delay(0.1f), alpha(1f, 0.5f),
						run(new Runnable() {
							@Override
							public void run() {
								game.setScreen(new GameScreen(game));
								Gdx.input.setCatchBackKey(false);
							}
						})));
				
			}
		});		
		
		layerMenu.add(btnPlay).padBottom(30f);
		
		// + Button Rate
		btnRate = UIBuilder.buildImageButton(skinMenu, "style_02", "image-rate");
		btnRate.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.log(TAG, "Click Rate Button");			
			}
		});
		layerMenu.add(btnRate).padLeft(30f);
		
		// + New line
		layerMenu.row();
		
		// + Button Achievement
		btnAchievement = UIBuilder.buildImageButton(skinMenu, "style_02", "image-achievement");
		btnAchievement.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.log(TAG, "Click Achievement Button");			
			}
		});
		layerMenu.add(btnAchievement).padRight(30f);
		
		// + Empty Cell
		layerMenu.add();
		
		// + Button More Games
		btnMoreGames = UIBuilder.buildImageButton(skinMenu, "style_02", "image-more-games");
		btnMoreGames.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.log(TAG, "Click More Games Button");			
			}
		});
		layerMenu.add(btnMoreGames).padLeft(30f);
		
		// 
		layer.add(layerMenu).expand();
		
		// + New line
		layer.row();
		
		// + Label author infomation
		lblInfoAuthor = new Label("VU TRUNG DUC 2015", labelStyleSmall);		
		layer.add(lblInfoAuthor).colspan(3);
		
		//layer.debug();

		return layer;
	}
	
	private void update(float delta) {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g,
				backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		stage.act(Math.min(delta, 1 / 60f));
		stage.draw();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		stage.dispose();
		skinMenu.dispose();
	}

	@Override
	public void dispose() {
		Assets.instance.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
}
