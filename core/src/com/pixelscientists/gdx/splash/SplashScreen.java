/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pixelscientists.gdx.LibgdxUtils;
import com.pixelscientists.gdx.inventory.InventoryScreen;

/**
 * This is the very first {@link Screen} which is shown when the game starts. It
 * loads the most common resources and displays a splash image for a couple of
 * seconds. The most common resources are the ones needed for the main menu,
 * lobby and options screen. The {@link LoadingScreen} will be responsible to
 * load all resources needed for the gaming screen.
 * 
 * @author Daniel Holderbaum
 */
public class SplashScreen implements Screen {

	private float minimumShowTime = 1.0f;

	private OrthographicCamera camera;

	private Viewport viewport;

	private Texture splash;

	@Override
	public void show() {
		// assets needed for this screen
		// Asset splashAsset = new Asset(AssetType.TEXTURE,
		// "textures/splash.png");
		LibgdxUtils.assets.load("textures/splash.png", Texture.class);
		LibgdxUtils.assets.finishLoading();
		splash = LibgdxUtils.assets.get("textures/splash.png", Texture.class);

		LibgdxUtils.assets.load("skins/uiskin.json", Skin.class);
		LibgdxUtils.assets.load("icons/icons.atlas", TextureAtlas.class);
		// LibgdxUtils.assets.load(splashAsset.name, splashAsset.type.clazz);
		// LibgdxUtils.assets.finishLoading();

		camera = new OrthographicCamera();
		viewport = new FitViewport(splash.getWidth(), splash.getHeight(), camera);
		// viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
		// true);

		// initiate loading of common resources
		// Set<Asset> neededAssets = new HashSet<Asset>();
		// neededAssets.add(splashAsset);
		// AssetOrganizer.organizeAssets(neededAssets, true);
	}

	@Override
	public void resume() {
		LibgdxUtils.assets.finishLoading();
	}

	@Override
	public void render(float deltaTime) {
		if (LibgdxUtils.assets.update() && minimumShowTime <= 0) {
			LibgdxUtils.game.setScreen(new InventoryScreen());
		}

		Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		LibgdxUtils.spriteBatch.setProjectionMatrix(camera.combined);
		LibgdxUtils.spriteBatch.begin();
		LibgdxUtils.spriteBatch.draw(splash, 0, 0);
		LibgdxUtils.spriteBatch.end();

		minimumShowTime -= deltaTime;
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
	}

}
