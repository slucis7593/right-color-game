package com.slucis.rightcolor.utils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.touchable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.slucis.rightcolor.ui.AnimatedImage;

public class UIBuilder {
	protected static final String TAG = UIBuilder.class.getName();

	public static Button buildImageButton(Skin skin, String btnStyle, String drawableName) {
		Button btn = buildButton(skin, btnStyle);
		
		Image img = new Image(skin, drawableName);
		
		Table layerImgButton = new Table();
		layerImgButton.setFillParent(true);
		layerImgButton.add(img).expand();
		
		btn.addActor(layerImgButton);
		
		return btn;
	}
	
	public static Button buildAnimatedImageButton(Skin skin, String btnStyle, Animation animation) {
		Button btn = buildButton(skin, btnStyle);
		
		AnimatedImage animatedImage = new AnimatedImage(animation);
		
		Table layerImgButton = new Table();
		layerImgButton.setFillParent(true);
		layerImgButton.add(animatedImage).expand();
		
		btn.addActor(layerImgButton);
		
		return btn;
	}
	
	private static Button buildButton(Skin skin, String btnStyle) {
		Button btn = new Button(skin, btnStyle);
		btn.setTransform(true);
		btn.setOrigin(Align.center);
		
		btn.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				actor.addAction(buildButtonAction(0.3f, 0.05f));				
			}
		});
		
		return btn;
	}
	
//	((SequenceAction) actor.getActions().get(0))
//	.addAction(run(new Runnable() {
//		@Override
//		public void run() {
//			game.setScreen(new GameScreen(game));
//		}
//	}));
	public static SequenceAction buildButtonAction(float scale, float duration) {		
		return sequence(touchable(Touchable.disabled),
				scaleBy(-scale, -scale, duration),
				scaleBy(scale, scale, duration),
				touchable(Touchable.enabled));
	}
}
