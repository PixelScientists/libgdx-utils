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
public enum Item {

	// ###########
	// T1 Crystals
	// ###########

	CRYSTAL_RED("redcrystal"), CRYSTAL_BLUE("bluecrystal"), CRYSTAL_GREEN("greencrystal"),

	// ###########
	// T2 Crystals
	// ###########

	CRYSTAL_YELLOW("yellowcrystal"), CRYSTAL_MAGENTA("magentacrystal"), CRYSTAL_CYAN("cyancrystal"),

	// ###########
	// T3 Crystals
	// ###########

	CRYSTAL_ORANGE("orangecrystal"), CRYSTAL_VIOLET("violetcrystal"), CRYSTAL_BROWN("browncrystal"),

	// ######
	// Metals
	// ######

	METAL_TITANIUM("titanium"), METAL_PALLADIUM("palladium"), METAL_IRIDIUM("iridium"), METAL_RHODIUM("rhodium"),

	// ##########
	// Base parts
	// ##########

	BASE_HULL("hullbase"), BASE_CANNON("cannonbase"), BASE_RAY("raybase"), BASE_LAUNCHER("launcherbase"), BASE_DROID("droidbase"), BASE_DROPPER("dropperbase"), BASE_BATTERY(
			"batterybase");

	private String textureRegion;

	private Item(String textureRegion) {
		this.textureRegion = textureRegion;
	}

	public String getTextureRegion() {
		return textureRegion;
	}

}
