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
public enum DateTimeType {
	StartDateTime(0), // rain gage
	EndDateTime(1), // subcatchment
	ReportStart(2), // conveyance system node
	CurrentTime(3); // conveyance system link
	

	private final int value;

	DateTimeType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
