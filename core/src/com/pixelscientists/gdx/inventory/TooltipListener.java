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
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Makes a given tooltip actor visible when the actor this listener is attached
 * to was hovered. It will also hide the tooltip when the mouse is not hovering
 * anymore.
 * 
 * @author Daniel Holderbaum
 */
public class TooltipListener extends InputListener {

	private boolean inside;

	private Actor tooltip;
	private boolean followCursor;

	private Vector2 position = new Vector2();
	private Vector2 tmp = new Vector2();
	private Vector2 offset = new Vector2(10, 10);

	public TooltipListener(Actor tooltip, boolean followCursor) {
		this.tooltip = tooltip;
		this.followCursor = followCursor;
	}

	@Override
	public boolean mouseMoved(InputEvent event, float x, float y) {
		if (inside && followCursor) {
			event.getListenerActor().localToStageCoordinates(tmp.set(x, y));
			tooltip.setPosition(tmp.x + position.x + offset.x, tmp.y + position.y + offset.y);
		}
		return false;
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		inside = true;
		tooltip.setVisible(true);
		tmp.set(x, y);
		event.getListenerActor().localToStageCoordinates(tmp);
		tooltip.setPosition(tmp.x + position.x + offset.x, tmp.y + position.y + offset.y);
		tooltip.toFront();
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		inside = false;
		tooltip.setVisible(false);
	}

	/**
	 * The offset of the tooltip from the touch position. It should not be
	 * positive as the tooltip will flicker otherwise.
	 */
	public void setOffset(float offsetX, float offsetY) {
		offset.set(offsetX, offsetY);
	}

}
