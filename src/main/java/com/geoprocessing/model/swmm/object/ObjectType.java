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
public enum ObjectType {
	GAGE(0), // rain gage
	SUBCATCH(1), // subcatchment
	NODE(2), // conveyance system node
	LINK(3), // conveyance system link
	POLLUT(4), // pollutant
	LANDUSE(5), // land use category
	TIMEPATTERN(6), // dry weather flow time pattern
	CURVE(7), // generic table of values
	TSERIES(8), // generic time series of values
	CONTROL(9), // conveyance system control rules
	TRANSECT(10), // irregular channel cross-section
	AQUIFER(11), // groundwater aquifer
	UNITHYD(12), // RDII unit hydrograph
	SNOWMELT(13), // snowmelt parameter set
	SHAPE(14), // custom conduit shape
	LID(15), // LID treatment units
	MAX_OBJ_TYPES(16); // MaximumObjectTypes

	private final int value;

	ObjectType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
