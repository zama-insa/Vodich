package com.vodich.core.bean;

import java.util.Date;
import java.util.List;

public class Result extends AbstractVodichBean {
	private String id;
	private String scenarioId;
	private Date launchTime;
	private Date finishTime;
	private List<Object> result;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}
	public Date getLaunchTime() {
		return launchTime;
	}
	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public List<Object> getResult() {
		return result;
	}
	public void setResult(List<Object> result) {
		this.result = result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Result that = (Result) obj;
		return this.id == that.id;
	}
}
