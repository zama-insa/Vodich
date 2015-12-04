package com.vodich.business;

import com.vodich.core.bean.Scenario;

public interface ScenarioService {

	public void save(Scenario scenario);
	
	public void launch(String scenarioId);
	
	public void delete(String scenarioId);
}
