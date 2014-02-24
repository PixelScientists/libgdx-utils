/* 
 * Copyright (c) 2014 Pixel Scientists
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.pixelscientists.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;

/**
 * This is used to work with just a fixed virtual viewport. It implements
 * "letterboxing" which means that it will maintain the aspect ratio of the
 * virtual viewport while scaling it as much as possible to fit the screen. It
 * has methods to help with setting the correct OpenGL viewport, as well as
 * dealing with {@code Stage}s and {@code Camera}s.
 * 
 * @author Daniel Holderbaum
 */
public class VirtualViewport {

	private int virtualWidth;

	private int virtualHeight;

	private int viewportWidth;

	private int viewportHeight;

	private int viewportX;

	private int viewportY;

	public VirtualViewport(int virtualWidth, int virtualHeight) {
		this.virtualWidth = virtualWidth;
		this.virtualHeight = virtualHeight;
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void resize(int width, int height) {
		Vector2 scaled = Scaling.fit.apply(virtualWidth, virtualHeight, width, height);
		viewportWidth = Math.round(scaled.x);
		viewportHeight = Math.round(scaled.y);
		viewportX = (width - viewportWidth) / 2;
		viewportY = (height - viewportHeight) / 2;
	}

	public void setOpenGLViewport() {
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public void setStageViewport(Stage stage) {
		stage.setViewport(virtualWidth, virtualHeight, true, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public void unproject(Camera camera, Vector3 vec) {
		camera.unproject(vec, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public void project(Camera camera, Vector3 vec) {
		camera.project(vec, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public Ray getPickRay(Camera camera, float x, float y) {
		return camera.getPickRay(x, y, viewportX, viewportY, viewportWidth, viewportHeight);
	}

	public int getVirtualWidth() {
		return virtualWidth;
	}

	public int getVirtualHeight() {
		return virtualHeight;
	}

	public int getViewportWidth() {
		return viewportWidth;
	}

	public int getViewportHeight() {
		return viewportHeight;
	}

	public int getViewportX() {
		return viewportX;
	}

	public int getViewportY() {
		return viewportY;
	}

}
