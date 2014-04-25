/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.math.MathUtils;
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

		this.item = Item.values()[MathUtils.random(0, Item.values().length - 1)];
		this.amount = 1;
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
