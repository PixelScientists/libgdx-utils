/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

import com.badlogic.gdx.utils.Array;

/**
 * @author Daniel Holderbaum
 */
public class Inventory {

	private Array<Slot> slots;

	public Inventory() {
		slots = new Array<Slot>(25);
		for (int i = 0; i < 25; i++) {
			slots.add(new Slot(null, 0));
		}
	}

	public int checkInventory(Item item) {
		int amount = 0;

		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				amount += slot.getAmount();
			}
		}

		return amount;
	}

	public boolean store(Item item, int amount) {
		// first check for a slot with the same item type
		Slot itemSlot = firstSlotWithItem(item);
		if (itemSlot != null) {
			itemSlot.add(item, amount);
			return true;
		} else {
			// now check for an available empty slot
			Slot emptySlot = firstSlotWithItem(null);
			if (emptySlot != null) {
				emptySlot.add(item, amount);
				return true;
			}
		}

		// no slot to add
		return false;
	}

	public Array<Slot> getSlots() {
		return slots;
	}

	private Slot firstSlotWithItem(Item item) {
		for (Slot slot : slots) {
			if (slot.getItem() == item) {
				return slot;
			}
		}

		return null;
	}

}
