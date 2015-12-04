package com.vodich.core.bean;

import java.util.Date;
import java.util.List;

public class Scenario {
	private String id;
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
}
