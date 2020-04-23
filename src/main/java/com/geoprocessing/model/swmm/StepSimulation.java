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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.geoprocessing.model.swmm.object.Link;
import com.geoprocessing.model.swmm.object.Node;
import com.geoprocessing.model.swmm.object.ObjectType;
import com.geoprocessing.model.swmm.object.RainGage;
import com.geoprocessing.model.swmm.object.Subcatchment;

/**
 * Run the SWMM5 step by step.
 * 
 * @author Mingda Zhang
 *
 */
public class StepSimulation {
	
	/**
	 * The time interval of the time series data from rain gage.
	 */
	private long stepInSecond;
	private JSWMM swmm;
	private LocalDateTime waitForValueTime;
	private StringBuffer errInfo = new StringBuffer();
	private List<RainGage> rainGages = new ArrayList<RainGage>();
	private List<Node>  nodes = new ArrayList<Node>();
	private List<Link> links = new ArrayList<Link>();
	private List<Subcatchment> subcatchments = new ArrayList<Subcatchment>();
	
	public StepSimulation(JSWMM swmm,long step){
		this.swmm = swmm;
		this.stepInSecond = step;
	}
	
	public void initialize(){
		this.swmm.open();
		this.swmm.start(true);
		this.waitForValueTime = this.swmm.getStartTime();
	}
	
	public boolean step(Map<String, Double> rainfall,LocalDateTime rainTime){
		if(!this.waitForValueTime.equals(rainTime)){
			this.errInfo.append("The simulation is waiting for the data at "+this.waitForValueTime);
			return false;
		}
		
		for(String raingage:rainfall.keySet()){
			try {
				swmm.setGagePrecip(raingage, rainfall.get(raingage));
			} catch (SWMMExecption e) {
				// TODO Auto-generated catch block
				this.errInfo.append("Errors occured when settingn the rain gage values");
				e.printStackTrace();
				return false;
			}
		}
		
		LocalDateTime nextWaitTime = this.waitForValueTime.plusSeconds(stepInSecond);
		
		LocalDateTime currentTime = this.swmm.getCurrentDateTime();
		while ((currentTime.isBefore(nextWaitTime))  ) {
			double eclapsed = this.swmm.step();
			try {
				System.out.println(swmm.getGagePrecip("RainGage"));
			} catch (SWMMExecption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentTime = this.swmm.getCurrentDateTime();
		}
		this.waitForValueTime = nextWaitTime;
		return true;
	}
	
	public boolean finish(){

		this.swmm.end();
		this.swmm.report();
		this.swmm.close();
		
		return true;
	}
	
	public RainGage getRainGage(int index){
		for(RainGage gage : this.rainGages){
			if(gage.getIndex()==index)
				return gage;
		}
		
		RainGage rainGage = new RainGage(swmm, index);
		this.rainGages.add(rainGage);
		return rainGage;
	}
	
	public RainGage getRainGage(String id){
		for(RainGage gage : this.rainGages){
			if(gage.getId().equals(id))
				return gage;
		}
		
		int index = this.swmm.findObjectIndex(ObjectType.GAGE.getValue(), id);
		RainGage rainGage = new RainGage(swmm, index);
		rainGage.setId(id);
		this.rainGages.add(rainGage);
		
		return rainGage;
	}
	
	public Subcatchment getSubcatchment(int index){
		for(Subcatchment subcatchment : this.subcatchments){
			if(subcatchment.getIndex()==index)
				return subcatchment;
		}
		
		Subcatchment subc = new Subcatchment(swmm, index);
		this.subcatchments.add(subc);
		return subc;
	}
	
	public Subcatchment getSubcatchment(String id){
		for(Subcatchment subc : this.subcatchments){
			if(subc.getId().equals(id))
				return subc;
		}
		
		int index = this.swmm.findObjectIndex(ObjectType.SUBCATCH.getValue(), id);
		Subcatchment subc = new Subcatchment(swmm, index);
		subc.setId(id);
		this.subcatchments.add(subc);
		
		return subc;
	}
	
	public Link getLink(int index){
		for(Link link : this.links){
			if(link.getIndex()==index)
				return link;
		}
		
		Link link = new Link(swmm, index);
		this.links.add(link);
		return link;
	}
	
	public Link getLink(String id){
		for(Link link : this.links){
			if(link.getId().equals(id))
				return link;
		}
		
		int index = this.swmm.findObjectIndex(ObjectType.LINK.getValue(), id);
		Link link = new Link(swmm, index);
		link.setId(id);
		this.links.add(link);
		
		return link;
	}
	
	public Node getNode(int index){
		for(Node node : this.nodes){
			if(node.getIndex()==index)
				return node;
		}
		
		Node node = new Node(swmm, index);
		this.nodes.add(node);
		return node;
	}
	
	public Node Node(String id){
		for(Node node : this.nodes){
			if(node.getId().equals(id))
				return node;
		}
		
		int index = this.swmm.findObjectIndex(ObjectType.NODE.getValue(), id);
		Node node = new Node(swmm, index);
		node.setId(id);
		this.nodes.add(node);
		
		return node;
	}
}
