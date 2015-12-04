package com.vodich.dao;

import java.util.List;

import org.elasticsearch.action.index.IndexResponse;

import com.vodich.core.bean.Scenario;

public class ScenarioDAOImpl implements ScenarioDAO {

	@Override
	public void save(Scenario scenario) throws DAOException {
		try {
			IndexResponse response = ElasticsearchUtils.saveScenario(scenario);
			System.out.println(response);
		} catch (Exception e) {
			throw new DAOException("[DAO] Save scenario failed", e);
		}
	}

	@Override
	public void delete(String scenarioId) {
		
	}

	@Override
	public Scenario load(String scenarioId) {
		return null;
	}

	@Override
	public List<Scenario> loadAll() {
		return null;
	}

}
