/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

/**
 * @author Daniel Holderbaum
 */
public class InventoryActor extends Window {

	private Inventory inventory;

	public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
		super("Inventory...", skin);

		this.inventory = inventory;

		TextButton closeButton = new TextButton("X", skin);
		closeButton.addListener(new HidingClickListener(this));
		getButtonTable().add(closeButton).height(getPadTop());

		setPosition(400, 100);
		defaults().space(8);
		row().fill().expandX();

		int i = 0;
		for (Slot slot : inventory.getSlots()) {
			SlotActor slotActor = new SlotActor(skin, slot);
			dragAndDrop.addSource(new SlotSource(slotActor));
			dragAndDrop.addTarget(new SlotTarget(slotActor));
			add(slotActor);

			i++;
			if (i % 5 == 0) {
				row();
			}
		}

		pack();

		// it is hidden by default
		setVisible(false);
	}

}
