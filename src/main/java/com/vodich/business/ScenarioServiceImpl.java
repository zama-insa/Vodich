package com.vodich.business;

import java.util.List;

import com.vodich.core.bean.Scenario;
import com.vodich.dao.DAOException;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;

public class ScenarioServiceImpl implements ScenarioService {
	
	private ScenarioDAO scenarioDAO;

	@Override
	public void save(Scenario scenario) throws DAOException {
		scenarioDAO.save(scenario);
	}

	@Override
	public void launch(String scenarioId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String scenarioId) throws DAOException {
		scenarioDAO.delete(scenarioId);

	}
	
	
	private ScenarioServiceImpl() {
		scenarioDAO = ScenarioDAOImpl.getInstance();
	}
	private static ScenarioServiceImpl instance;
	public static ScenarioService getInstance() {
		if (instance == null) {
			instance = new ScenarioServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Scenario> loadAll() {
		// TODO Auto-generated method stub
		return scenarioDAO.loadAll();
	}

	@Override
	public Scenario load(String scenarioID) throws DAOException {
		// TODO Auto-generated method stub
		return scenarioDAO.load(scenarioID);
	}
	
	

}
