/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * @author Daniel Holderbaum
 */
public class SlotTooltip extends Window implements SlotListener {

	private Skin skin;

	public SlotTooltip(Slot slot, Skin skin) {
		super("Tooltip...", skin);
		this.skin = skin;
		hasChanged(slot);
		slot.addListener(this);
		setVisible(false);
	}

	@Override
	public void hasChanged(Slot slot) {
		if (slot.isEmpty()) {
			setVisible(false);
			return;
		}

		setTitle(slot.getAmount() + "x " + slot.getItem());
		clear();
		Label label = new Label("Super awesome description of " + slot.getItem(), skin);
		add(label);
		pack();
	}

}
