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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.geoprocessing.model.swmm.JSWMM;
import com.geoprocessing.model.swmm.SWMMExecption;
import com.geoprocessing.model.swmm.StepSimulation;
import com.geoprocessing.model.swmm.UtilTool;

/**
 * @author Mingda Zhang
 */
public class SimulationTest {
	public static void main(String[] args){
		
		Map<LocalDateTime, Double> rainfall = new Precipitation().Year100();
		
		String infile = UtilTool.ResourcePath("example1.inp");
		JSWMM swmm = null;
		try {
			swmm = new JSWMM(infile);
		} catch (SWMMExecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		long stepInSecond = 300;
		String raingage = "RainGage";
		StepSimulation simulation = new StepSimulation(swmm, stepInSecond);
		
		simulation.initialize();
		LocalDateTime currentTime = swmm.getStartTime();
		LocalDateTime endTime = swmm.getEndTime();
		
		while (currentTime.isBefore(endTime)) {
			if(!rainfall.containsKey(currentTime))
				break;
			
			double preciptation = rainfall.get(currentTime);
			Map<String, Double> gageValueMap = new HashMap<String, Double>();
			gageValueMap.put(raingage, preciptation);
			simulation.step(gageValueMap, currentTime);
			
			currentTime = currentTime.plusSeconds(stepInSecond);
		}
		
		simulation.finish();
	}
}
