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

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Scenario that = (Scenario) o;
		if (id.compareTo(that.id) == 0) {
			if ((createdAt).compareTo(that.createdAt) == 0) {
				if ((flows).equals(that.flows)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "[Scenario id=" + id + "; name=" + name + ";createdAt=" + createdAt + ";flows=" + flows + "]";
	}
}
