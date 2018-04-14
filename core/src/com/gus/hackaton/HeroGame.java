package com.gus.hackaton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HeroGame extends ApplicationAdapter {

    public static final float WIDTH = 100;

    public static final float HEIGHT = 100;

    private ShapeRenderer shapeRenderer;

    private CameraInputController cameraInputController;

    // models:
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance boxInstance;
    private ModelInstance carrotInstance;

    private SpriteBatch batch;

	private Texture img;

    private PerspectiveCamera perspectiveCamera;

    private Environment environment;

    @Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

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

        // MODELS:
        modelBatch = new ModelBatch();
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal); // Position, Color, Normal and TextureCoordinates is supported
        boxInstance = new ModelInstance(model);

        ModelLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("marchew.obj"));
        carrotInstance = new ModelInstance(model);

        AnimationController animationController = new AnimationController(carrotInstance);
        animationController.setAnimation()


        // MOVING THE CAMERA:
        cameraInputController = new CameraInputController(perspectiveCamera);
        Gdx.input.setInputProcessor(cameraInputController);

	}

	@Override
	public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraInputController.update();

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

        modelBatch.begin(perspectiveCamera);
        //modelBatch.render(boxInstance, environment);
        modelBatch.render(carrotInstance, environment);
        modelBatch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();

        modelBatch.dispose();
        model.dispose();
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
