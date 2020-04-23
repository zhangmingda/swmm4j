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
import java.time.LocalDateTime;

import com.geoprocessing.model.swmm.object.DateTimeType;
import com.geoprocessing.model.swmm.object.ObjectType;

/**
 * @author Mingda Zhang
 */
public class JSWMM {
	/**
	 * inFile, path of input file
	 * rptFile, path of report file (to be created)
	 * outFile, path of binary output file (to be created)
	 * libFile, path of dll
	 */
	private String inFile,rptFile,outFile,libFile;
	
	/**
	 * ERR_NONE = 0, if error occurs, the errorCode > 0
	 */
	private int errorCode;
	
	/**
	 * Error message 
	 */
	private String errMsg;
	private LocalDateTime startTime=null, endTime=null, reportStart=null, currentTime=null;
	
	public JSWMM(String inFile) throws SWMMExecption{
		this.inFile = inFile;
		this.initialize();
	}
	
	public JSWMM(String inFile,String rptFile,String outFile) throws SWMMExecption{
		this.inFile = inFile;
		this.rptFile = rptFile;
		this.outFile = outFile;
		this.initialize();
	}
	
	public JSWMM(String inFile,String rptFile,String outFile,String libFile) throws SWMMExecption{
		this.inFile = inFile;
		this.rptFile = rptFile;
		this.outFile = outFile;
		this.libFile = libFile;
		this.initialize();
	}
	
	private void initialize() throws SWMMExecption{
		this.checkInFile(this.inFile);
		
		if(this.rptFile == null || this.inFile.trim().equals("")){
			this.rptFile = this.inFile.replace(".inp", ".rpt");
			this.outFile = this.inFile.replace(".inp", ".out");
		}
		
		if(this.libFile == null || this.libFile.trim().equals("")){
			this.libFile = UtilTool.ResourcePath("swmmdev.dll");
		}
		
		loadLib(this.libFile);
	}
	
	private void checkInFile(String inFile) throws SWMMExecption{
		File file = new File(inFile);
		if(!inFile.endsWith(".inp")){
			throw new SWMMExecption("The input should be a .inp file.");
		}
		
		if(!file.exists()){
			throw new SWMMExecption(inFile + " does not exist.");
		}
	}
	
	private void loadLib(String libFile) throws SWMMExecption{
		File file = new File(libFile);
		if(!file.exists()){
			throw new SWMMExecption("The library " + libFile + " does not exist.");
		}
		
		try {
			System.load(libFile);
		} catch (Exception e) {
			throw new SWMMExecption("Errors occured when loading the library " + libFile);
		}
	}
	
	public String getReport(){
		return this.rptFile;
	}
	
	public String getOutput(){
		return this.outFile;
	}
	
	public int run(){
		return this.run(this.inFile, this.rptFile, this.outFile);
	}
	/**
	 * Opens input file, runs and closes. 
	 * swmm_run(char* f1, char* f2, char* f3)
	 * @param inFile
	 * @param rptFile
	 * @param outFile
	 * @return
	 */
	private native int run(String inFile,String rptFile,String outFile);
	
	public int open(){
		return this.open(this.inFile, this.rptFile, this.outFile);
	}
	
	/**
	 * swmm_open(char* f1, char* f2, char* f3)
	 * @param inFile
	 * @param rptFile
	 * @param outFile
	 * @return
	 */
	private native int open(String inFile,String rptFile,String outFile);
	
	
	/**
	 * Start SWMM simulation
	 * @param saveFlag, TRUE or FALSE to save timeseries to report file
	 * @return error code
	 */
	public native int start(boolean saveFlag);
	
	/**
	 * Step SWMM simulation forward
	 * @return elapsed simulation time [milliseconds]
	 */
	public native double step();
	
	/**
	 * Ends SWMM Simulation
	 * @return error code
	 */
	public native int end();
	
	/**
	 * Write text report file
	 * @return error code
	 */
	public native int report();
	
	/**
	 * Frees all memory and files used by SWMM
	 * @return error code
	 */
	public native int close();
	
	/**
	 * Get full semantic version number
	 */
	public native String getSemVersion();
	
	/**
	 * Get the text of an error code.
	 * @param errorCode, the error code
	 * @return
	 */
	public native String getError(int errorCode);
	
	/**
	 * Get the warning information
	 * @return
	 */
	public native String getWarnings();
	
	private native String getSimulationDateTime(int type);
	
	public LocalDateTime getStartTime(){
		if(this.startTime == null){
			String dateTimeStr = getSimulationDateTime(DateTimeType.StartDateTime.getValue());
			this.startTime = parseTimeStr(dateTimeStr);
		}
		return this.startTime;
	}
	
	public LocalDateTime getEndTime(){
		if(this.endTime == null){
			String dateTimeStr = getSimulationDateTime(DateTimeType.EndDateTime.getValue());
			this.endTime = parseTimeStr(dateTimeStr);
		}
		return this.endTime;
	}
	
	public LocalDateTime getReportStart(){
		if(this.reportStart == null){
			String dateTimeStr = getSimulationDateTime(DateTimeType.ReportStart.getValue());
			this.reportStart = parseTimeStr(dateTimeStr);
		}
		return this.reportStart;
	}
	
	public LocalDateTime getCurrentDateTime(){
		String dateTimeStr = getSimulationDateTime(DateTimeType.CurrentTime.getValue());
		this.currentTime = parseTimeStr(dateTimeStr);
		return this.currentTime;
	}
	
	private LocalDateTime parseTimeStr(String datetimeStr){
		String times[] = datetimeStr.split(" ");
		return LocalDateTime.of(Integer.parseInt(times[0]), 
				Integer.parseInt(times[1]), 
				Integer.parseInt(times[2]), 
				Integer.parseInt(times[3]), 
				Integer.parseInt(times[4]), 
				Integer.parseInt(times[5]));
	}
	/**
	 * @param type
	 * @param id
	 * @return the index (if >= 0) or the error code (if <0, needs to multiply by -1)
	 */
	public native int findObjectIndex(int objType, String id);
	
	
	/**
	 * @param objType, the object type
	 * @return the count number (if >= 0) or the error code (if <0, needs to multiply by -1)
	 */
	public native int countObjects(int objType);
	
	/**
	 * 
	 * @param objType, the object type
	 * @param index, the index of object
	 * @return identifier of the object
	 */
	public native String getObjectId(int objType,int index);
	
	/**
	 * 
	 * @param index, the index of the node
	 * @param param
	 * @return
	 */
	public native double getNodeParam(int index, int param);
	
	/**
	 * 
	 * @param index, the index of the link
	 * @param param
	 * @return
	 */
	public native double getLinkParam(int index,int param);
	
	/**
	 * 
	 * @param index, the index of the rain gage
	 * @return precipitation (if >=0) or error code (if <0, needs to multiply by -1)
	 */
	public native double getGagePrecip(int index);
	
	/**
	 * 
	 * @param id, the identifier of the rain gage
	 * @return precipitation
	 * @throws SWMMExecption 
	 */
	public double getGagePrecip(String id) throws SWMMExecption{
		int index = this.findObjectIndex(ObjectType.GAGE.getValue(), id);
		if(index <0)
			this.handleErrorCode(index*(-1));
		
		double value = this.getGagePrecip(index);
		if(value <0 )
			this.handleErrorCode((int)value*(-1));
		
		return value;
	}

	/**
	 * set the precipitation of the rain gage
	 * @param index, the index of the rain gage
	 * @param precipitation
	 * @return error code
	 */
	public native int setGagePrecip(int index,double precipitation);
	
	
	/**
	 * set the precipitation of the rain gage
	 * @param id, the index of the rain gage
	 * @param precipitation
	 * @throws SWMMExecption 
	 */
	public void setGagePrecip(String id,double precipitation) throws SWMMExecption{
		int index = this.findObjectIndex(ObjectType.GAGE.getValue(), id);
		if(index <0)
			this.handleErrorCode(index*(-1));
		
		int code = setGagePrecip(index, precipitation);
		
		handleErrorCode(code);
	}
	
	public native double getSubcatchResult(int index, int subResultType);
	
	public native double getLinkResult(int index, int linkResultType);
	
	public native double getNodeResult(int index, int nodeResultType);
	
	/**
	 * handle the error code
	 * ERR_NONE = 0, if error occurs, the errorCode > 0
	 * @param errorCode
	 * @throws SWMMExecption 
	 */
	public void handleErrorCode(int errorCode) throws SWMMExecption{
		if(errorCode!=0){
			String errMsg = this.getError(errorCode);
			throw new SWMMExecption(errMsg);
		}
	}
	
}
