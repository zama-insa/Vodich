package com.vodich.business;

import java.util.List;

import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;

public interface ScenarioService {
	
	public void save(Scenario scenario) throws DAOException;
	
	public void launch(String scenarioId);
	
	public void delete(String scenarioId);
	
	public List<Scenario> loadAll();
	
	public Scenario load (String scenarioID) throws DAOException;
}
