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

import java.io.File;

/**
 * @author Mingda Zhang
 */
public class UtilTool {
	
	public static String ResourcePath(String filename){
		String file = UtilTool.class.getClassLoader().getResource(filename).getPath();
		File tarFile = new File(file);
		return tarFile.getAbsolutePath();
	}
	
	
	public static void main(String[] args){
		
		System.out.println(ResourcePath("example1.inp"));
	}
}
