package com.vodich.dao;

import java.util.List;

import com.vodich.core.bean.Scenario;

public interface ScenarioDAO {
	
	public void save(Scenario scenario);

	public void delete(String scenarioId);
	
	public Scenario load(String scenarioId);
	
	public List<Scenario> loadAll();
}
