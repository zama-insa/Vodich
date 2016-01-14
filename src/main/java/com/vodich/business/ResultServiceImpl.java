package com.vodich.business;

import com.vodich.core.bean.Result;
import com.vodich.dao.ResultDAO;
import com.vodich.dao.ResultDAOImpl;

public class ResultServiceImpl implements ResultService {

	private ResultDAO resultDAO;
	@Override
	public Result load(String id) {
		return resultDAO.load(id);
	}

	ResultServiceImpl(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
	private static ResultService instance;
	public static ResultService getInstance() {
		if (instance == null) {
			instance = new ResultServiceImpl(ResultDAOImpl.getInstance());
		}
		return instance;
	}
}
