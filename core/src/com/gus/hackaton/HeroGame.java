package com.gus.hackaton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HeroGame extends ApplicationAdapter {

    public static final float WIDTH = 100;

    public static final float HEIGHT = 100;


    private ShapeRenderer shapeRenderer;


    private SpriteBatch batch;
	private Texture img;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void pause() {
		// android callback
		super.pause();
	}

	@Override
	public void resume() {
		// android callback
		super.resume();
	}
}
