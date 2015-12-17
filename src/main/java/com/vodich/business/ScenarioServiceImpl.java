package com.vodich.business;

import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.JMSUtils;
import com.vodich.dao.DAOException;
import com.vodich.dao.ElasticsearchUtils;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;

public class ScenarioServiceImpl implements ScenarioService {
	
	private ScenarioDAO scenarioDAO;
	private JMSUtils jmsUtils;
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void save(Scenario scenario) throws DAOException {
		scenarioDAO.save(scenario);
	}

	@Override
	public void launch(String scenarioId) throws DAOException, JMSException {
		Scenario scenario = scenarioDAO.load(scenarioId);
		if (scenario == null) {
			throw new DAOException("Scenario with id " + scenarioId + " not found");
		}
		try {
			for(Flow flow : scenario.getFlows()){
				String json = mapper.writeValueAsString(flow);
				jmsUtils.startConnection();
				jmsUtils.send(1, json, Integer.parseInt(flow.getConsumer()));
			
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			jmsUtils.stopConnection();
		}
	}

	@Override
	public void delete(String scenarioId) throws DAOException {
		scenarioDAO.delete(scenarioId);

	}
	
	ScenarioServiceImpl(ScenarioDAO scenarioDAO, JMSUtils jmsUtils) {
		this.scenarioDAO = scenarioDAO;
		this.jmsUtils = jmsUtils;
	}
	
	private static ScenarioServiceImpl instance;
	public static ScenarioService getInstance() {
		if (instance == null) {
			try {
				instance = new ScenarioServiceImpl(ScenarioDAOImpl.getInstance(), JMSUtils.getInstance());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return instance;
	}

	@Override
	public List<Scenario> loadAll() {
		return scenarioDAO.loadAll();
	}

	@Override
	public Scenario load(String scenarioID) throws DAOException {
		return scenarioDAO.load(scenarioID);
	}
	
	public Scenario loadByName(String scenarioName) throws DAOException {
		return scenarioDAO.loadByName(scenarioName);
	}
	
	
	

}
