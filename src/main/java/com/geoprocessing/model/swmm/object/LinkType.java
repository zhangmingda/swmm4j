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
public enum LinkType {
    conduit(0), pump(1), orifice(2),weir(3),outlet(4);
	

	private final int value;

	LinkType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
