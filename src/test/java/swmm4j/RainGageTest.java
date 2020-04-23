/**
 * This program is free software; you can redistribute and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by the 
 * Free Software Foundation.
 * 
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package swmm4j;

import java.io.File;

import com.geoprocessing.model.swmm.UtilTool;

/**
 * @author Mingda Zhang
 */
public class RainGageTest {
	public static void main(String[] args){
		
		String inFile = UtilTool.ResourcePath("example1.inp");
		//String libFile = UtilTool.ResourcePath("example1.inp");
		
		File file = new File(inFile);
		String name = file.getName();
		
		String parentPath = file.getParent();
		
		String rptName = name.replace(".inp", ".rpt");
		
		String rptFile = parentPath + rptName;
		
		System.out.println(rptFile);
		
		System.out.println(inFile);
	}
}
