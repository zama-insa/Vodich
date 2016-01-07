package com.vodich.dao;

import com.vodich.core.bean.Result;

public interface ResultDAO {

	public void save(Result result);
	
	public Result load(String resultId);
}
