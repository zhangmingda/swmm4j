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
 * @author Mingda Zhang TO update
 */
public class RainGage extends AbstractObject{

	public RainGage(JSWMM swmm, int index) {
		super(swmm, index);
		this.objectType = ObjectType.GAGE;
	}

	public double getRainfall() {
		return this.swmm.getGagePrecip(this.index);
	}

	public void setRainfall(double rainfall) {
		this.swmm.setGagePrecip(this.index, rainfall);
	}

}
