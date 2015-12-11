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
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void save(Scenario scenario) throws DAOException {
		scenarioDAO.save(scenario);
	}

	@Override
	public void launch(String scenarioId) throws DAOException, JMSException {
		// TODO Auto-generated method stub
		Scenario scenario = scenarioDAO.load(scenarioId);
		try {
			JMSUtils jmsUtils = JMSUtils.getInstance();
			for(Flow flow : scenario.getFlows()){
				
				
				String json = mapper.writeValueAsString(flow);
				jmsUtils.startConnection();
				jmsUtils.send(1, json, Integer.parseInt(flow.getConsumer()));
			
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


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
