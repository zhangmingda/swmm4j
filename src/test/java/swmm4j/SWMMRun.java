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

import com.geoprocessing.model.swmm.JSWMM;
import com.geoprocessing.model.swmm.SWMMExecption;
import com.geoprocessing.model.swmm.UtilTool;

/**
 * @author Mingda Zhang
 */
public class SWMMRun {
	public static void main(String[] args) {
		String infile = UtilTool.ResourcePath("example1.inp");

		JSWMM swmm;
		try {
			swmm = new JSWMM(infile);

			swmm.open();
			swmm.start(true);

			
			System.out.println("start time "+ swmm.getStartTime());
			System.out.println("end time "+ swmm.getEndTime());
			System.out.println("report start time "+ swmm.getReportStart());
			
			double escaped = 0;
			do {
				escaped = swmm.step();
				System.out.println(swmm.getCurrentDateTime());
			} while (escaped > 0);

			swmm.end();
			swmm.report();
			swmm.close();
		} catch (SWMMExecption e) {
			e.printStackTrace();
		}
	}
}
