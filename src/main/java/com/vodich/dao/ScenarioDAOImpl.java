package com.vodich.dao;

import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.VodichUtils;

public class ScenarioDAOImpl implements ScenarioDAO {

	private ScenarioDAO scenarioDAO;
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void save(Scenario scenario) throws DAOException {
		try {
			if (VodichUtils.isNullOrEmpty(scenario.getName())) {
				throw new DAOException("Required field missing for Scenario : 'name'");
			}
			String scenarioId = ElasticsearchUtils.saveScenario(scenario);
			scenario.setId(scenarioId);
		} catch (Exception e) {
			throw new DAOException("[DAO] Save scenario failed", e);
		}
	}

	@Override
	public boolean delete(String scenarioId) throws DAOException {

		try {
			DeleteResponse response = ElasticsearchUtils.deleteScenario(scenarioId);
			return response.isFound();
		} catch (Exception e) {
			throw new DAOException("[DAO] Delete scenario failed", e);
		}

	}

	@Override
	public Scenario load(String scenarioId) {
		return ElasticsearchUtils.load(scenarioId);
	}

	public Scenario loadByName(String scenarioName) throws DAOException {
		return ElasticsearchUtils.loadByName(scenarioName);
	}

	@Override
	public List<Scenario> loadAll() {
		return ElasticsearchUtils.loadScenarii();
	}

	ScenarioDAOImpl() {
	}

	private static ScenarioDAOImpl instance;

	public static ScenarioDAO getInstance() {
		if (instance == null) {
			instance = new ScenarioDAOImpl();
		}
		return instance;
	}

}
