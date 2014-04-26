/* Copyright (c) 2014 PixelScientists
 * 
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

	private float minimumShowTime = 3.0f;

	private OrthographicCamera camera;

	private Viewport viewport;

	private Texture splash;

	@Override
	public void show() {
		LibgdxUtils.assets.load("textures/splash.png", Texture.class);
		LibgdxUtils.assets.finishLoading();
		splash = LibgdxUtils.assets.get("textures/splash.png", Texture.class);

		LibgdxUtils.assets.load("skins/uiskin.json", Skin.class);
		LibgdxUtils.assets.load("icons/icons.atlas", TextureAtlas.class);

		camera = new OrthographicCamera();
		viewport = new FitViewport(splash.getWidth(), splash.getHeight(), camera);
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
		// NOOP
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
	}

}
