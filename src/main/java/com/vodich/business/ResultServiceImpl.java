package com.vodich.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Result;
import com.vodich.core.bean.Scenario;
import com.vodich.core.util.JMSUtils;
import com.vodich.core.util.VodichUtils;
import com.vodich.dao.DAOException;
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ResultDAOImpl;
import com.vodich.dao.ScenarioDAO;
import com.vodich.dao.ScenarioDAOImpl;

public class ResultServiceImpl implements ResultService {

	private ScenarioDAO scenarioDAO;
	private ResultDAO resultDAO;
	private JMSUtils jmsUtils;
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public Result load(String id) {
		return resultDAO.load(id);
	}

	ResultServiceImpl(ResultDAO resultDAO, JMSUtils jmsUtils, ScenarioDAO scenarioDAO) {
		this.resultDAO = resultDAO;
		this.jmsUtils = jmsUtils;
		this.scenarioDAO = scenarioDAO;
	}

	private static ResultService instance;
	public static ResultService getInstance() {
		if (instance == null) {
			instance = new ResultServiceImpl(ResultDAOImpl.getInstance(), JMSUtils.getInstance(), ScenarioDAOImpl.getInstance());
		}
		return instance;
	}

	@Override
	public List<Result> loadAll() {
		return resultDAO.loadAll();
	}

	public String actualizeResult(Scenario scenario) throws IOException, JMSException {
		String rid = null;
			String resultString = jmsUtils.receive(6);
			if (VodichUtils.isNullOrEmpty(resultString)) {
				throw new JMSException("No result found for scenario '" + scenario.getName() + "'");
			}
			Map<String, Object> resultJson;
			try {
				resultJson = mapper.readValue(resultString, new TypeReference<Map<String, Object>>() {});
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			Result result = new Result();
			result.setFinishTime(new Date());
			scenario.setTotalLaunches(scenario.getTotalLaunches() + 1);
			result.setLaunchNum(scenario.getTotalLaunches());
			result.setName(scenario.getName() + " [" + result.getLaunchNum() + "]");
			result.setScenarioId(scenario.getId());
			result.setResult((List<Object>) resultJson.get("messageResults"));
			try {
				scenarioDAO.update(scenario);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new IOException("Error updating scenario + '" + scenario.getName() + "'");
			}
			rid = resultDAO.save(result);
			jmsUtils.stopConnection();
		return rid;
	}
}
