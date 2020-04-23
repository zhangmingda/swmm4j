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
public class Subcatchment extends AbstractObject{
	
	public Subcatchment(JSWMM swmm,int index){
		super(swmm, index);
		this.objectType = ObjectType.SUBCATCH;
	}
	
	public double getRunoff(){
		return this.swmm.getSubcatchResult(this.index, SubcResultType.newRunoff.getValue());
	}
	
	public double getRainfall(){
		return this.swmm.getSubcatchResult(this.index, SubcResultType.rainfall.getValue());
	}
}
