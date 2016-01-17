package com.vodich.dao;

import java.util.List;

import com.vodich.core.bean.Result;

public class ResultDAOImpl implements ResultDAO {

	@Override
	public void save(Result result) {
		ElasticsearchUtils.saveScenarioResult(result);
	}

	@Override
	public Result load(String resultId) {
		return ElasticsearchUtils.loadScenarioResult(resultId);
	}

	@Override
	public List<Result> loadAll() {
		return ElasticsearchUtils.loadAllScenarioResults();
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
