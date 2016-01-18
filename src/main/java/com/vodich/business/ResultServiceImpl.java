package com.vodich.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodich.core.bean.Result;
import com.vodich.core.util.JMSUtils;
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ResultDAOImpl;

public class ResultServiceImpl implements ResultService {

	private ResultDAO resultDAO;
	private JMSUtils jmsUtils;
	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public Result load(String id) {
		return resultDAO.load(id);
	}

	ResultServiceImpl(ResultDAO resultDAO, JMSUtils jmsUtils) {
		this.resultDAO = resultDAO;
		this.jmsUtils = jmsUtils;
	}

	private static ResultService instance;
	public static ResultService getInstance() {
		if (instance == null) {
			instance = new ResultServiceImpl(ResultDAOImpl.getInstance(), JMSUtils.getInstance());
		}
		return instance;
	}

	@Override
	public List<Result> loadAll() {
		return resultDAO.loadAll();
	}

	public String actualizeResult(String scenarioId) throws JMSException {
		String rid = null;
		try {
			String resultString = jmsUtils.receive();
			Map<String, Object> resultJson;
			try {
				resultJson = mapper.readValue(resultString, new TypeReference<Map<String, Object>>() {});
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			Result result = new Result();
			result.setFinishTime(new Date());
			result.setScenarioId(scenarioId);
			result.setResult((List<Object>) resultJson.get("messageResults"));
			rid = resultDAO.save(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jmsUtils.stopConnection();
		}
		return rid;
	}
}
