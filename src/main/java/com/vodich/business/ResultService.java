package com.vodich.business;

import java.util.List;

import com.vodich.core.bean.Result;

public interface ResultService {
	public Result load(String id);
	
	public List<Result> loadAll();
}
