/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
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

	public TooltipListener(Actor tooltip, boolean followCursor) {
		this.tooltip = tooltip;
		this.followCursor = followCursor;
	}

	@Override
	public boolean mouseMoved(InputEvent event, float x, float y) {
		if (inside && followCursor) {
			event.getListenerActor().localToStageCoordinates(tmp.set(x, y));
			tooltip.setPosition(tmp.x + position.x, tmp.y + position.y);
		}
		return false;
	}

	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		inside = true;
		tooltip.setVisible(true);
		tmp.set(x, y);
		event.getListenerActor().localToStageCoordinates(tmp);
		tooltip.setPosition(tmp.x + position.x + 10, tmp.y + position.y + 10);
		tooltip.toFront();
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		inside = false;
		tooltip.setVisible(false);
	}

}
