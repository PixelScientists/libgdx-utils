/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

/**
 * @author Daniel Holderbaum
 */
public class SlotTarget extends Target {

	private Slot targetSlot;

	public SlotTarget(SlotActor actor) {
		super(actor);
		targetSlot = actor.getSlot();
		getActor().setColor(Color.LIGHT_GRAY);
	}

	@Override
	public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		Slot payloadSlot = (Slot) payload.getObject();
		// if (targetSlot.getItem() == payloadSlot.getItem() ||
		// targetSlot.getItem() == null) {
		getActor().setColor(Color.WHITE);
		return true;
		// } else {
		// getActor().setColor(Color.DARK_GRAY);
		// return false;
		// }
	}

	@Override
	public void drop(Source source, Payload payload, float x, float y, int pointer) {
	}

	@Override
	public void reset(Source source, Payload payload) {
		getActor().setColor(Color.LIGHT_GRAY);
	}

}
