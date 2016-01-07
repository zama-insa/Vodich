package com.vodich.dao;

import com.vodich.core.bean.Result;

public class ResultDAOImpl implements ResultDAO {

	@Override
	public void save(Result result) {
		ElasticsearchUtils.saveScenarioResult(result);
	}

	@Override
	public ResultDAO load(String resultId) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResultDAOImpl() {}
	private static ResultDAO instance;
	public static ResultDAO getInstance() {
		if (instance == null) {
			instance = new ResultDAOImpl();
		}
		return instance;
	}

}
