/**
 * This program is free software; you can redistribute and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by the 
 * Free Software Foundation.
 * 
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package com.geoprocessing.model.swmm;

/**
 * @author Mingda Zhang
 */
public class SWMMExecption extends Exception{
	

	private static final long serialVersionUID = 1L;
	public SWMMExecption(String errMsg){
		super(errMsg);
	}
}
