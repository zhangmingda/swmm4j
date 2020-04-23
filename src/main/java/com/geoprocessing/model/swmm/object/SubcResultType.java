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
public enum SubcResultType {
    rainfall(0),  //Current Rainfall
    evapLoss(1), //Current Evaporation Loss
    infilLoss(2), //Current Infiltration Loss
    runon(3),  //Subcatchment Runon
    newRunoff(4),  //Current Runoff
    newSnowDepth(5);  //Current Snow Depth

	private final int value;

	SubcResultType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
