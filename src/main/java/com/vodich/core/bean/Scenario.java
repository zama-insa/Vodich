package com.vodich.core.bean;

import java.util.Date;
import java.util.List;

public class Scenario {
	private String id;
	private String name;
	private Date createdAt;
	private List<Flow> flows;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public List<Flow> getFlows() {
		return flows;
	}
	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Scenario scenario1, Scenario scenario2){
		if (scenario1.id.compareTo(scenario2.id) ==0){
			if ( (scenario1.createdAt).compareTo(scenario2.createdAt) == 0){
				if ((scenario1.flows).equals(scenario2.flows)){
					return true;
				}
				}
		}
		return false;
	}

}
