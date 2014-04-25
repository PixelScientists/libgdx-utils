/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx;

import java.util.Random;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Logger;
import com.pixelscientists.gdx.splash.SplashScreen;

/**
 * This is the main class of the game itself. It offers access to the
 * {@link Game}, {@link SpriteBatch}, {@link AssetManager} and {@link Logger}
 * instances and delegates everything to the {@link Game}.
 * 
 * @author Daniel Holderbaum
 */
public class LibgdxUtils implements ApplicationListener {

	/**
	 * This is the {@link Logger} which will be used throughout the whole
	 * application.
	 */
	public static final Logger logger = new Logger("LibGDX Utils");

	/**
	 * The {@link Game} is public and can be accessed to switch the
	 * {@link Screen}s.
	 */
	public static final Game game = new Game() {

		@Override
		public void create() {
			setScreen(new SplashScreen());
		}
	};

	/**
	 * The only {@link AssetManager} which is responsible for the whole game.
	 */
	public static final AssetManager assets = new AssetManager();

	public static final Random random = new Random();

	public static SpriteBatch spriteBatch;

	public static ModelBatch modelBatch;

	@Override
	public void create() {
		try {
			logger.setLevel(Logger.DEBUG);
			Gdx.app.setLogLevel(Application.LOG_DEBUG);

			Texture.setAssetManager(assets);

			spriteBatch = new SpriteBatch();
			modelBatch = new ModelBatch();

			game.create();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Gdx.app.exit();
		}
	}

	@Override
	public void resume() {
		try {
			game.resume();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Gdx.app.exit();
		}
	}

	@Override
	public void render() {
		try {
			game.render();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		try {
			game.resize(width, height);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Gdx.app.exit();
		}
	}

	@Override
	public void pause() {
		try {
			game.pause();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		game.dispose();
		assets.dispose();

		if (spriteBatch != null) {
			spriteBatch.dispose();
		}
		if (modelBatch != null) {
			modelBatch.dispose();
		}
	}
}