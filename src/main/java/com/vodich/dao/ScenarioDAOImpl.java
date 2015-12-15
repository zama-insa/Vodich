package com.vodich.dao;

import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.business.CommonService;
import com.vodich.business.CommonServiceImpl;
import com.vodich.core.bean.Scenario;

public class ScenarioDAOImpl implements ScenarioDAO {
	
	private ScenarioDAO scenarioDAO;
	private static ObjectMapper mapper = new ObjectMapper();

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
	public void delete(String scenarioId) throws DAOException{
		
		try {
		DeleteResponse response = ElasticsearchUtils.deleteScenario(scenarioId);
		System.out.println(response);
		} catch (Exception e) {
			throw new DAOException("[DAO] Save scenario failed", e);
		}
		
	}

	@Override
	public Scenario load(String scenarioId) {
		return ElasticsearchUtils.load(scenarioId);
	}

	@Override
	public List<Scenario> loadAll() {
		return ElasticsearchUtils.loadScenarii();
	}
	
	private ScenarioDAOImpl() {}
	private static ScenarioDAOImpl instance;
	public static ScenarioDAO getInstance() {
		if (instance == null) {
			instance = new ScenarioDAOImpl();
		}
		return instance;
	}
	
	
}
