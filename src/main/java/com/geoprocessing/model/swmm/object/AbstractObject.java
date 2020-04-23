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
public abstract class AbstractObject {
	
	protected JSWMM swmm;
	protected int index;
	protected String id;
	protected ObjectType objectType = ObjectType.NODE;
	
	public AbstractObject(JSWMM swmm,int index){
		this.swmm = swmm;
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	
	public String getId(){
		if(this.id == null || this.id.trim().equals("")){
			this.id = this.swmm.getObjectId(objectType.getValue(), index);
		}
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
}
