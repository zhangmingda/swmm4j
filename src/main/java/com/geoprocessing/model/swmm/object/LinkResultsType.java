/**
 * This program is free software; you can redistribute and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by the 
 * Free Software Foundation.
 * 
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package com.geoprocessing.model.swmm.object;

/**
 * @author Mingda Zhang
 */
public enum LinkResultsType {
	newFlow(0),newDepth(1),newVolume(2),surfArea1(3),surfArea2(4),setting(5),targetSetting(6),froude(7);

	private final int value;

	LinkResultsType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
