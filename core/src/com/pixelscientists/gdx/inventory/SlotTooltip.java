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

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * @author Daniel Holderbaum
 */
public class SlotTooltip extends Window implements SlotListener {

	private Skin skin;

	private Slot slot;

	public SlotTooltip(Slot slot, Skin skin) {
		super("Tooltip...", skin);
		this.slot = slot;
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

		// title displays the amount
		setTitle(slot.getAmount() + "x " + slot.getItem());
		clear();
		Label label = new Label("Super awesome description of " + slot.getItem(), skin);
		add(label);
		pack();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		// the listener sets this to true in case the slot is hovered
		// however, we don't want that in case the slot is empty
		if (slot.isEmpty()) {
			super.setVisible(false);
		}
	}

}
