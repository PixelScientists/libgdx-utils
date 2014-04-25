/* ######################################
 * Copyright 2014 (c) Pixel Scientists
 * All rights reserved.
 * Unauthorized copying of this file, via
 * any medium is strictly prohibited.
 * Proprietary and confidential.
 * ###################################### */
package com.pixelscientists.gdx.inventory;

/**
 * @author Daniel Holderbaum
 */
public interface SlotListener {

	/**
	 * Will be called whenever the slot has changed.
	 * 
	 * @param slot
	 *            The changed slot.
	 */
	void hasChanged(Slot slot);

}
