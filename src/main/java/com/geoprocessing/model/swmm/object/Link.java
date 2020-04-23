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

import com.geoprocessing.model.swmm.JSWMM;

/**
 * @author Mingda Zhang
 * TO update
 */
public class Link extends AbstractObject{
	
	
	public Link(JSWMM swmm,int index){
		super(swmm, index);
		this.objectType = ObjectType.LINK;
	}	
	
	public double getFlow(){
		return this.swmm.getLinkResult(this.index, LinkResultsType.newFlow.getValue());
	}
	
	public double getDepth(){
		return this.swmm.getLinkResult(this.index, LinkResultsType.newDepth.getValue());
	}
}
