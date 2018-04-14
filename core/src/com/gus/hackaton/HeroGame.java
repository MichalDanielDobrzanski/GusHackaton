package com.gus.hackaton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class HeroGame extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;

    private CameraInputController cameraInputController;

    // models:
    private ModelBatch modelBatch;
    private Array<ModelInstance> modelInstances = new Array<ModelInstance>();

    private SpriteBatch spriteBatch;

	private Texture texture;

    private PerspectiveCamera perspectiveCamera;

    private Environment environment;

    private AssetManager assetManager;
    private boolean loading;

    @Override
	public void create() {
		spriteBatch = new SpriteBatch();
		texture = new Texture("badlogic.jpg");

        // LIGHTING, DIRECTIONAL LIGHT https://i.stack.imgur.com/3udUJ.gif
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f)); // color of a light
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        // CAMERA:
		perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		perspectiveCamera.position.set(10, 10, 10);
        perspectiveCamera.lookAt(0,0,0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();

        modelBatch = new ModelBatch();

        // MOVING THE CAMERA:
        cameraInputController = new CameraInputController(perspectiveCamera);
        Gdx.input.setInputProcessor(cameraInputController);

        // CARROT:
        assetManager = new AssetManager();
        assetManager.load("marchew.g3db", Model.class);
        loading = true;

	}

	@Override
	public void render() {
        if (loading && assetManager.update())
            doneLoading();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraInputController.update();

		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0);
		spriteBatch.end();

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(modelInstances, environment);
        modelBatch.end();
	}

    private void doneLoading() {
        Model carrotModel = assetManager.get("marchew.g3db", Model.class);
        modelInstances.add(new ModelInstance(carrotModel));
        loading = false;
    }

    @Override
	public void dispose() {
		spriteBatch.dispose();
        modelBatch.dispose();

        texture.dispose();

        modelInstances.clear();
        assetManager.dispose();
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
