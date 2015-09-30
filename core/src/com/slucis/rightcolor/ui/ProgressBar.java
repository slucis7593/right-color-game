/*
 * Example:
 		imgProgressBar = new ProgressBarImage(
				skinMenu.getDrawable("white_rectangle"), 2f);
		imgProgressBar.addEndProgressListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				imgProgressBar.start();
				return true;
			}
		});
		layer.add(imgProgressBar).width(100f).height(300f);
		imgProgressBar.setBarWidth(100f);
		imgProgressBar.setBarHeight(300f);
		imgProgressBar.start();
 */
package com.slucis.rightcolor.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ProgressBar extends Image {
	private float duration = 1f;
	private float value = 0f;

	private boolean running = false;

	private float barWidth = 0f;
	private float barHeight = 0f;

	private EventListener endProgress;

	public ProgressBar(Drawable drawable) {
		super(drawable);
	}

	public ProgressBar(Drawable drawable, float duration) {
		this(drawable);

		this.duration = duration;
		this.value = duration;
	}

	public void updateColorBar() {
		if (value >= 0.5f * duration) {
			setColor(Color.GREEN);
		} else if (value >= 0.2f * duration) {
			setColor(Color.ORANGE);
		} else {
			setColor(Color.RED);
		}
	}

	@Override
	public void act(float delta) {
		if (running) {
			updateColorBar();
			value -= delta;

			if (value > 0) {
				setSize(barWidth, barHeight * value / duration);
			} else {
				value = 0;
				setSize(barWidth, barHeight * value / duration);
				running = false;
				if (endProgress != null)
					endProgress.handle(null);
			}
		}
	}

	public void start() {
		running = true;
		value = duration;
	}

	public void stop() {
		running = false;
		value = 0f;
		setSize(getImageWidth(), getImageHeight() * value / duration);
	}

	public void addEndProgressListener(EventListener event) {
		this.endProgress = event;
	}

	public float getValue() {
		return value;
	}

	public void setBarWidth(float barWidth) {
		this.barWidth = barWidth;
	}

	public void setBarHeight(float barHeight) {
		this.barHeight = barHeight;
	}
}
