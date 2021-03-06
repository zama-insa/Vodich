package com.vodich.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Flow;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.JMSUtils;
import com.vodich.dao.DAOException;
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ResultDAOImpl;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;

public class ScenarioServiceImpl implements ScenarioService {

	private ScenarioDAO scenarioDAO;
	private ResultDAO resultDAO;
	private JMSUtils jmsUtils;
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void save(Scenario scenario) throws DAOException {
		scenario.setCreatedAt(new Date());
		scenario.setTotalLaunches(0);
		scenarioDAO.save(scenario);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void launch(String scenarioId) throws DAOException, JMSException {
		Scenario scenario = scenarioDAO.load(scenarioId);
		if (scenario == null) {
			throw new DAOException("Scenario with id " + scenarioId + " not found");
		}
		try {
			for (Flow flow : scenario.getFlows()) {
				String json = mapper.writeValueAsString(flow);
				jmsUtils.startConnection();
				jmsUtils.send(1, json, Integer.parseInt(flow.getConsumer()));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean delete(String scenarioId) throws DAOException {
		
		return scenarioDAO.delete(scenarioId);

	}

	ScenarioServiceImpl(ScenarioDAO scenarioDAO, ResultDAO resultDAO, JMSUtils jmsUtils) {
		this.scenarioDAO = scenarioDAO;
		this.resultDAO = resultDAO;
		this.jmsUtils = jmsUtils;
	}

	private static ScenarioServiceImpl instance;

	public static ScenarioService getInstance() {
		if (instance == null) {
			instance = new ScenarioServiceImpl(ScenarioDAOImpl.getInstance(), ResultDAOImpl.getInstance(), JMSUtils.getInstance());
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

	@Override
	public double getMaxtime(Scenario scenario) {
		double max = 0;
		for(Flow flow : scenario.getFlows()){
			if(((double)(flow.getStop()*1000)+flow.getProcessTime()>max)){
				max = flow.getStop()*1000+flow.getProcessTime();
			}
		}
		return max+5000;
	}

}
