package com.vodich.business;

import java.util.List;

import javax.jms.JMSException;

import com.vodich.core.bean.Result;

public interface ResultService {
	public Result load(String id);
	
	public List<Result> loadAll();
	
	public String actualizeResult(String scenarioId) throws JMSException;
	
}
