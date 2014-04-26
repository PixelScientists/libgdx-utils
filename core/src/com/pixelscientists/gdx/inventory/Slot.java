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

import com.badlogic.gdx.utils.Array;

/**
 * @author Daniel Holderbaum
 */
public class Slot {

	private Item item;

	private int amount;

	private Array<SlotListener> slotListeners = new Array<SlotListener>();

	public Slot(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public boolean isEmpty() {
		return item == null || amount <= 0;
	}

	public void addListener(SlotListener slotListener) {
		slotListeners.add(slotListener);
	}

	public void removeListener(SlotListener slotListener) {
		slotListeners.removeValue(slotListener, true);
	}

	/**
	 * Returns {@code true} in case this slot has the same item type and at
	 * least the same amount of items as the given other slot.
	 * 
	 * @param other
	 *            The other slot to be checked.
	 * @return {@code True} in case this slot has the same item type and at
	 *         least the same amount of items as the given other slot.
	 *         {@code False} otherwise.
	 */
	public boolean matches(Slot other) {
		return this.item == other.item && this.amount >= other.amount;
	}

	public boolean add(Item item, int amount) {
		if (this.item == item || this.item == null) {
			this.item = item;
			this.amount += amount;
			notifyListeners();
			return true;
		}

		return false;
	}

	public boolean take(int amount) {
		if (this.amount >= amount) {
			this.amount -= amount;
			if (this.amount == 0) {
				item = null;
			}
			notifyListeners();
			return true;
		}

		return false;
	}

	private void notifyListeners() {
		for (SlotListener slotListener : slotListeners) {
			slotListener.hasChanged(this);
		}
	}

	public Item getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Slot[" + item + ":" + amount + "]";
	}
}
