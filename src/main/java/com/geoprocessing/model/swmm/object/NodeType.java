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
public enum NodeType {
	 junction(0),outfall(1),storage(2),divider(3);
	

	private final int value;

	NodeType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
