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
public enum NodeResultsType {
	   totalinflow(0),//Total Inflow
	   totaloutflow(1), //Total Outflow
	   losses(2), //Losses (evap + exfiltration loss)
	   newVolume(3),  //current Volume
	   overflow(4),  //overflow
       newDepth(5),  //Current water depth
	   newHead(6),  //Current water head
	   newLatFlow(7);  //Current Lateral Inflow

	private final int value;

	NodeResultsType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
