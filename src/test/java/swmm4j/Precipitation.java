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


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.geoprocessing.model.swmm.UtilTool;

/**
 * @author Mingda Zhang
 */
public class Precipitation {

	DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm");

	private Map<LocalDateTime, Double> Rainfall(String path) {

		Map<LocalDateTime, Double> precipitaion = new HashMap<LocalDateTime, Double>();
		//String path = UtilTool.ResourcePath(path);
		FileReader reader;
		try {
			reader = new FileReader(path);

			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#"))
					continue;

				String[] values = line.split("\\s+");
				if (values.length < 3)
					continue;
				String dtime = values[0].trim()+" "+values[1].trim();
				LocalDateTime dateTime = LocalDateTime.parse(dtime,
						formatter);
				double rainfall = Double.parseDouble(values[2].trim());

				precipitaion.put(dateTime, rainfall);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return precipitaion;
	}
	
	public Map<LocalDateTime, Double> Year100(){
		String path = UtilTool.ResourcePath("raingage100y.txt");
		return this.Rainfall(path);
	}
}
